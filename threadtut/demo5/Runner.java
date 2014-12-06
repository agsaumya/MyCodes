/**
 * 
 */
package com.mycodes.threadtut.demo5;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sauagarwal
 *
 */
public class Runner {

	private int count = 0;

	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();

	private void increment(){
		for(int i=0; i<10000; i++){
			count++;
		}
	}

	public void firstThread() throws InterruptedException{

		lock.lock();

		System.out.println("waiting.....");
		cond.await();//handovers the lock
		
		System.out.println("Woken up....");
		
		try{
			increment(); //if exception is thrown here then the prog will never be unlocked...so use try block
		}
		finally{
			lock.unlock();
		}
	}

	public void secondThread() throws InterruptedException{
		Thread.sleep(2000);
		
		lock.lock();

		System.out.println("Press the return key");
		new Scanner(System.in).nextLine();
		System.out.println("return key pressed");
		
		cond.signal();
		try{
			increment(); //if exception is thrown here then the prog will never be unlocked...so use try block
		}
		finally{
			lock.unlock();
		}
	}

	public void finished(){
		System.out.println("Count is : " + count);

	}

}
