package com.springboot.blog.payload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@ApiModel(description = "Post model information")
@Data
public class PostDto {

    @ApiModelProperty(value = "Blog post Id")
    private Long id;

    @ApiModelProperty(value = "Blog post title")
    @NotEmpty
    @Size(min = 2, message = "Post Dto should have min 2 chars")
    private String title;

    @ApiModelProperty(value = "Blog post description")
    @NotEmpty
    @Size(min = 10, message = "Post Dto should have min 10 chars")
    private String description;

    @ApiModelProperty(value = "Blog post content")
    @NotEmpty
    private String content;

    @ApiModelProperty(value = "Blog post comment")
    private Set<CommentDto> comments;

}
