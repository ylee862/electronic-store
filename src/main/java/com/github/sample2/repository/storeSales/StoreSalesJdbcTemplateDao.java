/*
Yedam Lee
This class is a repository layer that connects StoreSales database to our program.
It consists of the methods that is required from the service layer.
We communicate with the database using SQL and return the information we gained from the database.
 */
package com.github.sample2.repository.storeSales;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StoreSalesJdbcTemplateDao implements StoreSalesRepository{

    //using JdbcTemplate to communicate with database
    private JdbcTemplate jdbcTemplate;

    public StoreSalesJdbcTemplateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //using RowMapper to map each column from the database to StoreSales Entity.
    static RowMapper<StoreSales> storeSalesRowMapper = (((rs, rowNum) ->
            new StoreSales(
                    rs.getInt("id"),
                    rs.getNString("store_name"),
                    rs.getInt("amount"))
            ));

    //when service layer calls findStoreSalesById()
    @Override
    public StoreSales findStoreSalesById(Integer storeId) {

        //we use the storeId to check if we have the according store in our database
        return jdbcTemplate.queryForObject("SELECT * from store_sales " +
                                            "WHERE id = ? ", storeSalesRowMapper, storeId);
    }

    //when service layer calls updateSalesAmount()
    @Override
    public void updateSalesAmount(Integer storeId, Integer amount) {

        //we use the storeId and amount to update the store's sales after the customer has bought n number of products.
        jdbcTemplate.update("UPDATE store_sales " +
                "SET amount = ? " +
                "WHERE id = ? ", amount, storeId);

    }
}
