package com.ht.risk.activiti.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.activiti.entity.ActProcRelease;
import com.ht.risk.activiti.vo.ModelStartVo;
import com.ht.risk.api.model.activiti.ModelParamter;
import com.ht.risk.api.model.activiti.RpcDeployResult;
import com.ht.risk.api.model.activiti.RpcModelReleaseInfo;
import com.ht.risk.api.model.activiti.RpcStartParamter;
import com.ht.risk.common.result.Result;
import com.ht.risk.common.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-16
 */
public interface ActProcReleaseService extends BaseService<ActProcRelease> {

    Page<ActProcRelease> queryProcReleaseForPage(Page<ActProcRelease> page, ActProcRelease actProcRelease);

    Result<RpcDeployResult> proceDeploy(ModelParamter paramter,String userId);

    /*String startProcess(RpcStartParamter rpcStartParamter,String userId);*/

    String startModel(ModelStartVo modelStartVo,String userId);

    String startInputValidateProcess(RpcStartParamter paramter,String userId)throws Exception;

   /* Long startBatchValidateProcess(RpcStartParamter paramter,String userId)throws Exception;*/

    RpcModelReleaseInfo convertRpcActExcuteTask(ActProcRelease release);
}
