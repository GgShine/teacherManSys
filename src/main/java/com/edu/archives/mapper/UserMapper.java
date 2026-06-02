package com.edu.archives.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.archives.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM t_user WHERE username = #{username} AND deleted = 0")
    User findByUsername(@Param("username") String username);

    @Select("SELECT * FROM t_user WHERE department_id = #{departmentId} AND role_id = #{roleId} AND deleted = 0")
    List<User> findUsersByDeptAndRole(@Param("departmentId") Integer departmentId,
                                      @Param("roleId") Integer roleId);
}