/**
 * Demo of CountDownLatch
 * CountDownLatch let you countdown a number. It lets one or more thread wait till the latch=0
 * 
 */
package com.mycodes.threadtut.demo3;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author sauagarwal
 *
 */

class Processor2 implements Runnable{

	private CountDownLatch latch;
	
	public Processor2(CountDownLatch latch){
		this.latch = latch;
	}
	
	@Override
	public void run() {
		
		System.out.println("Started: ");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		latch.countDown(); // no use of synchronized since CountDownLatch is a threadsafe class
		
	}
	
}

public class CountDownLatchDemo {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CountDownLatch latch = new CountDownLatch(3);
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		//3 threads and 3 processors (task)
		
		for(int i=0; i< 3; i++){
			executor.submit(new Processor2(latch));
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed...");

	}

}
