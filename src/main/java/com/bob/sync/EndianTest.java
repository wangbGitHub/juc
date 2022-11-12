package com.bob.sync;

import com.bob.factory.UnsafeFactory;
import sun.misc.Unsafe;

import java.nio.ByteOrder;

/**
 * @Author wangbo
 * @Date 2022/11/7 21:27
 * @Description: TODO
 * @Version 1.0
 */
public class EndianTest {
	public static void main(String[] args) {
		Unsafe unsafe = UnsafeFactory.getUnsafe();
		long a = unsafe.allocateMemory(8);
		try {
			unsafe.putLong(a, 0x0102030405060708L);
			byte b = unsafe.getByte(a);
			ByteOrder byteOrder;
			switch (b) {
				case 0x01: byteOrder = ByteOrder.BIG_ENDIAN;     break;
				case 0x08: byteOrder = ByteOrder.LITTLE_ENDIAN;  break;
				default:
					byteOrder = null;
			}
			System.out.println(byteOrder);
		} finally {
			unsafe.freeMemory(a);
		}

	}
}

