package com.cm.encoder;

import com.cm.entity.Log;
import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class LogEncoder implements Encoder.Text<Log> {
    @Override
    public String encode(Log log) throws EncodeException {
        Gson gson = new Gson();
        return gson.toJson(log);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        System.out.println("初始化encoder");
    }

    @Override
    public void destroy() {
       System.out.println("销毁encoder");
    }
}
