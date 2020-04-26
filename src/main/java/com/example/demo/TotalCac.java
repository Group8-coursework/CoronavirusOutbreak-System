package com.example.demo;

public class TotalCac {
    public String cacu(Integer[] area){
        int total=0;
        for(int i=0;i<area.length;i++) {
            total = total + area[i];
        }
        String s = String.valueOf(total);
        return s;
    }
}
