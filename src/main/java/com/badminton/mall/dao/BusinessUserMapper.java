package com.badminton.mall.dao;

import com.badminton.mall.entity.BusinessUser;
import com.badminton.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessUserMapper {
    BusinessUser login(@Param("userName") String userName, @Param("password") String passwordMd5);

    BusinessUser selectByLoginName(String loginName);

    int insertSelective(BusinessUser businessUser);

    BusinessUser selectByPrimaryKey(Integer loginUserId);

    int updateByPrimaryKeySelective(BusinessUser businessUser);

    List<BusinessUser> findMallUserList(PageQueryUtil pageUtil);

    int getTotalMallUsers(PageQueryUtil pageUtil);

    int lockUserBatch(@Param("ids") Integer[] ids, @Param("lockStatus") int lockStatus);
}
