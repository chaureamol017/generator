package com.mycomp.generator.curd.model;

import java.util.ArrayList;
import java.util.List;

public class EntityModel {
	private String tableName;
	private String className;
	private List<ClassField> classFields = new ArrayList<>();

	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<ClassField> getClassFields() {
		return classFields;
	}
	public void setClassFields(List<ClassField> classFields) {
		this.classFields = classFields;
	}

	public void addClassFields(ClassField classField) {
		if (null == classFields) {
			this.classFields = new ArrayList<>();
		}
		this.classFields.add(classField);
	}

}
