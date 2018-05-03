package com.pxu.hrms.service.impl;

import com.pxu.hrms.dao.*;
import com.pxu.hrms.domain.*;
import com.pxu.hrms.service.HrmsService;
import com.pxu.hrms.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
@Service("hrmsService")
public class HrmsServiceImpl implements HrmsService {

    //    Auto-Inject Dao
    @Autowired
    private UserDao userDao;
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private JobDao jobDao;
    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private DocumentDao documentDao;

    /*****************User Service Implements**************************************************************/
    @Transactional(readOnly = true)
    @Override
    public User login(String loginname, String password) {
        System.out.println("HrmsServiceImpl Login -- >>");
        return userDao.selectByLoginnameAndPassword(loginname, password);
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserById(Integer id) {
        return userDao.selectById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findUser(User user, PageModel pageModel) {
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        int recordCount = userDao.count(params);
        System.out.println("recordCount -->> " + recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }
        List<User> users = userDao.selectByPage(params);
        return users;
    }

    @Override
    public void removeUserById(Integer id) {
        userDao.deleteById(id);
    }

    @Override
    public void modifyUser(User user) {
        userDao.update(user);
    }

    @Override
    public void addUser(User user) {
        userDao.save(user);
    }

    /***********************Employee Service Implements*************************************************************/
    @Override
    @Transactional(readOnly = true)
    public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
        Map<String, Object> params = new HashMap<>();
        params.put("employee", employee);
        int recordCount = employeeDao.count(params);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }
        return employeeDao.selectByPage(params);
    }

    @Override
    public void removeEmployeeById(Integer id) {
        employeeDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee findEmployeeById(Integer id) {
        return employeeDao.selectById(id);
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public void modifyEmployee(Employee employee) {
        employeeDao.update(employee);
    }


    /***********************Dept Service Implements*************************************************************/
    @Override
    @Transactional(readOnly = true)
    public List<Dept> findDept(Dept dept, PageModel pageModel) {
        Map<String, Object> params = new HashMap<>();
        params.put("dept", dept);
        int recordCount = deptDao.count(params);
        System.out.println("recordCount -->> " + recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }
        List<Dept> depts = deptDao.selectByPage(params);
        return depts;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Dept> findAllDept() {
        return deptDao.selectAllDept();
    }

    @Override
    public void removeDeptById(Integer id) {
        deptDao.deleteById(id);
    }

    @Override
    public void addDept(Dept dept) {
        deptDao.save(dept);
    }

    @Override
    @Transactional(readOnly = true)
    public Dept findDeptById(Integer id) {
        return deptDao.selectById(id);
    }

    @Override
    public void modifyDept(Dept dept) {
        deptDao.update(dept);
    }

    /***********************Job Service Implements*************************************************************/

    @Override
    @Transactional(readOnly = true)
    public List<Job> findJob(Job job, PageModel pageModel) {
        Map<String, Object> params = new HashMap<>();
        params.put("job", job);
        int recordCount = jobDao.count(params);
        System.out.println("recordCount --> " + recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }
        List<Job> jobs = jobDao.selectByPage(params);
        return jobs;
    }

    @Override
    public void removeJobById(Integer id) {
        jobDao.deleteById(id);
    }

    @Override
    public void addJob(Job job) {
        jobDao.save(job);
    }

    @Override
    @Transactional(readOnly = true)
    public Job findJobById(Integer id) {
        return jobDao.selectById(id);
    }

    @Override
    public void modifyJob(Job job) {
        jobDao.update(job);
    }

    /***********************Notice Service Implements*************************************************************/

    @Override
    @Transactional(readOnly = true)
    public List<Notice> findNotice(Notice notice, PageModel pageModel) {
        Map<String, Object> params = new HashMap<>();
        params.put("notice", notice);
        int recordCount = noticeDao.count(params);
        System.out.println("recordCount -->> " + recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0) {
            params.put("pagemodel", pageModel);
        }
        return noticeDao.selectByOage(params);
    }

    @Override
    public void removeNoticeById(Integer id) {
        noticeDao.deleteById(id);
    }

    @Override
    public void addNotice(Notice notice) {
        noticeDao.save(notice);
    }

    @Override
    @Transactional(readOnly = true)
    public Notice findNoticeById(Integer id) {
        return noticeDao.selectById(id);
    }

    @Override
    public void modifyNotice(Notice notice) {
        noticeDao.update(notice);
    }

    /***********************Document Service Implements*************************************************************/

    @Override
    @Transactional(readOnly = true)
    public List<Document> findDocument(Document document, PageModel pageModel) {
        Map<String, Object> params = new HashMap<>();
        params.put("document", document);
        int recordCount = documentDao.count(params);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }
        return documentDao.selectByOage(params);
    }

    @Override
    @Transactional(readOnly = true)
    public Document findDocumentById(Integer id) {
        return documentDao.selectById(id);
    }

    @Override
    public void removeDocumentById(Integer id) {
        documentDao.deleteById(id);
    }

    @Override
    public void addDocument(Document document) {
        documentDao.save(document);
    }

    @Override
    public void modifyDocument(Document document) {
        documentDao.update(document);
    }
}
