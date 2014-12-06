/**
 * 
 * every object has wait method which is called only within synchronized and waits efficiently (not using system resources)
 * it handovers control of lock - loose control of the lock
 * control is gained by either of two things: it gets control by itself or some other thread calls notify on same object lock
 * 
 * 
 */
package com.mycodes.threadtut.demo4;

import java.util.Scanner;

/**
 * @author sauagarwal
 *
 */
public class Processor {

	public void produce() throws InterruptedException{

		synchronized (this) {
			System.out.println("Producer thread running");
			wait();
			System.out.println("Resumed..");
		}
	}

	public void consume() throws InterruptedException{

		Scanner scanner = new Scanner(System.in);
		
		Thread.sleep(2000);
		
		synchronized(this){
			
			System.out.println("waiting for the return key..");
			scanner.nextLine();
			System.out.println("Return key pressed.");
			notify(); // notify one other thread to lock on this same object lock to wake up
			//notify doesn't relinquish its control of the lock
			//lock is relinquished when this block completes
			Thread.sleep(5000);
			
			
		}
	}

}


