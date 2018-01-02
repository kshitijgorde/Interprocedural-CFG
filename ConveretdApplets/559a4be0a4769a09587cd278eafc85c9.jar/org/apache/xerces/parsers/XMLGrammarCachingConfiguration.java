// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import org.apache.xerces.impl.dtd.DTDGrammar;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.impl.xs.XSMessageFormatter;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import java.io.IOException;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.impl.dtd.XMLDTDLoader;
import org.apache.xerces.impl.xs.XMLSchemaLoader;
import org.apache.xerces.util.XMLGrammarPoolImpl;
import org.apache.xerces.util.SynchronizedSymbolTable;

public class XMLGrammarCachingConfiguration extends StandardParserConfiguration
{
    public static final int BIG_PRIME = 2039;
    protected static final SynchronizedSymbolTable fStaticSymbolTable;
    protected static final XMLGrammarPoolImpl fStaticGrammarPool;
    protected static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    protected XMLSchemaLoader fSchemaLoader;
    protected XMLDTDLoader fDTDLoader;
    
    public XMLGrammarCachingConfiguration() {
        this(XMLGrammarCachingConfiguration.fStaticSymbolTable, XMLGrammarCachingConfiguration.fStaticGrammarPool, null);
    }
    
    public XMLGrammarCachingConfiguration(final SymbolTable symbolTable) {
        this(symbolTable, XMLGrammarCachingConfiguration.fStaticGrammarPool, null);
    }
    
    public XMLGrammarCachingConfiguration(final SymbolTable symbolTable, final XMLGrammarPool grammarPool) {
        this(symbolTable, grammarPool, null);
    }
    
    public XMLGrammarCachingConfiguration(final SymbolTable symbolTable, final XMLGrammarPool grammarPool, final XMLComponentManager parentSettings) {
        super(symbolTable, grammarPool, parentSettings);
        (this.fSchemaLoader = new XMLSchemaLoader(super.fSymbolTable)).setProperty("http://apache.org/xml/properties/internal/grammar-pool", super.fGrammarPool);
        this.fDTDLoader = new XMLDTDLoader(super.fSymbolTable, super.fGrammarPool);
    }
    
    public void lockGrammarPool() {
        super.fGrammarPool.lockPool();
    }
    
    public void clearGrammarPool() {
        super.fGrammarPool.clear();
    }
    
    public void unlockGrammarPool() {
        super.fGrammarPool.unlockPool();
    }
    
    public Grammar parseGrammar(final String type, final String uri) throws XNIException, IOException {
        final XMLInputSource source = new XMLInputSource(null, uri, null);
        return this.parseGrammar(type, source);
    }
    
    public Grammar parseGrammar(final String type, final XMLInputSource is) throws XNIException, IOException {
        if (type.equals("http://www.w3.org/2001/XMLSchema")) {
            return this.parseXMLSchema(is);
        }
        if (type.equals("http://www.w3.org/TR/REC-xml")) {
            return this.parseDTD(is);
        }
        return null;
    }
    
    protected void checkFeature(final String featureId) throws XMLConfigurationException {
        super.checkFeature(featureId);
    }
    
    protected void checkProperty(final String propertyId) throws XMLConfigurationException {
        super.checkProperty(propertyId);
    }
    
    SchemaGrammar parseXMLSchema(final XMLInputSource is) throws IOException {
        final XMLEntityResolver resolver = this.getEntityResolver();
        if (resolver != null) {
            this.fSchemaLoader.setEntityResolver(resolver);
        }
        if (super.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/xml-schema-1") == null) {
            super.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/xml-schema-1", new XSMessageFormatter());
        }
        this.fSchemaLoader.setProperty("http://apache.org/xml/properties/internal/error-reporter", super.fErrorReporter);
        final String propPrefix = "http://apache.org/xml/properties/";
        String propName = propPrefix + "schema/external-schemaLocation";
        this.fSchemaLoader.setProperty(propName, this.getProperty(propName));
        propName = propPrefix + "schema/external-noNamespaceSchemaLocation";
        this.fSchemaLoader.setProperty(propName, this.getProperty(propName));
        propName = "http://java.sun.com/xml/jaxp/properties/schemaSource";
        this.fSchemaLoader.setProperty(propName, this.getProperty(propName));
        this.fSchemaLoader.setFeature("http://apache.org/xml/features/validation/schema-full-checking", this.getFeature("http://apache.org/xml/features/validation/schema-full-checking"));
        final SchemaGrammar grammar = (SchemaGrammar)this.fSchemaLoader.loadGrammar(is);
        if (grammar != null) {
            super.fGrammarPool.cacheGrammars("http://www.w3.org/2001/XMLSchema", new Grammar[] { grammar });
        }
        return grammar;
    }
    
    DTDGrammar parseDTD(final XMLInputSource is) throws IOException {
        final XMLEntityResolver resolver = this.getEntityResolver();
        if (resolver != null) {
            this.fDTDLoader.setEntityResolver(resolver);
        }
        this.fDTDLoader.setProperty("http://apache.org/xml/properties/internal/error-reporter", super.fErrorReporter);
        final DTDGrammar grammar = (DTDGrammar)this.fDTDLoader.loadGrammar(is);
        if (grammar != null) {
            super.fGrammarPool.cacheGrammars("http://www.w3.org/TR/REC-xml", new Grammar[] { grammar });
        }
        return grammar;
    }
    
    static {
        fStaticSymbolTable = new SynchronizedSymbolTable(2039);
        fStaticGrammarPool = new XMLGrammarPoolImpl();
    }
}
