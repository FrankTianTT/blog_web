package com.example.blog.controller.user;

import com.example.blog.bl.user.AccountService;
import com.example.blog.po.User;
import com.example.blog.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/user")
public class AccountController {
    private final static String ACCOUNT_INFO_ERROR = "用户名或密码错误";
    @Autowired
    AccountService accountService;

    @PostMapping("/login")
    public ResponseVO login(@RequestBody UserForm userForm) {
        User user = accountService.login(userForm);
        if (user == null) {
            return ResponseVO.buildFailure(ACCOUNT_INFO_ERROR);
        }
        return ResponseVO.buildSuccess(user);

    }

    @PostMapping("/register")
    public ResponseVO registerAccount(@RequestBody UserVO userVO) {
        return accountService.registerAccount(userVO);
    }


    @GetMapping("/{id}/getUserInfo")
    public ResponseVO getUserInfo(@PathVariable int id) {
        User user = accountService.getUserInfo(id);
        if(user==null){
            return ResponseVO.buildFailure(ACCOUNT_INFO_ERROR);
        }
        return ResponseVO.buildSuccess(user);
    }

    @PostMapping("/{id}/update")
    public ResponseVO updateInfo(@RequestBody UserInfoVO userInfoVO, @PathVariable int id){
        return accountService.updateUserInfo(id,userInfoVO);
    }

    @PostMapping("/{id}/makeComment")
    public ResponseVO makeComment(@RequestBody CommentVO commentVO, @PathVariable int id){
        return accountService.makeComment(id, commentVO);
    }
}
