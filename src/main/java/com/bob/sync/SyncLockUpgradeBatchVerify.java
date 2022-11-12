package com.bob.sync;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangbo
 * @Date 2022/11/12 17:01
 * @Description: 验证批量锁撤销
 * @Version 1.0
 */
public class SyncLockUpgradeBatchVerify {
	public static void main(String[] args) throws InterruptedException {
		Thread.sleep(5000);
		List<Object> locks = new ArrayList<>();

		new Thread(() -> {
			for (int i = 0; i < 60; i++) {
				Object lock = new Object();
				synchronized (lock) {
					locks.add(lock);
				}
			}
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		Thread.sleep(3000);
		System.out.println("LOCK_30" + "-start-" + ClassLayout.parseInstance(locks.get(29)).toPrintable());

		new Thread(() -> {
			for (int i = 0; i < 60; i++) {
				Object lock = locks.get(i);
				if (i == 14 || i == 19 || i == 20 || i == 30) {
					System.out.println("LOCK_" + i + "-start-" + ClassLayout.parseInstance(lock).toPrintable());
				}
				synchronized (lock) {
					if (i == 14 || i == 19 || i == 20 || i == 30) {
						System.out.println("LOCK_" + i + "-underway-" + ClassLayout.parseInstance(lock).toPrintable());
					}
				}
				if (i == 14 || i == 19 || i == 20 || i == 30) {
					System.out.println("LOCK_" + i + "-end-" + ClassLayout.parseInstance(lock).toPrintable());
				}
			}
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		Thread.sleep(4000);

		new Thread(() -> {
			for (int i = 0; i < 60; i++) {
				Object lock = locks.get(i);
				if (i == 14 || i == 19 || i == 20 || i == 30 || i == 39 || i == 40 || i == 41) {
					System.out.println(Thread.currentThread().getName() + "-LOCK_" + i + "-start-" + ClassLayout.parseInstance(lock).toPrintable());
				}
				synchronized (lock) {
					if (i == 14 || i == 19 || i == 20 || i == 30 || i == 39 || i == 40 || i == 41) {
						System.out.println(Thread.currentThread().getName() + "-LOCK_" + i + "-underway-" + ClassLayout.parseInstance(lock).toPrintable());
					}
				}
				if (i == 14 || i == 19 || i == 20 || i == 30 || i == 39 || i == 40 || i == 41) {
					System.out.println(Thread.currentThread().getName() + "-NEW-LOCK_" + i + "-underway-" + ClassLayout.parseInstance(new Object()).toPrintable());
					System.out.println(Thread.currentThread().getName() + "-LOCK_" + i + "-end-" + ClassLayout.parseInstance(lock).toPrintable());
				}
			}
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "thread-3").start();


	}
}
