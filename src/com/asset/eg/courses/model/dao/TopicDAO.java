package com.asset.eg.courses.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.asset.eg.courses.model.pojo.Topic;
import com.asset.eg.courses.model.util.CoursesLoggers;
import com.asset.eg.courses.model.util.DBConstants;
import com.asset.eg.courses.model.util.CoursesException;
import com.asset.eg.courses.model.util.ErrorsStrings;
import com.asset.eg.courses.model.util.OracleConnection;


public class TopicDAO {

	Connection conn ;
	public TopicDAO(Connection connection){
		conn = connection;
	}
	/**
	 * adds topic to certain course
	 * @param topic object of course topic
	 * @return  true if the topic added ,false otherwise
	 * @throws CoursesException
	 *				through exception if there is SQL Exception at the database
	 */

	public boolean addTopic(Topic topic) throws CoursesException {
		String createTopic = "INSERT INTO COURSE_TOPIC(id,course_id,topic) VALUES(?,?,?)";
		PreparedStatement pstmnt = null;
		try {
			pstmnt = conn.prepareStatement(createTopic);
			pstmnt.setInt(1,topic.getId());			
			pstmnt.setInt(2,topic.getCourseId());
			pstmnt.setString(3,topic.getTopic());			
			int res = pstmnt.executeUpdate();
			return (res>0);
		} catch (SQLException e) {
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LTOPICDAO.error(CE);
			CoursesLoggers.LTOPICDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;
			
		}finally{
			
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);
		}		
	}
	
	/**
	 * gets all topics for certain course
	 * @param courseId the id of the course
	 * @return array list of topics for certain course
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 */
	public ArrayList<Topic> getAllTopics(int courseId) throws CoursesException{
		String createTopic = "select * from COURSE_TOPIC where course_id = ?";
		PreparedStatement pstmnt = null;
		ResultSet rs = null;
		try {
			pstmnt = conn.prepareStatement(createTopic);
			pstmnt.setInt(1,courseId);	
			rs = pstmnt.executeQuery();
			ArrayList<Topic> topics = new ArrayList<Topic>();
			while(rs.next()){
				Topic topic = new Topic();
				topic.setCourseId(rs.getInt(DBConstants.COURSE_TOPIC_COURSE_ID));
				topic.setTopic(rs.getString(DBConstants.COURSE_TOPIC_TOPIC));
				topics.add(topic);
			}
			return topics;
			
		} catch (SQLException e) {
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LTOPICDAO.error(CE);
			CoursesLoggers.LTOPICDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;
			
		}finally{
			OracleConnection.closeOracleResultSet(rs);
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);
		}
				
	} 

}
