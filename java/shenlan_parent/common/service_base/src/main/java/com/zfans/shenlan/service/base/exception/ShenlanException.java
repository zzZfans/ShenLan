package com.zfans.shenlan.service.base.exception;

import com.zfans.shenlan.common.base.result.ResultCodeEnum;
import lombok.Data;

/**
 * @Author Zfans
 * @DateTime 2021/02/02 15:56
 */
@Data
public class ShenlanException extends RuntimeException {


    //状态码
    private Integer code;

    /**
     * 接受状态码和消息
     *
     * @param code
     * @param message
     */
    public ShenlanException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 接收枚举类型
     *
     * @param resultCodeEnum
     */
    public ShenlanException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "ShenlanException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
