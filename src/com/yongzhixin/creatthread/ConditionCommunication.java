package com.yongzhixin.creatthread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionCommunication {

	public static void main(String[] args) {
		ConditionCommunication traditionalThreadCommunication = new ConditionCommunication();
		final Business business = traditionalThreadCommunication.new Business();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					business.sub(i);
				}
			}
		}).start();
		for (int i = 1; i <= 50; i++) {
			business.main(i);
		}
	}

	class Business {
		private boolean issub = true;
		private Lock lock = new ReentrantLock();
		private Condition condition = lock.newCondition();

		public void sub(int i) {
			lock.lock();
			try {
				while (!issub) {
					try {
						condition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("sub thread sequece of " + j
							+ ",loop of " + i);
				}
				issub = false;
				condition.signal();
			} finally {
				lock.unlock();
			}
		}

		public void main(int i) {
			lock.lock();
			try {
				while (issub) {
					try {
						condition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 20; j++) {
					System.out.println("main thread sequece of " + j
							+ ",loop of " + i);
				}
				issub = true;
				condition.signal();
			} finally {
				lock.unlock();
			}
		}
	}

}
