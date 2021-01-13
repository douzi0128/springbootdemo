package com.ym.springbootdemo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Response {

    private Boolean success;

    private Integer code;

    private String msg;

    private Object data;

    public static Response ok(Object data) {
        Response response = new Response();
        response.setCode(200);
        response.setMsg("操作成功");
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

}
