package com.example.mathexercise_hagaididi;

public class lesson {
    private String date;
    private String hour;
    private String teacherEmail;
    private String studentEmail;

    public lesson(String date, String hour, String teacherEmail, String studentEmail) {
        this.date = date;
        this.hour = hour;
        this.teacherEmail = teacherEmail;
        this.studentEmail = studentEmail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
}
