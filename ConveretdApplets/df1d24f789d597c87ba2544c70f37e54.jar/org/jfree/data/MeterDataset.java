// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

public interface MeterDataset extends ValueDataset
{
    public static final int NORMAL_DATA = 0;
    public static final int WARNING_DATA = 1;
    public static final int CRITICAL_DATA = 2;
    public static final int FULL_DATA = 3;
    
    Number getMinimumValue();
    
    Number getMaximumValue();
    
    Number getMinimumNormalValue();
    
    Number getMaximumNormalValue();
    
    Number getMinimumWarningValue();
    
    Number getMaximumWarningValue();
    
    Number getMinimumCriticalValue();
    
    Number getMaximumCriticalValue();
    
    boolean isValueValid();
    
    String getUnits();
    
    int getBorderType();
}
