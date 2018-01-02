// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

public final class ANFPatternClass
{
    private boolean isStandard;
    private BdifFPatternClass standardValue;
    private String vendorValue;
    
    ANFPatternClass() {
    }
    
    public ANFPatternClass(final boolean isStandard, final BdifFPatternClass standardValue, final String vendorValue) {
        this.isStandard = isStandard;
        this.standardValue = standardValue;
        this.vendorValue = vendorValue;
    }
    
    public static ANFPatternClass getStandard(final BdifFPatternClass value) {
        return new ANFPatternClass(true, value, null);
    }
    
    public static ANFPatternClass getVendor(final String value) {
        return new ANFPatternClass(false, BdifFPatternClass.UNKNOWN, value);
    }
    
    public boolean isStandard() {
        return this.isStandard;
    }
    
    public BdifFPatternClass getStandardValue() {
        return this.standardValue;
    }
    
    public String getVendorValue() {
        return this.vendorValue;
    }
}
