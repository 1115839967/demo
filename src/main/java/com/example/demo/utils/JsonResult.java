package com.example.demo.utils;

/**
 * @author xutiancheng
 * @since 2020-04-09 13:44
 */
public class JsonResult {
    // 状态（true, false）
    private Boolean status = false;
    // 错误代码
    private String errCode;
    // 错误信息
    private String err;
    // 结果对象
    private Object result;

    /*
     * 无参构造
     */
    public JsonResult(){

    }

    public JsonResult(Boolean status, String err){
        this.status = status;
        this.err = err;
    }

    public JsonResult(Boolean status, String err, Object result){
        this.status = status;
        this.err = err;
        this.result = result;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public void setResult(boolean status, Object result) {
        this.status = status;
        this.result = result;
    }
}