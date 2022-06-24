package com.nerdket.market.app.controller;

import com.nerdket.market.app.controller.response.SuccessResponse;
import com.nerdket.market.app.service.board.NoticeDto;
import com.nerdket.market.app.service.board.NoticeService;
import com.nerdket.market.app.service.user.UserDto;
import com.nerdket.market.util.jwt.JwtTokenService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;

@Slf4j
@RestController
@RequestMapping("notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;
    private final JwtTokenService jwtTokenService;

    @PostMapping()
    public SuccessResponse registerNotice(HttpServletRequest request, @RequestBody NoticeRegisterRequest registerRequest) {
        UserDto userDto = jwtTokenService.parseTokenFromRequest(request);
        noticeService.register(registerRequest.getNoticeDto(userDto.getUsername()));
        return new SuccessResponse();
    }

    @Data
    static class NoticeRegisterRequest {
        @NotEmpty
        private String title;

        @NotEmpty
        private String content;

        public NoticeDto getNoticeDto(String username) {
            return NoticeDto.builder().title(title).content(content).username(username).build();
        }

    }

}