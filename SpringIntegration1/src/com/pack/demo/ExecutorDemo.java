package com.pack.demo;


class Support1 extends Thread{
	
	@Override
	public void run() {
		
		for(int i =0; i<5; i++){
			System.out.println("Support1 cuurent value i =" +i);
		}
	}
	
}

class Support2 implements Runnable{
	
	@Override
	public void run() {
		for(int i =0; i<5; i++){
			System.out.println("Support2 cuurent value i =" +i);
		}
	}
	
}
public class ExecutorDemo {
	public static void main(String[] args) {
		Support1 s1 = new Support1();
		s1.start();
		Support2 s2 = new Support2();
		new Thread(s2).start();
	}

}
