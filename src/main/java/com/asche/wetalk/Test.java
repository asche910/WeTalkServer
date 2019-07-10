package com.asche.wetalk;


import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;

import static com.asche.wetalk.util.PrintUtils.println;

public class Test {

    public static void main(String[] args) {
        System.out.println("Test Start:...");


        MyRunnable runnable = new MyRunnable();

        Thread thread = new Thread(runnable, "MyThread-0");
        thread.start();

        try {
            Thread.sleep(500_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        runnable.setFlag(false);
    }
}

class MyRunnable implements Runnable{

    private volatile boolean flag = true;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        println(Thread.currentThread().getName() + " start!");

        while (flag){
            println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        println(Thread.currentThread().getName() + " finished!");
    }
}
