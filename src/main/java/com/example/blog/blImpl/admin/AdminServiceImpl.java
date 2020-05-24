package com.example.blog.blImpl.admin;

import com.example.blog.bl.admin.AdminService;
import com.example.blog.data.admin.AdminMapper;
import com.example.blog.po.User;
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
}
