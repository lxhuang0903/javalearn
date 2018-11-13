package com.yc.javalearn.java.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: ConstantOutOfMemory
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 * ignoring option PermSize=10M; support was removed in 8.0
 * -XX:MetaspaceSize=1M -XX:MaxMetaspaceSize=1M
 * @author: yuchao
 * @date: 2018/11/11
 */
public class ConstantOutOfMemory {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
