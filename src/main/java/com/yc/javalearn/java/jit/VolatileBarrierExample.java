package com.yc.javalearn.java.jit;

/**
 * FileName: VolatileBarrierExample
 *
 * @author: yuchao
 * @date: 2018/11/3
 */
public class VolatileBarrierExample {
    long a;
    volatile long v1 = 1;
    volatile long v2 = 1;

    void readAndWrite() {
        long j = v1;
        long i = v2;
        a = i + j;
        v1 = i + 1;
        long v = v1;
        v2 = j * 2;
    }

    public static void main(String[] args) {
        final VolatileBarrierExample ex = new VolatileBarrierExample();
        for (int i = 0; i < 50000; i++){
            ex.readAndWrite();
        }
    }
}

