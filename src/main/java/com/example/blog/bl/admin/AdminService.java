package com.example.blog.bl.admin;



import com.example.blog.po.Blog;

import com.example.blog.po.Comment;
import com.example.blog.po.User;

import com.example.blog.vo.BlogForm;

import com.example.blog.vo.ResponseVO;

import com.example.blog.vo.UserForm;



import java.util.List;



public interface AdminService {

    /**

     * 添加用户

     */

    ResponseVO addUser(UserForm userFrom);



    /**

     * 删除用户

     */

    ResponseVO DelUser(Integer id);





    /**

     * 添加文章

     */

    ResponseVO addBlog(BlogForm blogForm);



    /**

     * 删除文章

     */

    ResponseVO DelBlog(int id);

    /**
     * 获得所有评论
     * @return
     */
    List<Comment> getAllComments();

    List<User> getAllUsers();

}