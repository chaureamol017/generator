package com.mycomp.generator.curd.model;

public class ClassField {
	private final String fieldType;
	private final String columnName;
	private final String fieldName;
	private final String methodField;
	private final boolean isReference;
	private final String referenceTableName;
	private final String referenceEntityName;
	private final String referenceFieldName;

	public ClassField(String fieldType, String columnName, String referenceTableName) {
		super();
		this.fieldType = fieldType;
		this.columnName = columnName;
		this.fieldName = wordAsCamelCaseWithLowerCaseStart(columnName);
		this.methodField = wordAsCamelCase(columnName);
		this.isReference = null != referenceTableName;
		this.referenceTableName = referenceTableName;
		this.referenceEntityName = wordAsCamelCase(referenceTableName);
		this.referenceFieldName = wordAsCamelCaseWithLowerCaseStart(referenceTableName);
	}

	public String getFieldType() {
		return fieldType;
	}

	public String getColumnName() {
		return columnName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getMethodField() {
		return methodField;
	}

	public boolean getIsReference() {
		return isReference;
	}

	public String getReferenceTableName() {
		return referenceTableName;
	}

	public String getReferenceEntityName() {
		return referenceEntityName;
	}

	public String getReferenceFieldName() {
		return referenceFieldName;
	}
	public String getFieldType(Boolean isCallForRef) {
		return (isReference && isCallForRef) ? referenceEntityName : fieldType;
	}
	public String getFieldName(Boolean isCallForRef) {
		return (isReference && isCallForRef) ? referenceFieldName : fieldName;
	}
	public String getMethodField(Boolean isCallForRef) {
		return (isReference && isCallForRef) ? referenceEntityName : methodField;
	}

	private String wordAsCamelCaseWithLowerCaseStart(String word) {
		if (null == word) {
			return null;
		}
		String newWord = wordAsCamelCase(word);
		return newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
	}

	private String wordAsCamelCase(String word) {
		if (null == word) {
			return null;
		}
		String[] splitted = word.split("_");
		String newWord = "";
		for (String s : splitted) {
			if (s != null && s.length() > 0) {
				newWord += s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
			}
		}
		return newWord;
	}
}
