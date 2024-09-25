package com.evaluation.controller;

import com.evaluation.entity.IndexTableEntity;
import com.evaluation.entity.IndexTableEntityExample;
import com.evaluation.mapper.IndexTableEntityMapper;
import com.evaluation.utils.Layui;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: ChenXing
 * @date: 2023/4/23 21:50
 * @Description:
 */
@RestController
@Api(tags = "指标信息")
@RequestMapping("/first")
public class IndexController {

    @Autowired
    private IndexTableEntityMapper indexTableEntityMapper;

    /**
     * 新增指标信息
     */
    @ApiOperation("新增指标信息")
    @PostMapping("/add")
    public Integer add(@RequestBody IndexTableEntity entity) {
        if (StringUtils.isEmpty(entity.getParentId())) {
            entity.setParentId(null);
        }
        return indexTableEntityMapper.insert(entity);
    }

    /**
     * 删除指标信息
     */
    @ApiOperation("删除指标信息")
    @PostMapping(value = "/delete", consumes = "application/json")
    public Integer delete(@RequestBody IndexTableEntity entity) {
        return indexTableEntityMapper.deleteByPrimaryKey(entity.getId());
    }

    /**
     * 修改指标信息
     */
    @ApiOperation("修改指标信息")
    @PostMapping("/update")
    public Integer update(@RequestBody IndexTableEntity entity) {
        return indexTableEntityMapper.updateByPrimaryKey(entity);
    }

    /**
     * 管理员管理列表页面
     * 分页展示所有指标信息
     */
    @ApiOperation("管理员管理列表页面")
    @RequestMapping("/select")
    public Layui select(@RequestParam(required = false) String indexname, @RequestParam(value = "page") Integer page,
                        @RequestParam(value = "limit") Integer limit) {
        IndexTableEntityExample example = new IndexTableEntityExample();
        IndexTableEntityExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(indexname)) {
            criteria.andIndexnameLike("%"+indexname+"%");
        }
        criteria.andParentIdIsNotNull();
        Long cou = indexTableEntityMapper.countByExample(example);
        return Layui.data(cou.intValue(), indexTableEntityMapper.selectByExamplePaging(example, page - 1, limit));
    }

    /**
     * 删除指标信息
     */
    @ApiOperation("删除指标信息")
    @RequestMapping("/selectFir")
    public List<IndexTableEntity> selectFir() {
        IndexTableEntityExample example = new IndexTableEntityExample();
        example.or().andParentIdIsNull();
        return indexTableEntityMapper.selectByExample(example);
    }

    @ApiOperation("获取所有指标信息")
    @RequestMapping("/selectList")
    public List<IndexTableEntity> selectList() {
        IndexTableEntityExample example = new IndexTableEntityExample();
        example.or().andParentIdIsNotNull();
        return indexTableEntityMapper.selectByExample(example);
    }

    @ApiOperation("获取first指标信息")
    @RequestMapping("/getFirst")
    public IndexTableEntity getAdmin(@RequestParam(value = "id") Integer userid) {
        return indexTableEntityMapper.selectByPrimaryKey(userid);
    }
}
