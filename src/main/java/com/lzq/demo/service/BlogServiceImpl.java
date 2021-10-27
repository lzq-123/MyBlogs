package com.lzq.demo.service;

import com.lzq.demo.dao.BlogRepository;
import com.lzq.demo.pojo.Blog;
import com.lzq.demo.pojo.Tag;
import com.lzq.demo.pojo.Type;
import com.lzq.demo.util.MarkDownUtils;
import com.lzq.demo.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.*;

@Service
//@Component
public class BlogServiceImpl implements BlogService{

    @Autowired // 默认byType， 如果类型不唯一，则需要配合 @Qualifier("classImplName")

//    @Resource
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getById(id);
    }

    // 将blog的markDown语法转为HTML语言
    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = blogRepository.getById(id);
        Blog b = new Blog();
        BeanUtils.copyProperties(blog, b);
        String content = b.getContent();
        b.setContent(MarkDownUtils.markdownToHtmlExtensions(content));

        blogRepository.updateViews(id);
        return b;
    }

    /*
    *   查询 单表，多条件
        https://www.cnblogs.com/arrrrrya/p/7865090.html
    * */
    /*条件查询*/
    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
//        return blogRepository.findAll(pageable,blog);
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null){
                    predicates.add(criteriaBuilder.like(root.<String>get("title"), "%"+blog.getTitle()+"%"));
                }
                if (blog.getTypeId() != null){
                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }
                if (blog.isRecommend()){
                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
                // 将查询条件转为Array数组<Predicate>。
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    // 分页查询所有博客
    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    // 自定义查询语句
    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery(query, pageable);
    }

    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return blogRepository.findAll(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Join join = root.join("tags");
                return criteriaBuilder.equal(join.get("id"), tagId);
            }
        },pageable);

    }

    // 保存blog草稿
    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if (blog.getId() == null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else {
            blog.setUpdateTime(new Date());
        }
        return blogRepository.save(blog);
    }

    // 根据Id更新博客
    @Transactional
    @Override
    public Blog update(Long id, Blog blog) {
        Blog byId = blogRepository.getById(id);
        if (byId == null){
            return null;
        }
        BeanUtils.copyProperties(blog,byId);
        return blogRepository.save(byId);

    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0, size, sort);
        return blogRepository.findTop(pageable);
    }

    // 查询年份并对博客进行归档
    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogRepository.findGroupYear();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years){
            map.put(year, blogRepository.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countBlog() {
        // 获取blog表中的数据条数
        return blogRepository.count();
    }


}
