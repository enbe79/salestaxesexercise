package com.exercise.process;

import com.exercise.cdm.Basket;
import com.exercise.cdm.Order;
import com.exercise.cdm.Product;
import com.exercise.cdm.Taxes;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BellassaiEP on 29/11/2015.
 */
@Service
public class ShoppingManager {

    @Autowired
    @Getter
    private Basket basket;
    @Autowired
    private Taxes taxes;

    /**
     * Add to the shopping bag a Product and its quantity
     * @param product the chosen Product
     * @param quantity the amount of Product
     */
    public void addOrder(final Product product, final int quantity) {
        final Order order = new Order(product, quantity);
        basket.add(order);
    }

    /**
     * @return returns a String representing the receipt
     */
    public String getReceipt() {
        return basket.getReceipt();
    }

    /**
     * Resets the session
     */
    public void clearBasket() {
        basket.clear();
    }
}
