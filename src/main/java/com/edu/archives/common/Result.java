package com.edu.archives.common;

import lombok.Data;

@Data
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public static Result success() {
        Result result = new Result<>();
        result.setCode("200");
        result.setMsg("成功");
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode("200");
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    public static Result error(String code, String msg) {
        Result result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result<>();
        result.setCode("500");
        result.setMsg(msg);
        return result;
    }
}