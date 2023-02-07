package com.qfnu;

import com.qfnu.mapper.EmpMapper;
import com.qfnu.pojo.Emp;
import com.qfnu.pojo.EmpExample;
import com.qfnu.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MBGTest {

    @Test
    public void testMBG(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        //根据Id查询数据
        /*Emp emp = mapper.selectByPrimaryKey(1);
        System.out.println(emp);*/
        //查询所有数据
        /*List<Emp> list = mapper.selectByExample(null);
        list.forEach(System.out::println);*/
        //根据条件查询信息
        /*EmpExample example = new EmpExample();
        example.createCriteria().andEmpNameEqualTo("张三").andAgeGreaterThanOrEqualTo(20);
        example.or().andGenderEqualTo("男");
        List<Emp> list = mapper.selectByExample(example);
        list.forEach(System.out::println);*/
        Emp emp = new Emp(1,"小黑",null,"女");
        //测试普通修改功能
        /*mapper.updateByPrimaryKey(emp);*/
        //测试选择性修改
        mapper.updateByPrimaryKeySelective(emp);
    }
}
