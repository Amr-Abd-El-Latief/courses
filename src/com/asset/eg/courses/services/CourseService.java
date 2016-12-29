package com.asset.eg.courses.services;

import java.sql.Connection;
import java.util.ArrayList;

import com.asset.eg.courses.model.dao.CourseDAO;
import com.asset.eg.courses.model.dao.DBSequence;
import com.asset.eg.courses.model.pojo.Course;
import com.asset.eg.courses.model.util.CoursesLoggers;
import com.asset.eg.courses.model.util.DBConstants;
import com.asset.eg.courses.model.util.CoursesException;
import com.asset.eg.courses.model.util.ErrorsStrings;

public class CourseService {

	Connection conn;

	public CourseService(Connection connection) {
		conn = connection;
	}

	/**
	 * adds course to couse table
	 * 
	 * @param course
	 *            the course to be added
	 * @return true if the course added , false otherwise
	 * @throws CoursesException
	 *             through exception if there is SQL Exception at the database
	 */
	public boolean addCourse(Course course) throws CoursesException {
		int courseId = DBSequence.getNextId(conn,DBConstants.COURSE_SEQ);
		
		if(courseId!=-1){
		course.setId(courseId);	
		}else{
			CoursesException CE = new CoursesException(ErrorsStrings.CANT_GET_COURSE_ID_ERROR,ErrorsStrings.CANT_GET_COURSE_ID_MESSAGE);
			CoursesLoggers.LUSERSERVICE.error(CE);
			CoursesLoggers.LUSERSERVICE.info(ErrorsStrings.CANT_GET_COURSE_ID_MESSAGE);
			throw CE;				
		}
		
		CourseDAO courseDAO = new CourseDAO(conn);
		return courseDAO.addCourse(course);
	}

	/**
	 * gets all the available course (courses that will start after System Date)
	 * 
	 * @return Array list of All courses
	 * @throws CoursesException
	 *             through exception if there is SQL Exception at the database
	 */
	public ArrayList<Course> getAllCourses() throws CoursesException {
		CourseDAO courseDAO = new CourseDAO(conn);
		ArrayList<Course> coursesList = courseDAO.getAllCourses();
		return coursesList;
	}
/**
 * gets the course by course Code
 * @param courseCode 
 * @return
 * @throws CoursesException
 * 				through exception if there is SQL Exception at the database
 */
	public Course getCourseByCourseCode(String courseCode) throws CoursesException {
		CourseDAO courseDAO = new CourseDAO(conn);
		return courseDAO.getCourseByCourseCode(courseCode);
	}
	/**
	 * gets a course by course id
	 * @param courseId the id of the course
	 * @return the returned course
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 * 				or there is no courses with this id
	 */

	public Course getCourseByCourseId(int courseId) throws CoursesException {
		CourseDAO courseDAO = new CourseDAO(conn);
		Course course = courseDAO.getCourseById(courseId);

		if (course != null) {
			return course;
		} else {
			CoursesException CE = new CoursesException(ErrorsStrings.NO_COURSE_WITH_THIS_ID_ERROR,
					ErrorsStrings.NO_COURSE_WITH_THIS_ID_MESSAGE);
			CoursesLoggers.LUSERSERVICE.error(CE);
			CoursesLoggers.LUSERSERVICE.info(ErrorsStrings.NO_COURSE_WITH_THIS_ID_MESSAGE);
			throw CE;
		}
	}

}
