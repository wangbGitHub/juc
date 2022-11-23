package com.bob.aqs.monopolize;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author wangbo
 * @Date 2022/11/13 08:08
 * @Description: ReentrantLock 验证与练习
 * @Version 1.0
 */
public class ReentrantLockVerify {
	private static volatile int count = 0;

	public static void main(String[] args) throws InterruptedException {
		Lock lock = new ReentrantLock();
		CountDownLatch countDownLatch = new CountDownLatch(10);
		for (int i=0; i< 10;i++){
			new Thread(() ->{
				lock.lock();
				try {
					for (int j=0;j<10000;j++){
						count++;
					}
				} finally {
					lock.unlock();
					countDownLatch.countDown();
				}
			},"thread-"+i).start();
		}
		countDownLatch.await();
		System.out.println(count);


	}
}
