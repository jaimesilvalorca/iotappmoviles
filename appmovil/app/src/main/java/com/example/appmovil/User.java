package com.example.appmovil;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private String rut;
    private String name;
    private String lastname;
    private String birthday;

    private int age;



    public User(){}

    public User(String rut,String name, String lastname,String birthday,int age){
        this.rut = rut;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.age = age;
    }

    public User(String rut, String name, String lastname, String birthday) {
        this.rut = rut;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;

    }

    public String getRut() {return rut;}

    public void setRut(String rut) {this.rut = rut;}
    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getLastName() {return lastname;}

    public void setLastName(String lastname) {this.lastname = lastname;}


    public String getDate() {return birthday;}

    public void setDate(String birthday) {this.birthday= birthday;}
    public int getAge() {return age;}

    public void setAge(int age) {this.age= age;}


}
