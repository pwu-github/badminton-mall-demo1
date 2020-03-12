package com.badminton.mall.service;


import com.badminton.mall.entity.BusinessUser;
import com.badminton.mall.util.PageQueryUtil;
import com.badminton.mall.util.PageResult;

public interface BusinessUserService {
    BusinessUser businessLogin(String userName, String password);

    String register(String loginName, String password);

    BusinessUser getUserDetailById(Integer loginUserId);

    boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword);

    boolean updateName(Integer loginUserId, String loginUserName, String nickName);

    PageResult getNewBeeMallUsersPage(PageQueryUtil pageUtil);

    boolean lockUsers(Integer[] ids, int lockStatus);
}
