/**
 * @author Amr abd el latief amrabdellatief1@gmail.com
 */

package com.asset.eg.courses.delegate;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import com.asset.eg.courses.model.dao.TechnologyCategoryDAO;
import com.asset.eg.courses.model.dao.UserDAO;
import com.asset.eg.courses.model.pojo.City;
import com.asset.eg.courses.model.pojo.Country;
import com.asset.eg.courses.model.pojo.Course;
import com.asset.eg.courses.model.pojo.LearningField;
import com.asset.eg.courses.model.pojo.Objective;
import com.asset.eg.courses.model.pojo.TechnologyCategory;
import com.asset.eg.courses.model.pojo.Topic;
import com.asset.eg.courses.model.pojo.User;
import com.asset.eg.courses.model.util.CoursesLoggers;
import com.asset.eg.courses.model.util.CoursesException;
import com.asset.eg.courses.model.util.OracleConnection;
import com.asset.eg.courses.services.CityService;
import com.asset.eg.courses.services.CountryService;
import com.asset.eg.courses.services.CourseService;
import com.asset.eg.courses.services.LearningFieldService;
import com.asset.eg.courses.services.ObjectiveService;
import com.asset.eg.courses.services.TechnologyCategoryService;
import com.asset.eg.courses.services.TopicService;
import com.asset.eg.courses.services.USCService;
import com.asset.eg.courses.services.UserService;

public class MainDelegate {

	/**
	 * registers user to the system 
	 * @param user the user to be registered 
	 * @return true if the user added , false otherwise
	 * @throws CoursesException
	 * 			throws exception if	Database SQLexception
	 * 			throws exception if the user name exists 
	 * 			throws exception if the Email is already exists 	
	 * 			throws exception if cant get user id from the database sequence 
	 */
	public boolean registerToSystem(User user) throws CoursesException {
		CoursesLoggers.LMAINDELEGATE.info("Start of Register");
		Connection mainConnection = OracleConnection.getOracleConnection();
		UserService userService = new UserService(mainConnection);
		boolean res = false;
		try {
			res = userService.addUser(user);
			OracleConnection.commitOracleConnection(mainConnection);
		} catch (CoursesException e) {
			OracleConnection.rollBackOracleConnection(mainConnection);
			throw e;
		} finally {
			OracleConnection.closeOracleConnection(mainConnection);
		}
		CoursesLoggers.LMAINDELEGATE.info("End of Register");
		return res;
	}
	/**
	 * login user to the system 
	 * @param userName the name of the user
	 * @param password the password of the user
	 * @return User data if the user exist , null otherwise
	 * @throws CoursesException
	 * 			throws exception if	Database SQLexception
	 *   		throws exception if the user name and password doesnt match 
	 */

	public User login(String userName,String password) throws CoursesException {
		CoursesLoggers.LMAINDELEGATE.info("Start of login");
		Connection mainConnection = OracleConnection.getOracleConnection();
		UserService userService = new UserService(mainConnection);
		User user = userService.login(userName,password);		
		OracleConnection.closeOracleConnection(mainConnection);
		return user;
	}
	/**
	 * shows all courses in the course table
	 * @return array list of courses 
	 * @throws CoursesException
	 *             through exception if there is SQL Exception at the database
	 */

	public ArrayList<Course> showAllCourses() throws CoursesException {
		CoursesLoggers.LMAINDELEGATE.info("Start of show Courses");
		Connection mainConnection = OracleConnection.getOracleConnection();
		CourseService courseService = new CourseService(mainConnection);
		CoursesLoggers.LMAINDELEGATE.info("End of showAllCourses");
		ArrayList<Course> allCourses = courseService.getAllCourses();
		OracleConnection.closeOracleConnection(mainConnection);
		return allCourses;
	}
/**
 * shows all the topics in the course_topic table for certain course
 * @param courseId the id of the course
 * @return array list of all topics for certain course
 * @throws CoursesException 
 * 				through exception if there is SQL Exception at the database
 */
	public ArrayList<Topic> showAllTopics(int courseId) throws CoursesException {
		CoursesLoggers.LMAINDELEGATE.info("Start of show Topics");
		Connection mainConnection = OracleConnection.getOracleConnection();
		TopicService topicService = new TopicService(mainConnection);
		CoursesLoggers.LMAINDELEGATE.info("End of showTopics");
		ArrayList<Topic> allTopics = topicService.getAllTopics(courseId);
		OracleConnection.closeOracleConnection(mainConnection);
		return allTopics;
	}
/**
 * shows all the objectives of certain course
 * @param courseId the id of the course
 * @return array list of all objectives for this course
 * @throws CoursesException
 * 				through exception if there is SQL Exception at the database
 */
	public ArrayList<Objective> showAllObjectives(int courseId) throws CoursesException {
		CoursesLoggers.LMAINDELEGATE.info("Start of show objectives");
		Connection mainConnection = OracleConnection.getOracleConnection();
		ObjectiveService objectiveService = new ObjectiveService(mainConnection);
		CoursesLoggers.LMAINDELEGATE.info("End of showTopics");
		ArrayList<Objective> allObjectives = objectiveService.getAllgetAllObjectives(courseId);
		OracleConnection.closeOracleConnection(mainConnection);
		return allObjectives;
	}
	/**
	 * edits the profile of certain user 
	 * @param user the user data 
	 * @return true if the user data updated 
	 * @throws CoursesException 
	 * 				throws exception if the user data doesnt exist
	 */

	public boolean editProfile(User user) throws CoursesException {
		CoursesLoggers.LMAINDELEGATE.info("Start of editProfile");
		Connection mainConnection = OracleConnection.getOracleConnection();
		UserService userService = new UserService(mainConnection);
		boolean res = false;
		try {
			res = userService.updateUser(user);
			OracleConnection.commitOracleConnection(mainConnection);

		} catch (CoursesException e) {
			OracleConnection.rollBackOracleConnection(mainConnection);
			throw e;

		} finally {
			OracleConnection.closeOracleConnection(mainConnection);
		}
		CoursesLoggers.LMAINDELEGATE.info("End of editProfile");
		return res;

	}
	
	/**
	 * Views the course details of certain course
	 * @param courseCode the code of the course to be viewd
	 * @return the course object 
	 * @throws CoursesException
	 * 		through exception if there is SQL Exception at the database
	 */

	public Course viewCourseDetails(String courseCode) throws CoursesException {
		CoursesLoggers.LMAINDELEGATE.info("Start of viewCourseDetails");
		Connection mainConnection = OracleConnection.getOracleConnection();
		CourseService courseService = new CourseService(mainConnection);
		Course course = courseService.getCourseByCourseCode(courseCode);
		OracleConnection.closeOracleConnection(mainConnection);
		CoursesLoggers.LMAINDELEGATE.info("End of viewCourseDetails");
		return course;
	}
/**
 * views the course details by course id 
 * @param courseId the id of the course 
 * @return the course object 
 * @throws CoursesException
 * 				through exception if there is SQL Exception at the database
 * 				or there is no courses with this id
 */
	public Course viewCourseDetailsByID(int courseId) throws CoursesException {
		CoursesLoggers.LMAINDELEGATE.info("start of viewCourseDetailsByID");
		Connection mainConnection = OracleConnection.getOracleConnection();
		CourseService courseService = new CourseService(mainConnection);
		Course course = courseService.getCourseByCourseId(courseId);
		OracleConnection.closeOracleConnection(mainConnection);
		CoursesLoggers.LMAINDELEGATE.info("end of viewCourseDetailsByID");
		return course;
	}
	
	/**
	 * Subscribes user to course
	 * @param userId the id of the user
	 * @param courseId  the id of the course
	 * @return  true if the user successfully subscribed ,false other wise 
	 * @throws CoursesException
	 */

	public boolean subscripeToCourse(int userId, int courseId) throws CoursesException {
		CoursesLoggers.LMAINDELEGATE.info("Start of subscripeToCourse");
		Connection mainConnection = OracleConnection.getOracleConnection();
		USCService uscService = new USCService(mainConnection);
		boolean res = false;
		try {
			res = uscService.subscripeToCourse(userId, courseId);
			OracleConnection.commitOracleConnection(mainConnection);
		} catch (CoursesException e) {
			OracleConnection.rollBackOracleConnection(mainConnection);
			throw e;
		} finally {
			OracleConnection.closeOracleConnection(mainConnection);
		}
		CoursesLoggers.LMAINDELEGATE.info("End of subscripeToCourse");
		return res;
	}

	/**
	 * gets all the countries 	
	 * @return array list of countries
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database		
	 */
	public ArrayList<Country> getAllCountries() throws CoursesException {
		CoursesLoggers.LMAINDELEGATE.info("Start of  getAllCountries");
		Connection mainConnection = OracleConnection.getOracleConnection();
		CountryService countryService = new CountryService(mainConnection);
		CoursesLoggers.LMAINDELEGATE.info("End of getAllCountries");
		ArrayList<Country> allCountries = countryService.getAllCountries();
		OracleConnection.closeOracleConnection(mainConnection);
		return allCountries;
	}
	/**
	 * gets all cities of certain country 
	 * @param id the id of the country
	 * @return array list of all cities belong to this country
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database	
	 */

	public ArrayList<City> getCitiesOfCountry(int id) throws CoursesException {
		CoursesLoggers.LMAINDELEGATE.info("Start of  getCitiesOfCountry");
		Connection mainConnection = OracleConnection.getOracleConnection();
		CityService cityService = new CityService(mainConnection);
		CoursesLoggers.LMAINDELEGATE.info("End of getCitiesOfCountry");
		ArrayList<City> allCities = cityService.citiesOfCertainCountry(id);
		OracleConnection.closeOracleConnection(mainConnection);
		return allCities;
	}
/**
 * gets all the technologies 
 * @return Array list of technologies 
 * @throws CoursesException
 * 				through exception if there is SQL Exception at the database
 */
	public ArrayList<TechnologyCategory> getAllTechnologies() throws CoursesException {
		CoursesLoggers.LMAINDELEGATE.info("Start of  getAllTechnologies");
		Connection mainConnection = OracleConnection.getOracleConnection();
		TechnologyCategoryService techService = new TechnologyCategoryService(mainConnection);
		CoursesLoggers.LMAINDELEGATE.info("End of getAllTechnologies");
		ArrayList<TechnologyCategory> allTechnologies = techService.getAllTechnologies();
		OracleConnection.closeOracleConnection(mainConnection);
		return allTechnologies;
	}
	/**
	 * 	gets technology category by id
	 * @param id the id pof the technology category
	 * @return TechnologyCategory object
	 * @throws CoursesException 
	 * 				through exception if there is SQL Exception at the database
	 */

	public TechnologyCategory geCategorById(int id) throws CoursesException {
		CoursesLoggers.LMAINDELEGATE.info("Start of geCategorById");
		Connection mainConnection = OracleConnection.getOracleConnection();
		TechnologyCategoryService techService = new TechnologyCategoryService(mainConnection);
		TechnologyCategory techCategory = techService.geCategorById(id);
		OracleConnection.closeOracleConnection(mainConnection);
		CoursesLoggers.LMAINDELEGATE.info("End of geCategorById");
		return techCategory;
	}
/**
 * gets all the learning fields 
 * @return Array list of all learning fields
 * @throws CoursesException
 * 				through exception if there is SQL Exception at the database
 */
	public ArrayList<LearningField> getAllLearningFields() throws CoursesException {
		CoursesLoggers.LMAINDELEGATE.info("Start of  getAllLearningFields");
		Connection mainConnection = OracleConnection.getOracleConnection();
		LearningFieldService fieldService = new LearningFieldService(mainConnection);
		CoursesLoggers.LMAINDELEGATE.info("End of getAllLearningFields");
		ArrayList<LearningField> allFileds = fieldService.getAllLearningFields();
		OracleConnection.closeOracleConnection(mainConnection);
		return allFileds;
	}

	/**
	 * gets certain learning field 
	 * @param id the id of the learning field
	 * @return  LearningField object
	 * @throws CoursesException
	 * 				through exception if there is SQL Exception at the database
	 */
	public LearningField getLearningFieldById(int id) throws CoursesException {
		CoursesLoggers.LMAINDELEGATE.info("Start of getLearningFieldById");
		Connection mainConnection = OracleConnection.getOracleConnection();
		LearningFieldService fieldService = new LearningFieldService(mainConnection);
		LearningField field = fieldService.getLearningFieldById(id);
		OracleConnection.closeOracleConnection(mainConnection);
		CoursesLoggers.LMAINDELEGATE.info("End of getLearningFieldById");
		return field;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// create user for test
		User user = new User();
		user.setUserName("iii");
		user.setPasswd("iiii");
		user.setCellPhone("11111111111111");
		user.setCityId(1);
		user.setCountryId(1);
		user.setEmail("iiiii@t.com");
		user.setDateOfBirth(new java.sql.Date(200));
		MainDelegate mainDelegate = new MainDelegate();
		Connection connection;
/*
		try {
			connection = OracleConnection.getOracleConnection();
			UserDAO userDAO = new UserDAO(connection);
			System.out.println(userDAO.getNextrId());
		} catch (CoursesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		// ******************************test of delegate
		// test register to sys
		/*
		  try{ mainDelegate.registerToSystem(user); }catch(CoursesException e){
		  e.printStackTrace(); }
		 */
		 

		// test login
		/*
		  try { System.out.println("login result "+mainDelegate.login("iii","iiii")); }
		  catch (CoursesException e) { // TODO Auto-generated catch block
		  e.printStackTrace(); }
		 */

		// test view course
	/*
		 Course course =null; try { course =
		 mainDelegate.viewCourseDetails("1");
		 System.out.println(course.getCourseCode()); } catch (Exception e) {
		 // TODO Auto-generated catch block e.printStackTrace(); 
			 }
		*/ 

		
		// test view course by id
		/*
		  Course course =null; try { course =
		  mainDelegate.viewCourseDetailsByID(39);
		 System.out.println(course.getCourseCode()); } catch (Exception e) {
		  // TODO Auto-generated catch block e.printStackTrace(); }
		 
		  }
		 */
		 
		// test subscripte course to user
		/*
		 Course course =null; try { course =
		 mainDelegate.viewCourseDetails("1");
		 
		 } catch (Exception e) { // TODO Auto-generated catch block
		  e.printStackTrace(); }
		 
		 try { System.out.println(mainDelegate.subscripeToCourse(21,39)); }
		 catch (CoursesException e) { // TODO Auto-generated catch block
		 e.printStackTrace(); }
		 
		 */
		
		// test edit profile
		/*
		 try { System.out.println(mainDelegate.editProfile(user)); } catch
		 (Exception e) { // TODO Auto-generated catch block
		 e.printStackTrace(); 
		 }
		 */

		/*
		  try { System.out.println(mainDelegate.login(user)); } catch
		 (Exception e) { // TODO Auto-generated catch block
		 e.printStackTrace(); }
		 */
		
		 /*
		  try { System.out.println(mainDelegate.viewCourseDetails("1")); }
		  catch (SQLException e) { // TODO Auto-generated catch block
		 e.printStackTrace(); } catch (Exception e) { // TODO Auto-generated
		  catch block e.printStackTrace(); }
		  */
		 

		// test of list methods
	/*	
		 try { ArrayList<Objective> objectives =
		  mainDelegate.showAllObjectives(39); ArrayList<Topic> topics =
		  mainDelegate.showAllTopics(39); Iterator<Objective> iter
		  =objectives.iterator();
		 
		  while(iter.hasNext()){
		 System.out.println(iter.next().getObjective()); }
		  
		 Iterator<Topic> iter2 =topics.iterator();
		 
		  while(iter2.hasNext()){ System.out.println(iter2.next().getTopic());
		 } } catch (CoursesException e) { // TODO Auto-generated catch block
		  e.printStackTrace(); }
		 
*/
		
		/*
		
		  try { ArrayList<Course> courses = mainDelegate.showAllCourses();
		  Iterator<Course> iter2 =courses.iterator(); while(iter2.hasNext()){
		  System.out.println(iter2.next().getCourseName()); } } catch
		  (CoursesException e) { // TODO Auto-generated catch block
		  e.printStackTrace(); }
		 */

		// test of list methods
/*
		try {

			ArrayList<City> cities = mainDelegate.getCitiesOfCountry(1);
			ArrayList<Country> cs = mainDelegate.getAllCountries();
			Iterator<City> iter2 = cities.iterator();

			while (iter2.hasNext()) {
				System.out.println(iter2.next().getCityName());
			}

			Iterator<Country> iter3 = cs.iterator();

			while (iter3.hasNext()) {
				System.out.println(iter3.next().getCountry_name());
			}
		} catch (CoursesException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

	*/
		/*
		try {

			ArrayList<TechnologyCategory> techs = mainDelegate.getAllTechnologies();
			ArrayList<LearningField> lf = mainDelegate.getAllLearningFields();
			Iterator<TechnologyCategory> iter2 = techs.iterator();

			while (iter2.hasNext()) {
				System.out.println(iter2.next().getCategoryValue());
			}

			Iterator<LearningField> iter3 = lf.iterator();

			while (iter3.hasNext()) {
				System.out.println(iter3.next().getFieldValue());
			}
		} catch (CoursesException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
		/*
		
		try {

			TechnologyCategory techs = mainDelegate.geCategorById(1);
			LearningField lf = mainDelegate.getLearningFieldById(1);
	System.out.println(techs.getCategoryValue());
	System.out.println(lf.getFieldValue());
			
		}catch(

	CoursesException e)
	{ // TODO Auto-generated catch block
		e.printStackTrace();
	}
*/
		
		
}

		
	}
