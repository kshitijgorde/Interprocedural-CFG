// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl;

import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.XMLResourceIdentifier;

public interface XMLEntityHandler
{
    void startEntity(final String p0, final XMLResourceIdentifier p1, final String p2) throws XNIException;
    
    void endEntity(final String p0) throws XNIException;
}
