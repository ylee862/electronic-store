/*
Name: Yedam Lee
This is a program that resembles an electronic store,
where the users can search for an item they want to buy and buy it from the store.
The store is also allowed to POST new items and UPDATE or DELETE their products.
It is designed in 3-tier architecture style.
 */

package com.github.sample2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Sample2Application {

    public static void main(String[] args) {
        SpringApplication.run(Sample2Application.class, args);
    }

}
