package com.bob.jmm;

import sun.misc.Contended;

/**
 * @Author wangbo
 * @Date 2022/10/27 14:40
 * @Description: 内存伪共享问题
 * @Version 1.0
 * <p>
 * 解析
 */
public class ForgeShareVerify {

	public static void main(String[] args) throws InterruptedException {
		// 详解
		// 1.在未加volatile 情况下 执行时间平均时间:136
		// 2.在加volatile 情况下 执行时间平均时间:405
		// 问题原因:计算机在读取数据时,并不是一个一个数据读取,而是一个一个缓存行读取,读取时会把相邻的数据取出来放到一个缓存行中
		// 解决方案:
		// 方案一:将数据填充满一个缓存行
		// 方案二:@Contended + jvm参数 -XX:-RestrictContended


		Coord coord = new Coord();
		long start = System.currentTimeMillis();
		Thread thread_1 = new Thread(() -> {
			for (int i = 0; i < 1_000_0000; i++) {
				coord.x++;
			}
		});

		Thread thread_2 = new Thread(() -> {
			for (int i = 0; i < 1_000_0000; i++) {
				coord.y++;
			}
		});
		thread_1.start();
		thread_2.start();
		thread_1.join();
		thread_2.join();
		System.out.println("coord x:" + coord.x + ";y:" + coord.y);
		System.out.println("time: " + (System.currentTimeMillis() - start));

	}

	/**
	 * 坐标类
	 */
	static class Coord {
//		@Contended
		 volatile long x;
		//		long p1, p2, p3, p4, p5, p6, p7;
//		@Contended
		volatile long y;
	}
}
