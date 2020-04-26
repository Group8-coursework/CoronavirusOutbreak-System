package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller

public class normalquery {
    @Autowired
    JdbcTemplate jdbcTemp;
    @PostMapping(value="/queryn")
    @ResponseBody

    public WorldCollection saveStu(@RequestBody String place1){
        WorldCollection w1=new WorldCollection();
        worldarea wa=new worldarea();

        String sql = "SELECT currentc FROM world WHERE name=?";
        String sql1 = "SELECT confirmedc FROM world WHERE name=?";
       // String sql2 = "SELECT suspectedc FROM world WHERE provincename=?";
        String sql3 = "SELECT curedc FROM world WHERE name=?";
        String sql4 = "SELECT deadc FROM world WHERE name=?";
        List<String> l1=new ArrayList<String>();
        List<String> l2=new ArrayList<String>();
        List<String> l3=new ArrayList<String>();
        List<String> l4=new ArrayList<String>();
        l2.add(wa.world.get("USA").replace("\"", ""));//国家名
        l2.add((String)jdbcTemp.queryForObject(
                sql, new Object[] {wa.world.get("USA")}, String.class));//现存
        l2.add((String)jdbcTemp.queryForObject(
                sql1, new Object[] {wa.world.get("USA")}, String.class));//累计
        l2.add((String)jdbcTemp.queryForObject(
                sql4, new Object[] {wa.world.get("USA")}, String.class));//死亡
        l2.add((String)jdbcTemp.queryForObject(
                sql3, new Object[] {wa.world.get("USA")}, String.class));//治愈

        l3.add(wa.world.get("UK").replace("\"", ""));//国家名
        l3.add((String)jdbcTemp.queryForObject(
                sql, new Object[] {wa.world.get("UK")}, String.class));//现存
        l3.add((String)jdbcTemp.queryForObject(
                sql1, new Object[] {wa.world.get("UK")}, String.class));//累计
        l3.add((String)jdbcTemp.queryForObject(
                sql4, new Object[] {wa.world.get("UK")}, String.class));//死亡
        l3.add((String)jdbcTemp.queryForObject(
                sql3, new Object[] {wa.world.get("UK")}, String.class));//治愈

        l4.add(wa.world.get("Italy").replace("\"", ""));//国家名
        l4.add((String)jdbcTemp.queryForObject(
                sql, new Object[] {wa.world.get("Italy")}, String.class));//现存
        l4.add((String)jdbcTemp.queryForObject(
                sql1, new Object[] {wa.world.get("Italy")}, String.class));//累计
        l4.add((String)jdbcTemp.queryForObject(
                sql4, new Object[] {wa.world.get("Italy")}, String.class));//死亡
        l4.add((String)jdbcTemp.queryForObject(
                sql3, new Object[] {wa.world.get("Italy")}, String.class));//治愈

        l1.add(wa.world.get("France").replace("\"", ""));//国家名
        l1.add((String)jdbcTemp.queryForObject(
                sql, new Object[] {wa.world.get("France")}, String.class));//现存
        l1.add((String)jdbcTemp.queryForObject(
                sql1, new Object[] {wa.world.get("France")}, String.class));//累计
        l1.add((String)jdbcTemp.queryForObject(
                sql4, new Object[] {wa.world.get("France")}, String.class));//死亡
        l1.add((String)jdbcTemp.queryForObject(
                sql3, new Object[] {wa.world.get("France")}, String.class));//治愈
        w1.addlist(l1);
        w1.addlist(l2);
        w1.addlist(l3);
        w1.addlist(l4);
        return w1;
    }
}
