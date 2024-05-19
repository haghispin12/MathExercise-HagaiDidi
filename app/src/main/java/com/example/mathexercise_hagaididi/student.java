package com.example.mathexercise_hagaididi;

public class student {
    private String Email;
    private String name;
    private String age;

    public student(String email, String name ,String age) {
        Email = email;
        this.name = name;
        this.age = age;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
