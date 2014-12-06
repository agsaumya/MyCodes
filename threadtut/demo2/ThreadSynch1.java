/**
 * Basic Thread Synchronization
 * 
 * In some implementations run method code might decide to cache value of running and might not see change in state from another thread (java optimization)
 * First thread (Processor) is reading the value of running and another thread (Main thread) is trying to write/change the state
 * volatile keyword removes optimizations (prevents caching of values in the thread) (change of value of the variable can also be done by using thread synchronization - use of synchronized keyword)
 * 
 * Use of volatile keyword
 */
/**
 * @author sauagarwal
 *
 */
package com.mycodes.threadtut.demo2;

import java.util.Scanner;


class Processor extends Thread{
	
	//private boolean running = true;
	private volatile boolean running = true;
	
	public void run(){
		
		while(running){
			System.out.println("Hello");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown(){
		running = false;
	}
	
}

public class ThreadSynch1 {

	public static void main(String[] args) {
		
		Processor proc1 = new Processor();
		proc1.start();
		
		System.out.println("Press return key to stop.....");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		proc1.shutdown();

	}

}
