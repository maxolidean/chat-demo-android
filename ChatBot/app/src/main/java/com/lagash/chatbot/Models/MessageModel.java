package com.lagash.chatbot.Models;

/**
 * Created by Admin on 30-05-2017.
 */

public class MessageModel {
    public String response;
    public String id;
    public int result;
    public String msg;

    public MessageModel(String response, String id, int result, String msg) {
        this.response = response;
        this.id = id;
        this.result = result;
        this.msg = msg;
    }

    public MessageModel() {
    }

    public String getResponse() {
        return response;
    }

    public String getId() {
        return id;
    }

    public int getResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }

    public void setResponse(String response) {

        this.response = response;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
