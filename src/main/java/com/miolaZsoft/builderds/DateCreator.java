package com.miolaZsoft.builderds;

import org.jbehave.core.steps.ParameterConverters.DateConverter;

import java.util.Date;

public class DateCreator {
	
	public static Date date(String dateString) {
		DateConverter dateConverter = new DateConverter();
		Date date = (Date) dateConverter.convertValue(dateString, Date.class);
		return date;		
	}

}
