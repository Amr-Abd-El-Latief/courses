package com.asset.eg.courses.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.asset.eg.courses.model.pojo.USC;
import com.asset.eg.courses.model.util.CoursesLoggers;
import com.asset.eg.courses.model.util.CoursesException;
import com.asset.eg.courses.model.util.ErrorsStrings;
import com.asset.eg.courses.model.util.OracleConnection;

public class USCDAO{
	
	Connection conn;
	
	public USCDAO(Connection connection){
			conn = connection;
				}
	/**
	 * checks if the user is subscribed
	 * @param usc user sub scripe to course object 
	 * @return true if the user is subscriped to the course , false otherwise
	 * @throws CoursesException
	 *				through exception if there is SQL Exception at the database
	 */
	public boolean isUserSubscriped(USC usc) throws CoursesException{
			
		String FindUSCSql = "SELECT COUNT(*)  FROM USC WHERE user_id = ? and course_id=?";
		PreparedStatement pstmnt =null;
		ResultSet rs =null;
		try {
			pstmnt = conn.prepareStatement(FindUSCSql);
			pstmnt.setInt(1, usc.getUserId());
			pstmnt.setInt(2, usc.getCourseId());
			rs = pstmnt.executeQuery();
			rs.next();
			int resNumber = rs.getInt(1);
			boolean res = resNumber>0;
			return res;

		} catch (SQLException e) {
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LUSCDAO.error(CE);
			CoursesLoggers.LUSCDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;
			
		}finally{
			OracleConnection.closeOracleResultSet(rs);			
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);
		}
		
			
	}
	
/**
 * Subscribes user to certain course
 * @param usc object(user id,course id)
 * @return true if user subscriped , false other wise
 * @throws CoursesException
 * 				through exception if there is SQL Exception at the database
 */
	public boolean subscripeToCourse(USC usc) throws CoursesException{
		String createUserSql = "INSERT INTO USC(user_id,course_id) VALUES(?,?)";
		PreparedStatement pstmnt= null;
		try {
	pstmnt = conn.prepareStatement(createUserSql);
			pstmnt.setInt(1, usc.getUserId());
			pstmnt.setInt(2, usc.getCourseId());	
			int res = pstmnt.executeUpdate();
			// conn.commit(); ***************
			return (res > 0);
		} catch (SQLException e) {
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LUSCDAO.error(CE);
			CoursesLoggers.LUSCDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;
		}finally{
			
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);
		}
		
	}

	

}
