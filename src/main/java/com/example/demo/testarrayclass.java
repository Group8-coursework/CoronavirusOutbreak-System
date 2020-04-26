package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class testarrayclass {
    private List<Map<String,String>> list=new ArrayList<Map<String,String>>();

    testarrayclass(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("4", "value4");
        map1.put("5", "value5");
        map1.put("6", "value6");
     list.add(map);
     list.add(map1);
     }
}
