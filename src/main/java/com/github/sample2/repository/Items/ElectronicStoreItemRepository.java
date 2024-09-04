package com.github.sample2.repository.Items;

import java.util.List;

public interface ElectronicStoreItemRepository {
    List<ItemEntity> findAllItems();

    Integer saveItem(ItemEntity itemEntity);

    ItemEntity updateItemEntity(Integer idInt, ItemEntity itemEntity);

    ItemEntity findItemById(Integer idInt);

    void deleteItem(int i);

    void updateItemStock(Integer itemId, Integer i);
}
