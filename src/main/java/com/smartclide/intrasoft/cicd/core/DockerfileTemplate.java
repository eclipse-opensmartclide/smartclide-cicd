package com.smartclide.intrasoft.cicd.core;

import javax.validation.constraints.NotBlank;

public class DockerfileTemplate extends BaseTemplate {
	
	@NotBlank(message = "extension cannot be empty")
	private String extension;
	
	public DockerfileTemplate(String type,String ext) {
		super(type);
		this.extension = ext;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
}
