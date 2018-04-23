package com.pxu.hrms.dao.provider;

import com.pxu.hrms.domain.Job;
import com.pxu.hrms.domain.Notice;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.pxu.hrms.util.common.HrmsConstants.NOTICETABLE;

public class NoticeDynaSqlProvider {

    public String selectWithParam(final Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(NOTICETABLE);
                if (params.get("notice") != null) {
                    Notice notice = (Notice) params.get("notice");
                    if (notice.getTitle() != null && !notice.getTitle().equals("")) {
                        WHERE(" name LIKE CONCAT ('%',%{notice.title},'%') ");
                    }
                    if (notice.getContent() != null && !notice.getContent().equals("")) {
                        WHERE(" name LIKE CONCAT ('%',%{notice.content},'%') ");
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
                FROM(NOTICETABLE);
                if (params.get("notice") != null) {
                    Notice notice = (Notice) params.get("notice");
                    if (notice.getTitle() != null && !notice.getTitle().equals("")) {
                        WHERE(" name LIKE CONCAT ('%',%{notice.title},'%') ");
                    }
                    if (notice.getContent() != null && !notice.getContent().equals("")) {
                        WHERE(" name LIKE CONCAT ('%',%{notice.content},'%') ");
                    }
                }
            }
        }.toString();
    }

    public String insertNotice(final Notice notice) {
        return new SQL() {
            {
                INSERT_INTO(NOTICETABLE);
                if (notice.getTitle() != null) {
                    VALUES("title", "#{title}");
                }
                if (notice.getContent() != null) {
                    VALUES("content", "#{content}");
                }
                if (notice.getUser() != null && notice.getUser().getId() != null) {
                    VALUES("user_id", "#{user.id}");
                }
            }
        }.toString();
    }

    public String updateJob(final Notice notice) {
        return new SQL() {
            {
                UPDATE(NOTICETABLE);
                if (notice.getTitle() != null) {
                    SET(" title=#{title} ");
                }
                if (notice.getContent() != null) {
                    SET(" content=#{content}");
                }
                if (notice.getUser() != null && notice.getUser().getId() != null) {
                    SET(" user_id=#{user.id}");
                }
                WHERE(" id = #{id}");
            }
        }.toString();
    }
}
