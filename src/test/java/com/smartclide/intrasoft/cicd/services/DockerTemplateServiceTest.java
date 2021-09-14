package com.smartclide.intrasoft.cicd.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.smartclide.intrasoft.cicd.core.DockerfileTemplate;
import com.smartclide.intrasoft.cicd.startup.Application;
import com.smartclide.intrasoft.cicd.startup.Configuration;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class,Configuration.class})
public class DockerTemplateServiceTest {
	@Autowired
	private TemplateService<DockerfileTemplate> service;
	
	@ParameterizedTest
	@MethodSource("com.smartclide.intrasoft.cicd.services.TemplateProvider#dockerTemplateProvider")
	public void testCreateTemplate(DockerfileTemplate t,String value,boolean expected) {
		assertEquals(expected,service.createTemplate(t).getContent().contains(value));
	}
}
