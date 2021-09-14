package com.smartclide.intrasoft.cicd.startup;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @todo Different configurations must be provided as well for different environments (eg. prod, dev, staging, testing, local...)
 */
@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages="com.smartclide.intrasoft")
@Component
public class Configuration {
	
	private static Configuration configuration;
	
	@PostConstruct
	public void init() {
		configuration = this;
	}
	
	@Value("${pipelinesFolder}")
	private String pipelinesFolder;
	
	@Value("${pipelineName}")
	private String pipelineName;
	
	@Value("${dockerFilesFolder}")
	private String dockerFilesFolder;
	
	
	public static String getPipelinesFolder() {
		return configuration.pipelinesFolder;
	}
	
	public static String getPipelineName() {
		return configuration.pipelineName;
	}
	
	public static String getDockerFilesFolder() {
		return configuration.dockerFilesFolder;
	}
	
}
