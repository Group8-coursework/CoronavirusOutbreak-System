package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class QuaryMaker {//This class execute a basic MYSQL query

    @Autowired
    JdbcTemplate jdbcTemplate;
    public String query(String table,String id,String select,String attribute){
        String sql="SELECT "+select+" FROM "+table+" WHERE "+id+"=?";//define query sentence
        String s=(String)jdbcTemplate.queryForObject( sql, new Object[] {attribute}, String.class); //make a query
        return s;//return the result
    }
}
