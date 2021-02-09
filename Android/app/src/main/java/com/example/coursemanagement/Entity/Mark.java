package com.example.coursemanagement.Entity;

public class Mark {

    private int markID;
    private String marks;
    private String grade;

    public Mark() {

    }

    public Mark(int markID, String marks, String grade) {
        this.markID = markID;
        this.marks = marks;
        this.grade = grade;
    }

    public int getMarkID() {
        return markID;
    }

    public void setMarkID(int markID) {
        this.markID = markID;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
