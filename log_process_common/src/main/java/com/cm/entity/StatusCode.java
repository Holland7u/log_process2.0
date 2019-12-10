package com.cm.entity;

public class StatusCode {
    public static final int ORTHER=-2;//
    public static final int ERROR =-1;//系统错误
    public static final int SUCCESS=200; //成功
    public static final int NO_DATA=210;//暂无数据
    public static final int CODE_LOGIN =1003;//请登录
    public static final int NOT_FOUND=1004;//不存在符合请求条件的接口
    public static final int PARAM_NEED=1006;//缺少必填参数
    public static final int DEL_FILE=1005;//数据删除失败
    public static final int NO_DEL_DATA=1007;//没有找到被删除数据
    public static final int NO_TOKEN=211;//token为空
    public static final int LOGIN_ERROR=212;//登录异常
    public static final int VAILDATA_ERROR=1001;//数据验证错误
    public static final int RPC_ERROR=1002;//远程调用异常
}