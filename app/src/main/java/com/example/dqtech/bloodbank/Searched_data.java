package com.example.dqtech.bloodbank;

/**
 * Created by DQTECH on 12/1/2017.
 */

public class Searched_data {

    String uname;
    String gen;
    String number;
    String grp;

    public Searched_data(String uname,String gen,String number,String grp){
        this.uname=uname;
        this.gen=gen;
        this.number=number;
        this.grp=grp;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGrp() {
        return grp;
    }

    public void setGrp(String grp) {
        this.grp = grp;
    }
}
