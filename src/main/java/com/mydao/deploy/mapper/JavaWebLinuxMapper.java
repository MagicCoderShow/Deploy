package com.mydao.deploy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mydao.deploy.model.JavaWebLinux;

public interface JavaWebLinuxMapper {
	@Insert("INSERT INTO `deploy`.`java_web_linux` (`id`, `ip`, `loginname`, `password`, `version`, `projectid`,path) VALUES (#{id,jdbcType=INTEGER}, #{ip,jdbcType=VARCHAR}, #{loginname,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, #{version,jdbcType=VARCHAR}, #{projectid,jdbcType=INTEGER},#{path,jdbcType=VARCHAR})")
	@Options(keyProperty="id",useGeneratedKeys=true)
	public int insert(JavaWebLinux linux);
	
	@Update("UPDATE `deploy`.`java_web_linux` SET `ip`=#{ip,jdbcType=VARCHAR}, `loginname`=#{loginname,jdbcType=VARCHAR}, `password`=#{password,jdbcType=VARCHAR}, `version`=#{version,jdbcType=VARCHAR}, `projectid`= #{projectid,jdbcType=INTEGER},path = #{path,jdbcType=VARCHAR} WHERE  id = #{id,jdbcType=INTEGER}")
	public int update(JavaWebLinux linux);
	
	@Delete("delete from java_web_linux where id = #{id,jdbcType=INTEGER}")
	public int delete(@Param("id") Long id);
	
	@Select("select * from java_web_linux where projectid=#{projectid,jdbcType=INTEGER}")
	@ResultType(JavaWebLinux.class)
	public List<JavaWebLinux> selectAll(@Param("projectid")Long projectid);
	
	@Select("select * from java_web_linux where id = #{id,jdbcType=INTEGER}")
	@ResultType(JavaWebLinux.class)
	public JavaWebLinux selectById(@Param("id")Long id);
}
