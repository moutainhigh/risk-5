package com.ht.risk.api.feign.eip;

import com.ht.risk.api.model.eip.*;
import com.ht.ussp.core.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *天秤相关外联对接接口
 */
@FeignClient(value = "${eip.feign.service}",path = "${eip.feign.path}",url = "${eip.feign.url}")
public interface TcRpc {
    /**
     * 描述：网贷黑名单
     * @param input
     * @return a
     * @autor 张鹏
     * @date 2018/2/5 9:38
     */
    @PostMapping(value = "/black/netLoan", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<NetLoanOut> netLoan(@RequestBody NetLoanIn input);

    /**
     * 描述：老赖黑名单
     * @param input
     * @return a
     * @autor 张鹏
     * @date 2018/2/5 9:38
     */
    @PostMapping(value = "/black/oldLai", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<OldLaiOut> oldLai(@RequestBody OldLaiIn input);

    /**
     * 描述：自有黑名单
     * @param input
     * @return a
     * @autor 张鹏
     * @date 2018/2/5 9:38
     */
    @PostMapping(value = "/black/self", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<SelfDtoOut> self(@RequestBody OldLaiIn input);

    /**
     * 描述：自有黑名单
     * @param input
     * @return a
     * @autor 张鹏
     * @date 2018/2/5 9:38
     */
    @PostMapping(value = "/black/frontSea", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<FrontSeaDtoOut> frontSea(@RequestBody FrontSeaDtoIn input);


    /**
     * 描述：手机号验证
     * @param input
     * @return a
     * @autor 黄增猛
     * @date 2018/2/5 9:38
     */
    @PostMapping(value = "/black/mobileValid", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<MobileValidDtoOut> mobileValid(@RequestBody MobileValidDtoIn input);

    /**
     * 描述：负面消息查询
     * @param input
     * @return a
     * @autor 黄增猛
     * @date 2018/2/5 9:38
     */
    @PostMapping(value = "/news/negativeSearch", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<NegativeSearchDtoOut> negativeSearch(@RequestBody NegativeSearchDtoIn input);



}