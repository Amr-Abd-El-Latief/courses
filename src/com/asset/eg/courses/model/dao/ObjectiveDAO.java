package com.asset.eg.courses.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.asset.eg.courses.model.pojo.Objective;

import com.asset.eg.courses.model.util.CoursesLoggers;
import com.asset.eg.courses.model.util.DBConstants;
import com.asset.eg.courses.model.util.CoursesException;
import com.asset.eg.courses.model.util.ErrorsStrings;
import com.asset.eg.courses.model.util.OracleConnection;


public class ObjectiveDAO {

	Connection conn ;
	
	public ObjectiveDAO(Connection connection){
		conn = connection;
		
	}
	/**
	 * adds objective to course_objectives table
	 * @param obj
	 * @return true if the objective added , false otherwise
	 * @throws CoursesException
	 * 					through exception if there is SQL Exception at the database
	 */
	public boolean addObjective(Objective obj) throws CoursesException {	
		
		String createTopic = "INSERT INTO COURSE_objectives(id,Course_Id,objective) VALUES(?,?,?)";
		PreparedStatement pstmnt = null;
		
		try {
			pstmnt = conn.prepareStatement(createTopic);
			pstmnt.setInt(1,obj.getId());
			pstmnt.setInt(2,obj.getCourseId());
			pstmnt.setString(3,obj.getObjective());
			int res = pstmnt.executeUpdate();
			return (res>0);
		} catch (SQLException e) {
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LOBJECTIVEDAO.error(CE);
			CoursesLoggers.LOBJECTIVEDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;
		}finally{
			
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);
		}
		
	}
	/**
	 * gets all objectives from course_objectives table for certain course
	 * @param courseId  the id of course 
	 * @return  array of available objectives
	 * @throws CoursesException
	 *				through exception if there is SQL Exception at the database
	 */
	
	public ArrayList<Objective> getAllObjectives(int courseId) throws CoursesException{
		String createTopic = "select * from COURSE_Objectives where course_id = ?";
		PreparedStatement pstmnt = null;
		ResultSet rs = null;
		try {
			pstmnt = conn.prepareStatement(createTopic);
			pstmnt.setInt(1,courseId);	
			rs = pstmnt.executeQuery();
			ArrayList<Objective> Objectives = new ArrayList<Objective>();
			while(rs.next()){
				Objective objective = new Objective();
				objective.setCourseId(rs.getInt(DBConstants.COURSE_OBJECTIVES_COURSE_ID));
				objective.setId(rs.getInt(DBConstants.COURSE_OBJECTIVES_ID));	
				objective.setObjective(rs.getString(DBConstants.COURSE_OBJECTIVES_OBJECTIVE));
				Objectives.add(objective);
			}
			return Objectives;
			
		} catch (SQLException e) {
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LOBJECTIVEDAO.error(CE);
			CoursesLoggers.LOBJECTIVEDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;
		}finally{
			OracleConnection.closeOracleResultSet(rs);
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);
		}
				
	} 
}
