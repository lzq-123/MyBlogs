package com.lzq.demo.controller.admin;

import com.lzq.demo.pojo.Blog;
import com.lzq.demo.pojo.User;
import com.lzq.demo.service.BlogService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogsController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                    Pageable pageable,
                        BlogQuery blog, Model model){

        model.addAttribute("types", typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable, blog));
//        System.out.println("/admin/blogs => " + blogService.listBlog(pageable, blog));
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                Pageable pageable,
                        BlogQuery blog, Model model){
        Page<Blog> blogs = blogService.listBlog(pageable, blog);
        model.addAttribute("page",blogs);
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("blog", new Blog());
        return "admin/blogsInput";
    }

    @GetMapping("/blogs/input/{id}")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog", blog);
        return "admin/blogsInput";
    }

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b = blogService.saveBlog(blog);

        if (b == null){
            attributes.addFlashAttribute("msg", "新增失败");
        }else{
            attributes.addFlashAttribute("msg","新增成功");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/delete/{id}")
        public String deleteById(@PathVariable Long id, Blog blog){
        blogService.deleteBlog(id);
        return "redirect:/admin/blogs";
    }

//
}
