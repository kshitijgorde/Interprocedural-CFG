// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.parsers;

import org.w3c.dom.DOMImplementation;
import org.xml.sax.ErrorHandler;
import org.xml.sax.EntityResolver;
import java.io.File;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import java.io.InputStream;

public abstract class DocumentBuilder
{
    public Document parse(final InputStream is) throws SAXException, IOException {
        if (is == null) {
            throw new IllegalArgumentException("InputStream cannot be null");
        }
        final InputSource in = new InputSource(is);
        return this.parse(in);
    }
    
    public Document parse(final InputStream is, final String systemId) throws SAXException, IOException {
        if (is == null) {
            throw new IllegalArgumentException("InputStream cannot be null");
        }
        final InputSource in = new InputSource(is);
        in.setSystemId(systemId);
        return this.parse(in);
    }
    
    public Document parse(final String uri) throws SAXException, IOException {
        if (uri == null) {
            throw new IllegalArgumentException("URI cannot be null");
        }
        final InputSource in = new InputSource(uri);
        return this.parse(in);
    }
    
    public Document parse(final File f) throws SAXException, IOException {
        if (f == null) {
            throw new IllegalArgumentException("File cannot be null");
        }
        String uri = "file:" + f.getAbsolutePath();
        if (File.separatorChar == '\\') {
            uri = uri.replace('\\', '/');
        }
        final InputSource in = new InputSource(uri);
        return this.parse(in);
    }
    
    public abstract Document parse(final InputSource p0) throws SAXException, IOException;
    
    public abstract boolean isNamespaceAware();
    
    public abstract boolean isValidating();
    
    public abstract void setEntityResolver(final EntityResolver p0);
    
    public abstract void setErrorHandler(final ErrorHandler p0);
    
    public abstract Document newDocument();
    
    public abstract DOMImplementation getDOMImplementation();
}
