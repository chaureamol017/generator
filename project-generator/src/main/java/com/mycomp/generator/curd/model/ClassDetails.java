package com.mycomp.generator.curd.model;

import java.util.ArrayList;
import java.util.List;

public class ClassDetails {
	private static final String FILE_FORMAT= ".java";
	private String filePath;
	private String fileName;
	
	private String classPackage;
	private String classImports;
	private String classStart;
	private List<String> classFields = new ArrayList<>();
	private List<String> classFunctions = new ArrayList<>();
	private String classEnd;

	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileNameWithFormat() {
		return fileName + FILE_FORMAT;
	}
	public String getClassPackage() {
		return classPackage;
	}
	public void setClassPackage(String classPackage) {
		this.classPackage = classPackage;
	}
	public String getClassImports() {
		return classImports;
	}
	public void setClassImports(String classImports) {
		this.classImports = classImports;
	}
	public String getClassStart() {
		return classStart;
	}
	public void setClassStart(String classStart) {
		this.classStart = classStart;
	}
	public List<String> getClassFields() {
		return classFields;
	}
//	public void clearClassFields() {
//		this.classFields.clear();
//	}
	public void setClassFields(List<String> classFields) {
		this.classFields.addAll(classFields);
	}
	public void addClassField(String classField) {
		this.classFields.add(classField);
	}
	public List<String> getClassFunctions() {
		return classFunctions;
	}
//	public void clearClassFunctions() {
//		this.classFunctions.clear();
//	}
	public void setClassFunctions(List<String> classFunctions) {
		this.classFunctions.addAll(classFunctions);
	}
	public void addClassFunctions(String classFunction) {
		this.classFunctions.add(classFunction);
	}
	public String getClassEnd() {
		return classEnd;
	}
	public void setClassEnd(String classEnd) {
		this.classEnd = classEnd;
	}
}
