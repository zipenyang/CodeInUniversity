package com.mybatis_plus0326;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatis_plus0326.entity.User;
import com.mybatis_plus0326.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlus0326ApplicationTests {

	@Autowired
	private UserMapper userMapper;

	//查询user表所有数据
	@Test
	public void findAll(){
		List<User> users = userMapper.selectList(null);
		System.out.println(users);
	}

	//添加操作
	@Test
	public void addUser(){
		User user = new User();
		user.setName("Mary");
		user.setAge(20);
		user.setEmail("12345@qq.com");
		int insert = userMapper.insert(user);
		System.out.println("insert:"+insert);
	}

	//修改操作
	@Test
	public void updateUser(){
		User user = new User();
		user.setId(1640377574159302657L);
		user.setAge(120);
		int update = userMapper.updateById(user);
		System.out.println(update);
	}


	@Test
	public void testOptimisticLock(){
		//根据id查询数据
		User user = userMapper.selectById(1641359200670568449L);
		//进行修改
		user.setAge(30);
		//模拟取出数据后，数据库中version实际数据比取出的值大，即已被其它线程修改并更新了version
		user.setVersion(user.getVersion() - 1);
		//执行更新
		userMapper.updateById(user);
		int update = userMapper.updateById(user);
		System.out.println(update);
	}


	//多个id批量查询
	@Test
	public void testSelectDemo1(){
		List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
		System.out.println(users);
	}


	//简单的条件查询
	@Test
	public void testSelectByMap(){
		HashMap<String,Object> map = new HashMap<>();
		map.put("name","Jone");
		map.put("age",18);
		List<User> users = userMapper.selectByMap(map);
		System.out.println(users);
	}


	//分页查询
	@Test
	public void testPage(){
		//1.创建Page对象
		//传入两个参数，当前页 和 每页显示记录数
		Page<User> page = new Page<>(1,3);
		//2.调用mybatis-plus中的分页查询方法
		//调用mp查询过程中，底层封装
		//把所有分页数据封装到page对象里面
		userMapper.selectPage(page,null);
		//3.通过page对象获取分页数据
		System.out.println(page.getCurrent()); //当前页
		System.out.println(page.getRecords());  //每页数据的list集合
		System.out.println(page.getSize());  //每页显示记录数
		System.out.println(page.getTotal());  //总记录数
		System.out.println(page.getPages());  //总页数
		System.out.println(page.hasNext());  //是否有下一页
		System.out.println(page.hasPrevious());  //是否有上一页
	}


	//删除操作  物理删除
	@Test
	public void deleteUser(){
		int delete = userMapper.deleteById(7L);
		System.out.println(delete);
	}

	//批量删除
	@Test
	public void testDeleteUsers(){
		int batchIds = userMapper.deleteBatchIds(Arrays.asList(6));
		System.out.println(batchIds);
	}


	//mp实现复杂查询操作
	@Test
	public void testSelectQuery(){
		//创建对象
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		//通过QueryWrapper设置条件
		/*
		* ge、gt、le、lt
		* >=、>、<=、<
		* wrapper.ge("age",30);
		* List<User> users = userMapper.selectList(wrapper);
		* System.out.println(users);
		* eq、ne
		* =、!=
		* wrapper.eq("age",30);
		* wrapper.ne("age",30);
		* between  区间查询
		* wrapper.between("age",30,150);
		* like  模糊查询
		* wrapper.like("name","杨");
		* orderByDesc  降序排列
		* wrapper.orderByDesc("id");
		* last  sql拼接
		* wrapper.last("limit 1");
		* 指定要查询的列
		* wrapper.select("id","name");
		* */
		wrapper.select("id","name");
		List<User> users = userMapper.selectList(wrapper);
		System.out.println(users);
	}


}
