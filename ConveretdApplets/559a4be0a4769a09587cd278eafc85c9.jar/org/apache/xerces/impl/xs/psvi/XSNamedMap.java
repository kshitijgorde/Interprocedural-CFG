// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.psvi;

public interface XSNamedMap
{
    int getMapLength();
    
    XSObject getNSItem(final String p0, final String p1);
    
    XSObject getItem(final int p0);
}
