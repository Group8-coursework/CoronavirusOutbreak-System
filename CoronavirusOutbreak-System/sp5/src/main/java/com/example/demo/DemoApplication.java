package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void  main(String[] args){
		Thread cra = new Thread();//create a thread for web Crawler
		//Webpage = new DemoApplication();
		cra = new Quarttest();
		//Webpage.run();//Start springboot
		cra.run();//Start web crawler
		SpringApplication.run(DemoApplication.class,args);//Start up a Springboot application
	}

}
