// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet.capability;

import java.io.Reader;
import java.io.StringReader;
import org.xml.sax.InputSource;
import org.xml.sax.EntityResolver;

final class NoOpEntityResolver implements EntityResolver
{
    public InputSource resolveEntity(final String publicId, final String systemId) {
        return new InputSource(new StringReader(""));
    }
}
