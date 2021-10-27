package com.lzq.demo.controller.admin;

import com.lzq.demo.pojo.Type;
import com.lzq.demo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RequestMapping("/admin")
@Controller
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){

        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }

    /*跳转到新增页面*/
    @GetMapping("/types/input")
    public String input(){
        return "admin/typesInput";
    }

    @GetMapping("/types/update/{id}")
    public String totypesUpdate(@PathVariable Long id, Model model){
        model.addAttribute("type", typeService.getType(id));
        return "admin/typesUpdate";
    }

    @PostMapping("/types")
    public String post(Type type, RedirectAttributes redirectAttributes){
        Type byName = typeService.findByName(type.getName());
        if (byName != null){
            redirectAttributes.addFlashAttribute("msg", "该分类已经存在，新增失败！");
        }else{
            Type t = typeService.saveType(type);
            if (t == null){
                redirectAttributes.addFlashAttribute("msg", "新增失败");
            }else{
                redirectAttributes.addFlashAttribute("msg","新增成功");
            }
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/update/{id}")
    public String typesUpdate(Type type, @PathVariable Long id, RedirectAttributes redirectAttributes){
        Type byName = typeService.findByName(type.getName());

        if (byName != null){
            redirectAttributes.addFlashAttribute("msg", "该分类已经存在，操作失败！");
        }else{
            Type t = typeService.updateType(id, type);
            if (t == null){
                redirectAttributes.addFlashAttribute("msg", "操作失败");
            }else{
                redirectAttributes.addFlashAttribute("msg","操作成功");
            }
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/delete/{id}")
    public String typesUpdate(@PathVariable Long id,RedirectAttributes redirectAttributes){
        typeService.deleteType(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/admin/types";
    }
}
