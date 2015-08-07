package com.yongzhixin.creatthread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
		for (int i = 0; i < 10; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep((long) Math.random() * 10000);
						System.out
								.println("线程"
										+ Thread.currentThread().getName()
										+ "即将到达地点1,当前已有"
										+ (cyclicBarrier.getNumberWaiting() + 1)
										+ "个已经到达,"
										+ (cyclicBarrier.getNumberWaiting() == 9 ? "都到齐了,继续走"
												: "正在等候"));
						cyclicBarrier.await();

						Thread.sleep((long) Math.random() * 10000);
						System.out
								.println("线程"
										+ Thread.currentThread().getName()
										+ "即将到达地点2,当前已有"
										+ (cyclicBarrier.getNumberWaiting() + 1)
										+ "个已经到达,"
										+ (cyclicBarrier.getNumberWaiting() == 9 ? "都到齐了,继续走"
												: "正在等候"));
						cyclicBarrier.await();

						Thread.sleep((long) Math.random() * 10000);
						System.out
								.println("线程"
										+ Thread.currentThread().getName()
										+ "即将到达地点3,当前已有"
										+ (cyclicBarrier.getNumberWaiting() + 1)
										+ "个已经到达,"
										+ (cyclicBarrier.getNumberWaiting() == 9 ? "都到齐了,继续走"
												: "正在等候"));
						cyclicBarrier.await();
					} catch (Exception e) {

					}
				}
			};
			threadPool.execute(runnable);
		}
		threadPool.shutdown();
	}
}
