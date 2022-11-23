package com.bob.aqs.monopolize;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author wangbo
 * @Date 2022/11/13 20:35
 * @Description: 锁超时-失败
 * @Version 1.0
 */
public class ReentrantLockFailVerify {
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		Thread thread_1 = new Thread(() -> {

			System.out.println(Thread.currentThread().getName() + "  start" );
			// 注意： 即使是设置的公平锁，此方法也会立即返回获取锁成功或失败，公平策略不生效
			if (!lock.tryLock()) {
				System.out.println(Thread.currentThread().getName() + "获取锁失败，立即返回false" );
				return;
			}
			try {
				System.out.println(Thread.currentThread().getName() + "获取锁" );
			} finally {
				lock.unlock();
			}

		}, "thread_1");


		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " 获取到锁 underway" );
			thread_1.start();
			// 先让线程t1执行
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} finally {
			lock.unlock();
		}
	}
}
