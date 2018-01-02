// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xs;

public interface XSNamedMap
{
    int getLength();
    
    XSObject item(final int p0);
    
    XSObject itemByName(final String p0, final String p1);
}
