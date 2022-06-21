package com.nerdket.market.controller;

import com.nerdket.market.controller.response.SuccessResponse;
import com.nerdket.market.service.board.NoticeService;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

//    @PostMapping()
//    public SuccessResponse registerNotice(HttpServletResponse response) {
//
//    }
//
//    @Data
//    static class Notice
}
