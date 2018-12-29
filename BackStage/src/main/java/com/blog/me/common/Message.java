package com.blog.me.common;

public class Message<T> {

    private int code;

    private String msg;

    private T data;

    public Message(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Message(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Message ok(){
        return new Message(200,"success");
    }
    public static Message ok(String msg){
        return new Message(200,msg);
    }
    public static <T> Message<T> ok(T data){
        return new Message(200,"success",data);
    }

    public static Message error(){
        return new Message(500,"error");
    }

    public static Message error(String msg){
        return new Message(500,msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
