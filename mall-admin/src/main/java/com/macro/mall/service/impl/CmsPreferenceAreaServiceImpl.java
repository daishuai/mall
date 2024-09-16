package com.macro.mall.service.impl;

import com.macro.mall.mapper.CmsPreferenceAreaMapper;
import com.macro.mall.model.CmsPrefrenceArea;
import com.macro.mall.model.CmsPrefrenceAreaExample;
import com.macro.mall.service.CmsPreferenceAreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品优选管理Service实现类
 * Created by macro on 2018/6/1.
 */
@Service
public class CmsPreferenceAreaServiceImpl implements CmsPreferenceAreaService {
    @Resource
    private CmsPreferenceAreaMapper preferenceAreaMapper;

    @Override
    public List<CmsPrefrenceArea> listAll() {
        return preferenceAreaMapper.selectByExample(new CmsPrefrenceAreaExample());
    }
}
