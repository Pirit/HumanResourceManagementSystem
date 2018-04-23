package com.pxu.hrms.dao;

import com.pxu.hrms.dao.provider.DocumentDynaSqlProvider;
import com.pxu.hrms.dao.provider.NoticeDynaSqlProvider;
import com.pxu.hrms.domain.Document;
import com.pxu.hrms.domain.Notice;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.pxu.hrms.util.common.HrmsConstants.DOCUMENTTABLE;

public interface DocumentDao {

    @SelectProvider(type = DocumentDynaSqlProvider.class, method = "selectWithParam")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "CREATE_DATE", property = "createDate", javaType = Date.class),
            @Result(column = "USER_ID", property = "user", one = @One(select = "com.pxu.hrms.dao.UserDao.selectById", fetchType = FetchType.EAGER)),
    })
    List<DocumentDao> selectByOage(Map<String, Object> params);

    @SelectProvider(type = DocumentDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    @Select("select * from " + DOCUMENTTABLE + " where id=#{id} ")
    Notice selectById(Integer id);

    @Select("delete from " + DOCUMENTTABLE + " where id=#{id} ")
    void deleteById(Integer id);

    @SelectProvider(type = DocumentDynaSqlProvider.class, method = "insertDocument")
    void save(Document document);

    @SelectProvider(type = DocumentDynaSqlProvider.class, method = "updateDocument")
    void update(Document document);

}
