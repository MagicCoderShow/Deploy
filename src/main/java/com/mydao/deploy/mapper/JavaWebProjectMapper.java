package com.mydao.deploy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mydao.deploy.model.JavaWebProject;

public interface JavaWebProjectMapper {
	@Insert("INSERT INTO `deploy`.`java_web_project` (`id`, `name`, `profile`, `type`, `url`, `contextpath`, `port`, `war`,suffix,filename) VALUES (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, #{profile,jdbcType=VARCHAR}, #{type,jdbcType=INTEGER}, #{url,jdbcType=VARCHAR}, #{contextPath,jdbcType=VARCHAR}, #{port,jdbcType=INTEGER}, #{war,jdbcType=VARCHAR},#{suffix,jdbcType=VARCHAR},#{filename,jdbcType=VARCHAR})")
	@Options(keyProperty="id",useGeneratedKeys=true)
	public int insert(JavaWebProject project);
	
	@Update("UPDATE `deploy`.`java_web_project` SET  `name`=#{name,jdbcType=VARCHAR}, `profile`=#{profile,jdbcType=VARCHAR}, `type`=#{type,jdbcType=INTEGER}, `url`=#{url,jdbcType=VARCHAR}, `contextpath`= #{contextPath,jdbcType=VARCHAR}, `port`= #{port,jdbcType=INTEGER}, `war`=#{war,jdbcType=VARCHAR} ,suffix=#{suffix,jdbcType=VARCHAR},filename=#{filename,jdbcType=VARCHAR} WHERE id = #{id,jdbcType=INTEGER}")
	public int update(JavaWebProject project);
	
	@Delete("delete from java_web_project where id = #{id,jdbcType=INTEGER}")
	public int delete(@Param("id") Long id);
	
	@Select("select * from java_web_project")
	@ResultType(JavaWebProject.class)
	public List<JavaWebProject> selectAll();
	
	@Select("select * from java_web_project where id = #{id,jdbcType=INTEGER}")
	@ResultType(JavaWebProject.class)
	public JavaWebProject selectById(@Param("id")Long id);
}
