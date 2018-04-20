package com.pxu.hrms.dao;

import com.pxu.hrms.dao.provider.UserDynaSqlProvider;
import com.pxu.hrms.domain.User;

import static com.pxu.hrms.util.common.HrmsConstants.USERTABLE;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface UserDao {

    @Select("select * from " + USERTABLE + " where loginname=#{loginname} and password=#{password}")
    User selectByLoginnameAndPassword(
            @Param("loginname")
                    String logingname,
            @Param("password")
                    String password);

    @Select("select * from " + USERTABLE + " where id=#{id}")
    User selectById(Integer id);

    @Delete("delete from " + USERTABLE + " where id=#{id}")
    void deleteById(Integer id);

    @SelectProvider(type = UserDynaSqlProvider.class, method = "updateUser")
    void update(User user);

    @SelectProvider(type = UserDynaSqlProvider.class, method = "selectWithParam")
    List<User> selectByPage(Map<String, Object> params);

    @SelectProvider(type = UserDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    @SelectProvider(type = UserDynaSqlProvider.class, method = "inserUser")
    void save(User user);
}
