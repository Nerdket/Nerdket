package com.nerdket.market.app.controller;

import com.nerdket.market.app.controller.response.SuccessResponse;
import com.nerdket.market.app.service.board.NoticeDetailDto;
import com.nerdket.market.app.service.board.NoticeListDto;
import com.nerdket.market.app.service.board.NoticeRegisterDto;
import com.nerdket.market.app.service.board.NoticeService;
import com.nerdket.market.app.service.user.UserDto;
import com.nerdket.market.util.jwt.JwtTokenService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping()
    public SuccessResponse listNotice(@PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<NoticeListDto> noticeList = noticeService.listNotice(pageable).map(NoticeListDto::new);
        return new SuccessResponse(new NoticeListResponse(noticeList));
    }

    @GetMapping("{boardId}")
    public SuccessResponse getNotice(@PathVariable long boardId) {
        NoticeDetailDto notice = noticeService.getNotice(boardId);
        return new SuccessResponse(new NoticeGetResponse(notice));
    }

    @PostMapping("{boardId}")
    public SuccessResponse updateNotice(@PathVariable long boardId) {
        return null;
    }

    @DeleteMapping("{boardId}")
    public SuccessResponse deleteNotice(@PathVariable long boardId) {
        return null;
    }


    @Data
    static class NoticeRegisterRequest {
        @NotEmpty
        private String title;

        @NotEmpty
        private String content;

        public NoticeRegisterDto getNoticeDto(String username) {
            return NoticeRegisterDto.builder().title(title).content(content).username(username).build();
        }

    }

    @Data
    @AllArgsConstructor
    static class NoticeListResponse {
        private Page<NoticeListDto> noticeList;
    }

    @Data
    @AllArgsConstructor
    static class NoticeGetResponse {
        private NoticeDetailDto noticeDetailDto;
    }


}