package com.mydao.deploy.util;

import java.io.IOException;

import sun.tools.tree.NewInstanceExpression;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SFTPv3Client;

public class FileTool {
	private Connection conn;
	private SFTPv3Client sftp;
	private SCPClient scpClient;
	
	public FileTool() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public FileTool(String url, int port,String username, String password) throws IOException {
		conn = new Connection(url, port);
		conn.connect(); // 连接
		if (conn.authenticateWithPassword(username, password)) {
			sftp = new SFTPv3Client(conn);
			sftp.setCharset("UTF-8");
			scpClient = conn.createSCPClient();   
		}
	}


	public static FileTool getInstance(String url,// 服务器hostname
			int port,// 服务器端口
			String username, // 登录账号
			String password) throws IOException{
		return new FileTool(url, port, username, password);
	}

	/**
	 * Description: 向FTP服务器上传文件
	 * @Version      1.0
	 * @param url FTP服务器hostname
	 * @param port  FTP服务器端口
	 * @param username FTP登录账号
	 * @param password  FTP登录密码
	 * @param path  FTP服务器保存目录
	 * @param filename  上传到FTP服务器上的文件名
	 * @param input  输入流
	 * @return 成功返回true，否则返回false 
	 * @throws IOException *
	 */
	public FileTool uploadFile(
			String path, // 本地路径
			String remotepath // 远程目录
			) throws IOException {
        scpClient.put(path, remotepath);
		return this;
	}
	//测试
	public static void main(String[] args) throws IOException {
		FileTool.getInstance("192.168.0.69", 22, "root", "duyiwu2=").uploadFile("D:/Software/Shell/showlog.sh", "/home").close();
	}
	
	public void close(){
		conn.close();
	}
}