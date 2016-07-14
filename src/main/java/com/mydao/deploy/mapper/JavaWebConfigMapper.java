package com.mydao.deploy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mydao.deploy.model.JavaWebConfig;

public interface JavaWebConfigMapper {
	@Insert("INSERT INTO `deploy`.`java_web_config` (`id`, `name`, `linuxid`, `file`,suffix,filename) VALUES (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, #{linuxid,jdbcType=INTEGER}, #{file,jdbcType=VARCHAR},#{suffix,jdbcType=VARCHAR},#{filename,jdbcType=VARCHAR})")
	@Options(keyProperty="id",useGeneratedKeys=true)
	public int insert(JavaWebConfig config);
	
	@Update("UPDATE `deploy`.`java_web_config` SET `name`=#{name,jdbcType=VARCHAR}, `linuxid`=#{linuxid,jdbcType=INTEGER}, `file`=#{file,jdbcType=VARCHAR},suffix=#{suffix,jdbcType=VARCHAR},filename=#{filename,jdbcType=VARCHAR} WHERE id = #{id,jdbcType=INTEGER}")
	public int update(JavaWebConfig config);
	
	@Delete("delete from java_web_config where id = #{id,jdbcType=INTEGER}")
	public int delete(@Param("id") Long id);
	
	@Select("select * from java_web_config where linuxid = #{linuxid,jdbcType=INTEGER}")
	@ResultType(JavaWebConfig.class)
	public List<JavaWebConfig> selectAll(@Param("linuxid")Long linuxid);
	
	@Select("select * from java_web_config where id = #{id,jdbcType=INTEGER}")
	@ResultType(JavaWebConfig.class)
	public JavaWebConfig selectById(@Param("id")Long id);
}
