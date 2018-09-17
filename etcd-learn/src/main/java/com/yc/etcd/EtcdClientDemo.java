package com.yc.etcd;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutionException;

/**
 * FileName: EtcdClientDemo
 *
 * @author: yuchao
 * @date: 2018/9/4
 */
public class EtcdClientDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, NoSuchFieldException {
//        Client client = Client.builder().endpoints("http://localhost:2379").build();
//        KV kvClient = client.getKVClient();
//
//        ByteSequence key = ByteSequence.fromString("message1");
//        ByteSequence value = ByteSequence.fromString("test_value");

// put the key-value
//        kvClient.put(key, value);

// get the CompletableFuture
//        CompletableFuture<GetResponse> getFuture = kvClient.get(key);

// get the value from CompletableFuture
//        GetResponse response = getFuture.get();
//        System.out.println(response.getKvs().size());
//        System.out.println(response.getKvs());
//        System.out.println(response.toString());
// delete the key
//        kvClient.delete(key).get();


        Class<Demo> clazz = Demo.class;
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println(field.getGenericType());
        }
    }
}
