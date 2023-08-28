package com.example.blogsystem.Service;


import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.AuthRepository;
import com.example.blogsystem.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final AuthRepository authRepository;
    private final BlogRepository blogRepository;
    public List<Blog> getMyBlog(Integer user_id){
        User user = authRepository.findUserById(user_id);

        return blogRepository.findAllByUser(user);
    }

    public void addMyBlog(Integer user_id, Blog blog){
        User user = authRepository.findUserById(user_id);

        blog.setUser(user);
        blogRepository.save(blog);
    }



    public void updateMyBlog(Integer user_id, Integer blog_id ,Blog blog){
        User user = authRepository.findUserById(user_id);
        Blog blog1 = blogRepository.findBlogById(blog_id);

        if (blog1 != null && user.getId().equals(blog1.getUser().getId())){
            blog1.setTitle(blog.getTitle());
            blogRepository.save(blog1);
        }else {

            throw new ApiException("Id Not found");
        }
    }

    public void deleteMyBlog(Integer user_id, Integer blog_id){
        User user = authRepository.findUserById(user_id);
        Blog blog = blogRepository.findBlogById(blog_id);

        if (blog != null && user.getId().equals(blog.getUser().getId())){
            blogRepository.delete(blog);
        } else {

            throw new ApiException("Id Not found");
        }

    }

    public Blog getBlogById(Integer blog_id, Integer user_id) {
        Blog blog = blogRepository.findBlogById(blog_id);
        User user = authRepository.findUserById(user_id);

        if (blog != null && user.getId().equals(blog.getUser().getId())) {
            return blog;
        } else {
            throw new ApiException("Blog not found");
        }
    }

    public Blog getByTitle(String title, Integer user_id){
        Blog blog = blogRepository.findBlogByTitle(title);
        User user = authRepository.findUserById(user_id);

        if (blog != null && user.getId().equals(blog.getUser().getId())){
            return blog;
        }

        throw new ApiException("Blog not found");
    }
}
