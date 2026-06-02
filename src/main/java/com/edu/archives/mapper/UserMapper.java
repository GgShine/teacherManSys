package com.edu.archives.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.archives.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// 用户数据访问层。
// 调用关系：UserServiceImpl -> 本 Mapper。
public interface UserMapper extends BaseMapper<User> {
    // 按用户名查询用户。
    @Select("SELECT * FROM t_user WHERE username = #{username} AND deleted = 0")
    User findByUsername(@Param("username") String username);

    // 按部门和角色查询用户列表。
    @Select("SELECT * FROM t_user WHERE department_id = #{departmentId} AND role_id = #{roleId} AND deleted = 0")
    List<User> findUsersByDeptAndRole(@Param("departmentId") Integer departmentId,
                                      @Param("roleId") Integer roleId);
}