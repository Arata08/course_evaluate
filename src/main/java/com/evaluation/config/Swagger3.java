package com.evaluation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger3 {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30).apiInfo(
                new ApiInfoBuilder()
                .contact(new Contact("ChenXing","","cxuser@foxmail.com"))
                .title("课程评价系统")
                .build()
        );
    }

// @Api: 修饰整个类，描述Controller的作用
//    @Api(value = "注册登陆", tags = "注册登陆相关接口")
// @ApiOperation: 描述一个类的一个方法，或者说一个接口
//    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
//    @ApiParam            // 单个参数描述
// @ApiModel: 用对象来接收参数
//    @ApiModel(value = "用户对象BO", description = "用户从客户端传入的数据的封装")
// @ApiModelProperty: 用对象接收参数时，描述对象的一个字段
//    @ApiModelProperty(value = "用户名", name = "username", example = "Orcas", required = true)
//    @ApiResponse        // HTTP响应其中1个描述
//    @ApiResponses       // HTTP响应整体描述
//    @ApiIgnore            // 使用该注解忽略这个API
//    @ApiError 		    // 发生错误返回的信息
//    @ApiImplicitParam   // 一个请求参数
//    @ApiImplicitParams  // 多个请求参数
//   =》 @ApiImplicitParams({
//            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
//            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")})
}
