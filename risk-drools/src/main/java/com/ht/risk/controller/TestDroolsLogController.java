package com.ht.risk.controller;


import com.alibaba.fastjson.JSON;
import com.ht.risk.api.model.drools.RpcDroolsLog;
import com.ht.risk.api.model.log.RpcHitRuleInfo;
import com.ht.risk.common.result.Result;
import com.ht.risk.model.TestDroolsLog;
import com.ht.risk.service.TestDroolsLogService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dyb
 * @since 2018-01-18
 */
@RestController
@RequestMapping("/testDroolsLog")
public class TestDroolsLogController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(TestDroolsLogController.class);

    @Resource
    private TestDroolsLogService testDroolsLogService ;

    @RequestMapping("/save")
    @ApiOperation(value = "新增日志")
    @ResponseBody
    @Transactional()
    public String save(@RequestBody TestDroolsLog entity){
        String str=null;
        try {
            testDroolsLogService.insertOrUpdate(entity);
            return  entity.getId().toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }

    @RequestMapping("/getHitRuleInfo")
    public Result<List<RpcHitRuleInfo>> getHitRuleInfo(@RequestBody String procInstId){
        LOGGER.info("getHitRuleInfo mothod invoke,paramter:"+ procInstId);
        Result<List<RpcHitRuleInfo>> result = null;
        if(procInstId == null){
            LOGGER.error("getHitRuleInfo mothod invoke,paramter null exception");
            result =Result.error(1,"参数异常！");
            return  result;
        }
        try {
            List<RpcHitRuleInfo> tasks = testDroolsLogService.queryHitRuleInfoByProcInstId(procInstId);
            result = Result.success(tasks);
        }catch (Exception e){
            e.printStackTrace();
            result = Result.error(2,"getHitRuleInfo mothod excute exception,"+e);
            return  result;
        }
        LOGGER.info("getHitRuleInfo mothod invoke end,reustl:"+ JSON.toJSONString(result));
        return result;
    }

    @RequestMapping("/countHitRuleInfo")
    public Result<List<RpcHitRuleInfo>> countHitRuleInfo(@RequestBody List<String> procInstId){
        LOGGER.info("queryModelProcInstIdByBatchId mothod invoke,paramter:"+ procInstId);
        Result<List<RpcHitRuleInfo>> result = null;
        if(procInstId == null){
            LOGGER.error("queryModelProcInstIdByBatchId mothod invoke,paramter null exception");
            result =Result.error(1,"参数异常！");
            return  result;
        }
        try {
            List<RpcHitRuleInfo> tasks = testDroolsLogService.countHitRuleInfo(procInstId);
            result = Result.success(tasks);
        }catch (Exception e){
            e.printStackTrace();
            result = Result.error(2,"queryModelProcInstIdByBatchId mothod excute exception,"+e);
            return  result;
        }
        LOGGER.info("resultPage mothod invoke,reustl:"+ JSON.toJSONString(result));
        return result;
    }


    @RequestMapping("/queryModelDroolsLogs")
    public Result<List<RpcDroolsLog>> queryTestModelDroolsLogs(@RequestBody RpcDroolsLog rpcDroolsLog){
        LOGGER.info("queryModelDroolsLogs mothod invoke,paramter:"+ JSON.toJSONString(rpcDroolsLog));
        Result<List<RpcDroolsLog>> result = null;
        if(rpcDroolsLog == null || rpcDroolsLog.getProcinstId() == null){
            LOGGER.error("queryModelDroolsLogs mothod invoke,paramter null exception");
            result =Result.error(1,"参数异常！");
            return  result;
        }
        try {
            List<RpcDroolsLog> logs = testDroolsLogService.queryTestModelDroolsLogs(rpcDroolsLog.getProcinstId());
            result = Result.success(logs);
        }catch (Exception e){
            e.printStackTrace();
            result = Result.error(2,"queryModelDroolsLogs mothod excute exception,"+e);
            return  result;
        }
        LOGGER.info("queryModelDroolsLogs mothod invoke end,reustl:"+ JSON.toJSONString(result));
        return result;
    }
}

