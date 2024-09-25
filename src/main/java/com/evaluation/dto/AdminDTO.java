package com.evaluation.dto;

import com.evaluation.entity.AdminEntity;
import io.swagger.annotations.ApiModel;

/**
 * @author: ChenXing
 * @date: 2023/4/23 18:52
 * @Description:
 */
@ApiModel(value = "管理员实体类")
public class AdminDTO extends AdminEntity {

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
