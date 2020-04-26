package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.*;


@Controller
public class AccessHTMLController {
    worldarea w1= new worldarea();
    chinaarea c1= new chinaarea();
    String sql = "SELECT currentc FROM china WHERE provincename=?";
    String sqltotalchina = "SELECT confirmedc FROM china WHERE provincename=?";
    String sqlsuschina = "SELECT suspectedc FROM china WHERE provincename=?";
    String sqlcuredchina = "SELECT curedc FROM china WHERE provincename=?";
    String sqldeadchina = "SELECT deadc FROM china WHERE provincename=?";



    String sqlworld="SELECT currentc FROM world WHERE name=?";
    String sqlworldtotal="SELECT confirmedc FROM world WHERE name=?";
    String sqlworldcured="SELECT curedc FROM world WHERE name=?";
    String sqlworlddead="SELECT deadc FROM world WHERE name=?";


    String china1="SELECT sumcurr FROM sumc WHERE day=?";
    String world1="SELECT sumcurr FROM sumw WHERE day=?";



    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/")

    public String index(ModelMap map) throws UnsupportedEncodingException {

        map.addAttribute("cday1",(String)jdbcTemplate.queryForObject(
                china1, new Object[] {1}, String.class));
        map.addAttribute("cday2",(String)jdbcTemplate.queryForObject(
                china1, new Object[] {2}, String.class));
        map.addAttribute("cday3",(String)jdbcTemplate.queryForObject(
                china1, new Object[] {3}, String.class));
        map.addAttribute("cday4",(String)jdbcTemplate.queryForObject(
                china1, new Object[] {4}, String.class));
        map.addAttribute("cday5",(String)jdbcTemplate.queryForObject(
                china1, new Object[] {5}, String.class));
        map.addAttribute("cday6",(String)jdbcTemplate.queryForObject(
                china1, new Object[] {6}, String.class));
        map.addAttribute("cday7",(String)jdbcTemplate.queryForObject(
                china1, new Object[] {7}, String.class));

        map.addAttribute("wday1",(String)jdbcTemplate.queryForObject(
                world1, new Object[] {1}, String.class));
        map.addAttribute("wday2",(String)jdbcTemplate.queryForObject(
                world1, new Object[] {2}, String.class));
        map.addAttribute("wday3",(String)jdbcTemplate.queryForObject(
                world1, new Object[] {3}, String.class));
        map.addAttribute("wday4",(String)jdbcTemplate.queryForObject(
                world1, new Object[] {4}, String.class));
        map.addAttribute("wday5",(String)jdbcTemplate.queryForObject(
                world1, new Object[] {5}, String.class));
        map.addAttribute("wday6",(String)jdbcTemplate.queryForObject(
                world1, new Object[] {6}, String.class));
        map.addAttribute("wday7",(String)jdbcTemplate.queryForObject(
                world1, new Object[] {7}, String.class));





        for (String key:c1.chinastring.keySet()){
            map.addAttribute( key,(String)jdbcTemplate.queryForObject(
                    sql, new Object[] { c1.chinastring.get(key) }, String.class));

        }



        try {
        List<Integer> list= new ArrayList();
       for (String key:w1.world.keySet()){
           map.addAttribute( key,(String)jdbcTemplate.queryForObject(
                   sqlworld, new Object[] { w1.world.get(key) }, String.class));
           list.add(Integer.parseInt((String)jdbcTemplate.queryForObject(
                    sqlworldtotal, new Object[] { w1.world.get(key) }, String.class)));
       }
        Integer[] nsz=new Integer[list.size()];
        list.toArray(nsz);
        TotalCac t1=new TotalCac();
        String total="Total case: "+t1.cacu(nsz);
        map.addAttribute("totalcase",total);



    }catch (Exception e){}
       // System.out.println("\""+"121"+"\"");
        return"/a.htm";
        }

    @PostMapping("/savestu")
    @ResponseBody
    public Corona saveStu(@RequestBody String place)
    {
        Corona temp = new Corona();


        String current;
        String total;
        String susp;
        String cured;
        String died;

        place="\""+place+"\"";
        if(c1.chinastring.values().contains(place)){
            current=(String)jdbcTemplate.queryForObject(
                    sql, new Object[] { place }, String.class);
            temp.setcurrentc(Integer.parseInt(current));
            total=(String)jdbcTemplate.queryForObject(
                    sqltotalchina, new Object[] { place }, String.class);
            temp.settotal(Integer.parseInt(total));
            susp=(String)jdbcTemplate.queryForObject(
                    sqlsuschina, new Object[] { place }, String.class);
            temp.setsus(Integer.parseInt(susp));
            cured=(String)jdbcTemplate.queryForObject(
                    sqlcuredchina, new Object[] { place }, String.class);
            temp.setrecoverc(Integer.parseInt(cured));
            died=(String)jdbcTemplate.queryForObject(
                    sqldeadchina, new Object[] { place }, String.class);
            temp.setdied(Integer.parseInt(died));
            temp.setLocal(0);
            return temp;
        }
        else if(w1.world.values().contains(place)){
            current=(String)jdbcTemplate.queryForObject(
                    sqlworld, new Object[] { place }, String.class);
            temp.setcurrentc(Integer.parseInt(current));

            total=(String)jdbcTemplate.queryForObject(
                    sqlworldtotal, new Object[] { place }, String.class);
            temp.settotal(Integer.parseInt(total));

            cured=(String)jdbcTemplate.queryForObject(
                    sqlworldcured, new Object[] { place }, String.class);
            temp.setrecoverc(Integer.parseInt(cured));

            died=(String)jdbcTemplate.queryForObject(
                    sqlworlddead, new Object[] { place }, String.class);
            temp.setdied(Integer.parseInt(died));
            temp.setsus(0);
            temp.setLocal(1);
            return temp;
        }
        else{

        }
        System.out.println(1);

        return temp;
    }

    }


