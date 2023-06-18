package com.oauth.core.constant;

import cn.hutool.http.HttpStatus;
import lombok.Data;

/**
 * @author BeauHou
 */
@Data
public class HttpResultConstant {

    /**
     * 状态码
     */
    private int code;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    /**
     * 快速构建一个成功消息
     */
    public static HttpResultConstant success() {
        HttpResultConstant httpResultConstant = new HttpResultConstant();
        httpResultConstant.setCode(HttpStatus.HTTP_OK);
        return httpResultConstant;
    }


    /**
     * 快速构建一个成功消息
     *
     * @param data 数据
     */
    public static HttpResultConstant success(Object data) {
        HttpResultConstant httpResultConstant = new HttpResultConstant();
        httpResultConstant.setCode(HttpStatus.HTTP_OK);
        httpResultConstant.setData(data);
        return httpResultConstant;
    }


    /**
     * 快速构建一个成功消息
     *
     * @param data 数据
     */
    public static HttpResultConstant error(String errorMessage) {
        HttpResultConstant httpResultConstant = new HttpResultConstant();
        httpResultConstant.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
        httpResultConstant.setData(null);
        httpResultConstant.setMessage(errorMessage);
        return httpResultConstant;
    }
}
