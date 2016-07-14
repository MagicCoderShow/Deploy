package com.mydao.deploy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydao.deploy.mapper.JavaWebProjectMapper;
import com.mydao.deploy.model.JavaWebProject;

@Service("projectService")
public class JavaWebProjectService {
	@Autowired
	JavaWebProjectMapper javaWebProjectMapper;
	public int insert(JavaWebProject project){
		return javaWebProjectMapper.insert(project);
	}
	
	public int update(JavaWebProject project){
		return javaWebProjectMapper.update(project);
	}
	
	public List<JavaWebProject> selectAll(){
		return javaWebProjectMapper.selectAll();
	}
	
	public JavaWebProject selectById(Long id){
		return javaWebProjectMapper.selectById(id);
	}
	public int delete(Long id){
		return javaWebProjectMapper.delete(id);
	}
}
