package com.mycomp.generator.curd.service;

import static com.mycomp.generator.common.ClassTemplates.CLASS_START_TEMPLATE;
import static com.mycomp.generator.common.CommonTemplates.NEW_LINE;

import com.mycomp.generator.common.StringReplacer;
import com.mycomp.generator.curd.model.EntityModel;
import com.mycomp.generator.curd.model.ProjectDetails;
import com.mycomp.generator.curd.model.type.ClassType;

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
