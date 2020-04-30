package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ChinaCollection {//This class is design for as a json object that interact with HTML, it will contain a list with one or more specific province's data: current case, total case, dead case suspicious and cured case.
    private List<List<String>> list = new ArrayList<List<String>>();//create an arraylist that its element are also array list

    public void addlist(List<String> a) {
        list.add(a);
    }

    public void setList(List<List<String>> list) {
        this.list = list;
    }//default;t set method for a json object

    public List<List<String>> getList() {
        return list;
    }//default;t get method for a json object
}
