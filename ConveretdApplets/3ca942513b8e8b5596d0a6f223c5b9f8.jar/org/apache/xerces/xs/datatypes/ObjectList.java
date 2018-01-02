// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xs.datatypes;

public interface ObjectList
{
    int getLength();
    
    boolean contains(final Object p0);
    
    Object item(final int p0);
}
