package com.example.demo.controller;

import com.example.demo.entity.Notice;
import com.example.demo.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/notices")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/form")
    public String showForm() {
        return "notice_form";
    }

    @PostMapping("/add")
    public String addNotice(Notice notice) {
        notice.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        noticeService.saveNotice(notice);
        return "redirect:/notices/list";
    }

    @GetMapping("/list")
    public String listNotices(Model model) {
        List<Notice> notices = noticeService.getAllNotices();
        model.addAttribute("notices", notices);
        return "notice_room";
    }
    // 추가된 매핑
    @GetMapping("/notice_room")
    public String showNoticeRoom(Model model) {
        List<Notice> notices = noticeService.getAllNotices();
        model.addAttribute("notices", notices);
        return "notice_room";
    }
}