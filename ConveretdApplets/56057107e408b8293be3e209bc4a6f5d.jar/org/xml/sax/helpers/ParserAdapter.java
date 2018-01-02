// 
// Decompiled by Procyon v0.5.30
// 

package org.xml.sax.helpers;

import org.xml.sax.SAXParseException;
import java.util.Enumeration;
import org.xml.sax.Attributes;
import org.xml.sax.AttributeList;
import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.Locator;
import org.xml.sax.Parser;
import org.xml.sax.DocumentHandler;
import org.xml.sax.XMLReader;

public class ParserAdapter implements XMLReader, DocumentHandler
{
    private static final String FEATURES = "http://xml.org/sax/features/";
    private static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    private static final String NAMESPACE_PREFIXES = "http://xml.org/sax/features/namespace-prefixes";
    private static final String VALIDATION = "http://xml.org/sax/features/validation";
    private static final String EXTERNAL_GENERAL = "http://xml.org/sax/features/external-general-entities";
    private static final String EXTERNAL_PARAMETER = "http://xml.org/sax/features/external-parameter-entities";
    private NamespaceSupport nsSupport;
    private AttributeListAdapter attAdapter;
    private boolean parsing;
    private String[] nameParts;
    private Parser parser;
    private AttributesImpl atts;
    private boolean namespaces;
    private boolean prefixes;
    Locator locator;
    EntityResolver entityResolver;
    DTDHandler dtdHandler;
    ContentHandler contentHandler;
    ErrorHandler errorHandler;
    
    public ParserAdapter() throws SAXException {
        this.parsing = false;
        this.nameParts = new String[3];
        this.parser = null;
        this.atts = null;
        this.namespaces = true;
        this.prefixes = false;
        this.entityResolver = null;
        this.dtdHandler = null;
        this.contentHandler = null;
        this.errorHandler = null;
        final String driver = System.getProperty("org.xml.sax.parser");
        try {
            this.setup(ParserFactory.makeParser());
        }
        catch (ClassNotFoundException e1) {
            throw new SAXException("Cannot find SAX1 driver class " + driver, e1);
        }
        catch (IllegalAccessException e2) {
            throw new SAXException("SAX1 driver class " + driver + " found but cannot be loaded", e2);
        }
        catch (InstantiationException e3) {
            throw new SAXException("SAX1 driver class " + driver + " loaded but cannot be instantiated", e3);
        }
        catch (ClassCastException e4) {
            throw new SAXException("SAX1 driver class " + driver + " does not implement org.xml.sax.Parser");
        }
        catch (NullPointerException e5) {
            throw new SAXException("System property org.xml.sax.parser not specified");
        }
    }
    
    public ParserAdapter(final Parser parser) {
        this.parsing = false;
        this.nameParts = new String[3];
        this.parser = null;
        this.atts = null;
        this.namespaces = true;
        this.prefixes = false;
        this.entityResolver = null;
        this.dtdHandler = null;
        this.contentHandler = null;
        this.errorHandler = null;
        this.setup(parser);
    }
    
    private void setup(final Parser parser) {
        if (parser == null) {
            throw new NullPointerException("Parser argument must not be null");
        }
        this.parser = parser;
        this.atts = new AttributesImpl();
        this.nsSupport = new NamespaceSupport();
        this.attAdapter = new AttributeListAdapter();
    }
    
    public void setFeature(final String name, final boolean state) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name.equals("http://xml.org/sax/features/namespaces")) {
            this.checkNotParsing("feature", name);
            this.namespaces = state;
            if (!this.namespaces && !this.prefixes) {
                this.prefixes = true;
            }
        }
        else if (name.equals("http://xml.org/sax/features/namespace-prefixes")) {
            this.checkNotParsing("feature", name);
            this.prefixes = state;
            if (!this.prefixes && !this.namespaces) {
                this.namespaces = true;
            }
        }
        else {
            if (name.equals("http://xml.org/sax/features/validation") || name.equals("http://xml.org/sax/features/external-general-entities") || name.equals("http://xml.org/sax/features/external-parameter-entities")) {
                throw new SAXNotSupportedException("Feature: " + name);
            }
            throw new SAXNotRecognizedException("Feature: " + name);
        }
    }
    
    public boolean getFeature(final String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name.equals("http://xml.org/sax/features/namespaces")) {
            return this.namespaces;
        }
        if (name.equals("http://xml.org/sax/features/namespace-prefixes")) {
            return this.prefixes;
        }
        if (name.equals("http://xml.org/sax/features/validation") || name.equals("http://xml.org/sax/features/external-general-entities") || name.equals("http://xml.org/sax/features/external-parameter-entities")) {
            throw new SAXNotSupportedException("Feature: " + name);
        }
        throw new SAXNotRecognizedException("Feature: " + name);
    }
    
    public void setProperty(final String name, final Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
        throw new SAXNotRecognizedException("Property: " + name);
    }
    
    public Object getProperty(final String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        throw new SAXNotRecognizedException("Property: " + name);
    }
    
    public void setEntityResolver(final EntityResolver resolver) {
        if (resolver == null) {
            throw new NullPointerException("Null entity resolver");
        }
        this.entityResolver = resolver;
    }
    
    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }
    
    public void setDTDHandler(final DTDHandler handler) {
        if (handler == null) {
            throw new NullPointerException("Null DTD handler");
        }
        this.dtdHandler = handler;
    }
    
    public DTDHandler getDTDHandler() {
        return this.dtdHandler;
    }
    
    public void setContentHandler(final ContentHandler handler) {
        if (handler == null) {
            throw new NullPointerException("Null content handler");
        }
        this.contentHandler = handler;
    }
    
    public ContentHandler getContentHandler() {
        return this.contentHandler;
    }
    
    public void setErrorHandler(final ErrorHandler handler) {
        if (handler == null) {
            throw new NullPointerException("Null error handler");
        }
        this.errorHandler = handler;
    }
    
    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }
    
    public void parse(final String systemId) throws IOException, SAXException {
        this.parse(new InputSource(systemId));
    }
    
    public void parse(final InputSource input) throws IOException, SAXException {
        if (this.parsing) {
            throw new SAXException("Parser is already in use");
        }
        this.setupParser();
        this.parsing = true;
        try {
            this.parser.parse(input);
        }
        finally {
            this.parsing = false;
        }
        this.parsing = false;
    }
    
    public void setDocumentLocator(final Locator locator) {
        this.locator = locator;
        if (this.contentHandler != null) {
            this.contentHandler.setDocumentLocator(locator);
        }
    }
    
    public void startDocument() throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.startDocument();
        }
    }
    
    public void endDocument() throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.endDocument();
        }
    }
    
    public void startElement(final String qName, final AttributeList qAtts) throws SAXException {
        if (!this.namespaces) {
            if (this.contentHandler != null) {
                this.attAdapter.setAttributeList(qAtts);
                this.contentHandler.startElement("", "", qName.intern(), this.attAdapter);
            }
            return;
        }
        this.nsSupport.pushContext();
        boolean seenDecl = false;
        this.atts.clear();
        for (int length = qAtts.getLength(), i = 0; i < length; ++i) {
            final String attQName = qAtts.getName(i);
            final String type = qAtts.getType(i);
            final String value = qAtts.getValue(i);
            if (attQName.startsWith("xmlns")) {
                final int n = attQName.indexOf(58);
                String prefix;
                if (n == -1) {
                    prefix = "";
                }
                else {
                    prefix = attQName.substring(n + 1);
                }
                if (!this.nsSupport.declarePrefix(prefix, value)) {
                    this.reportError("Illegal Namespace prefix: " + prefix);
                }
                if (this.contentHandler != null) {
                    this.contentHandler.startPrefixMapping(prefix, value);
                }
                if (this.prefixes) {
                    this.atts.addAttribute("", "", attQName.intern(), type, value);
                }
                seenDecl = true;
            }
            else {
                final String[] attName = this.processName(attQName, true);
                this.atts.addAttribute(attName[0], attName[1], attName[2], type, value);
            }
        }
        if (seenDecl) {
            for (int length = this.atts.getLength(), j = 0; j < length; ++j) {
                final String attQName2 = this.atts.getQName(j);
                if (!attQName2.startsWith("xmlns")) {
                    final String[] attName2 = this.processName(attQName2, true);
                    this.atts.setURI(j, attName2[0]);
                    this.atts.setLocalName(j, attName2[1]);
                }
            }
        }
        if (this.contentHandler != null) {
            final String[] name = this.processName(qName, false);
            this.contentHandler.startElement(name[0], name[1], name[2], this.atts);
        }
    }
    
    public void endElement(final String qName) throws SAXException {
        if (!this.namespaces) {
            if (this.contentHandler != null) {
                this.contentHandler.endElement("", "", qName.intern());
            }
            return;
        }
        final String[] names = this.processName(qName, false);
        if (this.contentHandler != null) {
            this.contentHandler.endElement(names[0], names[1], names[2]);
            final Enumeration prefixes = this.nsSupport.getDeclaredPrefixes();
            while (prefixes.hasMoreElements()) {
                final String prefix = prefixes.nextElement();
                this.contentHandler.endPrefixMapping(prefix);
            }
        }
        this.nsSupport.popContext();
    }
    
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.characters(ch, start, length);
        }
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.ignorableWhitespace(ch, start, length);
        }
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.processingInstruction(target, data);
        }
    }
    
    private void setupParser() {
        this.nsSupport.reset();
        if (this.entityResolver != null) {
            this.parser.setEntityResolver(this.entityResolver);
        }
        if (this.dtdHandler != null) {
            this.parser.setDTDHandler(this.dtdHandler);
        }
        if (this.errorHandler != null) {
            this.parser.setErrorHandler(this.errorHandler);
        }
        this.parser.setDocumentHandler(this);
        this.locator = null;
    }
    
    private String[] processName(final String qName, final boolean isAttribute) throws SAXException {
        String[] parts = this.nsSupport.processName(qName, this.nameParts, isAttribute);
        if (parts == null) {
            parts = new String[] { null, null, qName.intern() };
            this.reportError("Undeclared prefix: " + qName);
        }
        return parts;
    }
    
    void reportError(final String message) throws SAXException {
        if (this.errorHandler == null) {
            return;
        }
        SAXParseException e;
        if (this.locator != null) {
            e = new SAXParseException(message, this.locator);
        }
        else {
            e = new SAXParseException(message, null, null, -1, -1);
        }
        this.errorHandler.error(e);
    }
    
    private void checkNotParsing(final String type, final String name) throws SAXNotSupportedException {
        if (this.parsing) {
            throw new SAXNotSupportedException("Cannot change " + type + ' ' + name + " while parsing");
        }
    }
    
    final class AttributeListAdapter implements Attributes
    {
        private AttributeList qAtts;
        
        void setAttributeList(final AttributeList qAtts) {
            this.qAtts = qAtts;
        }
        
        public int getLength() {
            return this.qAtts.getLength();
        }
        
        public String getURI(final int i) {
            return "";
        }
        
        public String getLocalName(final int i) {
            return "";
        }
        
        public String getQName(final int i) {
            return this.qAtts.getName(i).intern();
        }
        
        public String getType(final int i) {
            return this.qAtts.getType(i).intern();
        }
        
        public String getValue(final int i) {
            return this.qAtts.getValue(i);
        }
        
        public int getIndex(final String uri, final String localName) {
            return -1;
        }
        
        public int getIndex(final String qName) {
            for (int max = ParserAdapter.this.atts.getLength(), i = 0; i < max; ++i) {
                if (this.qAtts.getName(i).equals(qName)) {
                    return i;
                }
            }
            return -1;
        }
        
        public String getType(final String uri, final String localName) {
            return null;
        }
        
        public String getType(final String qName) {
            return this.qAtts.getType(qName).intern();
        }
        
        public String getValue(final String uri, final String localName) {
            return null;
        }
        
        public String getValue(final String qName) {
            return this.qAtts.getValue(qName);
        }
    }
}
