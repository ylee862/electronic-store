/*
Yedam Lee
This class represents an entity which is similar to dto, but different since entity must have same properties as the table in the database.
It basically represents a table in the database in java.
 */
package com.github.sample2.repository.Items;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

//the name of the entity and the table does not need to match
//only the properties must
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class ItemEntity {

    private Integer id;
    private String name;
    private String type;
    private Integer price;

    private Integer storeId;
    private Integer stock;
    private String cpu;
    private String capacity;

    public ItemEntity(Integer id, String name, String type, Integer price, String cpu, String capacity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.storeId = null;
        this.stock = 0;
        this.cpu = cpu;
        this.capacity = capacity;
    }

}
