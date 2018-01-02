// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.parser;

import org.xml.sax.SAXException;
import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.XMLReader;

public class XMLReaderImpl implements XMLReader
{
    private static final String FEATURES = "http://xml.org/sax/features/";
    private static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    private static final String NAMESPACE_PREFIXES = "http://xml.org/sax/features/namespace-prefixes";
    private static final String STRING_INTERNING = "http://xml.org/sax/features/string-interning";
    private static final String VALIDATION = "http://xml.org/sax/features/validation";
    private static final String EXTERNAL_GENERAL = "http://xml.org/sax/features/external-general-entities";
    private static final String EXTERNAL_PARAMETER = "http://xml.org/sax/features/external-parameter-entities";
    private static final String LEXICAL_PARAMETER_ENTITIES = "http://xml.org/sax/features/lexical-handler/parameter-entities";
    private static final String PROPERTIES = "http://xml.org/sax/properties/";
    private static final String LEXICAL_HANDLER = "http://xml.org/sax/properties/lexical-handler";
    private static final String DECLARATION_HANDLER = "http://xml.org/sax/properties/declaration-handler";
    private boolean namespaces;
    private boolean prefixes;
    private boolean validation;
    private LexicalHandler lexicalHandler;
    private DeclHandler declHandler;
    private ContentHandler contentHandler;
    private DTDHandler dtdHandler;
    private ErrorHandler errorHandler;
    private EntityResolver entityResolver;
    private Parser2 parser;
    private boolean parsing;
    
    public XMLReaderImpl() {
        this.namespaces = true;
        this.prefixes = false;
        this.validation = false;
    }
    
    public boolean getFeature(final String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name.equals("http://xml.org/sax/features/namespaces")) {
            return this.namespaces;
        }
        if (name.equals("http://xml.org/sax/features/namespace-prefixes")) {
            return this.prefixes;
        }
        if (name.equals("http://xml.org/sax/features/validation")) {
            return this.validation;
        }
        if (name.equals("http://xml.org/sax/features/string-interning") || name.equals("http://xml.org/sax/features/external-general-entities") || name.equals("http://xml.org/sax/features/external-parameter-entities")) {
            return true;
        }
        if (name.equals("http://xml.org/sax/features/lexical-handler/parameter-entities")) {
            return false;
        }
        throw new SAXNotRecognizedException("Feature: " + name);
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
        else if (name.equals("http://xml.org/sax/features/validation")) {
            this.checkNotParsing("feature", name);
            if (this.validation != state) {
                this.parser = null;
            }
            this.validation = state;
        }
        else if (name.equals("http://xml.org/sax/features/string-interning")) {
            if (!state) {
                throw new SAXNotSupportedException("Feature: " + name + " State: false");
            }
        }
        else {
            if (name.equals("http://xml.org/sax/features/external-general-entities") || name.equals("http://xml.org/sax/features/external-parameter-entities") || name.equals("http://xml.org/sax/features/lexical-handler/parameter-entities")) {
                throw new SAXNotSupportedException("Feature: " + name);
            }
            throw new SAXNotRecognizedException("Feature: " + name);
        }
    }
    
    public Object getProperty(final String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name.equals("http://xml.org/sax/properties/lexical-handler")) {
            return this.lexicalHandler;
        }
        if (name.equals("http://xml.org/sax/properties/declaration-handler")) {
            return this.declHandler;
        }
        throw new SAXNotRecognizedException("Property: " + name);
    }
    
    public void setProperty(final String name, final Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
        final String detail = "Property: " + name;
        if (name.equals("http://xml.org/sax/properties/lexical-handler")) {
            if (!(value instanceof LexicalHandler)) {
                throw new SAXNotSupportedException(detail);
            }
            this.lexicalHandler = (LexicalHandler)value;
        }
        else {
            if (!name.equals("http://xml.org/sax/properties/declaration-handler")) {
                throw new SAXNotRecognizedException("Property: " + name);
            }
            if (!(value instanceof DeclHandler)) {
                throw new SAXNotSupportedException(detail);
            }
            this.declHandler = (DeclHandler)value;
        }
    }
    
    public void setEntityResolver(final EntityResolver resolver) {
        if (resolver == null) {
            throw new NullPointerException("Null entity resolver");
        }
        this.entityResolver = resolver;
        if (this.parser != null) {
            this.parser.setEntityResolver(resolver);
        }
    }
    
    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }
    
    public void setDTDHandler(final DTDHandler handler) {
        if (handler == null) {
            throw new NullPointerException("Null DTD handler");
        }
        this.dtdHandler = handler;
        if (this.parser != null) {
            this.parser.setDTDHandler(this.dtdHandler);
        }
    }
    
    public DTDHandler getDTDHandler() {
        return this.dtdHandler;
    }
    
    public void setContentHandler(final ContentHandler handler) {
        if (handler == null) {
            throw new NullPointerException("Null content handler");
        }
        this.contentHandler = handler;
        if (this.parser != null) {
            this.parser.setContentHandler(handler);
        }
    }
    
    public ContentHandler getContentHandler() {
        return this.contentHandler;
    }
    
    public void setErrorHandler(final ErrorHandler handler) {
        if (handler == null) {
            throw new NullPointerException("Null error handler");
        }
        this.errorHandler = handler;
        if (this.parser != null) {
            this.parser.setErrorHandler(this.errorHandler);
        }
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
        this.parsing = true;
        if (this.parser == null) {
            if (this.validation) {
                this.parser = new ValidatingParser();
            }
            else {
                this.parser = new Parser2();
            }
        }
        this.parser.setNamespaceFeatures(this.namespaces, this.prefixes);
        this.parser.setContentHandler(this.contentHandler);
        this.parser.setDTDHandler(this.dtdHandler);
        this.parser.setErrorHandler(this.errorHandler);
        this.parser.setEntityResolver(this.entityResolver);
        this.parser.setLexicalHandler(this.lexicalHandler);
        this.parser.setDeclHandler(this.declHandler);
        try {
            this.parser.parse(input);
        }
        finally {
            this.parsing = false;
        }
    }
    
    private void checkNotParsing(final String type, final String name) throws SAXNotSupportedException {
        if (this.parsing) {
            throw new SAXNotSupportedException("Cannot change " + type + ' ' + name + " while parsing");
        }
    }
}
