// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.psvi;

import org.apache.xerces.xni.psvi.AttributePSVI;
import org.apache.xerces.xni.psvi.ElementPSVI;

public interface PSVIProvider
{
    ElementPSVI getElementPSVI();
    
    AttributePSVI getAttributePSVI(final int p0);
    
    AttributePSVI getAttributePSVIByName(final String p0, final String p1);
}
