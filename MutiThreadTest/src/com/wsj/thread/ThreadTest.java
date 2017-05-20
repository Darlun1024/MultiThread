package com.wsj.thread;

import com.wsj.thread.Atomic.ReadThread;
import com.wsj.thread.Atomic.WriteThread;

public class ThreadTest {
	public static void main(String[] args){
		testWait();
	}
	
	
	private static void testAtomic(){
		new WriteThread(1000L).start();
		new WriteThread(-999L).start();
		new WriteThread(2048L).start();
		new WriteThread(-165043L).start();
		new ReadThread().start();
	}
	
	/**
	 * 测试线程中断
	 */
	private static void testInterrupt(){
		ThreadInterrupt interrupt = new ThreadInterrupt();
		
		try {
			interrupt.startThread1();
			Thread.sleep(2000);
			interrupt.stopThread1WithInterrupt();
			interrupt.startThread2();
			Thread.sleep(2000);
			interrupt.stopThread2WithStop();
			interrupt.startThread3();
			Thread.sleep(2000);
			interrupt.stopThread3WithFlag();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void testWait(){
		ThreadWait.runThread1();
		ThreadWait.runThread2();
	}
}
