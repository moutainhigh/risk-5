package com.ht.risk.rule.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.Condition;
import com.ht.risk.api.model.activiti.RpcActExcuteTaskInfo;
import com.ht.risk.api.model.activiti.RpcModelReleaseInfo;
import com.ht.risk.api.model.activiti.RpcModelVerfication;
import com.ht.risk.api.model.log.RpcHitRuleInfo;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.ModelSence;
import com.ht.risk.rule.entity.VariableBind;
import com.ht.risk.rule.mapper.ModelSenceMapper;
import com.ht.risk.rule.mapper.VariableBindMapper;
import com.ht.risk.rule.rpc.ActivitiConfigRpc;
import com.ht.risk.rule.rpc.DroolsLogRpc;
import com.ht.risk.rule.service.ModelAnalysisSerivce;
import com.ht.risk.rule.vo.SenceParamterVo;
import com.ht.risk.rule.vo.VariableVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ModelAnalysisSerivceImpl implements ModelAnalysisSerivce {


    @Resource
    private ActivitiConfigRpc activitiConfigRpc;
    @Resource
    private DroolsLogRpc droolsLogRpc;
    @Resource
    private VariableBindMapper variableBindMapper;
    @Resource
    private ModelSenceMapper modelSenceMapper;

    @Override
    public Map<String,List<RpcHitRuleInfo>> queryModelVerfHitRuleInfo(String procInstanceId) {
        Map<String,List<RpcHitRuleInfo>> hitRuleMap = new HashMap<String,List<RpcHitRuleInfo>>();
        List<String> procInstIds = new ArrayList();
        procInstIds.add(procInstanceId);
        return getProcHitRules(procInstIds);
    }
    @Override
    public Map<String,List<RpcHitRuleInfo>> queryBatchModelVerfHitRuleInfo(Long batchId) {
        Map<String,List<RpcHitRuleInfo>> hitRuleMap = new HashMap<String,List<RpcHitRuleInfo>>();
        // 1 查询该批次的所有流程实例
        RpcModelVerfication rpcModelVerfication = new RpcModelVerfication();
        rpcModelVerfication.setBatchId(batchId);
        Result<List<RpcActExcuteTaskInfo>> result = activitiConfigRpc.queryTasksByBatchId(rpcModelVerfication);
        if(result == null || result.getCode() != 0 || result.getData() == null){
            return hitRuleMap;
        }
        // 2 查询流程实例触碰的规则
        List<String> procInstIds = new ArrayList<String>();
        for(Iterator<RpcActExcuteTaskInfo> iterator = result.getData().iterator();iterator.hasNext();){
            procInstIds.add(iterator.next().getProcInstId());
        }
        return getProcHitRules(procInstIds);
    }

    public Map<Long,SenceParamterVo>  queryModeVerfDataInfo(Long taskId) {
        Map<Long,SenceParamterVo> senceMap = new HashMap<Long,SenceParamterVo>();
        //获取模型任务信息
        RpcModelVerfication rpcModelVerfication = new RpcModelVerfication();
        rpcModelVerfication.setTaskId(taskId);
        Result<RpcActExcuteTaskInfo> result = activitiConfigRpc.getTaskInfoById(rpcModelVerfication);
        if(result == null || result.getCode() != 0 || result.getData() == null){
            return senceMap;
        }
        RpcActExcuteTaskInfo taskInfo = result.getData();
        Map<String, Object> data =  JSON.parseObject(taskInfo.getInParamter(),HashMap.class);
        // 获取模型定义信息
        rpcModelVerfication.setProcReleaseId(taskInfo.getProcReleaseId());
        Result<RpcModelReleaseInfo> releaseInfoResult = activitiConfigRpc.getProcReleaseById(rpcModelVerfication);
        if(releaseInfoResult == null || releaseInfoResult.getCode() != 0 || releaseInfoResult.getData() == null){
            return senceMap;
        }
        // 获取模型关联决策信息
        List<ModelSence> sences = modelSenceMapper.queryModelSenceInfo(releaseInfoResult.getData().getModelProcdefId());
        List<Long> versionIds = new ArrayList<Long>();
        ModelSence sence = null;
        SenceParamterVo paramterVo = null;
        for(Iterator<ModelSence> iterator = sences.iterator();iterator.hasNext();){
            sence = iterator.next();
            versionIds.add(sence.getSenceVersionId());
            paramterVo = new SenceParamterVo();
            paramterVo.setSenceName(sence.getSceneName());
            senceMap.put(sence.getSenceVersionId(),paramterVo);
        }
        //获取模型需要变量信息
        List<VariableBind> binds = variableBindMapper.selectList(Condition.create().in("sence_version_id",versionIds));
        String variableCode = null;
        VariableBind bind = null;
        VariableVo vo = null;
        List<VariableVo> vos = null;
        SenceParamterVo senceParamterVo = null;
        for(Iterator<VariableBind> iterator = binds.iterator();iterator.hasNext();){
            bind = iterator.next();
            variableCode = bind.getVariableCode();
            bind.setTmpValue(String.valueOf(data.get(variableCode)));
            vo = new VariableVo(bind);
            if(senceMap.containsKey(bind.getSenceVersionId())){
                paramterVo =senceMap.get(bind.getSenceVersionId());
                paramterVo.addVariableVo(vo);
            }
        }
        return senceMap;
    }


    private Map<String,List<RpcHitRuleInfo>> getProcHitRules(List<String> procInstIds){
        Map<String,List<RpcHitRuleInfo>> hitRuleMap = new HashMap<String,List<RpcHitRuleInfo>>();
        Result<List<RpcHitRuleInfo>> result = droolsLogRpc.countHitRuleInfo(procInstIds);
        if(result == null || result.getCode() != 0){
            return hitRuleMap;
        }
        List<RpcHitRuleInfo> hitRuleInfoDatas= result.getData();
        List<RpcHitRuleInfo> tmp = null;
        RpcHitRuleInfo tmpRule = null;
        for(Iterator<RpcHitRuleInfo> iterator = hitRuleInfoDatas.iterator();iterator.hasNext();){
            tmpRule = iterator.next();
            String senceVersionId = String.valueOf(tmpRule.getSenceVersionId());
            if(hitRuleMap.containsKey(senceVersionId)){
                tmp = hitRuleMap.get(senceVersionId);
                tmp.add(tmpRule);
            }else{
                tmp = new ArrayList<RpcHitRuleInfo>();
                tmp.add(tmpRule);
                hitRuleMap.put(senceVersionId,tmp);
            }
        }
        return hitRuleMap;
    }
}
