package com.mycomp.generator.curd.service;

import com.mycomp.generator.curd.model.EntityModel;
import com.mycomp.generator.curd.model.ProjectDetails;
import com.mycomp.generator.curd.model.type.ClassType;

import static com.mycomp.generator.curd.template.CommonTemplates.NEW_LINE;

import static com.mycomp.generator.curd.template.ClassTemplates.CLASS_START_TEMPLATE;
import static com.mycomp.generator.curd.template.ClassTemplates.SAVE_FUNCTION_SERVICE_TEMPLATE;
import static com.mycomp.generator.curd.template.ClassTemplates.UPDATE_FUNCTION_SERVICE_TEMPLATE;
import static com.mycomp.generator.curd.template.ClassTemplates.GET_ALL_FUNCTION_SERVICE_TEMPLATE;
import static com.mycomp.generator.curd.template.ClassTemplates.GET_FUNCTION_SERVICE_TEMPLATE;
import static com.mycomp.generator.curd.template.ClassTemplates.DELETE_FUNCTION_SERVICE_TEMPLATE;

public class ServiceGenerator extends AbstractGenerator {

	public ServiceGenerator(ProjectDetails projectDetails, EntityModel entityModel, ClassType classType) {
		super(projectDetails, entityModel, classType);
	}

	@Override
	protected void setClassImports() {
		String classImports = NEW_LINE + "import java.util.List;" + NEW_LINE + NEW_LINE 
				+ fileDetailsProvider.getClassImport(ClassType.MODEL)
				+ NEW_LINE;

		classDetails.setClassImports(classImports);
	}

	@Override
	protected void setClassStart() {
		String classStart = NEW_LINE + StringReplacer.repace(params, CLASS_START_TEMPLATE);

		classDetails.setClassStart(classStart);
	}

	@Override
	protected void setClassFieldsAndFunctions() {
		classDetails
				.addClassFunctions(NEW_LINE + StringReplacer.repace(params, SAVE_FUNCTION_SERVICE_TEMPLATE) + NEW_LINE);
		classDetails.addClassFunctions(
				NEW_LINE + StringReplacer.repace(params, UPDATE_FUNCTION_SERVICE_TEMPLATE) + NEW_LINE);
		classDetails.addClassFunctions(
				NEW_LINE + StringReplacer.repace(params, GET_ALL_FUNCTION_SERVICE_TEMPLATE) + NEW_LINE);
		classDetails
				.addClassFunctions(NEW_LINE + StringReplacer.repace(params, GET_FUNCTION_SERVICE_TEMPLATE) + NEW_LINE);
		classDetails.addClassFunctions(
				NEW_LINE + StringReplacer.repace(params, DELETE_FUNCTION_SERVICE_TEMPLATE) + NEW_LINE);

	}

}
