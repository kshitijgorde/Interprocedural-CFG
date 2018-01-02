// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.parsers;

import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.XMLReader;
import org.xml.sax.ContentHandler;
import org.xml.sax.Parser;
import org.xml.sax.DTDHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.DocumentHandler;
import java.io.File;
import org.xml.sax.helpers.DefaultHandler;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import org.xml.sax.HandlerBase;
import java.io.InputStream;

public abstract class SAXParser
{
    public void parse(final InputStream is, final HandlerBase hb) throws SAXException, IOException {
        if (is == null) {
            throw new IllegalArgumentException("InputStream cannot be null");
        }
        final InputSource input = new InputSource(is);
        this.parse(input, hb);
    }
    
    public void parse(final InputStream is, final HandlerBase hb, final String systemId) throws SAXException, IOException {
        if (is == null) {
            throw new IllegalArgumentException("InputStream cannot be null");
        }
        final InputSource input = new InputSource(is);
        input.setSystemId(systemId);
        this.parse(input, hb);
    }
    
    public void parse(final InputStream is, final DefaultHandler dh) throws SAXException, IOException {
        if (is == null) {
            throw new IllegalArgumentException("InputStream cannot be null");
        }
        final InputSource input = new InputSource(is);
        this.parse(input, dh);
    }
    
    public void parse(final InputStream is, final DefaultHandler dh, final String systemId) throws SAXException, IOException {
        if (is == null) {
            throw new IllegalArgumentException("InputStream cannot be null");
        }
        final InputSource input = new InputSource(is);
        input.setSystemId(systemId);
        this.parse(input, dh);
    }
    
    public void parse(final String uri, final HandlerBase hb) throws SAXException, IOException {
        if (uri == null) {
            throw new IllegalArgumentException("uri cannot be null");
        }
        final InputSource input = new InputSource(uri);
        this.parse(input, hb);
    }
    
    public void parse(final String uri, final DefaultHandler dh) throws SAXException, IOException {
        if (uri == null) {
            throw new IllegalArgumentException("uri cannot be null");
        }
        final InputSource input = new InputSource(uri);
        this.parse(input, dh);
    }
    
    public void parse(final File f, final HandlerBase hb) throws SAXException, IOException {
        if (f == null) {
            throw new IllegalArgumentException("File cannot be null");
        }
        String uri = "file:" + f.getAbsolutePath();
        if (File.separatorChar == '\\') {
            uri = uri.replace('\\', '/');
        }
        final InputSource input = new InputSource(uri);
        this.parse(input, hb);
    }
    
    public void parse(final File f, final DefaultHandler dh) throws SAXException, IOException {
        if (f == null) {
            throw new IllegalArgumentException("File cannot be null");
        }
        String uri = "file:" + f.getAbsolutePath();
        if (File.separatorChar == '\\') {
            uri = uri.replace('\\', '/');
        }
        final InputSource input = new InputSource(uri);
        this.parse(input, dh);
    }
    
    public void parse(final InputSource is, final HandlerBase hb) throws SAXException, IOException {
        if (is == null) {
            throw new IllegalArgumentException("InputSource cannot be null");
        }
        final Parser parser = this.getParser();
        if (hb != null) {
            parser.setDocumentHandler(hb);
            parser.setEntityResolver(hb);
            parser.setErrorHandler(hb);
            parser.setDTDHandler(hb);
        }
        parser.parse(is);
    }
    
    public void parse(final InputSource is, final DefaultHandler dh) throws SAXException, IOException {
        if (is == null) {
            throw new IllegalArgumentException("InputSource cannot be null");
        }
        final XMLReader reader = this.getXMLReader();
        if (dh != null) {
            reader.setContentHandler(dh);
            reader.setEntityResolver(dh);
            reader.setErrorHandler(dh);
            reader.setDTDHandler(dh);
        }
        reader.parse(is);
    }
    
    public abstract Parser getParser() throws SAXException;
    
    public abstract XMLReader getXMLReader() throws SAXException;
    
    public abstract boolean isNamespaceAware();
    
    public abstract boolean isValidating();
    
    public abstract void setProperty(final String p0, final Object p1) throws SAXNotRecognizedException, SAXNotSupportedException;
    
    public abstract Object getProperty(final String p0) throws SAXNotRecognizedException, SAXNotSupportedException;
}
