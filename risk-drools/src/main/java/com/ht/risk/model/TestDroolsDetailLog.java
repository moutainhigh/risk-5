package com.ht.risk.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author dyb
 * @since 2018-01-18
 */
@ApiModel
@TableName("RISK_TEST_DROOLS_DETAIL_LOG")
public class TestDroolsDetailLog extends Model<TestDroolsDetailLog> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
	@ApiModelProperty(required= true,value = "")
	private Long id;
	@TableField("DROOLS_LOGID")
	@ApiModelProperty(required= true,value = "")
	private Long droolsLogid;
    /**
     * 命中的规则
     */
	@TableField("EXECUTE_RULENAME")
	@ApiModelProperty(required= true,value = "命中的规则")
	private String executeRulename;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDroolsLogid() {
		return droolsLogid;
	}

	public void setDroolsLogid(Long droolsLogid) {
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
		return "TestDroolsDetailLog{" +
			"id=" + id +
			", droolsLogid=" + droolsLogid +
			", executeRulename=" + executeRulename +
			"}";
	}
}
