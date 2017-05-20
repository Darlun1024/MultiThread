package com.wsj.thread;

import javax.swing.plaf.SliderUI;

public class ThreadInterrupt {
	Thread thread1;
	Thread thread2;
	Thread thread3;
	boolean isStopped = false;
	public ThreadInterrupt(){
		
		
	}
	
	/**
	 * 使用interrupt中断
	 */
	public void  startThread1(){
		thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					//这里必须写判断是否中断的方法，否则中断不起作用
					if(Thread.currentThread().isInterrupted())break;
					System.out.println("threa1:"+System.currentTimeMillis());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						//在sleep期间，这里捕获到了InterruptedException，如果要中断线程，必须再标记一次
						//wait()同理
						Thread.currentThread().interrupt();
						e.printStackTrace();
					}
				}
			}
		});
		thread1.start();
	}
	
	
	public void stopThread1WithInterrupt(){
		thread1.interrupt(); //中断线程的正确姿势1
	}
	
	/**
	 * 使用stop中断
	 */
	public void  startThread2(){
		thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					System.out.println("threa2:"+System.currentTimeMillis());
				}
			}
		});
		thread2.start();
	}
	
	public void stopThread2WithStop(){
//		这个方法已经不推荐使用了，这个会强制结束当前进程，进而导致run内部的方法没有执行完，从而造成数据丢失或者破话的问题
		thread2.stop(); //中断线程的错误姿势
	}
	
	/**
	 * 使用标记中断
	 */
	public void  startThread3(){
		thread3 = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!isStopped){
					System.out.println("threa3:"+System.currentTimeMillis());
				}
			}
		});
		thread3.start();
	}
	
	
	public void stopThread3WithFlag(){
		isStopped = true;  //中断线程的正确姿势2
	}
	
	
	
	
}
