// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import org.w3c.dom.ls.LSSerializerFilter;
import org.w3c.dom.DOMErrorHandler;
import java.io.IOException;
import org.w3c.dom.Node;

public interface DOM3Serializer
{
    void serializeDOM3(final Node p0) throws IOException;
    
    void setErrorHandler(final DOMErrorHandler p0);
    
    DOMErrorHandler getErrorHandler();
    
    void setNodeFilter(final LSSerializerFilter p0);
    
    LSSerializerFilter getNodeFilter();
    
    void setNewLine(final char[] p0);
}
