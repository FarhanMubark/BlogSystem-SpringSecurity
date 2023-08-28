package com.example.blogsystem.Repository;

import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

    List<Blog> findAllByUser(User user);
    Blog findBlogById(Integer id);

    Blog findBlogByTitle(String title);
}
