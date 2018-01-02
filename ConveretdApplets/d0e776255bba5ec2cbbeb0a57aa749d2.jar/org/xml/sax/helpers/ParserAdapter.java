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
        final String property = System.getProperty("org.xml.sax.parser");
        try {
            this.setup(ParserFactory.makeParser());
        }
        catch (ClassNotFoundException ex) {
            throw new SAXException("Cannot find SAX1 driver class " + property, ex);
        }
        catch (IllegalAccessException ex2) {
            throw new SAXException("SAX1 driver class " + property + " found but cannot be loaded", ex2);
        }
        catch (InstantiationException ex3) {
            throw new SAXException("SAX1 driver class " + property + " loaded but cannot be instantiated", ex3);
        }
        catch (ClassCastException ex4) {
            throw new SAXException("SAX1 driver class " + property + " does not implement org.xml.sax.Parser");
        }
        catch (NullPointerException ex5) {
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
    
    public void setFeature(final String s, final boolean b) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s.equals("http://xml.org/sax/features/namespaces")) {
            this.checkNotParsing("feature", s);
            this.namespaces = b;
            if (!this.namespaces && !this.prefixes) {
                this.prefixes = true;
            }
        }
        else if (s.equals("http://xml.org/sax/features/namespace-prefixes")) {
            this.checkNotParsing("feature", s);
            this.prefixes = b;
            if (!this.prefixes && !this.namespaces) {
                this.namespaces = true;
            }
        }
        else {
            if (s.equals("http://xml.org/sax/features/validation") || s.equals("http://xml.org/sax/features/external-general-entities") || s.equals("http://xml.org/sax/features/external-parameter-entities")) {
                throw new SAXNotSupportedException("Feature: " + s);
            }
            throw new SAXNotRecognizedException("Feature: " + s);
        }
    }
    
    public boolean getFeature(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s.equals("http://xml.org/sax/features/namespaces")) {
            return this.namespaces;
        }
        if (s.equals("http://xml.org/sax/features/namespace-prefixes")) {
            return this.prefixes;
        }
        if (s.equals("http://xml.org/sax/features/validation") || s.equals("http://xml.org/sax/features/external-general-entities") || s.equals("http://xml.org/sax/features/external-parameter-entities")) {
            throw new SAXNotSupportedException("Feature: " + s);
        }
        throw new SAXNotRecognizedException("Feature: " + s);
    }
    
    public void setProperty(final String s, final Object o) throws SAXNotRecognizedException, SAXNotSupportedException {
        throw new SAXNotRecognizedException("Property: " + s);
    }
    
    public Object getProperty(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        throw new SAXNotRecognizedException("Property: " + s);
    }
    
    public void setEntityResolver(final EntityResolver entityResolver) {
        if (entityResolver == null) {
            throw new NullPointerException("Null entity resolver");
        }
        this.entityResolver = entityResolver;
    }
    
    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }
    
    public void setDTDHandler(final DTDHandler dtdHandler) {
        if (dtdHandler == null) {
            throw new NullPointerException("Null DTD handler");
        }
        this.dtdHandler = dtdHandler;
    }
    
    public DTDHandler getDTDHandler() {
        return this.dtdHandler;
    }
    
    public void setContentHandler(final ContentHandler contentHandler) {
        if (contentHandler == null) {
            throw new NullPointerException("Null content handler");
        }
        this.contentHandler = contentHandler;
    }
    
    public ContentHandler getContentHandler() {
        return this.contentHandler;
    }
    
    public void setErrorHandler(final ErrorHandler errorHandler) {
        if (errorHandler == null) {
            throw new NullPointerException("Null error handler");
        }
        this.errorHandler = errorHandler;
    }
    
    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }
    
    public void parse(final String s) throws IOException, SAXException {
        this.parse(new InputSource(s));
    }
    
    public void parse(final InputSource inputSource) throws IOException, SAXException {
        if (this.parsing) {
            throw new SAXException("Parser is already in use");
        }
        this.setupParser();
        this.parsing = true;
        try {
            this.parser.parse(inputSource);
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
    
    public void startElement(final String s, final AttributeList attributeList) throws SAXException {
        if (!this.namespaces) {
            if (this.contentHandler != null) {
                this.attAdapter.setAttributeList(attributeList);
                this.contentHandler.startElement("", "", s.intern(), this.attAdapter);
            }
            return;
        }
        this.nsSupport.pushContext();
        boolean b = false;
        this.atts.clear();
        for (int length = attributeList.getLength(), i = 0; i < length; ++i) {
            final String name = attributeList.getName(i);
            final String type = attributeList.getType(i);
            final String value = attributeList.getValue(i);
            if (name.startsWith("xmlns")) {
                final int index = name.indexOf(58);
                String substring;
                if (index == -1) {
                    substring = "";
                }
                else {
                    substring = name.substring(index + 1);
                }
                if (!this.nsSupport.declarePrefix(substring, value)) {
                    this.reportError("Illegal Namespace prefix: " + substring);
                }
                if (this.contentHandler != null) {
                    this.contentHandler.startPrefixMapping(substring, value);
                }
                if (this.prefixes) {
                    this.atts.addAttribute("", "", name.intern(), type, value);
                }
                b = true;
            }
            else {
                final String[] processName = this.processName(name, true);
                this.atts.addAttribute(processName[0], processName[1], processName[2], type, value);
            }
        }
        if (b) {
            for (int length2 = this.atts.getLength(), j = 0; j < length2; ++j) {
                final String qName = this.atts.getQName(j);
                if (!qName.startsWith("xmlns")) {
                    final String[] processName2 = this.processName(qName, true);
                    this.atts.setURI(j, processName2[0]);
                    this.atts.setLocalName(j, processName2[1]);
                }
            }
        }
        if (this.contentHandler != null) {
            final String[] processName3 = this.processName(s, false);
            this.contentHandler.startElement(processName3[0], processName3[1], processName3[2], this.atts);
        }
    }
    
    public void endElement(final String s) throws SAXException {
        if (!this.namespaces) {
            if (this.contentHandler != null) {
                this.contentHandler.endElement("", "", s.intern());
            }
            return;
        }
        final String[] processName = this.processName(s, false);
        if (this.contentHandler != null) {
            this.contentHandler.endElement(processName[0], processName[1], processName[2]);
            final Enumeration declaredPrefixes = this.nsSupport.getDeclaredPrefixes();
            while (declaredPrefixes.hasMoreElements()) {
                this.contentHandler.endPrefixMapping(declaredPrefixes.nextElement());
            }
        }
        this.nsSupport.popContext();
    }
    
    public void characters(final char[] array, final int n, final int n2) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.characters(array, n, n2);
        }
    }
    
    public void ignorableWhitespace(final char[] array, final int n, final int n2) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.ignorableWhitespace(array, n, n2);
        }
    }
    
    public void processingInstruction(final String s, final String s2) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.processingInstruction(s, s2);
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
    
    private String[] processName(final String s, final boolean b) throws SAXException {
        String[] processName = this.nsSupport.processName(s, this.nameParts, b);
        if (processName == null) {
            processName = new String[] { null, null, s.intern() };
            this.reportError("Undeclared prefix: " + s);
        }
        return processName;
    }
    
    void reportError(final String s) throws SAXException {
        if (this.errorHandler == null) {
            return;
        }
        SAXParseException ex;
        if (this.locator != null) {
            ex = new SAXParseException(s, this.locator);
        }
        else {
            ex = new SAXParseException(s, null, null, -1, -1);
        }
        this.errorHandler.error(ex);
    }
    
    private void checkNotParsing(final String s, final String s2) throws SAXNotSupportedException {
        if (this.parsing) {
            throw new SAXNotSupportedException("Cannot change " + s + ' ' + s2 + " while parsing");
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
        
        public String getURI(final int n) {
            return "";
        }
        
        public String getLocalName(final int n) {
            return "";
        }
        
        public String getQName(final int n) {
            return this.qAtts.getName(n).intern();
        }
        
        public String getType(final int n) {
            return this.qAtts.getType(n).intern();
        }
        
        public String getValue(final int n) {
            return this.qAtts.getValue(n);
        }
        
        public int getIndex(final String s, final String s2) {
            return -1;
        }
        
        public int getIndex(final String s) {
            for (int length = ParserAdapter.this.atts.getLength(), i = 0; i < length; ++i) {
                if (this.qAtts.getName(i).equals(s)) {
                    return i;
                }
            }
            return -1;
        }
        
        public String getType(final String s, final String s2) {
            return null;
        }
        
        public String getType(final String s) {
            return this.qAtts.getType(s).intern();
        }
        
        public String getValue(final String s, final String s2) {
            return null;
        }
        
        public String getValue(final String s) {
            return this.qAtts.getValue(s);
        }
    }
}
