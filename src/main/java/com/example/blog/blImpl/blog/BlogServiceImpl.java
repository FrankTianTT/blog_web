package com.example.blog.blImpl.blog;

import com.example.blog.bl.blog.BlogService;
import com.example.blog.data.blog.BlogMapper;
import com.example.blog.po.Blog;
import com.example.blog.vo.BlogVO;
import com.example.blog.vo.CommentVO;
import com.example.blog.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    private final static String UPDATE_ERROR = "修改失败";
    @Autowired
    private BlogMapper blogMapper;


    @Override
    public void addBlog(BlogVO blogVO){
        Blog blog = new Blog();
        blog.setUserId(blogVO.getUserId());
        blog.setCategoryId(blogVO.getCategoryId());
        blog.setCategoryName(blogVO.getCategoryName());
        blog.setUserName(blogVO.getUserName());
        blog.setTitle(blogVO.getTitle());
        blog.setContent(blogVO.getContent());
        blog.setLabel(blogVO.getLabel());
        blog.setViewTimes(0);
        blog.setCreateDate(LocalDate.now());
        blogMapper.insertBlog(blog);
    }

    @Override
    public void updateBlog(BlogVO blogVO){
        blogMapper.updateBlog(blogVO.getId(),blogVO.getCategoryId(),blogVO.getCategoryName(),
                blogVO.getTitle(),blogVO.getContent(),blogVO.getLabel());
    }

    @Override
    public List<BlogVO> retrieveBlogs(){
        List<BlogVO> blogs = blogMapper.selectAllBlog();
        for(BlogVO blog: blogs){
            blog.setComments(blogMapper.selectAllBlogComments(blog.getId()));
        }
        return blogs;
    }

    @Override
    public BlogVO retrieveBlogDetails(Integer blogId){
        return blogMapper.selectById(blogId);
    }

    @Override
    public List<CommentVO> retrieveBlogComments(Integer blogId){
        return blogMapper.selectAllBlogComments(blogId);
    }

    @Override
    public List<BlogVO> getBlogByTime(int year, int month){
        List<BlogVO> allBlogs = blogMapper.selectAllBlog();
        List<BlogVO> res = new ArrayList<>();
        for(BlogVO blogVO: allBlogs){
            String[] s = blogVO.getCreateDate().split("-");
            if(Integer.parseInt(s[0])==year && Integer.parseInt(s[1]) == month)
                res.add(blogVO);
        }
        return res;
    }
}
