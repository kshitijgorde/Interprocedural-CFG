// 
// Decompiled by Procyon v0.5.30
// 

package org.w3c.dom.ls;

import org.w3c.dom.DOMException;

public interface DOMImplementationLS
{
    public static final short MODE_SYNCHRONOUS = 1;
    public static final short MODE_ASYNCHRONOUS = 2;
    
    LSParser createLSParser(final short p0, final String p1) throws DOMException;
    
    LSSerializer createLSSerializer();
    
    LSInput createLSInput();
    
    LSOutput createLSOutput();
}
