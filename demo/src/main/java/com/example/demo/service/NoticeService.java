package com.example.demo.service;

import com.example.demo.entity.Notice;
import com.example.demo.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    public Notice saveNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }
}