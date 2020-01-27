package com.example.application1.model;

import android.widget.SpinnerAdapter;

import java.io.Serializable;

public class User implements Serializable {
    int user_id;
    String first_name;
    String last_name;
    String gender;
    String email_id;
    String password;
    int mobile_no;
    String city;

    public User() {
    }

    public User(String first_name, String last_name, String gender, String email_id, String password, int mobile_no, String city) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.email_id = email_id;
        this.password = password;
        this.mobile_no = mobile_no;
        this.city = city;
    }

    public User(int user_id, String first_name, String last_name, String gender, String email_id, String password, int mobile_no, String city) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.email_id = email_id;
        this.password = password;
        this.mobile_no = mobile_no;
        this.city = city;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(int mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getCity() {
        //r city = this.city;
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
