package com.ht.risk.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 张鹏
 * @since 2018-01-10
 */
@ApiModel
@TableName("risk_drools_detail_log")
public class DroolsDetailLog extends Model<DroolsDetailLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.UUID)
	@ApiModelProperty(required= true,value = "")
	private String id;
	@TableField("drools_logid")
	@ApiModelProperty(required= true,value = "")
	private String droolsLogid;
    /**
     * 命中的规则
     */
	@TableField("execute_rulename")
	@ApiModelProperty(required= true,value = "命中的规则")
	private String executeRulename;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDroolsLogid() {
		return droolsLogid;
	}

	public void setDroolsLogid(String droolsLogid) {
		this.droolsLogid = droolsLogid;
	}

	public String getExecuteRulename() {
		return executeRulename;
	}

	public void setExecuteRulename(String executeRulename) {
		this.executeRulename = executeRulename;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "DroolsDetaillog{" +
			"id=" + id +
			", droolsLogid=" + droolsLogid +
			", executeRulename=" + executeRulename +
			"}";
	}
}
