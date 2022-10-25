package com.bob.jmm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author wangbo
 * @Date 2022/10/25 11:37
 * @Description: 可见性验证
 * @Version 1.0
 * <p>
 * 探索可以打破可见性方式
 */
public class VilenessVerify {

	// private static  Integer SUM = 0;
	private static int SUM = 0;

	//	private volatile static boolean FLAY = true;
	//	private  static Boolean FLAY = true;
	private static boolean FLAY = true;

	public static void main(String[] args) throws InterruptedException {

		Thread thread_1 = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "start");
			while (FLAY) {
				SUM++;
				// 1.通过增加上下文切换来使线程读取到主存数据
				// Thread.yield();
				// 2.通过增加内存屏障的方式使线程读取到主存数据
				// ①Lock lock = new ReentrantLock();
				// ②volatile
				// ③Thread.sleep(1);
				// ④synchronized
				// ⑤使用偏移量
				// 3.使final修饰
				// 4.执行一定时间,使线程读取到主存数据(硬件层面)

//				try {
//					Thread.sleep(1);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				synchronized (FLAY) {
//
//				}
//				getUnsafe().storeFence();
//				executeTime(1000000);



			}
			System.out.println(Thread.currentThread().getName() + "end");
		});
		Thread thread_2 = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "exec start");
			FLAY = false;
			System.out.println(Thread.currentThread().getName() + "exec end");
		});
		thread_1.start();
		Thread.sleep(1000);
		thread_2.start();
		thread_1.join();
		thread_2.join();
		System.out.println(SUM);
	}


	/**
	 * 获取 Unsafe 对象
	 *
	 * @return 结果集
	 */
	private static Unsafe getUnsafe() {
		try {
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			return (Unsafe) field.get(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 执行指定时间
	 *
	 * @param duration 时长
	 */
	private static void executeTime(long duration) {
		long start = System.nanoTime();
		long end;
		do {
			end = System.nanoTime();
		} while (start + duration >= end);
	}


}
