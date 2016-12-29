package com.asset.eg.courses.test;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.junit.Ignore;
import org.junit.Test;

import com.asset.eg.courses.delegate.MainDelegate;
import com.asset.eg.courses.model.pojo.User;
import com.asset.eg.courses.model.util.CoursesException;

public class MainDelegateTest {
	MainDelegate mainDelegate = new MainDelegate();
	int[][] ids = { { 41, 27 } };

	// execute before class
	@BeforeClass
	public static void beforeClass() {
		System.out.println("** start of testing main Delegate ** ");
	}

	// execute after class
	@AfterClass
	public static void afterClass() {
		System.out.println("** end of testing MainDelegate ** ");
	}

	// test case insert new user data
	@Ignore
	@Test(expected = CoursesException.class)
	public void testInsertNewUser() throws CoursesException {
		User user = new User();
		user.setUserName("iiiee");
		user.setPasswd("iiiiee");
		user.setCellPhone("3");
		user.setCityId(1);
		user.setCountryId(1);
		user.setEmail("3@t.com");
		user.setDateOfBirth(new java.sql.Date(200));
		assertTrue(mainDelegate.registerToSystem(user));
	}

	// test case insert existing user data
	@Ignore
	@Test(expected = CoursesException.class)
	public void test() throws CoursesException {
		User user = new User();
		user.setUserName("iiiee");
		user.setPasswd("iiiiee");
		user.setCellPhone("3");
		user.setCityId(1);
		user.setCountryId(1);
		user.setEmail("3@t.com");
		user.setDateOfBirth(new java.sql.Date(200));
		assertFalse(mainDelegate.registerToSystem(user));
	}

	
	// test case update profile 
	@Ignore
	@Test()
	public void testUpdateUserProfile() throws CoursesException {
		User user = new User();
		user.setUserName("iiiee");
		user.setPasswd("iiiiee");
		user.setCellPhone("4");
		user.setCityId(1);
		user.setCountryId(1);
		user.setEmail("3dfsdf@t.com");
		user.setDateOfBirth(new java.sql.Date(200));
		assertTrue(mainDelegate.editProfile(user));
	}
	
	
	// test login with existing user

	// test case insert existing user data
	@Ignore
	@Test()
	public void testloginExistingUser() throws CoursesException {
		assertNotNull(mainDelegate.login("iiiee", "iiiee"));

	}

	// test case insert existing user data
	@Ignore
	@Test()
	public void testloginNotExistingUser() throws CoursesException {
		assertNotNull(mainDelegate.login("yyyyyyyy", "yyyyyyyyyyyy"));

	}

	// test show all courses
	@Ignore
	@Test()
	public void testShowAllCourses() throws CoursesException {
		assertNotNull(mainDelegate.showAllCourses());
	}

	// test view course details
	@Ignore
	@Test(expected = CoursesException.class)
	public void testViewCourseDetailByID() throws CoursesException {
		for (int i = 0; i < 10; i++) {
			assertNotNull(mainDelegate.viewCourseDetailsByID(i));
		}
	}

	// test subscripe to course
	@Ignore
	@Test
	public void testSubscripeToCourse() throws CoursesException {
		for (int[] is : ids) {
			assertTrue(mainDelegate.subscripeToCourse(is[0], is[1]));
		}
	}

	// test get learning fields
	@Ignore
	@Test()
	public void testGetLearningFields() throws CoursesException {
		assertNotNull(mainDelegate.getAllLearningFields());
	}

	// test All Lists
	@Ignore
	@Test()
	public void testGetLists() throws CoursesException {
		assertNotNull(mainDelegate.getAllCountries());
		assertNotNull(mainDelegate.getAllTechnologies());
		assertNotNull(mainDelegate.getCitiesOfCountry(1));
	}
	

}
