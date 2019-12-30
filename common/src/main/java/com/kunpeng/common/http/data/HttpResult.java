package com.kunpeng.common.http.data;


import androidx.annotation.NonNull;

import com.kunpeng.common.utils.ToolsHelper;

import java.util.Map;


public class HttpResult {
    private String apiNo;
    private String result;
    private Map<String, String> extras;
    /*API请求结果*/
    private boolean error = false;

    public HttpResult() {
    }

    public HttpResult(String apiNo, Map<String, String> extras) {
        this.apiNo = apiNo;
        this.extras = extras;
    }

    public HttpResult(String apiNo, String result, Map<String, String> extras, boolean error) {
        this.apiNo = apiNo;
        this.result = result;
        this.extras = extras;
        this.error = error;
    }

    public String getApiNo() {
        return ToolsHelper.toString(apiNo);
    }

    public void setApiNo(String apiNo) {
        this.apiNo = apiNo;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Map<String, String> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, String> extras) {
        this.extras = extras;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    @NonNull
    @Override
    public String toString() {
        return "HttpResult{" +
                "apiNo='" + apiNo + '\'' +
                ", result='" + result + '\'' +
                ", extras=" + extras +
                ", error=" + error +
                '}';
    }
}
