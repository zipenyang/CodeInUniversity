package com.qfnu;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qfnu.mapper.EmpMapper;
import com.qfnu.pojo.Emp;
import com.qfnu.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class PageTest {

    @Test
    public void testPAge(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        //查询功能之前开启分页功能
        PageHelper.startPage(1,4);
        List<Emp> list = mapper.selectByExample(null);
        //查询功能之后可以获取分页相关的所有数据
        PageInfo<Emp> pageInfo = new PageInfo<>(list,5);
        list.forEach(System.out::println);
        System.out.println(pageInfo);
    }
}
