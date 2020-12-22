package com.springblog.webblog.controller;

import com.springblog.webblog.model.Comment;
import com.springblog.webblog.model.Post;
import com.springblog.webblog.repository.CommentRepository;
import com.springblog.webblog.repository.PostRepository;
import com.springblog.webblog.service.CommentService;
import com.springblog.webblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {
    // TODO: Visa posto ir komentaro logika perkelti i service
    private PostRepository postRepository;
    private PostService postService;
    private CommentRepository commentRepository;
    private CommentService commentService;

    @Autowired
    public PostController(PostService postService, PostRepository postRepository, CommentRepository commentRepository, CommentService commentService) {
        this.postService = postService;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.commentService = commentService;
    }

    @GetMapping("/blog")
    public String postsList(Model model){
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog";
    }

    @GetMapping("/blog/add")
    public String addPost(){
        return "blog-new";
    }

    @PostMapping("/blog/add")
    public String addPost(@RequestParam String title, @RequestParam String description){
        Post post = new Post();
        post.setTitle(title);
        post.setDescription(description);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String postDetails(@PathVariable long id, Model model){
        List<Comment> comments = commentRepository.findAll();
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        model.addAttribute("blog", postService.getPostById(id));
        model.addAttribute("comments", comments);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String postEdit(@PathVariable long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        model.addAttribute("blog", postService.getPostById(id));
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String postEdit(@PathVariable long id, @RequestParam String title, @RequestParam String description){
        Post post = postService.getPostById(id);
        post.setTitle(title);
        post.setDescription(description);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/delete")
    public String postDelete(@PathVariable long id){
        Post post = postService.getPostById(id);
        postRepository.delete(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}/comment")
    public String addPostComment(@PathVariable long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        model.addAttribute("post", postService.getPostById(id));
        return "comment-new";
    }

    @PostMapping("/blog/{id}/comment")
    public String addPostComment(@PathVariable long id, @RequestParam String commentText){
        Comment comment = new Comment();
        comment.setCommentText(commentText);
        comment.setPost(postService.getPostById(id));
        commentRepository.save(comment);
        return "redirect:/blog";
    }

    @GetMapping("/blog/comment/{id}/edit")
    public String commentEdit(@PathVariable long id, Model model){
        if(!commentRepository.existsById(id)){
            return "redirect:/blog";
        }
        model.addAttribute("comment", commentService.getCommentById(id));
        return "comment-edit";
    }

    @PostMapping("/blog/comment/{id}/edit")
    public String commentEdit(@PathVariable long id, @RequestParam String commentText){
        Comment comment = commentService.getCommentById(id);
        comment.setCommentText(commentText);
        commentRepository.save(comment);
        return  "redirect:/blog";
    }

    @PostMapping("/blog/comment/{id}/delete")
    public String commentDelete(@PathVariable long id){
        Comment comment = commentService.getCommentById(id);
        commentRepository.delete(comment);
        return "redirect:/blog";
    }

}
