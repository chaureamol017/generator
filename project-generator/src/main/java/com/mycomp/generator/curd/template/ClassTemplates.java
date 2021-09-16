package com.mycomp.generator.curd.template;

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

public class ClassTemplates {

	public static final String PACKAGE_TEMPLATE = "package " + CLASS_PACKAGE + ";\r\n";
	
	public static final String CLASS_ENTITY_ANNOTATION_TEMPLATE = "@Entity\r\n" + 
			"@Table(name = \"" + TABLE_NAME +"\")";	

	public static final String CLASS_START_TEMPLATE = "public " + CLASS_TYPE + " " + CLASS_NAME + CLASS_POST_FIX + EXTENTIONS + IMPLEMENTATIONS + " {\r\n";
	
	public static final String CLASS_SERIAL_VERSION = "	private static final long serialVersionUID = 1L;";

	public static final String COLUMN_TEMPLATE ="    @Column(name = \"" + COLUMN_NAME + "\")";

	public static final String FIELD_TEMPLATE = "    private " + FIELD_TYPE + " " + FIELD_NAME + ";";
	
	public static final String GETTER_FUNCTIONS_TEMPLATE = "	public " + FIELD_TYPE + " get" + METHOD_FIELD + "() {\r\n" + 
			"		return " + FIELD_NAME + ";\r\n" + 
			"	}";

	public static final String SETTER_FUNCTIONS_TEMPLATE = "	public void set"  + METHOD_FIELD + "(" + FIELD_TYPE + " " + FIELD_NAME + ") {\r\n" + 
			"		this." + FIELD_NAME + " = " + FIELD_NAME + ";\r\n" + 
			"	}";

	public static final String SAVE_FUNCTION_SERVICE_TEMPLATE = "    " + SERVICE_MODEL + " save" + CLASS_NAME +"(" + SERVICE_MODEL + " model);";

	public static final String UPDATE_FUNCTION_SERVICE_TEMPLATE = "    " + SERVICE_MODEL + " update" + CLASS_NAME +"(" + SERVICE_MODEL + " model);";

	public static final String GET_FUNCTION_SERVICE_TEMPLATE = "    " + SERVICE_MODEL + " get" + CLASS_NAME + "(Long id);";

	public static final String GET_ALL_FUNCTION_SERVICE_TEMPLATE = "    List<" + SERVICE_MODEL + "> getAll" + CLASS_NAME + "s();";
	
	public static final String DELETE_FUNCTION_SERVICE_TEMPLATE = "    Boolean delete" + CLASS_NAME + "(Long id);";

}
