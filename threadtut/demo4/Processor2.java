/**
 * 
 * every object has wait method which is called only within synchronized and waits efficiently (not using system resources)
 * it handovers control of lock - loose control of the lock
 * control is gained by either of two things: it gets control by itself or some other thread calls notify on same object lock
 * 
 * 
 */
package com.mycodes.threadtut.demo4;

import java.util.LinkedList;
import java.util.Random;

/**
 * @author sauagarwal
 *
 */
public class Processor2 {

	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int LIMIT = 10; //limit the size of the linkedlist

	private Object lock = new Object();


	public void produce() throws InterruptedException{
		//add items to store

		int value = 0;

		while(true){

			synchronized (lock) {

				while(list.size() == LIMIT){ //check for the second time when this block wakes up
					lock.wait();
				}

				list.add(value++); //autoboxing will automatically happen from int to Integer
				lock.notify();
				
			}

		}

	}

	public void consume() throws InterruptedException{

		Random random = new Random();
		
		while(true){
			synchronized (lock) {
				
				while(list.size() == 0){ //check for the second time when this block wakes up
					lock.wait();
				}
				
				System.out.print("List size: " + list.size());
				int value = list.removeFirst(); //to make it fifo
				System.out.println("; value is " + value);
				lock.notify();
			}
			
			Thread.sleep(random.nextInt(1000));

		}
	}

}


