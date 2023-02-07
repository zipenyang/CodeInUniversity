package com.qfnu.ssm.service.impl;

import com.qfnu.ssm.mapper.EmployeeMapper;
import com.qfnu.ssm.pojo.Employee;
import com.qfnu.ssm.service.EmployeeService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeMapper employeeMapper;

    /**
     * 查询所有用户信息
     * @return
     */
    @Override
    public List<Employee> getAllEmployee() {
        return null;
    }
}
