package com.cm.realtime_push.dao;

import com.cm.entity.Log;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PushDao extends ElasticsearchRepository<Log,String> {
}
