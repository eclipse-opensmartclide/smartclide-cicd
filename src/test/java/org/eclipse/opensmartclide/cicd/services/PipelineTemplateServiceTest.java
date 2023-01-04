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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.eclipse.opensmartclide.cicd.core.PipelineTemplate;
import org.eclipse.opensmartclide.cicd.startup.Application;
import org.eclipse.opensmartclide.cicd.startup.Configuration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class, Configuration.class})
public class PipelineTemplateServiceTest {
	@Autowired
	private TemplateService<PipelineTemplate> service;

	@ParameterizedTest
	@MethodSource("org.eclipse.opensmartclide.cicd.services.TemplateProvider#pipelineTemplateProvider")
	public void testCreateTemplate(PipelineTemplate t,String value,boolean expected) {
		assertEquals(expected,service.createTemplate(t).getContent().contains(value));
	}
}
