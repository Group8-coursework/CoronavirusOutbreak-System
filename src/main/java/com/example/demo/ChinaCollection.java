package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ChinaCollection {
    private List<List<String>> list=new ArrayList<List<String>>();
    public void addlist(List<String> a){
        list.add(a);
    }
    public void setList(List<List<String>> list) {
        this.list = list;
    }

    public List<List<String>> getList() {
        return list;
    }
}
