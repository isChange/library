package com.igeek.library.config;

public class RunA implements Runnable{
    @Override
    public void run() {
        System.out.println("我被定时执行了");
    }
}
