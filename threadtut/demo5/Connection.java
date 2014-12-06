/**
 * 
 */
package com.mycodes.threadtut.demo5;

import java.util.concurrent.Semaphore;

/**
 * @author sauagarwal
 *
 */
public class Connection {

	private static Connection instance = new Connection();
	
	private Semaphore sem = new Semaphore(10); //another param - fairness can be added to make it first thread calling acquire to get the permit
	
	private int connections = 0; //num of conenctions
	
	private Connection(){
		
	}
	
	public static Connection getInstance(){
		return instance;
	}
	
	public void connect(){
		try {
			sem.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try{
			doConnect();
		}
		finally{
			sem.release();
		}
		
	}
	
	public void doConnect(){
		
		
		
		synchronized(this){
			connections++;
			System.out.println("Number of connections : " + connections);
		}
		
		//do some work
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		synchronized(this){
			connections--;
		}
		
		
		
	}
	
}
