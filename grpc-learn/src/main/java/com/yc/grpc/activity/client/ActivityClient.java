package com.yc.grpc.activity.client;

import com.yc.grpc.activity.ActivityRequest;
import com.yc.grpc.activity.ActivityServiceGrpc;
import com.yc.grpc.activity.Result;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.java.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.stream.LongStream;

/**
 * FileName: ActivityClient
 *
 * @author: yuchao
 * @date: 2018/9/13
 */
@Log
public class ActivityClient {

    private ManagedChannel channel;
    private ActivityServiceGrpc.ActivityServiceStub asyncStub;


    public ActivityClient(String host, int port){
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        asyncStub = ActivityServiceGrpc.newStub(channel);
    }

    public void createBatchActivity() throws InterruptedException {
        final CountDownLatch finishLatch = new CountDownLatch(1);
        StreamObserver<ActivityRequest> requestStreamObserver = asyncStub.createActivity(new StreamObserver<Result>() {
            @Override
            public void onNext(Result value) {
                log.log(Level.INFO, "onNext");
            }

            @Override
            public void onError(Throwable t) {
                log.log(Level.WARNING, "onError", t);
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                log.log(Level.INFO, "onCompleted");
                finishLatch.countDown();
            }
        });

        LongStream.range(1L, 10L).forEach(id -> requestStreamObserver.onNext(createActivity(id, finishLatch)));
        requestStreamObserver.onCompleted();
        if (!finishLatch.await(1, TimeUnit.MINUTES)) {
            log.log(Level.WARNING, "time out");
        }
    }

    private ActivityRequest createActivity(Long id, CountDownLatch finishLatch){
        if (finishLatch.getCount() == 0) {
            return null;
        }

        System.out.println("拼装活动"+id);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
        }

        return ActivityRequest.newBuilder().setId(id).setName("活动"+id).build();
    }

    public static void main(String[] args) throws InterruptedException {
        new ActivityClient("localhost", 40003).createBatchActivity();
    }

}
