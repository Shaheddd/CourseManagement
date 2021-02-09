package com.example.coursemanagement.Entity;

public class Teacher {

    private int teacherID;
    private String teacherFirstName;
    private String teacherLastName;
    private String teacherAddress;
    private String teacherPhoneNumber;
    private int userID;

    public Teacher(int teacherID, String teacherFirstName, String teacherLastName) {
        this.teacherID = teacherID;
        this.teacherFirstName = teacherFirstName;
        this.teacherLastName = teacherLastName;
    }

    public Teacher() {
    }

    public Teacher(int teacherID, String teacherFirstName, String teacherLastName, String teacherAddress, String teacherPhoneNumber, int userID) {
        this.teacherID = teacherID;
        this.teacherFirstName = teacherFirstName;
        this.teacherLastName = teacherLastName;
        this.teacherAddress = teacherAddress;
        this.teacherPhoneNumber = teacherPhoneNumber;
        this.userID = userID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
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

    public String getTeacherAddress() {
        return teacherAddress;
    }

    public void setTeacherAddress(String teacherAddress) {
        this.teacherAddress = teacherAddress;
    }

    public String getTeacherPhoneNumber() {
        return teacherPhoneNumber;
    }

    public void setTeacherPhoneNumber(String teacherPhoneNumber) {
        this.teacherPhoneNumber = teacherPhoneNumber;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
