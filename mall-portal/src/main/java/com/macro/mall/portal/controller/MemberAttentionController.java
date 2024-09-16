package com.macro.mall.portal.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.portal.domain.MemberBrandAttention;
import com.macro.mall.portal.service.MemberAttentionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 会员关注品牌管理Controller
 * Created by macro on 2018/8/2.
 */
@RestController
@Api(tags = "会员关注品牌管理")
@RequestMapping("/member/attention")
public class MemberAttentionController {
    @Resource
    private MemberAttentionService memberAttentionService;
    @ApiOperation("添加品牌关注")
    @PostMapping(value = "/add")
    public CommonResult<Integer> add(@RequestBody MemberBrandAttention memberBrandAttention) {
        int count = memberAttentionService.add(memberBrandAttention);
        if(count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("取消品牌关注")
    @PostMapping(value = "/delete")
    public CommonResult<Integer> delete(Long brandId) {
        int count = memberAttentionService.delete(brandId);
        if(count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("分页查询当前用户品牌关注列表")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<MemberBrandAttention>> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<MemberBrandAttention> page = memberAttentionService.list(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("根据品牌ID获取品牌关注详情")
    @GetMapping(value = "/detail")
    public CommonResult<MemberBrandAttention> detail(@RequestParam Long brandId) {
        MemberBrandAttention memberBrandAttention = memberAttentionService.detail(brandId);
        return CommonResult.success(memberBrandAttention);
    }

    @ApiOperation("清空当前用户品牌关注列表")
    @PostMapping(value = "/clear")
    public CommonResult<?> clear() {
        memberAttentionService.clear();
        return CommonResult.success(null);
    }
}
