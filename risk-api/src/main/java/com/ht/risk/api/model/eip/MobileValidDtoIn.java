package com.ht.risk.api.model.eip;

import com.ht.risk.api.comment.commEntryIn;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class MobileValidDtoIn extends commEntryIn {

	@ApiModelProperty(value = "真实姓名")
	private String realName;

	@ApiModelProperty(value = "证件证号")
	private String identityCard;

	@ApiModelProperty(value = "手机号码")
	private String mobilePhone;

	@ApiModelProperty(value = "是否取最新数据")
	private String isNew;

}