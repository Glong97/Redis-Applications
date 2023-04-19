package com.glong.redis_emailcode.common;

import com.glong.redis_emailcode.common.constant.ResultConstant;

public class ResultJson {
    /**
     * 返回的状态码
     * */
    private int code;
    /**
     * 返回的信息提示信息
     * */
    private String message;
    /**
     * 返回的数据
     * */
    private Object data;

    public ResultJson() {
    }

    public ResultJson(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public static ResultJson result(boolean r){
        if (r) {
            return success();
        }else {
            return error();
        }
    }

    public static ResultJson success(){
        return success(ResultConstant.SUCCESS_SEND,null);
    }

    public static ResultJson success(Object data){
        return success(ResultConstant.SUCCESS_SEND,data);
    }

    public static ResultJson success(String msg,Object data){
        return new ResultJson(ResultConstant.SUCCESS,msg,data);
    }

    public static ResultJson error(){
        return error(ResultConstant.ERROR_SEND,null);
    }

    public static ResultJson error(Object data){
        return error(ResultConstant.ERROR_SEND,data);
    }

    public static ResultJson error(String msg,Object data){
        return new ResultJson(ResultConstant.ERROR,msg,data);
    }
}
