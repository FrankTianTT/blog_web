package com.example.blog.controller.blog;

import com.example.blog.bl.blog.BlogService;
import com.example.blog.vo.BlogVO;
import com.example.blog.vo.CommentVO;
import com.example.blog.vo.ResponseVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    BlogService blogService;

    @PostMapping("/addBlog")
    public ResponseVO createBlog(@RequestBody BlogVO blogVO){
        blogService.addBlog(blogVO);
        return ResponseVO.buildSuccess(true);
    }

    @GetMapping("/all")
    public ResponseVO retrieveAllBlogs(){
        return ResponseVO.buildSuccess(blogService.retrieveBlogs());
    }

    @GetMapping("/{blogId}/detail")
    public ResponseVO retrieveBlogDetail(@PathVariable Integer blogId) {
        return ResponseVO.buildSuccess(blogService.retrieveBlogDetails(blogId));
    }

    @GetMapping("/{blogId}/comments")
    public ResponseVO retrieveBlogComments(@PathVariable Integer blogId) {
        return ResponseVO.buildSuccess(blogService.retrieveBlogComments(blogId));
    }


    @GetMapping("/{year}/{month}")
    public ResponseVO getBlogByTime(@PathVariable int year, @PathVariable int month){
        return ResponseVO.buildSuccess(blogService.getBlogByTime(year,month));
    }
}
