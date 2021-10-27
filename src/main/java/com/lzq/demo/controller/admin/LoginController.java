package com.lzq.demo.controller.admin;

import com.lzq.demo.pojo.User;
import com.lzq.demo.service.UserService;
import com.lzq.demo.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public String login(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String checkLogin(@RequestParam String username,
                             @RequestParam String password,
                             HttpSession session,
                             RedirectAttributes attributes){
//        System.out.println(MD5Utils.stringToMD5("root"));
        User user = userService.checkUser(username, MD5Utils.stringToMD5(password));
        if (user == null){
            attributes.addFlashAttribute("msg","账号或密码错误");
            return "redirect:/admin";
        }else{
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }
    }

    @GetMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
