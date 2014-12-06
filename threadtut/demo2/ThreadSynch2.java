/*
 * 
 * Thread Interleaving
 * we need to make sure that once a thread is using a variable then no other thread uses it. 
 * 
 * 
 * Use of synchronized - every object in java has an intrinsic lock (monitor lock or mutex)
 * If we call synchronized method of an object, you have to acquire an intrinsic lock before you call it
 * Only one thread can acquire the intrinsic lock then the second thread has to wait.
 * synchronized handles the case of volatile too. It lets each thread see the latest state of the variable
 * 
 */


package com.mycodes.threadtut.demo2;

public class ThreadSynch2 {

	private int count = 0;
	
	public synchronized void increment(){
		count++;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ThreadSynch2 app = new ThreadSynch2();
		app.doSomeWork();
	}
	
	public void doSomeWork(){
		
		Thread t1 = new Thread(new Runnable(){
			public void run() {
				for(int i=0; i<10000; i++){
					increment();
				}				
			}			
		});
		
		Thread t2 = new Thread(new Runnable(){
			public void run() {
				for(int i=0; i<10000; i++){
					increment();
				}				
			}		
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join(); // used to make main thread wait for t1 join method to finish
			t2.join(); // used to make main thread wait for t2 join method to finish
			//without join method sysout line below will get executed even before the above two threads start their operations.
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("value of count is " + count);
		
	}

}
