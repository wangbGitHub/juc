package com.bob.cas;

import com.bob.cas.bean.CasLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author wangbo
 * @Date 2022/11/2 16:55
 * @Description: cas验证
 * @Version 1.0
 */
public class CasVerify {

	private static volatile int sum = 0;

	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(10);
		ExecutorService executor = Executors.newFixedThreadPool(10);
		CasLock lock = new CasLock();
		for (int i = 0; i < 10; i++) {
			executor.submit(() -> {
				for (; ; ) {
					if (!lock.isLock() && lock.lock()) {
						try {
							for (int j = 0; j < 10_000; j++) {
								sum++;
							}
						} finally {
							lock.unLock();
						}
						break;
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
