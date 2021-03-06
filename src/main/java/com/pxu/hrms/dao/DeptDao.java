package com.pxu.hrms.dao;

import com.pxu.hrms.dao.provider.DeptDynaSqlProvider;
import com.pxu.hrms.domain.Dept;

import static com.pxu.hrms.util.common.HrmsConstants.DEPTTABLE;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface DeptDao {

    @SelectProvider(type = DeptDynaSqlProvider.class, method = "selectWithParam")
    List<Dept> selectByPage(Map<String, Object> params);

    @SelectProvider(type = DeptDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    @Select("select * from " + DEPTTABLE + " ")
    List<Dept> selectAllDept();

    @Select("select * from " + DEPTTABLE + " where id=#{id} ")
    Dept selectById(Integer id);

    @Delete("delete from " + DEPTTABLE + " where id = #{id} ")
    void deleteById(Integer id);

    @SelectProvider(type = DeptDynaSqlProvider.class, method = "insertDept")
    void save(Dept dept);

    @SelectProvider(type = DeptDynaSqlProvider.class, method = "updateDept")
    void update(Dept dept);
}
