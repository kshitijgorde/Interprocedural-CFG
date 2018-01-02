// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom3.as;

import org.w3c.dom.ls.DOMInputSource;
import org.w3c.dom.ls.DOMBuilder;

public interface DOMASBuilder extends DOMBuilder
{
    ASModel getAbstractSchema();
    
    void setAbstractSchema(final ASModel p0);
    
    ASModel parseASURI(final String p0) throws DOMASException, Exception;
    
    ASModel parseASInputSource(final DOMInputSource p0) throws DOMASException, Exception;
}
