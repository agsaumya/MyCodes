/**
 * How to start Threads in java
 */
package com.mycodes.threadtut.demo1;


class Runner extends Thread{

	@Override
	public void run() {
		for(int i=0; i<10; i++){
			System.out.println("Hello " + i);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
	
}


/**
 * @author sauagarwal
 *
 */
public class Thread1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Runner runner1 = new Runner();
		runner1.start();
		
		Runner runner2 = new Runner();
		runner2.start();

	}

}
