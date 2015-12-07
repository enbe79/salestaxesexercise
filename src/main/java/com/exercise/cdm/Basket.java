package com.exercise.cdm;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Created by BellassaiEP on 29/11/2015.
 */
@Component
public class Basket {

	private static final String RECEIPT_PROD_INFO_STR = "%d %s%s: %s\n";
	private static final String SALES_TAX_STR = "Sales Taxes: %s\n";
	private static final String TOTAL_STR = "Total: %s";
	private static final NumberFormat priceFormatter = new DecimalFormat("#0.00",
			DecimalFormatSymbols.getInstance(Locale.ENGLISH));

	@Autowired
	private Taxes taxes;

	@Getter
	private final List<Order> orders = new ArrayList<Order>();

	/**
	 * Adds a new Order
	 * @param order the Order to add
	 */
	public void add(final Order order) {
		orders.add(order);
	}

	/**
	 * Removes an Order
	 * @param order the Order to remove
	 * @return true if the remove operation succeed
	 */
	public boolean remove(final Order order) {
		return orders.remove(order);
	}

	/**
	 * @return an Iterator of the added Orders
	 */
	public Iterator<Order> iterator() {
		return orders.iterator();
	}

	/**
	 * Build a receipt containing info about all orders
	 * @return a String representing the receipt
	 */
	public String getReceipt() {
		final StringBuilder receipt = new StringBuilder();
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal totalTax = BigDecimal.ZERO;
		for (final Order o : orders) {
			final BigDecimal basicPrice = BigDecimal.valueOf(o.getProduct().getPrice() * o.getQuantity());
			final BigDecimal tax = BigDecimal.valueOf(getTax(o));

			final BigDecimal orderTotPrice = basicPrice.add(tax);

			receipt.append(String.format(RECEIPT_PROD_INFO_STR,
					o.getQuantity(),
					o.getProduct().isImported() ? "imported " : "",
					o.getProduct().getName(),
					priceFormatter.format(orderTotPrice)));

			totalTax = totalTax.add(tax);
			total = total.add(orderTotPrice);
		}
		receipt.append(String.format(SALES_TAX_STR, priceFormatter.format(totalTax)));
		receipt.append(String.format(TOTAL_STR, priceFormatter.format(total)));
		return receipt.toString();
	}

	private double getTax(final Order order) {
		final double tax = taxes.getTax(order.getProduct()) * order.getQuantity();
		long round = Math.round(tax * 100.0);
		final long tmp = round % 5;
		if (tmp != 0) {
			round = round + 5 - tmp;
		}
		return round / 100.0;
	}

	/**
	 * Empty all Orders in the basket
	 */
	public void clear() {
		orders.clear();
	}
}
