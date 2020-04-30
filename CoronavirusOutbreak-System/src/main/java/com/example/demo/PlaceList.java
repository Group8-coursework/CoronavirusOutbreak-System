package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class PlaceList {//This class is for create a optional list for a queried search
    private List<String> place=new ArrayList<String>();//create a new arraylist to keep the list
    public PlaceList(){
        chinaarea a1=new chinaarea();//Create a map that has every province name
        worldarea w1=new worldarea();//Create a map that has every country name
        for (String key:a1.chinastring.keySet()){
           place.add( a1.chinastring.get(key).replace("\"", ""));//put every province in optional list
        }
        for (String key:w1.world.keySet()){
            place.add( w1.world.get(key).replace("\"", ""));//put every country in optional list
        }
    }
    public void setList(List<String> list) {
        this.place = list;
    }//default set function for a json object
    public List<String> getPlace() {
        return place;
    }//default get function for a json object
}
