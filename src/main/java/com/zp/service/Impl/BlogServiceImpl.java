package com.zp.service.Impl;

import com.zp.NoFoundException;
import com.zp.dao.BlogsRepository;
import com.zp.pojo.Blog;
import com.zp.pojo.Type;
import com.zp.service.BlogService;
import com.zp.utils.MarkdownUtils;
import com.zp.utils.MyBeanUtils;
import com.zp.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService  {
    @Autowired
    private BlogsRepository blogsRepository;
    @Override
    public Blog getBlog(Long id) {
        return blogsRepository.getOne(id);
    }

    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = blogsRepository.getOne(id);
        if (blog == null){
            throw new NoFoundException("该博客为空！");
        }
        Blog blog1 = new Blog();
        BeanUtils.copyProperties(blog,blog1);
        String content = blog1.getContent();
        blog1.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        //更新观看次数
        blogsRepository.updateView(id);
        return blog1;
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogsRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if (blog.getId()==null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else {
            blog.setUpdateTime(new Date());
        }
        return blogsRepository.save(blog);
    }

    // @Transactional :
    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog){
        Blog blog1 = blogsRepository.getOne(id);
        if(blog1 == null){
            throw new NoFoundException("博客为空！");
        }
//        将blog 复制给 blog1
        BeanUtils.copyProperties(blog,blog1,MyBeanUtils.getNullPropertyNames(blog));
        blog1.setUpdateTime(new Date());
        return blogsRepository.save(blog1);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogsRepository.findAll(pageable);
    }

    //
     @Override
     public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
          return blogsRepository.findAll(new Specification<Blog>() {
              @Override
                public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                    List<Predicate> predicates = new ArrayList<>();
                    if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    predicates.add(cb.like(root.<String>get("title"), "%"+blog.getTitle()+"%"));
                }
                    if (blog.getTypeId() != null) {
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }
                     if (blog.isRecommend()) {
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
                    cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }


    //根据标签查询博客
    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return blogsRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join<Object, Object> join = root.join("tags");

                return cb.equal(join.get("id"),tagId);
            }
        },pageable);
    }

    //全局搜索，模糊查询
    @Override
    public Page<Blog> listBlog(Pageable pageable, String query) {
        return blogsRepository.findByQuery(pageable,query);
    }

    //首页右侧博客推荐
    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"updateTime");
        Pageable pageable = PageRequest.of(0,size,sort);
        return blogsRepository.findTop(pageable);
    }

    //归档：按照时间排序
    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogsRepository.findGroupYear();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year,blogsRepository.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return blogsRepository.count();
    }

}
