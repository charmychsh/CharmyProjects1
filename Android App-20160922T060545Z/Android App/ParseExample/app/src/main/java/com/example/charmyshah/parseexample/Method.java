package com.example.charmyshah.parseexample;

/**
 * Created by charmyshah on 5/6/15.
 */
public class Method {
    String method;
    String data;

    Method(String methMethod, String methDatas){
        method = methMethod;
        data = methDatas;
    }

    public String getMethod() {
       return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return this.getMethod();
    }

}
