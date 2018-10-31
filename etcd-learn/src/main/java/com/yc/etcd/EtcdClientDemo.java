package com.yc.etcd;

import com.coreos.jetcd.Client;
import com.coreos.jetcd.KV;
import com.coreos.jetcd.data.ByteSequence;
import com.coreos.jetcd.kv.GetResponse;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * FileName: EtcdClientDemo
 *
 * @author: yuchao
 * @date: 2018/9/4
 */
public class EtcdClientDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Client client = Client.builder().endpoints("http://localhost:2379").build();
        KV kvClient = client.getKVClient();

        ByteSequence key = ByteSequence.fromString("message1");
        ByteSequence value = ByteSequence.fromString("test_value");

        kvClient.put(key, value);

        CompletableFuture<GetResponse> getFuture = kvClient.get(key);

        GetResponse response = getFuture.get();

        System.out.println(response.getKvs().size());
        System.out.println(response.getKvs());
        System.out.println(response.toString());
    }
}
