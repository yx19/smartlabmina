package com.smartlab.yxmina.inter;

public class SendListenerImpl implements SendListener {
    @Override
    public boolean SendSuccess() {
        return false;
    }

    @Override
    public boolean SendFail(Exception exception) {
        return false;
    }
}
