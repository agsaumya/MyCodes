/**
 * 
 */
package com.mycodes.threadtut.demo5;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author sauagarwal
 *
 */
public class CallableFutureAppDemo {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ExecutorService executor = Executors.newCachedThreadPool();
		
		/*executor.submit(new Runnable(){

			//how to return from run method
			
			@Override
			public void run() {
				
				Random random = new Random();
				int duration = random.nextInt(4000);
				System.out.println("Starting to run.....");
				
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Finished...");
				
			}
			
		});*/
		
		Future<Integer> future =  executor.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
				Random random = new Random();
				int duration = random.nextInt(4000);
				
				if(duration > 2000)
					throw new IOException("Sleeping too long..");
				
				System.out.println("Starting to run.....");
				
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Finished...");
				
				return duration;
			}
			
		});
			
		try {
			System.out.println("Result is: " + future.get());//get blocks the future instance thread so no need to have awaittermination on executor
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
			IOException ex = (IOException) e.getCause();
			
			System.out.println(e.getMessage());
		}
		
		executor.shutdown();
		
		
	}

}
