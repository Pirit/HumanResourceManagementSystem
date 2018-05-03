package com.pxu.hrms.service;

import com.pxu.hrms.domain.*;
import com.pxu.hrms.util.tag.PageModel;

import java.util.List;

public interface HrmsService {

    /**
     * User Login
     *
     * @param loginname
     * @param password
     * @return User
     */
    User login(String loginname, String password);

    //    User Service
    User findUserById(Integer id);

    List<User> findUser(User user, PageModel pageModel);

    void removeUserById(Integer id);

    void modifyUser(User user);

    void addUser(User user);

    //    Employee Service
    List<Employee> findEmployee(Employee employee, PageModel pageModel);

    void removeEmployeeById(Integer id);

    Employee findEmployeeById(Integer id);

    void  addEmployee(Employee employee);

    void modifyEmployee(Employee employee);

//    Dept Service
    List<Dept> findDept(Dept dept, PageModel pageModel);

    List<Dept> findAllDept();

    void removeDeptById(Integer id);

    void addDept(Dept dept);

    Dept findDeptById(Integer id);

    void modifyDept(Dept dept);

//    Job Service
    List<Job> findJob(Job job, PageModel pageModel);

    void removeJobById(Integer id);

    void addJob(Job job);

    Job findJobById(Integer id);

    void modifyJob(Job job);

//    Notice Service
    List<Notice> findNotice(Notice notice, PageModel pageModel);

    void  removeNoticeById(Integer id);

    void addNotice(Notice notice);

    Notice findNoticeById(Integer id);

    void modifyNotice(Notice notice);

//    Document Service
    List<Document> findDocument(Document document, PageModel pageModel);

    Document findDocumentById(Integer id);

    void removeDocumentById(Integer id);

    void  addDocument(Document document);

    void modifyDocument(Document document);
}
