package com.mydao.deploy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydao.deploy.mapper.JavaWebConfigMapper;
import com.mydao.deploy.model.JavaWebConfig;

@Service("configService")
public class JavaWebConfigService {
	@Autowired
	JavaWebConfigMapper javaWebConfigMapper;
	public int insert(JavaWebConfig Config){
		return javaWebConfigMapper.insert(Config);
	}
	
	public int update(JavaWebConfig Config){
		return javaWebConfigMapper.update(Config);
	}
	
	public List<JavaWebConfig> selectAll(Long linuxid){
		return javaWebConfigMapper.selectAll(linuxid);
	}
	
	public JavaWebConfig selectById(Long id){
		return javaWebConfigMapper.selectById(id);
	}
	public int delete(Long id){
		return javaWebConfigMapper.delete(id);
	}
}
