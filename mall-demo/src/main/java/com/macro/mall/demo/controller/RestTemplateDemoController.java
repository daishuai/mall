package com.macro.mall.demo.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.PmsBrand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * RestTemplate示例Controller
 * Created by macro on 2018/9/17.
 */
@Api(tags = "RestTemplateDemoController", description = "RestTemplate示例")
@RestController
@RequestMapping("/template")
public class RestTemplateDemoController {
    @Resource
    private RestTemplate restTemplate;
    @Value("${host.mall.admin}")
    private String HOST_MALL_ADMIN;

    @ApiOperation("getForEntity url")
    @GetMapping(value = "/get/{id}")
    public Object getForEntity(@PathVariable Long id) {
        String url = HOST_MALL_ADMIN + "/brand/{id}";
        ResponseEntity<CommonResult> responseEntity = restTemplate.getForEntity(url, CommonResult.class, id);
        return responseEntity.getBody();
    }

    @ApiOperation("getForEntity params")
    @GetMapping(value = "/get2/{id}")
    public Object getForEntity2(@PathVariable Long id) {
        String url = HOST_MALL_ADMIN + "/brand/{id}";
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(id));
        ResponseEntity<CommonResult> responseEntity = restTemplate.getForEntity(url, CommonResult.class, params);
        return responseEntity.getBody();
    }

    @ApiOperation("getForEntity Uri")
    @GetMapping(value = "/get3/{id}")
    public Object getForEntity3(@PathVariable Long id) {
        String url = HOST_MALL_ADMIN + "/brand/{id}";
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(url).build().expand(id).encode();
        ResponseEntity<CommonResult> responseEntity = restTemplate.getForEntity(uriComponents.toUri(), CommonResult.class);
        return responseEntity.getBody();
    }

    @ApiOperation("getForObject url")
    @GetMapping(value = "/get4/{id}")
    public Object getForObject(@PathVariable Long id) {
        String url = HOST_MALL_ADMIN + "/brand/{id}";
        CommonResult commonResult = restTemplate.getForObject(url, CommonResult.class, id);
        return commonResult;
    }

    @ApiOperation("postForEntity jsonBody")
    @PostMapping(value = "/post")
    public Object postForEntity(@RequestBody PmsBrand brand) {
        String url = HOST_MALL_ADMIN + "/brand/create";
        ResponseEntity<CommonResult> responseEntity = restTemplate.postForEntity(url, brand, CommonResult.class);
        return responseEntity.getBody();
    }

    @ApiOperation("postForEntity jsonBody")
    @PostMapping(value = "/post2")
    public Object postForObject(@RequestBody PmsBrand brand) {
        String url = HOST_MALL_ADMIN + "/brand/create";
        CommonResult commonResult = restTemplate.postForObject(url, brand, CommonResult.class);
        return commonResult;
    }

    @ApiOperation("postForEntity form")
    @PostMapping(value = "/post3")
    public Object postForEntity3(@RequestParam String name) {
        String url = HOST_MALL_ADMIN + "/productAttribute/category/create";
        //设置头信息
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //构造表单参数
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        params.add("name", name);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<CommonResult> responseEntity = restTemplate.postForEntity(url, requestEntity, CommonResult.class);
        return responseEntity.getBody();
    }
}
