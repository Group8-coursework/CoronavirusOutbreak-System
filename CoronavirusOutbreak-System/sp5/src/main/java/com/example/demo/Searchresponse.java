package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class Searchresponse extends QuaryMaker{
    worldarea w1 = new worldarea();
    chinaarea c1 = new chinaarea();
    @PostMapping("/savestu")//map the specific request to this class
    @ResponseBody//Set a response function below:
    public Corona saveStu(@RequestBody String place) {//this function serves for handle search button click
        Corona temp = new Corona();//create a json object which will contain the search result

        String current;
        String total;
        String susp;
        String cured;
        String died;

        place = "\"" + place + "\"";
        if (c1.chinastring.values().contains(place)) {//If search for province

            current = query("china", "provincename", "currentc", place);//Make a query for current case
            temp.setcurrentc(Integer.parseInt(current));//put data in json object
            total = query("china", "provincename", "confirmedc", place);//Make a query for total case
            temp.settotal(Integer.parseInt(total));//put data in json object
            susp = query("china", "provincename", "suspectedc", place);//Make a query for suspicious case
            temp.setsus(Integer.parseInt(susp));//put data in json object
            cured = query("china", "provincename", "curedc", place);//Make a query for cured case
            temp.setrecoverc(Integer.parseInt(cured));//put data in json object
            died = query("china", "provincename", "deadc", place);//Make a query for dead case
            temp.setdied(Integer.parseInt(died));//put data in json object
            temp.setLocal(0);//represent it's a province
            return temp;
        } else if (w1.world.values().contains(place)) {//If search for a country

            current = query("world", "name", "currentc", place);//Make a query for current case
            temp.setcurrentc(Integer.parseInt(current));//put data in json object
            total = query("world", "name", "confirmedc", place);//Make a query for total case
            temp.settotal(Integer.parseInt(total));//put data in json object
            cured = query("world", "name", "curedc", place);//Make a query for cured case
            temp.setrecoverc(Integer.parseInt(cured));//put data in json object
            died = query("world", "name", "deadc", place);//Make a query for dead case
            temp.setdied(Integer.parseInt(died));//put data in json object
            temp.setsus(0);//there is no suspicious for every country in database
            temp.setLocal(1);//represent it's a country
            return temp;
        } else {

        }

        return temp;
    }
}
