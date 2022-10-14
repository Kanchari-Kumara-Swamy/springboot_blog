package com.springboot.blog.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(description = "Comment model information")
@Data

public class CommentDto {

    @ApiModelProperty(value = "comment id")
    private long id;

    @ApiModelProperty(value = "comment email")
    @NotEmpty
    @Email
    private String email;

    @ApiModelProperty(value = "comment name")
    @NotEmpty(message = "name should not be null")
    private String name;

    @ApiModelProperty(value = "comment body")
    @NotEmpty
    @Size(min = 10, message = "min 10 chars needed")
    private String body;
}
