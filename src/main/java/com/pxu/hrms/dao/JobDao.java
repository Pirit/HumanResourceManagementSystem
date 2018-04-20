package com.pxu.hrms.dao;

import com.pxu.hrms.dao.provider.JobDynaSqlProvider;
import com.pxu.hrms.domain.Job;

import static com.pxu.hrms.util.common.HrmsConstants.JOBTABLE;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface JobDao {

    @Select("select * from " + JOBTABLE + " where id=#{id} ")
    Job selectById(int id);

    @Delete("delete from " + JOBTABLE + " where id=#{id} ")
    Job deleteById(int id);

    @Select("select * from " + JOBTABLE + " ")
    List<Job> selectAllJob();

    @SelectProvider(type = JobDynaSqlProvider.class, method = "selectByParam")
    List<Job> selectByPage(Map<String, Object> params);

    @SelectProvider(type = JobDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    @SelectProvider(type = JobDynaSqlProvider.class, method = "insertJob")
    void save(Job job);

    @SelectProvider(type = JobDynaSqlProvider.class, method = "updateJob")
    void update(Job job);
}
