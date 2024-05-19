package com.example.mathexercise_hagaididi;

public class student {
    private String Email;
    private String name;
    private String status;

    public student(String email, String name ,String age,String status) {
        Email = email;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
