package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class formfill {
    @Autowired
    JdbcTemplate jdbcTemp;
    @PostMapping("/form")
    @ResponseBody

    public ChinaCollection saveStu(@RequestBody String place)
    {
        String sql = "SELECT currentc FROM china WHERE provincename=?";
        String sql1 = "SELECT confirmedc FROM china WHERE provincename=?";
        String sql2 = "SELECT suspectedc FROM china WHERE provincename=?";
        String sql3 = "SELECT curedc FROM china WHERE provincename=?";
        String sql4 = "SELECT deadc FROM china WHERE provincename=?";
        ChinaCollection cc1=new ChinaCollection();
        chinaarea c1 = new chinaarea();
        for (String key:c1.chinastring.keySet()){
           // map.addAttribute( key,(String)jdbcTemplate.queryForObject(
                 //   sql, new Object[] { c1.chinastring.get(key) }, String.class));
           // System.out.println(c1.chinastring.get(key));
            //System.out.println("haha");
           // QueryManager q1=new QueryManager("china",c1.chinastring.get(key));
            List<String> l1=new ArrayList<String>();
            l1.add(c1.chinastring.get(key).replace("\"", ""));
            l1.add((String)jdbcTemp.queryForObject(
                    sql, new Object[] {c1.chinastring.get(key)}, String.class));
            l1.add((String)jdbcTemp.queryForObject(
                    sql1, new Object[] {c1.chinastring.get(key)}, String.class));
            l1.add((String)jdbcTemp.queryForObject(
                    sql4, new Object[] {c1.chinastring.get(key)}, String.class));
            l1.add((String)jdbcTemp.queryForObject(
                    sql2, new Object[] {c1.chinastring.get(key)}, String.class));
            l1.add((String)jdbcTemp.queryForObject(
                    sql3, new Object[] {c1.chinastring.get(key)}, String.class));
            cc1.addlist(l1);
        }
       // System.out.println(cc1);
        //System.out.println(student.toString());
        //System.out.println(student);

       // System.out.println(1);
       // arraytest a=new arraytest();
        return cc1;
    }

}
