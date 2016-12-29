package com.asset.eg.courses.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.asset.eg.courses.model.pojo.TechnologyCategory;
import com.asset.eg.courses.model.util.CoursesLoggers;
import com.asset.eg.courses.model.util.DBConstants;
import com.asset.eg.courses.model.util.CoursesException;
import com.asset.eg.courses.model.util.ErrorsStrings;
import com.asset.eg.courses.model.util.OracleConnection;

public class TechnologyCategoryDAO {
	
	Connection conn ;
	public TechnologyCategoryDAO(Connection connection){
		conn = connection;
	}
	/**
	 * gets a certain category by its id
	 * @param id the id of the category
	 * @return TechnologyCategory a category object
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 */
	
	
	public TechnologyCategory getCategoryById(int id) throws CoursesException{
		
		// TODO Auto-generated method stub
		String query = "select * from technology_category where id = ?";
		TechnologyCategory temp = null;
		PreparedStatement pstmnt = null;
		ResultSet rs = null;
		try {
			pstmnt = conn.prepareStatement(query);
			pstmnt.setInt(1, id);
			rs = pstmnt.executeQuery();

			while (rs.next()) {
				temp = new TechnologyCategory();
				temp.setId(rs.getInt(DBConstants.TECHNOLOGY_CATEGORY_ID));
				temp.setCategoryValue(rs.getString(DBConstants.TECHNOLOGY_CATEGORY_CATEGORY_VALUE));

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
	 * gets all the categories in the Technology_category table
	 * @return array list of all technology categories
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 */
	
	public ArrayList<TechnologyCategory> getAllTCategories() throws CoursesException{
		String query = "select * from technology_category";
		PreparedStatement pstmnt = null;
		ResultSet rs = null;
		try {
			pstmnt = conn.prepareStatement(query);
			rs = pstmnt.executeQuery();
			ArrayList<TechnologyCategory> tCatgories = new ArrayList<TechnologyCategory>();
			
			while(rs.next()){
				TechnologyCategory tc = new TechnologyCategory();
				tc.setId(rs.getInt(DBConstants.TECHNOLOGY_CATEGORY_ID));
				tc.setCategoryValue(rs.getString(DBConstants.TECHNOLOGY_CATEGORY_CATEGORY_VALUE));
				tCatgories.add(tc);
			}
		
			return tCatgories;			
			
		} catch (SQLException e) {
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LCOURSEDAO.error(CE);
			CoursesLoggers.LCOURSEDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;
		}finally{
			OracleConnection.closeOracleResultSet(rs);
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);
		}
				
	} 
	

}
