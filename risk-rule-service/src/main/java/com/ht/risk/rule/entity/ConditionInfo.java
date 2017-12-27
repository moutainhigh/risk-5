package com.ht.risk.rule.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ht.risk.rule.vo.RuleItemTable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
/**
 * <p>
 * 规则条件信息表
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@ApiModel
@TableName("rule_condition_info")
public class ConditionInfo extends Model<ConditionInfo> {

    private static final long serialVersionUID = 1L;
	@TableField(exist = false)
    private RuleItemTable itemTable;



	/**
     * 主键
     */
    @TableId("condition_id")
	@ApiModelProperty(required= true,value = "主键")
	private Long conditionId;
    /**
     * 规则
     */
	@TableField("rule_id")
	@ApiModelProperty(required= true,value = "规则")
	private Long ruleId;
    /**
     * 条件名称
     */
	@TableField("condition_name")
	@ApiModelProperty(required= true,value = "条件名称")
	private String conditionName;
	/**
	 * 值
	 */
	@TableField("val")
	@ApiModelProperty(required= true,value = "值")
	private String val;


	@TableField(exist = false)
	@ApiModelProperty(required= true,value = "运算符")
	private String ysfText;

	public String getYsfText() {
		return ysfText;
	}

	public void setYsfText(String ysfText) {
		this.ysfText = ysfText;
	}

	@TableField(exist = false)
	@ApiModelProperty(required= true,value = "运算符")
	private String ysf;

	public String getYsf() {
		return ysf;
	}

	public void setYsf(String ysf) {
		this.ysf = ysf;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	/**
     * 条件表达式
     */
	@TableField("condition_expression")
	@ApiModelProperty(required= true,value = "条件表达式")
	private String conditionExpression;
    /**
     * 条件描述
     */
	@TableField("condition_desc")
	@ApiModelProperty(required= true,value = "条件描述")
	private String conditionDesc;
    /**
     * 是否有效
     */
	@TableField("is_effect")
	@ApiModelProperty(required= true,value = "是否有效")
	private Integer isEffect;
    /**
     * 创建人
     */
	@TableField("cre_user_id")
	@ApiModelProperty(required= true,value = "创建人")
	private Long creUserId;
    /**
     * 创建时间
     */
	@TableField("cre_time")
	@ApiModelProperty(required= true,value = "创建时间")
	private Date creTime;
    /**
     * 备注
     */
	@ApiModelProperty(required= true,value = "备注")
	private String remark;
	public RuleItemTable getItemTable() {
		return itemTable;
	}

	public void setItemTable(RuleItemTable itemTable) {
		this.itemTable = itemTable;
	}

	public Long getConditionId() {
		return conditionId;
	}

	public void setConditionId(Long conditionId) {
		this.conditionId = conditionId;
	}

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public String getConditionName() {
		return conditionName;
	}

	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}

	public String getConditionExpression() {
		return conditionExpression;
	}

	public void setConditionExpression(String conditionExpression) {
		this.conditionExpression = conditionExpression;
	}

	public String getConditionDesc() {
		return conditionDesc;
	}

	public void setConditionDesc(String conditionDesc) {
		this.conditionDesc = conditionDesc;
	}

	public Integer getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(Integer isEffect) {
		this.isEffect = isEffect;
	}

	public Long getCreUserId() {
		return creUserId;
	}

	public void setCreUserId(Long creUserId) {
		this.creUserId = creUserId;
	}

	public Date getCreTime() {
		return creTime;
	}

	public void setCreTime(Date creTime) {
		this.creTime = creTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	protected Serializable pkVal() {
		return this.conditionId;
	}

	@Override
	public String toString() {
		return "ConditionInfo{" +
			"conditionId=" + conditionId +
			", ruleId=" + ruleId +
			", conditionName=" + conditionName +
			", conditionExpression=" + conditionExpression +
			", conditionDesc=" + conditionDesc +
			", isEffect=" + isEffect +
			", creUserId=" + creUserId +
			", creTime=" + creTime +
			", remark=" + remark +
			"}";
	}
}
