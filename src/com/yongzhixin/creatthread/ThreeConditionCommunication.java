package com.yongzhixin.creatthread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreeConditionCommunication {

	public static void main(String[] args) {
		ThreeConditionCommunication traditionalThreadCommunication = new ThreeConditionCommunication();
		final Business business = traditionalThreadCommunication.new Business();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					business.sub2(i);
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					business.sub3(i);
				}
			}
		}).start();
		for (int i = 1; i <= 50; i++) {
			business.main(i);
		}
	}

	class Business {
		private int issub = 1;
		private Lock lock = new ReentrantLock();
		private Condition condition = lock.newCondition();
		private Condition condition2 = lock.newCondition();
		private Condition condition3 = lock.newCondition();

		public void sub2(int i) {
			lock.lock();
			try {
				while (issub != 2) {
					try {
						condition2.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("sub2 thread sequece of " + j
							+ ",loop of " + i);
				}
				issub = 3;
				condition3.signal();
			} finally {
				lock.unlock();
			}
		}

		public void sub3(int i) {
			lock.lock();
			try {
				while (issub != 3) {
					try {
						condition3.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("sub3 thread sequece of " + j
							+ ",loop of " + i);
				}
				issub = 1;
				condition.signal();
			} finally {
				lock.unlock();
			}
		}

		public void main(int i) {
			lock.lock();
			try {
				while (issub != 1) {
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
				issub = 2;
				condition2.signal();
			} finally {
				lock.unlock();
			}
		}
	}

}
