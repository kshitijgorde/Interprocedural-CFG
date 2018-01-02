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
    
    public XMLGrammarPreparser(final SymbolTable fSymbolTable) {
        this.fSymbolTable = fSymbolTable;
        this.fLoaders = new Hashtable();
        this.setLocale(Locale.getDefault());
        (this.fErrorReporter = new XMLErrorReporter()).setLocale(this.fLocale);
        this.fEntityResolver = new XMLEntityManager();
    }
    
    public boolean registerPreparser(final String s, final XMLGrammarLoader xmlGrammarLoader) {
        if (xmlGrammarLoader != null) {
            this.fLoaders.put(s, xmlGrammarLoader);
            return true;
        }
        if (XMLGrammarPreparser.KNOWN_LOADERS.containsKey(s)) {
            final String s2 = XMLGrammarPreparser.KNOWN_LOADERS.get(s);
            try {
                this.fLoaders.put(s, ObjectFactory.newInstance(s2, ObjectFactory.findClassLoader(), true));
            }
            catch (Exception ex) {
                return false;
            }
            return true;
        }
        return false;
    }
    
    public Grammar preparseGrammar(final String s, final XMLInputSource xmlInputSource) throws XNIException, IOException {
        if (this.fLoaders.containsKey(s)) {
            final XMLGrammarLoader xmlGrammarLoader = this.fLoaders.get(s);
            xmlGrammarLoader.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
            xmlGrammarLoader.setProperty("http://apache.org/xml/properties/internal/entity-resolver", this.fEntityResolver);
            xmlGrammarLoader.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
            if (this.fGrammarPool != null) {
                try {
                    xmlGrammarLoader.setProperty("http://apache.org/xml/properties/internal/grammar-pool", this.fGrammarPool);
                }
                catch (Exception ex) {}
            }
            return xmlGrammarLoader.loadGrammar(xmlInputSource);
        }
        return null;
    }
    
    public void setLocale(final Locale fLocale) {
        this.fLocale = fLocale;
    }
    
    public Locale getLocale() {
        return this.fLocale;
    }
    
    public void setErrorHandler(final XMLErrorHandler xmlErrorHandler) {
        this.fErrorReporter.setProperty("http://apache.org/xml/properties/internal/error-handler", xmlErrorHandler);
    }
    
    public XMLErrorHandler getErrorHandler() {
        return this.fErrorReporter.getErrorHandler();
    }
    
    public void setEntityResolver(final XMLEntityResolver fEntityResolver) {
        this.fEntityResolver = fEntityResolver;
    }
    
    public XMLEntityResolver getEntityResolver() {
        return this.fEntityResolver;
    }
    
    public void setGrammarPool(final XMLGrammarPool fGrammarPool) {
        this.fGrammarPool = fGrammarPool;
    }
    
    public XMLGrammarPool getGrammarPool() {
        return this.fGrammarPool;
    }
    
    public XMLGrammarLoader getLoader(final String s) {
        return this.fLoaders.get(s);
    }
    
    public void setFeature(final String s, final boolean b) {
        final Enumeration<XMLGrammarLoader> elements = (Enumeration<XMLGrammarLoader>)this.fLoaders.elements();
        while (elements.hasMoreElements()) {
            final XMLGrammarLoader xmlGrammarLoader = elements.nextElement();
            try {
                xmlGrammarLoader.setFeature(s, b);
            }
            catch (Exception ex) {}
        }
        if (s.equals("http://apache.org/xml/features/continue-after-fatal-error")) {
            this.fErrorReporter.setFeature("http://apache.org/xml/features/continue-after-fatal-error", b);
        }
    }
    
    public void setProperty(final String s, final Object o) {
        final Enumeration<XMLGrammarLoader> elements = (Enumeration<XMLGrammarLoader>)this.fLoaders.elements();
        while (elements.hasMoreElements()) {
            final XMLGrammarLoader xmlGrammarLoader = elements.nextElement();
            try {
                xmlGrammarLoader.setProperty(s, o);
            }
            catch (Exception ex) {}
        }
    }
    
    public boolean getFeature(final String s, final String s2) {
        return this.fLoaders.get(s).getFeature(s2);
    }
    
    public Object getProperty(final String s, final String s2) {
        return this.fLoaders.get(s).getProperty(s2);
    }
    
    static {
        (KNOWN_LOADERS = new Hashtable()).put("http://www.w3.org/2001/XMLSchema", "org.apache.xerces.impl.xs.XMLSchemaLoader");
        XMLGrammarPreparser.KNOWN_LOADERS.put("http://www.w3.org/TR/REC-xml", "org.apache.xerces.impl.dtd.XMLDTDLoader");
        RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/error-handler", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/grammar-pool" };
    }
}
