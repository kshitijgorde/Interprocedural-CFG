// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.util;

import java.io.IOException;
import java.io.Reader;
import java.io.InputStream;
import org.w3c.dom.ls.DOMInputSource;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.w3c.dom.ls.DOMEntityResolver;
import org.apache.xerces.xni.parser.XMLEntityResolver;

public class DOMEntityResolverWrapper implements XMLEntityResolver
{
    protected DOMEntityResolver fEntityResolver;
    
    public DOMEntityResolverWrapper() {
    }
    
    public DOMEntityResolverWrapper(final DOMEntityResolver entityResolver) {
        this.setEntityResolver(entityResolver);
    }
    
    public void setEntityResolver(final DOMEntityResolver entityResolver) {
        this.fEntityResolver = entityResolver;
    }
    
    public DOMEntityResolver getEntityResolver() {
        return this.fEntityResolver;
    }
    
    public XMLInputSource resolveEntity(final XMLResourceIdentifier resourceIdentifier) throws XNIException, IOException {
        if (this.fEntityResolver != null) {
            try {
                final DOMInputSource inputSource = (resourceIdentifier == null) ? this.fEntityResolver.resolveEntity((String)null, (String)null, (String)null) : this.fEntityResolver.resolveEntity(resourceIdentifier.getPublicId(), resourceIdentifier.getLiteralSystemId(), resourceIdentifier.getBaseSystemId());
                if (inputSource != null) {
                    final String publicId = inputSource.getPublicId();
                    final String systemId = inputSource.getSystemId();
                    final String baseSystemId = inputSource.getBaseURI();
                    final InputStream byteStream = inputSource.getByteStream();
                    final Reader charStream = inputSource.getCharacterStream();
                    final String encoding = inputSource.getEncoding();
                    final XMLInputSource xmlInputSource = new XMLInputSource(publicId, systemId, baseSystemId);
                    xmlInputSource.setByteStream(byteStream);
                    xmlInputSource.setCharacterStream(charStream);
                    xmlInputSource.setEncoding(encoding);
                    return xmlInputSource;
                }
            }
            catch (Exception e) {
                throw new XNIException(e);
            }
        }
        return null;
    }
}
