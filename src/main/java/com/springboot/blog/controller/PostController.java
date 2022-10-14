package com.springboot.blog.controller;

import com.springboot.blog.constants.AppConstants;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.response.PostResponse;
import com.springboot.blog.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@Api(value = "CURD REST API for Post resources")
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;


    //constructor
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation(value = " Create Post rest api")
    // a condition for user to get access
    @PreAuthorize("hasRole('ADMIN')")
    //create blog post
    @PostMapping
    //@Valid is to validate the data we are accepting from client, like max char is 10 etc
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
            return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = " Get all Post rest api")
    //get all post rest api
    @GetMapping
    public PostResponse getAllPosts(
            // getting params from url
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation(value = " Get Post by id rest api")
    //get post by Id api
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long Id){
        return ResponseEntity.ok(postService.getPostById(Id));
    }


    @ApiOperation(value = " Update Post by id rest api")

    @PreAuthorize("hasRole('ADMIN')")
    //update post by Id rest api
    @PutMapping("/{id}")

    public ResponseEntity<PostDto> updatePostById(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long Id){
        return new ResponseEntity<>(postService.updatePostById(postDto, Id), HttpStatus.OK);
    }

    @ApiOperation(value = " Delete Post by id rest api")

    @PreAuthorize("hasRole('ADMIN')")
    //delete post rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deletePostById(@PathVariable(name = "id") long Id ){
        postService.deletePostById(Id);
        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }

}

class Solution {
    public boolean isPalindrome(int x) {
        String s = Integer.toString(x);
        StringBuffer str = new StringBuffer(s);
        if(str.reverse().toString().equals(s)) return true;
        else return false;
    }
}