package com.smartlab.yxmina.inter;

/**
 * 发送的监听器
 */
public interface SendListener {

    boolean SendSuccess();

    boolean SendFail(Exception exception);

}
