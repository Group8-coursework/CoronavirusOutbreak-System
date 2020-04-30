package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller

public class FourCountryData extends QuaryMaker {// This class initialize data for USA UK France and Italy at the bottom of our web page

    @PostMapping(value = "/queryn")//map the specific request to this class
    @ResponseBody//Set a response function below:

    public WorldCollection saveStu(@RequestBody String place1) {
        WorldCollection w1 = new WorldCollection();//Create a arraylist to keep four countries' data
        worldarea wa = new worldarea();//Create a map with chinese name-english name pair

        List<String> l1 = new ArrayList<String>();//initialize a list for each country
        List<String> l2 = new ArrayList<String>();
        List<String> l3 = new ArrayList<String>();
        List<String> l4 = new ArrayList<String>();
        l2.add(wa.world.get("USA").replace("\"", ""));//国家名
        l2.add(query("world", "name", "currentc", wa.world.get("USA")));//现存
        l2.add(query("world", "name", "confirmedc", wa.world.get("USA")));//累计
        l2.add(query("world", "name", "deadc", wa.world.get("USA")));//死亡
        l2.add(query("world", "name", "curedc", wa.world.get("USA")));//治愈

        l3.add(wa.world.get("UK").replace("\"", ""));//国家名
        l3.add(query("world", "name", "currentc", wa.world.get("UK")));//现存
        l3.add(query("world", "name", "confirmedc", wa.world.get("UK")));//累计
        l3.add(query("world", "name", "deadc", wa.world.get("UK")));//死亡
        l3.add(query("world", "name", "curedc", wa.world.get("UK")));//治愈

        l4.add(wa.world.get("Italy").replace("\"", ""));//国家名
        l4.add(query("world", "name", "currentc", wa.world.get("Italy")));//现存
        l4.add(query("world", "name", "confirmedc", wa.world.get("Italy")));//累计
        l4.add(query("world", "name", "deadc", wa.world.get("Italy")));//死亡
        l4.add(query("world", "name", "curedc", wa.world.get("Italy")));//治愈

        l1.add(wa.world.get("France").replace("\"", ""));//国家名
        l1.add(query("world", "name", "currentc", wa.world.get("France")));//现存
        l1.add(query("world", "name", "confirmedc", wa.world.get("France")));//累计
        l1.add(query("world", "name", "deadc", wa.world.get("France")));//死亡
        l1.add(query("world", "name", "curedc", wa.world.get("France")));//治愈


        w1.addlist(l1);//Add each country's data to the list
        w1.addlist(l2);
        w1.addlist(l3);
        w1.addlist(l4);
        return w1;//return the list to HTML
    }
}
