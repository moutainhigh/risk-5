package com.ht.risk.eip.controller;

import com.ht.risk.api.feign.eip.CommonRpc;
import com.ht.risk.api.feign.eip.DemoRpc;
import com.ht.risk.api.model.eip.MessageDtoIn;
import com.ht.risk.api.model.eip.QueryUserInformationAuthDtoIn;
import com.ht.risk.api.model.eip.QueryUserInformationAuthDtoOut;
import com.ht.ussp.core.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * demo
 * </p>
 * @author 张鹏
 * @since 2017-12-15
 */
@RestController
@Api(tags = "test", description = "通用服务接口", hidden = true)
public class Test {


    @Autowired
    private DemoRpc demoRpc;

    @PostMapping("/test")
    @ApiOperation(value = "demo测试",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public String test( ) throws Exception{
        String result =  demoRpc.test();
        return result;
    }
}
