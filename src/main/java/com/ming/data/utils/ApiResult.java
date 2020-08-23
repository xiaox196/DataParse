package com.ming.data.utils;


/**
 * web api 返回值的基础实体，web api 的返回对象建议都使用此对象包装。
 *
 * @param <T>
 */
public class ApiResult<T> {
    public boolean success;
    public String message;
    public int ret;
    public T data;

    public static <U> ApiResult<U> fail(String message) {
        ApiResult<U> result = new ApiResult<U>();
        result.success = false;
        result.message = message;
        result.ret = ErrorCode.EXCEPTION;
        return result;
    }
    
    public static <U> ApiResult<U> fail(int ret, String message) {
        ApiResult<U> result = new ApiResult<U>();
        result.success = false;
        result.message = message;
        result.ret = ret;
        return result;
    }

    public static <U> ApiResult<U> fail(Exception e) {
        ApiResult<U> result = new ApiResult<U>();
        result.success = false;
        result.ret = ErrorCode.EXCEPTION;
        if (e.getLocalizedMessage()==null) {
            result.message = e.toString();
        } else {
            result.message = e.getLocalizedMessage();
        }

        return result;
    }

    public static <U> ApiResult<U> success(U data) {
        return success(data, "success");
    }

    public static <U> ApiResult<U> success(U data, String message) {
        ApiResult<U> result = new ApiResult<U>();
        result.success = true;
        result.data = data;
        result.ret = ErrorCode.OK;
        result.message = message;
        return result;
    }
}
