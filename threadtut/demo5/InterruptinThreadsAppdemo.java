/**
 * 
 */
package com.mycodes.threadtut.demo5;

import java.util.Random;

/**
 * @author sauagarwal
 *
 */
public class InterruptinThreadsAppdemo {

	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.out.println("Starting....");
		
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				Random random = new Random();
				
				//First way to catch interruption
				for(int i=0; i<1E8; i++){
					
					/*if(Thread.currentThread().isInterrupted()){
						System.out.println("Intdrrupted");
						break;
					}*/
					
				////Second way to catch interruption
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						System.out.println("Interrupted");
						break;
						//e.printStackTrace();
					}
					
					Math.sin(random.nextDouble());
				}
				
			}
			
			
		});
		t1.start();
		
		Thread.sleep(500);

		t1.interrupt();//doesnt stop the thread and tells the thread that it has been interrupted
		
		t1.join();
		
		System.out.println("Finished....");

	}

}
