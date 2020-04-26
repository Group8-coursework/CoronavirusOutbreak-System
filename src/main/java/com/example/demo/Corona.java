package com.example.demo;

import lombok.Data;

@Data
public class Corona {

    private String place;
    private Integer currentc;
    private Integer recoverc;
    private Integer total;
    private Integer died;
    private Integer sus;
    private Integer local;

    public void setplace(String a){
        place=a;
    };
    public void setcurrentc(int c){
        currentc = c;
    };
    public void setrecoverc(int d){
        recoverc = d;
    };
    public void settotal(int d){
        total = d;
    };
    public void setdied(int d){
        died = d;
    };
    public void setsus(int d){
        sus = d;
    };
    public void setLocal(int d) {local=d;
    }
    public String getplace(){return place;}
    public int getcurrent(){return currentc;}
    public int getcured(){return recoverc;}
    public int gettotal(){return total;}
    public int getdead(){return died;}
    public int getsus(){return sus;}

}
