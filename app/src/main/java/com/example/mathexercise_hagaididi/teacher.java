package com.example.mathexercise_hagaididi;

import android.widget.Button;
import android.widget.EditText;

public class teacher {
    private String phoneNumber;
    private int price;
    private String location;
    private String name;
    private String Email;

    public teacher(String phoneNumber, int price, String location, String name,String Email) {
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.location = location;
        this.name = name;
        this.Email = Email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
