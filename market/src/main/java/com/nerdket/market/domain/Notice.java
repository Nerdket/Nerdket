package com.nerdket.market.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("notice")
public class Notice extends Board {
}
