package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class Search {
    @PostMapping(value="/search_batch")//map the specific request to this class
    @ResponseBody//Set a response function below:
    public PlaceList saveStu(@RequestBody String place1){
        PlaceList p1=new PlaceList();//Create an optional list of province and country names for search
        return p1;//return the list
    }
}
