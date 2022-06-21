package com.nerdket.market.controller;

import com.nerdket.market.controller.response.SuccessResponse;
import com.nerdket.market.service.board.NoticeDto;
import com.nerdket.market.service.board.NoticeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping()
    public SuccessResponse registerNotice(@RequestBody NoticeRegisterRequest request) {
        System.out.println(request);
        LocalDateTime createdDate = noticeService.register(request.getNoticeDto());
        return new SuccessResponse(new NoticeRegisterResponse(createdDate));
    }

    @Data
    static class NoticeRegisterRequest {
        @NotEmpty
        private String title;

        @NotEmpty
        private String content;

        @NotEmpty
        private long userId;

        public NoticeDto getNoticeDto() {
            return NoticeDto.builder().title(title).content(content).userId(userId).build();
        }

    }

    @Data
    @AllArgsConstructor
    static class NoticeRegisterResponse {
        private LocalDateTime createdDate;
    }
}
