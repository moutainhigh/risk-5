package com.ht.risk.eip.controller;


import com.alibaba.fastjson.JSON;
import com.ht.risk.api.model.eip.*;
import com.ht.risk.eip.Rpc.StRpc;
import com.ht.risk.eip.config.FeignSpringFormEncoder;
import com.ht.ussp.core.Result;
import com.netflix.discovery.converters.Auto;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPageOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * @Author dyb
 * @Description
 * @Date 2018/2/27 15:02
 */
@RestController
@RequestMapping("/st")
@Api(tags = "StController", description = "", hidden = true)
public class StController {

    @Autowired
    StRpc stRpc;



    @PostMapping("/idVerify")
    @ApiOperation(value = "商汤身份验证", httpMethod = "POST")
    @ResponseBody
    public Result<IdVerifyRespDto> idVerify(@RequestBody IdVerifyReqDto input) throws Exception {
        System.out.println(">>>>>>>>>");
        Result<IdVerifyRespDto> result = stRpc.idVerify(input);
        System.out.println("<<<<<<<<<<<"+JSON.toJSONString(result));
        return result;
    }

    @PostMapping(value="/faceCompare",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "商汤公安人脸照比对", httpMethod = "POST")
    @ResponseBody
    public Result<FaceCompareDtoOut> faceCompare(FaceCompareReqDto input) throws Exception {
        System.out.println(">>>>>>>>>");

//        Feign.Builder encoder = Feign.builder()
//                .decoder(new JacksonDecoder())
//                .encoder(new FeignSpringFormEncoder());
//        stRpc= encoder.target(StRpc.class, "http://172.16.200.112:30101/");

        // http://192.168.15.245:30406/eip/st/faceCompare
        String url="http://172.16.200.112:30101/eip/st/faceCompare";
        RestTemplate rest = new RestTemplate();
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("fileImg", input.getSelfieFile());

//        Object string = rest.postForObject(url, param, String.class);
//        System.out.println(string);
//
//        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String,Object>>(param);
//        ResponseEntity<String> result = rest.exchange(url, HttpMethod.POST, httpEntity, String.class);

        // 可用版本
        Result<FaceCompareDtoOut> result = stRpc.faceCompare(input.getIdNumber(), input.getName(),
                "", "", "",input.getSelfieFile());

//        List result=uploadFile(input.getSelfieFile());
        System.out.println("<<<<<<<<<<<"+JSON.toJSONString(result));
        return null;
    }

    public List uploadFile(MultipartFile jarFile)throws Exception{
        RestTemplate restTemplate=new RestTemplate();
        String url = "http://172.16.200.112:30101/eip/st/faceCompare";
//        String url = clientProperties.getApiUrl(URL_USER_UPLOAD);
        HttpHeaders headers = new HttpHeaders();

        String tempFileName = UUID.randomUUID() + jarFile.getOriginalFilename().substring(jarFile.getOriginalFilename().lastIndexOf("."));
        String tempFilePath = "d:\\"+ tempFileName;
        File tempFile = new File(tempFilePath);
        jarFile.transferTo(tempFile);

        FileSystemResource fileSystemResource = new FileSystemResource(tempFilePath);
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);
        String cd = "filename=\"" + jarFile.getOriginalFilename() + "\"";
        headers.add("Content-Disposition", cd);
        headers.add("app","fk");
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();
        form.add("file", fileSystemResource);
        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);
        ResponseEntity<List> responseEntity = null;
        Object obj = restTemplate.postForEntity(url, files, List.class);
//        responseEntity = restTemplate.postForEntity(url, files, List.class);
        try {
            tempFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity.getBody();
    }

    @PostMapping("/checkLive")
    @ApiOperation(value = "商汤公安活体人脸照比对", httpMethod = "POST")
    @ResponseBody
    public Result<CheckLiveReqDtoOut> checkLive(CheckLiveReqDtoIn input) throws Exception {
        System.out.println(">>>>>>>>>");
        Result<CheckLiveReqDtoOut> result = stRpc.checkLive(input);
        System.out.println("<<<<<<<<<<<"+JSON.toJSONString(result));
        return result;
    }


}