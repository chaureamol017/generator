package com.mycomp.generator.curd.service;

import com.mycomp.generator.curd.model.EntityModel;
import com.mycomp.generator.curd.model.ProjectDetails;
import com.mycomp.generator.curd.model.type.ClassType;

import static com.mycomp.generator.curd.template.CommonTemplates.NEW_LINE;

import static com.mycomp.generator.curd.template.ClassTemplates.CLASS_START_TEMPLATE;

public class DaoGenerator extends AbstractGenerator {
	
	public DaoGenerator(ProjectDetails projectDetails, EntityModel entityModel, ClassType classType) {
		super(projectDetails, entityModel, classType);
	}

	@Override
	protected void setClassImports() {
		String classImports = NEW_LINE + "import org.springframework.data.jpa.repository.JpaRepository;\r\n"
				+ "import org.springframework.stereotype.Repository;\r\n" + NEW_LINE
				+ fileDetailsProvider.getClassImport(ClassType.ENTITY) + NEW_LINE;

		classDetails.setClassImports(classImports);
	}


	@Override
	protected void setClassStart() {
		String classStart = NEW_LINE + "@Repository" + NEW_LINE + StringReplacer.repace(params, CLASS_START_TEMPLATE);

		classDetails.setClassStart(classStart);
	}

	@Override
	protected void setClassFieldsAndFunctions() {
		
	}

}
