package com.cm.realtime_push.service;

import com.cm.entity.Log;

import java.util.List;

public interface PushService {
    List<Log> getAll();

    void saveLog(Log log);
}
