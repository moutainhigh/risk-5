package com.ht.risk.api.feign.eip;

import com.ht.risk.api.model.eip.*;
import com.ht.ussp.core.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


//@PropertySource("classpath:config.properties")
@FeignClient(value = "eip-out",path = "/eip",configuration =StRpc.MultipartSupportConfig.class )
public interface StRpc {

    /**
     * 描述：商汤身份验证
     */
    @PostMapping(value = "/tc/st/idVerify",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<IdVerifyRespDto> idVerify(@RequestBody IdVerifyReqDto input);

    /**
     * 描述：商汤公安人脸照比对
     */
//    @PostMapping(value = "/st/faceCompare",headers = { "app=FK" ,"content-type=application/x-www-form-urlencoded" }, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
//    Result<FaceCompareDtoOut> faceCompare(@RequestBody FaceCompareReqDto input);
    @RequestMapping(value = "/st/faceCompare", headers = {"app=FK"},method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public Result<FaceCompareDtoOut> faceCompare(@RequestParam(value = "idNumber", required = true) String idNumber,
                              @RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "selfieUrl", required = false) String selfieUrl,
                              @RequestParam(value = "selfieImageId", required = false) String selfieImageId,
                              @RequestParam(value = "selfieAutoRotate", required = false) String selfieAutoRotate,
                              @RequestPart(value = "selfieFile", required = false) MultipartFile selfieFile);








    /**
     * 描述：商汤公安活体人脸照比对
     */
    @PostMapping(value = "/st/checkLive",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<CheckLiveReqDtoOut> checkLive(@RequestBody CheckLiveReqDtoIn input);

    class MultipartSupportConfig {

    }
}

