package com.yongzhixin.creatthread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheDemo {
	private Map<String, Object> cache = new HashMap<String, Object>();

	public static void main(String[] args) {

	}

	private ReadWriteLock rwl = new ReentrantReadWriteLock();

	public Object getData(String key) {
		rwl.readLock().lock();
		Object value = null;
		try {
			value = cache.get(key);
			if (value == null) {
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				try {
					if (value == null) {
						value = "赋值";
					}
				} finally {
					rwl.writeLock().unlock();
				}
				rwl.readLock().lock();
			}
		} finally {
			rwl.readLock().unlock();
		}
		return value;
	}

}
