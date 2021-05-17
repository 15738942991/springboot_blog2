package com.zp.controller.admin;

import com.zp.pojo.Tag;
import com.zp.pojo.Type;
import com.zp.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagsController {
    @Autowired
    TagsService tagsService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable,
            Model model){
        model.addAttribute("page",tagsService.listTag(pageable));
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Type());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("tag",tagsService.getTag(id));
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String editInput(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag byName = tagsService.getByName(tag.getName());
        if (byName!=null){
            result.rejectValue("name","nameError","不能重复添加的标签!");
        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }

        Tag t = tagsService.saveTag(tag);
        if (t==null){
            attributes.addFlashAttribute("message","添加失败");
        }else {
            attributes.addFlashAttribute("message","添加成功");
        }

        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String editInput(@Valid Tag tag, BindingResult result,@PathVariable Long id ,RedirectAttributes attributes){
        Tag byName2 = tagsService.getByName(tag.getName());
        if (byName2!=null){
            result.rejectValue("name","nameError","不能重复添加的标签！");
        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }

        Tag t = tagsService.updateTag(id,tag);
        if (t==null){
            attributes.addFlashAttribute("message","修改失败");
        }else {
            attributes.addFlashAttribute("message","修改成功");
        }

        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String deleteTag(@PathVariable Long id){
        tagsService.deleteById(id);
        return "redirect:/admin/tags";
    }
}
