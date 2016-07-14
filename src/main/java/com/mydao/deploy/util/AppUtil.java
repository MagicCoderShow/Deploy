package com.mydao.deploy.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 工具类
 * @author 旭平
 *
 */
public class AppUtil {
	
	/**
	 * 获取Request
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attrs.getRequest();
		return request;
	}
	
	/**
	 * 获取Session
	 * @return
	 */
	public static HttpSession getSession(){
		return getRequest().getSession();
	}


}
