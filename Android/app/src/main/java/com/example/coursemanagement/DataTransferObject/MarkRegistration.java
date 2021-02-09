package com.example.coursemanagement.DataTransferObject;

import com.example.coursemanagement.Entity.Exam;
import com.example.coursemanagement.Entity.Student;
import com.example.coursemanagement.Entity.Teacher;

public class MarkRegistration {

    private int marks;
    private String grade;

    private int examID;
    private int teacherID;
    private int studentID;
    private int markID;

    private Exam exam;
    private Teacher teacher;
    private Student student;

    public MarkRegistration() {

    }

    public MarkRegistration(int marks, String grade, int examID, int teacherID, int studentID, int markID, Exam exam, Teacher teacher, Student student) {
        this.marks = marks;
        this.grade = grade;
        this.examID = examID;
        this.teacherID = teacherID;
        this.studentID = studentID;
        this.markID = markID;
        this.exam = exam;
        this.teacher = teacher;
        this.student = student;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getMarkID() {
        return markID;
    }

    public void setMarkID(int markID) {
        this.markID = markID;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
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
}
