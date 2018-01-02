// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.statistics;

import org.jfree.data.category.CategoryDataset;

public interface StatisticalCategoryDataset extends CategoryDataset
{
    Number getMeanValue(final int p0, final int p1);
    
    Number getMeanValue(final Comparable p0, final Comparable p1);
    
    Number getStdDevValue(final int p0, final int p1);
    
    Number getStdDevValue(final Comparable p0, final Comparable p1);
}
