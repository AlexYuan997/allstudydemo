package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangeng.domain.AclPermission;

import java.util.List;

public interface AclPermissionMapper  extends BaseMapper<AclPermission> {
    List<String> selectPermisson(String username);

    List<String> selectRoleName(String username);
}