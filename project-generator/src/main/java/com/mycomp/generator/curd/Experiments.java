package com.mycomp.generator.curd;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.mycomp.generator.curd.model.ClassDetails;
import com.mycomp.generator.curd.model.ClassField;
import com.mycomp.generator.curd.model.EntityModel;
import com.mycomp.generator.curd.model.ProjectDetails;
import com.mycomp.generator.curd.model.type.ClassType;
import com.mycomp.generator.curd.service.AbstractGenerator;
import com.mycomp.generator.curd.service.DaoGenerator;
import com.mycomp.generator.curd.service.EntityAndModelGenerator;
import com.mycomp.generator.curd.service.ServiceGenerator;
import com.mycomp.generator.curd.writer.WriteFileSteps;


public class Experiments {

	public static void main(String[] args) {
		
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails.setProjectPathDotSeparated("G:.WorkSpace.WebApps.spring-boot-properties");
		projectDetails.setJavaPathDotSeparated("src.main.java");
		projectDetails.setDefaultPackage("com.mycomp.demo.gen");
		EntityModel entityModel = new EntityModel();
		entityModel.setTableName("my_table");
		entityModel.setClassName("MyTable");
		
		ClassField classField = new ClassField("Long", "my_table_id", null);
		ClassField classField1 = new ClassField("Long", "product_name", null);
		
		entityModel.addClassFields(classField);		
		entityModel.addClassFields(classField1);

		AbstractGenerator generator1 = new EntityAndModelGenerator(projectDetails, entityModel, ClassType.ENTITY);
		AbstractGenerator generator2 = new EntityAndModelGenerator(projectDetails, entityModel, ClassType.MODEL);
		AbstractGenerator generator3 = new EntityAndModelGenerator(projectDetails, entityModel, ClassType.WEB);
		AbstractGenerator generator4 = new DaoGenerator(projectDetails, entityModel, ClassType.DAO);
		AbstractGenerator generator5 = new ServiceGenerator(projectDetails, entityModel, ClassType.SERVICE);
		
		WriteFileSteps.INSTANCE.writeFile(generator1.generate());
		WriteFileSteps.INSTANCE.writeFile(generator2.generate());
		WriteFileSteps.INSTANCE.writeFile(generator3.generate());
		WriteFileSteps.INSTANCE.writeFile(generator4.generate());
		WriteFileSteps.INSTANCE.writeFile(generator5.generate());
		
//		writeFile();
//		System.out.println(getFilePath());
	}


	public static void writeFile() {
		try {
			String fileNameWithPath = "G:\\WorkSpace\\WebApps\\bank-management\\src\\main\\java\\com\\mycomp\\fin\\test.txt";
			String data = "Newtest file";

			final FileWriter writer = new FileWriter(fileNameWithPath);
			writer.write(data);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static String getFilePath() {
		String projectPath = getPathForDoeSeparator("G:.WorkSpace.WebApps.bank-management");
		String javaPath = getPathForDoeSeparator("src.main.java");
		String packagePath = getPathForDoeSeparator("com.mycomp.fin");
		
		return join(new String[] {projectPath, javaPath, packagePath, ""});
	}

	private static String getPathForDoeSeparator(String dotSeparatedPath) {
		String[] projectPath = dotSeparatedPath.split("\\.");
		return join(projectPath);
	}

	private static String join(String[] pathArray) {
		return String.join(File.separator, pathArray);
	}

}
