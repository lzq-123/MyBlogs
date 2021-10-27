package com.lzq.demo.controller.admin;

import com.lzq.demo.pojo.Tag;
import com.lzq.demo.pojo.Type;
import com.lzq.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TagsController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
//    @ResponseBody
    public String tags(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model){
        model.addAttribute("page", tagService.listTag(pageable));

        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(){
        return "admin/tagsInput";
    }

    @GetMapping("/tags/update/{id}")
    public String updateTags(@PathVariable Long id, Model model){
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tagsUpdate";
    }

    @PostMapping("/tags/input")
    public String tagsInput(Tag tag, RedirectAttributes redirectAttributes){
        Tag byName = tagService.findByName(tag.getName());
        if (byName != null){
            redirectAttributes.addFlashAttribute("msg", "该标签已经存在，新增失败！");
        }else{
            Tag t = tagService.saveTag(tag);
            if (t == null){
                redirectAttributes.addFlashAttribute("msg", "新增失败");
            }else{
                redirectAttributes.addFlashAttribute("msg","新增成功");
            }
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/update/{id}")
    public String updateTagsPost(Tag tag, @PathVariable Long id,RedirectAttributes redirectAttributes){
        Tag byName = tagService.findByName(tag.getName());
        if (byName != null){
            redirectAttributes.addFlashAttribute("msg", "该标签已经存在，更新失败！");
        }else{
            Tag t = tagService.updateTag(id, tag);
            if (t == null){
                redirectAttributes.addFlashAttribute("msg", "更新失败");
            }else{
                redirectAttributes.addFlashAttribute("msg","更新成功");
            }
        }
        return "redirect:/admin/tags";
    }


    @GetMapping("/tags/delete/{id}")
    public String deleteTages(@PathVariable Long id){
        tagService.deleteTag(id);
        return "redirect:/admin/tags";
    }


}
