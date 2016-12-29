package com.asset.eg.courses.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.asset.eg.courses.model.pojo.Country;
import com.asset.eg.courses.model.util.CoursesLoggers;
import com.asset.eg.courses.model.util.DBConstants;
import com.asset.eg.courses.model.util.CoursesException;
import com.asset.eg.courses.model.util.ErrorsStrings;
import com.asset.eg.courses.model.util.OracleConnection;

public class CountryDAO {
	
	Connection conn ;
	public CountryDAO(Connection connection){
		conn = connection;
	}
	/**
	 * gets all countries
	 * @return array list of countries
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 */
	
	public ArrayList<Country> getAllCountries() throws CoursesException{
		String query = "select * from country";
		PreparedStatement pstmnt = null;
		ResultSet rs = null;
		try {
			pstmnt = conn.prepareStatement(query);
			rs = pstmnt.executeQuery();
			ArrayList<Country> countries = new ArrayList<Country>();			
			while(rs.next()){
				Country temp = new Country();
				temp.setId(rs.getInt(DBConstants.COUNTRY_ID));
				temp.setCountry_name(rs.getString(DBConstants.COUNTRY_COUNTRY_NAME));
				countries.add(temp);
			}	
			return countries;			
		} catch (SQLException e) {
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LCOUNTRYDAO.error(CE);
			CoursesLoggers.LCOUNTRYDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;			

		}finally{
			OracleConnection.closeOracleResultSet(rs);
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);
		}
				
	} 
	


}
