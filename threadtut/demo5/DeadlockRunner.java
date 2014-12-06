/**
 * 
 */
package com.mycodes.threadtut.demo5;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sauagarwal
 *
 */
public class DeadlockRunner {

	private Account ac1 = new Account();
	private Account ac2 = new Account();

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();


	private void acquireLocks(Lock l1, Lock l2) throws InterruptedException{
		while(true){
			//acquire the locks

			boolean gotFirstLock = false;
			boolean gotSecondLock = false;	

			try{
				gotFirstLock = l1.tryLock();
				gotSecondLock = l2.tryLock();
			}
			finally{
				if(gotFirstLock && gotSecondLock)
					return;

				if(gotFirstLock) l1.unlock();

				if(gotSecondLock) l1.unlock();
			}


			//locks not acquired
			Thread.sleep(100);
		}
	}

	public void firstThread() throws InterruptedException{

		Random random = new Random();
		for(int i=0; i<10000; i++){

			//lock1.lock();
			//lock2.lock();

			acquireLocks(lock1, lock2);
			try{
				Account.transfer(ac1, ac2, random.nextInt(100));
			}
			finally{
				lock1.unlock();
				lock2.unlock();
			}
		}

	}

	public void secondThread() throws InterruptedException{

		Random random = new Random();
		for(int i=0; i<10000; i++){

			//lock1.lock();
			//lock2.lock();

			acquireLocks(lock2, lock1);
			try{
				Account.transfer(ac2, ac1, random.nextInt(100));
			}
			finally{
				lock1.unlock();
				lock2.unlock();
			}
		}
	}

	public void finished(){
		System.out.println("Account 1 balance : " + ac1.getBalance());
		System.out.println("Account 2 balance : " + ac2.getBalance());
		System.out.println("Total balance  : " + (ac1.getBalance()+ac2.getBalance()));

	}

}
