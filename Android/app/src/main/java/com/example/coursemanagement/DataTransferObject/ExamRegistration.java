package com.example.coursemanagement.DataTransferObject;

import com.example.coursemanagement.Entity.Batch;
import com.example.coursemanagement.Entity.Teacher;

public class ExamRegistration {

    private int examID;
    private String examName;
    private String examType;
    private String examDescription;

    private int batchID;
    private int teacherID;

    private Batch batch;
    private Teacher teacher;

    public ExamRegistration() {

    }

    public ExamRegistration(int examID, String examName, String examType, String examDescription, int batchID, int teacherID, Batch batch, Teacher teacher) {
        this.examID = examID;
        this.examName = examName;
        this.examType = examType;
        this.examDescription = examDescription;
        this.batchID = batchID;
        this.teacherID = teacherID;
        this.batch = batch;
        this.teacher = teacher;
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

    public int getBatchID() {
        return batchID;
    }

    public void setBatchID(int batchID) {
        this.batchID = batchID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
