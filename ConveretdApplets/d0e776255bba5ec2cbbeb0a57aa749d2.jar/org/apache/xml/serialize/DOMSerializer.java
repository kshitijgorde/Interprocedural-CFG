// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serialize;

import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Document;
import java.io.IOException;
import org.w3c.dom.Element;

public interface DOMSerializer
{
    void serialize(final Element p0) throws IOException;
    
    void serialize(final Document p0) throws IOException;
    
    void serialize(final DocumentFragment p0) throws IOException;
}
