package com.evaluation.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author: ChenXing
 * @date: 2023/4/23 15:17
 * @Description:
 */
@ApiModel(value = "登录实体类")
@Data
public class LoginDTO {
    String username;

    String password;

    String userType;
}
