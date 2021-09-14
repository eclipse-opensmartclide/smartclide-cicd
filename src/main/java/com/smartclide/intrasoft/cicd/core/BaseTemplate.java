package com.smartclide.intrasoft.cicd.core;

import javax.validation.constraints.NotBlank;

public abstract class BaseTemplate {
	@NotBlank(message = "type cannot be empty")
	private String type;
	private String content;
	
	public BaseTemplate() {}
	public BaseTemplate(String type) {this.type = type;}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
