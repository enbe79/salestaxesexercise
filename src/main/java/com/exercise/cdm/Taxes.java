package com.exercise.cdm;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by BellassaiEP on 28/11/2015.
 */
@Component
public class Taxes {

    private static final int BASIC_TAX = 10;
    private static final int IMPORT_TAX = 5;

    private static final Set<ProductType> basicTaxExemption = new HashSet<ProductType>();

    static {
        basicTaxExemption.add(ProductType.BOOK);
        basicTaxExemption.add(ProductType.FOOD);
        basicTaxExemption.add(ProductType.MEDICAL);
    }

    /**
     * Returns the amount of tax to apply to a given Product
     * @param product the Product
     * @return the tax (%)
     */
    public double getTax(final Product product) {
        int totalTax = product.isImported() ? IMPORT_TAX : 0;
        if (!basicTaxExemption.contains(product.getType())) {
            totalTax += BASIC_TAX;
        }

        return product.getPrice() * totalTax / 100;
    }
}
