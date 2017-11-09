package com.example.dqtech.bloodbank;

/**
 * Created by DQTECH on 10/26/2017.
 */

public class Users {
    private String userid;
    private String name;
    private String gender;
    private String email;
    private String contact;
    private String address;
    private String city;
    private String username;
    private String bgroup;

    public Users(){
        //this constructor is required
    }
    public Users(String userid,String name,String email,String contact,
                 String address,String city,String bgroup,String gender){
        this.userid=userid;
        this.name=name;
        this.gender=gender;
        this.email=email;
        this.contact=contact;
        this.address=address;
        this.city=city;
       // this.username=username;
        this.bgroup=bgroup;
    }

    public String getUserid() {
        return userid;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBgroup(String bgroup) {
        this.bgroup = bgroup;
    }

    public String getUsername() {
        return username;
    }

    public String getBgroup() {
        return bgroup;
    }
}
