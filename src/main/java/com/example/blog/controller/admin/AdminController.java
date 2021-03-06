package com.example.blog.controller.admin;


import com.example.blog.bl.admin.AdminService;

import com.example.blog.po.Blog;

import com.example.blog.po.User;

import com.example.blog.vo.BlogForm;

import com.example.blog.vo.BlogVO;
import com.example.blog.vo.ResponseVO;

import com.example.blog.vo.UserForm;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


@RestController()

@RequestMapping("/api/admin")

public class AdminController {

    @Autowired

    AdminService adminService;



    @PostMapping("/getAllUsers")

    public ResponseVO getAllUsers(){

        return ResponseVO.buildSuccess(adminService.getAllUsers());

    }

    @PostMapping("/addManager")

    public ResponseVO addUser(@RequestBody UserForm userForm){
        return adminService.addUser(userForm);
    }



    @PostMapping("/DelUser/{id}")

    public ResponseVO DelUser(@PathVariable("id") Integer id){

        return ResponseVO.buildSuccess(adminService.DelUser(id));
    }



    @PostMapping("/addBlog")
    public ResponseVO addBlog(@RequestBody BlogForm blogform){
        return adminService.addBlog(blogform);
    }



    @PostMapping("/DelBlog/{id}")
    public ResponseVO DelBlog(@PathVariable("id") int id){
        return adminService.DelBlog(id);
    }

    @PostMapping("/getAllComments")
    public ResponseVO getAllComments(){
        return ResponseVO.buildSuccess(adminService.getAllComments());
    }

    @PostMapping("/DelComments/{id}")
    public ResponseVO DelCommentsById(@PathVariable("id") int id){
        adminService.DelComments(id);
        return ResponseVO.buildSuccess(true);
    }

}