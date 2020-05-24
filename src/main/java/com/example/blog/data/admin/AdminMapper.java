package com.example.blog.data.admin;

import com.example.blog.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {

    List<User> getAllUsers();
}
