// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp.validation;

import org.apache.xerces.util.URI;
import org.apache.xerces.impl.XMLEntityManager;
import java.io.InputStream;
import org.w3c.dom.ls.LSInput;
import java.io.Reader;
import java.io.StringReader;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ext.EntityResolver2;

final class LSResourceResolverAdapter implements EntityResolver2
{
    private static final String XML_TYPE = "http://www.w3.org/TR/REC-xml";
    protected LSResourceResolver fEntityResolver;
    
    public LSResourceResolverAdapter() {
    }
    
    public LSResourceResolverAdapter(final LSResourceResolver entityResolver) {
        this.setEntityResolver(entityResolver);
    }
    
    public void setEntityResolver(final LSResourceResolver fEntityResolver) {
        this.fEntityResolver = fEntityResolver;
    }
    
    public LSResourceResolver getEntityResolver() {
        return this.fEntityResolver;
    }
    
    public InputSource getExternalSubset(final String s, final String s2) throws SAXException, IOException {
        return null;
    }
    
    public InputSource resolveEntity(final String s, final String s2, final String s3, final String s4) throws SAXException, IOException {
        if (this.fEntityResolver != null) {
            final LSInput resolveResource = this.fEntityResolver.resolveResource("http://www.w3.org/TR/REC-xml", null, s2, s4, s3);
            if (resolveResource != null) {
                final String publicId = resolveResource.getPublicId();
                resolveResource.getSystemId();
                final String baseURI = resolveResource.getBaseURI();
                final Reader characterStream = resolveResource.getCharacterStream();
                final InputStream byteStream = resolveResource.getByteStream();
                final String stringData = resolveResource.getStringData();
                final String encoding = resolveResource.getEncoding();
                final InputSource inputSource = new InputSource();
                inputSource.setPublicId(publicId);
                inputSource.setSystemId((baseURI != null) ? this.resolveSystemId(s4, baseURI) : s4);
                if (characterStream != null) {
                    inputSource.setCharacterStream(characterStream);
                }
                else if (byteStream != null) {
                    inputSource.setByteStream(byteStream);
                }
                else if (stringData != null && stringData.length() != 0) {
                    inputSource.setCharacterStream(new StringReader(stringData));
                }
                inputSource.setEncoding(encoding);
                return inputSource;
            }
        }
        return null;
    }
    
    public InputSource resolveEntity(final String s, final String s2) throws SAXException, IOException {
        return this.resolveEntity(null, s, null, s2);
    }
    
    private String resolveSystemId(final String s, final String s2) {
        try {
            return XMLEntityManager.expandSystemId(s, s2, false);
        }
        catch (URI.MalformedURIException ex) {
            return s;
        }
    }
}
