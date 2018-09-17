package com.yc.javalearn.java8;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * FileName: InstantDemo
 *
 * @author: yuchao
 * @date: 2018/9/3
 */
public class InstantDemo {
    public static void main(String[] args) {
//        Instant now = Instant.now();
//        System.out.println(now.toEpochMilli());
//        System.out.println(System.currentTimeMillis());
//        System.out.println(LocalDateTime.now().atZone(ZoneOffset.UTC).toInstant().toEpochMilli());
//        System.out.println(LocalDateTime.now().atZone(ZoneOffset.of("+3")).toInstant().toEpochMilli());
//        System.out.println(System.currentTimeMillis());
//        System.out.println(LocalDateTime.now().atZone(ZoneOffset.UTC).toInstant().toEpochMilli());

        List<String> list = Arrays.asList("a", "b", "c");

        IntStream.range(0, list.size()).boxed().peek(System.out::println).collect(Collectors.toMap(index -> list.get(index), Function.identity()));

//        list.stream().collect(Collectors.toMap(Function.identity(), e -> list.indexOf(e)));
    }
}
