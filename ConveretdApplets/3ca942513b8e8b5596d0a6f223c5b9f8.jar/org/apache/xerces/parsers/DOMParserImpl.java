// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.parser.XMLDTDSource;
import org.apache.xerces.xni.parser.XMLDTDContentModelSource;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.QName;
import java.io.Reader;
import java.io.StringReader;
import org.w3c.dom.Node;
import org.apache.xerces.xni.XMLDTDContentModelHandler;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSException;
import org.w3c.dom.DOMError;
import org.apache.xerces.dom.DOMErrorImpl;
import org.apache.xerces.xni.parser.XMLParseException;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.w3c.dom.Document;
import org.apache.xerces.dom.DOMStringListImpl;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import java.util.StringTokenizer;
import org.apache.xerces.util.DOMEntityResolverWrapper;
import org.w3c.dom.ls.LSResourceResolver;
import org.apache.xerces.util.DOMErrorHandlerWrapper;
import org.w3c.dom.DOMErrorHandler;
import java.util.Locale;
import org.w3c.dom.DOMException;
import org.apache.xerces.dom.DOMMessageFormatter;
import java.util.Stack;
import org.w3c.dom.ls.LSParserFilter;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.impl.Constants;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.w3c.dom.DOMStringList;
import java.util.Vector;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.ls.LSParser;

public class DOMParserImpl extends AbstractDOMParser implements LSParser, DOMConfiguration
{
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
    protected static final String XMLSCHEMA = "http://apache.org/xml/features/validation/schema";
    protected static final String XMLSCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    protected static final String DYNAMIC_VALIDATION = "http://apache.org/xml/features/validation/dynamic";
    protected static final String NORMALIZE_DATA = "http://apache.org/xml/features/validation/schema/normalized-value";
    protected static final String DISALLOW_DOCTYPE_DECL_FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String PSVI_AUGMENT = "http://apache.org/xml/features/validation/schema/augment-psvi";
    protected boolean fNamespaceDeclarations;
    protected String fSchemaType;
    protected boolean fBusy;
    private boolean exitNow;
    private Thread currThread;
    protected static final boolean DEBUG = false;
    private Vector fSchemaLocations;
    private String fSchemaLocation;
    private DOMStringList fRecognizedParameters;
    private AbortHandler abortHandler;
    
    public DOMParserImpl(final String s, final String s2) {
        this((XMLParserConfiguration)ObjectFactory.createObject("org.apache.xerces.xni.parser.XMLParserConfiguration", s));
        if (s2 != null) {
            if (s2.equals(Constants.NS_DTD)) {
                super.fConfiguration.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", Constants.NS_DTD);
                this.fSchemaType = Constants.NS_DTD;
            }
            else if (s2.equals(Constants.NS_XMLSCHEMA)) {
                super.fConfiguration.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", Constants.NS_XMLSCHEMA);
            }
        }
    }
    
    public DOMParserImpl(final XMLParserConfiguration xmlParserConfiguration) {
        super(xmlParserConfiguration);
        this.fNamespaceDeclarations = true;
        this.fSchemaType = null;
        this.fBusy = false;
        this.exitNow = false;
        this.fSchemaLocations = new Vector();
        this.fSchemaLocation = null;
        this.abortHandler = new AbortHandler();
        super.fConfiguration.addRecognizedFeatures(new String[] { "canonical-form", "cdata-sections", "charset-overrides-xml-encoding", "infoset", "namespace-declarations", "split-cdata-sections", "supported-media-types-only", "certified", "well-formed", "ignore-unknown-character-denormalizations" });
        super.fConfiguration.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false);
        super.fConfiguration.setFeature("namespace-declarations", true);
        super.fConfiguration.setFeature("well-formed", true);
        super.fConfiguration.setFeature("http://apache.org/xml/features/include-comments", true);
        super.fConfiguration.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", true);
        super.fConfiguration.setFeature("http://xml.org/sax/features/namespaces", true);
        super.fConfiguration.setFeature("http://apache.org/xml/features/validation/dynamic", false);
        super.fConfiguration.setFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes", false);
        super.fConfiguration.setFeature("http://apache.org/xml/features/create-cdata-nodes", false);
        super.fConfiguration.setFeature("canonical-form", false);
        super.fConfiguration.setFeature("charset-overrides-xml-encoding", true);
        super.fConfiguration.setFeature("split-cdata-sections", true);
        super.fConfiguration.setFeature("supported-media-types-only", false);
        super.fConfiguration.setFeature("ignore-unknown-character-denormalizations", true);
        super.fConfiguration.setFeature("certified", true);
        try {
            super.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema/normalized-value", false);
        }
        catch (XMLConfigurationException ex) {}
    }
    
    public DOMParserImpl(final SymbolTable symbolTable) {
        this((XMLParserConfiguration)ObjectFactory.createObject("org.apache.xerces.xni.parser.XMLParserConfiguration", "org.apache.xerces.parsers.XIncludeAwareParserConfiguration"));
        super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
    }
    
    public DOMParserImpl(final SymbolTable symbolTable, final XMLGrammarPool xmlGrammarPool) {
        this((XMLParserConfiguration)ObjectFactory.createObject("org.apache.xerces.xni.parser.XMLParserConfiguration", "org.apache.xerces.parsers.XIncludeAwareParserConfiguration"));
        super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
        super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/grammar-pool", xmlGrammarPool);
    }
    
    public void reset() {
        super.reset();
        this.fNamespaceDeclarations = super.fConfiguration.getFeature("namespace-declarations");
        if (super.fSkippedElemStack != null) {
            super.fSkippedElemStack.removeAllElements();
        }
        this.fSchemaLocations.clear();
        super.fRejectedElement.clear();
        super.fFilterReject = false;
        this.fSchemaType = null;
    }
    
    public DOMConfiguration getDomConfig() {
        return this;
    }
    
    public LSParserFilter getFilter() {
        return super.fDOMFilter;
    }
    
    public void setFilter(final LSParserFilter fdomFilter) {
        super.fDOMFilter = fdomFilter;
        if (super.fSkippedElemStack == null) {
            super.fSkippedElemStack = new Stack();
        }
    }
    
    public void setParameter(final String s, final Object o) throws DOMException {
        if (o instanceof Boolean) {
            final boolean booleanValue = (boolean)o;
            try {
                if (s.equalsIgnoreCase("comments")) {
                    super.fConfiguration.setFeature("http://apache.org/xml/features/include-comments", booleanValue);
                }
                else if (s.equalsIgnoreCase("datatype-normalization")) {
                    super.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema/normalized-value", booleanValue);
                }
                else if (s.equalsIgnoreCase("entities")) {
                    super.fConfiguration.setFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes", booleanValue);
                }
                else if (s.equalsIgnoreCase("disallow-doctype")) {
                    super.fConfiguration.setFeature("http://apache.org/xml/features/disallow-doctype-decl", booleanValue);
                }
                else if (s.equalsIgnoreCase("supported-media-types-only") || s.equalsIgnoreCase("normalize-characters") || s.equalsIgnoreCase("check-character-normalization") || s.equalsIgnoreCase("canonical-form")) {
                    if (booleanValue) {
                        throw new DOMException((short)9, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { s }));
                    }
                }
                else if (s.equalsIgnoreCase("namespaces")) {
                    super.fConfiguration.setFeature("http://xml.org/sax/features/namespaces", booleanValue);
                }
                else if (s.equalsIgnoreCase("infoset")) {
                    if (booleanValue) {
                        super.fConfiguration.setFeature("http://xml.org/sax/features/namespaces", true);
                        super.fConfiguration.setFeature("namespace-declarations", true);
                        super.fConfiguration.setFeature("http://apache.org/xml/features/include-comments", true);
                        super.fConfiguration.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", true);
                        super.fConfiguration.setFeature("http://apache.org/xml/features/validation/dynamic", false);
                        super.fConfiguration.setFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes", false);
                        super.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema/normalized-value", false);
                        super.fConfiguration.setFeature("http://apache.org/xml/features/create-cdata-nodes", false);
                    }
                }
                else if (s.equalsIgnoreCase("cdata-sections")) {
                    super.fConfiguration.setFeature("http://apache.org/xml/features/create-cdata-nodes", booleanValue);
                }
                else if (s.equalsIgnoreCase("namespace-declarations")) {
                    super.fConfiguration.setFeature("namespace-declarations", booleanValue);
                }
                else if (s.equalsIgnoreCase("well-formed") || s.equalsIgnoreCase("ignore-unknown-character-denormalizations")) {
                    if (!booleanValue) {
                        throw new DOMException((short)9, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { s }));
                    }
                }
                else if (s.equalsIgnoreCase("validate")) {
                    super.fConfiguration.setFeature("http://xml.org/sax/features/validation", booleanValue);
                    if (this.fSchemaType != Constants.NS_DTD) {
                        super.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema", booleanValue);
                        super.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema-full-checking", booleanValue);
                    }
                    if (booleanValue) {
                        super.fConfiguration.setFeature("http://apache.org/xml/features/validation/dynamic", false);
                    }
                }
                else if (s.equalsIgnoreCase("validate-if-schema")) {
                    super.fConfiguration.setFeature("http://apache.org/xml/features/validation/dynamic", booleanValue);
                    if (booleanValue) {
                        super.fConfiguration.setFeature("http://xml.org/sax/features/validation", false);
                    }
                }
                else if (s.equalsIgnoreCase("element-content-whitespace")) {
                    super.fConfiguration.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", booleanValue);
                }
                else if (s.equalsIgnoreCase("psvi")) {
                    super.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema/augment-psvi", true);
                    super.fConfiguration.setProperty("http://apache.org/xml/properties/dom/document-class-name", "org.apache.xerces.dom.PSVIDocumentImpl");
                }
                else {
                    super.fConfiguration.setFeature(s.toLowerCase(Locale.ENGLISH), booleanValue);
                }
                return;
            }
            catch (XMLConfigurationException ex) {
                throw new DOMException((short)8, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_FOUND", new Object[] { s }));
            }
        }
        if (s.equalsIgnoreCase("error-handler")) {
            if (!(o instanceof DOMErrorHandler)) {
                if (o != null) {
                    throw new DOMException((short)9, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "TYPE_MISMATCH_ERR", new Object[] { s }));
                }
            }
            try {
                super.fErrorHandler = new DOMErrorHandlerWrapper((DOMErrorHandler)o);
                super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/error-handler", super.fErrorHandler);
            }
            catch (XMLConfigurationException ex2) {}
        }
        else if (s.equalsIgnoreCase("resource-resolver")) {
            if (!(o instanceof LSResourceResolver)) {
                if (o != null) {
                    throw new DOMException((short)9, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "TYPE_MISMATCH_ERR", new Object[] { s }));
                }
            }
            try {
                super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/entity-resolver", new DOMEntityResolverWrapper((LSResourceResolver)o));
            }
            catch (XMLConfigurationException ex3) {}
        }
        else if (s.equalsIgnoreCase("schema-location")) {
            if (!(o instanceof String)) {
                if (o != null) {
                    throw new DOMException((short)9, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "TYPE_MISMATCH_ERR", new Object[] { s }));
                }
            }
            try {
                if (o == null) {
                    this.fSchemaLocation = null;
                    super.fConfiguration.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", null);
                }
                else {
                    this.fSchemaLocation = (String)o;
                    final StringTokenizer stringTokenizer = new StringTokenizer(this.fSchemaLocation, " \n\t\r");
                    if (stringTokenizer.hasMoreTokens()) {
                        this.fSchemaLocations.clear();
                        this.fSchemaLocations.add(stringTokenizer.nextToken());
                        while (stringTokenizer.hasMoreTokens()) {
                            this.fSchemaLocations.add(stringTokenizer.nextToken());
                        }
                        super.fConfiguration.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", this.fSchemaLocations.toArray());
                    }
                    else {
                        super.fConfiguration.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", o);
                    }
                }
            }
            catch (XMLConfigurationException ex4) {}
        }
        else if (s.equalsIgnoreCase("schema-type")) {
            if (!(o instanceof String)) {
                if (o != null) {
                    throw new DOMException((short)9, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "TYPE_MISMATCH_ERR", new Object[] { s }));
                }
            }
            try {
                if (o == null) {
                    super.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema", false);
                    super.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema-full-checking", false);
                    super.fConfiguration.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", null);
                    this.fSchemaType = null;
                }
                else if (o.equals(Constants.NS_XMLSCHEMA)) {
                    super.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema", true);
                    super.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);
                    super.fConfiguration.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", Constants.NS_XMLSCHEMA);
                    this.fSchemaType = Constants.NS_XMLSCHEMA;
                }
                else if (o.equals(Constants.NS_DTD)) {
                    super.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema", false);
                    super.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema-full-checking", false);
                    super.fConfiguration.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", Constants.NS_DTD);
                    this.fSchemaType = Constants.NS_DTD;
                }
            }
            catch (XMLConfigurationException ex5) {}
        }
        else {
            if (!s.equalsIgnoreCase("http://apache.org/xml/properties/dom/document-class-name")) {
                throw new DOMException((short)8, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_FOUND", new Object[] { s }));
            }
            super.fConfiguration.setProperty("http://apache.org/xml/properties/dom/document-class-name", o);
        }
    }
    
    public Object getParameter(final String s) throws DOMException {
        if (s.equalsIgnoreCase("comments")) {
            return super.fConfiguration.getFeature("http://apache.org/xml/features/include-comments") ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("datatype-normalization")) {
            return super.fConfiguration.getFeature("http://apache.org/xml/features/validation/schema/normalized-value") ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("entities")) {
            return super.fConfiguration.getFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes") ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("namespaces")) {
            return super.fConfiguration.getFeature("http://xml.org/sax/features/namespaces") ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("validate")) {
            return super.fConfiguration.getFeature("http://xml.org/sax/features/validation") ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("validate-if-schema")) {
            return super.fConfiguration.getFeature("http://apache.org/xml/features/validation/dynamic") ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("element-content-whitespace")) {
            return super.fConfiguration.getFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace") ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("disallow-doctype")) {
            return super.fConfiguration.getFeature("http://apache.org/xml/features/disallow-doctype-decl") ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("infoset")) {
            return (super.fConfiguration.getFeature("http://xml.org/sax/features/namespaces") && super.fConfiguration.getFeature("namespace-declarations") && super.fConfiguration.getFeature("http://apache.org/xml/features/include-comments") && super.fConfiguration.getFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace") && !super.fConfiguration.getFeature("http://apache.org/xml/features/validation/dynamic") && !super.fConfiguration.getFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes") && !super.fConfiguration.getFeature("http://apache.org/xml/features/validation/schema/normalized-value") && !super.fConfiguration.getFeature("http://apache.org/xml/features/create-cdata-nodes")) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("cdata-sections")) {
            return super.fConfiguration.getFeature("http://apache.org/xml/features/create-cdata-nodes") ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("check-character-normalization") || s.equalsIgnoreCase("normalize-characters")) {
            return Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("namespace-declarations") || s.equalsIgnoreCase("well-formed") || s.equalsIgnoreCase("ignore-unknown-character-denormalizations") || s.equalsIgnoreCase("canonical-form") || s.equalsIgnoreCase("supported-media-types-only") || s.equalsIgnoreCase("split-cdata-sections") || s.equalsIgnoreCase("charset-overrides-xml-encoding")) {
            return super.fConfiguration.getFeature(s.toLowerCase(Locale.ENGLISH)) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("error-handler")) {
            if (super.fErrorHandler != null) {
                return super.fErrorHandler.getErrorHandler();
            }
            return null;
        }
        else {
            if (s.equalsIgnoreCase("resource-resolver")) {
                try {
                    final XMLEntityResolver xmlEntityResolver = (XMLEntityResolver)super.fConfiguration.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
                    if (xmlEntityResolver != null && xmlEntityResolver instanceof DOMEntityResolverWrapper) {
                        return ((DOMEntityResolverWrapper)xmlEntityResolver).getEntityResolver();
                    }
                    return null;
                }
                catch (XMLConfigurationException ex) {
                    return null;
                }
            }
            if (s.equalsIgnoreCase("schema-type")) {
                return super.fConfiguration.getProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage");
            }
            if (s.equalsIgnoreCase("schema-location")) {
                return this.fSchemaLocation;
            }
            if (s.equalsIgnoreCase("http://apache.org/xml/properties/internal/symbol-table")) {
                return super.fConfiguration.getProperty("http://apache.org/xml/properties/internal/symbol-table");
            }
            if (s.equalsIgnoreCase("http://apache.org/xml/properties/dom/document-class-name")) {
                return super.fConfiguration.getProperty("http://apache.org/xml/properties/dom/document-class-name");
            }
            throw new DOMException((short)8, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_FOUND", new Object[] { s }));
        }
    }
    
    public boolean canSetParameter(final String s, final Object o) {
        if (o instanceof Boolean) {
            final boolean booleanValue = (boolean)o;
            if (s.equalsIgnoreCase("supported-media-types-only") || s.equalsIgnoreCase("normalize-characters") || s.equalsIgnoreCase("check-character-normalization") || s.equalsIgnoreCase("canonical-form")) {
                return !booleanValue;
            }
            if (s.equalsIgnoreCase("well-formed") || s.equalsIgnoreCase("ignore-unknown-character-denormalizations")) {
                return booleanValue;
            }
            if (s.equalsIgnoreCase("cdata-sections") || s.equalsIgnoreCase("charset-overrides-xml-encoding") || s.equalsIgnoreCase("comments") || s.equalsIgnoreCase("datatype-normalization") || s.equalsIgnoreCase("disallow-doctype") || s.equalsIgnoreCase("entities") || s.equalsIgnoreCase("infoset") || s.equalsIgnoreCase("namespaces") || s.equalsIgnoreCase("namespace-declarations") || s.equalsIgnoreCase("validate") || s.equalsIgnoreCase("validate-if-schema") || s.equalsIgnoreCase("element-content-whitespace") || s.equalsIgnoreCase("xml-declaration")) {
                return true;
            }
            try {
                super.fConfiguration.getFeature(s.toLowerCase(Locale.ENGLISH));
                return true;
            }
            catch (XMLConfigurationException ex) {
                return false;
            }
        }
        if (s.equalsIgnoreCase("error-handler")) {
            return o instanceof DOMErrorHandler || o == null;
        }
        if (s.equalsIgnoreCase("resource-resolver")) {
            return o instanceof LSResourceResolver || o == null;
        }
        if (s.equalsIgnoreCase("schema-type")) {
            return (o instanceof String && (o.equals(Constants.NS_XMLSCHEMA) || o.equals(Constants.NS_DTD))) || o == null;
        }
        if (s.equalsIgnoreCase("schema-location")) {
            return o instanceof String || o == null;
        }
        return s.equalsIgnoreCase("http://apache.org/xml/properties/dom/document-class-name");
    }
    
    public DOMStringList getParameterNames() {
        if (this.fRecognizedParameters == null) {
            final Vector<String> vector = new Vector<String>();
            vector.add("namespaces");
            vector.add("cdata-sections");
            vector.add("canonical-form");
            vector.add("namespace-declarations");
            vector.add("split-cdata-sections");
            vector.add("entities");
            vector.add("validate-if-schema");
            vector.add("validate");
            vector.add("datatype-normalization");
            vector.add("charset-overrides-xml-encoding");
            vector.add("check-character-normalization");
            vector.add("supported-media-types-only");
            vector.add("ignore-unknown-character-denormalizations");
            vector.add("normalize-characters");
            vector.add("well-formed");
            vector.add("infoset");
            vector.add("disallow-doctype");
            vector.add("element-content-whitespace");
            vector.add("comments");
            vector.add("error-handler");
            vector.add("resource-resolver");
            vector.add("schema-location");
            vector.add("schema-type");
            this.fRecognizedParameters = new DOMStringListImpl(vector);
        }
        return this.fRecognizedParameters;
    }
    
    public Document parseURI(final String s) throws LSException {
        if (this.fBusy) {
            throw new DOMException((short)11, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_STATE_ERR", null));
        }
        final XMLInputSource xmlInputSource = new XMLInputSource(null, s, null);
        try {
            this.currThread = Thread.currentThread();
            this.fBusy = true;
            this.parse(xmlInputSource);
            this.fBusy = false;
        }
        catch (Exception fException) {
            this.fBusy = false;
            if (this.exitNow) {
                this.exitNow = false;
                this.restoreHandlers();
                return null;
            }
            if (fException != AbstractDOMParser.abort) {
                if (!(fException instanceof XMLParseException) && super.fErrorHandler != null) {
                    final DOMErrorImpl domErrorImpl = new DOMErrorImpl();
                    domErrorImpl.fException = fException;
                    domErrorImpl.fMessage = fException.getMessage();
                    domErrorImpl.fSeverity = 3;
                    super.fErrorHandler.getErrorHandler().handleError(domErrorImpl);
                }
                throw new LSException((short)81, fException.getMessage());
            }
        }
        return this.getDocument();
    }
    
    public Document parse(final LSInput lsInput) throws LSException {
        final XMLInputSource dom2xmlInputSource = this.dom2xmlInputSource(lsInput);
        if (this.fBusy) {
            throw new DOMException((short)11, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INVALID_STATE_ERR", null));
        }
        try {
            this.currThread = Thread.currentThread();
            this.fBusy = true;
            this.parse(dom2xmlInputSource);
            this.fBusy = false;
        }
        catch (Exception fException) {
            this.fBusy = false;
            if (this.exitNow) {
                this.exitNow = false;
                this.restoreHandlers();
                return null;
            }
            if (fException != AbstractDOMParser.abort) {
                if (!(fException instanceof XMLParseException) && super.fErrorHandler != null) {
                    final DOMErrorImpl domErrorImpl = new DOMErrorImpl();
                    domErrorImpl.fException = fException;
                    domErrorImpl.fMessage = fException.getMessage();
                    domErrorImpl.fSeverity = 3;
                    super.fErrorHandler.getErrorHandler().handleError(domErrorImpl);
                }
                throw new LSException((short)81, fException.getMessage());
            }
        }
        return this.getDocument();
    }
    
    private void restoreHandlers() {
        super.fConfiguration.setDocumentHandler(this);
        super.fConfiguration.setDTDHandler(this);
        super.fConfiguration.setDTDContentModelHandler(this);
    }
    
    public Node parseWithContext(final LSInput lsInput, final Node node, final short n) throws DOMException, LSException {
        throw new DOMException((short)9, "Not supported");
    }
    
    XMLInputSource dom2xmlInputSource(final LSInput lsInput) {
        XMLInputSource xmlInputSource;
        if (lsInput.getCharacterStream() != null) {
            xmlInputSource = new XMLInputSource(lsInput.getPublicId(), lsInput.getSystemId(), lsInput.getBaseURI(), lsInput.getCharacterStream(), "UTF-16");
        }
        else if (lsInput.getByteStream() != null) {
            xmlInputSource = new XMLInputSource(lsInput.getPublicId(), lsInput.getSystemId(), lsInput.getBaseURI(), lsInput.getByteStream(), lsInput.getEncoding());
        }
        else if (lsInput.getStringData() != null && lsInput.getStringData().length() > 0) {
            xmlInputSource = new XMLInputSource(lsInput.getPublicId(), lsInput.getSystemId(), lsInput.getBaseURI(), new StringReader(lsInput.getStringData()), "UTF-16");
        }
        else {
            if ((lsInput.getSystemId() == null || lsInput.getSystemId().length() <= 0) && (lsInput.getPublicId() == null || lsInput.getPublicId().length() <= 0)) {
                if (super.fErrorHandler != null) {
                    final DOMErrorImpl domErrorImpl = new DOMErrorImpl();
                    domErrorImpl.fType = "no-input-specified";
                    domErrorImpl.fMessage = "no-input-specified";
                    domErrorImpl.fSeverity = 3;
                    super.fErrorHandler.getErrorHandler().handleError(domErrorImpl);
                }
                throw new LSException((short)81, "no-input-specified");
            }
            xmlInputSource = new XMLInputSource(lsInput.getPublicId(), lsInput.getSystemId(), lsInput.getBaseURI());
        }
        return xmlInputSource;
    }
    
    public boolean getAsync() {
        return false;
    }
    
    public boolean getBusy() {
        return this.fBusy;
    }
    
    public void abort() {
        if (this.fBusy) {
            this.fBusy = false;
            if (this.currThread != null) {
                this.exitNow = true;
                if (this.currThread == Thread.currentThread()) {
                    throw AbstractDOMParser.abort;
                }
                super.fConfiguration.setDocumentHandler(this.abortHandler);
                super.fConfiguration.setDTDHandler(this.abortHandler);
                super.fConfiguration.setDTDContentModelHandler(this.abortHandler);
                this.currThread.interrupt();
            }
        }
    }
    
    public void startElement(final QName qName, final XMLAttributes xmlAttributes, final Augmentations augmentations) {
        if (!this.fNamespaceDeclarations && super.fNamespaceAware) {
            for (int i = xmlAttributes.getLength() - 1; i >= 0; --i) {
                if (XMLSymbols.PREFIX_XMLNS == xmlAttributes.getPrefix(i) || XMLSymbols.PREFIX_XMLNS == xmlAttributes.getQName(i)) {
                    xmlAttributes.removeAttributeAt(i);
                }
            }
        }
        super.startElement(qName, xmlAttributes, augmentations);
    }
    
    private class AbortHandler implements XMLDocumentHandler, XMLDTDHandler, XMLDTDContentModelHandler
    {
        private XMLDocumentSource documentSource;
        private XMLDTDContentModelSource dtdContentSource;
        private XMLDTDSource dtdSource;
        
        public void startDocument(final XMLLocator xmlLocator, final String s, final NamespaceContext namespaceContext, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void xmlDecl(final String s, final String s2, final String s3, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void doctypeDecl(final String s, final String s2, final String s3, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void comment(final XMLString xmlString, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void processingInstruction(final String s, final XMLString xmlString, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void startElement(final QName qName, final XMLAttributes xmlAttributes, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void emptyElement(final QName qName, final XMLAttributes xmlAttributes, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void startGeneralEntity(final String s, final XMLResourceIdentifier xmlResourceIdentifier, final String s2, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void textDecl(final String s, final String s2, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void endGeneralEntity(final String s, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void characters(final XMLString xmlString, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void ignorableWhitespace(final XMLString xmlString, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void endElement(final QName qName, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void startCDATA(final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void endCDATA(final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void endDocument(final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void setDocumentSource(final XMLDocumentSource documentSource) {
            this.documentSource = documentSource;
        }
        
        public XMLDocumentSource getDocumentSource() {
            return this.documentSource;
        }
        
        public void startDTD(final XMLLocator xmlLocator, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void startParameterEntity(final String s, final XMLResourceIdentifier xmlResourceIdentifier, final String s2, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void endParameterEntity(final String s, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void startExternalSubset(final XMLResourceIdentifier xmlResourceIdentifier, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void endExternalSubset(final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void elementDecl(final String s, final String s2, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void startAttlist(final String s, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void attributeDecl(final String s, final String s2, final String s3, final String[] array, final String s4, final XMLString xmlString, final XMLString xmlString2, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void endAttlist(final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void internalEntityDecl(final String s, final XMLString xmlString, final XMLString xmlString2, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void externalEntityDecl(final String s, final XMLResourceIdentifier xmlResourceIdentifier, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void unparsedEntityDecl(final String s, final XMLResourceIdentifier xmlResourceIdentifier, final String s2, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void notationDecl(final String s, final XMLResourceIdentifier xmlResourceIdentifier, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void startConditional(final short n, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void ignoredCharacters(final XMLString xmlString, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void endConditional(final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void endDTD(final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void setDTDSource(final XMLDTDSource dtdSource) {
            this.dtdSource = dtdSource;
        }
        
        public XMLDTDSource getDTDSource() {
            return this.dtdSource;
        }
        
        public void startContentModel(final String s, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void any(final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void empty(final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void startGroup(final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void pcdata(final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void element(final String s, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void separator(final short n, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void occurrence(final short n, final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void endGroup(final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void endContentModel(final Augmentations augmentations) throws XNIException {
            throw AbstractDOMParser.abort;
        }
        
        public void setDTDContentModelSource(final XMLDTDContentModelSource dtdContentSource) {
            this.dtdContentSource = dtdContentSource;
        }
        
        public XMLDTDContentModelSource getDTDContentModelSource() {
            return this.dtdContentSource;
        }
    }
}
