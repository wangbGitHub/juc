package com.bob.sync;


import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author wangbo
 * @Date 2022/11/7 15:52
 * @Description: synchronized 升级校验
 * @Version 1.0
 * <p>
 * -XX:+PrintFlagsFinal
 * -XX:BiasedLockingStartupDelay=0
 * -XX:-UseBiasedLocking
 */
public class SyncLockUpgradeVerify {


	public static void main(String[] args) throws InterruptedException {
//		Thread.sleep(5000);
		// 1.无锁升级为轻量级锁
//		Object LOCK = new Object();
//		System.out.println("LOCK" + "-init-" + ClassLayout.parseInstance(LOCK).toPrintable());
//
//		Thread thread_1 =  new Thread(() -> {
//			System.out.println("LOCK" + "-start-" + ClassLayout.parseInstance(LOCK).toPrintable());
//			synchronized (LOCK) {
//				System.out.println("LOCK" + "-underway-" + ClassLayout.parseInstance(LOCK).toPrintable());
//			}
//			System.out.println("LOCK" + "-end-" + ClassLayout.parseInstance(LOCK).toPrintable());
//		}, "thread_1");
//		thread_1.start();
//		thread_1.join();
//		System.out.println("LOCK" + "-final-" + ClassLayout.parseInstance(LOCK).toPrintable());

		// 2.偏向锁 发生偏向
		// 发生偏向锁优化
//		Thread.sleep(5000);
//		Object LOCK = new Object();
//		System.out.println("LOCK" + "-init-" + ClassLayout.parseInstance(LOCK).toPrintable());
//
//		Thread thread_1 =  new Thread(() -> {
//			System.out.println("LOCK" + "-start-" + ClassLayout.parseInstance(LOCK).toPrintable());
//			synchronized (LOCK) {
//				System.out.println("LOCK" + "-underway-" + ClassLayout.parseInstance(LOCK).toPrintable());
//			}
//			System.out.println("LOCK" + "-end-" + ClassLayout.parseInstance(LOCK).toPrintable());
//		}, "thread_1");
//		thread_1.start();
//		thread_1.join();
//		System.out.println("LOCK" + "-final-" + ClassLayout.parseInstance(LOCK).toPrintable());


		// 2.偏向锁升级 轻量级锁
//		Object LOCK_1 = new Object();
//
//		System.out.println("LOCK_1" + "-init-" + ClassLayout.parseInstance(LOCK_1).toPrintable());
//
//		Thread thread_2 = new Thread(() -> {
//			System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-start-" + ClassLayout.parseInstance(LOCK_1).toPrintable());
//			synchronized (LOCK_1) {
//				System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-underway-" + ClassLayout.parseInstance(LOCK_1).toPrintable());
//			}
//			System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-end-" + ClassLayout.parseInstance(LOCK_1).toPrintable());
//			// 这里需要进行线程保活,否则jvm存在优化会复用这个线程
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}, "thread_2");
//		thread_2.start();
//		// 防止执行过快,没有形成轻微竞争调教
//		Thread.sleep(10);
//		Thread thread_3 = new Thread(() -> {
//			System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-start-" + ClassLayout.parseInstance(LOCK_1).toPrintable());
//			synchronized (LOCK_1) {
//				System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-underway-" + ClassLayout.parseInstance(LOCK_1).toPrintable());
//			}
//			System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-end-" + ClassLayout.parseInstance(LOCK_1).toPrintable());
//		}, "thread_3");
//		thread_3.start();
//		thread_2.join();
//		thread_3.join();
//		System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-final-" + ClassLayout.parseInstance(LOCK_1).toPrintable());

		// 2.重量锁
//		Thread.sleep(5000);
//		Object LOCK_1 = new Object();
//
//		System.out.println("LOCK_1" + "-init-" + ClassLayout.parseInstance(LOCK_1).toPrintable());
//
//		Thread thread_2 = new Thread(() -> {
//			System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-start-" + ClassLayout.parseInstance(LOCK_1).toPrintable());
//			synchronized (LOCK_1) {
//				System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-underway-" + ClassLayout.parseInstance(LOCK_1).toPrintable());
//			}
//			System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-end-" + ClassLayout.parseInstance(LOCK_1).toPrintable());
//			// 这里需要进行线程保活,否则jvm存在优化会复用这个线程
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}, "thread_2");
//		thread_2.start();
//		// 防止执行过快,没有形成轻微竞争调教
//		Thread thread_3 = new Thread(() -> {
//			System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-start-" + ClassLayout.parseInstance(LOCK_1).toPrintable());
//			synchronized (LOCK_1) {
//				System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-underway-" + ClassLayout.parseInstance(LOCK_1).toPrintable());
//			}
//			System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-end-" + ClassLayout.parseInstance(LOCK_1).toPrintable());
//		}, "thread_3");
//		thread_3.start();
//		thread_2.join();
//		thread_3.join();
//		System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-final-" + ClassLayout.parseInstance(LOCK_1).toPrintable());

//
		Thread.sleep(5000);
		Object LOCK = new Object();
		System.out.println("LOCK" + "-init-" + ClassLayout.parseInstance(LOCK).toPrintable());

//		LOCK.hashCode();
//		System.out.println("LOCK" + "-start-" + ClassLayout.parseInstance(LOCK).toPrintable());
		synchronized (LOCK){
			LOCK.wait(200);
			System.out.println("LOCK" + "-underway-" + ClassLayout.parseInstance(LOCK).toPrintable());
		}
		System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-end-" + ClassLayout.parseInstance(LOCK).toPrintable());

		// 3.偏向锁升级 重量级锁
//		Object LOCK_2 = new Object();
//
//		System.out.println("LOCK_1" + "-init-" + ClassLayout.parseInstance(LOCK_2).toPrintable());
//
//		Thread thread_4 = new Thread(() -> {
//			System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-start-" + ClassLayout.parseInstance(LOCK_2).toPrintable());
//			synchronized (LOCK_2) {
//				System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-underway-" + ClassLayout.parseInstance(LOCK_2).toPrintable());
//			}
//			System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-end-" + ClassLayout.parseInstance(LOCK_2).toPrintable());
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}, "thread_4");
//		thread_4.start();
//		Thread thread_5 = new Thread(() -> {
//			System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-start-" + ClassLayout.parseInstance(LOCK_2).toPrintable());
//			synchronized (LOCK_2) {
//				System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-underway-" + ClassLayout.parseInstance(LOCK_2).toPrintable());
//			}
//			System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-end-" + ClassLayout.parseInstance(LOCK_2).toPrintable());
//		}, "thread_5");
//		thread_5.start();
//		Thread.sleep(5000);
//		System.out.println(Thread.currentThread().getName() + "-LOCK_1" + "-final-" + ClassLayout.parseInstance(LOCK_2).toPrintable());






		// 延时产生可偏向对象
//		Thread.sleep(5000);
//		// 创建一个list，来存放锁对象
//		List<Object> list = new ArrayList<>();
//
//		// 线程1
//		new Thread(() -> {
//			for (int i = 0; i < 50; i++) {
//				// 新建锁对象
//				Object lock = new Object();
//				synchronized (lock) {
//					list.add(lock);
//				}
//			}
//			try {
//				//为了防止JVM线程复用，在创建完对象后，保持线程thead1状态为存活
//				Thread.sleep(100000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}, "thead1").start();
//
//		// 睡眠3s钟保证线程thead1创建对象完成
//		Thread.sleep(3000);
//		System.out.println((ClassLayout.parseInstance(list.get(19)).toPrintable()));
//
//		// 线程2
//		new Thread(() -> {
//			for (int i = 0; i < 40; i++) {
//				Object obj = list.get(i);
//				synchronized (obj) {
//					if(i>=15&&i<=21||i>=38){
//						System.out.println("thread2-第" + (i + 1) + "次加锁执行中\t"+
//								ClassLayout.parseInstance(obj).toPrintable());
//					}
//				}
//				if(i==17||i==19){
//					System.out.println("thread2-第" + (i + 1) + "次释放锁\t"+
//							ClassLayout.parseInstance(obj).toPrintable());
//				}
//			}
//			try {
//				Thread.sleep(100000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}, "thead2").start();
//
//		LockSupport.park();

//		延时产生可偏向对象
//		Thread.sleep(5000);
//		// 创建一个list，来存放锁对象
//		List<Object> list = new ArrayList<>();
//
//		// 线程1
//		new Thread(() -> {
//			for (int i = 0; i < 50; i++) {
//				// 新建锁对象
//				Object lock = new Object();
//				synchronized (lock) {
//					list.add(lock);
//				}
//			}
//			try {
//				// 为了防止JVM线程复用，在创建完对象后，保持线程thead1状态为存活
//				Thread.sleep(100000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}, "thead1").start();
//
//		//睡眠3s钟保证线程thead1创建对象完成
//		Thread.sleep(5000);
//		System.out.println("LOCK_30" + "-init-" + (ClassLayout.parseInstance(list.get(19)).toPrintable()));
//
////		 线程2
//		new Thread(() -> {
//			for (int i = 0; i < 40; i++) {
//				Object obj = list.get(i);
//				synchronized (obj) {
//					if (i >= 15 && i <= 21 || i >= 38) {
//						System.out.println(Thread.currentThread().getName() + "-LOCK_" + (i + 1) + "-underway-" +
//								ClassLayout.parseInstance(obj).toPrintable());
//					}
//				}
//				if (i == 15 || i == 19 || i == 39) {
//					System.out.println(Thread.currentThread().getName() + "-LOCK_" + (i + 1) + "-end-" +
//							ClassLayout.parseInstance(obj).toPrintable());
//				}
//			}
//			try {
//				Thread.sleep(100000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}, "thead2").start();
//
//		new Thread(() -> {
//			for (int i = 0; i < 50; i++) {
//				Object lock = list.get(i);
//				if (i >= 15 && i <= 21 || i >= 35 && i <= 41) {
//					System.out.println(Thread.currentThread().getName() + "-LOCK_" + (i + 1) + "-start-" +
//							ClassLayout.parseInstance(lock).toPrintable());
//				}
//				synchronized (lock) {
//					if (i >= 15 && i <= 21 || i >= 35 && i <= 41) {
//						System.out.println(Thread.currentThread().getName() + "-LOCK_" + (i + 1) + "-underway-" +
//								ClassLayout.parseInstance(lock).toPrintable());
//					}
//				}
//				if (i >= 15 && i <= 21 || i >= 35 && i <= 41) {
//					System.out.println(Thread.currentThread().getName() + "-LOCK_" + (i + 1) + "-end-" +
//							ClassLayout.parseInstance(lock).toPrintable());
//				}
//			}
//		}, "thread3").start();
//
//		Thread.sleep(3000);
//		System.out.println("新的创建对象:" + (ClassLayout.parseInstance(new Object()).toPrintable()));
//
//		LockSupport.park();


//		Thread.sleep(5000);
//		List<Object> locks = new ArrayList<>();
//
//		new Thread(() -> {
//			for (int i = 0; i< 60;i++){
//				Object lock = new Object();
//				synchronized (lock) {
//					locks.add(lock);
//				}
//			}
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}).start();
//
//		Thread.sleep(3000);
//		System.out.println("LOCK_30" + "-start-" + ClassLayout.parseInstance(locks.get(29)).toPrintable());
//
//		new Thread(() -> {
//			for (int i = 0; i< 60;i++){
//				Object lock = locks.get(i);
//				if (i == 14 || i == 19 || i == 20 || i == 30){
//					System.out.println(Thread.currentThread().getName() +"-LOCK_"+ i + "-start-" + ClassLayout.parseInstance(lock).toPrintable());
//				}
//				synchronized (lock){
//					if (i == 14 || i == 19 || i == 20 || i == 30){
//						System.out.println(Thread.currentThread().getName() +"-LOCK_"+ i + "-underway-" + ClassLayout.parseInstance(lock).toPrintable());
//					}
//				}
//				if (i == 14 || i == 19 || i == 20 || i == 30){
//					System.out.println(Thread.currentThread().getName() +"-LOCK_"+ i + "-end-" + ClassLayout.parseInstance(lock).toPrintable());
//				}
//			}
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		},"thread-2").start();
//		Thread.sleep(6000);
//		new Thread(() -> {
//			for (int i = 0; i< 60;i++){
//				Object lock = locks.get(i);
//				if (i == 14 || i == 19 || i == 20 || i == 30 || i == 39|| i == 40|| i == 41){
//					System.out.println(Thread.currentThread().getName() +"-LOCK_"+ i + "-start-" + ClassLayout.parseInstance(lock).toPrintable());
//				}
//				synchronized (lock){
//					if (i == 14 || i == 19 || i == 20 || i == 30 || i == 39|| i == 40|| i == 41){
//						System.out.println(Thread.currentThread().getName() +"-LOCK_"+ i + "-underway-" + ClassLayout.parseInstance(lock).toPrintable());
//					}
//				}
//				if (i == 14 || i == 19 || i == 20 || i == 30 || i == 39|| i == 40|| i == 41){
//					System.out.println(Thread.currentThread().getName() +"-LOCK_"+ i + "-end-" + ClassLayout.parseInstance(lock).toPrintable());
//				}
//			}
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		},"thread-3").start();


	}
}
