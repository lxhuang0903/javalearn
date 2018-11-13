package com.yc.javalearn.java.heap;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * FileName: DirectoryMemoryOutOfmemory
 * -Xmx20M -XX:MaxDirectMemorySize=10M
 * MaxDirectMemorySize not work???
 * @author: yuchao
 * @date: 2018/11/11
 */
public class DirectoryMemoryOutOfmemory {
    private static final int ONE_MB = 1024*1024;

    private static int count = 1;


    public static void main(String[] args) {
        try {

            Field field = Unsafe.class.getDeclaredField("theUnsafe");

            field.setAccessible(true);

            Unsafe unsafe = (Unsafe) field.get(null);

            while (true) {

                unsafe.allocateMemory(ONE_MB);

                count++;

            }

        } catch (Exception e) {

            System.out.println("Exception:instance created "+count);

            e.printStackTrace();

        } catch (Error e) {

            System.out.println("Error:instance created "+count);

            e.printStackTrace();

        }
    }
}
