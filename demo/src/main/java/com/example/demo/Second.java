package com.example.demo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@EnableScheduling
//@Component
public class Second {

	WriteFile wf =null;
	
	@Autowired
	Third third;
	
	@PostConstruct
	public void init(){
		wf = third.m1();
	}
	@Scheduled(initialDelay=0, fixedRate=1000)
	public void method1(){
		System.out.println("method1 invoked");
	}
	
	@Scheduled(cron = "0/1 * * * * * ")
	public void method2(){
		System.out.println("method2 invoked");
	}
	
	@Scheduled(initialDelay=0, fixedRate=1000)
	public void method3(){
		System.out.println("method3 invoked");
		wf.getStatus();
	}
	
}
