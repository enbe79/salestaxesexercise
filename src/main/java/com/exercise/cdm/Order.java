package com.exercise.cdm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by BellassaiEP on 29/11/2015.
 */
@AllArgsConstructor
public class Order {

    @Getter
    private Product product;
    @Getter
    @Setter
    private int quantity;

}
