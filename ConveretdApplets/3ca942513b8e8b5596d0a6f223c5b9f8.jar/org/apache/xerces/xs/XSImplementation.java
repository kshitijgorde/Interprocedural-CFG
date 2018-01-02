// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xs;

public interface XSImplementation
{
    StringList getRecognizedVersions();
    
    XSLoader createXSLoader(final StringList p0) throws XSException;
}
