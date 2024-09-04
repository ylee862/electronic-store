/*
Yedam Lee
This Class represents an Item dto, where it carries the information of the Item in the electronic store.
It needs an empty constructor and getters in order to be serialized and deserialized into json.
 */
package com.github.sample2.web.dto;

import com.github.sample2.repository.Items.ItemEntity;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Item {
    private String id;
    private String name;
    private String type;
    private int price;
    private Spec spec;

    //When we want to get Item using ItemBody
    public Item(Integer id, ItemBody itemBody){
        this.id = String.valueOf(id);
        this.name = itemBody.getName();
        this.type = itemBody.getType();
        this.spec = itemBody.getSpec();
        this.price = itemBody.getPrice();
    }

    //When we want to get Item using ItemEntity
    public Item(ItemEntity itemEntity){
        this.id = String.valueOf(id);
        this.name = itemEntity.getName();
        this.type = itemEntity.getType();
        this.spec = new Spec(itemEntity.getCpu(), itemEntity.getCapacity());
        this.price = itemEntity.getPrice();
    }

}
