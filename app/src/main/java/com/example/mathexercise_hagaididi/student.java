package com.example.mathexercise_hagaididi;

public class student {
    private String Email;
    private String status;

    public student(String email,String status) {
        Email = email;
        this.status= status;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
}
