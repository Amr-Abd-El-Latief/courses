package com.asset.eg.courses.services;

import java.sql.Connection;
import java.util.ArrayList;

import com.asset.eg.courses.model.dao.TechnologyCategoryDAO;
import com.asset.eg.courses.model.pojo.TechnologyCategory;
import com.asset.eg.courses.model.util.CoursesException;

public class TechnologyCategoryService {
	Connection conn;
	
	public TechnologyCategoryService(Connection connection) {
		conn = connection;	
	}
	/**
	 * gets all technologies 
	 * @return Array List of all technologies 
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 */
	public ArrayList<TechnologyCategory> getAllTechnologies() throws CoursesException {
		TechnologyCategoryDAO technologyCategoryDAO;
		technologyCategoryDAO = new TechnologyCategoryDAO(conn);
		return technologyCategoryDAO.getAllTCategories();
		}	
	
	/**
	 * gets certain category by its id
	 * @param id the id of the category
	 * @return Technology category object
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 */
	public TechnologyCategory geCategorById(int id) throws CoursesException {
		TechnologyCategoryDAO technologyCategoryDAO;
		technologyCategoryDAO = new TechnologyCategoryDAO(conn);
		return technologyCategoryDAO.getCategoryById(id);
		}	
	
}
