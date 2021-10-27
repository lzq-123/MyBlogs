package com.lzq.demo.controller;

import com.lzq.demo.pojo.Blog;
import com.lzq.demo.service.BlogService;
import com.lzq.demo.service.CommentService;
import com.lzq.demo.service.TagService;
import com.lzq.demo.service.TypeService;
import com.lzq.demo.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                    Pageable pageable,
                        Model model){
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(6));

        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(6));
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam String query,
                         @PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                         Pageable pageable,
                         Model model){
        model.addAttribute("page", blogService.listBlog("%"+query+"%", pageable));
        model.addAttribute("query", query);
        return "search";
    }

    // 根据Id查询博客信息
    @GetMapping("/blog/{id}")
    public String blogs(@PathVariable Long id, Model model){
        model.addAttribute("blog", blogService.getAndConvert(id));
        model.addAttribute("comments", commentService.listCommentByBlogId(id));
        return "blog";
    }
}
