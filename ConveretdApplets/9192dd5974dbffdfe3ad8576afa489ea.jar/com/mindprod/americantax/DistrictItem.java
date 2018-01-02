// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.americantax;

import java.util.Comparator;
import java.io.Serializable;

final class DistrictItem implements Serializable
{
    public static final long serialVersionUID = 4L;
    private final String cityName;
    private final String countyName;
    private transient byte sortCategory;
    private final int districtTaxRate;
    private final int stateTaxRate;
    
    public int getDistrictTaxRate() {
        return this.districtTaxRate;
    }
    
    public int getStateTaxRate() {
        return this.stateTaxRate;
    }
    
    public int getTotalTaxRate() {
        return this.stateTaxRate + this.districtTaxRate;
    }
    
    public String toString() {
        if (this.cityName.length() > 0) {
            if (this.countyName.length() > 0) {
                return this.cityName + " in " + this.countyName;
            }
            return this.cityName;
        }
        else {
            if (this.countyName.length() <= 0) {
                return "state tax only";
            }
            if (this.countyName.endsWith("incomplete")) {
                return this.countyName;
            }
            return this.countyName + " county";
        }
    }
    
    DistrictItem(final StateItem stateItem, final String countyName, final String cityName, final double districtPercent) {
        this.countyName = countyName.intern();
        this.cityName = cityName.intern();
        this.districtTaxRate = (int)(districtPercent * 10000.0 + 0.5);
        this.stateTaxRate = stateItem.getStateTaxRate();
        this.setSortCategory();
    }
    
    private void setSortCategory() {
        if (this.cityName.length() > 0) {
            if (this.countyName.length() > 0) {
                this.sortCategory = 1;
            }
            else {
                this.sortCategory = 2;
            }
        }
        else if (this.countyName.length() > 0) {
            this.sortCategory = 3;
        }
        else {
            this.sortCategory = 0;
        }
    }
    
    static class DisplayOrder implements Comparator<DistrictItem>
    {
        public final int compare(final DistrictItem a, final DistrictItem b) {
            int diff = a.sortCategory - b.sortCategory;
            if (diff != 0) {
                return diff;
            }
            diff = a.cityName.compareToIgnoreCase(b.cityName);
            if (diff != 0) {
                return diff;
            }
            return a.countyName.compareToIgnoreCase(b.countyName);
        }
    }
}
