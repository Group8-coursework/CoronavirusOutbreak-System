package com.example.demo;

public class WebServerStart {

    public static void main(String[] args) {

        Thread Webpage = new Thread();//Create a thread for springboot
        Thread cra = new Thread();//create a thread for web Crawler
        Webpage = new DemoApplication();
        cra = new Quarttest();
        Webpage.run();//Start springboot
        cra.run();//Start web crawler
    }
}
