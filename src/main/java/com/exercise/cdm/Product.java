package com.exercise.cdm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Created by BellassaiEP on 29/11/2015.
 */
@AllArgsConstructor
@Data
public class Product {

    private final UUID id = UUID.randomUUID();

    private String name;
    private ProductType type;
    private double price;
    private boolean imported;

}
