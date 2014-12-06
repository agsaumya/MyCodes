/**
 * 
 */
package com.mycodes.threadtut.demo5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author sauagarwal
 *
 */
public class SemaphoreAppDemo {

	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		/*Semaphore sem = new Semaphore(1); // semaphores used to control access to some code
		
		sem.release();//increase the num of permits
		sem.acquire(); // decreases the num of permits
		
		System.out.println("Available permits: " + sem.availablePermits());
		
		*/
		
		//Connection.getInstance().connect();
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for(int i=0; i<200; i++) {
			
			executor.submit(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Connection.getInstance().connect();
				}
				
				
			});
			
		}
		
		executor.shutdown();
		
		executor.awaitTermination(1, TimeUnit.DAYS);
		
	}

}
