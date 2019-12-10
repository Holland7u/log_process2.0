package com.cm.realtime_push.service.impl;

import com.cm.entity.Log;
import com.cm.realtime_push.dao.PushDao;
import com.cm.realtime_push.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasePushServiceImpl implements PushService {


    @Autowired
    private PushDao pushDao;

    @Override
    public List<Log> getAll() {
        Iterable<Log> all = pushDao.findAll();
        List<Log> logs = new ArrayList<>();
        all.forEach(logs::add);
        return logs;
    }

    @Override
    public void saveLog(Log log) {
        pushDao.save(log);
    }
}
