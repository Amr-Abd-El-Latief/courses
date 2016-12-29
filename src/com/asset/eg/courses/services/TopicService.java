package com.asset.eg.courses.services;

import java.sql.Connection;
import java.util.ArrayList;

import com.asset.eg.courses.model.dao.DBSequence;
import com.asset.eg.courses.model.dao.TopicDAO;
import com.asset.eg.courses.model.pojo.Topic;
import com.asset.eg.courses.model.util.CoursesException;
import com.asset.eg.courses.model.util.CoursesLoggers;
import com.asset.eg.courses.model.util.DBConstants;
import com.asset.eg.courses.model.util.ErrorsStrings;

public class TopicService  {

	Connection conn;
	
	public TopicService(Connection connection) {
		conn = connection;	
	}
	/**
	 * adds topic to course_topic table
	 * @param topic the topic object
	 * @return true if the topic added ,false otherwise
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 */
	public boolean addTopic(Topic topic) throws CoursesException {
		int topicId = DBSequence.getNextId(conn,DBConstants.TOPIC_SEQ);		
		if(topicId!=-1){
			topic.setId(topicId);	
		}else{
			CoursesException CE = new CoursesException(ErrorsStrings.CANT_GET_TOPIC_ID_ERROR,ErrorsStrings.CANT_GET_TOPIC_ID_MESSAGE);
			CoursesLoggers.LTOPICSERVICE.error(CE);
			CoursesLoggers.LTOPICSERVICE.info(ErrorsStrings.CANT_GET_TOPIC_ID_MESSAGE);
			throw CE;		
		}		
		
		TopicDAO topicDAO;
		topicDAO = new TopicDAO(conn);
		return topicDAO.addTopic(topic);

	}
	
	/**
	 * gets all topics for certain course
	 * @param courseId the id of the course
	 * @return Array list of all topics for this course
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 */
	public ArrayList<Topic> getAllTopics(int courseId) throws CoursesException{
		TopicDAO topicDAO;
		topicDAO = new TopicDAO(conn);
		return topicDAO.getAllTopics(courseId);
	}

}
