package com.mydao.deploy.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mydao.deploy.mapper.JavaWebConfigMapper;
import com.mydao.deploy.mapper.JavaWebLinuxMapper;
import com.mydao.deploy.mapper.JavaWebProjectMapper;
import com.mydao.deploy.model.JavaWebConfig;
import com.mydao.deploy.model.JavaWebLinux;
import com.mydao.deploy.model.JavaWebProject;
import com.mydao.deploy.util.FileTool;
import com.mydao.deploy.util.RemoteShellTool;

@Service("linuxService")
public class JavaWebLinuxService {
	@Autowired
	JavaWebLinuxMapper javaWebLinuxMapper;
	@Autowired
	JavaWebConfigMapper javaWebConfigMapper;
	@Autowired
	JavaWebProjectMapper javaWebProjectMapper;
	public int insert(JavaWebLinux linux){
		return javaWebLinuxMapper.insert(linux);
	}
	
	public int update(JavaWebLinux linux){
		return javaWebLinuxMapper.update(linux);
	}
	
	public List<JavaWebLinux> selectAll(Long projectid){
		return javaWebLinuxMapper.selectAll(projectid);
	}
	
	public JavaWebLinux selectById(Long id){
		return javaWebLinuxMapper.selectById(id);
	}
	public int delete(Long id){
		return javaWebLinuxMapper.delete(id);
	}
	
	
	public String getStatus(Long id) throws IOException {
		JavaWebLinux info = javaWebLinuxMapper.selectById(id);
		if(info != null) {
			RemoteShellTool tool = new RemoteShellTool(info.getIp(), info.getLoginname(),
					info.getPassword(), "utf-8");
			tool.exec("mkdir -p /home/tomcat/sh");
			FileTool.getInstance(info.getIp(), 22, info.getLoginname(), info.getPassword()).uploadFile("/home/file/sh/isrunning.sh", "/home/tomcat/sh").close();
			String out = tool.exec("sh " +"/home/tomcat/sh/isrunning.sh" + " java");
			return String.valueOf(StringUtils.hasText(out) && out.contains(info.getPath()));
		} else {
			return "false";
		}
	}
	public String deploy(Long id) throws IOException{
		JavaWebProject project = javaWebProjectMapper.selectById(id);
		List<JavaWebLinux> linux = javaWebLinuxMapper.selectAll(id);
		StringBuffer sb =new StringBuffer();
		for (JavaWebLinux l : linux) {
			sb.append(l.getIp()+":"+l.getPath()+"\n");
			RemoteShellTool tool = new RemoteShellTool(l.getIp(), l.getLoginname(),
					l.getPassword(), "utf-8");
			tool.exec("mkdir -p /home/tomcat/war");
			FileTool ft = FileTool.getInstance(l.getIp(), 22, l.getLoginname(), l.getPassword());
			ft.uploadFile(project.getWar(), "/home/tomcat/war");
			tool.exec("mkdir -p /home/tomcat/config");
			List<JavaWebConfig> configs = javaWebConfigMapper.selectAll(l.getId());
			for (JavaWebConfig config : configs) {
				ft.uploadFile(config.getFile(), "/home/tomcat/config");
			}
//			tool.exec("mkdir /home/tomcat/temp/"+project.getFilename().replace(project.getSuffix(), ""));
			tool.exec("mkdir -p /home/tomcat/sh");
			ft.uploadFile("/home/file/sh/deploy.sh", "/home/tomcat/sh").close();
			sb.append(tool.exec("sh /home/tomcat/sh/deploy.sh "+project.getFilename().replace(project.getSuffix(), "")+" "+project.getFilename()+" "+l.getPath())+"\n");
		}
		return sb.toString();
	}
	
	public String stop(Long id) throws IOException{
		JavaWebProject project = javaWebProjectMapper.selectById(id);
		List<JavaWebLinux> linux = javaWebLinuxMapper.selectAll(id);
		StringBuffer sb =new StringBuffer();
		for (JavaWebLinux l : linux) {
			sb.append(l.getIp()+":"+l.getPath()+"\n");
			RemoteShellTool tool = new RemoteShellTool(l.getIp(), l.getLoginname(),
					l.getPassword(), "utf-8");
			FileTool ft = FileTool.getInstance(l.getIp(), 22, l.getLoginname(), l.getPassword());
			tool.exec("mkdir -p /home/tomcat/sh");
			ft.uploadFile("/home/file/sh/stop.sh", "/home/tomcat/sh").close();
			String result = tool.exec("sh /home/tomcat/sh/stop.sh "+l.getPath())+"\n";
			sb.append(result);
		}
		return sb.toString();
	}
	
	public String restart(Long id) throws IOException{
		JavaWebProject project = javaWebProjectMapper.selectById(id);
		List<JavaWebLinux> linux = javaWebLinuxMapper.selectAll(id);
		StringBuffer sb =new StringBuffer();
		for (JavaWebLinux l : linux) {
			sb.append(l.getIp()+":"+l.getPath()+"\n");
			RemoteShellTool tool = new RemoteShellTool(l.getIp(), l.getLoginname(),
					l.getPassword(), "utf-8");
			FileTool ft = FileTool.getInstance(l.getIp(), 22, l.getLoginname(), l.getPassword());
			tool.exec("mkdir -p /home/tomcat/sh");
			ft.uploadFile("/home/file/sh/restart.sh", "/home/tomcat/sh").close();
			sb.append(tool.exec("sh /home/tomcat/sh/restart.sh "+l.getPath())+"\n");
		}
		return sb.toString();
	}
	
	
	public String deploylinux(Long id) throws IOException{
		JavaWebLinux linux = javaWebLinuxMapper.selectById(id);
		JavaWebProject project = javaWebProjectMapper.selectById(linux.getProjectid());
		StringBuffer sb =new StringBuffer();
		sb.append(linux.getIp()+":"+linux.getPath()+"\n");
			RemoteShellTool tool = new RemoteShellTool(linux.getIp(), linux.getLoginname(),
					linux.getPassword(), "utf-8");
			tool.exec("mkdir -p /home/tomcat/war");
			FileTool ft = FileTool.getInstance(linux.getIp(), 22, linux.getLoginname(),linux.getPassword());
			ft.uploadFile(project.getWar(), "/home/tomcat/war");
			tool.exec("mkdir -p /home/tomcat/config");
			List<JavaWebConfig> configs = javaWebConfigMapper.selectAll(linux.getId());
			for (JavaWebConfig config : configs) {
				ft.uploadFile(config.getFile(), "/home/tomcat/config");
			}
//			tool.exec("mkdir /home/tomcat/temp/"+project.getFilename().replace(project.getSuffix(), ""));
			tool.exec("mkdir -p /home/tomcat/sh");
			ft.uploadFile("/home/file/sh/deploy.sh", "/home/tomcat/sh").close();
			sb.append(tool.exec("sh /home/tomcat/sh/deploy.sh "+project.getFilename().replace(project.getSuffix(), "")+" "+project.getFilename()+" "+linux.getPath())+"\n");
		return sb.toString();
	}
	
	public String stoplinux(Long id) throws IOException{
		JavaWebLinux l = javaWebLinuxMapper.selectById(id);
		JavaWebProject project = javaWebProjectMapper.selectById(l.getProjectid());
		StringBuffer sb =new StringBuffer();
			sb.append(l.getIp()+":"+l.getPath()+"\n");
			RemoteShellTool tool = new RemoteShellTool(l.getIp(), l.getLoginname(),
					l.getPassword(), "utf-8");
			FileTool ft = FileTool.getInstance(l.getIp(), 22, l.getLoginname(), l.getPassword());
			tool.exec("mkdir -p /home/tomcat/sh");
			ft.uploadFile("/home/file/sh/stop.sh", "/home/tomcat/sh").close();
			String result = tool.exec("sh /home/tomcat/sh/stop.sh "+l.getPath())+"\n";
			sb.append(result);
		return sb.toString();
	}
	
	public String restartlinux(Long id) throws IOException{
		JavaWebLinux l = javaWebLinuxMapper.selectById(id);
		JavaWebProject project = javaWebProjectMapper.selectById(l.getProjectid());
		StringBuffer sb =new StringBuffer();
			sb.append(l.getIp()+":"+l.getPath()+"\n");
			RemoteShellTool tool = new RemoteShellTool(l.getIp(), l.getLoginname(),
					l.getPassword(), "utf-8");
			FileTool ft = FileTool.getInstance(l.getIp(), 22, l.getLoginname(), l.getPassword());
			tool.exec("mkdir -p /home/tomcat/sh");
			ft.uploadFile("/home/file/sh/restart.sh", "/home/tomcat/sh").close();
			sb.append(tool.exec("sh /home/tomcat/sh/restart.sh "+l.getPath())+"\n");
		return sb.toString();
	}
	
}
