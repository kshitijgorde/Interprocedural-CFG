// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.powcost;

import java.io.Serializable;

public class CostLocItem implements Serializable
{
    public static final long serialVersionUID = 1L;
    private final String countryAbbr;
    private final String currencyAbbr;
    private final String locationDescription;
    private final String stateAbbr;
    private final double costPerKwh;
    
    public CostLocItem(final String countryAbbr, final String stateAbbr, final String locationDescription, final double costPerKwh, final String currencyAbbr) {
        this.countryAbbr = countryAbbr;
        this.stateAbbr = stateAbbr;
        this.locationDescription = locationDescription;
        this.costPerKwh = costPerKwh;
        this.currencyAbbr = currencyAbbr;
    }
    
    public double getCostPerKwh() {
        return this.costPerKwh;
    }
    
    public String getCountryAbbr() {
        return this.countryAbbr;
    }
    
    public String getCurrencyAbbr() {
        return this.currencyAbbr;
    }
    
    public String getLocationDescription() {
        return this.locationDescription;
    }
    
    public String getStateAbbr() {
        return this.stateAbbr;
    }
    
    public String toString() {
        return this.countryAbbr + " " + this.stateAbbr + " " + this.locationDescription;
    }
}
