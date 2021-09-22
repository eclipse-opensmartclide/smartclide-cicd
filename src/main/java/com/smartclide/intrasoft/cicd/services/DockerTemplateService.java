package com.smartclide.intrasoft.cicd.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.smartclide.intrasoft.cicd.core.DockerfileTemplate;
import com.smartclide.intrasoft.cicd.startup.Configuration;

@Service
public class DockerTemplateService implements TemplateService<DockerfileTemplate> {
	
	@Override
	public DockerfileTemplate createTemplate(DockerfileTemplate template) {
		template.setContent(fetchDockerfileContent(template.getType())
				.replaceAll("%EXTRA_BUILD_COMMANDS%", template.getExtraCommands())
				.replaceAll("%EXTENSION%", template.getExtension()));
		return template;
	}
	
	
	private String fetchDockerfileContent(String type) {
		try {
			return new String(new ClassPathResource(Configuration.getDockerFilesFolder()+type.toLowerCase()
				+".Dockerfile").getInputStream().readAllBytes(),StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
