// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.parser;

import java.io.IOException;
import org.xml.sax.SAXException;
import java.net.URL;
import org.xml.sax.InputSource;
import org.xml.sax.EntityResolver;
import org.xml.sax.Locator;

class ExternalEntity extends EntityDecl
{
    String systemId;
    String publicId;
    String notation;
    
    public ExternalEntity(final Locator l) {
    }
    
    public InputSource getInputSource(final EntityResolver r) throws SAXException, IOException {
        InputSource retval = r.resolveEntity(this.publicId, this.systemId);
        if (retval == null) {
            retval = Resolver.createInputSource(new URL(this.systemId), false);
        }
        return retval;
    }
}
