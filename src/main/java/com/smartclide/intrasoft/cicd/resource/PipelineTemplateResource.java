/*******************************************************************************
 * Copyright (c) 2021 INTRASOFT International
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     avraampiperidis - initial API and implementation
 *******************************************************************************/
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
