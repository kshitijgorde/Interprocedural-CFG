// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.urls;

import org.jfree.data.CategoryDataset;

public interface CategoryURLGenerator
{
    String generateURL(final CategoryDataset p0, final int p1, final int p2);
    
    Object clone() throws CloneNotSupportedException;
}
