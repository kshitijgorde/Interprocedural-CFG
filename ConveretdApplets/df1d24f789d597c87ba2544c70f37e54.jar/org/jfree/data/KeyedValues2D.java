// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import java.util.List;

public interface KeyedValues2D extends Values2D
{
    Comparable getRowKey(final int p0);
    
    int getRowIndex(final Comparable p0);
    
    List getRowKeys();
    
    Comparable getColumnKey(final int p0);
    
    int getColumnIndex(final Comparable p0);
    
    List getColumnKeys();
    
    Number getValue(final Comparable p0, final Comparable p1);
}
