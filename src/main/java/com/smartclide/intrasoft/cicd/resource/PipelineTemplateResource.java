package com.smartclide.intrasoft.cicd.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartclide.intrasoft.cicd.core.PipelineTemplate;
import com.smartclide.intrasoft.cicd.services.TemplateService;
import com.smartclide.intrasoft.cicd.startup.Configuration;

@RestController
@RequestMapping("templates/pipelines")
public class PipelineTemplateResource extends BaseTemplateResource<PipelineTemplate>  {
	@Autowired
	public PipelineTemplateResource(TemplateService<PipelineTemplate> service) {
		super(service,Configuration.getPipelineName());
	}
}
