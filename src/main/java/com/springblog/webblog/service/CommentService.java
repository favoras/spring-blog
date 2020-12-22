package com.springblog.webblog.service;

import com.springblog.webblog.exception.CommentNotFoundException;
import com.springblog.webblog.model.Comment;
import com.springblog.webblog.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment getCommentById(long id){
        return commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
    }
}
