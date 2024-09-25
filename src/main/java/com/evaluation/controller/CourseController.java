package com.evaluation.controller;

import com.alibaba.fastjson.JSONObject;
import com.evaluation.dto.LoginUserDTO;
import com.evaluation.entity.*;
import com.evaluation.enums.UserTypeEnum;
import com.evaluation.mapper.*;
import com.evaluation.utils.Layui;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: ChenXing
 * @date: 2023/4/23 23:03
 * @Description:
 */
@RestController
@Api(tags = "课程管理")
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseEntityMapper courseEntityMapper;
    @Autowired
    private AdminEntityMapper adminEntityMapper;
    @Autowired
    private TeacherEntityMapper teacherEntityMapper;
    @Autowired
    private CourseTeaEntityMapper courseTeaEntityMapper;
    @Autowired
    private StudentEntityMapper studentEntityMapper;

    @PostMapping("/add")
    public Integer add(@RequestBody CourseEntity entity) {
        return courseEntityMapper.insert(entity);
    }

    @PostMapping(value = "/delete", consumes = "application/json")
    public Integer delete(@RequestBody CourseEntity entity) {
        return courseEntityMapper.deleteByPrimaryKey(entity.getId());
    }

    /**
     * 修改课程信息接口
     */
    @ApiOperation("修改课程信息接口")
    @PostMapping("/update")
    public Integer update(@RequestBody CourseEntity entity) {
        return courseEntityMapper.updateByPrimaryKey(entity);
    }

    /**
     * 管理员页面-评分流程查询
     * 教师查看自己关联课程信息
     */
    @ApiOperation("管理员页面-评分流程查询")
    @RequestMapping("/select")
    public Layui select(@RequestParam(required = false) String courseName, @RequestParam(value = "page",defaultValue = "1") Integer page,
                        @RequestParam(value = "limit",defaultValue = "100") Integer limit) {
        CourseEntityExample example = new CourseEntityExample();
        if (!StringUtils.isEmpty(courseName)) {
            example.or().andCourseNameLike("%"+courseName+"%");
        }
        Long cou = courseEntityMapper.countByExample(example);
        return Layui.data(cou.intValue(), courseEntityMapper.selectByExamplePaging(example, page - 1, limit));
    }

    /**
     * 根据id获取课程详情接口
     */
    @ApiOperation("根据id获取课程详情接口")
    @RequestMapping("/getCourse")
    public CourseEntity getAdmin(@RequestParam(value = "id") Integer id) {
        return courseEntityMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据所选课程,获取当前课程关联老师以及评价信息
     * 分页
     */
    @ApiOperation("根据所选课程,获取当前课程关联老师以及评价信息")
    @RequestMapping("/queryTeacherByCourseId")
    public Layui queryTeacherByCourseId(@RequestParam(value = "id") Integer id) {
        CourseTeaEntityExample example = new CourseTeaEntityExample();
        example.or().andCourseIdEqualTo(id);
        List<CourseTeaEntity> courseTeaEntities = courseTeaEntityMapper.selectByExample(example);
        if (courseTeaEntities.size() == 0) {
            return null;
        }
        TeacherEntityExample teacherEntityExample = new TeacherEntityExample();
        teacherEntityExample.or().andTeaIdIn(courseTeaEntities.stream().map(CourseTeaEntity::getTeacherId).collect(Collectors.toList()));
        List<TeacherEntity> teacherEntities = teacherEntityMapper.selectByExample(teacherEntityExample);
        return Layui.data(teacherEntities.size(), teacherEntities);
    }

    /**
     * 修改登录用户信息
     * 当前登录用户修改用户名密码业务
     */
    @ApiOperation("修改登录用户信息")
    @RequestMapping("/updateLoginUser")
    public Integer updateLoginUser(@RequestBody LoginUserDTO loginUserDTO) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String loginType = session.getAttribute("loginUserType").toString();
        UserTypeEnum userTypeEnum = UserTypeEnum.getEventByCode(loginType);
        int userDTO = 0;
        switch (userTypeEnum) {
            case ADMIN:
                AdminEntity adminEntity = new AdminEntity();
                adminEntity.setUserid(loginUserDTO.getId());
                adminEntity.setUsername(loginUserDTO.getUsername());
                adminEntity.setUserpw(loginUserDTO.getPassword());
                userDTO = adminEntityMapper.updateByPrimaryKeySelective(adminEntity);
                break;
            case TEACHER:
                TeacherEntity teacherEntity = new TeacherEntity();
                teacherEntity.setTeaId(loginUserDTO.getId());
                teacherEntity.setLoginName(loginUserDTO.getUsername());
                teacherEntity.setLoginPw(loginUserDTO.getPassword());
                userDTO = teacherEntityMapper.updateByPrimaryKey(teacherEntity);
                break;
            case STUDENT:
                StudentEntity studentEntity = new StudentEntity();
                studentEntity.setStuId(loginUserDTO.getId());
                studentEntity.setLoginName(loginUserDTO.getUsername());
                studentEntity.setLoginPw(loginUserDTO.getPassword());
                userDTO = studentEntityMapper.updateByPrimaryKey(studentEntity);
                break;
            default:
                break;
        }
        return userDTO;
    }

    /**
     * 获取当前登录用户的用户名密码业务
     */
    @ApiOperation("获取当前登录用户的用户名密码业务")
    @RequestMapping("/getLoginUser")
    public LoginUserDTO getLoginUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String loginType = session.getAttribute("loginUserType").toString();
        String loginUserId = session.getAttribute("loginUserId").toString();
        UserTypeEnum userTypeEnum = UserTypeEnum.getEventByCode(loginType);
        LoginUserDTO userDTO = new LoginUserDTO();
        switch (userTypeEnum) {
            case ADMIN:
                AdminEntity adminEntity = adminEntityMapper.selectByPrimaryKey(Integer.parseInt(loginUserId));
                userDTO.setId(adminEntity.getUserid());
                userDTO.setUsername(adminEntity.getUsername());
                userDTO.setPassword(adminEntity.getUserpw());
                break;
            case TEACHER:
                TeacherEntity teacherEntity = teacherEntityMapper.selectByPrimaryKey(Integer.parseInt(loginUserId));
                userDTO.setId(teacherEntity.getTeaId());
                userDTO.setUsername(teacherEntity.getLoginName());
                userDTO.setPassword(teacherEntity.getLoginPw());
                break;
            case STUDENT:
                StudentEntity studentEntity = studentEntityMapper.selectByPrimaryKey(Integer.parseInt(loginUserId));
                userDTO.setId(studentEntity.getStuId());
                userDTO.setUsername(studentEntity.getLoginName());
                userDTO.setPassword(studentEntity.getLoginPw());
                break;
            default:
                break;
        }
        System.out.println(JSONObject.toJSONString(userDTO));
        return userDTO;
    }
}
