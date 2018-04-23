package com.pxu.hrms.dao.provider;

import com.pxu.hrms.domain.Job;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.pxu.hrms.util.common.HrmsConstants.JOBTABLE;

public class JobDynaSqlProvider {

    public String selectWithParam(final Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(JOBTABLE);
                if (params.get("job") != null) {
                    Job job = (Job) params.get("job");
                    if (job.getName() != null && !job.getName().equals("")) {
                        WHERE(" name LIKE CONCAT ('%',%{job.name},'%') ");
                    }
                }
            }
        }.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    public String count(final Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM(JOBTABLE);
                if (params.get("job") != null) {
                    Job job = (Job) params.get("job");
                    if (job.getName() != null && !job.getName().equals("")) {
                        WHERE(" name LIKE CONCAT ('%',#{job.name},'%'') ");
                    }
                }
            }
        }.toString();
    }

    public String insertJob(final Job job) {
        return new SQL() {
            {
                INSERT_INTO(JOBTABLE);
                if (job.getName() != null) {
                    VALUES("name", "#{name}");
                }
                if (job.getRemark() != null) {
                    VALUES("remark", "#{remark}");
                }
            }
        }.toString();
    }

    public String updateJob(final Job job) {
        return new SQL() {
            {
                UPDATE(JOBTABLE);
                if (job.getName() != null) {
                    SET(" name=#{name} ");
                }
                if (job.getRemark() != null) {
                    SET(" remark=#{remark}");
                }
                WHERE(" id = #{id}");
            }
        }.toString();
    }
}
