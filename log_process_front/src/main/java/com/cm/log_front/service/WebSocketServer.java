package com.cm.log_front.service;

import com.cm.encoder.LogEncoder;
import com.cm.entity.Log;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@EnableWebSocket
@ServerEndpoint(value = "/websocket",encoders = {LogEncoder.class})
public class WebSocketServer {

    private static CopyOnWriteArraySet<WebSocketServer> webSocketServers =
            new CopyOnWriteArraySet<>();
    private Session session;

    private static List<Session> sessions = new ArrayList<>();

    public static List<Session> getSessions() {
        return sessions;
    }

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        session.setMaxIdleTimeout(1000000);
        sessions.add(session);
        CloseableHttpResponse response = null;
        try {
            CloseableHttpClient httpClient =HttpClientBuilder.create().build();
            //构建请求体
            HttpGet httpGet = new HttpGet("http://localhost:9002/push/true");
            //执行请求
            httpClient.execute(httpGet);
            } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("有一个客户端已经连接上");
    }

    @OnMessage
    public void onMessage(String msg){
        System.out.println("接收到前端发来的消息 = " + msg);
    }

    @OnClose
    public void onClose(){
        webSocketServers.remove(this);
        System.out.println("有一个连接已经关闭");
    }

    public void sendMsg(List<Log> msg, Session session) throws IOException {
        if(session!=null){
            try {
                for (Log log : msg) {
                    session.getBasicRemote().sendObject(log);
                }
            } catch (EncodeException e) {
                e.printStackTrace();
            }
        }
    }
}
