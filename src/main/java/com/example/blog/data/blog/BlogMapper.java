package com.example.blog.data.blog;

import com.example.blog.po.Blog;
import com.example.blog.vo.BlogVO;
import com.example.blog.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {
    int insertBlog(Blog blog);

    int updateBlog(@Param("id") int id, @Param("categoryId") int categoryId, @Param("categoryName") String categoryName,
                   @Param("title") String title, @Param("content") String content, @Param("label") String label);

    List<BlogVO> selectAllBlog();

    BlogVO selectById(@Param("id") Integer id);

    List<CommentVO> selectAllBlogComments(@Param("blogId") Integer blogId);
}
