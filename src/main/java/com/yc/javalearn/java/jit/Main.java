package com.yc.javalearn.java.jit;

/**
 * FileName: Main
 *  jvm
 *  -Xint
 *  -XX:+PrintCompilation
 * @author: yuchao
 * @date: 2018/11/2
 */
public class Main extends Thread {

    private volatile static boolean flag = false;

    @Override
    public void run() {
        while (!flag) {

        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        try {
            m.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }
}
