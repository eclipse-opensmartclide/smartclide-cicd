package com.smartclide.intrasoft.cicd.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartclide.intrasoft.cicd.core.BaseTemplate;
import com.smartclide.intrasoft.cicd.services.TemplateService;

public abstract class BaseTemplateResource<T extends BaseTemplate> {
	private TemplateService<T> service;
	private String attachmentFilename;
	
	public BaseTemplateResource(TemplateService<T> service) {
		this.service = service;
	}
	public BaseTemplateResource(TemplateService<T> service,String attachmentFilename) {
		this(service);
		this.attachmentFilename = attachmentFilename;
	}
	
	public TemplateService<T> getService() {
		return service;
	}
	
	/**
	 * A generic resource to fetch and create the tempalte based on type/implementation of the endpoint service
	 * Based on the type of the project will fetch the specific template 
	 * and replace the values with the given ones.
	 */
	@GetMapping("{type}")
	@ResponseBody
	public T getTemplate(@Validated T template) {
		return this.getService().createTemplate(template);
	}
	

	@GetMapping(value = "{type}/download",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public byte[] getTemaplteFile(@Validated T t,
			HttpServletResponse response) {
		response.setHeader("Content-Disposition", "attachment; filename="+attachmentFilename);
		return getTemplate(t).getContent().getBytes();
	}
}
