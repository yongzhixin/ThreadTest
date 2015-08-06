package com.yongzhixin.creatthread;

public class TraditionalThreadCommunication {

	public static void main(String[] args) {
		TraditionalThreadCommunication traditionalThreadCommunication = new TraditionalThreadCommunication();
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

		public synchronized void sub(int i) {
			while (!issub) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int j = 1; j <= 10; j++) {
				System.out.println("sub thread sequece of " + j + ",loop of "
						+ i);
			}
			issub = false;
			this.notify();
		}

		public synchronized void main(int i) {
			while (issub) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int j = 1; j <= 20; j++) {
				System.out.println("main thread sequece of " + j + ",loop of "
						+ i);
			}
			issub = true;
			this.notify();
		}
	}

}
