package com.example.blog.blImpl.blog;

import com.example.blog.bl.blog.BlogService;
import com.example.blog.data.blog.BlogMapper;
import com.example.blog.po.Blog;
import com.example.blog.vo.BlogVO;
import com.example.blog.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;


    @Override
    public void addBlog(BlogVO blogVO){
        Blog blog = new Blog();
        blog.setUserId(blogVO.getUserId());
        blog.setCategoryId(blogVO.getCategoryId());
        blog.setTitle(blogVO.getTitle());
        blog.setContent(blogVO.getContent());
        blog.setLabel(blogVO.getLabel());
        blog.setViewTimes(0);
        blog.setCreateDate(LocalDate.now());
        blogMapper.insertBlog(blog);
    }

    @Override
    public List<BlogVO> retrieveBlogs(){
        return blogMapper.selectAllBlog();
    }

    @Override
    public BlogVO retrieveBlogDetails(Integer blogId){
        return blogMapper.selectById(blogId);
    }

    @Override
    public List<CommentVO> retrieveBlogComments(Integer blogId){
        return blogMapper.selectAllBlogComments(blogId);
    }
}
