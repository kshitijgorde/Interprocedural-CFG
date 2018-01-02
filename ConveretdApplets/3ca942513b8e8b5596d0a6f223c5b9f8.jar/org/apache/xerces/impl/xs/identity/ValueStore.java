// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.identity;

import org.apache.xerces.xs.ShortList;

public interface ValueStore
{
    void addValue(final Field p0, final Object p1, final short p2, final ShortList p3);
    
    void reportError(final String p0, final Object[] p1);
}
