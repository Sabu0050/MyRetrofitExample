package com.sabututxp.myretrofitexample.activities;

/**
 * Created by s on 9/22/17.
 */

class UserInfo {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String confirmPassword;

    public UserInfo(String name, String email, String phoneNumber, String password, String confirmPassword) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
