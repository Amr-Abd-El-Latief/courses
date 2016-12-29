package com.asset.eg.courses.services;

import java.sql.Connection;
import java.util.ArrayList;

import com.asset.eg.courses.model.dao.CountryDAO;
import com.asset.eg.courses.model.pojo.Country;
import com.asset.eg.courses.model.util.CoursesException;

public class CountryService {

	Connection conn;
	
	public CountryService(Connection connection) {
		conn = connection;	
	}
	
	/**
	 * gets all the countries in country table
	 * @return
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database		
	 */
	public ArrayList<Country> getAllCountries() throws CoursesException {
		CountryDAO countryDAO;
		countryDAO = new CountryDAO(conn);
		return countryDAO.getAllCountries();
	}	

}
