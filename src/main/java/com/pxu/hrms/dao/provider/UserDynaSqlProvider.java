package com.pxu.hrms.dao.provider;

import static com.pxu.hrms.util.common.HrmsConstants.USERTABLE;

import com.pxu.hrms.domain.User;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class UserDynaSqlProvider {

    public String selectWithParam(final Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(USERTABLE);
                if (params.get("user") != null) {
                    User user = (User) params.get("user");
                    if (user.getUsername() != null && !user.getUsername().equals("")) {
                        WHERE(" username LIKE CONCAT ('%', #{user.username},'%') ");
                    }
                    if (user.getStatus() != null && !user.getStatus().equals("")) {
                        WHERE(" status LIKE CONCAT ('%',#{user.status},'%') ");
                    }
                }
            }
        }.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam},#{pageModel.pageSize} ";
        }
        return sql;
    }

    public String count(final Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM(USERTABLE);
                if (params.get("user") != null) {
                    User user = (User) params.get("user");
                    if (user.getUsername() != null && !user.getUsername().equals("")) {
                        WHERE(" username LIKE CONCAT ('%',#{user.username},'%') ");
                    }
                    if (user.getStatus() != null && !user.getStatus().equals("")) {
                        WHERE(" status LIKE CONCAT ('%',#{user.status},'%') ");
                    }
                }
            }
        }.toString();
    }

    public String insertUser(final User user) {
        return new SQL() {
            {
                INSERT_INTO(USERTABLE);
                if (user.getUsername() != null && !user.getUsername().equals("")) {
                    VALUES("username", "#{username}");
                }
                if (user.getStatus() != null && !user.getStatus().equals("")) {
                    VALUES("status", "#{status}");
                }
                if (user.getLoginname() != null && !user.getLoginname().equals("")) {
                    VALUES("loginname", "#{loginname}");
                }
                if (user.getPassword() != null && !user.getPassword().equals("")) {
                    VALUES("password", "#{password}");
                }
            }
        }.toString();
    }

    public String updateUser(final User user) {
        return new SQL() {
            {
                UPDATE(USERTABLE);
                if (user.getUsername() != null) {
                    SET(" username=#{username} ");
                }
                if (user.getStatus() != null) {
                    SET(" status=#{status} ");
                }
                if (user.getLoginname() != null) {
                    SET(" loginname=#{loginname} ");
                }
                if (user.getPassword() != null) {
                    SET(" password=#{password} ");
                }
                if (user.getCreateDate() != null) {
                    SET(" create_date=#{createDate} ");
                }
                WHERE(" id=#{id}");
            }
        }.toString();
    }
}
