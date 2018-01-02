// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.canadiantax;

import com.mindprod.common11.BigDate;
import java.io.Serializable;

class ProvincialTaxFact implements Comparable<ProvincialTaxFact>, Serializable
{
    static final long serialVersionUID = 1L;
    private final BigDate date;
    private final boolean taxOnTax;
    private final double gstRate;
    private final double hstRate;
    private final double pstRate;
    
    public final int compareTo(final ProvincialTaxFact other) {
        return other.date.compareTo(this.date);
    }
    
    public BigDate getDate() {
        return this.date;
    }
    
    public double getGstRate() {
        return this.gstRate;
    }
    
    public double getHstRate() {
        return this.hstRate;
    }
    
    public double getPstRate() {
        return this.pstRate;
    }
    
    public boolean isTaxOnTax() {
        return this.taxOnTax;
    }
    
    ProvincialTaxFact(final BigDate date, final double gstRate, final double hstRate, final double pstRate, final boolean taxOnTax) {
        this.date = date;
        this.gstRate = gstRate;
        this.hstRate = hstRate;
        this.pstRate = pstRate;
        this.taxOnTax = taxOnTax;
    }
}
