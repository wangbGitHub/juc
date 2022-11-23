package com.bob.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author wangbo
 * @Date 2022/11/17 14:19
 * @Description: 线程池验证
 * @Version 1.0
 */
public class ThreadPoolVerify {

	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3,
				5, TimeUnit.SECONDS,
				new ArrayBlockingQueue(1), Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.AbortPolicy());
		executor.execute(() -> {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						System.out.println(1111);
						e.printStackTrace();
					}
					System.out.println(1);
				}
				);
		executor.execute(() -> {
			System.out.println(2);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				System.out.println(222);
				e.printStackTrace();
			}
		});
		executor.execute(() -> {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				System.out.println(3333);
				e.printStackTrace();
			}
			System.out.println(3);
		});

		executor.execute(() -> {
			System.out.println(3);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				System.out.println(4444);
				e.printStackTrace();
			}
		});
	}
}
