/*
Yedam Lee
This class represents a StoreSales Entity where it has the information of the store.
 */
package com.github.sample2.repository.storeSales;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@AllArgsConstructor
@Setter
@EqualsAndHashCode
public class StoreSales {
    private Integer id;
    private String storeName;
    private Integer amount;
}
