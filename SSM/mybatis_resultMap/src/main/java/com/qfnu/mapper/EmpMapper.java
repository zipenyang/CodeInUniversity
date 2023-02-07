package com.qfnu.mapper;

import com.qfnu.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {

    /**
     *根据部门id查新部门以及部门中的员工信息
     * @param empid
     * @return
     */
    Emp getEmpByEmpId(@Param("empid") Integer empid);

    /**
     * 获取员工及其对应的部门信息
     * @param empid
     * @return
     */
    Emp getEmpAndDeptByEmpID(@Param("empid") Integer empid);

    /**
     * 通过分步查询获取员工及其对应的部门信息的第一步
     * @param empid
     * @return
     */
    Emp getEmpAndDeptByStepOne(@Param("empid") Integer empid);

    /**
     * 通过分步查询查询部门以及部门中的员工信息的第二步
     * @param deptId
     * @return
     */
    List<Emp> getDeptAndEmpBySteptwo(@Param("deptId") Integer deptId);
}
