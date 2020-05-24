package com.example.blog.Controller.admin;

import com.example.blog.bl.admin.AdminService;
import com.example.blog.vo.ResponseVO;
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

}
