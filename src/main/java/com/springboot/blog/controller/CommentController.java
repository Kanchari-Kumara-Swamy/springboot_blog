package com.springboot.blog.controller;


import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/vi")
// not adding Swagger API customizations here to know the difference
public class CommentController {

    @Autowired
    private CommentService commentService;

    //Create a comment api
    @PostMapping("posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment( @PathVariable(name = "postId") long postId,
                                                    @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    //getting comments by post id api
    @GetMapping("posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") long postId){
        return commentService.getCommentsById(postId);
    }

    //getting a single comment id api
    @GetMapping("posts/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "postId") long postId,
                                                     @PathVariable(name = "commentId") long commentId){
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
    }

    //updating comment by id api
    @PutMapping("posts/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable(name = "postId") long postId,
                                                         @PathVariable(name = "commentId") long commentId,
                                                        @Valid @RequestBody CommentDto commentRequest){
        return new ResponseEntity<>(commentService.updateCommentById(postId, commentId, commentRequest), HttpStatus.OK);
    }

    //delete comment api
    @DeleteMapping("posts/{postId}/comment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(name = "postId") long postId,
                                                @PathVariable(name = "commentId") long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("comment deleted successfully", HttpStatus.OK);
    }
}
