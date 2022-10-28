package com.bob.factory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author wangbo
 * @Date 2022/10/28 16:49
 * @Description: Unsafe工厂
 * @Version 1.0
 */
public class UnsafeFactory {

	/**
	 * 获取 Unsafe 对象
	 *
	 * @return 结果集
	 */
	public static Unsafe getUnsafe() {
		try {
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			return (Unsafe) field.get(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
