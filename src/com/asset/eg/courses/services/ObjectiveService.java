package com.asset.eg.courses.services;


import java.sql.Connection;
import java.util.ArrayList;

import com.asset.eg.courses.model.dao.DBSequence;
import com.asset.eg.courses.model.dao.ObjectiveDAO;
import com.asset.eg.courses.model.pojo.Objective;
import com.asset.eg.courses.model.util.CoursesException;
import com.asset.eg.courses.model.util.CoursesLoggers;
import com.asset.eg.courses.model.util.DBConstants;
import com.asset.eg.courses.model.util.ErrorsStrings;

public class ObjectiveService {

Connection conn;	
	


public ObjectiveService(Connection connection){
		conn = connection;		
}
/**
 * adds objective to the objectives table
 * @param obj the objective
 * @return true if added , false otherwise
 * @throws CoursesException
 * 				through exception if there is SQL Exception at the database
 */
	public boolean addObjective(Objective obj) throws CoursesException {
		int objectiveId = DBSequence.getNextId(conn,DBConstants.OBJECTIVE_SEQ);		
		if(objectiveId!=-1){
		obj.setId(objectiveId);	
		}else{
			CoursesException CE = new CoursesException(ErrorsStrings.CANT_GET_OBJECTIVE_ID_ERROR,ErrorsStrings.CANT_GET_OBJECTIVE_ID_MESSAGE);
			CoursesLoggers.LOBJECTIVESERVICE.error(CE);
			CoursesLoggers.LOBJECTIVESERVICE.info(ErrorsStrings.CANT_GET_OBJECTIVE_ID_MESSAGE);
			throw CE;		
		}		
		ObjectiveDAO ObjectiveDAO = new ObjectiveDAO(conn);	
		return ObjectiveDAO.addObjective(obj);
	
	}
	/**
	 * gets all the objectives for certain course
	 * @param courseId the id of the course 
	 * @return Array List of Objectives 
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 */
	public ArrayList<Objective> getAllgetAllObjectives(int courseId) throws CoursesException{
		ObjectiveDAO ObjectiveDAO = new ObjectiveDAO(conn);	
		return ObjectiveDAO.getAllObjectives(courseId);
	}

}
