// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.canadiantax;

import com.mindprod.common11.BigDate;

class CalculateCanadianTaxes
{
    private boolean taxOnTax;
    private double gstAmount;
    private double gstRate;
    private double hstAmount;
    private double hstRate;
    private double pstAmount;
    private double pstRate;
    private double totalPayableAmount;
    private double totalTaxAmount;
    
    public void calculateCanadianTaxes(final Province province, final BigDate date, final double saleAmount) {
        final ProvincialTaxFact taxFact = province.findTaxFactForDate(date);
        this.gstRate = taxFact.getGstRate();
        this.hstRate = taxFact.getHstRate();
        this.pstRate = taxFact.getPstRate();
        this.taxOnTax = taxFact.isTaxOnTax();
        this.calculateCanadianTaxesGuts(saleAmount);
    }
    
    public double calculateSaleAmount(final Province province, final BigDate date, final double targetTotalPayable) {
        final ProvincialTaxFact taxFact = province.findTaxFactForDate(date);
        this.gstRate = taxFact.getGstRate();
        this.hstRate = taxFact.getHstRate();
        this.pstRate = taxFact.getPstRate();
        this.taxOnTax = taxFact.isTaxOnTax();
        final double totalTaxRate = this.getTotalTaxRate();
        double saleAmount = targetTotalPayable / (1.0 + totalTaxRate / 100.0);
        double bestDiff = 9.99999999E8;
        double bestSaleAmount = saleAmount;
        while (true) {
            this.calculateCanadianTaxesGuts(saleAmount);
            final double diff = this.totalPayableAmount - targetTotalPayable;
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
    
    public double getEffectivePstRate() {
        return this.taxOnTax ? (this.pstRate * (1.0 + this.gstRate / 100.0)) : this.pstRate;
    }
    
    public double getGstAmount() {
        return dollarRounded(this.gstAmount);
    }
    
    public double getGstRate() {
        return this.gstRate;
    }
    
    public double getHstAmount() {
        return dollarRounded(this.hstAmount);
    }
    
    public double getHstRate() {
        return this.hstRate;
    }
    
    public double getPstAmount() {
        return dollarRounded(this.pstAmount);
    }
    
    public double getPstRate() {
        return this.pstRate;
    }
    
    public double getTotalPayableAmount() {
        return dollarRounded(this.totalPayableAmount);
    }
    
    public double getTotalTaxAmount() {
        return dollarRounded(this.totalTaxAmount);
    }
    
    public double getTotalTaxRate() {
        return (this.hstRate > 0.0) ? this.hstRate : (this.gstRate + (this.taxOnTax ? (this.pstRate * (1.0 + this.gstRate / 100.0)) : this.pstRate));
    }
    
    public boolean isTaxOnTax() {
        return this.taxOnTax;
    }
    
    private static double dollarRounded(final double dollars) {
        return Math.rint(dollars * 100.0) / 100.0;
    }
    
    private void calculateCanadianTaxesGuts(final double saleAmount) {
        this.gstAmount = saleAmount * this.gstRate / 100.0;
        this.hstAmount = saleAmount * this.hstRate / 100.0;
        final double pstBase = this.taxOnTax ? (saleAmount + this.gstAmount) : saleAmount;
        this.pstAmount = pstBase * this.pstRate / 100.0;
        this.totalTaxAmount = ((this.hstRate > 0.0) ? this.hstAmount : (this.gstAmount + this.pstAmount));
        this.totalPayableAmount = saleAmount + this.totalTaxAmount;
    }
}
