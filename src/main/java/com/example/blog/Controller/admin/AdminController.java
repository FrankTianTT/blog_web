package com.example.blog.Controller.admin;

import com.example.blog.bl.admin.AdminService;
import com.example.blog.po.Blog;
import com.example.blog.po.User;
import com.example.blog.vo.BlogForm;
import com.example.blog.vo.ResponseVO;
import com.example.blog.vo.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/DelUser")
    public ResponseVO DelUser(@RequestBody User user){
        return ResponseVO.buildSuccess(adminService.DelUser(user));
    }

    @PostMapping("/addBlog")
    public ResponseVO addBlog(@RequestBody BlogForm blogform){

        return adminService.addBlog(blogform);
    }

    @PostMapping("/DelBlog")
    public ResponseVO DelBlog(@RequestBody Blog blog){

        return adminService.DelBlog(blog);
    }
}
