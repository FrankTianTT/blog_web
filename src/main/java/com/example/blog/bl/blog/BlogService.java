package com.example.blog.bl.blog;

import com.example.blog.po.Blog;
import com.example.blog.util.ServiceException;
import com.example.blog.vo.BlogVO;
import com.example.blog.vo.CommentVO;

import java.util.List;

public interface BlogService {
    void addBlog(BlogVO blogVO);

    void updateBlog(BlogVO blogVO);

    List<BlogVO> retrieveBlogs();

    BlogVO retrieveBlogDetails(Integer blogId);

    List<CommentVO> retrieveBlogComments(Integer blogId);

    List<BlogVO> getBlogByTime(int year, int month);
}
