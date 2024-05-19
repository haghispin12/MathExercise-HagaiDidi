package com.example.mathexercise_hagaididi;

public class connection {
    private String EmailTeacher;
    private String EmailStudent;
    private String status;

    public connection(String emailTeacher, String emailStudent, String status) {
        EmailTeacher = emailTeacher;
        EmailStudent = emailStudent;
        this.status = status;
    }

    public String getEmailTeacher() {
        return EmailTeacher;
    }

    public void setEmailTeacher(String emailTeacher) {
        EmailTeacher = emailTeacher;
    }

    public String getEmailStudent() {
        return EmailStudent;
    }

    public void setEmailStudent(String emailStudent) {
        EmailStudent = emailStudent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
