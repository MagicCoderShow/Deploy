package com.mydao.deploy.model;

import java.sql.Blob;

public class JavaWebConfig {
	private Long id;
	private String name;
	private Long linuxid;
	private String file;
	private String suffix;
    private String filename;
    public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getLinuxid() {
		return linuxid;
	}
	public void setLinuxid(Long linuxid) {
		this.linuxid = linuxid;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}


	
}
