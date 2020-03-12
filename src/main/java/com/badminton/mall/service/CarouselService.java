package com.badminton.mall.service;

import com.badminton.mall.controller.vo.IndexCarouselVO;
import com.badminton.mall.entity.Carousel;
import com.badminton.mall.util.PageQueryUtil;
import com.badminton.mall.util.PageResult;

import java.util.List;

public interface CarouselService {
    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    PageResult getCarouselPage(PageQueryUtil pageUtil);

    String saveCarousel(Carousel carousel);

    String updateCarousel(Carousel carousel);

    Carousel getCarouselById(Integer id);

    Boolean deleteBatch(Integer[] ids);

    /**
     * 返回固定数量的轮播图对象(首页调用)
     *
     * @param number
     * @return
     */
    List<IndexCarouselVO> getCarouselsForIndex(int number);
}
