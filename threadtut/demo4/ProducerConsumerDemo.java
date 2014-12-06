/**
 * 
 * BlockingQueues - ArrayBlockingQueue is a data structure to store data items in FiFo fashion
 * These are thread safe - so don't have to worry about low level thread synchronization(use of synchronized keyword))
 * 
 * producer is producing things and consumer is removing things from the queue
 * 
 * we are using java.util.concurrent package
 */
package com.mycodes.threadtut.demo4;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author sauagarwal
 *
 */

public class ProducerConsumerDemo {

	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
	
	private static void producer() throws InterruptedException{
		Random random = new Random();
		
		while(true){
			queue.put(random.nextInt(100));
		}		
	}
	
	private static void consumer() throws InterruptedException{
		Random random = new Random(); //randomly take things from the queue
		
		while(true){
			Thread.sleep(100);
			
			if(random.nextInt(10) == 0){
				Integer value = queue.take();
				
				System.out.println("Taken value: " + value);
				System.out.println("Queue Size: " + queue.size());
				System.out.println();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		Thread t1 = new Thread(new Runnable(){
			public void run() {
				// TODO Auto-generated method stub
				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		});
		
		Thread t2 = new Thread(new Runnable(){
			public void run() {
				// TODO Auto-generated method stub
				try {
					consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
	}

}
