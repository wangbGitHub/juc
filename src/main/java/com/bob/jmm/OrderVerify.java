package com.bob.jmm;

/**
 * @Author wangbo
 * @Date 2022/10/25 15:26
 * @Description: 有续性验证
 * @Version 1.0
 */
public class OrderVerify {

	private static int CONSTANT = 5;

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 1_000_000; i++) {
			CONSTANT = 5;
			Thread thread_1 = new Thread(() -> {
				CONSTANT = CONSTANT + 8;
			});
			Thread thread_2 = new Thread(() -> {
				CONSTANT = CONSTANT + 4;
			});
			thread_1.start();
			thread_2.start();
//			thread_1.join();
//			thread_2.join();
			if (CONSTANT != 17){
				System.out.println(CONSTANT );
			}
		}
	}
}
