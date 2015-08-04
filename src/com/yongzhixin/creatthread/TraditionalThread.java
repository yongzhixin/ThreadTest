package com.yongzhixin.creatthread;

public class TraditionalThread {

	public static void main(String[] args) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("第一个thread : "
							+ Thread.currentThread().getName());
				}
			}
		};
		thread.start();
		
		Thread thread2=new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("第二个thread : "
							+ Thread.currentThread().getName());
				}
			}
		});
		thread2.start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("第三个runnable : "
							+ Thread.currentThread().getName());
				}
			}
		}){
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("第三个thread : "
							+ Thread.currentThread().getName());
				}
			};
		}.start();
	}
}
