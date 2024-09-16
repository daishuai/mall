package com.macro.mall.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.dto.SmsCouponParam;
import com.macro.mall.model.SmsCoupon;
import com.macro.mall.service.SmsCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 优惠券管理Controller
 * Created by macro on 2018/8/28.
 */
@RestController
@Api(tags = "优惠券管理")
@RequestMapping("/coupon")
public class SmsCouponController {
    @Resource
    private SmsCouponService couponService;

    @ApiOperation("添加优惠券")
    @PostMapping(value = "/create")
    public CommonResult<Integer> add(@RequestBody SmsCouponParam couponParam) {
        int count = couponService.create(couponParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除优惠券")
    @PostMapping(value = "/delete/{id}")
    public CommonResult<Integer> delete(@PathVariable Long id) {
        int count = couponService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改优惠券")
    @PostMapping(value = "/update/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, @RequestBody SmsCouponParam couponParam) {
        int count = couponService.update(id, couponParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("根据优惠券名称和类型分页获取优惠券列表")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<SmsCoupon>> list(@RequestParam(value = "name", required = false) String name,
                                                    @RequestParam(value = "type", required = false) Integer type,
                                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsCoupon> couponList = couponService.list(name, type, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(couponList));
    }

    @ApiOperation("获取单个优惠券的详细信息")
    @GetMapping(value = "/{id}")
    public CommonResult<SmsCouponParam> getItem(@PathVariable Long id) {
        SmsCouponParam couponParam = couponService.getItem(id);
        return CommonResult.success(couponParam);
    }
}
