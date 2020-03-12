package com.badminton.mall.service;


import com.badminton.mall.entity.GoodsInfo;
import com.badminton.mall.util.PageQueryUtil;
import com.badminton.mall.util.PageResult;

import java.util.List;

public interface GoodsService {
    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    PageResult getNewBeeMallGoodsPage(PageQueryUtil pageUtil);

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    String saveNewBeeMallGoods(GoodsInfo goods);

    /**
     * 批量新增商品数据
     *
     * @param goodsInfoList
     * @return
     */
    void batchSaveNewBeeMallGoods(List<GoodsInfo> goodsInfoList);

    /**
     * 修改商品信息
     *
     * @param goods
     * @return
     */
    String updateNewBeeMallGoods(GoodsInfo goods);

    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    GoodsInfo getNewBeeMallGoodsById(Long id);

    /**
     * 批量修改销售状态(上架下架)
     *
     * @param ids
     * @return
     */
    Boolean batchUpdateSellStatus(Long[] ids, int sellStatus);

    /**
     * 商品搜索
     *
     * @param pageUtil
     * @return
     */
    PageResult searchNewBeeMallGoods(PageQueryUtil pageUtil);

    void incView(Long goodsId);
}
