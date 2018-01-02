// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.util.Vector;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.impl.Constants;
import org.apache.xerces.util.DOMEntityResolverWrapper;
import org.w3c.dom.ls.LSResourceResolver;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.DOMException;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.XMLDTDContentModelHandler;
import org.apache.xerces.xni.XMLDTDHandler;
import java.io.IOException;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.impl.msg.XMLMessageFormatter;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.xni.parser.XMLComponent;
import java.util.HashMap;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.w3c.dom.DOMStringList;
import org.apache.xerces.impl.dv.DTDDVFactory;
import org.apache.xerces.util.DOMErrorHandlerWrapper;
import org.apache.xerces.impl.XMLErrorReporter;
import java.util.Locale;
import org.apache.xerces.impl.validation.ValidationManager;
import java.util.ArrayList;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.w3c.dom.DOMConfiguration;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.apache.xerces.util.ParserConfigurationSettings;

public class DOMConfigurationImpl extends ParserConfigurationSettings implements XMLParserConfiguration, DOMConfiguration
{
    protected static final String XML11_DATATYPE_VALIDATOR_FACTORY = "org.apache.xerces.impl.dv.dtd.XML11DTDDVFactoryImpl";
    protected static final String XERCES_VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String XERCES_NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String SCHEMA = "http://apache.org/xml/features/validation/schema";
    protected static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    protected static final String DYNAMIC_VALIDATION = "http://apache.org/xml/features/validation/dynamic";
    protected static final String NORMALIZE_DATA = "http://apache.org/xml/features/validation/schema/normalized-value";
    protected static final String SCHEMA_ELEMENT_DEFAULT = "http://apache.org/xml/features/validation/schema/element-default";
    protected static final String SEND_PSVI = "http://apache.org/xml/features/validation/schema/augment-psvi";
    protected static final String GENERATE_SYNTHETIC_ANNOTATIONS = "http://apache.org/xml/features/generate-synthetic-annotations";
    protected static final String VALIDATE_ANNOTATIONS = "http://apache.org/xml/features/validate-annotations";
    protected static final String HONOUR_ALL_SCHEMALOCATIONS = "http://apache.org/xml/features/honour-all-schemaLocations";
    protected static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
    protected static final String DISALLOW_DOCTYPE_DECL_FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
    protected static final String BALANCE_SYNTAX_TREES = "http://apache.org/xml/features/validation/balance-syntax-trees";
    protected static final String WARN_ON_DUPLICATE_ATTDEF = "http://apache.org/xml/features/validation/warn-on-duplicate-attdef";
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String XML_STRING = "http://xml.org/sax/properties/xml-string";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String GRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    protected static final String DTD_VALIDATOR_PROPERTY = "http://apache.org/xml/properties/internal/validator/dtd";
    protected static final String DTD_VALIDATOR_FACTORY_PROPERTY = "http://apache.org/xml/properties/internal/datatype-validator-factory";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    protected static final String SCHEMA_LOCATION = "http://apache.org/xml/properties/schema/external-schemaLocation";
    protected static final String SCHEMA_NONS_LOCATION = "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation";
    XMLDocumentHandler fDocumentHandler;
    protected short features;
    protected static final short NAMESPACES = 1;
    protected static final short DTNORMALIZATION = 2;
    protected static final short ENTITIES = 4;
    protected static final short CDATA = 8;
    protected static final short SPLITCDATA = 16;
    protected static final short COMMENTS = 32;
    protected static final short VALIDATE = 64;
    protected static final short PSVI = 128;
    protected static final short WELLFORMED = 256;
    protected static final short NSDECL = 512;
    protected static final short INFOSET_TRUE_PARAMS = 801;
    protected static final short INFOSET_FALSE_PARAMS = 14;
    protected static final short INFOSET_MASK = 815;
    protected SymbolTable fSymbolTable;
    protected ArrayList fComponents;
    protected ValidationManager fValidationManager;
    protected Locale fLocale;
    protected XMLErrorReporter fErrorReporter;
    protected final DOMErrorHandlerWrapper fErrorHandlerWrapper;
    protected DTDDVFactory fCurrentDVFactory;
    protected DTDDVFactory fDatatypeValidatorFactory;
    protected DTDDVFactory fXML11DatatypeFactory;
    private DOMStringList fRecognizedParameters;
    
    protected DOMConfigurationImpl() {
        this(null, null);
    }
    
    protected DOMConfigurationImpl(final SymbolTable symbolTable) {
        this(symbolTable, null);
    }
    
    protected DOMConfigurationImpl(SymbolTable fSymbolTable, final XMLComponentManager xmlComponentManager) {
        super(xmlComponentManager);
        this.features = 0;
        this.fErrorHandlerWrapper = new DOMErrorHandlerWrapper();
        super.fRecognizedFeatures = new ArrayList();
        super.fRecognizedProperties = new ArrayList();
        super.fFeatures = new HashMap();
        super.fProperties = new HashMap();
        this.addRecognizedFeatures(new String[] { "http://xml.org/sax/features/validation", "http://xml.org/sax/features/namespaces", "http://apache.org/xml/features/validation/schema", "http://apache.org/xml/features/validation/schema-full-checking", "http://apache.org/xml/features/validation/dynamic", "http://apache.org/xml/features/validation/schema/normalized-value", "http://apache.org/xml/features/validation/schema/element-default", "http://apache.org/xml/features/validation/schema/augment-psvi", "http://apache.org/xml/features/generate-synthetic-annotations", "http://apache.org/xml/features/validate-annotations", "http://apache.org/xml/features/honour-all-schemaLocations", "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only", "http://apache.org/xml/features/disallow-doctype-decl", "http://apache.org/xml/features/validation/balance-syntax-trees", "http://apache.org/xml/features/validation/warn-on-duplicate-attdef", "http://apache.org/xml/features/internal/parser-settings" });
        this.setFeature("http://xml.org/sax/features/validation", false);
        this.setFeature("http://apache.org/xml/features/validation/schema", false);
        this.setFeature("http://apache.org/xml/features/validation/schema-full-checking", false);
        this.setFeature("http://apache.org/xml/features/validation/dynamic", false);
        this.setFeature("http://apache.org/xml/features/validation/schema/normalized-value", false);
        this.setFeature("http://apache.org/xml/features/validation/schema/element-default", false);
        this.setFeature("http://xml.org/sax/features/namespaces", true);
        this.setFeature("http://apache.org/xml/features/validation/schema/augment-psvi", true);
        this.setFeature("http://apache.org/xml/features/generate-synthetic-annotations", false);
        this.setFeature("http://apache.org/xml/features/validate-annotations", false);
        this.setFeature("http://apache.org/xml/features/honour-all-schemaLocations", false);
        this.setFeature("http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only", false);
        this.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false);
        this.setFeature("http://apache.org/xml/features/validation/balance-syntax-trees", false);
        this.setFeature("http://apache.org/xml/features/validation/warn-on-duplicate-attdef", false);
        this.setFeature("http://apache.org/xml/features/internal/parser-settings", true);
        this.addRecognizedProperties(new String[] { "http://xml.org/sax/properties/xml-string", "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-handler", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-manager", "http://apache.org/xml/properties/internal/validation-manager", "http://apache.org/xml/properties/internal/grammar-pool", "http://java.sun.com/xml/jaxp/properties/schemaSource", "http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://apache.org/xml/properties/schema/external-schemaLocation", "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", "http://apache.org/xml/properties/internal/validator/dtd", "http://apache.org/xml/properties/internal/datatype-validator-factory" });
        this.features |= 0x1;
        this.features |= 0x4;
        this.features |= 0x20;
        this.features |= 0x8;
        this.features |= 0x10;
        this.features |= 0x100;
        this.features |= 0x200;
        if (fSymbolTable == null) {
            fSymbolTable = new SymbolTable();
        }
        this.fSymbolTable = fSymbolTable;
        this.fComponents = new ArrayList();
        this.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
        this.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter = new XMLErrorReporter());
        this.addComponent(this.fErrorReporter);
        this.fDatatypeValidatorFactory = DTDDVFactory.getInstance();
        this.fXML11DatatypeFactory = DTDDVFactory.getInstance("org.apache.xerces.impl.dv.dtd.XML11DTDDVFactoryImpl");
        this.setProperty("http://apache.org/xml/properties/internal/datatype-validator-factory", this.fCurrentDVFactory = this.fDatatypeValidatorFactory);
        final XMLEntityManager xmlEntityManager = new XMLEntityManager();
        this.setProperty("http://apache.org/xml/properties/internal/entity-manager", xmlEntityManager);
        this.addComponent(xmlEntityManager);
        this.setProperty("http://apache.org/xml/properties/internal/validation-manager", this.fValidationManager = this.createValidationManager());
        if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210") == null) {
            final XMLMessageFormatter xmlMessageFormatter = new XMLMessageFormatter();
            this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210", xmlMessageFormatter);
            this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1999/REC-xml-names-19990114", xmlMessageFormatter);
        }
        if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/xml-schema-1") == null) {
            MessageFormatter messageFormatter = null;
            try {
                messageFormatter = (MessageFormatter)ObjectFactory.newInstance("org.apache.xerces.impl.xs.XSMessageFormatter", ObjectFactory.findClassLoader(), true);
            }
            catch (Exception ex) {}
            if (messageFormatter != null) {
                this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/xml-schema-1", messageFormatter);
            }
        }
        try {
            this.setLocale(Locale.getDefault());
        }
        catch (XNIException ex2) {}
    }
    
    public void parse(final XMLInputSource xmlInputSource) throws XNIException, IOException {
    }
    
    public void setDocumentHandler(final XMLDocumentHandler fDocumentHandler) {
        this.fDocumentHandler = fDocumentHandler;
    }
    
    public XMLDocumentHandler getDocumentHandler() {
        return this.fDocumentHandler;
    }
    
    public void setDTDHandler(final XMLDTDHandler xmldtdHandler) {
    }
    
    public XMLDTDHandler getDTDHandler() {
        return null;
    }
    
    public void setDTDContentModelHandler(final XMLDTDContentModelHandler xmldtdContentModelHandler) {
    }
    
    public XMLDTDContentModelHandler getDTDContentModelHandler() {
        return null;
    }
    
    public void setEntityResolver(final XMLEntityResolver xmlEntityResolver) {
        if (xmlEntityResolver != null) {
            super.fProperties.put("http://apache.org/xml/properties/internal/entity-resolver", xmlEntityResolver);
        }
    }
    
    public XMLEntityResolver getEntityResolver() {
        return super.fProperties.get("http://apache.org/xml/properties/internal/entity-resolver");
    }
    
    public void setErrorHandler(final XMLErrorHandler xmlErrorHandler) {
        if (xmlErrorHandler != null) {
            super.fProperties.put("http://apache.org/xml/properties/internal/error-handler", xmlErrorHandler);
        }
    }
    
    public XMLErrorHandler getErrorHandler() {
        return super.fProperties.get("http://apache.org/xml/properties/internal/error-handler");
    }
    
    public boolean getFeature(final String s) throws XMLConfigurationException {
        return s.equals("http://apache.org/xml/features/internal/parser-settings") || super.getFeature(s);
    }
    
    public void setFeature(final String s, final boolean b) throws XMLConfigurationException {
        super.setFeature(s, b);
    }
    
    public void setProperty(final String s, final Object o) throws XMLConfigurationException {
        super.setProperty(s, o);
    }
    
    public void setLocale(final Locale locale) throws XNIException {
        this.fLocale = locale;
        this.fErrorReporter.setLocale(locale);
    }
    
    public Locale getLocale() {
        return this.fLocale;
    }
    
    public void setParameter(final String s, final Object o) throws DOMException {
        if (o instanceof Boolean) {
            final boolean booleanValue = (boolean)o;
            if (s.equalsIgnoreCase("comments")) {
                this.features = (booleanValue ? ((short)(this.features | 0x20)) : ((short)(this.features & 0xFFFFFFDF)));
            }
            else if (s.equalsIgnoreCase("datatype-normalization")) {
                this.setFeature("http://apache.org/xml/features/validation/schema/normalized-value", booleanValue);
                this.features = (booleanValue ? ((short)(this.features | 0x2)) : ((short)(this.features & 0xFFFFFFFD)));
                if (booleanValue) {
                    this.features |= 0x40;
                }
            }
            else if (s.equalsIgnoreCase("namespaces")) {
                this.features = (booleanValue ? ((short)(this.features | 0x1)) : ((short)(this.features & 0xFFFFFFFE)));
            }
            else if (s.equalsIgnoreCase("cdata-sections")) {
                this.features = (booleanValue ? ((short)(this.features | 0x8)) : ((short)(this.features & 0xFFFFFFF7)));
            }
            else if (s.equalsIgnoreCase("entities")) {
                this.features = (booleanValue ? ((short)(this.features | 0x4)) : ((short)(this.features & 0xFFFFFFFB)));
            }
            else if (s.equalsIgnoreCase("split-cdata-sections")) {
                this.features = (booleanValue ? ((short)(this.features | 0x10)) : ((short)(this.features & 0xFFFFFFEF)));
            }
            else if (s.equalsIgnoreCase("validate")) {
                this.features = (booleanValue ? ((short)(this.features | 0x40)) : ((short)(this.features & 0xFFFFFFBF)));
            }
            else if (s.equalsIgnoreCase("well-formed")) {
                this.features = (booleanValue ? ((short)(this.features | 0x100)) : ((short)(this.features & 0xFFFFFEFF)));
            }
            else if (s.equalsIgnoreCase("namespace-declarations")) {
                this.features = (booleanValue ? ((short)(this.features | 0x200)) : ((short)(this.features & 0xFFFFFDFF)));
            }
            else if (s.equalsIgnoreCase("infoset")) {
                if (booleanValue) {
                    this.features |= 0x321;
                    this.features &= 0xFFFFFFF1;
                    this.setFeature("http://apache.org/xml/features/validation/schema/normalized-value", false);
                }
            }
            else if (s.equalsIgnoreCase("normalize-characters") || s.equalsIgnoreCase("canonical-form") || s.equalsIgnoreCase("validate-if-schema") || s.equalsIgnoreCase("check-character-normalization")) {
                if (booleanValue) {
                    throw new DOMException((short)9, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { s }));
                }
            }
            else if (s.equalsIgnoreCase("element-content-whitespace")) {
                if (!booleanValue) {
                    throw new DOMException((short)9, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { s }));
                }
            }
            else if (s.equalsIgnoreCase("http://apache.org/xml/features/validation/schema/augment-psvi")) {
                if (!booleanValue) {
                    throw new DOMException((short)9, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { s }));
                }
            }
            else {
                if (!s.equalsIgnoreCase("psvi")) {
                    throw new DOMException((short)8, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_FOUND", new Object[] { s }));
                }
                this.features = (booleanValue ? ((short)(this.features | 0x80)) : ((short)(this.features & 0xFFFFFF7F)));
            }
        }
        else if (s.equalsIgnoreCase("error-handler")) {
            if (!(o instanceof DOMErrorHandler) && o != null) {
                throw new DOMException((short)9, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "TYPE_MISMATCH_ERR", new Object[] { s }));
            }
            this.fErrorHandlerWrapper.setErrorHandler((DOMErrorHandler)o);
            this.setErrorHandler(this.fErrorHandlerWrapper);
        }
        else if (s.equalsIgnoreCase("resource-resolver")) {
            if (!(o instanceof LSResourceResolver)) {
                if (o != null) {
                    throw new DOMException((short)9, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "TYPE_MISMATCH_ERR", new Object[] { s }));
                }
            }
            try {
                this.setEntityResolver(new DOMEntityResolverWrapper((LSResourceResolver)o));
            }
            catch (XMLConfigurationException ex) {}
        }
        else if (s.equalsIgnoreCase("schema-location")) {
            if (!(o instanceof String)) {
                if (o != null) {
                    throw new DOMException((short)9, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "TYPE_MISMATCH_ERR", new Object[] { s }));
                }
            }
            try {
                this.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", o);
            }
            catch (XMLConfigurationException ex2) {}
        }
        else if (s.equalsIgnoreCase("schema-type")) {
            if (!(o instanceof String)) {
                if (o != null) {
                    throw new DOMException((short)9, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "TYPE_MISMATCH_ERR", new Object[] { s }));
                }
            }
            try {
                if (o == null) {
                    this.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", null);
                }
                else if (o.equals(Constants.NS_XMLSCHEMA)) {
                    this.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", Constants.NS_XMLSCHEMA);
                }
                else if (o.equals(Constants.NS_DTD)) {
                    this.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", Constants.NS_DTD);
                }
            }
            catch (XMLConfigurationException ex3) {}
        }
        else if (s.equalsIgnoreCase("http://apache.org/xml/properties/internal/symbol-table")) {
            if (!(o instanceof SymbolTable)) {
                throw new DOMException((short)9, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "TYPE_MISMATCH_ERR", new Object[] { s }));
            }
            this.setProperty("http://apache.org/xml/properties/internal/symbol-table", o);
        }
        else {
            if (!s.equalsIgnoreCase("http://apache.org/xml/properties/internal/grammar-pool")) {
                throw new DOMException((short)8, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_FOUND", new Object[] { s }));
            }
            if (!(o instanceof XMLGrammarPool)) {
                throw new DOMException((short)9, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "TYPE_MISMATCH_ERR", new Object[] { s }));
            }
            this.setProperty("http://apache.org/xml/properties/internal/grammar-pool", o);
        }
    }
    
    public Object getParameter(final String s) throws DOMException {
        if (s.equalsIgnoreCase("comments")) {
            return ((this.features & 0x20) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("namespaces")) {
            return ((this.features & 0x1) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("datatype-normalization")) {
            return ((this.features & 0x2) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("cdata-sections")) {
            return ((this.features & 0x8) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("entities")) {
            return ((this.features & 0x4) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("split-cdata-sections")) {
            return ((this.features & 0x10) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("validate")) {
            return ((this.features & 0x40) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("well-formed")) {
            return ((this.features & 0x100) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("namespace-declarations")) {
            return ((this.features & 0x200) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("infoset")) {
            return ((this.features & 0x32F) == 0x321) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("normalize-characters") || s.equalsIgnoreCase("canonical-form") || s.equalsIgnoreCase("validate-if-schema") || s.equalsIgnoreCase("check-character-normalization")) {
            return Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("http://apache.org/xml/features/validation/schema/augment-psvi")) {
            return Boolean.TRUE;
        }
        if (s.equalsIgnoreCase("psvi")) {
            return ((this.features & 0x80) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("element-content-whitespace")) {
            return Boolean.TRUE;
        }
        if (s.equalsIgnoreCase("error-handler")) {
            return this.fErrorHandlerWrapper.getErrorHandler();
        }
        if (s.equalsIgnoreCase("resource-resolver")) {
            final XMLEntityResolver entityResolver = this.getEntityResolver();
            if (entityResolver != null && entityResolver instanceof DOMEntityResolverWrapper) {
                return ((DOMEntityResolverWrapper)entityResolver).getEntityResolver();
            }
            return null;
        }
        else {
            if (s.equalsIgnoreCase("schema-type")) {
                return this.getProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage");
            }
            if (s.equalsIgnoreCase("schema-location")) {
                return this.getProperty("http://java.sun.com/xml/jaxp/properties/schemaSource");
            }
            if (s.equalsIgnoreCase("http://apache.org/xml/properties/internal/symbol-table")) {
                return this.getProperty("http://apache.org/xml/properties/internal/symbol-table");
            }
            if (s.equalsIgnoreCase("http://apache.org/xml/properties/internal/grammar-pool")) {
                return this.getProperty("http://apache.org/xml/properties/internal/grammar-pool");
            }
            throw new DOMException((short)8, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_FOUND", new Object[] { s }));
        }
    }
    
    public boolean canSetParameter(final String s, final Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof Boolean) {
            if (s.equalsIgnoreCase("comments") || s.equalsIgnoreCase("datatype-normalization") || s.equalsIgnoreCase("cdata-sections") || s.equalsIgnoreCase("entities") || s.equalsIgnoreCase("split-cdata-sections") || s.equalsIgnoreCase("namespaces") || s.equalsIgnoreCase("validate") || s.equalsIgnoreCase("well-formed") || s.equalsIgnoreCase("infoset") || s.equalsIgnoreCase("namespace-declarations")) {
                return true;
            }
            if (s.equalsIgnoreCase("normalize-characters") || s.equalsIgnoreCase("canonical-form") || s.equalsIgnoreCase("validate-if-schema") || s.equalsIgnoreCase("check-character-normalization")) {
                return !o.equals(Boolean.TRUE);
            }
            return (s.equalsIgnoreCase("element-content-whitespace") || s.equalsIgnoreCase("http://apache.org/xml/features/validation/schema/augment-psvi")) && o.equals(Boolean.TRUE);
        }
        else {
            if (s.equalsIgnoreCase("error-handler")) {
                return o instanceof DOMErrorHandler;
            }
            if (s.equalsIgnoreCase("resource-resolver")) {
                return o instanceof LSResourceResolver;
            }
            if (s.equalsIgnoreCase("schema-location")) {
                return o instanceof String;
            }
            if (s.equalsIgnoreCase("schema-type")) {
                return o instanceof String && o.equals(Constants.NS_XMLSCHEMA);
            }
            if (s.equalsIgnoreCase("http://apache.org/xml/properties/internal/symbol-table")) {
                return o instanceof SymbolTable;
            }
            return s.equalsIgnoreCase("http://apache.org/xml/properties/internal/grammar-pool") && o instanceof XMLGrammarPool;
        }
    }
    
    public DOMStringList getParameterNames() {
        if (this.fRecognizedParameters == null) {
            final Vector<String> vector = new Vector<String>();
            vector.add("comments");
            vector.add("datatype-normalization");
            vector.add("cdata-sections");
            vector.add("entities");
            vector.add("split-cdata-sections");
            vector.add("namespaces");
            vector.add("validate");
            vector.add("infoset");
            vector.add("normalize-characters");
            vector.add("canonical-form");
            vector.add("validate-if-schema");
            vector.add("check-character-normalization");
            vector.add("well-formed");
            vector.add("namespace-declarations");
            vector.add("element-content-whitespace");
            vector.add("error-handler");
            vector.add("schema-type");
            vector.add("schema-location");
            vector.add("resource-resolver");
            vector.add("http://apache.org/xml/properties/internal/grammar-pool");
            vector.add("http://apache.org/xml/properties/internal/symbol-table");
            vector.add("http://apache.org/xml/features/validation/schema/augment-psvi");
            this.fRecognizedParameters = new DOMStringListImpl(vector);
        }
        return this.fRecognizedParameters;
    }
    
    protected void reset() throws XNIException {
        if (this.fValidationManager != null) {
            this.fValidationManager.reset();
        }
        for (int size = this.fComponents.size(), i = 0; i < size; ++i) {
            ((XMLComponent)this.fComponents.get(i)).reset(this);
        }
    }
    
    protected void checkProperty(final String s) throws XMLConfigurationException {
        if (s.startsWith("http://xml.org/sax/properties/") && s.length() - "http://xml.org/sax/properties/".length() == "xml-string".length() && s.endsWith("xml-string")) {
            throw new XMLConfigurationException((short)1, s);
        }
        super.checkProperty(s);
    }
    
    protected void addComponent(final XMLComponent xmlComponent) {
        if (this.fComponents.contains(xmlComponent)) {
            return;
        }
        this.fComponents.add(xmlComponent);
        this.addRecognizedFeatures(xmlComponent.getRecognizedFeatures());
        this.addRecognizedProperties(xmlComponent.getRecognizedProperties());
    }
    
    protected ValidationManager createValidationManager() {
        return new ValidationManager();
    }
    
    protected final void setDTDValidatorFactory(final String s) {
        if ("1.1".equals(s)) {
            if (this.fCurrentDVFactory != this.fXML11DatatypeFactory) {
                this.setProperty("http://apache.org/xml/properties/internal/datatype-validator-factory", this.fCurrentDVFactory = this.fXML11DatatypeFactory);
            }
        }
        else if (this.fCurrentDVFactory != this.fDatatypeValidatorFactory) {
            this.setProperty("http://apache.org/xml/properties/internal/datatype-validator-factory", this.fCurrentDVFactory = this.fDatatypeValidatorFactory);
        }
    }
}
