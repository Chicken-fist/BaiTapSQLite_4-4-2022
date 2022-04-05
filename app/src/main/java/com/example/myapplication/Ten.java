package com.example.myapplication;

import java.io.Serializable;

public class Ten implements Serializable {

    int _id;
    String _name;
    public Ten(){   }
    public Ten(int id, String name){
        this._id = id;
        this._name = name;
    }

    public Ten(String name){
        this._name = name;
    }
    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }

}
