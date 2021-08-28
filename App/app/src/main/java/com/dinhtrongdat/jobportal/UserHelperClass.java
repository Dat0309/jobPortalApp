package com.dinhtrongdat.jobportal;

public class UserHelperClass {
    private String user, pass, phone;

    public UserHelperClass() {

    }

    public UserHelperClass(String user, String pass, String phone) {
        this.user = user;
        this.pass = pass;
        this.phone = phone;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
