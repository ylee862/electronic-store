/*
Yedam Lee
This class represents a ItemBody dto, which is used when the user POSTS new Item.
It does not have an ID indicated yet since we are just receiving it as a parameter.
 */
package com.github.sample2.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemBody {
    private String name;
    private String type;
    private int price;
    private Spec spec;
}
