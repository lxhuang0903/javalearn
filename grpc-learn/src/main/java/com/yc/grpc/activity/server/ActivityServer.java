package com.yc.grpc.activity.server;

import com.yc.grpc.activity.ActivityRequest;
import com.yc.grpc.activity.ActivityServiceGrpc;
import com.yc.grpc.activity.Result;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: ActivityServer
 *
 * @author: yuchao
 * @date: 2018/9/13
 */
public class ActivityServer {

    Server server;

    public void start() throws IOException {
        int port = 40003;
        server = ServerBuilder.forPort(port).addService(new ActivityServiceImpl()).build().start();
        Runtime.getRuntime().addShutdownHook(new Thread(()-> ActivityServer.this.stop()));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    class ActivityServiceImpl extends ActivityServiceGrpc.ActivityServiceImplBase{
        @Override
        public StreamObserver<ActivityRequest> createActivity(StreamObserver<Result> responseObserver) {
            List<ActivityRequest> list = new ArrayList<>();
            return new StreamObserver<ActivityRequest>(){
                @Override
                public void onNext(ActivityRequest activityRequest) {
                    System.out.println("一个请求对象艰难的到达,预处理下");
                    preHandle(activityRequest);
                    list.add(activityRequest);
                    if (list.size() == 9) {
                        try {
                            System.out.println("延迟最后一个请求处理时长");
                            Thread.sleep(3000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onError(Throwable t) {
                    System.out.println("出错了");
                }

                @Override
                public void onCompleted() {
                    System.out.println("所有请求都到达,可以开工,事务搞起来");
                    doTransaction(list);
                }
            };
        }

        private void preHandle(ActivityRequest request) {
            System.out.println("各种复杂的校验逻辑....");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
        }

        private void doTransaction(List<ActivityRequest> list) {
            list.forEach(System.out::println);
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        ActivityServer activityServer = new ActivityServer();
        activityServer.start();
        activityServer.blockUntilShutdown();
    }
}
