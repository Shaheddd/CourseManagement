package com.example.coursemanagement.Entity;

public class Administrator {

    private int administratorID;
    private String administratorFirstName;
    private String administratorLastName;
    private String administratorAddress;
    private String administratorPhoneNumber;
    private int userID;

    public Administrator() {

    }

    public Administrator(int administratorID, String administratorFirstName, String administratorLastName, String administratorAddress, String administratorPhoneNumber, int userID) {
        this.administratorID = administratorID;
        this.administratorFirstName = administratorFirstName;
        this.administratorLastName = administratorLastName;
        this.administratorAddress = administratorAddress;
        this.administratorPhoneNumber = administratorPhoneNumber;
        this.userID = userID;
    }

    public int getAdministratorID() {
        return administratorID;
    }

    public void setAdministratorID(int administratorID) {
        this.administratorID = administratorID;
    }

    public String getAdministratorFirstName() {
        return administratorFirstName;
    }

    public void setAdministratorFirstName(String administratorFirstName) {
        this.administratorFirstName = administratorFirstName;
    }

    public String getAdministratorLastName() {
        return administratorLastName;
    }

    public void setAdministratorLastName(String administratorLastName) {
        this.administratorLastName = administratorLastName;
    }

    public String getAdministratorAddress() {
        return administratorAddress;
    }

    public void setAdministratorAddress(String administratorAddress) {
        this.administratorAddress = administratorAddress;
    }

    public String getAdministratorPhoneNumber() {
        return administratorPhoneNumber;
    }

    public void setAdministratorPhoneNumber(String administratorPhoneNumber) {
        this.administratorPhoneNumber = administratorPhoneNumber;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
