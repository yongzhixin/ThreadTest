package com.yongzhixin.creatthread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLockTest {

	public static void main(String[] args) {
		// 非静态内部类创建
		// TraditionalThreadSynchronize traditionalThreadSynchronize = new
		// TraditionalThreadSynchronize();
		// final Outputer outputer = traditionalThreadSynchronize.new
		// Outputer();
		final Outputer outputer = new ThreadLockTest.Outputer();// 静态内部类创建
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output("yongzhixin");
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output("baiducom");
				}
			}
		}).start();

	}

	static class Outputer {
		Lock lock = new ReentrantLock();

		public void output(String msg) {
			int len = msg.length();
			// synchronized (this) {// 同一对象锁
			// synchronized (Outputer.class) {// 与output3同步
			lock.lock();
			try {
				for (int i = 0; i < len; i++) {
					System.out.print(msg.charAt(i));
				}
				System.out.println();
			} finally {
				lock.unlock();
			}
			// }
		}

		public synchronized void output2(String msg) {
			int len = msg.length();
			for (int i = 0; i < len; i++) {
				System.out.print(msg.charAt(i));
			}
			System.out.println();
		}

		public static synchronized void output3(String msg) {
			int len = msg.length();
			for (int i = 0; i < len; i++) {
				System.out.print(msg.charAt(i));
			}
			System.out.println();
		}
	}
}
