package com.kunpeng.common.http.data;

import java.util.List;

public class RequestModel {
    /**
     * errors : [{"errcode":"401","errmsg":"用户名或密码错误","field":"username&password"}]
     * status : failure
     */

    private String status;
    private List<ErrorsBean> errors;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ErrorsBean> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorsBean> errors) {
        this.errors = errors;
    }

    public static class ErrorsBean {
        /**
         * errcode : 401
         * errmsg : 用户名或密码错误
         * field : username&password
         */

        private String errcode;
        private String errmsg;
        private String field;

        public String getErrcode() {
            return errcode;
        }

        public void setErrcode(String errcode) {
            this.errcode = errcode;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }
    }
}
