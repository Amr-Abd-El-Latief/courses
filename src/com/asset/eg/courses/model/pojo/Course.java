package com.asset.eg.courses.model.pojo;

import java.util.Date;

public class Course {
	int id;
	String courseCode;
	String courseName;
	int learningField;
	int technologyCategory;
	Date startDate;
	Date endDate;
	String  description;
	int isCourse;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getLearningField() {
		return learningField;
	}
	public void setLearningField(int learningField) {
		this.learningField = learningField;
	}
	public int getTechnologyCategory() {
		return technologyCategory;
	}
	public void setTechnologyCategory(int technologyCategory) {
		this.technologyCategory = technologyCategory;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIsCourse() {
		return isCourse;
	}
	public void setIsCourse(int isCourse) {
		this.isCourse = isCourse;
	}


}
