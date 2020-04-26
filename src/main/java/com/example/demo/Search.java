package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class Search {
    @PostMapping(value="/search_batch")
    @ResponseBody

    public PlaceList saveStu(@RequestBody String place1){
        PlaceList p1=new PlaceList();
        return p1;
    }
}
