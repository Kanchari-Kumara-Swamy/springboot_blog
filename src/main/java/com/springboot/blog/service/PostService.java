package com.springboot.blog.service;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.response.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long Id);

    PostDto updatePostById(PostDto postDto,long Id);

    void deletePostById(long Id);
}
