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
package org.eclipse.opensmartclide.cicd.core;

import javax.validation.constraints.NotBlank;

public class DockerfileTemplate extends BaseTemplate {

	@NotBlank(message = "extension cannot be empty")
	private String extension;
	private String extraCommands = "";

	public DockerfileTemplate() {}

	public DockerfileTemplate(String type,String ext) {
		super(type);
		this.extension = ext;
	}

	public DockerfileTemplate(String type,String ext,String extraCommands) {
		this(type,ext);
		this.extraCommands = extraCommands;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getExtraCommands() {
		return extraCommands;
	}

	public void setExtraCommands(String extraCommands) {
		this.extraCommands = extraCommands;
	}

}
