package com.zp.controller;

import com.zp.pojo.Comment;
import com.zp.pojo.User;
import com.zp.service.BlogService;
import com.zp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String list(@PathVariable Long blogId, Model model ){
        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }



    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        Long BlogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(BlogId));
        User user = (User) session.getAttribute("user");
        if (user != null){
            comment.setAvater(user.getAvatar());
            comment.setAdminComment(true);
        }else{
            comment.setAvater(user.getAvatar());
        }
        commentService.saveComment(comment);
        return "redirect:/comments/"+BlogId;

    }
}
