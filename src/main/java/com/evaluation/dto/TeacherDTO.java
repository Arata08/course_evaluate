package com.evaluation.dto;

import com.evaluation.entity.TeacherEntity;
import io.swagger.annotations.ApiModel;

/**
 * @author: ChenXing
 * @date: 2023/4/23 19:48
 * @Description:
 */
@ApiModel(value = "老师实体类")
public class TeacherDTO extends TeacherEntity {

    Integer[] courseIds;

    public Integer[] getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(Integer[] courseIds) {
        this.courseIds = courseIds;
    }
}
