package com.example.coursemanagement.Entity;

public class Exam {

    private int examID;
    private String examName;
    private String examType;
    private String examDescription;

    public Exam() {

    }

    public Exam(int examID, String examName, String examType, String examDescription) {
        this.examID = examID;
        this.examName = examName;
        this.examType = examType;
        this.examDescription = examDescription;
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getExamDescription() {
        return examDescription;
    }

    public void setExamDescription(String examDescription) {
        this.examDescription = examDescription;
    }
}
