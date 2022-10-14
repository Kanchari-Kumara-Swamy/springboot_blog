package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFound;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.response.PostResponse;
import com.springboot.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PostRepository postRepository;


    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

       //DTO to entity
        Post post = mapToEntity(postDto);

        Post newPost = postRepository.save(post);

        //entity to Dto
        PostDto postResponse = mapToDto(newPost);
        return postResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);
        //get content for page obj
        List<Post> listOfPosts = posts.getContent();
        List<PostDto> content = listOfPosts.stream().map(this::mapToDto).collect(Collectors.toList());

        //using post response to pass the values

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize((posts.getSize()));
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(long Id) {

        Post post = postRepository.findById(Id).orElseThrow(() -> new ResourceNotFound("Post", "id", Id));
        return mapToDto(post);
    }
    @Override
    public PostDto updatePostById(PostDto postDto, long Id) {
        //get post by Id from DB
        Post post = postRepository.findById(Id).orElseThrow(() -> new ResourceNotFound("Post", "id", Id));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(post.getDescription());

        Post updatePost = postRepository.save(post);

        return mapToDto(updatePost);
    }

    // delete post by ID
    @Override
    public void deletePostById(long Id) {
        Post post = postRepository.findById(Id).orElseThrow(() -> new ResourceNotFound("Post", "id", Id));
        postRepository.delete(post);
    }


    //entity to Dto
    private PostDto mapToDto(Post post){

        PostDto postDto = mapper.map(post, PostDto.class);
        /*
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        */
        return postDto;
    }


    //DTO to entity
    private Post mapToEntity(PostDto postDto){

        Post post = mapper.map(postDto, Post.class);
        /*
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        */
        return post;
    }
}
