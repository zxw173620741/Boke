package org.demo.houduan.common;

import lombok.Data;

/**
 * 统一返回结果类
 * <T> 是泛型，意思是 data 里可以装 User，也可以装 String，还可以装 List
 */
@Data
public class Result<T> {
    // 1. 状态码：比如 1 代表成功，0 代表失败
    private Integer code;
    // 2. 提示信息：比如 "登录成功"、"用户名已存在"
    private String msg;
    // 3. 真正的数据：比如 User 对象，或者 List<Blog>
    private T data;

    // 快速生成一个“成功”的包裹
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.code = 1;
        result.msg = "操作成功";
        result.data = data;
        return result;
    }

    // 快速生成一个“失败”的包裹
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.code = 0;
        result.msg = msg;
        result.data = null;
        return result;
    }
}