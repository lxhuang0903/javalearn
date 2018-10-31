package com.yc.grpc.helloworld.client;

import com.yc.grpc.helloworld.GreeterGrpc;
import com.yc.grpc.helloworld.HelloReply;
import com.yc.grpc.helloworld.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * FileName: HelloWorldClient
 *
 * @author: yuchao
 * @date: 2018/9/7
 */
public class HelloWorldClient {

    private final ManagedChannel channel;
    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    public HelloWorldClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext().build());
    }

    HelloWorldClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public String great(String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply helloReply = blockingStub.sayHello(request);
        return helloReply.getMessage();
    }

    public static void main(String[] args) throws InterruptedException {
        HelloWorldClient client = new HelloWorldClient("172.16.71.4", 50005);
        try {
            System.out.println(client.great("world"));
        } finally {
            client.shutdown();
        }
    }

}
