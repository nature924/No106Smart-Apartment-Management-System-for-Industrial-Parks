package com.dao;

import com.entity.WeixiubaobeiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.WeixiubaobeiView;

/**
 * 维修报备 Dao 接口
 *
 * @author 
 */
public interface WeixiubaobeiDao extends BaseMapper<WeixiubaobeiEntity> {

   List<WeixiubaobeiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
