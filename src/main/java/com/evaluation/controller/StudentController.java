package com.evaluation.controller;

import com.alibaba.fastjson.JSONObject;
import com.evaluation.entity.StudentEntity;
import com.evaluation.entity.StudentEntityExample;
import com.evaluation.mapper.StudentEntityMapper;
import com.evaluation.utils.Layui;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author: ChenXing
 * @date: 2023/4/23 15:40
 * @Description:
 */
@RestController
@Api(tags = "学生管理")
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentEntityMapper studentEntityMapper;

    /**
     * 新增学生信息接口
     */
    @ApiOperation("新增学生信息接口")
    @PostMapping("/add")
    public Integer add(@RequestBody StudentEntity entity) {
        return studentEntityMapper.insert(entity);
    }

    /**
     * 删除学生信息接口
     */
    @ApiOperation("删除学生信息接口")
    @PostMapping("/delete")
    public Integer delete(@RequestBody StudentEntity entity) {
        return studentEntityMapper.deleteByPrimaryKey(entity.getStuId());
    }

    /**
     * 修改学生信息
     */
    @ApiOperation("修改学生信息")
    @PostMapping("/update")
    public Integer update(@RequestBody StudentEntity entity) {
        return studentEntityMapper.updateByPrimaryKey(entity);
    }

    /**
     * 管理员页面-查询所有学生信息接口-分页
     */
    @ApiOperation("管理员页面-查询所有学生信息接口-分页")
    @RequestMapping("/select")
    public Layui select(@RequestParam(required = false) String stuRealname, @RequestParam(value = "page") Integer page,
                        @RequestParam(value = "limit") Integer limit) {
        StudentEntityExample example = new StudentEntityExample();
        if (!StringUtils.isEmpty(stuRealname)){
            example.or().andStuRealnameLike("%"+stuRealname+"%");
        }
        Long cou = studentEntityMapper.countByExample(example);
        return Layui.data(cou.intValue(), studentEntityMapper.selectByExamplePaging(example, page - 1, limit));
    }

    /**
     * 获取学生详情接口
     */
    @ApiOperation("获取学生详情接口")
    @RequestMapping("/getStudent")
    public StudentEntity getAdmin(@RequestParam(value="stuId")Integer stuId) {
        return studentEntityMapper.selectByPrimaryKey(stuId);
    }
}
