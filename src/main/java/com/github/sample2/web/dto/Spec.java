/*
Yedam Lee
This class represents a Spec dto, which is used to keep the spec of an electronic product (cpu and capacity).
 */
package com.github.sample2.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Spec {
    private String cpu;
    private String capacity;
}
