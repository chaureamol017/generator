package com.mycomp.generator.curd.service;

import static com.mycomp.generator.common.ClassTemplates.CLASS_ENTITY_ANNOTATION_TEMPLATE;
import static com.mycomp.generator.common.ClassTemplates.CLASS_START_TEMPLATE;
import static com.mycomp.generator.common.ClassTemplates.COLUMN_TEMPLATE;
import static com.mycomp.generator.common.ClassTemplates.FIELD_TEMPLATE;
import static com.mycomp.generator.common.ClassTemplates.GETTER_FUNCTIONS_TEMPLATE;
import static com.mycomp.generator.common.ClassTemplates.SETTER_FUNCTIONS_TEMPLATE;
import static com.mycomp.generator.common.CommonTemplates.NEW_LINE;

import java.util.ArrayList;
import java.util.List;

import com.mycomp.generator.common.StringReplacer;
import com.mycomp.generator.curd.model.ClassField;
import com.mycomp.generator.curd.model.EntityModel;
import com.mycomp.generator.curd.model.ProjectDetails;
import com.mycomp.generator.curd.model.type.ClassType;

public class EntityAndModelGenerator extends AbstractGenerator {

	public EntityAndModelGenerator(ProjectDetails projectDetails, EntityModel entityModel, ClassType classType) {
		super(projectDetails, entityModel, classType);
	}

	@Override
	protected void setClassImports() {
		String classImports = NEW_LINE;

		if (ClassType.ENTITY == classType) {
			classImports += "import java.io.Serializable;\r\n" + NEW_LINE
					+ "import javax.persistence.Column;\r\n" + "import javax.persistence.Entity;\r\n"
					+ "import javax.persistence.EnumType;\r\n" + "import javax.persistence.Enumerated;\r\n"
					+ "import javax.persistence.GeneratedValue;\r\n" + "import javax.persistence.GenerationType;\r\n"
					+ "import javax.persistence.Id;\r\n" + "import javax.persistence.Table;" + NEW_LINE;
		}
		classDetails.setClassImports(classImports);
	}

	@Override
	protected void setClassStart() {
		String start = "";
		String end = "";
		if (ClassType.ENTITY == classType) {
			start = StringReplacer.repace(params, CLASS_ENTITY_ANNOTATION_TEMPLATE) + NEW_LINE;
			end = "private static final long serialVersionUID = 1L;" + NEW_LINE;
		}
		String classStart = NEW_LINE + start + StringReplacer.repace(params, CLASS_START_TEMPLATE) + end;
		
		

		classDetails.setClassStart(classStart);
	}

	@Override
	protected void setClassFieldsAndFunctions() {
		List<ClassField> classFields = entityModel.getClassFields();
		List<String> fieldsList = new ArrayList<>();
		List<String> functionsList = new ArrayList<>();

		classFields.stream().forEach(field -> {
			createFieldAndFunctionsAddToList(field, fieldsList, functionsList, false);
		});
		if (ClassType.ENTITY == classType) {
			classFields.stream().filter(field -> field.getIsReference())
			.forEach(field -> {
				createFieldAndFunctionsAddToList(field, fieldsList, functionsList, true);
			});
		}
		fieldsList.add(NEW_LINE);

		classDetails.setClassFields(fieldsList);
		classDetails.setClassFunctions(functionsList);
	}

	private void createFieldAndFunctionsAddToList(ClassField field, List<String> fieldsList, List<String> functionsList,
			Boolean isForReference) {
			fileDetailsProvider.updateParams(params, field, isForReference);
			setFieldsList(fieldsList);
			setFunctionsList(functionsList);
	}

	private void setFieldsList(List<String> fieldsList) {
		String fieldInfo = NEW_LINE;
		if (ClassType.ENTITY == classType) {
			fieldInfo += StringReplacer.repace(params, COLUMN_TEMPLATE) + NEW_LINE;
		}
		fieldInfo += StringReplacer.repace(params, FIELD_TEMPLATE) ;
		fieldsList.add(fieldInfo);
	}

	private void setFunctionsList(List<String> functionsList) {
		functionsList.add(StringReplacer.repace(params, NEW_LINE + GETTER_FUNCTIONS_TEMPLATE) + NEW_LINE);
		functionsList.add(StringReplacer.repace(params, NEW_LINE + SETTER_FUNCTIONS_TEMPLATE) + NEW_LINE);
	}

}
