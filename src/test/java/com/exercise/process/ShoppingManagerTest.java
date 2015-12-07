package com.exercise.process;

import com.exercise.cdm.Product;
import com.exercise.cdm.ProductType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by BellassaiEP on 29/11/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans-test.xml"})
public class ShoppingManagerTest {

    @Autowired
    private ShoppingManager shoppingManager;

    @Before
    public void initTest() {
        shoppingManager.clearBasket();
    }

    @Test
    public void testAddOrder() throws Exception {
        Product p = new Product("MyBook", ProductType.BOOK, 15.33, false);
        shoppingManager.addOrder(p, 1);
        assertEquals(1, shoppingManager.getBasket().getOrders().size());
    }

    @Test
    public void testGetReceipt() throws Exception {
        Product p = new Product("MyBook", ProductType.BOOK, 15.33, false);
        shoppingManager.addOrder(p, 1);

        System.out.println(shoppingManager.getReceipt());
    }
}