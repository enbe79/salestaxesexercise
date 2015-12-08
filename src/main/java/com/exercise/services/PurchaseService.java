package com.exercise.services;

import com.exercise.cdm.Product;
import com.exercise.cdm.ProductType;
import com.exercise.process.ShoppingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by BellassaiEP on 29/11/2015.
 */
@Component(value = "purchase")
@Path("/sales")
public class PurchaseService {

	@Autowired
	private ShoppingManager shoppingManager;

	@POST
	@Path("/add_order")
	public Response addToBasket(@FormParam("name") String name,
								@FormParam("type") String type,
								@FormParam("price") double price,
								@FormParam("imported") String imported,
								@FormParam("amount") @DefaultValue("1") int amount) {
		ProductType pt = ProductType.valueOf(type.toUpperCase());

		boolean isImported = imported != null && imported.equalsIgnoreCase("on");
		final Product product = new Product(name, pt, price, isImported);
		shoppingManager.addOrder(product, amount);

		return Response.noContent().build();
	}

	@GET
	@Path("/receipt")
	public String showReceipt() {
		String res = shoppingManager.getReceipt();
		res = res.replaceAll("\n", "<br>");
		return res;
	}

	@POST
	@Path("/reset")
	public Response reset() {
		shoppingManager.clearBasket();
		return Response.noContent().build();
	}
}
