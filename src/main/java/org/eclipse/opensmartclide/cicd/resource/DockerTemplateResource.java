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
package org.eclipse.opensmartclide.cicd.resource;

import javax.servlet.http.HttpServletResponse;

import org.eclipse.opensmartclide.cicd.core.DockerfileTemplate;
import org.eclipse.opensmartclide.cicd.services.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
