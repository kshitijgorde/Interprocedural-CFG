// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.currcon;

import java.io.Serializable;

public final class Exch implements Serializable
{
    private static final long serialVersionUID = 5L;
    private final String currCode;
    private final String currName;
    private final String symbol;
    private double exchangeRate;
    private final int decimalPlaces;
    
    public static long getSerialVersionUID() {
        return 5L;
    }
    
    public Exch(final String currCode, final String currName, final String symbol, final int decimalPlaces, final double exchangeRate) {
        if (currCode.length() != 3) {
            throw new IllegalArgumentException("Currency Code must be three letters.");
        }
        if (decimalPlaces < 0 | decimalPlaces > 3) {
            throw new IllegalArgumentException("bad decimal places in exchange rate table.");
        }
        this.currCode = currCode.toUpperCase();
        this.symbol = symbol;
        this.currName = currName;
        this.decimalPlaces = decimalPlaces;
        this.exchangeRate = exchangeRate;
    }
    
    public String getCurrAbbr() {
        return this.currCode;
    }
    
    public String getCurrName() {
        return this.currName;
    }
    
    public int getDecimalPlaces() {
        return this.decimalPlaces;
    }
    
    public double getExchangeRate() {
        return this.exchangeRate;
    }
    
    public void setExchangeRate(final double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
    
    public String getSymbol() {
        return this.symbol;
    }
}
