package com.example.coursemanagement.Entity;

public class Batch {

    private int batchID;
    private String batchCode;

    private Course course;
    private Teacher teacher;

    public Batch() {
    }

    public Batch(int batchID, String batchCode, Course course, Teacher teacher) {
        this.batchID = batchID;
        this.batchCode = batchCode;
        this.course = course;
        this.teacher = teacher;
    }

    public int getBatchID() {
        return batchID;
    }

    public void setBatchID(int batchID) {
        this.batchID = batchID;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
