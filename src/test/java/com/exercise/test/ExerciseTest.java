package com.exercise.test;

import com.exercise.cdm.Product;
import com.exercise.cdm.ProductType;
import com.exercise.process.ShoppingManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by BellassaiEP on 07/12/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans-test.xml"})
public class ExerciseTest {

	private static final String RES1 = "1 book: 12.49\n" +
			"1 music CD: 16.49\n" +
			"1 chocolate bar: 0.85\n" +
			"Sales Taxes: 1.50\n" +
			"Total: 29.83";
	private static final String RES2 = "1 imported box of chocolates: 10.50\n" +
			"1 imported bottle of perfume: 54.65\n" +
			"Sales Taxes: 7.65\n" +
			"Total: 65.15";
	private static final String RES3 = "1 imported bottle of perfume: 32.19\n" +
			"1 bottle of perfume: 20.89\n" +
			"1 packet of headache pills: 9.75\n" +
			"1 imported box of chocolates: 11.85\n" +
			"Sales Taxes: 6.70\n" +
			"Total: 74.68";

	@Autowired
	private ShoppingManager shoppingManager;

	@Before
	public void init() {
		shoppingManager.clearBasket();
	}

	@Test
	public void testInput1() {
		Product p = new Product("book", ProductType.BOOK, 12.49, false);
		shoppingManager.addOrder(p, 1);

		p = new Product("music CD", ProductType.MUSIC, 14.99, false);
		shoppingManager.addOrder(p, 1);

		p = new Product("chocolate bar", ProductType.FOOD, 0.85, false);
		shoppingManager.addOrder(p, 1);

		String receipt = shoppingManager.getReceipt();
		Assert.assertEquals(RES1, receipt);

		System.out.println("Output 1:");
		System.out.println(receipt);
	}

	@Test
	public void testInput2() {
		Product p = new Product("box of chocolates", ProductType.FOOD, 10, true);
		shoppingManager.addOrder(p, 1);

		p = new Product("bottle of perfume", ProductType.OTHER, 47.50, true);
		shoppingManager.addOrder(p, 1);

		String receipt = shoppingManager.getReceipt();
		Assert.assertEquals(RES2, receipt);

		System.out.println("Output 2:");
		System.out.println(receipt);
	}

	@Test
	public void testInput3() {
		Product p = new Product("bottle of perfume", ProductType.OTHER, 27.99, true);
		shoppingManager.addOrder(p, 1);

		p = new Product("bottle of perfume", ProductType.OTHER, 18.99, false);
		shoppingManager.addOrder(p, 1);

		p = new Product("packet of headache pills", ProductType.MEDICAL, 9.75, false);
		shoppingManager.addOrder(p, 1);

		p = new Product("box of chocolates", ProductType.FOOD, 11.25, true);
		shoppingManager.addOrder(p, 1);

		String receipt = shoppingManager.getReceipt();
		Assert.assertEquals(RES3, receipt);

		System.out.println("Output 3:");
		System.out.println(receipt);
	}
}
