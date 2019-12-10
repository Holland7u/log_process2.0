package com.cm.realtime_push.controller;

import com.alibaba.fastjson.JSON;
import com.cm.config.ConfigBean;
import com.cm.entity.Log;
import com.cm.entity.Result;
import com.cm.entity.StatusCode;
import com.cm.realtime_push.service.PushService;
import com.cm.util.LogUtil;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("push")
public class PushController {

    @Autowired
    private PushService pushService;


    @GetMapping("/{flag}")
    public Result doConnect(@PathVariable String flag){
        LogUtil logUtil = new LogUtil();
        //得到数据
        List<Log> logs = logUtil.analysisLog();
        //创建请求参数
        String logstr = JSON.toJSONString(logs);
        //创建请求客户端
        CloseableHttpClient client = HttpClientBuilder.create().build();
        //构建post请求体
        HttpPost httpPost = new HttpPost("http://localhost:9001/front/push");
        //设置请求头
        httpPost.setHeader("Content-Type","application/json");
        //构建请求参数
        StringEntity entity = new StringEntity(logstr,"UTF-8");
        //设置请求参数
        httpPost.setEntity(entity);
        try {
            //发送post请求
            client.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.SUCCESS,"连接成功");
    }

    @GetMapping
    public Result getAll(){
        try{
            List<Log> log = pushService.getAll();
            return new Result(StatusCode.SUCCESS,log,"查询成功");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new Result(StatusCode.ERROR,"查询失败");
    }

    @PostMapping("save")
    public Result save(@RequestBody Log log){
        ConfigBean.setIndexName("occ-front-server");
        try{
            if(log!=null){
                pushService.saveLog(log);
            }
            return new Result(StatusCode.SUCCESS,"添加成功");
        }catch(Exception e){           //自定义全局异常捕获，统一根据异常信息返回错误码
            e.printStackTrace();
        }
        return new Result(StatusCode.ERROR,"添加失败");

    }
}
