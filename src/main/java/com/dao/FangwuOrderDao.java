package com.dao;

import com.entity.FangwuOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.FangwuOrderView;

/**
 * 房屋租赁订单 Dao 接口
 *
 * @author 
 */
public interface FangwuOrderDao extends BaseMapper<FangwuOrderEntity> {

   List<FangwuOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
