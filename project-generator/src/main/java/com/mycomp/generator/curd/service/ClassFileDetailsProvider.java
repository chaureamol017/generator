package com.mycomp.generator.curd.service;

import static com.mycomp.generator.curd.template.CommonTemplates.CLASS_NAME;
import static com.mycomp.generator.curd.template.CommonTemplates.CLASS_PACKAGE;
import static com.mycomp.generator.curd.template.CommonTemplates.CLASS_POST_FIX;
import static com.mycomp.generator.curd.template.CommonTemplates.CLASS_TYPE;
import static com.mycomp.generator.curd.template.CommonTemplates.COLUMN_NAME;
import static com.mycomp.generator.curd.template.CommonTemplates.EXTENTIONS;
import static com.mycomp.generator.curd.template.CommonTemplates.FIELD_NAME;
import static com.mycomp.generator.curd.template.CommonTemplates.FIELD_TYPE;
import static com.mycomp.generator.curd.template.CommonTemplates.IMPLEMENTATIONS;
import static com.mycomp.generator.curd.template.CommonTemplates.METHOD_FIELD;
import static com.mycomp.generator.curd.template.CommonTemplates.SERVICE_MODEL;
import static com.mycomp.generator.curd.template.CommonTemplates.TABLE_NAME;
import static com.mycomp.generator.curd.template.CommonTemplates.WEB_MODEL;

import java.io.File;
import java.util.Map;

import com.mycomp.generator.curd.model.ClassField;
import com.mycomp.generator.curd.model.EntityModel;
import com.mycomp.generator.curd.model.ProjectDetails;
import com.mycomp.generator.curd.model.type.ClassType;

public class ClassFileDetailsProvider {

	private final ClassType classType;
	private final ProjectDetails projectDetails;
	private final EntityModel entityModel;

	public ClassFileDetailsProvider(ClassType classType, ProjectDetails projectDetails, EntityModel entityModel) {
		super();
		this.classType = classType;
		this.projectDetails = projectDetails;
		this.entityModel = entityModel;
	}

	public void populateParams(Map<String, String> params) {
		params.put(CLASS_PACKAGE, getClassPackage(classType));
		params.put(CLASS_TYPE, getClassType());
		params.put(CLASS_POST_FIX, getClassPostFix(classType));
		params.put(IMPLEMENTATIONS, getClassImplementations(entityModel.getClassName()));
		params.put(EXTENTIONS, getClassExtentions(entityModel.getClassName()));
		params.put(TABLE_NAME, entityModel.getTableName());
		params.put(CLASS_NAME, entityModel.getClassName());
		params.put(SERVICE_MODEL, entityModel.getClassName() + getClassPostFix(ClassType.MODEL));
		params.put(WEB_MODEL, entityModel.getClassName() + getClassPostFix(ClassType.WEB));
	}

	public void updateParams(Map<String, String> params, ClassField classField, Boolean isCallForRef) {
		if (null != classField) {
			params.put(COLUMN_NAME, classField.getColumnName());
			params.put(FIELD_TYPE, classField.getFieldType(isCallForRef));
			params.put(FIELD_NAME, classField.getFieldName(isCallForRef));
			params.put(METHOD_FIELD, classField.getMethodField(isCallForRef));
		}
	}

	public String getClassImport(ClassType classType) {
		switch (classType) {
		case MODEL:
		case ENTITY:
			return "import " + getClassPackage(classType) + "." + entityModel.getClassName()
					+ getClassPostFix(classType) + ";";
		default:
			return "";
		}
	}

	public String getClassPackage(ClassType classType) {
		if (ClassType.ENTITY == classType) {
			return projectDetails.getDefaultPackage() + ".entity";
		} else if (ClassType.MODEL == classType) {
			return projectDetails.getDefaultPackage() + ".service.api.model";
		} else if (ClassType.WEB == classType) {
			return projectDetails.getDefaultPackage() + ".web.v1.model";
		} else if (ClassType.DAO == classType) {
			return projectDetails.getDefaultPackage() + ".dao.api";
		} else if (ClassType.SERVICE == classType) {
			return projectDetails.getDefaultPackage() + ".service.api";
		} else {
			return projectDetails.getDefaultPackage();
		}
	}

	public String getClassType() {
		if (ClassType.DAO == classType || ClassType.SERVICE == classType) {
			return "interface";
		} else {
			return "class";
		}
	}

	public String getClassPostFix(ClassType classType) {
		if (ClassType.ENTITY == classType) {
			return "";
		} else {
			return classType.getType();
		}
	}

	public String getClassImplementations(String className) {
		if (ClassType.ENTITY == classType) {
			return " implements Serializable";
		} else {
			return "";
		}
	}

	public String getClassExtentions(String className) {
		if (ClassType.DAO == classType) {
			return " extends JpaRepository<" + className + ", Long>";
		} else {
			return "";
		}
	}

	public String getFilePath() {
		String classPackage = getClassPackage(classType);

		String projectPath = getPathForDoeSeparator(projectDetails.getProjectPathDotSeparated());
		String javaPath = getPathForDoeSeparator(projectDetails.getJavaPathDotSeparated());
		String packagePath = getPathForDoeSeparator(classPackage);

		return join(new String[] { projectPath, javaPath, packagePath });
	}

	private String getPathForDoeSeparator(String dotSeparatedPath) {
		String[] projectPath = dotSeparatedPath.split("\\.");
		return join(projectPath);
	}

	private String join(String[] pathArray) {
		return String.join(File.separator, pathArray);
	}

}
