package com.evaluation.controller;

import com.evaluation.entity.AdminEntity;
import com.evaluation.entity.AdminEntityExample;
import com.evaluation.mapper.AdminEntityMapper;
import com.evaluation.utils.Layui;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author: ChenXing
 * @date: 2023/4/23 15:07
 * @Description:
 */
@Api(tags = "管理员")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminEntityMapper adminEntityMapper;

    /**
     * 添加管理员接口
     */
    @ApiOperation("添加管理员接口")
    @PostMapping("/add")
    public Integer add(@RequestBody AdminEntity entity) {
        return adminEntityMapper.insert(entity);
    }

    /**
     * 删除管理员接口
     */
    @ApiOperation("删除管理员接口")
    @PostMapping(value = "/delete", consumes = "application/json")
    public Integer delete(@RequestBody AdminEntity entity) {
        return adminEntityMapper.deleteByPrimaryKey(entity.getUserid());
    }

    /**
     * 修改管理员信息
     */
    @ApiOperation("修改管理员信息")
    @PostMapping("/update")
    public Integer update(@RequestBody AdminEntity entity) {
        return adminEntityMapper.updateByPrimaryKey(entity);
    }

    /**
     * 管理员页面-查询所有管理员账号信息业务-分页
     */
    @ApiOperation("管理员页面-查询所有管理员账号信息业务-分页")
    @RequestMapping("/select")
    public Layui select(@RequestParam(required = false) String username,@RequestParam(value = "page")Integer page,
                        @RequestParam(value = "limit")Integer limit) {
        AdminEntityExample example = new AdminEntityExample();
        if (!StringUtils.isEmpty(username)){
            example.or().andUsernameLike("%"+username+"%");
        }
        example.getOrderByClause();
        Long cou = adminEntityMapper.countByExample(example);
        return Layui.data(cou.intValue(), adminEntityMapper.selectByExamplePaging(example, page-1, limit));
    }

    /**
     * 根据用户id(管理员用户)获取管理员信息接口
     */
    @ApiOperation("根据用户id(管理员用户)获取管理员信息接口")
    @RequestMapping("/getAdmin")
    public AdminEntity getAdmin(@RequestParam(value="userid")Integer userid) {
        return adminEntityMapper.selectByPrimaryKey(userid);
    }

}
