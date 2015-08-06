package com.yongzhixin.creatthread;

import java.util.Random;

public class ThreadLocalTest {

	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName()
							+ " has put data " + data);
					threadLocal.set(data);
					MyThreadData.getThreadInstance().setName("name" + data);
					MyThreadData.getThreadInstance().setAge(data);
					new A().get();
					new B().get();
				}
			}).start();
		}
	}

	static class A {
		public void get() {
			int data = threadLocal.get();
			System.out.println("A from " + Thread.currentThread().getName()
					+ " get data " + data);
			MyThreadData myThreadData = MyThreadData.getThreadInstance();
			System.out.println("A from " + Thread.currentThread().getName()
					+ " get Mydata " + myThreadData.getName() + ","
					+ myThreadData.getAge());
		}
	}

	static class B {
		public void get() {
			int data = threadLocal.get();
			System.out.println("B from " + Thread.currentThread().getName()
					+ " get data " + data);
			MyThreadData myThreadData = MyThreadData.getThreadInstance();
			System.out.println("B from " + Thread.currentThread().getName()
					+ " get Mydata " + myThreadData.getName() + ","
					+ myThreadData.getAge());
		}
	}

}

class MyThreadData {
	// private static MyThreadData instance = null;// new MyThreadData();
	private String name;
	private Integer age;
	private static ThreadLocal<MyThreadData> threadLocal = new ThreadLocal<MyThreadData>();

	private MyThreadData() {
	}

	public static/* synchronized */MyThreadData getThreadInstance() {
		MyThreadData instance = threadLocal.get();
		if (instance == null) {
			instance = new MyThreadData();
			threadLocal.set(instance);
		}
		return instance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
