package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class placeQuery {
    countryName cn1=new countryName();
    @Autowired
    JdbcTemplate jdbcTemp;
    @PostMapping("/map")
    @ResponseBody
    public Corona Map(@RequestBody String location){

        String sql = "SELECT currentc FROM world WHERE name=?";
        String sql1 = "SELECT confirmedc FROM world WHERE name=?";
        String sql3 = "SELECT curedc FROM world WHERE name=?";
        String sql4 = "SELECT deadc FROM world WHERE name=?";

        Corona co1=new Corona();

            co1.setplace(cn1.globalname.get(location.replace("\"", "")));
            co1.setcurrentc(Integer.parseInt((String)jdbcTemp.queryForObject(
                    sql, new Object[] {cn1.globalname.get(location.replace("\"", ""))}, String.class)));
            co1.settotal(Integer.parseInt((String)jdbcTemp.queryForObject(
                    sql1, new Object[] {cn1.globalname.get(location.replace("\"", ""))}, String.class)));
            co1.setdied(Integer.parseInt((String)jdbcTemp.queryForObject(
                    sql4, new Object[] {cn1.globalname.get(location.replace("\"", ""))}, String.class)));
            co1.setrecoverc(Integer.parseInt((String)jdbcTemp.queryForObject(
                    sql3, new Object[] {cn1.globalname.get(location.replace("\"", ""))}, String.class)));
            co1.setsus(0);

        return co1;
    }

}
