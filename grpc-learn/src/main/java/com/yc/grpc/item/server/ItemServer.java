package com.yc.grpc.item.server;

import com.yc.grpc.item.Empty;
import com.yc.grpc.item.ItemDTO;
import com.yc.grpc.item.ItemServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * FileName: ItemServer
 *
 * @author: yuchao
 * @date: 2018/9/13
 */
public class ItemServer {

    private Server server;

    public void start() throws IOException {
        int port = 50006;
        server = ServerBuilder.forPort(port).addService(new ItemServiceImpl()).build().start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> ItemServer.this.stop()));
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    class ItemServiceImpl extends ItemServiceGrpc.ItemServiceImplBase{

        @Override
        public void searchAllItems(Empty request, StreamObserver<ItemDTO> responseObserver) {
            queryFromDB().stream().forEach(item -> {
                        try {
                            Thread.sleep(3000L);
                        } catch (InterruptedException e) {
                        }
                        responseObserver.onNext(item);

                    }
            );
            responseObserver.onCompleted();
        }

        private List<ItemDTO> queryFromDB() {
            return Arrays.asList(
                    ItemDTO.newBuilder().setItemId(1L).setItemName("商品1").build(),
                    ItemDTO.newBuilder().setItemId(2L).setItemName("商品2").build(),
                    ItemDTO.newBuilder().setItemId(3L).setItemName("商品3").build(),
                    ItemDTO.newBuilder().setItemId(4L).setItemName("商品4").build(),
                    ItemDTO.newBuilder().setItemId(5L).setItemName("商品5").build()
            );
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ItemServer itemServer = new ItemServer();
        itemServer.start();
        itemServer.blockUntilShutdown();
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

}
