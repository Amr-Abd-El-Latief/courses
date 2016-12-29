package com.asset.eg.courses.services;

import java.sql.Connection;
import java.util.ArrayList;

import com.asset.eg.courses.model.dao.LearningFieldDAO;
import com.asset.eg.courses.model.pojo.LearningField;
import com.asset.eg.courses.model.util.CoursesException;

public class LearningFieldService {
	
	Connection conn;
	
	public LearningFieldService(Connection connection) {
		conn = connection;	
	}
	/**
	 * gets all the learning fields
	 * @return Array List of Learning Fields objects
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 */
	
	public ArrayList<LearningField> getAllLearningFields() throws CoursesException {
		LearningFieldDAO learningFieldDAO;
		learningFieldDAO = new LearningFieldDAO(conn);
		return learningFieldDAO.getAllLFields();
		}	
	/**
	 * gets the Learning Field with the given id
	 * @param id the id of the learning Field
	 * @return Learning Field object
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 */
	public LearningField getLearningFieldById(int id) throws CoursesException {
		LearningFieldDAO learningFieldDAO;
		learningFieldDAO = new LearningFieldDAO(conn);
		return learningFieldDAO.getLearningField(id);
		}	
	
}
