package com.ht.risk.rule.rpc;

import com.ht.risk.api.model.drools.RpcDroolsLog;
import com.ht.risk.api.model.log.RpcHitRuleInfo;
import com.ht.risk.common.result.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("risk-drools")
public interface DroolsLogRpc {

    @RequestMapping("/testDroolsLog/getHitRuleInfo")
    public Result<List<RpcHitRuleInfo>> getTestHitRuleInfo(@RequestBody String procInstId);

    @RequestMapping("/testDroolsLog/queryModelDroolsLogs")
    public Result<List<RpcDroolsLog>> queryTestModelDroolsLogs(RpcDroolsLog rpcDroolsLog);


    @RequestMapping("/droolsLog/getHitRuleInfo")
    public Result<List<RpcHitRuleInfo>> getHitRuleInfo(@RequestBody String procInstId);

    @RequestMapping("/droolsLog/queryModelDroolsLogs")
    public Result<List<RpcDroolsLog>> queryModelDroolsLogs(RpcDroolsLog rpcDroolsLog);





    @RequestMapping("/testDroolsLog/countHitRuleInfo")
    public Result<List<RpcHitRuleInfo>> countHitRuleInfo(@RequestBody List<String> procInstId);




    @RequestMapping("/testDroolsLog/queryModelDroolsLogs")
    public Result<List<RpcDroolsLog>> queryDroolsLogs(RpcDroolsLog rpcDroolsLog);



}
