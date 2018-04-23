package com.pxu.hrms.dao;

import com.pxu.hrms.dao.provider.EmployeeDynaSqlProvider;
import com.pxu.hrms.domain.Employee;

import static com.pxu.hrms.util.common.HrmsConstants.EMPLOYEETABLE;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface EmployeeDao {

    @SelectProvider(type = EmployeeDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);


    @SelectProvider(type = EmployeeDynaSqlProvider.class, method = "selectWithParam")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "CARD_ID", property = "cardId"),
            @Result(column = "POST_CODE", property = "postCode"),
            @Result(column = "QQ_NUM", property = "qqNum"),
            @Result(column = "BIRTHDAY", property = "birthday", javaType = Date.class),
            @Result(column = "CREATE_DATE", property = "createDate", javaType = Date.class),
            @Result(column = "DEPT_ID", property = "dept", one = @One(select = "com.pxu.hrms.dao.DeptDao.selectById", fetchType = FetchType.EAGER)),
            @Result(column = "JOB_ID", property = "job", one = @One(select = "com.pxu.hrms.dao.JobDao.selectById", fetchType = FetchType.EAGER))
    })
    List<Employee> selectByPage(Map<String, Object> params);

    @SelectProvider(type = EmployeeDynaSqlProvider.class, method = "insertEmployee")
    void save(Employee employee);

    @Delete("delete from " + EMPLOYEETABLE + " where id=#{id} ")
    void deleteById(Integer id);

    @Select("select * from " + EMPLOYEETABLE + " where id=#{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "CARD_ID", property = "cardId"),
            @Result(column = "POST_CODE", property = "postCode"),
            @Result(column = "QQ_NUM", property = "qqNum"),
            @Result(column = "BIRTHDAY", property = "birthday", javaType = Date.class),
            @Result(column = "CREATE_DATE", property = "createDate", javaType = Date.class),
            @Result(column = "DEPT_ID", property = "dept", one = @One(select = "com.pxu.hrms.dao.DeptDao.selectById", fetchType = FetchType.EAGER)),
            @Result(column = "JOB_ID", property = "job", one = @One(select = "com.pxu.hrms.dao.JobDao.selectById", fetchType = FetchType.EAGER))
    })
    Employee selectById(Integer id);

    @SelectProvider(type = EmployeeDynaSqlProvider.class, method = "updateEmployee")
    void update(Employee employee);
}
