package com.example.coursemanagement.DataTransferObject;

import com.example.coursemanagement.Entity.Student;
import com.example.coursemanagement.Entity.Teacher;

public class BatchRegistration {

    private String batchCode;
    private int teacherID;
    private int courseID;
    private int studentID;
    private int batchID;
    private int examID;

    private String teacherFirstName;
    private String teacherLastName;

    private String courseName;
    private String courseType;
    private String courseDescription;

    private Teacher teacher;
    private Student student;

    public BatchRegistration() {

    }

    public BatchRegistration(String batchCode, int teacherID, int courseID, int studentID, int batchID, int examID, String teacherFirstName, String teacherLastName, String courseName, String courseType, String courseDescription, Teacher teacher, Student student) {
        this.batchCode = batchCode;
        this.teacherID = teacherID;
        this.courseID = courseID;
        this.studentID = studentID;
        this.batchID = batchID;
        this.examID = examID;
        this.teacherFirstName = teacherFirstName;
        this.teacherLastName = teacherLastName;
        this.courseName = courseName;
        this.courseType = courseType;
        this.courseDescription = courseDescription;
        this.teacher = teacher;
        this.student = student;
    }

    public BatchRegistration(String batchCode, int teacherID, int courseID, int studentID, int batchID, int examID, String teacherFirstName, String teacherLastName, Teacher teacher, Student student) {
        this.batchCode = batchCode;
        this.teacherID = teacherID;
        this.courseID = courseID;
        this.studentID = studentID;
        this.batchID = batchID;
        this.examID = examID;
        this.teacherFirstName = teacherFirstName;
        this.teacherLastName = teacherLastName;
        this.teacher = teacher;
        this.student = student;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getBatchID() {
        return batchID;
    }

    public void setBatchID(int batchID) {
        this.batchID = batchID;
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
