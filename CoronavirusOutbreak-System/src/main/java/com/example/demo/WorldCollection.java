package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class WorldCollection {//This class is design for as a json object that interact with HTML, it will contain a list with one or more specific country's data: current case, total case, dead case and cured case.
    private List<List<String>> wwlist=new ArrayList<List<String>>();//create an arraylist that its element are also array list
    public void addlist(List<String> a){
        wwlist.add(a);
    }//function for add data to the list
    public void setList(List<List<String>> list) {
        this.wwlist = list;
    }//default;t set method for a json object
    public List<List<String>> getList() {
        return wwlist;
    }//default;t get method for a json object
}
