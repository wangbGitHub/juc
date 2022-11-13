package com.bob.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author wangbo
 * @Date 2022/11/13 20:20
 * @Description: ReentrantLock可中断
 * @Version 1.0
 */
public class ReentrantLockInterruptiblyVerify {

	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		Thread thread_1 = new Thread(() -> {

			System.out.println(Thread.currentThread().getName()+" "+ "start");
			try {
				lock.lockInterruptibly();
				try {
					System.out.println(Thread.currentThread().getName()+" "+ "underway");
				} finally {
					lock.unlock();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println(Thread.currentThread().getName()+ " 等锁的过程中被中断");
			}

		}, "Thread-1");
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName()+  " 线程获得了锁");
			thread_1.start();
			//先让线程t1执行
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			thread_1.interrupt();
			System.out.println(thread_1.getName() + "执行中断");
		} finally {
			lock.unlock();
		}
	}
}
