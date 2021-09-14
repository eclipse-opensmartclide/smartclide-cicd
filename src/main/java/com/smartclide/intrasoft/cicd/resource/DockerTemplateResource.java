package com.smartclide.intrasoft.cicd.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.smartclide.intrasoft.cicd.core.DockerfileTemplate;
import com.smartclide.intrasoft.cicd.services.TemplateService;

@RestController
@RequestMapping("templates/dockerfiles")
public class DockerTemplateResource extends BaseTemplateResource<DockerfileTemplate> {
	@Autowired
	public DockerTemplateResource(TemplateService<DockerfileTemplate> service) {
		super(service,"Dockerfile");
	}
	
	@GetMapping(value = "dockerignore")
	@ResponseBody
	public String getDockerIngore(@RequestParam(value="ignores", required = false) String[] ignores) {
		var sBuilder = new StringBuilder("target/ \n").append("build/ \n");
		if(ignores != null && ignores.length > 0) {
			for (String ignore : ignores) {
				sBuilder.append(ignore + "\n");
			}
		}
		return sBuilder.toString();
	}
	
	@GetMapping(value = "dockerignore/download",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public byte[] getDockerIgnoreFile(@RequestParam(value="ignores", required = false) String[] ignores,HttpServletResponse response) {
		response.setHeader("Content-Disposition", "attachment; filename=.dockerignore");
		return getDockerIngore(ignores).getBytes();
	}
}
