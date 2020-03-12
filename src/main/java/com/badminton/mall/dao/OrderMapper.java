package com.badminton.mall.dao;


import com.badminton.mall.entity.MallOrder;
import com.badminton.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(MallOrder record);

    int insertSelective(MallOrder record);

    MallOrder selectByPrimaryKey(Long orderId);

    MallOrder selectByOrderNo(String orderNo);

    int updateByPrimaryKeySelective(MallOrder record);

    int updateByPrimaryKey(MallOrder record);

    List<MallOrder> findNewBeeMallOrderList(PageQueryUtil pageUtil);

    int getTotalNewBeeMallOrders(PageQueryUtil pageUtil);

    List<MallOrder> selectByPrimaryKeys(@Param("orderIds") List<Long> orderIds);

    int checkOut(@Param("orderIds") List<Long> orderIds);

    int closeOrder(@Param("orderIds") List<Long> orderIds, @Param("orderStatus") int orderStatus);

    int checkDone(@Param("orderIds") List<Long> asList);
}