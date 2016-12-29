package com.asset.eg.courses.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.asset.eg.courses.model.pojo.LearningField;
import com.asset.eg.courses.model.util.CoursesLoggers;
import com.asset.eg.courses.model.util.DBConstants;
import com.asset.eg.courses.model.util.CoursesException;
import com.asset.eg.courses.model.util.ErrorsStrings;
import com.asset.eg.courses.model.util.OracleConnection;

public class LearningFieldDAO {
	
	Connection conn ;
	public LearningFieldDAO(Connection connection){
		conn = connection;
	}	
	
	/**
	 * gets certain learning field 
	 * @param id the id of learning field
	 * @return returns learning field opject
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 */
	
	public LearningField getLearningField(int id) throws CoursesException{
		
		// TODO Auto-generated method stub
		String query = "select * from learning_field where id = ?";
		LearningField temp = null;
		PreparedStatement pstmnt = null;
		ResultSet rs = null;
		try {
			pstmnt = conn.prepareStatement(query);
			pstmnt.setInt(1, id);
			rs = pstmnt.executeQuery();

			while (rs.next()) {
				temp = new LearningField();
				temp.setId(rs.getInt(DBConstants.LEARNING_FIELD_ID));
				temp.setFieldValue(rs.getString(DBConstants.LEARNING_FIELD_FIELD_VALUE));

			}
			return temp;
		} catch (SQLException e) {
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LLEARNINGFIELDDAO.error(CE);
			CoursesLoggers.LLEARNINGFIELDDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;			
		} finally {
			OracleConnection.closeOracleResultSet(rs);
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);

		}
		
	}
	/**
	 * gets all the fields from learning field table
	 * @return array list of learning fields
	 * @throws CoursesException
	 * 					through exception if there is SQL Exception at the database
	 */
	
	public ArrayList<LearningField> getAllLFields() throws CoursesException{
		String query = "select * from learning_field";
		PreparedStatement pstmnt = null;
		ResultSet rs = null;
		try {
			pstmnt = conn.prepareStatement(query);
			rs = pstmnt.executeQuery();
			ArrayList<LearningField> lFields = new ArrayList<LearningField>();
			
			while(rs.next()){
				LearningField tc = new LearningField();
				tc.setId(rs.getInt(DBConstants.LEARNING_FIELD_ID));
				tc.setFieldValue(rs.getString(DBConstants.LEARNING_FIELD_FIELD_VALUE));
				lFields.add(tc);
			}
		
			return lFields;			
			
		} catch (SQLException e) {
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LLEARNINGFIELDDAO.error(CE);
			CoursesLoggers.LLEARNINGFIELDDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;			
		}finally{
			OracleConnection.closeOracleResultSet(rs);
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);
		}
				
	} 
	
}
