package com.bob.jmm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author wangbo
 * @Date 2022/10/31 14:33
 * @Description: 原子性校验
 * @Version 1.0
 */
public class AtomicityVerify {

	private static volatile int sum = 0;


	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(10);
		ExecutorService executor = Executors.newFixedThreadPool(10);
		Lock lock = new ReentrantLock();
		for (int i = 0; i < 10; i++) {
			executor.submit(() -> {
				try {
					lock.lock();
					for (int j = 0; j < 10_000; j++) {
						sum++;
					}
				} finally {
					lock.unlock();
					countDownLatch.countDown();
				}
			});
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(sum);
	}
}
