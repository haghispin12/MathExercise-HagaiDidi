package com.example.mathexercise_hagaididi;

import java.lang.reflect.Type;

public class Users {
    private String Email;
    private String Type;

    public Users(String email, String type) {
        Email = email;
        Type = type;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
