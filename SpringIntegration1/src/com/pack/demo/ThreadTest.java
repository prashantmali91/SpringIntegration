package com.pack.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Support11 extends Thread{
	@Override
	public void run() {
		for(int i =0; i<5; i++){
			System.out.println("Support1 cuurent value i =" +i);
		}
	}
	
}

class Support22 implements Runnable{
	@Override
	public void run() {
		for(int i =0; i<5; i++){
			System.out.println("Support2-" +Thread.currentThread().getName() +" cuurent value i =" +i);
		}
	}
	
}
public class ThreadTest {
	public static void main(String[] args) throws Exception {
		Support22 s2 =new Support22();
		//ExecutorService executorService = Executors.newCachedThreadPool();
		//ExecutorService executorService = Executors.newFixedThreadPool(2);
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		//ExecutorService executorService = Executors.newWorkStealingPool();
		
		executorService.execute(s2);
		executorService.execute(s2);
		executorService.execute(s2);
		executorService.execute(s2);
		Thread.sleep(1000);
		executorService.execute(s2);
		executorService.shutdown();
	}

}
