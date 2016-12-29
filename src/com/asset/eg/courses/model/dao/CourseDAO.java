package com.asset.eg.courses.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.asset.eg.courses.model.pojo.Course;
import com.asset.eg.courses.model.util.CoursesLoggers;
import com.asset.eg.courses.model.util.DBConstants;
import com.asset.eg.courses.model.util.CoursesException;
import com.asset.eg.courses.model.util.ErrorsStrings;
import com.asset.eg.courses.model.util.OracleConnection;

public class CourseDAO {

	Connection conn;

	public CourseDAO(Connection connection) {
		conn = connection;
	}
/**
 * adds course to the course table
 * @param course course to be added
 * @return true if the course added other wise false
 * @throws CoursesException
 *				through exception if there is SQL Exception at the database
 */
	public boolean addCourse(Course course) throws CoursesException {
		// TODO Auto-generated method stub
		String createUserSql = "INSERT INTO COURSE(id,course_code,course_name,is_course,learning_field,technology_category,start_date,end_date,description) VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmnt = null;
		try {
			pstmnt = conn.prepareStatement(createUserSql);
			pstmnt.setInt(1, course.getId());
			pstmnt.setString(2, course.getCourseCode());
			pstmnt.setString(3, course.getCourseName());
			pstmnt.setInt(4, course.getIsCourse());
			pstmnt.setInt(5, course.getLearningField());
			pstmnt.setInt(6, course.getTechnologyCategory());
			pstmnt.setDate(7,new java.sql.Date(course.getStartDate().getTime()));
			pstmnt.setDate(8, new java.sql.Date(course.getEndDate().getTime()));
			pstmnt.setString(9, course.getDescription());
			if(course.getEndDate().compareTo(course.getStartDate())>0){
			int res = pstmnt.executeUpdate();
			return (res > 0);
			
			}else{
				return false;
			}
		} catch (SQLException e) {
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LCOURSEDAO.error(CE);
			CoursesLoggers.LCOURSEDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;
		} finally {
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);
		}

	}
	
	/**
	 * gets all the available courses which its start date after the system current date
	 * @return array list of courses
	 * @throws CoursesException 
	 *				through exception if there is SQL Exception at the database
	 */

	public ArrayList<Course> getAllCourses() throws CoursesException {
		// TODO Auto-generated method stub
		String getAllCoursesSQL = "select * from course  where start_date > to_date((SELECT TO_CHAR  (SYSDATE, 'MM-DD-YYYY ') \"NOW\" FROM DUAL),'MM-DD-YYYY')";
		PreparedStatement pstmnt = null;
		ResultSet rs = null;
		try {
			pstmnt = conn.prepareStatement(getAllCoursesSQL);
			rs = pstmnt.executeQuery();
			ArrayList<Course> courses =null;

				courses = new ArrayList<Course>();		

			while (rs.next()) {				
				Course temp = new Course();
				temp.setCourseCode(rs.getString(DBConstants.COURSE_COURSE_CODE));
				temp.setCourseName(rs.getString(DBConstants.COURSE_COURSE_NAME));
				temp.setIsCourse(rs.getInt(DBConstants.COURSE_IS_COURSE));
				temp.setLearningField(rs.getInt(DBConstants.COURSE_LEARNING_FIELD));
				temp.setTechnologyCategory(rs.getInt(DBConstants.COURSE_TECHNOLOGY_CATEGORY));
				temp.setStartDate(rs.getDate(DBConstants.COURSE_START_DATE));
				temp.setEndDate(rs.getDate(DBConstants.COURSE_END_DATE));
				temp.setDescription(rs.getString(DBConstants.COURSE_DESCRIPTION));
				courses.add(temp);
			}		
				return courses;		
		} catch (SQLException e) {
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LCOURSEDAO.error(CE);
			CoursesLoggers.LCOURSEDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;			

		} finally {
			OracleConnection.closeOracleResultSet(rs);
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);	
		}
	}
	/**
	 * gets certain course by the course id
	 * @param iD the id of the course
	 * @return course  the course
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 */

	public Course getCourseById(Integer iD) throws CoursesException {
		// TODO Auto-generated method stub
		String getAllCoursesSQL = "select * from Course where id = ?";
		Course temp = null;
		PreparedStatement pstmnt = null;
		ResultSet rs = null;
		try {
			pstmnt = conn.prepareStatement(getAllCoursesSQL);
			pstmnt.setInt(1, iD);
			rs = pstmnt.executeQuery();
			while (rs.next()) {
				temp = new Course();
				temp.setId(rs.getInt(DBConstants.COURSE_ID));
				temp.setCourseCode(rs.getString(DBConstants.COURSE_COURSE_CODE));
				temp.setCourseName(rs.getString(DBConstants.COURSE_COURSE_NAME));
				temp.setIsCourse(rs.getInt(DBConstants.COURSE_IS_COURSE));
				temp.setLearningField(rs.getInt(DBConstants.COURSE_LEARNING_FIELD));
				temp.setTechnologyCategory(rs.getInt(DBConstants.COURSE_TECHNOLOGY_CATEGORY));
				temp.setStartDate(rs.getDate(DBConstants.COURSE_START_DATE));
				temp.setEndDate(rs.getDate(DBConstants.COURSE_END_DATE));
				temp.setDescription(rs.getString(DBConstants.COURSE_DESCRIPTION));
			}
			return temp;
		} catch (SQLException e) {
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LCOURSEDAO.error(CE);
			CoursesLoggers.LCOURSEDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;			

		} finally {
			OracleConnection.closeOracleResultSet(rs);
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);

		}

	}
	
	/**
	 * gets certain course by course code
	 * @param courseCode the code of the course
	 * @return  Course
	 * @throws CoursesException
	 *				through exception if there is SQL Exception at the database
	 */

	public Course getCourseByCourseCode(String courseCode) throws CoursesException {
		// TODO Auto-generated method stub
		String getAllCoursesSQL = "select * from Course where course_code = ?";
		Course temp = null;
		PreparedStatement pstmnt = null;
		ResultSet rs = null;
		try {

			pstmnt = conn.prepareStatement(getAllCoursesSQL);
			pstmnt.setString(1, courseCode);
			rs = pstmnt.executeQuery();
			while (rs.next()) {
				temp = new Course();
				temp.setId(rs.getInt(DBConstants.COURSE_ID));
				temp.setCourseCode(rs.getString(DBConstants.COURSE_COURSE_CODE));
				temp.setCourseName(rs.getString(DBConstants.COURSE_COURSE_CODE));
				temp.setIsCourse(rs.getInt(DBConstants.COURSE_IS_COURSE));
				temp.setLearningField(rs.getInt(DBConstants.COURSE_LEARNING_FIELD));
				temp.setTechnologyCategory(rs.getInt(DBConstants.COURSE_TECHNOLOGY_CATEGORY));
				temp.setStartDate(rs.getDate(DBConstants.COURSE_START_DATE));
				temp.setEndDate(rs.getDate(DBConstants.COURSE_END_DATE));
				temp.setDescription(rs.getString(DBConstants.COURSE_DESCRIPTION));
			}
			return temp;
		} catch (SQLException e) {
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LCOURSEDAO.error(CE);
			CoursesLoggers.LCOURSEDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;			

		} finally {
			OracleConnection.closeOracleResultSet(rs);
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);
		}
	}

}
