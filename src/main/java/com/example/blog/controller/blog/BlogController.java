package com.example.blog.controller.blog;

import com.example.blog.bl.blog.BlogService;
import com.example.blog.vo.BlogVO;
import com.example.blog.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    BlogService blogService;

    @PostMapping("/addBlog")
    public ResponseVO createHotel(@RequestBody BlogVO blogVO){
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

}
