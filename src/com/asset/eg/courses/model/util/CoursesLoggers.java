package com.asset.eg.courses.model.util;

import org.apache.log4j.Logger;

import com.asset.eg.courses.delegate.MainDelegate;
import com.asset.eg.courses.model.dao.*;
import com.asset.eg.courses.services.CityService;
import com.asset.eg.courses.services.CountryService;
import com.asset.eg.courses.services.CourseService;
import com.asset.eg.courses.services.LearningFieldService;
import com.asset.eg.courses.services.ObjectiveService;
import com.asset.eg.courses.services.TechnologyCategoryService;
import com.asset.eg.courses.services.TopicService;
import com.asset.eg.courses.services.USCService;
import com.asset.eg.courses.services.UserService;

public class CoursesLoggers {

	// Connection Logger

	final static public Logger ORACLECONNECTION = Logger.getLogger(OracleConnection.class);

	// DAO Loggers

	final static public Logger LUSERDAO = Logger.getLogger(UserDAO.class);
	final static public Logger LCOURSEDAO = Logger.getLogger(CourseDAO.class);
	final static public Logger LOBJECTIVEDAO = Logger.getLogger(ObjectiveDAO.class);
	final static public Logger LTOPICDAO = Logger.getLogger(TopicDAO.class);
	final static public Logger LUSCDAO = Logger.getLogger(USCDAO.class);	
	final static public Logger LCITYDAO = Logger.getLogger(CityDAO.class);
	final static public Logger LCOUNTRYDAO = Logger.getLogger(CountryDAO.class);
	final static public Logger LLEARNINGFIELDDAO = Logger.getLogger(LearningFieldDAO.class);
	final static public Logger LTECHNOLOGYCATEGORYDAO = Logger.getLogger(TechnologyCategoryDAO.class);


	// Service Loggers

	final static public Logger LUSERSERVICE = Logger.getLogger(UserService.class);
	final static public Logger LCOURSESERVICE = Logger.getLogger(CourseService.class);
	final static public Logger LOBJECTIVESERVICE = Logger.getLogger(ObjectiveService.class);
	final static public Logger LTOPICSERVICE = Logger.getLogger(TopicService.class);
	final static public Logger LUSCSERVICE = Logger.getLogger(USCService.class);	
	final static public Logger LCITYService = Logger.getLogger(CityService.class);
	final static public Logger LCOUNTRYService  = Logger.getLogger(CountryService .class);
	final static public Logger LLEARNINGFIELDService = Logger.getLogger(LearningFieldService .class);
	final static public Logger LTECHNOLOGYCATEGORYService  = Logger.getLogger(TechnologyCategoryService .class);

	// MainDelegateLogger

	final static public Logger LMAINDELEGATE = Logger.getLogger(MainDelegate.class);

}
