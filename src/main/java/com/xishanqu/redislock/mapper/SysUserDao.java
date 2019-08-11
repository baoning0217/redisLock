package com.xishanqu.redislock.mapper;

import com.xishanqu.redislock.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * SysUserDao继承基类
 * @Author Bao
 */
@Mapper
public interface SysUserDao {

    /**
     * 新增
     *
     * @param sysUser
     * @return
     */
    int insertSelective(SysUser sysUser);

    /**
     * 更新
     * @param sysUser
     * @return
     */
    int updateByPrimaryKeySelective(SysUser sysUser);

    /**
     * 查询
     * @Param sysUserId
     * @Return SysUser
     * @Author BaoNing
     * @Time
     */
    SysUser queryBySysUserId(@Param("sysUserId") String sysUserId);


    /**
     * 登录查询
     * @Param
     * @Return
     * @Author BaoNing
     * @Time
     */
    SysUser queryUserByPhoneAndPassword(@Param("mobile") String mobile,
                                        @Param("password") String passowrd);


}