package com.cm.log_front.controller;

import com.cm.entity.Log;
import com.cm.log_front.service.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.Session;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("front")
public class FrontController {


    @Autowired
    private WebSocketServer webSocketServer;

    @PostMapping("push")
    public void pushInfo(@RequestBody List<Log> logs){
        System.out.println("logs = " + logs);
        List<Session> sessions = WebSocketServer.getSessions();
        if(sessions.size()>0){
            Session session = sessions.get(0);
            try {
                webSocketServer.sendMsg(logs,session);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
