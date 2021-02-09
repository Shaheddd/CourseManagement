package com.example.coursemanagement.Entity;

public class Course {

    private int courseID;
    private String courseName;
    private String courseType;
    private String courseDescription;

    public Course() {
    }

    public Course(int courseID, String courseName, String courseType, String courseDescription) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseType = courseType;
        this.courseDescription = courseDescription;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
}
