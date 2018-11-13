package com.yc.javalearn.java.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: HeapOutOfMemoryErrorDemo
 * -verbose:gc -Xms20M -Xmx20M -XX:+PrintGCDetails
 * java.lang.OutOfMemoryError: Java heap space
 * @author: yuchao
 * @date: 2018/11/11
 */
public class HeapOutOfMemoryErrorDemo {

    public static void main(String[] args) {
        List<Test> list = new ArrayList<>();
        while (true) {
            list.add(new Test());
        }
    }


    static class Test {
    }

}
