package com.wsj.thread;

/**
 * 这个程序用来检验Long类型的写入是不是原子性的，结果是这个操作是原子的
 * 为什么与书里面讲的不一样嘞
 * 测试的设备是64位机器，因此可以一次性写人64位的数据，
 * Long正好是64位的，因此在64位的设备中，Long的写人是原子的
 * 
 * 一下是88中JVM定义的原子操作
 * lock:将一个变量标识为被一个线程独占状态
 * unclock:将一个变量从独占状态释放出来，释放后的变量才可以被其他线程锁定
 * read:将一个变量的值从主内存传输到工作内存中，以便随后的load操作
 * load:把read操作从主内存中得到的变量值放入工作内存的变量的副本中
 * use:把工作内存中的一个变量的值传给执行引擎，每当虚拟机遇到一个使用到变量的指令时都会使用该指令
 * assign:把一个从执行引擎接收到的值赋给工作内存中的变量，每当虚拟机遇到一个给变量赋值的指令时，都要使用该操作
 * store:把工作内存中的一个变量的值传递给主内存，以便随后的write操作
 * write:把store操作从工作内存中得到的变量的值写到主内存中的变量
 * 
 * 这里如果long 换成一个对象（例如String）会发生什么情况嘞
 * 对象的赋值本质上是传递一个地址给变量，地址也是整形（32位机器，地址32位，64位机器，地址64位，JVM可以设置压缩地址）
 * @author gxsn
 */
public class Atomic {
	public static long shareNumber = 0;
	public static String shareString = "abc";
	
	
	public static class WriteThread extends Thread{
		long number;
		public WriteThread(long number){
			this.number = number;
		}
		
		@Override
		public void run() {
			while(true){
				shareNumber = number;
				Thread.yield();
			}
		}
		
	}
	
	public static class ReadThread extends Thread{
		public ReadThread(){
		}
		
		@Override
		public void run() {
			while(true){
				long tmp = shareNumber;
				if(tmp!=1000L && tmp!=2048L&& tmp!=-999L && tmp!=-165043L)
				System.out.println(tmp);
				Thread.yield();
			}
		}
		
	}
	
}





