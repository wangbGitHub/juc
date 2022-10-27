package com.bob.thread;

/**
 * @Author wangbo
 * @Date 2022/10/27 15:49
 * @Description: 线程状态切换
 * @Version 1.0
 */
public class ThreadStateVerify {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("------------------- 正常流程 start ------------------");
		// 验证 常规流程
		verifyRoutine();
		System.out.println("------------------- 正常流程 end ------------------");
		// 验证 BLOCKED
		// 满足如下条件
		// ①synchronized
		// ②Object.wait()
		// ③锁的对象偏重锁
		System.out.println("------------------- BLOCKED start ------------------");
		verifyBlocked();
		System.out.println("------------------- BLOCKED start ------------------");

		System.out.println("------------------- WAITING Object.wait()  start ------------------");
		verifyWaiting();
		System.out.println("------------------- WAITING Object.wait()  end ------------------");

		Thread thread_7 = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " exec start");
			System.out.println(Thread.currentThread().getName() + "-state:" + Thread.currentThread().getState());
			executeTime(1000000000);
			System.out.println(Thread.currentThread().getName() + " exec end");
		},"thread_7");
		Thread thread_8 = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " exec start");
			System.out.println(Thread.currentThread().getName() + "-state:" + Thread.currentThread().getState());
			Thread.State state = thread_7.getState();
			while (state != Thread.State.WAITING){
				System.out.println(state);
			}
			System.out.println(thread_7.getName() + "-"+ thread_7.getState());
			System.out.println(Thread.currentThread().getName() + " exec end");
		},"thread_8");
		System.out.println(thread_7.getName() + "-state:" + thread_7.getState());
		thread_7.start();
		thread_8.start();
		System.out.println(1);
		thread_7.join();
		thread_8.join();
		System.out.println(thread_7.getName() + "-state:" + thread_7.getState());

	}

	/**
	 * 验证WAITING Object.wait()
	 *
	 * @throws InterruptedException
	 */
	private static void verifyWaiting() throws InterruptedException {
		Object thread_5_lock = new Object();
		Thread thread_5 = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " exec start");
			System.out.println(Thread.currentThread().getName() + "-state:" + Thread.currentThread().getState());
			synchronized (thread_5_lock) {
				try {
					thread_5_lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				executeTime(1000000000);
				executeTime(1000000000);
				executeTime(1000000000);
			}
			System.out.println(Thread.currentThread().getName() + " exec end");
		}, "Thread_5");
		Thread thread_6 = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " exec start");
			System.out.println(Thread.currentThread().getName() + "-state:" + Thread.currentThread().getState());
			synchronized (thread_5_lock) {
				thread_5_lock.notify();
				executeTime(1000000000);
			}
			System.out.println(Thread.currentThread().getName() + " exec end");
		}, "Thread_6");
		System.out.println(thread_5.getName() + "-state:" + thread_5.getState());
		thread_5.start();
		Thread.sleep(10);
		thread_6.start();
		System.out.println(1);
		System.out.println(thread_5.getName() + "-state:" + thread_5.getState());
		thread_5.join();
		thread_6.join();
		System.out.println(thread_5.getName() + "-state:" + thread_5.getState());
	}

	/**
	 * 验证阻塞状态
	 *
	 * @throws InterruptedException
	 */
	private static void verifyBlocked() throws InterruptedException {
		Object thread_2_lock = new Object();
		Thread thread_2 = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " exec start");
			System.out.println(Thread.currentThread().getName() + "-state:" + Thread.currentThread().getState());
			synchronized (thread_2_lock) {
				try {
					thread_2_lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				executeTime(1000000000);
				executeTime(1000000000);
				executeTime(1000000000);
			}
			System.out.println(Thread.currentThread().getName() + " exec end");
		}, "Thread_2");
		Thread thread_3 = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " exec start");
			System.out.println(Thread.currentThread().getName() + "-state:" + Thread.currentThread().getState());
			synchronized (thread_2_lock) {
				thread_2_lock.notify();
				executeTime(1000000000);
			}
			System.out.println(Thread.currentThread().getName() + " exec end");
		}, "Thread_3");
		Thread thread_4 = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " exec start");
			System.out.println(Thread.currentThread().getName() + "-state:" + Thread.currentThread().getState());
			synchronized (thread_2_lock) {
				executeTime(1000000000);
			}
			System.out.println(Thread.currentThread().getName() + " exec end");
		}, "Thread_4");
		System.out.println(thread_2.getName() + "-state:" + thread_2.getState());
		thread_2.start();
		Thread.sleep(10);
		thread_3.start();
		thread_4.start();
		System.out.println(1);
		System.out.println(thread_2.getName() + "-state:" + thread_2.getState());
		thread_2.join();
		System.out.println(thread_2.getName() + "-state:" + thread_2.getState());
		thread_3.join();
		thread_4.join();
	}

	private static void verifyRoutine() throws InterruptedException {
		Thread thread_1 = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " exec start");
			System.out.println(Thread.currentThread().getName() + "-state:" + Thread.currentThread().getState());
			System.out.println(Thread.currentThread().getName() + " exec end");
		}, "Thread_1");
		System.out.println(thread_1.getName() + "-state:" + thread_1.getState());
		thread_1.start();
		thread_1.join();
		System.out.println(thread_1.getName() + "-state:" + thread_1.getState());
	}

	/**
	 * 执行指定时间
	 *
	 * @param duration 时长
	 */
	private static void executeTime(long duration) {
		long start = System.nanoTime();
		long end;
		do {
			end = System.nanoTime();
		} while (start + duration >= end);
	}
}

