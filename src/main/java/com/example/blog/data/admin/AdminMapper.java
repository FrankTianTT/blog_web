package com.example.blog.data.admin;



import com.example.blog.po.Blog;

import com.example.blog.po.Comment;
import com.example.blog.po.User;

import com.example.blog.vo.UserForm;

import org.apache.ibatis.annotations.Mapper;

import org.springframework.stereotype.Repository;



import java.util.List;



@Mapper

@Repository

public interface AdminMapper {



    List<User> getAllUsers();

    int addUser(User user);

    int addBlog(Blog blog);

    void DelUser(Integer id);

    List<Comment> getAllComments();

    void DelBlog(int id);

}