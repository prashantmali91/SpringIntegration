package com.example.demo;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number"
				+ "");
		sc.nextInt();
		ApplicationContext context =SpringApplication.run(DemoApplication.class, args);
	    /*First first = context.getBean(First.class);
	   for(int i =0 ;i <10; i++){
	    	first.m1();
	    	System.out.println("started request for" + i);
	    }*/
		
		Fifth fifth = context.getBean(Fifth.class);
		Future<Integer> future = fifth.m1();
		System.out.println("Return from "+future.get());
		
	}
	
	@Bean
	public TaskExecutor createTaskExecutor(){
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor(); 
		taskExecutor.setCorePoolSize(2);
		taskExecutor.setQueueCapacity(2);
		//taskExecutor.setMaxPoolSize(5);
		return taskExecutor;
	}
}
