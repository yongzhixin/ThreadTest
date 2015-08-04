package com.yongzhixin.creatthread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TraditionalTimer {
	private static int count = 0;
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		/*new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("定时器触发...");
			}
		//}, 5000);
		}, 5000,3000);*/
		
		class MyTimerTask extends TimerTask{
			@Override
			public void run() {
				count++;
				count = count % 2;
				System.out.println("定时器触发...");
				new Timer().schedule(new MyTimerTask(), 2000 + 2000*count);
			}
		}
		
		new Timer().schedule(new MyTimerTask(), 2000);
		
		while(true){
			System.out.println(new Date().getSeconds());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
