package com.evaluation.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author: ChenXing
 * @date: 2023/4/25 22:57
 * @Description:
 */
@ApiModel(value = "评价实体类")
@Data
public class PJDTO {

    private Integer id;

    private Integer zongfen;

    private String shijian;

    private String teacherName;

    private String studentName;

}
