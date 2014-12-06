/**

 * How to start Threads in java
 */
package com.mycodes.threadtut.demo1;

/**
 * @author sauagarwal
 *
 */
public class Thread3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Thread thread1 = new Thread(new Runnable(){

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
			
		});
		
		thread1.start();

	}

}
