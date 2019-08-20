//package com.lanfangyi.nettyim.handler;
//
//import java.nio.ByteBuffer;
//import java.nio.channels.AsynchronousSocketChannel;
//import java.nio.channels.CompletionHandler;
//
//public class WriteHandler implements CompletionHandler<Integer, ByteBuffer> {
//
//    private AsynchronousSocketChannel asc;
//
//    public WriteHandler(AsynchronousSocketChannel asc) {
//        this.asc = asc;
//    }
//
//    /**
//     * 写出成功则触发该方法
//     * @param result 已发送数据的大小
//     * @param buffer
//     */
//    @Override
//    public void completed(Integer result, ByteBuffer buffer) {
//        if (!asc.isOpen()) {
//            System.out.println("连接已断开");
//        }
//        System.out.println("写出成功");
//        //还没发完，继续发
//        if( buffer.hasRemaining()){
//            //使用this，不会有线程安全问题，不是递归
//            asc.write(buffer, buffer, this);
//        }
//
//    }
//
//    /**
//     * 写出失败将触发该方法
//     * @param exc
//     * @param buffer
//     */
//    @Override
//    public void failed(Throwable exc, ByteBuffer buffer) {
//        System.out.println("写出失败");
//    }
//}
