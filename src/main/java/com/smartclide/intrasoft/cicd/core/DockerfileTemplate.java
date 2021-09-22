package com.smartclide.intrasoft.cicd.core;

import javax.validation.constraints.NotBlank;

public class DockerfileTemplate extends BaseTemplate {
	
	@NotBlank(message = "extension cannot be empty")
	private String extension;
	private String extraCommands = "";
	
	public DockerfileTemplate() {}
	
	public DockerfileTemplate(String type,String ext) {
		super(type);
		this.extension = ext;
	}
	
	public DockerfileTemplate(String type,String ext,String extraCommands) {
		this(type,ext);
		this.extraCommands = extraCommands;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getExtraCommands() {
		return extraCommands;
	}

	public void setExtraCommands(String extraCommands) {
		this.extraCommands = extraCommands;
	}
	
}
