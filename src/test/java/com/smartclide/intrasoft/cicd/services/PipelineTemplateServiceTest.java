package com.smartclide.intrasoft.cicd.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.smartclide.intrasoft.cicd.core.PipelineTemplate;
import com.smartclide.intrasoft.cicd.startup.Application;
import com.smartclide.intrasoft.cicd.startup.Configuration;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class,Configuration.class})
public class PipelineTemplateServiceTest {
	@Autowired 
	private TemplateService<PipelineTemplate> service;
	
	@ParameterizedTest
	@MethodSource("com.smartclide.intrasoft.cicd.services.TemplateProvider#pipelineTemplateProvider")
	public void testCreateTemplate(PipelineTemplate t,String value,boolean expected) {
		assertEquals(expected,service.createTemplate(t).getContent().contains(value));
	}
}
