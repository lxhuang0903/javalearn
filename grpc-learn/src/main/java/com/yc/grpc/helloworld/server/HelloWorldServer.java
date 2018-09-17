package com.yc.grpc.helloworld.server;

import com.yc.grpc.helloworld.GreeterGrpc;
import com.yc.grpc.helloworld.HelloReply;
import com.yc.grpc.helloworld.HelloRequest;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

/**
 * FileName: HelloWorldServer
 *
 * @author: yuchao
 * @date: 2018/9/7
 */
public class HelloWorldServer {

    private Server server;

    private void start() throws IOException {
        int port = 50005;
        server = ServerBuilder.forPort(port).addService(new GreeterImpl()).build().start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> HelloWorldServer.this.stop()));

    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    class GreeterImpl extends GreeterGrpc.GreeterImplBase{
        @Override
        public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
            HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();
            responseObserver.onNext(reply);
//            responseObserver.onNext(reply);
//            responseObserver.onError(new RuntimeException("xxxxx error"));
            responseObserver.onCompleted();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HelloWorldServer helloWorldServer = new HelloWorldServer();
        helloWorldServer.start();
        helloWorldServer.blockUntilShutdown();
    }
}
