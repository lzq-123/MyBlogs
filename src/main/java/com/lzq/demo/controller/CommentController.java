package com.lzq.demo.controller;

import com.lzq.demo.pojo.Comment;
import com.lzq.demo.pojo.Blog;
import com.lzq.demo.service.BlogService;
import com.lzq.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @Value("${manage.avatar}")
    private String manageAvatar;


    @GetMapping("/comments/{blogId}")
    /*
    * 参数一定要记得加 @PathVariable
    * */
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        return "blog :: commentList";
    }

    @PostMapping("/blog/comments")
    public String post(Comment comment, HttpSession httpSession){
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        comment.setAvatar(avatar);
        if (httpSession.getAttribute("user") != null){
            comment.setManage(true);
            comment.setAvatar(manageAvatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }
}
