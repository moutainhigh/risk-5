package com.ht.risk.rule.service;

import com.ht.risk.rule.entity.ModelValidateBean;
import com.ht.risk.rule.entity.ValidateBatch;
import com.ht.risk.common.service.BaseService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-10
 */
public interface ValidateBatchService extends BaseService<ValidateBatch> {

    public List<ModelValidateBean> analysisBatchJobs(ValidateBatch validateBatch);

}
