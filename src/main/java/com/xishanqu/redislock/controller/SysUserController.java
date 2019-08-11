package com.xishanqu.redislock.controller;

import com.xishanqu.redislock.model.SysUser;
import com.xishanqu.redislock.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 中文类名:
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/8/2
 */
@RestController
@RequestMapping("/api/sys/user")
@Slf4j
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


    /**
     * 增加用户
     * @Param
     * @Return
     * @Author BaoNing
     * @Time
     */
    @PostMapping("/save")
    public int saveSysUser(@RequestBody SysUser sysUser){
        return sysUserService.saveSysUser(sysUser);
    }

    /**
     * 查询用户
     * @Param
     * @Return
     * @Author BaoNing
     * @Time
     */
    @GetMapping("/get/{sysUserId}")
    public SysUser querySysUser(@PathVariable("sysUserId") String sysUserId){
        return sysUserService.querySysUser(sysUserId);
    }


}
