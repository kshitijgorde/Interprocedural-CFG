// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.readers;

import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import org.xml.sax.AttributeList;
import org.xml.sax.Locator;
import org.xml.sax.EntityResolver;
import org.xml.sax.DocumentHandler;
import org.apache.xerces.parsers.SAXParser;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import java.util.Vector;
import java.util.Hashtable;

public class XCatalog extends XMLCatalogHandler
{
    public static final String XCATALOG_DTD_PUBLICID = "-//DTD XCatalog//EN";
    static final String DTD = "xcatalog.dtd";
    static final String XCATALOG = "XCatalog";
    static final String MAP = "Map";
    static final String PUBLICID = "PublicID";
    static final String HREF = "HRef";
    static final String DELEGATE = "Delegate";
    static final String EXTEND = "Extend";
    static final String BASE = "Base";
    static final String REMAP = "Remap";
    static final String SYSTEMID = "SystemID";
    private static final boolean DEBUG = false;
    private Hashtable delegate;
    private Vector delegateOrder;
    
    public XCatalog() {
        this.delegate = new Hashtable();
        this.delegateOrder = new Vector();
    }
    
    public void loadCatalog(final InputSource inputSource) throws SAXException, IOException {
        new Parser(inputSource);
    }
    
    public InputSource resolveEntity(final String s, final String s2) throws SAXException, IOException {
        if (s != null) {
            final String publicMapping = this.getPublicMapping(s);
            if (publicMapping != null) {
                InputSource resolveEntity = this.resolveEntity(null, publicMapping);
                if (resolveEntity == null) {
                    resolveEntity = new InputSource(publicMapping);
                }
                resolveEntity.setPublicId(s);
                return resolveEntity;
            }
            final Enumeration delegateCatalogKeys = this.getDelegateCatalogKeys();
            while (delegateCatalogKeys.hasMoreElements()) {
                final String s3 = delegateCatalogKeys.nextElement();
                if (s.startsWith(s3)) {
                    final InputSource resolveEntity2 = this.getDelegateCatalog(s3).resolveEntity(s, s2);
                    if (resolveEntity2 != null) {
                        return resolveEntity2;
                    }
                    continue;
                }
            }
        }
        final String systemMapping = this.getSystemMapping(s2);
        if (systemMapping != null) {
            final InputSource inputSource = new InputSource(systemMapping);
            inputSource.setPublicId(s);
            return inputSource;
        }
        return null;
    }
    
    public void addDelegateCatalog(final String s, final XCatalog xCatalog) {
        synchronized (this.delegate) {
            if (!this.delegate.containsKey(s)) {
                final int size = this.delegateOrder.size();
                boolean b = false;
                for (int i = 0; i < size; ++i) {
                    final String s2 = this.delegateOrder.elementAt(i);
                    if (s.startsWith(s2) || s.compareTo(s2) < 0) {
                        this.delegateOrder.insertElementAt(s, i);
                        b = true;
                        break;
                    }
                }
                if (!b) {
                    this.delegateOrder.addElement(s);
                }
            }
            this.delegate.put(s, xCatalog);
        }
    }
    
    public void removeDelegateCatalog(final String s) {
        synchronized (this.delegate) {
            this.delegate.remove(s);
            this.delegateOrder.removeElement(s);
        }
    }
    
    public Enumeration getDelegateCatalogKeys() {
        return this.delegateOrder.elements();
    }
    
    public XCatalog getDelegateCatalog(final String s) {
        return this.delegate.get(s);
    }
    
    boolean isURL(final String s) {
        try {
            new URL(s);
            return true;
        }
        catch (MalformedURLException ex) {
            return false;
        }
    }
    
    class Parser extends SAXParser implements DocumentHandler
    {
        private String base;
        
        public Parser(final InputSource inputSource) throws SAXException, IOException {
            this.setEntityResolver(new Resolver());
            this.setDocumentHandler(this);
            this.setBase(inputSource.getSystemId());
            this.parse(inputSource);
        }
        
        protected void setBase(String base) throws SAXException {
            if (base == null) {
                base = "";
            }
            base = super.fEntityHandler.expandSystemId(base);
            final int lastIndex = base.lastIndexOf(47);
            if (lastIndex != -1) {
                base = base.substring(0, lastIndex + 1);
            }
            this.base = base;
        }
        
        public void processingInstruction(final String s, final String s2) {
        }
        
        public void setDocumentLocator(final Locator locator) {
        }
        
        public void startDocument() {
        }
        
        public void endElement(final String s) {
        }
        
        public void endDocument() {
        }
        
        public void characters(final char[] array, final int n, final int n2) {
        }
        
        public void ignorableWhitespace(final char[] array, final int n, final int n2) {
        }
        
        public void startElement(final String s, final AttributeList list) throws SAXException {
            try {
                if (s.equals("XCatalog")) {
                    return;
                }
                if (s.equals("Map")) {
                    final String value = list.getValue("PublicID");
                    String s2 = list.getValue("HRef");
                    if (!XCatalog.this.isURL(s2)) {
                        s2 = this.base + s2;
                    }
                    XCatalog.this.addPublicMapping(value, s2);
                }
                else if (s.equals("Delegate")) {
                    final String value2 = list.getValue("PublicID");
                    String s3 = list.getValue("HRef");
                    if (!XCatalog.this.isURL(s3)) {
                        s3 = this.base + s3;
                    }
                    final String expandSystemId = super.fEntityHandler.expandSystemId(s3);
                    final XCatalog xCatalog = new XCatalog();
                    xCatalog.loadCatalog(new InputSource(expandSystemId));
                    XCatalog.this.addDelegateCatalog(value2, xCatalog);
                }
                else if (s.equals("Extend")) {
                    String s4 = list.getValue("HRef");
                    if (!XCatalog.this.isURL(s4)) {
                        s4 = this.base + s4;
                    }
                    XCatalog.this.loadCatalog(new InputSource(super.fEntityHandler.expandSystemId(s4)));
                }
                else if (s.equals("Base")) {
                    this.setBase(list.getValue("HRef"));
                }
                else if (s.equals("Remap")) {
                    final String value3 = list.getValue("SystemID");
                    String s5 = list.getValue("HRef");
                    if (!XCatalog.this.isURL(s5)) {
                        s5 = this.base + s5;
                    }
                    XCatalog.this.addSystemMapping(value3, s5);
                }
            }
            catch (Exception ex) {
                throw new SAXException(ex);
            }
        }
        
        class Resolver implements EntityResolver
        {
            public InputSource resolveEntity(final String publicId, final String s) throws SAXException, IOException {
                if (publicId != null && publicId.equals("-//DTD XCatalog//EN")) {
                    final InputSource inputSource = new InputSource();
                    inputSource.setPublicId(publicId);
                    final InputStream resourceAsStream = this.getClass().getResourceAsStream("xcatalog.dtd");
                    inputSource.setByteStream(resourceAsStream);
                    inputSource.setCharacterStream(new InputStreamReader(resourceAsStream));
                    return inputSource;
                }
                return null;
            }
        }
    }
}
