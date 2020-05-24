package com.example.blog.data.user;

import com.example.blog.po.Comment;
import com.example.blog.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountMapper {
    int createNewAccount(User user);

    User getAccountByName(@Param("email") String email);

    User getAccountById(@Param("id") int id);

    int updateAccount(@Param("id") int id, @Param("password") String password, @Param("userName") String username,
                      @Param("profile") String profile,@Param("qqNumber") String qqNumber);

    int insertComment(Comment comment);
}
