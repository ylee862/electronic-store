/*
Yedam Lee
This Class represents a Data Transfer Object which carries information,
the id of the product and the amount of an item the customer wants to purchase.
 */
package com.github.sample2.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BuyOrder {

    private Integer itemId;
    private Integer itemNums;

}
