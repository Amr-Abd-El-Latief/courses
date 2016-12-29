/**
 *@author  Amr Abd El latief 
 * 
 * 
 */

package com.asset.eg.courses.services;


import java.sql.Connection;

import com.asset.eg.courses.model.dao.DBSequence;
import com.asset.eg.courses.model.dao.UserDAO;
import com.asset.eg.courses.model.pojo.User;
import com.asset.eg.courses.model.util.CoursesLoggers;
import com.asset.eg.courses.model.util.DBConstants;
import com.asset.eg.courses.model.util.CoursesException;
import com.asset.eg.courses.model.util.ErrorsStrings;

public class UserService{

	
	Connection conn;

	public UserService(Connection connection) {
		conn = connection;	
	}
	/**
	 * adds user to the Data base
	 * @param user the user data to be added 
	 * @return true if the user added ,false otherwise
	 * @throws CoursesException
	 * 			throws exception if the user name exists 
	 * 			throws exception if the Email is already exists 
	 * 			throws exception if cant get user id from the database sequence	
	 */
	

	public boolean addUser(User user) throws CoursesException{
		UserDAO userDAO = new UserDAO(conn);
		int userId = DBSequence.getNextId(conn,DBConstants.COURSE_USER_SEQ_SECOND);
		if(userId!=-1){
			user.setId(userId);
		}else{			
			CoursesException CE = new CoursesException(ErrorsStrings.CANT_GET_USER_ID_ERROR,ErrorsStrings.CANT_GET_USER_ID_MESSAGE);
			CoursesLoggers.LUSERSERVICE.error(CE);
			CoursesLoggers.LUSERSERVICE.info(ErrorsStrings.CANT_GET_USER_ID_MESSAGE);
			throw CE;				
			
		}
		if (userDAO.getUserByEmail(user.getEmail()) == null && userDAO.getUserByUserName(user.getUserName()) == null ) {
			return userDAO.addUser(user);
		} else if(userDAO.getUserByEmail(user.getEmail()) == null){
			CoursesException CE = new CoursesException(ErrorsStrings.USER_NAME_ALREADY_EXIST_ERROR,ErrorsStrings.USER_NAME_ALREADY_EXIST_MESSAGE);
			CoursesLoggers.LUSERSERVICE.error(CE);
			CoursesLoggers.LUSERSERVICE.info(ErrorsStrings.USER_NAME_ALREADY_EXIST_MESSAGE);
			throw CE;				
			
		}else {
			CoursesException CE = new CoursesException(ErrorsStrings.EMAIL_ALREADY_EXISTS_ERROR,ErrorsStrings.EMAIL_ALREADY_EXISTS_MESSAGE);
			CoursesLoggers.LUSERSERVICE.error(CE);
			CoursesLoggers.LUSERSERVICE.info(ErrorsStrings.EMAIL_ALREADY_EXISTS_MESSAGE);
			throw CE;	
			}	
	}
	/**
	 * updates users data
	 * @param user the new data of user that will be updated
	 * @return true if the user updated ,false otherwise
	 * @throws CoursesException
	 * 				throws exception if the user data doesnt exist
	 */

	public boolean updateUser(User user) throws CoursesException {
		UserDAO userDAO = new UserDAO(conn);
		if (userDAO.getUserByUserName(user.getUserName()) != null) {
			return userDAO.updateUser(user);
		}
	
		CoursesException CE = new CoursesException(ErrorsStrings.USER_DATA_NOT_EXIST_ERROR,ErrorsStrings.USER_DATA_NOT_EXIST_MESSAGE);
		CoursesLoggers.LUSERSERVICE.error(CE);
		CoursesLoggers.LUSERSERVICE.info(ErrorsStrings.USER_DATA_NOT_EXIST_MESSAGE);
		throw CE;	
	}

	public User findUser(User user) throws CoursesException {
		UserDAO userDAO = new UserDAO(conn);
		return userDAO.findUser(user);
	}
	/**
	 * logging user to the system
	 * @param user the user Data
	 * @return logged in user data ,null other wise
	 * @throws CoursesException
	 * 			throws exception if the retrieved user no exist 
	 */

	public User login(User user) throws CoursesException {
		UserDAO userDAO = new UserDAO(conn);
		
		User retrievedUser = userDAO.findUser(user);
		if(retrievedUser==null){
			CoursesException CE = new CoursesException(ErrorsStrings.INVALED_USER_NAME_PASSWORD_ERROR,ErrorsStrings.INVALED_USER_NAME_PASSWORD_MESSAGE);
			CoursesLoggers.LUSERSERVICE.error(CE);
			CoursesLoggers.LUSERSERVICE.info(ErrorsStrings.INVALED_USER_NAME_PASSWORD_MESSAGE);
			throw CE;			
		}
		return userDAO.findUser(user);
	}
	
	/**
	 * if the user name and password are exist it loging user to the system
	 * @param userName the name of the user
	 * @param password the password of the user
	 * @return user if the username and password match 
	 * @throws CoursesException
	 *   		throws exception if the user name and password doesnt match 
	 */
	
	public User login(String userName,String password) throws CoursesException {
		UserDAO userDAO = new UserDAO(conn);		
		User retrievedUser = userDAO.getUser(userName,password);
		if(retrievedUser==null){
			CoursesException CE = new CoursesException(ErrorsStrings.INVALED_USER_NAME_PASSWORD_ERROR,ErrorsStrings.INVALED_USER_NAME_PASSWORD_MESSAGE);
			CoursesLoggers.LUSERSERVICE.error(CE);
			CoursesLoggers.LUSERSERVICE.info(ErrorsStrings.INVALED_USER_NAME_PASSWORD_MESSAGE);
			throw CE;			
		}
		return retrievedUser;
	}
}
