// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xs;

public interface ShortList
{
    int getLength();
    
    boolean contains(final short p0);
    
    short item(final int p0) throws XSException;
}
