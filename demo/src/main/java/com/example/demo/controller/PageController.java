package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.JoinDTO;
import com.example.demo.service.JoinService;



@Controller
public class PageController {
    @GetMapping("/")
    public String getOpenPage() {
        return "start";
    }

    @GetMapping("/start")
    public String getStartPage() {
        return "start";
    }
    
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/main")
    public String getMainPage() {
        return "main";
    }
    @GetMapping("/main0")
    public String getMain0Page(){
        return "main0";
    }

    @GetMapping("/id_search")
    public String getId_searchPage() {
        return "id_search";
    }

    @GetMapping("/pw_reset")
    public String getPw_resetPage() {
        return "pw_reset";
    }

    @Autowired
    private JoinService joinService;

    @GetMapping("/join")
    public String getJoinPage() {
        return "join";
    }

    
    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO) {

        System.out.println(joinDTO.getName());

        joinService.joinProcess(joinDTO);
        
        return "redirect:/login";
    }

    @GetMapping("/notice_room")
    public String getNotice_roomPage() {
        return "notice_room";
    }

    @GetMapping("/chatroom_list")
    public String getChatroom_listPage() {
        return "chatroom_list";
    }

    @GetMapping("/contact")
    public String getContactPage() {
        return "contact";
    }

    @GetMapping("/user_infor")
    public String getUser_inforPage() {
        return "user_infor";
    }  

    @GetMapping("/kku_email_verification")
    public String getKku_email_verificationPage() {
        return "kku_email_verification";
    }

    @GetMapping("/room_condition")
    public String getRoom_conditionPage() {
        return "room_condition";
    }

    @GetMapping("/chatroom")
    public String getChatroomPage() {
        return "chatroom";
    }
    @GetMapping("/roby1")
    public String getRoby1Page() {
        return "roby1";
    }

    @GetMapping("/roby1_participate")
    public String getRoby1_participatePage() {
        return "roby1_participate";
    }

    @GetMapping("/update-2024-05-22")
    public String getUpdate0522Page() {
        return "update-2024-05-22";
    }

    @GetMapping("/update-2024-05-20")
    public String getUpdate0520Page() {
        return "update-2024-05-20";
    }

    @GetMapping("/declaration")
    public String getDeclarationPage() {
        return "declaration";
    }
    @GetMapping("/user_info1")
    public String getUser_infoPage(){
        return "user_info1";
    }
}
