package com.lzq.demo.service;

import com.lzq.demo.pojo.Blog;
import com.lzq.demo.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface BlogService {

    // 根据id查询一个blog对象
    Blog getBlog(Long id);

    // 根据Id获取blog对象，并且将其转换成HTML文档
    Blog getAndConvert(Long id);

    // 根据特定的条件分页查询
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    // 分页查询
    Page<Blog> listBlog(Pageable pageable);

    // 根据blog的title或者content进行的分页查询
    Page<Blog> listBlog(String query,Pageable pageable);

    // 根据tagId进行的分页查询
    Page<Blog> listBlog(Long tagId, Pageable pageable);

    // 插入一个blog对象
    Blog saveBlog(Blog blog);

    // 根据Id更新一个blog对象
    Blog update(Long id, Blog blog);

    // 根据id删除一个blog对象
    void deleteBlog(Long id);

    List<Blog> listRecommendBlogTop(Integer size);

    Map<String, List<Blog>> archiveBlog();

    Long countBlog();

}
