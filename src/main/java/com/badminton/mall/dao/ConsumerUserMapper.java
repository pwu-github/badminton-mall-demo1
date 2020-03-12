package com.badminton.mall.dao;


import com.badminton.mall.entity.ConsumerUser;
import com.badminton.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsumerUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(ConsumerUser record);

    int insertSelective(ConsumerUser record);

    ConsumerUser selectByPrimaryKey(Long userId);

    ConsumerUser selectByLoginName(String loginName);

    ConsumerUser selectByLoginNameAndPasswd(@Param("loginName") String loginName, @Param("password") String password);

    int updateByPrimaryKeySelective(ConsumerUser record);

    int updateByPrimaryKey(ConsumerUser record);

    List<ConsumerUser> findMallUserList(PageQueryUtil pageUtil);

    int getTotalMallUsers(PageQueryUtil pageUtil);

    int lockUserBatch(@Param("ids") Integer[] ids, @Param("lockStatus") int lockStatus);
}