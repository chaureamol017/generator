package com.mycomp.generator.common;

import java.util.Map;

public class StringReplacer {

	public static String repace(Map<String, String> params, String template) {
		for(Map.Entry<String, String> entry : params.entrySet()) {
			template = template.replaceAll(entry.getKey(), entry.getValue());
		}

		return template;
	}
}
