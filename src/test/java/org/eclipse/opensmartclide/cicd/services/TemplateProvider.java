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

import java.util.Arrays;
import java.util.stream.Stream;

import org.eclipse.opensmartclide.cicd.core.DockerfileTemplate;
import org.eclipse.opensmartclide.cicd.core.PipelineTemplate;
import org.junit.jupiter.params.provider.Arguments;

public class TemplateProvider {

	static Stream<Arguments> pipelineTemplateProvider() {
		PipelineTemplate t2 = new PipelineTemplate("generic","test_app",null);
		t2.setReleaseOnly("DEVELOPMENT_BRANCH");
		t2.setBuildCommand("mvn clean package");
		t2.setTestCommand("mvn test -DskipTests=false");
		t2.setVariables(Arrays.asList("GIT_DEPTH: 10","PARAM: VALUE"));
		return Stream.of(
				Arguments.of(new PipelineTemplate("maven", "TEST_APP", null),"TEST_APP",true),
				Arguments.of(new PipelineTemplate("generic", "TEST_APP", null),"TEST_APP",true),
				Arguments.of(t2,t2.getReleaseOnly(),true),
				Arguments.of(t2,"master",false),
				Arguments.of(t2,t2.getBuildCommand(),true),
				Arguments.of(t2,t2.getTestCommand(),true),
				Arguments.of(t2,t2.getVariables().get(0),true),
				Arguments.of(t2,t2.getVariables().get(1),true),
				Arguments.of(t2,"VAR: WRONG",false)
		);
	}


	static Stream<Arguments> dockerTemplateProvider() {
		return Stream.of(
				Arguments.of(new DockerfileTemplate("maven","jar"),".jar",true),
				Arguments.of(new DockerfileTemplate("maven","war"),".war",true),
				Arguments.of(new DockerfileTemplate("maven","war"),".war",true),
				Arguments.of(new DockerfileTemplate("maven","exe","myCommand"),"myCommand",true)
			);
	}

}
