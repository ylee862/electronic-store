/*
Yedam Lee
This class connects the program to the web using Rest API.
This allows the user to GET the information of an item,
and the stores can POST their new item, DELETE it or UPDATE the information.
 */
package com.github.sample2.web.controller;

import com.github.sample2.service.ElectronicStoreItemService;
import com.github.sample2.web.dto.BuyOrder;
import com.github.sample2.web.dto.Item;
import com.github.sample2.web.dto.ItemBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controller connects us to the web and it is in the WAS, dispatch servlet
@RestController

//If we are using REST API (for JSON), we must start with /api path
//since I am using postman to update the information.
@RequestMapping("/api")
public class ElectronicStoreController {

    //bringing the service layer (connects web and db) because we cannot communicate directly with repository layer
    private ElectronicStoreItemService electronicStoreItemService;

    public ElectronicStoreController(ElectronicStoreItemService electronicStoreItemService) {
        this.electronicStoreItemService = electronicStoreItemService;
    }


    //if user goes into /api/items and we GET the request, we return a list of Item (dto).
    @GetMapping("/items")
    public List<Item> findAllItems(){

        return electronicStoreItemService.findAllItems();
    }


    //when we want to create a new item, we post it to the same url
    //and we get the requested item info from the web using (@RequestBody dto) (that is going to come in as a param)
    @PostMapping("/items")
    public String registerItem(@RequestBody ItemBody itemBody){

        //we return the ID of the item that has been added
        Integer itemId = electronicStoreItemService.saveItem(itemBody);

        return "ID: " + itemId;
    }

    //returning a value using id path, this is used when one wants to find an item that is equal to the id
    //we receive the path number using @PathVariable
    @GetMapping("/items/{id}")
    public Item findItemByPathId(@PathVariable String id){

        //returning the Item found using the "id"
        return electronicStoreItemService.findItemById(id);

    }

    //When the user wants to GET the information of more than one Items.
    //getting the param as List since we might get more than one query; id=2&id=5...
    @GetMapping("/items-queries")
    public List<Item> findItemByQueryId(@RequestParam("id") List<String> ids){

        //Returning the list of Items found using the "ids"
        return electronicStoreItemService.findItemsByIds(ids);
    }

    //Deleting the Item by getting the path ID of the item we want to delete
    @DeleteMapping("/items/{id}")
    public String deleteItemByPathId(@PathVariable String id){

        electronicStoreItemService.deleteItem(id);

        //returning a message indicating that the Item has been successfully deleted
        return "Object with id: " + id + " has been deleted";
    }

    //When updating an element, we usually get the id of what we want to give the change to
    //with the new information that needs to be updated on that same path id
    @PutMapping("/items/{id}")
    public Item updateItem(@PathVariable String id, @RequestBody ItemBody itembody){

        //returning the new Item that has been updated
        return electronicStoreItemService.updateItem(id, itembody);
    }

    //When the user wants to buy a product
    @PostMapping("/items/buy")
    public String buyItem(@RequestBody BuyOrder buyOrder){

        //getting the amount of items the user wants or can buy
        Integer orderItemNums = electronicStoreItemService.buyItems(buyOrder);

        //returning to the customer that they have bought certain amount of items
        return "You have bought " + orderItemNums + " products.";
    }

}
