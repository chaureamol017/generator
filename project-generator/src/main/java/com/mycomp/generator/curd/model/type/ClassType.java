package com.mycomp.generator.curd.model.type;

public enum ClassType {
	ENTITY("Enttity"),
	MODEL("Model"),
	WEB("Web"),
	DAO("Dao"),
	WEB_ADAPTER("WebAdapter"),
	MODEL_ADAPTER("Adapter"),
	SERVICE("Service"),
	SERVICE_IMPL("ServiceImpl"),
	CONTROLLER("Controller");

	private final String type;
	ClassType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
