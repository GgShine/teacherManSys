package com.edu.archives.common;

import lombok.Data;

// 统一响应体。
// 调用关系：Controller/Service 统一返回 Result，前端 request.js 按 code/msg/data 解析。
@Data
public class Result<T> {
    // 业务状态码："200" 表示成功。
    private String code;
    // 业务消息。
    private String msg;
    // 业务数据负载。
    private T data;

    // 成功响应（无 data）。
    public static Result success() {
        Result result = new Result<>();
        result.setCode("200");
        result.setMsg("成功");
        return result;
    }

    // 成功响应（带 data）。
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode("200");
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    // 失败响应（自定义 code + msg）。
    public static Result error(String code, String msg) {
        Result result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    // 失败响应（默认 code=500）。
    public static Result error(String msg) {
        Result result = new Result<>();
        result.setCode("500");
        result.setMsg(msg);
        return result;
    }
}