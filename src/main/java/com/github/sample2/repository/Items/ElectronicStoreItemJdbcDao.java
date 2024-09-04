/*
Yedam Lee
This class represents the methods in ElectronicStoreItemRepository Interface.
Where the methods are called from the service layer to get Item information from the database.
We use singleton design pattern using @Repository annotation.
 */
package com.github.sample2.repository.Items;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ElectronicStoreItemJdbcDao implements ElectronicStoreItemRepository{

    //using JdbcTemplate to communicate with the database
    private JdbcTemplate jdbcTemplate;

    //rowMapper maps each entity(java object) to DB's table
    //this is also a functional interface so we can use its function
    //rowMapper reads line by line (rowNum) and gets each property (rs)
    //each rs is mapped to certain value ex.id,name ...
    static RowMapper<ItemEntity> itemEntityRowMapper = (((rs, rowNum) ->
                new ItemEntity(
                        rs.getInt("id"),

                        //"N" means NOT NULL
                        rs.getNString("name"),
                        rs.getNString("type"),
                        rs.getInt("price"),
                        rs.getInt("store_id"),
                        rs.getInt("stock"),
                        rs.getNString("cpu"),
                        rs.getNString("capacity"))
            ));

    //we make the constructor to call the bean, which is in the JdbcConfig
    public ElectronicStoreItemJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //it is not likely to change the types here, we move that step to the service layer
    //this is so the dto does not get into dao layer
    @Override
    public List<ItemEntity> findAllItems() {

        //since jdbcTemplate is connected to DB, we can use query to GET what we want
        return jdbcTemplate.query("SELECT * FROM item", itemEntityRowMapper);
    }

    //method used when we receive POST from the web layer -> service layer
    @Override
    public Integer saveItem(ItemEntity itemEntity) {

        //we use update to POST (get each property of itemBody in the DB)
        jdbcTemplate.update("INSERT INTO item(name, type, price, cpu, capacity) VALUES (?, ?, ?, ?, ?)",
                            itemEntity.getName(), itemEntity.getType(), itemEntity.getPrice(),
                            itemEntity.getCpu(), itemEntity.getCapacity());

        //we get an object from the DB where the name equals itemBody.getName()
        ItemEntity itemEntityFound = jdbcTemplate.queryForObject("SELECT * FROM item WHERE name = ?", itemEntityRowMapper, itemEntity.getName());

        //return the id of new value;
        return itemEntityFound.getId();
    }

    //method used when we want to update the information of an item
    @Override
    public ItemEntity updateItemEntity(Integer idInt, ItemEntity itemEntity) {

        //using SQL to update new information of "idInt"
        jdbcTemplate.update("UPDATE item " +
                            "SET name = ?, type = ?, price = ?, cpu = ?, capacity = ? " +
                            "WHERE id = ? ",
                            itemEntity.getName(), itemEntity.getType(),
                            itemEntity.getPrice(), itemEntity.getCpu(), itemEntity.getCapacity(), idInt);

        //returning the updated ItemEntity with the "idInt"
        return jdbcTemplate.queryForObject("SELECT * FROM item WHERE id = ?", itemEntityRowMapper, idInt);



    }

    //method used to find Item using path id
    @Override
    public ItemEntity findItemById(Integer idInt) {

        return jdbcTemplate.queryForObject("SELECT * FROM item WHERE id = ?", itemEntityRowMapper, idInt);
    }

    //method used to delete Item from the database
    @Override
    public void deleteItem(int id) {

        jdbcTemplate.update("DELETE item " +
                        "WHERE id = ? ", id);

    }

    //method used to update the number of stocks of the Item in the database
    @Override
    public void updateItemStock(Integer itemId, Integer stock) {
        jdbcTemplate.update("UPDATE item " +
                        "SET stock = ? " +
                        "WHERE id = ? ", stock, itemId);

    }
}
