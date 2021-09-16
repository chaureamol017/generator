package com.mycomp.generator.curd.service;

import static com.mycomp.generator.curd.template.ClassTemplates.PACKAGE_TEMPLATE;

import java.util.HashMap;
import java.util.Map;

import com.mycomp.generator.curd.model.ClassDetails;
import com.mycomp.generator.curd.model.EntityModel;
import com.mycomp.generator.curd.model.ProjectDetails;
import com.mycomp.generator.curd.model.type.ClassType;

public abstract class AbstractGenerator {
	protected final ClassDetails classDetails = new ClassDetails();
	protected final Map<String,String> params = new HashMap<>();

	protected final ClassFileDetailsProvider fileDetailsProvider;
	protected final EntityModel entityModel;
	protected final ClassType classType;

	public AbstractGenerator(ProjectDetails projectDetails, EntityModel entityModel, ClassType classType) {
		this.entityModel = entityModel;
		this.classType = classType;
		this.fileDetailsProvider = new ClassFileDetailsProvider(classType, projectDetails, entityModel);
	}

	public ClassDetails generate() {
		String filePath = fileDetailsProvider.getFilePath();
		String fileName = entityModel.getClassName() + fileDetailsProvider.getClassPostFix(classType);

		fileDetailsProvider.populateParams(params);

		classDetails.setFilePath(filePath);
		classDetails.setFileName(fileName);

		setClassPackage();
		setClassImports();
		setClassStart();
		setClassFieldsAndFunctions();
		setClassEnd();

		return classDetails;
	}

	protected abstract void setClassImports();
	protected abstract void setClassStart();
	protected abstract void setClassFieldsAndFunctions();
	

	private void setClassPackage() {
		String classPackage = StringReplacer.repace(params, PACKAGE_TEMPLATE);
		classDetails.setClassPackage(classPackage);
	}

	private void setClassEnd() {
		String classEnd = "\r\n" + "}";
		classDetails.setClassEnd(classEnd);
	}
}
