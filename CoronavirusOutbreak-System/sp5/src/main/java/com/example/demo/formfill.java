package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class formfill extends QuaryMaker {

    @PostMapping("/form")//map the specific request to this class
    @ResponseBody//Set a response function below:

    public ChinaCollection saveStu(@RequestBody String place) {//this function serves for fill the form with china's data in HTML

        ChinaCollection cc1 = new ChinaCollection();//Create a arraylist that will keep data for every province
        chinaarea c1 = new chinaarea();
        for (String key : c1.chinastring.keySet()) {

            List<String> l1 = new ArrayList<String>();//Create a list for every province for: current, total, cured, dead, suspicious cases
            l1.add(c1.chinastring.get(key).replace("\"", ""));
            l1.add(query("china", "provincename", "currentc", c1.chinastring.get(key)));//query and put data in an array
            l1.add(query("china", "provincename", "confirmedc", c1.chinastring.get(key)));
            l1.add(query("china", "provincename", "deadc", c1.chinastring.get(key)));
            l1.add(query("china", "provincename", "suspectedc", c1.chinastring.get(key)));
            l1.add(query("china", "provincename", "curedc", c1.chinastring.get(key)));
            cc1.addlist(l1);//Put each province's list to another list cc1
        }
        return cc1;//return to HTML request
    }
}
