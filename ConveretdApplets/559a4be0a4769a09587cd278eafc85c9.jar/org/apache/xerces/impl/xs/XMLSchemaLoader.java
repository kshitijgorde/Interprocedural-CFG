// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import java.io.Reader;
import java.io.FileNotFoundException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.Vector;
import org.xml.sax.InputSource;
import java.io.InputStream;
import java.util.StringTokenizer;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XNIException;
import java.io.IOException;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.util.DefaultErrorHandler;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.impl.xs.models.CMBuilder;
import org.apache.xerces.impl.xs.traversers.XSDHandler;
import java.util.Locale;
import java.util.Hashtable;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.grammars.XMLGrammarLoader;

public class XMLSchemaLoader implements XMLGrammarLoader
{
    protected static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    private static final String[] RECOGNIZED_FEATURES;
    public static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    public static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    public static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    public static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    protected static final String SCHEMA_LOCATION = "http://apache.org/xml/properties/schema/external-schemaLocation";
    protected static final String SCHEMA_NONS_LOCATION = "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation";
    protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    private static final String[] RECOGNIZED_PROPERTIES;
    private boolean fIsCheckedFully;
    private boolean fAllowJavaEncodings;
    private SymbolTable fSymbolTable;
    private XMLErrorReporter fErrorReporter;
    private XMLEntityResolver fEntityResolver;
    private XMLGrammarPool fGrammarPool;
    private String fExternalSchemas;
    private String fExternalNoNSSchema;
    private Object fJAXPSource;
    private Hashtable fJAXPCache;
    private Locale fLocale;
    private XSDHandler fSchemaHandler;
    private XSGrammarBucket fGrammarBucket;
    private XSDeclarationPool fDeclPool;
    private SubstitutionGroupHandler fSubGroupHandler;
    private CMBuilder fCMBuilder;
    private boolean fJAXPProcessed;
    private XSDDescription fXSDDescription;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$java$io$File;
    static /* synthetic */ Class class$java$io$InputStream;
    static /* synthetic */ Class class$org$xml$sax$InputSource;
    
    public XMLSchemaLoader() {
        this(new SymbolTable(), null, new XMLEntityManager(), null, null, null);
    }
    
    public XMLSchemaLoader(final SymbolTable symbolTable) {
        this(symbolTable, null, new XMLEntityManager(), null, null, null);
    }
    
    XMLSchemaLoader(final XMLErrorReporter errorReporter, final XSGrammarBucket grammarBucket, final SubstitutionGroupHandler sHandler, final CMBuilder builder) {
        this(null, errorReporter, null, grammarBucket, sHandler, builder);
    }
    
    XMLSchemaLoader(final SymbolTable symbolTable, XMLErrorReporter errorReporter, final XMLEntityResolver entityResolver, XSGrammarBucket grammarBucket, SubstitutionGroupHandler sHandler, CMBuilder builder) {
        this.fIsCheckedFully = false;
        this.fAllowJavaEncodings = false;
        this.fSymbolTable = null;
        this.fErrorReporter = new XMLErrorReporter();
        this.fEntityResolver = null;
        this.fGrammarPool = null;
        this.fExternalSchemas = null;
        this.fExternalNoNSSchema = null;
        this.fJAXPSource = null;
        this.fLocale = Locale.getDefault();
        this.fDeclPool = null;
        this.fJAXPProcessed = false;
        this.fXSDDescription = new XSDDescription();
        this.fSymbolTable = symbolTable;
        if (errorReporter == null) {
            errorReporter = new XMLErrorReporter();
            errorReporter.setProperty("http://apache.org/xml/properties/internal/error-handler", new DefaultErrorHandler());
        }
        this.fErrorReporter = errorReporter;
        this.fEntityResolver = entityResolver;
        if (grammarBucket == null) {
            grammarBucket = new XSGrammarBucket();
        }
        this.fGrammarBucket = grammarBucket;
        if (sHandler == null) {
            sHandler = new SubstitutionGroupHandler(this.fGrammarBucket);
        }
        this.fSubGroupHandler = sHandler;
        if (builder == null) {
            builder = new CMBuilder();
        }
        this.fCMBuilder = builder;
        this.fSchemaHandler = new XSDHandler(this.fGrammarBucket);
        this.fDeclPool = new XSDeclarationPool();
        this.fJAXPCache = new Hashtable();
    }
    
    public String[] getRecognizedFeatures() {
        return XMLSchemaLoader.RECOGNIZED_FEATURES.clone();
    }
    
    public boolean getFeature(final String featureId) throws XMLConfigurationException {
        if (featureId.equals("http://apache.org/xml/features/validation/schema-full-checking")) {
            return this.fIsCheckedFully;
        }
        if (featureId.equals("http://apache.org/xml/features/continue-after-fatal-error")) {
            return this.fErrorReporter.getFeature("http://apache.org/xml/features/continue-after-fatal-error");
        }
        throw new XMLConfigurationException((short)0, featureId);
    }
    
    public void setFeature(final String featureId, final boolean state) throws XMLConfigurationException {
        if (featureId.equals("http://apache.org/xml/features/validation/schema-full-checking")) {
            this.fIsCheckedFully = state;
        }
        else if (featureId.equals("http://apache.org/xml/features/continue-after-fatal-error")) {
            this.fErrorReporter.setFeature("http://apache.org/xml/features/continue-after-fatal-error", state);
        }
        else {
            if (!featureId.equals("http://apache.org/xml/features/allow-java-encodings")) {
                throw new XMLConfigurationException((short)0, featureId);
            }
            this.fAllowJavaEncodings = state;
        }
    }
    
    public String[] getRecognizedProperties() {
        return XMLSchemaLoader.RECOGNIZED_PROPERTIES.clone();
    }
    
    public Object getProperty(final String propertyId) throws XMLConfigurationException {
        if (propertyId.equals("http://apache.org/xml/properties/internal/symbol-table")) {
            return this.fSymbolTable;
        }
        if (propertyId.equals("http://apache.org/xml/properties/internal/error-reporter")) {
            return this.fErrorReporter;
        }
        if (propertyId.equals("http://apache.org/xml/properties/internal/error-handler")) {
            return this.fErrorReporter.getErrorHandler();
        }
        if (propertyId.equals("http://apache.org/xml/properties/internal/entity-resolver")) {
            return this.fEntityResolver;
        }
        if (propertyId.equals("http://apache.org/xml/properties/internal/grammar-pool")) {
            return this.fGrammarPool;
        }
        if (propertyId.equals("http://apache.org/xml/properties/schema/external-schemaLocation")) {
            return this.fExternalSchemas;
        }
        if (propertyId.equals("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation")) {
            return this.fExternalNoNSSchema;
        }
        if (propertyId.equals("http://java.sun.com/xml/jaxp/properties/schemaSource")) {
            return this.fJAXPSource;
        }
        throw new XMLConfigurationException((short)0, propertyId);
    }
    
    public void setProperty(final String propertyId, final Object state) throws XMLConfigurationException, ClassCastException {
        if (propertyId.equals("http://apache.org/xml/properties/internal/symbol-table")) {
            this.fSymbolTable = (SymbolTable)state;
        }
        else if (propertyId.equals("http://apache.org/xml/properties/internal/error-reporter")) {
            this.fErrorReporter = (XMLErrorReporter)state;
        }
        else if (propertyId.equals("http://apache.org/xml/properties/internal/error-handler")) {
            this.fErrorReporter.setProperty(propertyId, state);
        }
        else if (propertyId.equals("http://apache.org/xml/properties/internal/entity-resolver")) {
            this.fEntityResolver = (XMLEntityResolver)state;
        }
        else if (propertyId.equals("http://apache.org/xml/properties/internal/grammar-pool")) {
            this.fGrammarPool = (XMLGrammarPool)state;
        }
        else if (propertyId.equals("http://apache.org/xml/properties/schema/external-schemaLocation")) {
            this.fExternalSchemas = (String)state;
        }
        else if (propertyId.equals("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation")) {
            this.fExternalNoNSSchema = (String)state;
        }
        else {
            if (!propertyId.equals("http://java.sun.com/xml/jaxp/properties/schemaSource")) {
                throw new XMLConfigurationException((short)0, propertyId);
            }
            this.fJAXPSource = state;
            this.fJAXPProcessed = false;
        }
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
    
    public void reset() {
        this.fGrammarBucket.reset();
        if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/xml-schema-1") == null) {
            this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/xml-schema-1", new XSMessageFormatter());
        }
        if (this.fGrammarPool != null) {
            final Grammar[] initialGrammars = this.fGrammarPool.retrieveInitialGrammarSet("http://www.w3.org/2001/XMLSchema");
            for (int i = 0; i < initialGrammars.length; ++i) {
                if (!this.fGrammarBucket.putGrammar((SchemaGrammar)initialGrammars[i], true)) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "GrammarConflict", null, (short)0);
                }
            }
            this.fCMBuilder.setDeclPool(null);
        }
        else {
            this.fDeclPool.reset();
            this.fCMBuilder.setDeclPool(this.fDeclPool);
        }
        this.fSchemaHandler.reset(this.fErrorReporter, this.fEntityResolver, this.fSymbolTable, this.fGrammarPool, this.fAllowJavaEncodings);
        if (this.fGrammarPool == null) {
            this.fDeclPool.reset();
            this.fSchemaHandler.setDeclPool(this.fDeclPool);
        }
        else {
            this.fSchemaHandler.setDeclPool(null);
        }
        this.fSubGroupHandler.reset();
        this.fJAXPProcessed = false;
    }
    
    public Grammar loadGrammar(final XMLInputSource source) throws IOException, XNIException {
        this.reset();
        final XSDDescription desc = new XSDDescription();
        desc.fContextType = 3;
        desc.setBaseSystemId(source.getBaseSystemId());
        desc.setLiteralSystemId(source.getSystemId());
        final Hashtable locationPairs = new Hashtable();
        if (!tokenizeSchemaLocationStr(this.fExternalSchemas, locationPairs)) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "SchemaLocation", new Object[] { this.fExternalSchemas }, (short)0);
        }
        if (this.fExternalNoNSSchema != null) {
            final LocationArray noNs = new LocationArray();
            noNs.addLocation(this.fExternalNoNSSchema);
            locationPairs.put(XMLSymbols.EMPTY_STRING, noNs);
        }
        final SchemaGrammar grammar = this.loadSchema(desc, source, locationPairs);
        if (grammar != null && this.fGrammarPool != null) {
            this.fGrammarPool.cacheGrammars("http://www.w3.org/2001/XMLSchema", this.fGrammarBucket.getGrammars());
        }
        return grammar;
    }
    
    SchemaGrammar loadSchema(final XSDDescription desc, final XMLInputSource source, final Hashtable locationPairs) throws IOException, XNIException {
        if (!this.fJAXPProcessed) {
            this.processJAXPSchemaSource(locationPairs);
        }
        final SchemaGrammar grammar = this.fSchemaHandler.parseSchema(source, desc, locationPairs);
        if (this.fIsCheckedFully) {
            XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fErrorReporter);
        }
        return grammar;
    }
    
    public static XMLInputSource resolveDocument(final XSDDescription desc, final Hashtable locationPairs, final XMLEntityResolver entityResolver) throws IOException {
        String loc = null;
        if (desc.getContextType() == 2 || desc.fromInstance()) {
            final String namespace = desc.getTargetNamespace();
            final String ns = (namespace == null) ? XMLSymbols.EMPTY_STRING : namespace;
            final LocationArray tempLA = locationPairs.get(ns);
            if (tempLA != null) {
                loc = tempLA.getFirstLocation();
            }
        }
        if (loc == null) {
            final String[] hints = desc.getLocationHints();
            if (hints != null && hints.length > 0) {
                loc = hints[0];
            }
        }
        final String expandedLoc = XMLEntityManager.expandSystemId(loc, desc.getBaseSystemId());
        desc.setLiteralSystemId(loc);
        desc.setExpandedSystemId(expandedLoc);
        return entityResolver.resolveEntity(desc);
    }
    
    public static boolean tokenizeSchemaLocationStr(final String schemaStr, final Hashtable locations) {
        if (schemaStr != null) {
            final StringTokenizer t = new StringTokenizer(schemaStr, " \n\t\r");
            while (t.hasMoreTokens()) {
                final String namespace = t.nextToken();
                if (!t.hasMoreTokens()) {
                    return false;
                }
                final String location = t.nextToken();
                LocationArray la = locations.get(namespace);
                if (la == null) {
                    la = new LocationArray();
                    locations.put(namespace, la);
                }
                la.addLocation(location);
            }
        }
        return true;
    }
    
    private void processJAXPSchemaSource(final Hashtable locationPairs) throws IOException {
        this.fJAXPProcessed = true;
        if (this.fJAXPSource == null) {
            return;
        }
        final Class componentType = this.fJAXPSource.getClass().getComponentType();
        XMLInputSource xis = null;
        String sid = null;
        if (componentType == null) {
            if (this.fJAXPSource instanceof InputStream || this.fJAXPSource instanceof InputSource) {
                final SchemaGrammar g = this.fJAXPCache.get(this.fJAXPSource);
                if (g != null) {
                    this.fGrammarBucket.putGrammar(g);
                    return;
                }
            }
            this.fXSDDescription.reset();
            xis = this.xsdToXMLInputSource(this.fJAXPSource);
            sid = xis.getSystemId();
            this.fXSDDescription.fContextType = 3;
            if (sid != null) {
                this.fXSDDescription.setLiteralSystemId(sid);
                this.fXSDDescription.setExpandedSystemId(sid);
                this.fXSDDescription.fLocationHints = new String[] { sid };
            }
            final SchemaGrammar g = this.loadSchema(this.fXSDDescription, xis, locationPairs);
            if (this.fJAXPSource instanceof InputStream || this.fJAXPSource instanceof InputSource) {
                this.fJAXPCache.put(this.fJAXPSource, g);
            }
            this.fGrammarBucket.putGrammar(g);
            return;
        }
        if (componentType != ((XMLSchemaLoader.class$java$lang$Object == null) ? (XMLSchemaLoader.class$java$lang$Object = class$("java.lang.Object")) : XMLSchemaLoader.class$java$lang$Object) && componentType != ((XMLSchemaLoader.class$java$lang$String == null) ? (XMLSchemaLoader.class$java$lang$String = class$("java.lang.String")) : XMLSchemaLoader.class$java$lang$String) && componentType != ((XMLSchemaLoader.class$java$io$File == null) ? (XMLSchemaLoader.class$java$io$File = class$("java.io.File")) : XMLSchemaLoader.class$java$io$File) && componentType != ((XMLSchemaLoader.class$java$io$InputStream == null) ? (XMLSchemaLoader.class$java$io$InputStream = class$("java.io.InputStream")) : XMLSchemaLoader.class$java$io$InputStream) && componentType != ((XMLSchemaLoader.class$org$xml$sax$InputSource == null) ? (XMLSchemaLoader.class$org$xml$sax$InputSource = class$("org.xml.sax.InputSource")) : XMLSchemaLoader.class$org$xml$sax$InputSource)) {
            throw new XMLConfigurationException((short)1, "\"http://java.sun.com/xml/jaxp/properties/schemaSource\" property cannot have an array of type {" + componentType.getName() + "}. Possible types of the array supported are Object, String, File, " + "InputStream, InputSource.");
        }
        final Object[] objArr = (Object[])this.fJAXPSource;
        final Vector jaxpSchemaSourceNamespaces = new Vector();
        for (int i = 0; i < objArr.length; ++i) {
            if (objArr[i] instanceof InputStream || objArr[i] instanceof InputSource) {
                final SchemaGrammar g2 = this.fJAXPCache.get(objArr[i]);
                if (g2 != null) {
                    this.fGrammarBucket.putGrammar(g2);
                    continue;
                }
            }
            this.fXSDDescription.reset();
            xis = this.xsdToXMLInputSource(objArr[i]);
            sid = xis.getSystemId();
            this.fXSDDescription.fContextType = 3;
            if (sid != null) {
                this.fXSDDescription.setLiteralSystemId(sid);
                this.fXSDDescription.setExpandedSystemId(sid);
                this.fXSDDescription.fLocationHints = new String[] { sid };
            }
            String targetNamespace = null;
            final SchemaGrammar grammar = this.loadSchema(this.fXSDDescription, xis, locationPairs);
            if (grammar != null) {
                targetNamespace = grammar.getTargetNamespace();
                if (jaxpSchemaSourceNamespaces.contains(targetNamespace)) {
                    throw new IllegalArgumentException(" When using array of Objects as the value of SCHEMA_SOURCE property , no two Schemas should share the same targetNamespace. ");
                }
                jaxpSchemaSourceNamespaces.add(targetNamespace);
                if (objArr[i] instanceof InputStream || objArr[i] instanceof InputSource) {
                    this.fJAXPCache.put(objArr[i], grammar);
                }
                this.fGrammarBucket.putGrammar(grammar);
            }
        }
    }
    
    private XMLInputSource xsdToXMLInputSource(final Object val) {
        if (val instanceof String) {
            final String loc = (String)val;
            if (this.fEntityResolver != null) {
                this.fXSDDescription.reset();
                this.fXSDDescription.setValues(null, loc, null, null);
                XMLInputSource xis = null;
                try {
                    xis = this.fEntityResolver.resolveEntity(this.fXSDDescription);
                }
                catch (IOException ex) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "schema_reference.4", new Object[] { loc }, (short)1);
                }
                if (xis == null) {
                    return new XMLInputSource(null, loc, null);
                }
                return xis;
            }
        }
        else {
            if (val instanceof InputSource) {
                return saxToXMLInputSource((InputSource)val);
            }
            if (val instanceof InputStream) {
                return new XMLInputSource(null, null, null, (InputStream)val, null);
            }
            if (val instanceof File) {
                final File file = (File)val;
                InputStream is = null;
                try {
                    is = new BufferedInputStream(new FileInputStream(file));
                }
                catch (FileNotFoundException ex2) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "schema_reference.4", new Object[] { file.toString() }, (short)1);
                }
                return new XMLInputSource(null, null, null, is, null);
            }
        }
        throw new XMLConfigurationException((short)1, "\"http://java.sun.com/xml/jaxp/properties/schemaSource\" property cannot have a value of type {" + val.getClass().getName() + "}. Possible types of the value supported are String, File, InputStream, " + "InputSource OR an array of these types.");
    }
    
    private static XMLInputSource saxToXMLInputSource(final InputSource sis) {
        final String publicId = sis.getPublicId();
        final String systemId = sis.getSystemId();
        final Reader charStream = sis.getCharacterStream();
        if (charStream != null) {
            return new XMLInputSource(publicId, systemId, null, charStream, null);
        }
        final InputStream byteStream = sis.getByteStream();
        if (byteStream != null) {
            return new XMLInputSource(publicId, systemId, null, byteStream, sis.getEncoding());
        }
        return new XMLInputSource(publicId, systemId, null);
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        RECOGNIZED_FEATURES = new String[] { "http://apache.org/xml/features/validation/schema-full-checking", "http://apache.org/xml/features/continue-after-fatal-error", "http://apache.org/xml/features/allow-java-encodings" };
        RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/error-handler", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/grammar-pool", "http://apache.org/xml/properties/schema/external-schemaLocation", "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", "http://java.sun.com/xml/jaxp/properties/schemaSource" };
    }
    
    static class LocationArray
    {
        int length;
        String[] locations;
        
        LocationArray() {
            this.locations = new String[2];
        }
        
        public void resize(final int oldLength, final int newLength) {
            final String[] temp = new String[newLength];
            System.arraycopy(this.locations, 0, temp, 0, Math.min(oldLength, newLength));
            this.locations = temp;
            this.length = Math.min(oldLength, newLength);
        }
        
        public void addLocation(final String location) {
            if (this.length >= this.locations.length) {
                this.resize(this.length, Math.max(1, this.length * 2));
            }
            this.locations[this.length++] = location;
        }
        
        public String[] getLocationArray() {
            if (this.length < this.locations.length) {
                this.resize(this.locations.length, this.length);
            }
            return this.locations;
        }
        
        public String getFirstLocation() {
            return (this.length > 0) ? this.locations[0] : null;
        }
        
        public int getLength() {
            return this.length;
        }
    }
}
