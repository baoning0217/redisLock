package com.xishanqu.redislock.service;

import com.xishanqu.redislock.common.constant.IDKeyConstant;
import com.xishanqu.redislock.mapper.SysUserDao;
import com.xishanqu.redislock.model.SysUser;
import com.xishanqu.redislock.utils.MD5Util;
import com.xishanqu.redislock.utils.SequenceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 中文类名:
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/8/2
 */
@Service
public class SysUserService {

    @Autowired
    private SysUserDao sysUserDao;


    /**
     * 保存用户
     * @Param
     * @Return
     * @Author BaoNing
     * @Time
     */
    public int saveSysUser(SysUser sysUser){
        //生成用户id
        sysUser.setSysUserId(SequenceUtils.genKey(IDKeyConstant.USER.SYS_USER_ID,10));
        sysUser.setPassword(MD5Util.encode(sysUser.getPassword()));
        sysUser.setCreateTime(new Date());
        return sysUserDao.insertSelective(sysUser);
    }

    /**
     * 查询用户
     * @Param
     * @Return
     * @Author BaoNing
     * @Time
     */
    public SysUser querySysUser(String sysUserId){
        SysUser sysUser = sysUserDao.queryBySysUserId(sysUserId);
        return sysUser;
    }


}
