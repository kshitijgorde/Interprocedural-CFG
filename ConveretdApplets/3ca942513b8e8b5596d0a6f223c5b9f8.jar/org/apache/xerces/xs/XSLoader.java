// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xs;

import org.w3c.dom.ls.LSInput;
import org.w3c.dom.DOMConfiguration;

public interface XSLoader
{
    DOMConfiguration getConfig();
    
    XSModel loadURIList(final StringList p0);
    
    XSModel loadInputList(final LSInputList p0);
    
    XSModel loadURI(final String p0);
    
    XSModel load(final LSInput p0);
}
