package com.zp.service;

import com.zp.pojo.Blog;
import com.zp.vo.BlogQuery;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Blog getBlog(Long id);
    //博客详情页面，把markdown转换成html
    Blog getAndConvert(Long id);

    void deleteBlog(Long id);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog) throws NotFoundException;

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Pageable pageable,BlogQuery blog);
    //查询标签
    Page<Blog> listBlog(Long tagId,Pageable pageable);

    //全局搜索，模糊查询
    Page<Blog> listBlog(Pageable pageable,String  query);

    List<Blog> listRecommendBlogTop(Integer size);
    //归档：按照时间排序
    Map<String,List<Blog>> archiveBlog();

    Long countBlog();

}
