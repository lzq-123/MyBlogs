package com.lzq.demo.controller;

import com.lzq.demo.pojo.Blog;
import com.lzq.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ArchivesController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model){
        Map<String, List<Blog>> map = new HashMap<>();
        // 获取key=年份，value=blogs集合的数据
        model.addAttribute("archivesMap", blogService.archiveBlog());
        // 获取blogs的总数目
        model.addAttribute("blogCount", blogService.countBlog());
        return "archives";
    }
}
