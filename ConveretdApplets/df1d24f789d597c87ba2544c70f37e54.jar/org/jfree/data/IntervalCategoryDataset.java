// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

public interface IntervalCategoryDataset extends CategoryDataset
{
    Number getStartValue(final int p0, final int p1);
    
    Number getStartValue(final Comparable p0, final Comparable p1);
    
    Number getEndValue(final int p0, final int p1);
    
    Number getEndValue(final Comparable p0, final Comparable p1);
}
