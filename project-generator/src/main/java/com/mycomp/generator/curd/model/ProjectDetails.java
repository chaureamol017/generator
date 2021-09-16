package com.mycomp.generator.curd.model;

public class ProjectDetails {
	private String projectPathDotSeparated;
	private String javaPathDotSeparated;
	private String defaultPackage;

	public String getProjectPathDotSeparated() {
		return projectPathDotSeparated;
	}
	public void setProjectPathDotSeparated(String projectPathDotSeparated) {
		this.projectPathDotSeparated = projectPathDotSeparated;
	}
	public String getJavaPathDotSeparated() {
		return javaPathDotSeparated;
	}
	public void setJavaPathDotSeparated(String javaPathDotSeparated) {
		this.javaPathDotSeparated = javaPathDotSeparated;
	}
	public String getDefaultPackage() {
		return defaultPackage;
	}
	public void setDefaultPackage(String defaultPackage) {
		this.defaultPackage = defaultPackage;
	}

}
