/**
 * 
 */
package com.mycodes.threadtut.demo4;

/**
 * @author sauagarwal
 *
 */
public class ProducerConsumerDemo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub

		//final Processor processor = new Processor();
		final Processor2 processor = new Processor2();
		Thread t1 = new Thread(new Runnable(){
			public void run() {
				// TODO Auto-generated method stub
				try {
					processor.produce();
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
					processor.consume();
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
