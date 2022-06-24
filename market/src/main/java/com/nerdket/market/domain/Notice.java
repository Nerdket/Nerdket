package com.nerdket.market.domain;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("notice")
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Notice extends Board {

    @Builder
    public Notice(String title, String content, User user, Long hit) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.hit = hit;
    }
}
