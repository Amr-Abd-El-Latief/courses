package com.asset.eg.courses.services;

import java.sql.Connection;
import java.util.ArrayList;

import com.asset.eg.courses.model.dao.CityDAO;
import com.asset.eg.courses.model.pojo.City;
import com.asset.eg.courses.model.util.CoursesException;

public class CityService {	
	Connection conn;	
	public CityService(Connection connection) {
		conn = connection;	
	}
	/**
	 * get all ceties of certain country
	 * @param id country id
	 * @return Array list of all cities of this country
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 */
	public ArrayList<City> citiesOfCertainCountry(int id) throws CoursesException {
		CityDAO cityDAO;
		cityDAO = new CityDAO(conn);
		return cityDAO.getCitysByCountry(id);
		}	
}
