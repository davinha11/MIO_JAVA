package com.davena.exercises;

public class Test_Thread {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> System.out.println(String.format("Hello, i'm %s", Thread.currentThread().getName())) );
        t.start();
        t.join();

        System.out.println(Thread.currentThread().getName());

    }

}
