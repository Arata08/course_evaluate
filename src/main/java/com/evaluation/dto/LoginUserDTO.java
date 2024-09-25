package com.evaluation.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author: ChenXing
 * @date: 2023/4/26 00:08
 * @Description:
 */
@ApiModel(value = "LoginUserDTO")
@Data
public class LoginUserDTO {

    private Integer id;

    private String username;

    private String password;
}
