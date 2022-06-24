package com.nerdket.market.service.board;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QnaDto{
    private String title;
    private String content;
    private long userId;

}