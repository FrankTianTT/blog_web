package com.example.blog.blImpl.user;

import com.example.blog.bl.user.AccountService;
import com.example.blog.data.blog.BlogMapper;
import com.example.blog.data.user.AccountMapper;
import com.example.blog.po.Comment;
import com.example.blog.po.User;
import com.example.blog.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    private final static String UPDATE_ERROR = "修改失败";
    private final static String WRITE_ERROR = "评论失败";
    @Autowired
    private AccountMapper accountMapper;
    private BlogMapper blogMapper;

    @Override
    public ResponseVO registerAccount(UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO,user);
        try {
            accountMapper.createNewAccount(user);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
    }

    @Override
    public User login(UserForm userForm) {
        User user = accountMapper.getAccountByName(userForm.getEmail());
        if (null == user || !user.getPassword().equals(userForm.getPassword())) {
            return null;
        }
        return user;
    }

    @Override
    public User getUserInfo(int id) {
        User user = accountMapper.getAccountById(id);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public ResponseVO updateUserInfo(int id, UserInfoVO userInfoVO) {
        try {
            accountMapper.updateAccount(id, userInfoVO.getPassword(), userInfoVO.getUserName(), userInfoVO.getProfile(),userInfoVO.getQqNumber());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO makeComment(int id, CommentVO commentVO){
        try {
            Comment comment = new Comment();
            comment.setUserId(id);
            comment.setBlogId(commentVO.getBlogId());
            comment.setAuthorName(commentVO.getAuthorName());
            comment.setContent(commentVO.getContent());
            comment.setDatetime(commentVO.getDatetime());
            accountMapper.insertComment(comment);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(WRITE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<BlogVO> retrieveUserBlogs(int userId){
        List<BlogVO> blogs = accountMapper.selectAllUserBlogs(userId);
        for(BlogVO blog: blogs){
            blog.setComments(blogMapper.selectAllBlogComments(blog.getId()));
        }
        return blogs;
    }
}
