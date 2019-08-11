package com.xishanqu.redislock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * t_sys_user
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 系统用户ID
     */
    private String sysUserId;

    /**
     * 账户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 地址
     */
    private String address;

    /**
     * 创建时间
     */
    private Date createTime;



}