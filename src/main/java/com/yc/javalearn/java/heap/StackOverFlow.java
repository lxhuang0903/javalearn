package com.yc.javalearn.java.heap;

/**
 * FileName: StackOverFlow
 *
 * @author: yuchao
 * @date: 2018/11/11
 */
public class StackOverFlow {

    private int i;

    public void plus() {
        i++;

        plus();
    }

    public static void main(String[] args) {
        new StackOverFlow().plus();
    }

}
