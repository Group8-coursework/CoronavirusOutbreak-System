package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class WorldMapClick extends QuaryMaker {
    countryName cn1 = new countryName();
    @Autowired
    JdbcTemplate jdbcTemp;

    @PostMapping("/map")//map the specific request to this class
    @ResponseBody//Set a response function below:
    public Corona Map(@RequestBody String location) {//this function serves for click event on worldmap.

        Corona co1 = new Corona();//Create a json object

        co1.setplace(cn1.globalname.get(location.replace("\"", "")));//put the place name in json object
        co1.setcurrentc(Integer.parseInt(query("world", "name", "currentc", cn1.globalname.get(location.replace("\"", "")))));//put the current case in json object
        co1.settotal(Integer.parseInt(query("world", "name", "confirmedc", cn1.globalname.get(location.replace("\"", "")))));//put the total case in json object
        co1.setdied(Integer.parseInt(query("world", "name", "deadc", cn1.globalname.get(location.replace("\"", "")))));//put the dead case in json object
        co1.setrecoverc(Integer.parseInt(query("world", "name", "curedc", cn1.globalname.get(location.replace("\"", "")))));//put the cured case in json object
        co1.setsus(0);//There is no suspicious case data for every country in database

        return co1;//return a json object to HTML request
    }

}
