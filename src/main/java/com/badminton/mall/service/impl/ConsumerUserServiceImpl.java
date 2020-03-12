package com.badminton.mall.service.impl;

import com.badminton.mall.common.Constants;
import com.badminton.mall.common.ServiceResultEnum;
import com.badminton.mall.controller.vo.UserVO;
import com.badminton.mall.dao.ConsumerUserMapper;
import com.badminton.mall.entity.ConsumerUser;
import com.badminton.mall.service.ConsumerUserService;
import com.badminton.mall.util.BeanUtil;
import com.badminton.mall.util.MD5Util;
import com.badminton.mall.util.PageQueryUtil;
import com.badminton.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ConsumerUserServiceImpl implements ConsumerUserService {

    @Autowired
    private ConsumerUserMapper consumerUserMapper;

    @Override
    public PageResult getNewBeeMallUsersPage(PageQueryUtil pageUtil) {
        List<ConsumerUser> consumerUsers = consumerUserMapper.findMallUserList(pageUtil);
        int total = consumerUserMapper.getTotalMallUsers(pageUtil);
        PageResult pageResult = new PageResult(consumerUsers, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String register(String loginName, String password) {
        if (consumerUserMapper.selectByLoginName(loginName) != null) {
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
        }
        ConsumerUser registerUser = new ConsumerUser();
        registerUser.setLoginName(loginName);
        registerUser.setNickName(loginName);
        String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        registerUser.setPasswordMd5(passwordMD5);
        if (consumerUserMapper.insertSelective(registerUser) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String login(String loginName, String passwordMD5, HttpSession httpSession) {
        ConsumerUser user = consumerUserMapper.selectByLoginNameAndPasswd(loginName, passwordMD5);
        if (user != null && httpSession != null) {
            if (user.getLockedFlag() == 1) {
                return ServiceResultEnum.LOGIN_USER_LOCKED.getResult();
            }
            //昵称太长 影响页面展示
            if (user.getNickName() != null && user.getNickName().length() > 7) {
                String tempNickName = user.getNickName().substring(0, 7) + "..";
                user.setNickName(tempNickName);
            }
            UserVO userVO = new UserVO();
            BeanUtil.copyProperties(user, userVO);
            //设置购物车中的数量
            httpSession.setAttribute(Constants.MALL_USER_SESSION_KEY, userVO);
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.LOGIN_ERROR.getResult();
    }

    @Override
    public UserVO updateUserInfo(ConsumerUser consumerUser, HttpSession httpSession) {
        ConsumerUser user = consumerUserMapper.selectByPrimaryKey(consumerUser.getUserId());
        if (user != null) {
            user.setNickName(consumerUser.getNickName());
            user.setAddress(consumerUser.getAddress());
            user.setIntroduceSign(consumerUser.getIntroduceSign());
            if (consumerUserMapper.updateByPrimaryKeySelective(user) > 0) {
                UserVO userVO = new UserVO();
                user = consumerUserMapper.selectByPrimaryKey(consumerUser.getUserId());
                BeanUtil.copyProperties(user, userVO);
                httpSession.setAttribute(Constants.MALL_USER_SESSION_KEY, userVO);
                return userVO;
            }
        }
        return null;
    }

    @Override
    public Boolean lockUsers(Integer[] ids, int lockStatus) {
        if (ids.length < 1) {
            return false;
        }
        return consumerUserMapper.lockUserBatch(ids, lockStatus) > 0;
    }
}
