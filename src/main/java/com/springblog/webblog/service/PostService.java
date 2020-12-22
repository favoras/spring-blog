package com.springblog.webblog.service;

import com.springblog.webblog.exception.PostNotFoundException;
import com.springblog.webblog.repository.PostRepository;
import com.springblog.webblog.model.Post;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post getPostById(long id) {

        return postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
    }
}
