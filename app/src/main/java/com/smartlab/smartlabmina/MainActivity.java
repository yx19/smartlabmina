package com.smartlab.smartlabmina;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;


import com.smartlab.yxmina.config.Config;
import com.smartlab.yxmina.inter.ConnectListener;
import com.smartlab.yxmina.inter.KeepAliveListener;
import com.smartlab.yxmina.inter.ReceiveListener;
import com.smartlab.yxmina.mina.ConnectManager;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new mina().start();
    }

    class mina extends Thread {
        @Override
        public void run() {
            super.run();
            Config config = Config.newInstance();
            config.setIp("192.168.0.102");//设置服务器ip
            config.setPort(8080);//设置服务器端口 " +
            config.setHeartRequest("0x11");//设置发送给服务器的心跳请求包
            config.setHeartResponse("0x12");//设置接收服务器返回的心跳响应包
            config.setkeepalive(true);//设置支持⻓链接
            config.setreconnect(true);
            config.setHeartFrequency(5);//设置心跳包发送间隔单位秒
            config.setHearttimeout(3);//设置心跳超时判断时间单位秒
            config.setReconnectFrequency(5000);//设置重连的时间间隔单位毫秒
            config.setConnectListener(new ConnectListener() {
                @Override
                public boolean ConnectSuccess(IoSession ioSession) {
                    return false;
                }

                @Override
                public boolean ConnectFail(Exception e) {
                    return false;
                }

                @Override
                public boolean DisConnect(IoSession ioSession) {
                    return false;
                }
            });
            config.setReceiveListener(new ReceiveListener() {//设置数据接收监听
                @Override
                public boolean ReceiverData(String data) {//接收数据后执行
                    System.out.println(data);
                    return false;
                }

                @Override
                public boolean ReceiverFail(Error error) {//接收数据出现异常时调用
                    return false;
                }
            });
            config.setKeepAliveListener(new KeepAliveListener() {//设置心跳机制监听器
                @Override
                public void BeforeSendHeartbeat(IoSession arg0) {
                }

                @Override
                public void AfterReceiverHeartbeat(IoSession arg0, Object arg1) {
                }

                @Override
                public void HeartbeatTimeOut(KeepAliveFilter arg0, IoSession arg1) {
                }
            });

            final ConnectManager connectManager = ConnectManager.newInstance();
            connectManager.creatConfig(config);//为连接管理传入mina的配置信息
            connectManager.connect();//开启连接
        }
    }

}
