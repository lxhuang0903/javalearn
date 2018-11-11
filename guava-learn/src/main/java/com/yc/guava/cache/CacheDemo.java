package com.yc.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * FileName: CacheDemo
 *
 * @author: yuchao
 * @date: 2018/11/1
 */
public class CacheDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LoadingCache<Long, User> cache = CacheBuilder.newBuilder()
                .maximumSize(3L)
                .expireAfterWrite(2000L, TimeUnit.MILLISECONDS)
                .removalListener(notification -> System.out.println("remove key="+notification.getKey()))
                .build(new CacheLoader<Long, User>() {
                    @Override
                    public User load(Long key) {
                        User user = new User();
                        user.setUserId(key);
                        user.setName("user" + key);
                        return user;
                    }
                });

        User user = cache.get(2L);
        System.out.println(user);
        System.out.println(cache.size());
        Thread.sleep(5000L);
        User user1 = cache.get(3L);
        User user2 = cache.get(4L);
        System.out.println(cache.size());
        System.out.println(user1);
        System.out.println(user2);
    }
}
