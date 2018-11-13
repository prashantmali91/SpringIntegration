package com.example.demo;

import java.util.Date;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class Fourth {

	@Async
	public void m1(){
		System.out.println("Starting m1.........");
		 for(int i =0 ;i<10; i++){
			System.out.println("m1-run of "+ Thread.currentThread().getName()+ ", i=" +i); 
         	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         
          }
	}
	
	
	public void m2(){
		System.out.println("Starting m2.........");
		 for(int i =0 ;i<10; i++){
			System.out.println("m2-run of "+ Thread.currentThread().getName()+ ", i=" +i); 
         	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         
          }
	}
}
