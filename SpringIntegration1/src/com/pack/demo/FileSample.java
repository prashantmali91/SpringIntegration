package com.pack.demo;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class WriteFile implements Runnable{

	private String status = "Starting..";
	@Override
	public void run() {
		 try{    
             FileOutputStream fout=new FileOutputStream("C://SpringIntegrationWorkspace/test.txt");    
             for(int i =0 ;i<10; i++){
            	String str = "\rHello "+new Date(); 
            	fout.write(str.getBytes());
            	Thread.sleep(1000);
            	status = i *10 +" percent completed";
             }
             fout.close();
             status = "Done!";
            }catch(Exception e){System.out.println(e);
            
            } 
		    finally {
				
			}
         
		
	}
	public String getStatus() {
		System.out.println("Starting of get Status.." + new Date());
		try {
			Thread.sleep((int) (Math.random()*1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Ending of get Status.." + new Date());
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
public class FileSample {
	public static void main(String[] args) throws Exception {
		WriteFile wf = new WriteFile();
		new Thread(wf).start();
		
		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
		//ses.scheduleAtFixedRate(()-> System.out.println("Current Status " +wf.getStatus()),0,1,TimeUnit.SECONDS);
		ses.scheduleWithFixedDelay(()-> System.out.println("Current Status " +wf.getStatus()),0,1,TimeUnit.SECONDS);
				
		
		ses.awaitTermination(15,TimeUnit.SECONDS);
		ses.shutdown();
	}
}
