package com.smartclide.intrasoft.cicd.services;

import com.smartclide.intrasoft.cicd.core.BaseTemplate;

public interface TemplateService<T extends BaseTemplate> {
	T createTemplate(T t);
}
