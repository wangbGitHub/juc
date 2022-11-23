package com.bob.aqs.monopolize;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author wangbo
 * @Date 2022/11/13 20:49
 * @Description: 公平锁验证
 * @Version 1.0
 */
public class ReentrantLockFairVerify {

	public static void main(String[] args) throws InterruptedException {
		// 公平锁
		ReentrantLock lock = new ReentrantLock(true); 

		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				lock.lock();
				try {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " running...");
				} finally {
					lock.unlock();
				}
			}, "thread_" + i).start();
		}
		// 1s 之后去争抢锁
		Thread.sleep(1000);

		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				lock.lock();
				try {
					System.out.println(Thread.currentThread().getName() + " running...");
				} finally {
					lock.unlock();
				}
			}, "thread_1_" + i).start();
		}
	}
}
