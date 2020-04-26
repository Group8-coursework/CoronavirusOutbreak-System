package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class QueryManager {
    private String table;
    private String want;
    private String id;
    private Corona c;
    @Autowired
    JdbcTemplate jdbcTemplate;
    public QueryManager(String a,String b){
        table=a;
        want=b;
        c=new Corona();

    }
    public Corona query(){
        String sql = "SELECT currentc FROM "+table+" WHERE provincename=?";
        String sql1 = "SELECT confirmedc FROM "+table+" WHERE provincename=?";
        String sql2 = "SELECT suspectedc FROM "+table+" WHERE provincename=?";
        String sql3 = "SELECT curedc FROM "+table+" WHERE provincename=?";
        String sql4 = "SELECT deadc FROM "+table+" WHERE provincename=?";
        System.out.println("HHHHHHHHHHHHHHHHHHsql1 "+sql);
       String sqlres = (String)jdbcTemplate.queryForObject(
                sql, new Object[] { want }, String.class);

       System.out.println("This is result "+sqlres);


       String sql1res= (String)jdbcTemplate.queryForObject(
                sql1, new Object[] {  want }, String.class);
       String sql2res= (String)jdbcTemplate.queryForObject(
                sql2, new Object[] {  want }, String.class);
       String sql3res= (String)jdbcTemplate.queryForObject(
                sql3, new Object[] {  want }, String.class);
        String sql4res=(String)jdbcTemplate.queryForObject(
                sql4, new Object[] { want }, String.class);
        c.setrecoverc(Integer.valueOf(sql3res));
        c.setsus(Integer.valueOf(sql2res));
        c.setdied(Integer.valueOf(sql4res));
        c.setcurrentc(Integer.valueOf(sqlres));
        c.settotal(Integer.valueOf(sql1res));
        c.setplace(id);
        return c;
    }
}
