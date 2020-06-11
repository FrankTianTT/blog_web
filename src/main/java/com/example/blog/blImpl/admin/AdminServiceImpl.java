package com.example.blog.blImpl.admin;



import com.example.blog.bl.admin.AdminService;

import com.example.blog.data.admin.AdminMapper;

import com.example.blog.enums.UserType;

import com.example.blog.po.Blog;

import com.example.blog.po.Comment;
import com.example.blog.po.User;

import com.example.blog.vo.BlogForm;

import com.example.blog.vo.ResponseVO;

import com.example.blog.vo.UserForm;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;



@Service

public class AdminServiceImpl implements AdminService {

    @Autowired

    AdminMapper adminMapper;



    @Override

    public List<User> getAllUsers() {

        return adminMapper.getAllUsers();

    }

    @Override
    public void DelComments(int id) {
        adminMapper.DelComments(id);
    }


    private final static String ACCOUNT_EXIST = "账号已存在";

    private final static String BLOG_ERROR = "文章不满足要求";

    @Override

    public ResponseVO addUser(UserForm userForm) {

        User user = new User();

        user.setEmail(userForm.getEmail());

        user.setPassword(userForm.getPassword());

        user.setUserType(UserType.Admin);

        try {

            adminMapper.addUser(user);

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return ResponseVO.buildFailure(ACCOUNT_EXIST);

        }

        return ResponseVO.buildSuccess(true);

    }



    @Override

    public ResponseVO DelUser(Integer id) {

        adminMapper.DelUser(id);

        return ResponseVO.buildSuccess(true);

    }



    @Override

    public ResponseVO addBlog(BlogForm blogForm) {

        Blog blog = new Blog();

        blog.setContent(blogForm.getContent());

        blog.setTitle(blogForm.getTitle());

        blog.setLabel(blogForm.getLabel());

        try{

            adminMapper.addBlog(blog);

        }catch (Exception e){

            System.out.println(BLOG_ERROR);

            return ResponseVO.buildFailure(BLOG_ERROR);

        }

        return ResponseVO.buildSuccess(true);

    }



    @Override

    public ResponseVO DelBlog(int id) {

        adminMapper.DelBlog(id);

        return ResponseVO.buildSuccess(true);

    }

    @Override
    public List<Comment> getAllComments() {

        return adminMapper.getAllComments();
    }

}