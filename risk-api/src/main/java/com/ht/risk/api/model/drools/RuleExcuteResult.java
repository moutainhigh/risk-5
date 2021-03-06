package com.ht.risk.api.model.drools;

import io.swagger.annotations.ApiModelProperty;

public class RuleExcuteResult {

    public RuleExcuteResult() {
        super();
    }

    public RuleExcuteResult(int code, String msg, RuleStandardResult data, String senceVersoionId, String totalScope) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.senceVersoionId = senceVersoionId;
        this.scope = totalScope;
    }

    public RuleExcuteResult(int code, String msg, RuleStandardResult data, String senceVersoionId) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.senceVersoionId = senceVersoionId;
    }

    /**
     * 错误代码
     */
    @ApiModelProperty(required = true, value = "错误代码")
    private int code;  // 0-成功,其他失败
    /**
     * 错误描述
     */
    @ApiModelProperty(required = true, value = "错误描述")
    private String msg;

    /**
     * 传递给请求者的数据
     */
    @ApiModelProperty(value = "传递给请求者的数据")
    private RuleStandardResult data;

    @ApiModelProperty(value = "决策版本")
    private String senceVersoionId;

    private String scope;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
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

    public RuleStandardResult getData() {
        return data;
    }

    public void setData(RuleStandardResult data) {
        this.data = data;
    }

    public String getSenceVersoionId() {
        return senceVersoionId;
    }

    public void setSenceVersoionId(String senceVersoionId) {
        this.senceVersoionId = senceVersoionId;
    }
}
