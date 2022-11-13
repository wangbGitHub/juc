package com.bob.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author wangbo
 * @Date 2022/11/13 20:28
 * @Description: ReentrantLock 可重入验证
 * @Version 1.0
 */
public class ReentrantLockReentryVerify {
	private static Lock LOCK = new ReentrantLock();

	public static void main(String[] args) {

		LOCK.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "进行锁");
			methodLock();
		} finally {
			LOCK.unlock();
		}
	}

	private static void methodLock() {
		LOCK.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "进行锁");
			int i = 0;
			i++;
		} finally {
			LOCK.unlock();
		}
	}

}
