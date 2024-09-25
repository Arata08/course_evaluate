package com.evaluation.dto;

import com.evaluation.entity.StudentEntity;
import io.swagger.annotations.ApiModel;

/**
 * @author: ChenXing
 * @date: 2023/4/23 19:49
 * @Description:
 */
@ApiModel(value = "学生实体类")
public class StudentDTO extends StudentEntity {

    private Integer page;

    private Integer limit;


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
