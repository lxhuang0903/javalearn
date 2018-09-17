package com.yc.grpc.item.client;

import com.yc.grpc.item.Empty;
import com.yc.grpc.item.ItemDTO;
import com.yc.grpc.item.ItemServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

/**
 * FileName: ItemClient
 *
 * @author: yuchao
 * @date: 2018/9/13
 */
public class ItemClient {

    private ManagedChannel channel;
    private ItemServiceGrpc.ItemServiceBlockingStub blockingStub;


    public ItemClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        blockingStub = ItemServiceGrpc.newBlockingStub(channel);
    }

    public void searchAllItem() {
        Empty empty = Empty.newBuilder().build();
        Iterator<ItemDTO> iterator = blockingStub.searchAllItems(empty);
        for (int i = 0; iterator.hasNext(); i++) {
            System.out.println(iterator.next());
        }
    }

    public static void main(String[] args) {
        new ItemClient("localhost", 50006).searchAllItem();
    }

}
