package com.bob.aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author wangbo
 * @Date 2022/11/13 20:58
 * @Description: 条件变量
 * @Version 1.0
 */
public class ReentrantLockConditionVerify {
	private static ReentrantLock LOCK = new ReentrantLock();
	private static Condition BILL_CON = LOCK.newCondition();
	private static Condition MONEY_CON = LOCK.newCondition();

	private static boolean HASH_BILL = false;
	private static boolean HASH_MONEY = false;

	/**
	 * 买票
	 */
	public void cigratee() {
		LOCK.lock();
		try {
			while (!HASH_BILL) {
				try {
					System.out.println("没有票,需要等票");
					BILL_CON.await();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("有票，买票");
		} finally {
			LOCK.unlock();
		}
	}

	//送外卖
	public void takeout() {
		LOCK.lock();
		try {
			while (!HASH_MONEY) {
				try {
					System.out.println("没有钱,需要等钱");
					MONEY_CON.await();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("有钱，买票");
		} finally {
			LOCK.unlock();
		}
	}

	public static void main(String[] args) {
		ReentrantLockConditionVerify test = new ReentrantLockConditionVerify();
		new Thread(() -> {
			test.cigratee();
		}).start();

		new Thread(() -> {
			test.takeout();
		}).start();

		new Thread(() -> {
			LOCK.lock();
			try {
				HASH_BILL = true;
				// 唤醒买票的等待线程
				BILL_CON.signal();
			} finally {
				LOCK.unlock();
			}


		}, "thread-0").start();

		new Thread(() -> {
			LOCK.lock();
			try {
				HASH_MONEY = true;
				// 唤醒送钱的等待线程
				MONEY_CON.signal();
			} finally {
				LOCK.unlock();
			}
		}, "thread-1").start();
	}
}
