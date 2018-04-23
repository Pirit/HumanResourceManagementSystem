package com.pxu.hrms.dao.provider;

import com.pxu.hrms.domain.Document;
import com.pxu.hrms.domain.Notice;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.pxu.hrms.util.common.HrmsConstants.DOCUMENTTABLE;

public class DocumentDynaSqlProvider {

    public String selectWithParam(final Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(DOCUMENTTABLE);
                if (params.get("document") != null) {
                    Document document = (Document) params.get("document");
                    if (document.getTitle() != null && !document.getTitle().equals("")) {
                        WHERE(" name LIKE CONCAT ('%',%{document.title},'%') ");
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
                FROM(DOCUMENTTABLE);
                if (params.get("document") != null) {
                    Document document = (Document) params.get("document");
                    if (document.getTitle() != null && !document.getTitle().equals("")) {
                        WHERE(" name LIKE CONCAT ('%',%{document.title},'%') ");
                    }
                }
            }
        }.toString();
    }

    public String insertNotice(final Document document) {
        return new SQL() {
            {
                INSERT_INTO(DOCUMENTTABLE);
                if (document.getTitle() != null && !document.getTitle().equals("")) {
                    VALUES("title", "#{title}");
                }
                if (document.getFileName() != null && !document.getFileName().equals("")) {
                    VALUES("filename", "#{fileName}");
                }
                if (document.getRemark() != null && !document.getRemark().equals("")) {
                    VALUES("remark", "#{remark}");
                }
                if (document.getUser() != null && document.getUser().getId() != null) {
                    VALUES("user_id", "#{user.id}");
                }
            }
        }.toString();
    }

    public String updateJob(final Document document) {
        return new SQL() {
            {
                UPDATE(DOCUMENTTABLE);
                if (document.getTitle() != null && !document.getTitle().equals("")) {
                    SET(" title=#{title} ");
                }
                if (document.getFileName() != null && !document.getFileName().equals("")) {
                    SET(" filename=#{filename}");
                }
                if (document.getRemark() != null && !document.getRemark().equals("")) {
                    SET(" remark=#{remark}");
                }
                if (document.getUser() != null && document.getUser().getId() != null) {
                    SET(" user_id=#{user.id}");
                }
                WHERE(" id = #{id}");
            }
        }.toString();
    }
}
