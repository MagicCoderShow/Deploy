package com.mydao.deploy.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mydao.deploy.model.JavaWebConfig;
import com.mydao.deploy.model.JavaWebLinux;
import com.mydao.deploy.model.JavaWebProject;
import com.mydao.deploy.service.JavaWebConfigService;
import com.mydao.deploy.service.JavaWebLinuxService;
import com.mydao.deploy.service.JavaWebProjectService;

@Controller
@RequestMapping(value="/")
public class JavaWebController {
	@Autowired
	JavaWebProjectService projectService;
	@Autowired
	JavaWebLinuxService linuxService;
	@Autowired
	JavaWebConfigService configService;
	/**
	 * 首页，获取项目列表
	 * @param mv
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView index(ModelAndView mv){
		mv.setViewName("index");
		List<JavaWebProject> ps = projectService.selectAll();
		return mv.addObject("projects", ps);
	}
	/**
	 * 创建新项目
	 * @return
	 */
	@RequestMapping("/new/project")
	public ModelAndView newProject(@RequestBody @ModelAttribute JavaWebProject project){
		ModelAndView mv = new ModelAndView("new/project");
		if(project.getId()!=null){
			project = projectService.selectById(project.getId());
		}
		mv.addObject("project", project);
		return mv;
	}
	/**
	 * 创建linux服务器
	 * @return
	 */
	@RequestMapping("/new/linux")
	public ModelAndView newLinux(@RequestBody @ModelAttribute JavaWebLinux linux){
		ModelAndView mv = new ModelAndView("new/linux");
		if(linux.getId()!=null){
			linux = linuxService.selectById(linux.getId());
		}
		mv.addObject("linux", linux);
		return mv;
	}
	/**
	 * 创建linux服务器
	 * @return
	 */
	@RequestMapping("/new/config")
	public ModelAndView newLinux(@RequestBody @ModelAttribute JavaWebConfig config){
		ModelAndView mv = new ModelAndView("new/config");
		if(config.getId()!=null){
			config = configService.selectById(config.getId());
		}
		mv.addObject("config", config);
		return mv;
	}
	/**
	 * 添加新项目
	 * @param project
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/edit/project")
	public String insertProject(@RequestBody@ModelAttribute JavaWebProject project,@RequestParam(value = "file", required = false)MultipartFile file,HttpServletRequest request) throws IOException{
		//后缀
		project.setSuffix(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
		project.setFilename(file.getOriginalFilename());
		//保存文件
		File war = new File("/home/file/war/"+System.currentTimeMillis(),file.getOriginalFilename());
		if(!war.exists()){
			war.mkdirs();
		}
		file.transferTo(war);
		project.setWar(war.getAbsolutePath());
		if(project.getId()==null){
			projectService.insert(project);
		}else{
			projectService.update(project);
		}
		return "redirect:/";
	}
	/**
	 * 编辑linux服务器
	 * @param linux
	 * @return
	 */
	@RequestMapping("/edit/linux")
	public String insertLinux(@RequestBody@ModelAttribute JavaWebLinux linux){
		if(linux.getId()==null){
			linuxService.insert(linux);
		}else{
			linuxService.update(linux);
		}
		return "redirect:/detail/project/"+linux.getProjectid();
	}
	/**
	 * 添加新项目
	 * @param project
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/edit/config")
	public String editConfig(@RequestBody@ModelAttribute JavaWebConfig config,@RequestParam(value = "configfile", required = false)MultipartFile file,HttpServletRequest request) throws IOException{
		//后缀
		String filename = file.getOriginalFilename();
		config.setSuffix(filename.substring(filename.lastIndexOf(".")));
		config.setFilename(filename);
		//保存文件
		File cf = new File("/home/file/config/"+System.currentTimeMillis(),filename);
		if(!cf.exists()){
			cf.mkdirs();
		}
		file.transferTo(cf);
		config.setFile(cf.getAbsolutePath());
		if(config.getId()==null){
			configService.insert(config);
		}else{
			configService.update(config);
		}
		return "redirect:/detail/linux/"+config.getLinuxid();
	}
	
	
	
	/**
	 * 获取项目信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/detail/project/{id}")
	public ModelAndView showproject(@PathVariable("id")Long id){
		ModelAndView mv = new ModelAndView("detail/project");
		mv.addObject("project", projectService.selectById(id));
		mv.addObject("linuxs", linuxService.selectAll(id));
		return mv;
	}
	/**
	 * 获取linux信息
	 * @param projectid
	 * @return
	 */
	@RequestMapping("/detail/linux/{id}")
	public ModelAndView listlinux(@PathVariable("id")Long id){
		ModelAndView mv = new ModelAndView("detail/linux");
		mv.addObject("linux", linuxService.selectById(id));
		mv.addObject("config", configService.selectAll(id));
		return mv;
	}
	
	/**
	 * 删除项目
	 * @param project
	 * @return
	 */
	@RequestMapping("/delete/project")
	public String deleteProject(@RequestBody @ModelAttribute JavaWebProject project){
		projectService.delete(project.getId());
		return "redirect:/";
	}
	/**
	 * 删除linux服务器
	 * @param linux
	 * @return
	 */
	@RequestMapping("/delete/linux")
	public String deleteLinux(@RequestBody @ModelAttribute JavaWebLinux linux){
		linuxService.delete(linux.getId());
		return "redirect:/detail/project/"+linux.getProjectid();
	}
	/**
	 * 删除项目
	 * @param project
	 * @return
	 */
	@RequestMapping("/delete/config")
	public String deleteConfig(@RequestBody @ModelAttribute JavaWebConfig config){
		configService.delete(config.getId());
		return "redirect:/detail/linux/"+config.getLinuxid();
	}
	
	
	
	
	
	@ResponseBody
	@RequestMapping("/todeploy")
	public String deploy(Long id) throws IOException{
		return linuxService.deploy(id);
	}
	
	@ResponseBody
	@RequestMapping("/restart")
	public String restart(Long id) throws IOException{
		return linuxService.restart(id);
	}
	
	@ResponseBody
	@RequestMapping("/stop")
	public String stop(Long id) throws IOException{
		return linuxService.stop(id);
	}
	
	@ResponseBody
	@RequestMapping("/todeploy/linux")
	public String deploylinux(Long id) throws IOException{
		return linuxService.deploylinux(id);
	}
	
	@ResponseBody
	@RequestMapping("/restart/linux")
	public String restartlinux(Long id) throws IOException{
		return linuxService.restartlinux(id);
	}
	
	@ResponseBody
	@RequestMapping("/stop/linux")
	public String stoplinux(Long id) throws IOException{
		return linuxService.stoplinux(id);
	}
	
	
	@ResponseBody
	@RequestMapping("/status")
	public String status(Long id) throws IOException{
		return linuxService.getStatus(id);
	}
	@RequestMapping("/upload")
	public void upload(String filepath,HttpServletResponse response) throws IOException{
		File file = new File(filepath);
		if(file!=null){
			InputStream fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset(); 
			response.setContentType("application/octet-stream;charset=ISO8859-1");  
			ServletOutputStream outputStream = response.getOutputStream(); 
			String filename = new String((filepath.substring(filepath.lastIndexOf("\\")+1, filepath.length())).getBytes("gb2312"), "iso8859-1");
			response.setHeader("Content-disposition", "attachment; filename=" + filename);
			outputStream.write(buffer);
			outputStream.flush();
			outputStream.close();
		}
	}
	
}
