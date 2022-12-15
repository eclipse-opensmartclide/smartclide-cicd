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

import org.eclipse.opensmartclide.cicd.core.DockerfileTemplate;
import org.eclipse.opensmartclide.cicd.startup.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

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
