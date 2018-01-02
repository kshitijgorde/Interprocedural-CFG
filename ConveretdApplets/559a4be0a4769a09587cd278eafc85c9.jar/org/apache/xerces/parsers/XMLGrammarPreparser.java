// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import java.util.Enumeration;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import java.io.IOException;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.util.ObjectFactory;
import org.apache.xerces.xni.grammars.XMLGrammarLoader;
import org.apache.xerces.impl.XMLEntityManager;
import java.util.Locale;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.util.SymbolTable;
import java.util.Hashtable;

public class XMLGrammarPreparser
{
    private static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String GRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    private static final Hashtable KNOWN_LOADERS;
    private static final String[] RECOGNIZED_PROPERTIES;
    protected SymbolTable fSymbolTable;
    protected XMLErrorReporter fErrorReporter;
    protected XMLEntityResolver fEntityResolver;
    protected XMLGrammarPool fGrammarPool;
    protected Locale fLocale;
    private Hashtable fLoaders;
    
    public XMLGrammarPreparser() {
        this(new SymbolTable());
    }
    
    public XMLGrammarPreparser(final SymbolTable symbolTable) {
        this.fSymbolTable = symbolTable;
        this.fLoaders = new Hashtable();
        this.setLocale(Locale.getDefault());
        (this.fErrorReporter = new XMLErrorReporter()).setLocale(this.fLocale);
        this.fEntityResolver = new XMLEntityManager();
    }
    
    public boolean registerPreparser(final String grammarType, final XMLGrammarLoader loader) {
        if (loader != null) {
            this.fLoaders.put(grammarType, loader);
            return true;
        }
        if (XMLGrammarPreparser.KNOWN_LOADERS.containsKey(grammarType)) {
            final String loaderName = XMLGrammarPreparser.KNOWN_LOADERS.get(grammarType);
            try {
                final ClassLoader cl = ObjectFactory.findClassLoader();
                final XMLGrammarLoader gl = (XMLGrammarLoader)ObjectFactory.newInstance(loaderName, cl, true);
                this.fLoaders.put(grammarType, gl);
            }
            catch (Exception e) {
                return false;
            }
            return true;
        }
        return false;
    }
    
    public Grammar preparseGrammar(final String type, final XMLInputSource is) throws XNIException, IOException {
        if (this.fLoaders.containsKey(type)) {
            final XMLGrammarLoader gl = this.fLoaders.get(type);
            gl.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
            gl.setProperty("http://apache.org/xml/properties/internal/entity-resolver", this.fEntityResolver);
            gl.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
            if (this.fGrammarPool != null) {
                try {
                    gl.setProperty("http://apache.org/xml/properties/internal/grammar-pool", this.fGrammarPool);
                }
                catch (Exception ex) {}
            }
            return gl.loadGrammar(is);
        }
        return null;
    }
    
    public void setLocale(final Locale locale) {
        this.fLocale = locale;
    }
    
    public Locale getLocale() {
        return this.fLocale;
    }
    
    public void setErrorHandler(final XMLErrorHandler errorHandler) {
        this.fErrorReporter.setProperty("http://apache.org/xml/properties/internal/error-handler", errorHandler);
    }
    
    public XMLErrorHandler getErrorHandler() {
        return this.fErrorReporter.getErrorHandler();
    }
    
    public void setEntityResolver(final XMLEntityResolver entityResolver) {
        this.fEntityResolver = entityResolver;
    }
    
    public XMLEntityResolver getEntityResolver() {
        return this.fEntityResolver;
    }
    
    public void setGrammarPool(final XMLGrammarPool grammarPool) {
        this.fGrammarPool = grammarPool;
    }
    
    public XMLGrammarPool getGrammarPool() {
        return this.fGrammarPool;
    }
    
    public XMLGrammarLoader getLoader(final String type) {
        return this.fLoaders.get(type);
    }
    
    public void setFeature(final String featureId, final boolean value) {
        final Enumeration loaders = this.fLoaders.elements();
        while (loaders.hasMoreElements()) {
            final XMLGrammarLoader gl = loaders.nextElement();
            try {
                gl.setFeature(featureId, value);
            }
            catch (Exception ex) {}
        }
        if (featureId.equals("http://apache.org/xml/features/continue-after-fatal-error")) {
            this.fErrorReporter.setFeature("http://apache.org/xml/features/continue-after-fatal-error", value);
        }
    }
    
    public void setProperty(final String propId, final Object value) {
        final Enumeration loaders = this.fLoaders.elements();
        while (loaders.hasMoreElements()) {
            final XMLGrammarLoader gl = loaders.nextElement();
            try {
                gl.setProperty(propId, value);
            }
            catch (Exception ex) {}
        }
    }
    
    public boolean getFeature(final String type, final String featureId) {
        final XMLGrammarLoader gl = this.fLoaders.get(type);
        return gl.getFeature(featureId);
    }
    
    public Object getProperty(final String type, final String propertyId) {
        final XMLGrammarLoader gl = this.fLoaders.get(type);
        return gl.getProperty(propertyId);
    }
    
    static {
        (KNOWN_LOADERS = new Hashtable()).put("http://www.w3.org/2001/XMLSchema", "org.apache.xerces.impl.xs.XMLSchemaLoader");
        XMLGrammarPreparser.KNOWN_LOADERS.put("http://www.w3.org/TR/REC-xml", "org.apache.xerces.impl.dtd.XMLDTDLoader");
        RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/error-handler", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/grammar-pool" };
    }
}
