package com.example.blog.bl.user;

import com.example.blog.po.User;
import com.example.blog.vo.*;

import java.util.List;

public interface AccountService {
    ResponseVO registerAccount(UserVO userVO);

    User login(UserForm userForm);

    User getUserInfo(int id);

    ResponseVO updateUserInfo(int id, UserInfoVO userInfoVO);

    ResponseVO makeComment(int id, CommentVO commentVO);

    List<BlogVO> retrieveUserBlogs(int userId);

}
