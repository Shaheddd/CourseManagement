package com.example.coursemanagement.Entity;

public class Headmaster {

    private int headmasterID;
    private String headmasterFirstName;
    private String headmasterLastName;
    private String headmasterAddress;
    private String headmasterPhoneNumber;
    private int userID;

    public Headmaster() {

    }

    public Headmaster(int headmasterID, String headmasterFirstName, String headmasterLastName, String headmasterAddress, String headmasterPhoneNumber, int userID) {
        this.headmasterID = headmasterID;
        this.headmasterFirstName = headmasterFirstName;
        this.headmasterLastName = headmasterLastName;
        this.headmasterAddress = headmasterAddress;
        this.headmasterPhoneNumber = headmasterPhoneNumber;
        this.userID = userID;
    }


    public int getHeadmasterID() {
        return headmasterID;
    }

    public void setHeadmasterID(int headmasterID) {
        this.headmasterID = headmasterID;
    }

    public String getHeadmasterFirstName() {
        return headmasterFirstName;
    }

    public void setHeadmasterFirstName(String headmasterFirstName) {
        this.headmasterFirstName = headmasterFirstName;
    }

    public String getHeadmasterLastName() {
        return headmasterLastName;
    }

    public void setHeadmasterLastName(String headmasterLastName) {
        this.headmasterLastName = headmasterLastName;
    }

    public String getHeadmasterAddress() {
        return headmasterAddress;
    }

    public void setHeadmasterAddress(String headmasterAddress) {
        this.headmasterAddress = headmasterAddress;
    }

    public String getHeadmasterPhoneNumber() {
        return headmasterPhoneNumber;
    }

    public void setHeadmasterPhoneNumber(String headmasterPhoneNumber) {
        this.headmasterPhoneNumber = headmasterPhoneNumber;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
