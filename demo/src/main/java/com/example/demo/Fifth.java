package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class Fifth {


	@Async
	public Future<Integer> m1(){
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
		 return  new AsyncResult<Integer>(10);
	}
	
	@Async
	public List<String> m2(){
		System.out.println("Starting m2.........");
    	 List<String> list = new ArrayList<>();
		 for(int i =0 ;i<10; i++){
			 list.add("name"+i);
			System.out.println("m2-run of "+ Thread.currentThread().getName()+ ", i=" +i); 
         	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         
          }
		 return  (List<String>) new AsyncResult<List<String>>(list);
	}

}
