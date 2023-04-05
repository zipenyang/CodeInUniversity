package com.qfnu.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfnu.commonutils.R;
import com.qfnu.eduservice.entity.EduTeacher;
import com.qfnu.eduservice.entity.vo.TeacherQuery;
import com.qfnu.eduservice.service.EduTeacherService;
import com.qfnu.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-03-31
 */
@Api(value = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    //service注入
    @Autowired
    private EduTeacherService eduTeacherService;

    //查询讲师表中的所有数据
    @ApiOperation(value = "查询所有讲师")
    @GetMapping("findAll")
    public R findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("iteams",list);
    }


    //逻辑删除讲师功能
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeById(@ApiParam(name = "id",value = "讲师id",required = true) @PathVariable("id")String id){
        boolean removed = eduTeacherService.removeById(id);
        if (removed){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //分页查询讲师的方法
    @ApiOperation(value = "分页查询")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable("current")long current,@PathVariable("limit") long limit){
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        try{
            int i = 10 / 0;
        }catch (Exception e){
            //执行自定义异常
            throw new GuliException(20001,"执行了自定义异常处理。。。。");
        }

        //调用方法实现分页
        eduTeacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();  //总记录数
        List<EduTeacher> records = pageTeacher.getRecords();  //每页数据的list集合
        Map map = new HashMap();
        map.put("total",total);
        map.put("rows",records);
        return R.ok().data(map);
    }


    //条件查询带分页的方法
    @ApiOperation(value = "条件查询带分页")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable("current")long current, @PathVariable("limit") long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        //判断条件值是否为空，如果不为空则拼接条件
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)){
            //构建条件
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            //构建条件
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            //构建条件
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            //构建条件
            wrapper.le("gmt_modified",end);
        }
        //调用方法实现分页
        eduTeacherService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();  //总记录数
        List<EduTeacher> records = pageTeacher.getRecords();  //每页数据的list集合
        Map map = new HashMap();
        map.put("total",total);
        map.put("rows",records);
        return R.ok().data(map);
    }


    //添加讲师的接口方法
    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }


    //根据讲师id进行查询
    @ApiOperation(value = "根据讲师id进行查询")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable("id") String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",teacher);
    }


    //讲师修改功能
    @ApiOperation(value = "讲师修改")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean updated = eduTeacherService.updateById(eduTeacher);
        if (updated){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

