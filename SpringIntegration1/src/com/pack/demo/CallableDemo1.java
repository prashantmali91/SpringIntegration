package com.pack.demo;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

class Collable1 implements Callable<String>{

	@Override
	public String call() throws Exception {
		System.out.println("In call....");
		Thread.sleep(300);
		return "abc";
	}
	
}
class WriteFile1 implements Runnable{

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
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
public class CallableDemo1 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		WriteFile1 wf= new WriteFile1();
		
		Future<String> callable1 = Executors.newScheduledThreadPool(1).submit(new Collable1());
		System.out.println(callable1.get());
		
		Callable<String> callable2 = Executors.callable(wf, "Completed");
		
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
		Future<String> fo = ses.submit(callable2);
		System.out.println("after submit "+ fo.get());
		ses.shutdown();

	}

}
