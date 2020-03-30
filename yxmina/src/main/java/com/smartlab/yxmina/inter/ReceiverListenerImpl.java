package com.smartlab.yxmina.inter;

public class ReceiverListenerImpl implements ReceiveListener {

    @Override
    public boolean ReceiverData(String data) {
        return false;
    }

    @Override
    public boolean ReceiverFail(Error error) {
        return false;
    }
}
