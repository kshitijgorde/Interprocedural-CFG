// 
// Decompiled by Procyon v0.5.30
// 

package org.xml.sax.ext;

import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import org.xml.sax.EntityResolver;

public interface EntityResolver2 extends EntityResolver
{
    InputSource getExternalSubset(final String p0, final String p1) throws SAXException, IOException;
    
    InputSource resolveEntity(final String p0, final String p1, final String p2, final String p3) throws SAXException, IOException;
}
