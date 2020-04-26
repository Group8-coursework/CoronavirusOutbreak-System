package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class PlaceList {
    private List<String> place=new ArrayList<String>();
    public PlaceList(){
        chinaarea a1=new chinaarea();
        worldarea w1=new worldarea();
        for (String key:a1.chinastring.keySet()){
           place.add( a1.chinastring.get(key).replace("\"", ""));
        }
        for (String key:w1.world.keySet()){
            place.add( w1.world.get(key).replace("\"", ""));
        }

    }

    public void setList(List<String> list) {
        this.place = list;
    }

    public List<String> getPlace() {
        return place;
    }
}
