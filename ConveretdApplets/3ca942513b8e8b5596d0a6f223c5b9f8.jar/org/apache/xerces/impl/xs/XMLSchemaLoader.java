// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import java.io.StringReader;
import org.w3c.dom.ls.LSResourceResolver;
import org.w3c.dom.DOMErrorHandler;
import org.apache.xerces.dom.DOMStringListImpl;
import org.w3c.dom.DOMException;
import org.apache.xerces.dom.DOMMessageFormatter;
import org.w3c.dom.DOMError;
import org.apache.xerces.dom.DOMErrorImpl;
import org.apache.xerces.xs.StringList;
import org.apache.xerces.xs.LSInputList;
import org.apache.xerces.xni.grammars.XSGrammar;
import org.apache.xerces.xs.XSModel;
import org.w3c.dom.ls.LSInput;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.Vector;
import org.xml.sax.InputSource;
import java.io.InputStream;
import java.util.StringTokenizer;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.xni.XNIException;
import java.io.IOException;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.impl.xs.models.CMNodeFactory;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.util.DefaultErrorHandler;
import org.apache.xerces.util.DOMEntityResolverWrapper;
import org.apache.xerces.util.DOMErrorHandlerWrapper;
import org.w3c.dom.DOMStringList;
import java.util.Locale;
import java.util.Hashtable;
import org.apache.xerces.impl.xs.models.CMBuilder;
import org.apache.xerces.impl.xs.traversers.XSDHandler;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.ParserConfigurationSettings;
import org.w3c.dom.DOMConfiguration;
import org.apache.xerces.xs.XSLoader;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.grammars.XMLGrammarLoader;

public class XMLSchemaLoader implements XMLGrammarLoader, XMLComponent, XSLoader, DOMConfiguration
{
    protected static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    protected static final String STANDARD_URI_CONFORMANT_FEATURE = "http://apache.org/xml/features/standard-uri-conformant";
    protected static final String VALIDATE_ANNOTATIONS = "http://apache.org/xml/features/validate-annotations";
    protected static final String DISALLOW_DOCTYPE = "http://apache.org/xml/features/disallow-doctype-decl";
    protected static final String GENERATE_SYNTHETIC_ANNOTATIONS = "http://apache.org/xml/features/generate-synthetic-annotations";
    protected static final String HONOUR_ALL_SCHEMALOCATIONS = "http://apache.org/xml/features/honour-all-schemaLocations";
    protected static final String AUGMENT_PSVI = "http://apache.org/xml/features/validation/schema/augment-psvi";
    protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
    private static final String[] RECOGNIZED_FEATURES;
    public static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    public static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    public static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    public static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    protected static final String SCHEMA_LOCATION = "http://apache.org/xml/properties/schema/external-schemaLocation";
    protected static final String SCHEMA_NONS_LOCATION = "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation";
    protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    protected static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    private static final String[] RECOGNIZED_PROPERTIES;
    private ParserConfigurationSettings fLoaderConfig;
    private SymbolTable fSymbolTable;
    private XMLErrorReporter fErrorReporter;
    private XMLEntityManager fEntityManager;
    private XMLEntityResolver fUserEntityResolver;
    private XMLGrammarPool fGrammarPool;
    private String fExternalSchemas;
    private String fExternalNoNSSchema;
    private Object fJAXPSource;
    private boolean fIsCheckedFully;
    private boolean fJAXPProcessed;
    private boolean fSettingsChanged;
    private XSDHandler fSchemaHandler;
    private XSGrammarBucket fGrammarBucket;
    private XSDeclarationPool fDeclPool;
    private SubstitutionGroupHandler fSubGroupHandler;
    private CMBuilder fCMBuilder;
    private XSDDescription fXSDDescription;
    private Hashtable fJAXPCache;
    private Locale fLocale;
    private DOMStringList fRecognizedParameters;
    private DOMErrorHandlerWrapper fErrorHandler;
    private DOMEntityResolverWrapper fResourceResolver;
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
    
    XMLSchemaLoader(final XMLErrorReporter xmlErrorReporter, final XSGrammarBucket xsGrammarBucket, final SubstitutionGroupHandler substitutionGroupHandler, final CMBuilder cmBuilder) {
        this(null, xmlErrorReporter, null, xsGrammarBucket, substitutionGroupHandler, cmBuilder);
    }
    
    XMLSchemaLoader(final SymbolTable symbolTable, XMLErrorReporter fErrorReporter, final XMLEntityManager fEntityManager, XSGrammarBucket fGrammarBucket, SubstitutionGroupHandler fSubGroupHandler, CMBuilder fcmBuilder) {
        this.fLoaderConfig = new ParserConfigurationSettings();
        this.fSymbolTable = null;
        this.fErrorReporter = new XMLErrorReporter();
        this.fEntityManager = null;
        this.fUserEntityResolver = null;
        this.fGrammarPool = null;
        this.fExternalSchemas = null;
        this.fExternalNoNSSchema = null;
        this.fJAXPSource = null;
        this.fIsCheckedFully = false;
        this.fJAXPProcessed = false;
        this.fSettingsChanged = true;
        this.fDeclPool = null;
        this.fXSDDescription = new XSDDescription();
        this.fLocale = Locale.getDefault();
        this.fRecognizedParameters = null;
        this.fErrorHandler = null;
        this.fResourceResolver = null;
        this.fLoaderConfig.addRecognizedFeatures(XMLSchemaLoader.RECOGNIZED_FEATURES);
        this.fLoaderConfig.addRecognizedProperties(XMLSchemaLoader.RECOGNIZED_PROPERTIES);
        if (symbolTable != null) {
            this.fLoaderConfig.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
        }
        if (fErrorReporter == null) {
            fErrorReporter = new XMLErrorReporter();
            fErrorReporter.setLocale(this.fLocale);
            fErrorReporter.setProperty("http://apache.org/xml/properties/internal/error-handler", new DefaultErrorHandler());
        }
        this.fErrorReporter = fErrorReporter;
        if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/xml-schema-1") == null) {
            this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/xml-schema-1", new XSMessageFormatter());
        }
        this.fLoaderConfig.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
        this.fEntityManager = fEntityManager;
        if (this.fEntityManager != null) {
            this.fLoaderConfig.setProperty("http://apache.org/xml/properties/internal/entity-manager", this.fEntityManager);
        }
        this.fLoaderConfig.setFeature("http://apache.org/xml/features/validation/schema/augment-psvi", true);
        if (fGrammarBucket == null) {
            fGrammarBucket = new XSGrammarBucket();
        }
        this.fGrammarBucket = fGrammarBucket;
        if (fSubGroupHandler == null) {
            fSubGroupHandler = new SubstitutionGroupHandler(this.fGrammarBucket);
        }
        this.fSubGroupHandler = fSubGroupHandler;
        final CMNodeFactory cmNodeFactory = new CMNodeFactory();
        if (fcmBuilder == null) {
            fcmBuilder = new CMBuilder(cmNodeFactory);
        }
        this.fCMBuilder = fcmBuilder;
        this.fSchemaHandler = new XSDHandler(this.fGrammarBucket);
        this.fDeclPool = new XSDeclarationPool();
        this.fJAXPCache = new Hashtable();
        this.fSettingsChanged = true;
    }
    
    public String[] getRecognizedFeatures() {
        return XMLSchemaLoader.RECOGNIZED_FEATURES.clone();
    }
    
    public boolean getFeature(final String s) throws XMLConfigurationException {
        return this.fLoaderConfig.getFeature(s);
    }
    
    public void setFeature(final String s, final boolean generateSyntheticAnnotations) throws XMLConfigurationException {
        this.fSettingsChanged = true;
        if (s.equals("http://apache.org/xml/features/continue-after-fatal-error")) {
            this.fErrorReporter.setFeature("http://apache.org/xml/features/continue-after-fatal-error", generateSyntheticAnnotations);
        }
        else if (s.equals("http://apache.org/xml/features/generate-synthetic-annotations")) {
            this.fSchemaHandler.setGenerateSyntheticAnnotations(generateSyntheticAnnotations);
        }
        this.fLoaderConfig.setFeature(s, generateSyntheticAnnotations);
    }
    
    public String[] getRecognizedProperties() {
        return XMLSchemaLoader.RECOGNIZED_PROPERTIES.clone();
    }
    
    public Object getProperty(final String s) throws XMLConfigurationException {
        return this.fLoaderConfig.getProperty(s);
    }
    
    public void setProperty(final String s, final Object fjaxpSource) throws XMLConfigurationException {
        this.fSettingsChanged = true;
        this.fLoaderConfig.setProperty(s, fjaxpSource);
        if (s.equals("http://java.sun.com/xml/jaxp/properties/schemaSource")) {
            this.fJAXPSource = fjaxpSource;
            this.fJAXPProcessed = false;
        }
        else if (s.equals("http://apache.org/xml/properties/internal/grammar-pool")) {
            this.fGrammarPool = (XMLGrammarPool)fjaxpSource;
        }
        else if (s.equals("http://apache.org/xml/properties/schema/external-schemaLocation")) {
            this.fExternalSchemas = (String)fjaxpSource;
        }
        else if (s.equals("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation")) {
            this.fExternalNoNSSchema = (String)fjaxpSource;
        }
        else if (s.equals("http://apache.org/xml/properties/internal/entity-resolver")) {
            this.fEntityManager.setProperty("http://apache.org/xml/properties/internal/entity-resolver", fjaxpSource);
        }
        else if (s.equals("http://apache.org/xml/properties/internal/error-reporter")) {
            this.fErrorReporter = (XMLErrorReporter)fjaxpSource;
            if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/xml-schema-1") == null) {
                this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/xml-schema-1", new XSMessageFormatter());
            }
        }
    }
    
    public void setLocale(final Locale locale) {
        this.fLocale = locale;
        this.fErrorReporter.setLocale(locale);
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
    
    public void setEntityResolver(final XMLEntityResolver fUserEntityResolver) {
        this.fUserEntityResolver = fUserEntityResolver;
        this.fLoaderConfig.setProperty("http://apache.org/xml/properties/internal/entity-resolver", fUserEntityResolver);
        this.fEntityManager.setProperty("http://apache.org/xml/properties/internal/entity-resolver", fUserEntityResolver);
    }
    
    public XMLEntityResolver getEntityResolver() {
        return this.fUserEntityResolver;
    }
    
    public void loadGrammar(final XMLInputSource[] array) throws IOException, XNIException {
        for (int length = array.length, i = 0; i < length; ++i) {
            this.loadGrammar(array[i]);
        }
    }
    
    public Grammar loadGrammar(final XMLInputSource xmlInputSource) throws IOException, XNIException {
        this.reset(this.fLoaderConfig);
        this.fSettingsChanged = false;
        final XSDDescription xsdDescription = new XSDDescription();
        xsdDescription.fContextType = 3;
        xsdDescription.setBaseSystemId(xmlInputSource.getBaseSystemId());
        xsdDescription.setLiteralSystemId(xmlInputSource.getSystemId());
        final Hashtable hashtable = new Hashtable();
        processExternalHints(this.fExternalSchemas, this.fExternalNoNSSchema, hashtable, this.fErrorReporter);
        final SchemaGrammar loadSchema = this.loadSchema(xsdDescription, xmlInputSource, hashtable);
        if (loadSchema != null && this.fGrammarPool != null) {
            this.fGrammarPool.cacheGrammars("http://www.w3.org/2001/XMLSchema", this.fGrammarBucket.getGrammars());
            if (this.fIsCheckedFully && this.fJAXPCache.get(loadSchema) != loadSchema) {
                XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fErrorReporter);
            }
        }
        return loadSchema;
    }
    
    SchemaGrammar loadSchema(final XSDDescription xsdDescription, final XMLInputSource xmlInputSource, final Hashtable hashtable) throws IOException, XNIException {
        if (!this.fJAXPProcessed) {
            this.processJAXPSchemaSource(hashtable);
        }
        return this.fSchemaHandler.parseSchema(xmlInputSource, xsdDescription, hashtable);
    }
    
    public static XMLInputSource resolveDocument(final XSDDescription xsdDescription, final Hashtable hashtable, final XMLEntityResolver xmlEntityResolver) throws IOException {
        String firstLocation = null;
        if (xsdDescription.getContextType() == 2 || xsdDescription.fromInstance()) {
            final String targetNamespace = xsdDescription.getTargetNamespace();
            final LocationArray locationArray = hashtable.get((targetNamespace == null) ? XMLSymbols.EMPTY_STRING : targetNamespace);
            if (locationArray != null) {
                firstLocation = locationArray.getFirstLocation();
            }
        }
        if (firstLocation == null) {
            final String[] locationHints = xsdDescription.getLocationHints();
            if (locationHints != null && locationHints.length > 0) {
                firstLocation = locationHints[0];
            }
        }
        final String expandSystemId = XMLEntityManager.expandSystemId(firstLocation, xsdDescription.getBaseSystemId(), false);
        xsdDescription.setLiteralSystemId(firstLocation);
        xsdDescription.setExpandedSystemId(expandSystemId);
        return xmlEntityResolver.resolveEntity(xsdDescription);
    }
    
    public static void processExternalHints(final String s, final String s2, final Hashtable hashtable, final XMLErrorReporter xmlErrorReporter) {
        if (s != null) {
            try {
                SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_SCHEMALOCATION).fType.validate(s, null, null);
                if (!tokenizeSchemaLocationStr(s, hashtable)) {
                    xmlErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "SchemaLocation", new Object[] { s }, (short)0);
                }
            }
            catch (InvalidDatatypeValueException ex) {
                xmlErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", ex.getKey(), ex.getArgs(), (short)0);
            }
        }
        if (s2 != null) {
            try {
                SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION).fType.validate(s2, null, null);
                LocationArray locationArray = hashtable.get(XMLSymbols.EMPTY_STRING);
                if (locationArray == null) {
                    locationArray = new LocationArray();
                    hashtable.put(XMLSymbols.EMPTY_STRING, locationArray);
                }
                locationArray.addLocation(s2);
            }
            catch (InvalidDatatypeValueException ex2) {
                xmlErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", ex2.getKey(), ex2.getArgs(), (short)0);
            }
        }
    }
    
    public static boolean tokenizeSchemaLocationStr(final String s, final Hashtable hashtable) {
        if (s != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, " \n\t\r");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (!stringTokenizer.hasMoreTokens()) {
                    return false;
                }
                final String nextToken2 = stringTokenizer.nextToken();
                LocationArray locationArray = hashtable.get(nextToken);
                if (locationArray == null) {
                    locationArray = new LocationArray();
                    hashtable.put(nextToken, locationArray);
                }
                locationArray.addLocation(nextToken2);
            }
        }
        return true;
    }
    
    private void processJAXPSchemaSource(final Hashtable hashtable) throws IOException {
        this.fJAXPProcessed = true;
        if (this.fJAXPSource == null) {
            return;
        }
        final Class<?> componentType = this.fJAXPSource.getClass().getComponentType();
        if (componentType == null) {
            if (this.fJAXPSource instanceof InputStream || this.fJAXPSource instanceof InputSource) {
                final SchemaGrammar schemaGrammar = this.fJAXPCache.get(this.fJAXPSource);
                if (schemaGrammar != null) {
                    this.fGrammarBucket.putGrammar(schemaGrammar);
                    return;
                }
            }
            this.fXSDDescription.reset();
            final XMLInputSource xsdToXMLInputSource = this.xsdToXMLInputSource(this.fJAXPSource);
            final String systemId = xsdToXMLInputSource.getSystemId();
            this.fXSDDescription.fContextType = 3;
            if (systemId != null) {
                this.fXSDDescription.setBaseSystemId(xsdToXMLInputSource.getBaseSystemId());
                this.fXSDDescription.setLiteralSystemId(systemId);
                this.fXSDDescription.setExpandedSystemId(systemId);
                this.fXSDDescription.fLocationHints = new String[] { systemId };
            }
            final SchemaGrammar loadSchema = this.loadSchema(this.fXSDDescription, xsdToXMLInputSource, hashtable);
            if (loadSchema != null) {
                if (this.fJAXPSource instanceof InputStream || this.fJAXPSource instanceof InputSource) {
                    this.fJAXPCache.put(this.fJAXPSource, loadSchema);
                    if (this.fIsCheckedFully) {
                        XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fErrorReporter);
                    }
                }
                this.fGrammarBucket.putGrammar(loadSchema);
            }
            return;
        }
        if (componentType != ((XMLSchemaLoader.class$java$lang$Object == null) ? (XMLSchemaLoader.class$java$lang$Object = class$("java.lang.Object")) : XMLSchemaLoader.class$java$lang$Object) && componentType != ((XMLSchemaLoader.class$java$lang$String == null) ? (XMLSchemaLoader.class$java$lang$String = class$("java.lang.String")) : XMLSchemaLoader.class$java$lang$String) && componentType != ((XMLSchemaLoader.class$java$io$File == null) ? (XMLSchemaLoader.class$java$io$File = class$("java.io.File")) : XMLSchemaLoader.class$java$io$File) && componentType != ((XMLSchemaLoader.class$java$io$InputStream == null) ? (XMLSchemaLoader.class$java$io$InputStream = class$("java.io.InputStream")) : XMLSchemaLoader.class$java$io$InputStream) && componentType != ((XMLSchemaLoader.class$org$xml$sax$InputSource == null) ? (XMLSchemaLoader.class$org$xml$sax$InputSource = class$("org.xml.sax.InputSource")) : XMLSchemaLoader.class$org$xml$sax$InputSource)) {
            throw new XMLConfigurationException((short)1, "\"http://java.sun.com/xml/jaxp/properties/schemaSource\" property cannot have an array of type {" + componentType.getName() + "}. Possible types of the array supported are Object, String, File, " + "InputStream, InputSource.");
        }
        final Object[] array = (Object[])this.fJAXPSource;
        final Vector<String> vector = new Vector<String>();
        for (int i = 0; i < array.length; ++i) {
            if (array[i] instanceof InputStream || array[i] instanceof InputSource) {
                final SchemaGrammar schemaGrammar2 = this.fJAXPCache.get(array[i]);
                if (schemaGrammar2 != null) {
                    this.fGrammarBucket.putGrammar(schemaGrammar2);
                    continue;
                }
            }
            this.fXSDDescription.reset();
            final XMLInputSource xsdToXMLInputSource2 = this.xsdToXMLInputSource(array[i]);
            final String systemId2 = xsdToXMLInputSource2.getSystemId();
            this.fXSDDescription.fContextType = 3;
            if (systemId2 != null) {
                this.fXSDDescription.setBaseSystemId(xsdToXMLInputSource2.getBaseSystemId());
                this.fXSDDescription.setLiteralSystemId(systemId2);
                this.fXSDDescription.setExpandedSystemId(systemId2);
                this.fXSDDescription.fLocationHints = new String[] { systemId2 };
            }
            final SchemaGrammar schema = this.fSchemaHandler.parseSchema(xsdToXMLInputSource2, this.fXSDDescription, hashtable);
            if (this.fIsCheckedFully) {
                XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fErrorReporter);
            }
            if (schema != null) {
                final String targetNamespace = schema.getTargetNamespace();
                if (vector.contains(targetNamespace)) {
                    throw new IllegalArgumentException(" When using array of Objects as the value of SCHEMA_SOURCE property , no two Schemas should share the same targetNamespace. ");
                }
                vector.add(targetNamespace);
                if (array[i] instanceof InputStream || array[i] instanceof InputSource) {
                    this.fJAXPCache.put(array[i], schema);
                }
                this.fGrammarBucket.putGrammar(schema);
            }
        }
    }
    
    private XMLInputSource xsdToXMLInputSource(final Object o) {
        if (o instanceof String) {
            final String s = (String)o;
            this.fXSDDescription.reset();
            this.fXSDDescription.setValues(null, s, null, null);
            XMLInputSource resolveEntity = null;
            try {
                resolveEntity = this.fEntityManager.resolveEntity(this.fXSDDescription);
            }
            catch (IOException ex) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "schema_reference.4", new Object[] { s }, (short)1);
            }
            if (resolveEntity == null) {
                return new XMLInputSource(null, s, null);
            }
            return resolveEntity;
        }
        else {
            if (o instanceof InputSource) {
                return saxToXMLInputSource((InputSource)o);
            }
            if (o instanceof InputStream) {
                return new XMLInputSource(null, null, null, (InputStream)o, null);
            }
            if (o instanceof File) {
                final File file = (File)o;
                InputStream inputStream = null;
                try {
                    inputStream = new BufferedInputStream(new FileInputStream(file));
                }
                catch (FileNotFoundException ex2) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "schema_reference.4", new Object[] { file.toString() }, (short)1);
                }
                return new XMLInputSource(null, null, null, inputStream, null);
            }
            throw new XMLConfigurationException((short)1, "\"http://java.sun.com/xml/jaxp/properties/schemaSource\" property cannot have a value of type {" + o.getClass().getName() + "}. Possible types of the value supported are String, File, InputStream, " + "InputSource OR an array of these types.");
        }
    }
    
    private static XMLInputSource saxToXMLInputSource(final InputSource inputSource) {
        final String publicId = inputSource.getPublicId();
        final String systemId = inputSource.getSystemId();
        final Reader characterStream = inputSource.getCharacterStream();
        if (characterStream != null) {
            return new XMLInputSource(publicId, systemId, null, characterStream, null);
        }
        final InputStream byteStream = inputSource.getByteStream();
        if (byteStream != null) {
            return new XMLInputSource(publicId, systemId, null, byteStream, inputSource.getEncoding());
        }
        return new XMLInputSource(publicId, systemId, null);
    }
    
    public Boolean getFeatureDefault(final String s) {
        if (s.equals("http://apache.org/xml/features/validation/schema/augment-psvi")) {
            return Boolean.TRUE;
        }
        return null;
    }
    
    public Object getPropertyDefault(final String s) {
        return null;
    }
    
    public void reset(final XMLComponentManager xmlComponentManager) throws XMLConfigurationException {
        this.fGrammarBucket.reset();
        this.fSubGroupHandler.reset();
        boolean feature;
        try {
            feature = xmlComponentManager.getFeature("http://apache.org/xml/features/internal/parser-settings");
        }
        catch (XMLConfigurationException ex) {
            feature = true;
        }
        if (!feature || !this.fSettingsChanged) {
            this.fJAXPProcessed = false;
            this.initGrammarBucket();
            return;
        }
        this.fEntityManager = (XMLEntityManager)xmlComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-manager");
        this.fErrorReporter = (XMLErrorReporter)xmlComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        boolean feature2;
        try {
            feature2 = xmlComponentManager.getFeature("http://apache.org/xml/features/validation/schema/augment-psvi");
        }
        catch (XMLConfigurationException ex2) {
            feature2 = false;
        }
        if (!feature2) {
            this.fDeclPool.reset();
            this.fCMBuilder.setDeclPool(this.fDeclPool);
            this.fSchemaHandler.setDeclPool(this.fDeclPool);
        }
        else {
            this.fCMBuilder.setDeclPool(null);
            this.fSchemaHandler.setDeclPool(null);
        }
        try {
            this.fExternalSchemas = (String)xmlComponentManager.getProperty("http://apache.org/xml/properties/schema/external-schemaLocation");
            this.fExternalNoNSSchema = (String)xmlComponentManager.getProperty("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation");
        }
        catch (XMLConfigurationException ex3) {
            this.fExternalSchemas = null;
            this.fExternalNoNSSchema = null;
        }
        try {
            this.fJAXPSource = xmlComponentManager.getProperty("http://java.sun.com/xml/jaxp/properties/schemaSource");
            this.fJAXPProcessed = false;
        }
        catch (XMLConfigurationException ex4) {
            this.fJAXPSource = null;
            this.fJAXPProcessed = false;
        }
        try {
            this.fGrammarPool = (XMLGrammarPool)xmlComponentManager.getProperty("http://apache.org/xml/properties/internal/grammar-pool");
        }
        catch (XMLConfigurationException ex5) {
            this.fGrammarPool = null;
        }
        this.initGrammarBucket();
        try {
            this.fErrorReporter.setFeature("http://apache.org/xml/features/continue-after-fatal-error", xmlComponentManager.getFeature("http://apache.org/xml/features/continue-after-fatal-error"));
        }
        catch (XMLConfigurationException ex6) {}
        try {
            this.fIsCheckedFully = xmlComponentManager.getFeature("http://apache.org/xml/features/validation/schema-full-checking");
        }
        catch (XMLConfigurationException ex7) {
            this.fIsCheckedFully = false;
        }
        try {
            this.fSchemaHandler.setGenerateSyntheticAnnotations(xmlComponentManager.getFeature("http://apache.org/xml/features/generate-synthetic-annotations"));
        }
        catch (XMLConfigurationException ex8) {
            this.fSchemaHandler.setGenerateSyntheticAnnotations(false);
        }
        this.fSchemaHandler.reset(xmlComponentManager);
    }
    
    private void initGrammarBucket() {
        if (this.fGrammarPool != null) {
            final Grammar[] retrieveInitialGrammarSet = this.fGrammarPool.retrieveInitialGrammarSet("http://www.w3.org/2001/XMLSchema");
            for (int i = 0; i < retrieveInitialGrammarSet.length; ++i) {
                if (!this.fGrammarBucket.putGrammar((SchemaGrammar)retrieveInitialGrammarSet[i], true)) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "GrammarConflict", null, (short)0);
                }
            }
        }
    }
    
    public DOMConfiguration getConfig() {
        return this;
    }
    
    public XSModel load(final LSInput lsInput) {
        try {
            return ((XSGrammar)this.loadGrammar(this.dom2xmlInputSource(lsInput))).toXSModel();
        }
        catch (Exception ex) {
            this.reportDOMFatalError(ex);
            return null;
        }
    }
    
    public XSModel loadInputList(final LSInputList list) {
        final int length = list.getLength();
        if (length == 0) {
            return null;
        }
        final SchemaGrammar[] array = new SchemaGrammar[length];
        for (int i = 0; i < length; ++i) {
            try {
                array[i] = (SchemaGrammar)this.loadGrammar(this.dom2xmlInputSource(list.item(i)));
            }
            catch (Exception ex) {
                this.reportDOMFatalError(ex);
                return null;
            }
        }
        return new XSModelImpl(array);
    }
    
    public XSModel loadURI(final String s) {
        try {
            return ((XSGrammar)this.loadGrammar(new XMLInputSource(null, s, null))).toXSModel();
        }
        catch (Exception ex) {
            this.reportDOMFatalError(ex);
            return null;
        }
    }
    
    public XSModel loadURIList(final StringList list) {
        final int length = list.getLength();
        if (length == 0) {
            return null;
        }
        final SchemaGrammar[] array = new SchemaGrammar[length];
        for (int i = 0; i < length; ++i) {
            try {
                array[i] = (SchemaGrammar)this.loadGrammar(new XMLInputSource(null, list.item(i), null));
            }
            catch (Exception ex) {
                this.reportDOMFatalError(ex);
                return null;
            }
        }
        return new XSModelImpl(array);
    }
    
    void reportDOMFatalError(final Exception fException) {
        if (this.fErrorHandler != null) {
            final DOMErrorImpl domErrorImpl = new DOMErrorImpl();
            domErrorImpl.fException = fException;
            domErrorImpl.fMessage = fException.getMessage();
            domErrorImpl.fSeverity = 3;
            this.fErrorHandler.getErrorHandler().handleError(domErrorImpl);
        }
    }
    
    public boolean canSetParameter(final String s, final Object o) {
        if (o instanceof Boolean) {
            (boolean)o;
            return s.equals("validate") || s.equals("http://apache.org/xml/features/validation/schema-full-checking") || s.equals("http://apache.org/xml/features/validate-annotations") || s.equals("http://apache.org/xml/features/continue-after-fatal-error") || s.equals("http://apache.org/xml/features/allow-java-encodings") || s.equals("http://apache.org/xml/features/standard-uri-conformant") || s.equals("http://apache.org/xml/features/generate-synthetic-annotations") || s.equals("http://apache.org/xml/features/honour-all-schemaLocations");
        }
        return s.equals("error-handler") || s.equals("resource-resolver") || s.equals("http://apache.org/xml/properties/internal/symbol-table") || s.equals("http://apache.org/xml/properties/internal/error-reporter") || s.equals("http://apache.org/xml/properties/internal/error-handler") || s.equals("http://apache.org/xml/properties/internal/entity-resolver") || s.equals("http://apache.org/xml/properties/internal/grammar-pool") || s.equals("http://apache.org/xml/properties/schema/external-schemaLocation") || s.equals("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation") || s.equals("http://java.sun.com/xml/jaxp/properties/schemaSource");
    }
    
    public Object getParameter(final String s) throws DOMException {
        if (s.equals("error-handler")) {
            return (this.fErrorHandler != null) ? this.fErrorHandler.getErrorHandler() : null;
        }
        if (s.equals("resource-resolver")) {
            return (this.fResourceResolver != null) ? this.fResourceResolver.getEntityResolver() : null;
        }
        try {
            return this.getFeature(s) ? Boolean.TRUE : Boolean.FALSE;
        }
        catch (Exception ex) {
            try {
                return this.getProperty(s);
            }
            catch (Exception ex2) {
                throw new DOMException((short)9, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { s }));
            }
        }
    }
    
    public DOMStringList getParameterNames() {
        if (this.fRecognizedParameters == null) {
            final Vector<String> vector = new Vector<String>();
            vector.add("validate");
            vector.add("error-handler");
            vector.add("resource-resolver");
            vector.add("http://apache.org/xml/properties/internal/symbol-table");
            vector.add("http://apache.org/xml/properties/internal/error-reporter");
            vector.add("http://apache.org/xml/properties/internal/error-handler");
            vector.add("http://apache.org/xml/properties/internal/entity-resolver");
            vector.add("http://apache.org/xml/properties/internal/grammar-pool");
            vector.add("http://apache.org/xml/properties/schema/external-schemaLocation");
            vector.add("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation");
            vector.add("http://java.sun.com/xml/jaxp/properties/schemaSource");
            vector.add("http://apache.org/xml/features/validation/schema-full-checking");
            vector.add("http://apache.org/xml/features/continue-after-fatal-error");
            vector.add("http://apache.org/xml/features/allow-java-encodings");
            vector.add("http://apache.org/xml/features/standard-uri-conformant");
            vector.add("http://apache.org/xml/features/validate-annotations");
            vector.add("http://apache.org/xml/features/generate-synthetic-annotations");
            vector.add("http://apache.org/xml/features/honour-all-schemaLocations");
            this.fRecognizedParameters = new DOMStringListImpl(vector);
        }
        return this.fRecognizedParameters;
    }
    
    public void setParameter(final String s, final Object o) throws DOMException {
        if (o instanceof Boolean) {
            final boolean booleanValue = (boolean)o;
            if (s.equals("validate") && booleanValue) {
                return;
            }
            try {
                this.setFeature(s, booleanValue);
            }
            catch (Exception ex) {
                throw new DOMException((short)9, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { s }));
            }
        }
        else if (s.equals("error-handler")) {
            if (o instanceof DOMErrorHandler) {
                try {
                    this.setErrorHandler(this.fErrorHandler = new DOMErrorHandlerWrapper((DOMErrorHandler)o));
                }
                catch (XMLConfigurationException ex2) {}
                return;
            }
            throw new DOMException((short)9, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { s }));
        }
        else {
            if (!s.equals("resource-resolver")) {
                try {
                    this.setProperty(s, o);
                }
                catch (Exception ex3) {
                    throw new DOMException((short)9, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { s }));
                }
                return;
            }
            if (o instanceof LSResourceResolver) {
                try {
                    this.setEntityResolver(this.fResourceResolver = new DOMEntityResolverWrapper((LSResourceResolver)o));
                }
                catch (XMLConfigurationException ex4) {}
                return;
            }
            throw new DOMException((short)9, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { s }));
        }
    }
    
    XMLInputSource dom2xmlInputSource(final LSInput lsInput) {
        XMLInputSource xmlInputSource;
        if (lsInput.getCharacterStream() != null) {
            xmlInputSource = new XMLInputSource(lsInput.getPublicId(), lsInput.getSystemId(), lsInput.getBaseURI(), lsInput.getCharacterStream(), "UTF-16");
        }
        else if (lsInput.getByteStream() != null) {
            xmlInputSource = new XMLInputSource(lsInput.getPublicId(), lsInput.getSystemId(), lsInput.getBaseURI(), lsInput.getByteStream(), lsInput.getEncoding());
        }
        else if (lsInput.getStringData() != null && lsInput.getStringData().length() != 0) {
            xmlInputSource = new XMLInputSource(lsInput.getPublicId(), lsInput.getSystemId(), lsInput.getBaseURI(), new StringReader(lsInput.getStringData()), "UTF-16");
        }
        else {
            xmlInputSource = new XMLInputSource(lsInput.getPublicId(), lsInput.getSystemId(), lsInput.getBaseURI());
        }
        return xmlInputSource;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        RECOGNIZED_FEATURES = new String[] { "http://apache.org/xml/features/validation/schema-full-checking", "http://apache.org/xml/features/validation/schema/augment-psvi", "http://apache.org/xml/features/continue-after-fatal-error", "http://apache.org/xml/features/allow-java-encodings", "http://apache.org/xml/features/standard-uri-conformant", "http://apache.org/xml/features/disallow-doctype-decl", "http://apache.org/xml/features/generate-synthetic-annotations", "http://apache.org/xml/features/validate-annotations", "http://apache.org/xml/features/honour-all-schemaLocations" };
        RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/entity-manager", "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/error-handler", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/grammar-pool", "http://apache.org/xml/properties/schema/external-schemaLocation", "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", "http://java.sun.com/xml/jaxp/properties/schemaSource", "http://apache.org/xml/properties/security-manager" };
    }
    
    static class LocationArray
    {
        int length;
        String[] locations;
        
        LocationArray() {
            this.locations = new String[2];
        }
        
        public void resize(final int n, final int n2) {
            final String[] locations = new String[n2];
            System.arraycopy(this.locations, 0, locations, 0, Math.min(n, n2));
            this.locations = locations;
            this.length = Math.min(n, n2);
        }
        
        public void addLocation(final String s) {
            if (this.length >= this.locations.length) {
                this.resize(this.length, Math.max(1, this.length * 2));
            }
            this.locations[this.length++] = s;
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
