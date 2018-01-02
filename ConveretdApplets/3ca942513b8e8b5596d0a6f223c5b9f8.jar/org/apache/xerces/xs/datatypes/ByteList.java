// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xs.datatypes;

import org.apache.xerces.xs.XSException;

public interface ByteList
{
    int getLength();
    
    boolean contains(final byte p0);
    
    byte item(final int p0) throws XSException;
}
