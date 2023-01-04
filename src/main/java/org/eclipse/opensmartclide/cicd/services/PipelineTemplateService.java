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
package org.eclipse.opensmartclide.cicd.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.eclipse.opensmartclide.cicd.core.PipelineTemplate;
import org.eclipse.opensmartclide.cicd.startup.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;


/**
 * A concrete pipeline generator service
 */
@Service
public class PipelineTemplateService implements TemplateService<PipelineTemplate> {
	/**
	 * @param pipeline
	 * @return the pipeline
	 *
	 * @todo replace with visitor pattern or something else
	 */
	@Override
	public PipelineTemplate createTemplate(PipelineTemplate template) {
		var content = setCommonPipelineContent(fetchPipelineContent(template.getType()),template);
		switch (template.getType().toLowerCase()) {
		case "maven":
			setPipelineContentMaven(content,template);
			break;
		case "gradle":
			setPipelineContentMaven(content,template);
			break;
		default:
			setPipelineContentGeneric(content,template);
			break;
		}
		return template;
	}


	private void setPipelineContentMaven(String content,PipelineTemplate pipeline) {
		pipeline.setContent(content.replaceAll("%APP_NAME%",pipeline.getAppName())
				.replaceAll("%RELEASE_ONLY%", pipeline.getReleaseOnly() == null ? "master" : pipeline.getReleaseOnly()));
	}


	private void setPipelineContentGeneric(String content,PipelineTemplate pipeline) {
		content = content.replaceAll("%PIPELINE_IMAGE%", pipeline.getImage() == null ? "alpine" : pipeline.getImage())
				.replaceAll("%APP_NAME%", pipeline.getAppName())
				.replaceAll("%BUILD_COMMAND%", pipeline.getBuildCommand() == null ? "echo \"Running build command...\"" : pipeline.getBuildCommand())
				.replaceAll("%TEST_COMMAND%", pipeline.getTestCommand() == null ? "echo \"Running test command...\"" : pipeline.getTestCommand())
				.replaceAll("%RELEASE_ONLY%", pipeline.getReleaseOnly() == null ? "master" : pipeline.getReleaseOnly());
		pipeline.setContent(content);
	}

	@Autowired
	ResourceLoader resourceLoader;


	private String fetchPipelineContent(String type) {

		try {
			return new String(new ClassPathResource(Configuration.getPipelinesFolder()+type.toLowerCase()
				+Configuration.getPipelineName()).getInputStream().readAllBytes(),StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String setCommonPipelineContent(String content,PipelineTemplate pipeline) {
		if(content == null) {
			throw new RuntimeException("pipeline content cannot be empty!");
		}
		var builder = new StringBuilder("");
		if(pipeline.getVariables() != null) {
			for (var variable : pipeline.getVariables()) {
				builder.append("  "+variable+" \n");
			}
		}
		return content.replaceAll("%APP_NAME%", pipeline.getAppName()).replaceAll("%PARAMS%", builder.toString());
	}

}
