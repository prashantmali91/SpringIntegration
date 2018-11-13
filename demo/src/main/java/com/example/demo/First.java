package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class First {

	@Autowired
	private TaskExecutor taskExecutor;
	
	public void m1(){
		taskExecutor.execute(()-> {
		 for(int i =0 ;i <5; i++){
			System.out.println("Run of " + Thread.currentThread().getName()+", i=" +i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 }
			
		});
	}
}
