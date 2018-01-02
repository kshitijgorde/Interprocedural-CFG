// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.americantax;

import java.util.Iterator;
import java.util.Vector;

public final class AmericanTaxTable
{
    private static final boolean DEBUGGING = true;
    
    public static double calcDistrictSalesTax(final DistrictItem districtItem, final double saleAmount) {
        return calcTax(districtItem.getDistrictTaxRate(), saleAmount);
    }
    
    public static double calcSaleAmount(final DistrictItem district, final double totalPayable) {
        double saleAmount = dollarRounded(totalPayable / (1.0 + district.getTotalTaxRate() / 1000000.0));
        double bestDiff = 999999.0;
        double bestSaleAmount = saleAmount;
        while (true) {
            final double totalPayable2 = calcTotalPayable(district, saleAmount);
            final double diff = totalPayable2 - totalPayable;
            if (Math.abs(diff) >= bestDiff) {
                return bestSaleAmount;
            }
            bestSaleAmount = saleAmount;
            bestDiff = Math.abs(diff);
            if (diff == 0.0) {
                return bestSaleAmount;
            }
            if (diff > 0.0) {
                saleAmount -= 0.01;
            }
            else {
                saleAmount += 0.01;
            }
            saleAmount = dollarRounded(saleAmount);
        }
    }
    
    public static double calcStateSalesTax(final DistrictItem districtItem, final double saleAmount) {
        return calcTax(districtItem.getStateTaxRate(), saleAmount);
    }
    
    public static double calcTax(final int rate, final double saleAmount) {
        final long saleInPennies = (long)(saleAmount * 100.0 + 0.5);
        final long taxScaled = saleInPennies * rate;
        final long taxInPennies = (taxScaled + 500000L) / 1000000L;
        return taxInPennies / 100.0;
    }
    
    public static double calcTotalPayable(final DistrictItem districtItem, final double saleAmount) {
        return calcTax(districtItem.getTotalTaxRate(), saleAmount) + saleAmount;
    }
    
    public static double calcTotalSalesTax(final DistrictItem districtItem, final double saleAmount) {
        return calcTax(districtItem.getTotalTaxRate(), saleAmount);
    }
    
    private static double dollarRounded(final double dollars) {
        return Math.rint(dollars * 100.0) / 100.0;
    }
    
    public static void main(final String[] args) {
        final Vector<StateItem> states = StateItem.getStateChoices();
        for (final StateItem state : states) {
            System.out.println(state);
            final Vector<DistrictItem> districts = state.getDistrictItems();
            for (final DistrictItem district : districts) {
                System.out.print(district + ":");
            }
        }
    }
}
