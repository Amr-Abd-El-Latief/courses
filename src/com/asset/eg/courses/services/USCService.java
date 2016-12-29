package com.asset.eg.courses.services;


import java.sql.Connection;
import com.asset.eg.courses.model.dao.CourseDAO;
import com.asset.eg.courses.model.dao.USCDAO;
import com.asset.eg.courses.model.dao.UserDAO;
import com.asset.eg.courses.model.pojo.Course;
import com.asset.eg.courses.model.pojo.USC;
import com.asset.eg.courses.model.pojo.User;
import com.asset.eg.courses.model.util.CoursesLoggers;
import com.asset.eg.courses.model.util.CoursesException;
import com.asset.eg.courses.model.util.ErrorsStrings;

public class USCService{

	Connection conn;
	
	public USCService(Connection connection) {
		conn = connection;

	}
/**
 * subscribes user  to course
 * @param userId the 
 * @param courseId
 * @return
 * @throws CoursesException
 * 		throws 
 */
	public boolean subscripeToCourse(int userId,int courseId) throws CoursesException {

		// check for user , check for course , check user
		USCDAO uscDAO = new USCDAO(conn);
		UserDAO userDAO = new UserDAO(conn);
		CourseDAO courseDAO = new CourseDAO(conn);
		
		User retrievedUser = userDAO.getUserByID(userId);
		Course retrievedCourse=null;
	
			retrievedCourse = courseDAO.getCourseById(courseId);
	

		if (retrievedUser != null && retrievedCourse != null) {
			// insert
			USC usc = new USC();
			usc.setUserId(retrievedUser.getId());
			usc.setCourseId(retrievedCourse.getId());
			if (uscDAO.isUserSubscriped(usc)) {
				CoursesException CE = new CoursesException(ErrorsStrings.USER_ALREADY_SUBSCRIPED_ERROR,ErrorsStrings.USER_ALREADY_SUBSCRIPED_MESSAGE);
				CoursesLoggers.LUSCSERVICE.error(CE);
				CoursesLoggers.LUSCSERVICE.info(ErrorsStrings.USER_ALREADY_SUBSCRIPED_MESSAGE);
				throw CE;				
			}else{
			return uscDAO.subscripeToCourse(usc);
			}
		}else
		{
		CoursesException CE = new CoursesException(ErrorsStrings.USER_DOESNT_EXIST_ERROR,ErrorsStrings.USER_DOESNT_EXIST_MESSAGE);
			CoursesLoggers.LUSCSERVICE.error(CE);
			CoursesLoggers.LUSCSERVICE.info(ErrorsStrings.USER_DOESNT_EXIST_MESSAGE);
			throw CE;				
		}

	}

}
