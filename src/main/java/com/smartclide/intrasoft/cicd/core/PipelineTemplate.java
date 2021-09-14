package com.smartclide.intrasoft.cicd.core;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.smartclide.intrasoft.cicd.startup.Configuration;


public class PipelineTemplate extends BaseTemplate {
	
	private String appName = "";
	
	private List<String> variables;
	
	private String image;
	private String testWhen;
	private String testOnly;
	private String reportOnly;
	private String reportWhen;
	private String releaseOnly;
	private String releaseWhen;
	
	private String buildCommand;
	private String testCommand;
	
	
	public PipelineTemplate(String type,String name,String content) {
		this.setType(type);
		this.appName = name;
		this.setContent(content);
	}
	public PipelineTemplate() {}
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String name) {
		this.appName = name;
	}
	public List<String> getVariables() {
		return variables;
	}
	public void setVariables(List<String> variables) {
		this.variables = variables;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTestWhen() {
		return testWhen;
	}
	public void setTestWhen(String testWhen) {
		this.testWhen = testWhen;
	}
	public String getTestOnly() {
		return testOnly;
	}
	public void setTestOnly(String testOnly) {
		this.testOnly = testOnly;
	}
	public String getReportOnly() {
		return reportOnly;
	}
	public void setReportOnly(String reportOnly) {
		this.reportOnly = reportOnly;
	}
	public String getReportWhen() {
		return reportWhen;
	}
	public void setReportWhen(String reportWhen) {
		this.reportWhen = reportWhen;
	}
	public String getReleaseOnly() {
		return releaseOnly;
	}
	public void setReleaseOnly(String releaseOnly) {
		this.releaseOnly = releaseOnly;
	}
	public String getReleaseWhen() {
		return releaseWhen;
	}
	public void setReleaseWhen(String releaseWhen) {
		this.releaseWhen = releaseWhen;
	}
	public String getBuildCommand() {
		return buildCommand;
	}
	public void setBuildCommand(String buildCommand) {
		this.buildCommand = buildCommand;
	}
	public String getTestCommand() {
		return testCommand;
	}
	public void setTestCommand(String testCommand) {
		this.testCommand = testCommand;
	}
}
