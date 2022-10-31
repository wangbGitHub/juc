package com.bob.jmm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author wangbo
 * @Date 2022/10/31 14:33
 * @Description: 原子性校验
 * @Version 1.0
 */
public class AtomicityVerify {

	private static volatile int sum = 0;

	private static Object LOCK = new Object();

	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(10);
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			executor.submit(() -> {
				synchronized (LOCK){
					for (int j = 0; j < 10_000; j++) {
						sum++;
					}
				}
				countDownLatch.countDown();
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
