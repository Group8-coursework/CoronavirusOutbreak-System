package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.*;


@Controller
public class AccessHTMLController extends QuaryMaker {
    worldarea w1 = new worldarea(); //Create a map which keeps Chinese and English name pairs
    chinaarea c1 = new chinaarea(); //Create a map which keeps Chinese and English name pairs

    @RequestMapping("/") //Map next function to http://localhost:8099/
    public String index(ModelMap map) throws UnsupportedEncodingException {//This function initializes basic data for HTML file that user's browser requests


        map.addAttribute("worldsit", "Global Situation- Current: " + //initialize data for HTML element: Global Situation
                query("sumw", "day", "sumcurr", "7")
                + " Total: " +
                query("sumw", "day", "sumconfir", "7")
                + " Cured: " +
                query("sumw", "day", "sumdead", "7")
                + " Death: " +
                query("sumw", "day", "sumdead", "7"));


        map.addAttribute("cday1", query("sumc", "day", "sumcurr", "1"));//initialize data for line chart in HTML(Query for current case in china 7 days ago)
        map.addAttribute("cday2", query("sumc", "day", "sumcurr", "2"));//initialize data for line chart in HTML(Query for current case in china 6 days ago)
        map.addAttribute("cday3", query("sumc", "day", "sumcurr", "3"));//initialize data for line chart in HTML(Query for current case in china 5 days ago)
        map.addAttribute("cday4", query("sumc", "day", "sumcurr", "4"));//initialize data for line chart in HTML(Query for current case in china 4 days ago)
        map.addAttribute("cday5", query("sumc", "day", "sumcurr", "5"));//initialize data for line chart in HTML(Query for current case in china 3 days ago)
        map.addAttribute("cday6", query("sumc", "day", "sumcurr", "6"));//initialize data for line chart in HTML(Query for current case in china 2 days ago)
        map.addAttribute("cday7", query("sumc", "day", "sumcurr", "7"));//initialize data for line chart in HTML(Query for current case in china 1 days ago)
        map.addAttribute("wday1", query("sumw", "day", "sumcurr", "1"));//initialize data for line chart in HTML(Query for current case in the world 7 days ago)
        map.addAttribute("wday2", query("sumw", "day", "sumcurr", "2"));//initialize data for line chart in HTML(Query for current case in the world 6 days ago)
        map.addAttribute("wday3", query("sumw", "day", "sumcurr", "3"));//initialize data for line chart in HTML(Query for current case in the world 5 days ago)
        map.addAttribute("wday4", query("sumw", "day", "sumcurr", "4"));//initialize data for line chart in HTML(Query for current case in the world 4 days ago)
        map.addAttribute("wday5", query("sumw", "day", "sumcurr", "5"));//initialize data for line chart in HTML(Query for current case in the world 3 days ago)
        map.addAttribute("wday6", query("sumw", "day", "sumcurr", "6"));//initialize data for line chart in HTML(Query for current case in the world 2 days ago)
        map.addAttribute("wday7", query("sumw", "day", "sumcurr", "7"));//initialize data for line chart in HTML(Query for current case in the world 1 days ago)


        map.addAttribute("chinadead", query("world", "name", "deadc", "\"中国\""));//initialize data for pie chart in HTML
        map.addAttribute("chinacured", query("world", "name", "curedc", "\"中国\""));
        map.addAttribute("chinacurrent", query("world", "name", "currentc", "\"中国\""));
        map.addAttribute("chinatotal", query("world", "name", "confirmedc", "\"中国\""));


        for (String key : c1.chinastring.keySet()) {                              //traverse every province in china and query for its current case number and then put the data on HTML
            map.addAttribute(key, query("china", "provincename", "currentc", c1.chinastring.get(key)));//query for data and map to HTML
        }
        try {
            List<Integer> list = new ArrayList();
            for (String key : w1.world.keySet()) {//traverse every country in the world and query for its current case number and then put the data on HTML

                map.addAttribute(key, query("world", "name", "currentc", w1.world.get(key)));//query for data and map to HTML
                list.add(Integer.parseInt(query("world", "name", "confirmedc", w1.world.get(key))));//put the numbers into an ArrayList
            }
            Integer[] nsz = new Integer[list.size()];
            list.toArray(nsz);
            TotalCac t1 = new TotalCac();//Calculate current case all over the world
            String total = "Total case: " + t1.cacu(nsz);//Set HTML element
            map.addAttribute("totalcase", total);//map the data to HTML element

        } catch (Exception e) {
        }
        return "/a.htm";//Return HTML that has just initialized
    }

}


