package com.nerdket.market.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("notice")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Notice extends Board {

    @Builder
    public Notice(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }
}
