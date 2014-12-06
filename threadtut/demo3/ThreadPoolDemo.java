/**
 * demo of ThreadPool
 * WorkerThreads = 2
 * Number of Tasks = 5
 * Threads are reused to run multiple tasks - more efficient than having 5 threads for 5 tasks
 * 
 */
package com.mycodes.threadtut.demo3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author sauagarwal
 *
 */

class Processor implements Runnable{

	private int id;
	
	public Processor(int id){
		this.id = id; 
	}
	
	@Override
	public void run() {
		System.out.println("Starting: " + id);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed: " + id);	
	}
}

public class ThreadPoolDemo {

	
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
//ExecutorService has its own managerial thread
		for(int i=0; i<5; i++){
			executor.submit(new Processor(i));
		}
		
		executor.shutdown();//stops accepting more tasks
		
		System.out.println("All tasks submitted");
		//lets wait till all tasks are completed - below code
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("All takss compelted...");
	}

}
