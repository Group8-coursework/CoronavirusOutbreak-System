package com.example.demo;

public class TotalCac {
    public String cacu(Integer[] area){//This function simply calculate sum of a int list
        int total=0;
        for(int i=0;i<area.length;i++) {
            total = total + area[i];
        }
        String s = String.valueOf(total);
        return s;
    }
}
