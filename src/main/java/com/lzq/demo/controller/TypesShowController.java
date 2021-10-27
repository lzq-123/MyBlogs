package com.lzq.demo.controller;

import com.lzq.demo.pojo.Blog;
import com.lzq.demo.pojo.Type;
import com.lzq.demo.service.BlogService;
import com.lzq.demo.service.TypeService;
import com.lzq.demo.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TypesShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String type(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                   Pageable pageable,
                       @PathVariable Long id, Model model){
        BlogQuery blog = new BlogQuery();
        List<Type> types = typeService.listTypeTop(10000);
        model.addAttribute("types", types);
        /*
        *  1. 先获取全部的type，降序
        *  2. 判断id初始值是否为-1，如果是就赋值给BlogQuery.id
        *  3. 分页查询
        * */
        if (id == -1){
            id = types.get(0).getId();
        }
        blog.setTypeId(id);
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        model.addAttribute("typeId",id);
        return "types";
    }
}
