package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication extends Thread{//Will be multi thread

	public void run(){
		SpringApplication.run(DemoApplication.class);//Start up a Springboot application
	}

}
