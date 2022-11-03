package com.bob.cas.bean;

import com.bob.factory.UnsafeFactory;
import sun.misc.Unsafe;

/**
 * @Author wangbo
 * @Date 2022/10/28 16:53
 * @Description: CAS工具类
 * @Version 1.0
 * <p>
 * 需求:
 * 1.提供无锁架构实现原子性
 * 方案:
 * 1.提供标志位
 * 2.使用偏移量来改变标志位
 */
public class CasLock {

	private volatile int status;

	/**
	 * 锁的状态
	 */
	private static final int STATUS_LOCK = 1;
	/**
	 * 无锁状态
	 */
	private static final int STATUS_LOCK_FREE = 0;

	/**
	 * 加锁
	 *
	 * @return true 加锁成功 false 失败
	 */
	public boolean lock() {
		return assignment(STATUS_LOCK_FREE, STATUS_LOCK);
	}


	/**
	 * 解锁
	 */
	public boolean unLock() {
		if (this.status == STATUS_LOCK_FREE) {
			return true;
		}
		while (status == STATUS_LOCK_FREE || assignment(STATUS_LOCK, STATUS_LOCK_FREE)) {
			return true;
		}
		return true;
	}

	/**
	 * 获取锁的状态
	 *
	 * @return true 有锁 false 无锁
	 */
	public boolean isLock() {
		return this.status == STATUS_LOCK;
	}

	/**
	 * 赋值
	 *
	 * @param source 源
	 * @param target 目标
	 * @return 赋值成功 true 失败 false
	 */
	private boolean assignment(int source, int target) {
		try {
			Unsafe unsafe = UnsafeFactory.getUnsafe();
			Class clazz = CasLock.class;
			long count = unsafe.objectFieldOffset(clazz.getDeclaredField("status"));
			return unsafe.compareAndSwapInt(this, count, source, target);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return false;
	}

}
