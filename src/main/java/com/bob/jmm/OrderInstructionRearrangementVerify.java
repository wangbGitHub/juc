package com.bob.jmm;

/**
 * @Author wangbo
 * @Date 2022/10/25 16:28
 * @Description: 有序性指令重排
 * @Version 1.0
 * <p>
 * 以下结果可以出现
 * 0,0
 * 1,0
 * 0,1
 * 1,1
 */
public class OrderInstructionRearrangementVerify {

	private static int x = 0, y = 0;

	private static int a = 0, b = 0;

	public static void main(String[] args) throws InterruptedException {
		int i = 0;
		while (true) {
			i++;
			x = 0;
			y = 0;
			a = 0;
			b = 0;
			/**
			 *  x,y:
			 */
			Thread thread1 = new Thread(() -> {
				a = 1;
				x = b;
			});
			Thread thread2 = new Thread(() -> {
				b = 1;
				y = a;
			});
			thread1.start();
			thread2.start();
			thread1.join();
			thread2.join();

			System.out.println("第" + i + "次（" + x + "," + y + ")");

			if (x == 0 && y == 0) {
				break;
			}

		}

	}

}
