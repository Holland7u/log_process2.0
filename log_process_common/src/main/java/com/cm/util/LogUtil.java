package com.cm.util;

import com.cm.entity.Log;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
*@Date 2019/12/7
*@Author Guo
*内容：拆分日志工具类
*/
public class LogUtil {

    private String logs ;
    private String fileUrl = "";
    private String encoding = "UTF-8";
    private String logType;


    /**
     * 入口方法，传入数据判断格式
     * */
    private void checkType(String logMessage){
        if(logMessage.startsWith("BL##ALARM")){
            this.logType="ALARM";
        }else if(logMessage.startsWith("INFO#")||logMessage.startsWith("WARN#")){
            this.logType="INFO";
        }else{
            this.logType ="MESSAGE";
        }
    }

    /**
     * 根据传入的alarmLog拆分数据为log
     * */
    public Log analysisLog(String log){
        Log resLog = new Log();
        if(log!=null){
            //1.掐头去尾 去掉BL## ##LB
            String subLog = log.substring(4, log.length() - 4);
            System.out.println("subLog = " + subLog);
            //2.根据#分割
            String[] split = subLog.split("#");
            //3.设置主键（告警编码+偏移量）
//            resLog.setId(split[2]);
            //4.设置细粒度分类类型
//            resLog.setContentType(split[9]);
            //5.设置服务名
            //5.1截取服务名
            String index = split[8];
            String[] indexArr = index.split("-");
            String resIndex = "";
            for (int i = 0; i < indexArr.length-1; i++) {
                resIndex += indexArr[i];
            }
            //5.2设置服务名
            //6.设置日志类型
            //7.设置时间戳
            resLog.setTimestamp(split[3]);
            //8.添加描述
            resLog.setDescription(split[10]);
            //9.设置主机名以及Ip
            resLog.setHost_ip(split[4]);
            //10.全文存入
            resLog.setMessage(log);
        }
        return resLog;
    }

    /**
     * 根据properties指定的Log地址拆分数据为Log
     * */
    public List<Log> analysisLog() {
        List<Log> logs = new ArrayList<>();
        try {
            Properties prop = PropertiesLoaderUtils.loadAllProperties("common.properties");
            String basepath = prop.getProperty("basepath");
            File file = new File(basepath);
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);
            //创建一个集合存放每一行的数据
            List<String> list = new ArrayList<>();
            String str;
            //循环读取，按一行读取
            while((str=bf.readLine())!=null){
                list.add(str);
            }
            Log log = null;
            for (String s : list) {
                log = analysisLog(s);
                logs.add(log);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logs;
    }
}
