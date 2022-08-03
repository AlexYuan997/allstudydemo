package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sangeng.domain.LoginUser;
import com.sangeng.domain.User;
import com.sangeng.mapper.AclPermissionMapper;
import com.sangeng.mapper.AclUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AclUserMapper userMapper;

    @Autowired
    private AclPermissionMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        User user = userMapper.selectOne(queryWrapper);
        //如果没有查询到用户就抛出异常
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或者密码错误");
        }
        //权限菜单和角色拼接在一起
        List<String> rols= menuMapper.selectRoleName(username);
        List<String> permissons = menuMapper.selectPermisson(username);
        List<String> allpermissons=new ArrayList<>();
        rols.stream().forEach(s->allpermissons.add("ROLE_"+s));
        permissons.stream().forEach(s->allpermissons.add(s));
        //把数据封装成UserDetails返回
        return new LoginUser(user,allpermissons);
    }
}
