package com.wsj.thread;

public class ThreadWait {
	private static Thread thread1;
	private static Thread thread2;
	
	private static Object object = new Object();
	public static void runThread1(){
		thread1 = new Thread(new Runnable() {
			public void run() {
				synchronized (object) {
					try {
						System.out.println(System.currentTimeMillis()+" 线程1：等线程2notify后，我才能运行");
						object.wait();
						System.out.println(System.currentTimeMillis()+" 线程1：线程2发信号过来了");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		thread1.start();
	}
	
	public static void runThread2(){
		thread2 = new Thread(new Runnable() {
			public void run() {
				synchronized (object) {
					System.out.println(System.currentTimeMillis()+ " 线程2：我获得了锁");
					object.notify(); //通知其他等待的线程
					try {
						System.out.println(System.currentTimeMillis()+ " 线程2：不急，我先睡2秒钟");
						Thread.sleep(2000);   //sleep 不会立即释放资源，而wait会，这也是两个方法的区别之一
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		thread2.start();
	}
	

}
