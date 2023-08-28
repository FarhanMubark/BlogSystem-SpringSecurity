package com.example.blogsystem.Controller;

import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/get-my-todo")
    public ResponseEntity getMyBlog(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(blogService.getMyBlog(user.getId()));
    }

    @PutMapping("/update-my-blog/{blog_id}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user,@PathVariable Integer blog_id, @RequestBody @Valid Blog blog){
        blogService.updateMyBlog(user.getId(), blog_id, blog);
        return ResponseEntity.status(200).body("Blog Updated");
    }

    @PostMapping("/add-my-todo")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user, @RequestBody Blog blog){
        blogService.addMyBlog(user.getId(), blog);
        return ResponseEntity.status(200).body("Blog added");

    }



    @DeleteMapping("/delete-my-todo/{blog_id}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user, @PathVariable Integer blog_id){
        blogService.deleteMyBlog(user.getId(), blog_id);

        return ResponseEntity.status(200).body("Blog Deleted");
    }

    @GetMapping("/get-by-id/{blog_id}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal User user, @PathVariable Integer blog_id){
       Blog blog = blogService.getBlogById(blog_id, user.getId());
       return ResponseEntity.status(200).body(blog);
    }

    @GetMapping("/get-by-id/{title}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal User user, @PathVariable String title){
        Blog blog = blogService.getByTitle(title, user.getId());
        return ResponseEntity.status(200).body(blog);
    }



}
