package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChinaMapClick extends QuaryMaker{

    @PostMapping("/chinamap")//map the specific request to this class
    @ResponseBody//Set a response function below:
    public Corona ChinaMap(@RequestBody String location) {//this function serves for click event on china map.

        Corona chcor = new Corona();//Create a json object
        provinceName pn1 = new provinceName();//Because quired name from HTML is different from the data in the database, so we create a map to keep the mapping relationship
        chcor.setplace(pn1.proname.get(location.replace("\"", "")));//put the place name in json object
        chcor.setcurrentc(Integer.parseInt(query("china","provincename","currentc",pn1.proname.get(location.replace("\"", "")))));//put the current in json object
        chcor.settotal(Integer.parseInt(query("china","provincename","confirmedc",pn1.proname.get(location.replace("\"", "")))));//put the total case in json object
        chcor.setdied(Integer.parseInt(query("china","provincename","deadc",pn1.proname.get(location.replace("\"", "")))));//put the dead case in json object
        chcor.setrecoverc(Integer.parseInt(query("china","provincename","curedc",pn1.proname.get(location.replace("\"", "")))));//put the cured case in json object
        chcor.setsus(Integer.parseInt(query("china","provincename","suspectedc",pn1.proname.get(location.replace("\"", "")))));//put the suspicious case in json object
        return chcor;//return a json object to HTML request
    }
}
