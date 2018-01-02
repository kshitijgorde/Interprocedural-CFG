// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graphApi.io;

import org.xml.sax.SAXException;
import org.xml.sax.ContentHandler;

public class DefaultLexicalHandler implements LexicalHandler
{
    SAXReader reader;
    ContentHandlerFactory factory;
    
    public DefaultLexicalHandler() {
        this(null);
    }
    
    public DefaultLexicalHandler(final SAXReader saxReader) {
        this(saxReader, null);
    }
    
    public DefaultLexicalHandler(final SAXReader reader, final ContentHandlerFactory factory) {
        this.reader = reader;
        this.factory = factory;
    }
    
    public void setSAXReader(final SAXReader reader) {
        this.reader = reader;
    }
    
    public void startDTD(final String s, final String s2, final String s3) throws SAXException {
        ContentHandlerFactory contentHandlerFactory = this.factory;
        if (contentHandlerFactory == null) {
            contentHandlerFactory = this.reader.getContentHandlerFactory();
        }
        if (contentHandlerFactory == null) {
            contentHandlerFactory = new ContentHandlerFactory();
        }
        final hypergraph.graphApi.io.ContentHandler contentHandler = contentHandlerFactory.createContentHandler(s);
        contentHandler.setReader(this.reader);
        this.reader.getReader().setContentHandler(contentHandler);
    }
    
    public void endDTD() throws SAXException {
    }
    
    public void startEntity(final String s) throws SAXException {
    }
    
    public void endEntity(final String s) throws SAXException {
    }
    
    public void startCDATA() throws SAXException {
    }
    
    public void endCDATA() throws SAXException {
    }
    
    public void comment(final char[] array, final int n, final int n2) throws SAXException {
    }
}
