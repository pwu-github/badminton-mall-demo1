/**
 * FileName: BusinessUserServiceImpl
 * Author: WP
 * Date: 2020/3/8 18:37
 * Description:
 * History:
 **/
package com.badminton.mall.service.impl;

import com.badminton.mall.common.ServiceResultEnum;
import com.badminton.mall.dao.BusinessUserMapper;
import com.badminton.mall.entity.BusinessUser;
import com.badminton.mall.service.BusinessUserService;
import com.badminton.mall.util.MD5Util;
import com.badminton.mall.util.PageQueryUtil;
import com.badminton.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessUserServiceImpl implements BusinessUserService {
    @Autowired
    private BusinessUserMapper businessUserMapper;
    @Override
    public BusinessUser businessLogin(String userName, String password) {
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        return businessUserMapper.login(userName, passwordMd5);
    }

    @Override
    public String register(String loginName, String password) {
        if (businessUserMapper.selectByLoginName(loginName) != null) {
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
        }
        BusinessUser businessUser = new BusinessUser();
        businessUser.setLoginName(loginName);
        businessUser.setNickName(loginName);
        String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        businessUser.setPassword(passwordMD5);
        if (businessUserMapper.insertSelective(businessUser) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public BusinessUser getUserDetailById(Integer loginUserId) {
        return businessUserMapper.selectByPrimaryKey(loginUserId);
    }

    @Override
    public boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        BusinessUser businessUser = businessUserMapper.selectByPrimaryKey(loginUserId);
        //当前用户非空才可以进行更改
        if (businessUser != null) {
            String originalPasswordMd5 = MD5Util.MD5Encode(originalPassword, "UTF-8");
            String newPasswordMd5 = MD5Util.MD5Encode(newPassword, "UTF-8");
            //比较原密码是否正确
            if (originalPasswordMd5.equals(businessUser.getPassword())) {
                //设置新密码并修改
                businessUser.setPassword(newPasswordMd5);
                if (businessUserMapper.updateByPrimaryKeySelective(businessUser) > 0) {
                    //修改成功则返回true
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        BusinessUser businessUser = businessUserMapper.selectByPrimaryKey(loginUserId);
        //当前用户非空才可以进行更改
        if (businessUser != null) {
            //设置新名称并修改
            businessUser.setLoginName(loginUserName);
            businessUser.setNickName(nickName);
            if (businessUserMapper.updateByPrimaryKeySelective(businessUser) > 0) {
                //修改成功则返回true
                return true;
            }
        }
        return false;
    }

    @Override
    public PageResult getNewBeeMallUsersPage(PageQueryUtil pageUtil) {
        List<BusinessUser> businessUsers = businessUserMapper.findMallUserList(pageUtil);
        int total = businessUserMapper.getTotalMallUsers(pageUtil);
        PageResult pageResult = new PageResult(businessUsers, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public boolean lockUsers(Integer[] ids, int lockStatus) {
        if (ids.length < 1) {
            return false;
        }
        return businessUserMapper.lockUserBatch(ids, lockStatus) > 0;
    }
}
