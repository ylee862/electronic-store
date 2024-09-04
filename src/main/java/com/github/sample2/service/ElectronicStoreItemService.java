package com.github.sample2.service;

import com.github.sample2.repository.Items.ElectronicStoreItemRepository;
import com.github.sample2.repository.Items.ItemEntity;
import com.github.sample2.repository.storeSales.StoreSales;
import com.github.sample2.repository.storeSales.StoreSalesRepository;
import com.github.sample2.web.dto.BuyOrder;
import com.github.sample2.web.dto.Item;
import com.github.sample2.web.dto.ItemBody;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


/*
Yedam Lee
This class represents the service layer of our program.
It keeps all the detailed coding necessary when we get the request using REST API in the web layer.
It is also designed in singleton pattern using @Service bean.
 */
@Service
public class ElectronicStoreItemService {

    //Connecting the service layer to the repository layer.
    private ElectronicStoreItemRepository electronicStoreItemRepository;

    //Also connecting the service layer to the store's repository layer
    private StoreSalesRepository storeSalesRepository;


    public ElectronicStoreItemService(ElectronicStoreItemRepository electronicStoreItemRepository, StoreSalesRepository storeSalesRepository) {
        this.electronicStoreItemRepository = electronicStoreItemRepository;
        this.storeSalesRepository = storeSalesRepository;
    }

    //when the web layer calls findAllItem()
    public List<Item> findAllItems() {

        //service layer connects with the repository layer to find all the items in the database as a list
        List<ItemEntity> itemEntities = electronicStoreItemRepository.findAllItems();

        //since we are returning it with Item type, we must map it to Item type
        //this is done by getting the ItemEntity's property and using Item's constructor to create new Item element
        return itemEntities.stream().map(Item::new).collect(Collectors.toList());
    }

    //when the web layer calls saveItem()
    public Integer saveItem(ItemBody itemBody) {

        //we change the ItemBody dto to ItemEntity so we can post it onto the database
        ItemEntity itemEntity = new ItemEntity(null, itemBody.getName(), itemBody.getType(), itemBody.getPrice(),
                itemBody.getSpec().getCpu(), itemBody.getSpec().getCapacity());

        //we are returning the id using saveItem method which returns new item's id
        return electronicStoreItemRepository.saveItem(itemEntity);
    }

    //when the web layer calls findItemById()
    public Item findItemById(String id) {

        //we change the String id value to Integer type
        Integer idInt = Integer.valueOf(id);

        //we search the item using the id in the repository layer
        ItemEntity itemEntity = electronicStoreItemRepository.findItemById(idInt);

        //changing the ItemEntity type back to Item
        Item item = new Item(itemEntity);

        //returning the Item found
        return item;
    }

    //when the web layer calls findItemsByIds()
    public List<Item> findItemsByIds(List<String> ids) {

        //we find a list of all the items in the database
        List<ItemEntity> itemEntities = electronicStoreItemRepository.findAllItems();

        //then we use stream() to easily sort out the Items with "ids"
        return itemEntities.stream().map(Item::new)
                .filter((item -> ids.contains(item.getId())))
                        .collect(Collectors.toList());
    }

    //when the web layer calls deleteItem()
    public void deleteItem(String id) {

        //we delete it by getting the id of an item
        electronicStoreItemRepository.deleteItem(Integer.parseInt(id));
    }

    //when the web layer calls updateItem
    public Item updateItem(String id, ItemBody itembody) {

        //we change the String type to Integer
        Integer idInt = Integer.valueOf(id);

        //creating new entity with new information (itembody)
        ItemEntity itemEntity = new ItemEntity(idInt, itembody.getName(), itembody.getType(),
                itembody.getPrice(), itembody.getSpec().getCpu(), itembody.getSpec().getCapacity());

        //getting the value of updated entity
        ItemEntity itemEntityUpdated = electronicStoreItemRepository.updateItemEntity(idInt, itemEntity);

        //changing it back to Item type
        Item itemUpdated = new Item(itemEntityUpdated);

        return itemUpdated;
    }

    //making this method transactional using the annotation
    //when the user wants to buy the item
    @Transactional
    public Integer buyItems(BuyOrder buyOrder) {

        //getting id and amount (how much one wants to buy) of the item buyOrder
        Integer itemId = buyOrder.getItemId();
        Integer itemAmount = buyOrder.getItemNums();

        //we look for the product in the repository layer + we check the amount of products left
        ItemEntity itemEntity = electronicStoreItemRepository.findItemById(itemId);

        //if there is no such store available we throw an exception
        if (itemEntity.getStoreId() == null) throw new RuntimeException("No such market is found");

        //if there is no more products left to buy, then we throw an exception
        if (itemEntity.getStock() <= 0) throw new RuntimeException("It is sold out");

        //getting the number of Items the customer CAN buy
        Integer successBuyItemNums;

        //if the amount of items the customer wants to purchase is equal or more than the stock
        if (itemAmount >= itemEntity.getStock()) {

            //we give the customer the maximum amount of items
            successBuyItemNums = itemEntity.getStock();
        }

        //if not, then we let the customer buy only the amount they want to purchase
        else {
            successBuyItemNums = itemAmount;
        }

        //if the amount of items has been determined, we find out the price
        Integer totalPrice = successBuyItemNums * itemEntity.getPrice();

        //then we get rid of the number of products that has been sold in the database
        electronicStoreItemRepository.updateItemStock(itemId, itemEntity.getStock() - successBuyItemNums);

        //checking how much there was in the store in the first place
        StoreSales storeSales = storeSalesRepository.findStoreSalesById(itemEntity.getStoreId());

        //then we add the total sales amount to the store
        storeSalesRepository.updateSalesAmount(itemEntity.getStoreId(), storeSales.getAmount() + totalPrice);

        //returning the amount of items the customers bought
        return successBuyItemNums;

    }
}
