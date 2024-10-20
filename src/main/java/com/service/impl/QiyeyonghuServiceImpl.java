package com.service.impl;

import com.utils.StringUtil;
import com.service.DictionaryService;
import com.utils.ClazzDiff;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import com.dao.QiyeyonghuDao;
import com.entity.QiyeyonghuEntity;
import com.service.QiyeyonghuService;
import com.entity.view.QiyeyonghuView;

/**
 * 企业用户 服务实现类
 */
@Service("qiyeyonghuService")
@Transactional
public class QiyeyonghuServiceImpl extends ServiceImpl<QiyeyonghuDao, QiyeyonghuEntity> implements QiyeyonghuService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        Page<QiyeyonghuView> page =new Query<QiyeyonghuView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
