package com.example.coursemanagement.DataTransferObject;

public class CourseRegistration {

    private String courseName;
    private String courseType;
    private String courseDescription;

    public CourseRegistration() {

    }

    public CourseRegistration(String courseName, String courseType, String courseDescription) {
        this.courseName = courseName;
        this.courseType = courseType;
        this.courseDescription = courseDescription;
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
