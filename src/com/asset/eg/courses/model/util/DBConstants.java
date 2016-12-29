package com.asset.eg.courses.model.util;

public class DBConstants {	
	//CITY TABLE
	public static final String CITY_ID = "ID";
	public static final String CITY_CITY_NAME = "CITY_NAME";
	public static final String CITY_COUNTRY_ID = "COUNTRY_ID";
	
	//COUNTRY TABLE
	public static final String COUNTRY_ID = "ID";
	public static final String COUNTRY_COUNTRY_NAME = "COUNTRY_NAME";
	
	//COURSE TABLE 
	public static final String COURSE_ID = "ID";
	public static final String COURSE_COURSE_CODE = "COURSE_CODE";
	public static final String COURSE_COURSE_NAME = "COURSE_NAME";	
	public static final String COURSE_START_DATE= "START_DATE";
	public static final String COURSE_END_DATE = "END_DATE";
	public static final String COURSE_DESCRIPTION = "DESCRIPTION";
	public static final String COURSE_IS_COURSE = "IS_COURSE";
	public static final String COURSE_TECHNOLOGY_CATEGORY = "TECHNOLOGY_CATEGORY";
	public static final String COURSE_LEARNING_FIELD = "LEARNING_FIELD";
	public static final String COURSE_OBJECTIVES_ID = "ID";
	public static final String COURSE_OBJECTIVES_OBJECTIVE = "OBJECTIVE";
	public static final String COURSE_OBJECTIVES_COURSE_ID = "COURSE_ID";

	//TOPIC TABLE
	public static final String COURSE_TOPIC_ID = "ID";
	public static final String COURSE_TOPIC_TOPIC = "TOPIC";
	public static final String COURSE_TOPIC_COURSE_ID = "COURSE_ID";
	
	//USER TABLE
	public static final String COURSE_USER_ID = "ID";
	public static final String COURSE_USER_USER_NAME = "USER_NAME";
	public static final String COURSE_USER_PASSWD= "PASSWD";
	public static final String COURSE_USER_EMAIL = "EMAIL";
	public static final String COURSE_USER_CELL_PHONE= "CELL_PHONE";
	public static final String COURSE_USER_DATE_OF_BIRTH = "DATE_OF_BIRTH";
	public static final String COURSE_USER_FIRST_NAME= "FIRST_NAME";
	public static final String COURSE_USER_LAST_NAME = "LAST_NAME";
	public static final String COURSE_USER_COUNTRY_ID= "COUNTRY_ID";
	public static final String COURSE_USER_CITY_ID = "CITY_ID";

	//LEARNING FIELD TABLE
	public static final String LEARNING_FIELD_ID = "ID";
	public static final String LEARNING_FIELD_FIELD_VALUE = "FIELD_VALUE";
	
	//TECHNOLOGY CATEGORY TABLE
	
	public static final String TECHNOLOGY_CATEGORY_ID = "ID";
	public static final String TECHNOLOGY_CATEGORY_CATEGORY_VALUE = "CATEGORY_VALUE";	
	
	// USC TABLE
	public static final String USC_USER_ID = "USER_ID";
	public static final String USC_COURSE_ID = "COURSE_ID";

	// sequences 
	public static final String COURSE_USER_SEQ_SECOND = "COURSE_USER_SEQ_SECOND";
	public static final String COURSE_SEQ = "COURSE_SEQ";
	public static final String OBJECTIVE_SEQ = "OBJECTIVE_SEQ";
	public static final String TOPIC_SEQ = "TOPIC_SEQ";
	
	
	}
