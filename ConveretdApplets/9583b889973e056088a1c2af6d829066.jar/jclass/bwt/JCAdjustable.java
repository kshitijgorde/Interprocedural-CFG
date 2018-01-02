// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

public interface JCAdjustable
{
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    
    int getOrientation();
    
    void setMinimum(final int p0);
    
    int getMinimum();
    
    void setMaximum(final int p0);
    
    int getMaximum();
    
    void setUnitIncrement(final int p0);
    
    int getUnitIncrement();
    
    void setBlockIncrement(final int p0);
    
    int getBlockIncrement();
    
    void setVisibleAmount(final int p0);
    
    int getVisibleAmount();
    
    void setValue(final int p0);
    
    int getValue();
    
    void addAdjustmentListener(final JCAdjustmentListener p0);
    
    void removeAdjustmentListener(final JCAdjustmentListener p0);
}
