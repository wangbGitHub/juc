package com.bob.aqs.share;

import java.util.concurrent.CountDownLatch;

/**
 * @Author wangbo
 * @Date 2022/11/21 18:04
 * @Description: 闭锁
 * @Version 1.0
 */
public class CountDownLatchVerify {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(3);
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + " start ");
				countDownLatch.countDown();
				System.out.println(Thread.currentThread().getName() + " end ");
			}, "thread-" + i).start();
		}
		countDownLatch.await();
	}
}
