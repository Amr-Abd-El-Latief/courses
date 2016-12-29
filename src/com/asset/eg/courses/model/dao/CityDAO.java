package com.asset.eg.courses.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.asset.eg.courses.model.pojo.City;
import com.asset.eg.courses.model.util.CoursesLoggers;
import com.asset.eg.courses.model.util.DBConstants;
import com.asset.eg.courses.model.util.CoursesException;
import com.asset.eg.courses.model.util.ErrorsStrings;
import com.asset.eg.courses.model.util.OracleConnection;

public class CityDAO {
	Connection conn ;
	
	public CityDAO(Connection connection){
		conn = connection;
	}	

	/**
	 * gets all cities for certain Country
	 * @param id  country id
	 * @return Array list of Cities for certain country
	 * @throws CoursesException 
	 * 				through exception if there is SQL Exception at the database
	 */
	public ArrayList<City> getCitysByCountry(int id) throws CoursesException{
		String query = "select * from city where country_id=?";
		PreparedStatement pstmnt = null;
		ResultSet rs = null;
		try {
			pstmnt = conn.prepareStatement(query);
			pstmnt.setInt(1,id);			
			rs = pstmnt.executeQuery();
			ArrayList<City> tCatgories = new ArrayList<City>();			
			while(rs.next()){
				City tc = new City();
				tc.setId(rs.getInt(DBConstants.CITY_ID));
				tc.setCityName(rs.getString(DBConstants.CITY_CITY_NAME));
				tCatgories.add(tc);
			}	
			return tCatgories;				
		} catch (SQLException e) {
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LCITYDAO.error(CE);
			CoursesLoggers.LCITYDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;
		}finally{
			OracleConnection.closeOracleResultSet(rs);
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);
		}
		
	} 
	

}
