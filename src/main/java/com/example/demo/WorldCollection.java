package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class WorldCollection {
    private List<List<String>> wwlist=new ArrayList<List<String>>();
    public void addlist(List<String> a){
        wwlist.add(a);
    }
    public void setList(List<List<String>> list) {
        this.wwlist = list;
    }
    public List<List<String>> getList() {
        return wwlist;
    }
}
