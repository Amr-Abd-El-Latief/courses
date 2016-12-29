/**
 *@author  Amr Abd El latief 
 * 
 * 
 */

package com.asset.eg.courses.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.asset.eg.courses.model.pojo.User;
import com.asset.eg.courses.model.util.CoursesLoggers;
import com.asset.eg.courses.model.util.DBConstants;
import com.asset.eg.courses.model.util.CoursesException;
import com.asset.eg.courses.model.util.EncryptDecrypt;
import com.asset.eg.courses.model.util.ErrorsStrings;
import com.asset.eg.courses.model.util.OracleConnection;


public class UserDAO  {

	// Single Connection per table
	Connection conn;
	public UserDAO(Connection connection) {
		conn = connection;
	}
	/**
	 * adds user to the course_user table
	 * @param user the user to be added 
	 * @return true if the user added , false other wise
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 */

	public boolean addUser(User user) throws CoursesException {
		String createUserSql = "INSERT INTO COURSE_USER(id,user_name,passwd,email,country_id,cell_phone,city_id,date_of_birth,first_name,last_name) VALUES(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmnt = null;
		try {
			pstmnt = conn.prepareStatement(createUserSql);
			pstmnt.setInt(1, user.getId());
			pstmnt.setString(2, user.getUserName());
			pstmnt.setString(3,EncryptDecrypt.encrypt(user.getPasswd()));
			pstmnt.setString(4, user.getEmail());
			pstmnt.setInt(5, user.getCountryId());
			pstmnt.setString(6, user.getCellPhone());
			pstmnt.setInt(7, user.getCityId());
			pstmnt.setDate(8,new java.sql.Date(user.getDateOfBirth().getTime()));
			pstmnt.setString(9, user.getFirstName());
			pstmnt.setString(10, user.getLastName());
			int res = pstmnt.executeUpdate();
			// conn.commit(); ***************
			return (res > 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LUSERDAO.error(CE);
			CoursesLoggers.LUSERDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;
			
		}finally{
			
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);
		}
	}

	/**
	 * updates certain user 
	 * @param user the user to be updated
	 * @return true if the use updated , false other wise
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 */

	public boolean updateUser(User user) throws CoursesException{
		String createUserSql = "UPDATE  COURSE_USER set email=?,passwd=?,country_id=?,cell_phone=?,city_id=?,date_of_birth=?,first_Name=?,last_Name=? where user_name=?";
		PreparedStatement pstmnt = null;
		try {
		pstmnt = conn.prepareStatement(createUserSql);

			pstmnt.setString(1, user.getEmail());
			pstmnt.setString(2,EncryptDecrypt.encrypt(user.getPasswd()));
			pstmnt.setInt(3, user.getCountryId());
			pstmnt.setString(4, user.getCellPhone());
			pstmnt.setInt(5, user.getCityId());
			pstmnt.setDate(6,new java.sql.Date(user.getDateOfBirth().getTime()));
		
			pstmnt.setString(7,user.getFirstName());
			pstmnt.setString(8,user.getLastName());
			pstmnt.setString(9,user.getUserName());
			int res = pstmnt.executeUpdate();
			// conn.commit(); ***************
			return (res > 0);
		} catch (SQLException e) {
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LUSERDAO.error(CE);
			CoursesLoggers.LUSERDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;
			
			
		}finally{
			
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);
		}
	}

	/**
	 * finds certain user in the course_user table
	 * @param user the user with the id  
	 * @return  the found user 
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 */
	public User findUser(User user)throws CoursesException {

		User userRetrieved = null;
		String loginUserSql = "SELECT * FROM COURSE_USER WHERE passwd = ? and user_name=?";
		PreparedStatement pstmnt = null;
		ResultSet rs = null;
		try {
			 pstmnt = conn.prepareStatement(loginUserSql);

			pstmnt.setString(1, user.getUserName());
	//		System.out.println(user.getUserName());
			pstmnt.setString(2,EncryptDecrypt.encrypt(user.getPasswd()));
	//		System.out.println( EncryptDecrypt.encrypt(user.getPasswd()));
			rs = pstmnt.executeQuery();
		
			while (rs.next()) {
				userRetrieved = new User();
				userRetrieved.setId(rs.getInt(DBConstants.COURSE_USER_ID));
				userRetrieved.setUserName(rs.getString(DBConstants.COURSE_USER_USER_NAME));
				userRetrieved.setPasswd(EncryptDecrypt.Decrypt(rs.getString(DBConstants.COURSE_USER_PASSWD)));
				userRetrieved.setEmail(rs.getString(DBConstants.COURSE_USER_EMAIL));
				userRetrieved.setCellPhone(rs.getString(DBConstants.COURSE_USER_CELL_PHONE));
				userRetrieved.setCityId(rs.getInt(DBConstants.COURSE_USER_CITY_ID));
				userRetrieved.setCountryId(rs.getInt(DBConstants.COURSE_USER_COUNTRY_ID));
				userRetrieved.setDateOfBirth(rs.getDate(DBConstants.COURSE_USER_DATE_OF_BIRTH));
				userRetrieved.setFirstName(rs.getString(DBConstants.COURSE_USER_FIRST_NAME));
				userRetrieved.setLastName(rs.getString(DBConstants.COURSE_USER_LAST_NAME));
			}

			return userRetrieved;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LUSERDAO.error(CE);
			CoursesLoggers.LUSERDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;		

		}finally{
			OracleConnection.closeOracleResultSet(rs);
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);
		}
	}
	

/**
 * gets user by user name and password
 * @param userName the name of the user 
 * @param password the password of the user
 * @return User opject wraps all the data of the user
 * @throws CoursesException
 * 				through exception if there is SQL Exception at the database
 */

	public User getUser(String userName ,String password)throws CoursesException {			
		User testUser = getUserByUserName(userName);		
		if(testUser ==null){			
			return null;
		}else{
			if(testUser.getPasswd().equals(EncryptDecrypt.encrypt(password))){
								return testUser;								
			}else{// not logged in
					return null;			
			}			
		}	

	}

	/**
	 * gets certain user by his email 
	 * @param email  the email of the user
 	 * @return User data from the database
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 */
	public User getUserByEmail(String email) throws CoursesException {

		User userRetrieved = null;
		String loginUserSql = "SELECT * FROM COURSE_USER WHERE  EMAIL=?";
		PreparedStatement pstmnt = null;
		ResultSet rs = null;
		try {
			 pstmnt = conn.prepareStatement(loginUserSql);

			pstmnt.setString(1, email);

			rs = pstmnt.executeQuery();
			while (rs.next()) {
				userRetrieved = new User();
				userRetrieved.setId(rs.getInt(DBConstants.COURSE_USER_ID));
				userRetrieved.setUserName(rs.getString(DBConstants.COURSE_USER_USER_NAME));
				userRetrieved.setPasswd(EncryptDecrypt.Decrypt(rs.getString(DBConstants.COURSE_USER_PASSWD)));
				userRetrieved.setEmail(rs.getString(DBConstants.COURSE_USER_EMAIL));
				userRetrieved.setCellPhone(rs.getString(DBConstants.COURSE_USER_CELL_PHONE));
				userRetrieved.setCityId(rs.getInt(DBConstants.COURSE_USER_CITY_ID));
				userRetrieved.setCountryId(rs.getInt(DBConstants.COURSE_USER_COUNTRY_ID));
				userRetrieved.setDateOfBirth(rs.getDate(DBConstants.COURSE_USER_DATE_OF_BIRTH));
				userRetrieved.setFirstName(rs.getString(DBConstants.COURSE_USER_FIRST_NAME));
				userRetrieved.setLastName(rs.getString(DBConstants.COURSE_USER_LAST_NAME));		
			}
			return userRetrieved;

		} catch (SQLException e) {
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LUSERDAO.error(CE);
			CoursesLoggers.LUSERDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;
			
		}finally{
			OracleConnection.closeOracleResultSet(rs);
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);
		}

	}

	/**
	 * gets certain user by his name
	 * @param userName the name of user to be retrieved
	 * @return User objects wraps user data
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 */
	public User getUserByUserName(String userName) throws CoursesException {
		User userRetrieved = null;
		String loginUserSql = "SELECT * FROM COURSE_USER WHERE  user_name=?";
		PreparedStatement pstmnt = null;
		ResultSet rs = null;
		try {
			pstmnt = conn.prepareStatement(loginUserSql);
			pstmnt.setString(1, userName);
			rs = pstmnt.executeQuery();
			while (rs.next()) {
				userRetrieved = new User();
				userRetrieved.setId(rs.getInt(DBConstants.COURSE_USER_ID));
				userRetrieved.setUserName(rs.getString(DBConstants.COURSE_USER_USER_NAME));
				userRetrieved.setPasswd(rs.getString(DBConstants.COURSE_USER_PASSWD));
				userRetrieved.setEmail(rs.getString(DBConstants.COURSE_USER_EMAIL));
				userRetrieved.setCellPhone(rs.getString(DBConstants.COURSE_USER_CELL_PHONE));
				userRetrieved.setCityId(rs.getInt(DBConstants.COURSE_USER_CITY_ID));
				userRetrieved.setCountryId(rs.getInt(DBConstants.COURSE_USER_COUNTRY_ID));
				userRetrieved.setDateOfBirth(rs.getDate(DBConstants.COURSE_USER_DATE_OF_BIRTH));
				userRetrieved.setFirstName(rs.getString(DBConstants.COURSE_USER_FIRST_NAME));
				userRetrieved.setLastName(rs.getString(DBConstants.COURSE_USER_LAST_NAME));		
		
				System.out.println("second get by name");
			}
			return userRetrieved;
		} catch (SQLException e) {
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LUSERDAO.error(CE);
			CoursesLoggers.LUSERDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;
			
		}finally{
			OracleConnection.closeOracleResultSet(rs);
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);
		}

	}
	
	/**
	 * gets certain user by its id
	 * @param id the id of the user
	 * @return User object wraps user data , null if the user doesn't exist
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 */
	public User getUserByID(Integer id) throws CoursesException {

		User userRetrieved = null;
		String loginUserSql = "SELECT * FROM COURSE_USER WHERE  id=?";
		PreparedStatement pstmnt = null;
		ResultSet rs =null;
		try {
		 pstmnt = conn.prepareStatement(loginUserSql);
			pstmnt.setInt(1, id);
			rs = pstmnt.executeQuery();
			while(rs.next()) {
				userRetrieved = new User();
				userRetrieved.setId(rs.getInt(DBConstants.COURSE_USER_ID));
				userRetrieved.setUserName(rs.getString(DBConstants.COURSE_USER_USER_NAME));
				userRetrieved.setPasswd(EncryptDecrypt.Decrypt(rs.getString(DBConstants.COURSE_USER_PASSWD)));
				userRetrieved.setEmail(rs.getString(DBConstants.COURSE_USER_EMAIL));
				userRetrieved.setCellPhone(rs.getString(DBConstants.COURSE_USER_CELL_PHONE));
				userRetrieved.setCityId(rs.getInt(DBConstants.COURSE_USER_CITY_ID));
				userRetrieved.setCountryId(rs.getInt(DBConstants.COURSE_USER_COUNTRY_ID));
				userRetrieved.setDateOfBirth(rs.getDate(DBConstants.COURSE_USER_DATE_OF_BIRTH));
				userRetrieved.setFirstName(rs.getString(DBConstants.COURSE_USER_FIRST_NAME));
				userRetrieved.setLastName(rs.getString(DBConstants.COURSE_USER_LAST_NAME));		
		
			}
			return userRetrieved;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LUSERDAO.error(CE);
			CoursesLoggers.LUSERDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;
			
		}finally{
			OracleConnection.closeOracleResultSet(rs);
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);
		}

	}

}
