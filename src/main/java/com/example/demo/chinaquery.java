package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class chinaquery {
    String sql = "SELECT currentc FROM china WHERE provincename=?";
    String sql1 = "SELECT confirmedc FROM china WHERE provincename=?";
    String sql3 = "SELECT curedc FROM china WHERE provincename=?";
    String sql4 = "SELECT deadc FROM china WHERE provincename=?";
    String sql2 = "SELECT suspectedc FROM china WHERE provincename=?";

    @Autowired
    JdbcTemplate jdbcTemp;
    @PostMapping("/chinamap")
    @ResponseBody

    public Corona ChinaMap(@RequestBody String location){
        System.out.println(location);
        Corona chcor=new Corona();
        provinceName pn1=new provinceName();
        chcor.setplace(pn1.proname.get(location.replace("\"", "")));
        chcor.setcurrentc(Integer.parseInt((String)jdbcTemp.queryForObject(
                sql, new Object[] {pn1.proname.get(location.replace("\"", ""))}, String.class)));
        chcor.settotal(Integer.parseInt((String)jdbcTemp.queryForObject(
                sql1, new Object[] {pn1.proname.get(location.replace("\"", ""))}, String.class)));
        chcor.setdied(Integer.parseInt((String)jdbcTemp.queryForObject(
                sql4, new Object[] {pn1.proname.get(location.replace("\"", ""))}, String.class)));
        chcor.setrecoverc(Integer.parseInt((String)jdbcTemp.queryForObject(
                sql3, new Object[] {pn1.proname.get(location.replace("\"", ""))}, String.class)));
        chcor.setsus(Integer.parseInt((String)jdbcTemp.queryForObject(
                sql2, new Object[] {pn1.proname.get(location.replace("\"", ""))}, String.class)));
      return chcor;
    }
}
