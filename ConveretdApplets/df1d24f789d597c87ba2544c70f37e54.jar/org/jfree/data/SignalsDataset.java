// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

public interface SignalsDataset extends XYDataset
{
    public static final int ENTER_LONG = 1;
    public static final int ENTER_SHORT = -1;
    public static final int EXIT_LONG = 2;
    public static final int EXIT_SHORT = -2;
    
    int getType(final int p0, final int p1);
    
    double getLevel(final int p0, final int p1);
}
