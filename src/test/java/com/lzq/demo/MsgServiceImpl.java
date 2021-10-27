package com.lzq.demo;

public class MsgServiceImpl implements MsgService{
    @Override
    public void sendMsg(String msg) {
        System.out.println("send Msg now ! " + msg);
    }
}
