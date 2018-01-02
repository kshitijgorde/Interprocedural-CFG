// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.xni.grammars.XMLSchemaDescription;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.impl.xs.opti.ElementImpl;
import org.apache.xerces.xs.XSParticle;
import org.apache.xerces.impl.xs.XSGroupDecl;
import org.apache.xerces.impl.xs.XSModelGroupImpl;
import org.apache.xerces.impl.xs.XSComplexTypeDecl;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.impl.xs.XMLSchemaLoader;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.xni.QName;
import java.util.Stack;
import org.apache.xerces.util.URI;
import org.apache.xerces.impl.xs.SchemaNamespaceSupport;
import org.apache.xerces.impl.xs.XMLSchemaException;
import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.util.DefaultErrorHandler;
import java.io.Reader;
import java.io.StringReader;
import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.w3c.dom.Node;
import java.util.ArrayList;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.xml.sax.ContentHandler;
import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.SAXException;
import org.apache.xerces.util.SAXInputSource;
import org.apache.xerces.util.DOMUtil;
import org.w3c.dom.Document;
import org.apache.xerces.util.DOMInputSource;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.apache.xerces.impl.xs.opti.SchemaParsingConfig;
import org.apache.xerces.impl.xs.opti.SchemaDOM;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.impl.xs.util.SimpleLocator;
import org.apache.xerces.impl.xs.XSElementDecl;
import org.apache.xerces.xs.XSObject;
import org.w3c.dom.Element;
import org.apache.xerces.impl.xs.XSParticleDecl;
import org.apache.xerces.parsers.XML11Configuration;
import org.apache.xerces.impl.xs.opti.SchemaDOMParser;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.impl.xs.XSDDescription;
import org.apache.xerces.impl.xs.XSGrammarBucket;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.impl.XMLErrorReporter;
import java.util.Vector;
import org.apache.xerces.impl.xs.XSDeclarationPool;
import java.util.Hashtable;

public class XSDHandler
{
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String XMLSCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final String STANDARD_URI_CONFORMANT_FEATURE = "http://apache.org/xml/features/standard-uri-conformant";
    protected static final String DISALLOW_DOCTYPE = "http://apache.org/xml/features/disallow-doctype-decl";
    protected static final String GENERATE_SYNTHETIC_ANNOTATIONS = "http://apache.org/xml/features/generate-synthetic-annotations";
    protected static final String VALIDATE_ANNOTATIONS = "http://apache.org/xml/features/validate-annotations";
    protected static final String HONOUR_ALL_SCHEMALOCATIONS = "http://apache.org/xml/features/honour-all-schemaLocations";
    private static final String NAMESPACE_PREFIXES = "http://xml.org/sax/features/namespace-prefixes";
    protected static final String STRING_INTERNING = "http://xml.org/sax/features/string-interning";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    public static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    public static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    public static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    public static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    protected static final boolean DEBUG_NODE_POOL = false;
    static final int ATTRIBUTE_TYPE = 1;
    static final int ATTRIBUTEGROUP_TYPE = 2;
    static final int ELEMENT_TYPE = 3;
    static final int GROUP_TYPE = 4;
    static final int IDENTITYCONSTRAINT_TYPE = 5;
    static final int NOTATION_TYPE = 6;
    static final int TYPEDECL_TYPE = 7;
    public static final String REDEF_IDENTIFIER = "_fn3dktizrknc9pi";
    protected Hashtable fNotationRegistry;
    protected XSDeclarationPool fDeclPool;
    private Hashtable fUnparsedAttributeRegistry;
    private Hashtable fUnparsedAttributeGroupRegistry;
    private Hashtable fUnparsedElementRegistry;
    private Hashtable fUnparsedGroupRegistry;
    private Hashtable fUnparsedIdentityConstraintRegistry;
    private Hashtable fUnparsedNotationRegistry;
    private Hashtable fUnparsedTypeRegistry;
    private Hashtable fUnparsedAttributeRegistry_sub;
    private Hashtable fUnparsedAttributeGroupRegistry_sub;
    private Hashtable fUnparsedElementRegistry_sub;
    private Hashtable fUnparsedGroupRegistry_sub;
    private Hashtable fUnparsedIdentityConstraintRegistry_sub;
    private Hashtable fUnparsedNotationRegistry_sub;
    private Hashtable fUnparsedTypeRegistry_sub;
    private Hashtable fXSDocumentInfoRegistry;
    private Hashtable fDependencyMap;
    private Hashtable fImportMap;
    private Vector fAllTNSs;
    private Hashtable fLocationPairs;
    private static final Hashtable EMPTY_TABLE;
    private Hashtable fHiddenNodes;
    private Hashtable fTraversed;
    private Hashtable fDoc2SystemId;
    private XSDocumentInfo fRoot;
    private Hashtable fDoc2XSDocumentMap;
    private Hashtable fRedefine2XSDMap;
    private Hashtable fRedefine2NSSupport;
    private Hashtable fRedefinedRestrictedAttributeGroupRegistry;
    private Hashtable fRedefinedRestrictedGroupRegistry;
    private boolean fLastSchemaWasDuplicate;
    private boolean fValidateAnnotations;
    private boolean fHonourAllSchemaLocations;
    private XMLErrorReporter fErrorReporter;
    private XMLEntityResolver fEntityResolver;
    private XSAttributeChecker fAttributeChecker;
    private SymbolTable fSymbolTable;
    private XSGrammarBucket fGrammarBucket;
    private XSDDescription fSchemaGrammarDescription;
    private XMLGrammarPool fGrammarPool;
    XSDAttributeGroupTraverser fAttributeGroupTraverser;
    XSDAttributeTraverser fAttributeTraverser;
    XSDComplexTypeTraverser fComplexTypeTraverser;
    XSDElementTraverser fElementTraverser;
    XSDGroupTraverser fGroupTraverser;
    XSDKeyrefTraverser fKeyrefTraverser;
    XSDNotationTraverser fNotationTraverser;
    XSDSimpleTypeTraverser fSimpleTypeTraverser;
    XSDUniqueOrKeyTraverser fUniqueOrKeyTraverser;
    XSDWildcardTraverser fWildCardTraverser;
    SchemaDOMParser fSchemaParser;
    SchemaContentHandler fXSContentHandler;
    XML11Configuration fAnnotationValidator;
    XSAnnotationGrammarPool fGrammarBucketAdapter;
    private static final int INIT_STACK_SIZE = 30;
    private static final int INC_STACK_SIZE = 10;
    private int fLocalElemStackPos;
    private XSParticleDecl[] fParticle;
    private Element[] fLocalElementDecl;
    private XSDocumentInfo[] fLocalElementDecl_schema;
    private int[] fAllContext;
    private XSObject[] fParent;
    private String[][] fLocalElemNamespaceContext;
    private static final int INIT_KEYREF_STACK = 2;
    private static final int INC_KEYREF_STACK_AMOUNT = 2;
    private int fKeyrefStackPos;
    private Element[] fKeyrefs;
    private XSDocumentInfo[] fKeyrefsMapXSDocumentInfo;
    private XSElementDecl[] fKeyrefElems;
    private String[][] fKeyrefNamespaceContext;
    private static final String[][] NS_ERROR_CODES;
    private static final String[] ELE_ERROR_CODES;
    private Vector fReportedTNS;
    private static final String[] COMP_TYPE;
    private static final String[] CIRCULAR_CODES;
    private SimpleLocator xl;
    
    private String null2EmptyString(final String s) {
        return (s == null) ? XMLSymbols.EMPTY_STRING : s;
    }
    
    private String emptyString2Null(final String s) {
        return (s == XMLSymbols.EMPTY_STRING) ? null : s;
    }
    
    private String doc2SystemId(final Element element) {
        String documentURI = null;
        if (element.getOwnerDocument() instanceof SchemaDOM) {
            documentURI = ((SchemaDOM)element.getOwnerDocument()).getDocumentURI();
        }
        return (documentURI != null) ? documentURI : this.fDoc2SystemId.get(element);
    }
    
    public XSDHandler() {
        this.fNotationRegistry = new Hashtable();
        this.fDeclPool = null;
        this.fUnparsedAttributeRegistry = new Hashtable();
        this.fUnparsedAttributeGroupRegistry = new Hashtable();
        this.fUnparsedElementRegistry = new Hashtable();
        this.fUnparsedGroupRegistry = new Hashtable();
        this.fUnparsedIdentityConstraintRegistry = new Hashtable();
        this.fUnparsedNotationRegistry = new Hashtable();
        this.fUnparsedTypeRegistry = new Hashtable();
        this.fUnparsedAttributeRegistry_sub = new Hashtable();
        this.fUnparsedAttributeGroupRegistry_sub = new Hashtable();
        this.fUnparsedElementRegistry_sub = new Hashtable();
        this.fUnparsedGroupRegistry_sub = new Hashtable();
        this.fUnparsedIdentityConstraintRegistry_sub = new Hashtable();
        this.fUnparsedNotationRegistry_sub = new Hashtable();
        this.fUnparsedTypeRegistry_sub = new Hashtable();
        this.fXSDocumentInfoRegistry = new Hashtable();
        this.fDependencyMap = new Hashtable();
        this.fImportMap = new Hashtable();
        this.fAllTNSs = new Vector();
        this.fLocationPairs = null;
        this.fHiddenNodes = null;
        this.fTraversed = new Hashtable();
        this.fDoc2SystemId = new Hashtable();
        this.fRoot = null;
        this.fDoc2XSDocumentMap = new Hashtable();
        this.fRedefine2XSDMap = new Hashtable();
        this.fRedefine2NSSupport = new Hashtable();
        this.fRedefinedRestrictedAttributeGroupRegistry = new Hashtable();
        this.fRedefinedRestrictedGroupRegistry = new Hashtable();
        this.fValidateAnnotations = false;
        this.fHonourAllSchemaLocations = false;
        this.fLocalElemStackPos = 0;
        this.fParticle = new XSParticleDecl[30];
        this.fLocalElementDecl = new Element[30];
        this.fLocalElementDecl_schema = new XSDocumentInfo[30];
        this.fAllContext = new int[30];
        this.fParent = new XSObject[30];
        this.fLocalElemNamespaceContext = new String[30][1];
        this.fKeyrefStackPos = 0;
        this.fKeyrefs = new Element[2];
        this.fKeyrefsMapXSDocumentInfo = new XSDocumentInfo[2];
        this.fKeyrefElems = new XSElementDecl[2];
        this.fKeyrefNamespaceContext = new String[2][1];
        this.fReportedTNS = null;
        this.xl = new SimpleLocator();
        this.fHiddenNodes = new Hashtable();
        this.fSchemaParser = new SchemaDOMParser(new SchemaParsingConfig());
    }
    
    public XSDHandler(final XSGrammarBucket fGrammarBucket) {
        this();
        this.fGrammarBucket = fGrammarBucket;
        this.fSchemaGrammarDescription = new XSDDescription();
    }
    
    public SchemaGrammar parseSchema(final XMLInputSource xmlInputSource, final XSDDescription xsdDescription, final Hashtable fLocationPairs) throws IOException {
        this.fLocationPairs = fLocationPairs;
        this.fSchemaParser.resetNodePool();
        String s = null;
        final short contextType = xsdDescription.getContextType();
        if (contextType != 3) {
            SchemaGrammar schemaGrammar;
            if (this.fHonourAllSchemaLocations && contextType == 2 && this.isExistingGrammar(xsdDescription)) {
                schemaGrammar = this.fGrammarBucket.getGrammar(xsdDescription.getTargetNamespace());
            }
            else {
                schemaGrammar = this.findGrammar(xsdDescription);
            }
            if (schemaGrammar != null) {
                return schemaGrammar;
            }
            s = xsdDescription.getTargetNamespace();
            if (s != null) {
                s = this.fSymbolTable.addSymbol(s);
            }
        }
        this.prepareForParse();
        Element element;
        if (xmlInputSource instanceof DOMInputSource) {
            this.fHiddenNodes.clear();
            final Node node = ((DOMInputSource)xmlInputSource).getNode();
            if (node instanceof Document) {
                element = DOMUtil.getRoot((Document)node);
            }
            else {
                if (!(node instanceof Element)) {
                    return null;
                }
                element = (Element)node;
            }
        }
        else if (xmlInputSource instanceof SAXInputSource) {
            XMLReader xmlReader = ((SAXInputSource)xmlInputSource).getXMLReader();
            final InputSource inputSource = ((SAXInputSource)xmlInputSource).getInputSource();
            boolean feature = false;
            if (xmlReader != null) {
                try {
                    feature = xmlReader.getFeature("http://xml.org/sax/features/namespace-prefixes");
                }
                catch (SAXException ex) {}
            }
            else {
                try {
                    xmlReader = XMLReaderFactory.createXMLReader();
                }
                catch (SAXException ex2) {
                    xmlReader = new SAXParser();
                }
                try {
                    xmlReader.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
                    feature = true;
                }
                catch (SAXException ex3) {}
            }
            boolean feature2 = false;
            try {
                feature2 = xmlReader.getFeature("http://xml.org/sax/features/string-interning");
            }
            catch (SAXException ex4) {}
            if (this.fXSContentHandler == null) {
                this.fXSContentHandler = new SchemaContentHandler();
            }
            this.fXSContentHandler.reset(this.fSchemaParser, this.fSymbolTable, feature, feature2);
            xmlReader.setContentHandler(this.fXSContentHandler);
            try {
                xmlReader.parse(inputSource);
            }
            catch (SAXException ex5) {
                return null;
            }
            final Document document = this.fXSContentHandler.getDocument();
            if (document == null) {
                return null;
            }
            element = DOMUtil.getRoot(document);
        }
        else {
            element = this.getSchemaDocument(s, xmlInputSource, contextType == 3, contextType, null);
        }
        if (element == null) {
            return null;
        }
        if (contextType == 3) {
            final String attrValue = DOMUtil.getAttrValue(element, SchemaSymbols.ATT_TARGETNAMESPACE);
            String addSymbol;
            if (attrValue != null && attrValue.length() > 0) {
                addSymbol = this.fSymbolTable.addSymbol(attrValue);
                xsdDescription.setTargetNamespace(addSymbol);
            }
            else {
                addSymbol = null;
            }
            final SchemaGrammar grammar = this.findGrammar(xsdDescription);
            if (grammar != null) {
                return grammar;
            }
            final String expandSystemId = XMLEntityManager.expandSystemId(xmlInputSource.getSystemId(), xmlInputSource.getBaseSystemId(), false);
            this.fTraversed.put(new XSDKey(expandSystemId, contextType, addSymbol), element);
            if (expandSystemId != null) {
                this.fDoc2SystemId.put(element, expandSystemId);
            }
        }
        this.prepareForTraverse();
        this.fRoot = this.constructTrees(element, xmlInputSource.getSystemId(), xsdDescription);
        if (this.fRoot == null) {
            return null;
        }
        this.buildGlobalNameRegistries();
        final ArrayList list = this.fValidateAnnotations ? new ArrayList() : null;
        this.traverseSchemas(list);
        this.traverseLocalElements();
        this.resolveKeyRefs();
        for (int i = this.fAllTNSs.size() - 1; i >= 0; --i) {
            final String s2 = this.fAllTNSs.elementAt(i);
            final Vector<String> importedGrammars = this.fImportMap.get(s2);
            final SchemaGrammar grammar2 = this.fGrammarBucket.getGrammar(this.emptyString2Null(s2));
            if (grammar2 != null) {
                int size = 0;
                for (int j = 0; j < importedGrammars.size(); ++j) {
                    final SchemaGrammar grammar3 = this.fGrammarBucket.getGrammar(importedGrammars.elementAt(j));
                    if (grammar3 != null) {
                        importedGrammars.setElementAt((String)grammar3, size++);
                    }
                }
                importedGrammars.setSize(size);
                grammar2.setImportedGrammars(importedGrammars);
            }
        }
        if (this.fValidateAnnotations && list.size() > 0) {
            this.validateAnnotations(list);
        }
        return this.fGrammarBucket.getGrammar(this.fRoot.fTargetNamespace);
    }
    
    private void validateAnnotations(final ArrayList list) {
        if (this.fAnnotationValidator == null) {
            this.createAnnotationValidator();
        }
        final int size = list.size();
        final XMLInputSource xmlInputSource = new XMLInputSource(null, null, null);
        this.fGrammarBucketAdapter.refreshGrammars(this.fGrammarBucket);
        for (int i = 0; i < size; i += 2) {
            xmlInputSource.setSystemId(list.get(i));
            for (XSAnnotationInfo next = (XSAnnotationInfo)list.get(i + 1); next != null; next = next.next) {
                xmlInputSource.setCharacterStream(new StringReader(next.fAnnotation));
                try {
                    this.fAnnotationValidator.parse(xmlInputSource);
                }
                catch (IOException ex) {}
            }
        }
    }
    
    private void createAnnotationValidator() {
        this.fAnnotationValidator = new XML11Configuration();
        this.fGrammarBucketAdapter = new XSAnnotationGrammarPool();
        this.fAnnotationValidator.setFeature("http://xml.org/sax/features/validation", true);
        this.fAnnotationValidator.setFeature("http://apache.org/xml/features/validation/schema", true);
        this.fAnnotationValidator.setProperty("http://apache.org/xml/properties/internal/grammar-pool", this.fGrammarBucketAdapter);
        final XMLErrorHandler errorHandler = this.fErrorReporter.getErrorHandler();
        this.fAnnotationValidator.setProperty("http://apache.org/xml/properties/internal/error-handler", (errorHandler != null) ? errorHandler : new DefaultErrorHandler());
    }
    
    SchemaGrammar getGrammar(final String s) {
        return this.fGrammarBucket.getGrammar(s);
    }
    
    protected SchemaGrammar findGrammar(final XSDDescription xsdDescription) {
        SchemaGrammar grammar = this.fGrammarBucket.getGrammar(xsdDescription.getTargetNamespace());
        if (grammar == null && this.fGrammarPool != null) {
            grammar = (SchemaGrammar)this.fGrammarPool.retrieveGrammar(xsdDescription);
            if (grammar != null && !this.fGrammarBucket.putGrammar(grammar, true)) {
                this.reportSchemaWarning("GrammarConflict", null, null);
                grammar = null;
            }
        }
        return grammar;
    }
    
    protected XSDocumentInfo constructTrees(final Element element, final String s, final XSDDescription xsdDescription) {
        if (element == null) {
            return null;
        }
        String s2 = xsdDescription.getTargetNamespace();
        final short contextType = xsdDescription.getContextType();
        XSDocumentInfo xsDocumentInfo;
        try {
            xsDocumentInfo = new XSDocumentInfo(element, this.fAttributeChecker, this.fSymbolTable);
        }
        catch (XMLSchemaException ex) {
            this.reportSchemaError(XSDHandler.ELE_ERROR_CODES[contextType], new Object[] { s }, element);
            return null;
        }
        if (xsDocumentInfo.fTargetNamespace != null && xsDocumentInfo.fTargetNamespace.length() == 0) {
            this.reportSchemaWarning("EmptyTargetNamespace", new Object[] { s }, element);
            xsDocumentInfo.fTargetNamespace = null;
        }
        if (s2 != null) {
            final int n = 0;
            if (contextType == 0 || contextType == 1) {
                if (xsDocumentInfo.fTargetNamespace == null) {
                    xsDocumentInfo.fTargetNamespace = s2;
                    xsDocumentInfo.fIsChameleonSchema = true;
                }
                else if (s2 != xsDocumentInfo.fTargetNamespace) {
                    this.reportSchemaError(XSDHandler.NS_ERROR_CODES[contextType][n], new Object[] { s2, xsDocumentInfo.fTargetNamespace }, element);
                    return null;
                }
            }
            else if (contextType != 3 && s2 != xsDocumentInfo.fTargetNamespace) {
                this.reportSchemaError(XSDHandler.NS_ERROR_CODES[contextType][n], new Object[] { s2, xsDocumentInfo.fTargetNamespace }, element);
                return null;
            }
        }
        else if (xsDocumentInfo.fTargetNamespace != null) {
            if (contextType != 3) {
                this.reportSchemaError(XSDHandler.NS_ERROR_CODES[contextType][1], new Object[] { s2, xsDocumentInfo.fTargetNamespace }, element);
                return null;
            }
            xsdDescription.setTargetNamespace(xsDocumentInfo.fTargetNamespace);
            s2 = xsDocumentInfo.fTargetNamespace;
        }
        xsDocumentInfo.addAllowedNS(xsDocumentInfo.fTargetNamespace);
        SchemaGrammar schemaGrammar;
        if (contextType == 0 || contextType == 1) {
            schemaGrammar = this.fGrammarBucket.getGrammar(xsDocumentInfo.fTargetNamespace);
        }
        else if (this.fHonourAllSchemaLocations && contextType == 2) {
            schemaGrammar = this.findGrammar(xsdDescription);
            if (schemaGrammar == null) {
                schemaGrammar = new SchemaGrammar(xsDocumentInfo.fTargetNamespace, xsdDescription.makeClone(), this.fSymbolTable);
                this.fGrammarBucket.putGrammar(schemaGrammar);
            }
        }
        else {
            schemaGrammar = new SchemaGrammar(xsDocumentInfo.fTargetNamespace, xsdDescription.makeClone(), this.fSymbolTable);
            this.fGrammarBucket.putGrammar(schemaGrammar);
        }
        schemaGrammar.addDocument(null, this.fDoc2SystemId.get(xsDocumentInfo.fSchemaElement));
        this.fDoc2XSDocumentMap.put(element, xsDocumentInfo);
        final Vector<XSDocumentInfo> vector = new Vector<XSDocumentInfo>();
        for (Element element2 = DOMUtil.getFirstChildElement(element); element2 != null; element2 = DOMUtil.getNextSiblingElement(element2)) {
            final String localName = DOMUtil.getLocalName(element2);
            if (!localName.equals(SchemaSymbols.ELT_ANNOTATION)) {
                String s3;
                Element element3;
                if (localName.equals(SchemaSymbols.ELT_IMPORT)) {
                    final Object[] checkAttributes = this.fAttributeChecker.checkAttributes(element2, true, xsDocumentInfo);
                    s3 = (String)checkAttributes[XSAttributeChecker.ATTIDX_SCHEMALOCATION];
                    String addSymbol = (String)checkAttributes[XSAttributeChecker.ATTIDX_NAMESPACE];
                    if (addSymbol != null) {
                        addSymbol = this.fSymbolTable.addSymbol(addSymbol);
                    }
                    if (addSymbol == xsDocumentInfo.fTargetNamespace) {
                        this.reportSchemaError("src-import.1.1", new Object[] { addSymbol }, element2);
                    }
                    final Element firstChildElement = DOMUtil.getFirstChildElement(element2);
                    if (firstChildElement != null) {
                        final String localName2 = DOMUtil.getLocalName(firstChildElement);
                        if (localName2.equals(SchemaSymbols.ELT_ANNOTATION)) {
                            schemaGrammar.addAnnotation(this.fElementTraverser.traverseAnnotationDecl(firstChildElement, checkAttributes, true, xsDocumentInfo));
                        }
                        else {
                            this.reportSchemaError("s4s-elt-must-match.1", new Object[] { localName, "annotation?", localName2 }, element2);
                        }
                        if (DOMUtil.getNextSiblingElement(firstChildElement) != null) {
                            this.reportSchemaError("s4s-elt-must-match.1", new Object[] { localName, "annotation?", DOMUtil.getLocalName(DOMUtil.getNextSiblingElement(firstChildElement)) }, element2);
                        }
                    }
                    else {
                        final String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element2);
                        if (syntheticAnnotation != null) {
                            schemaGrammar.addAnnotation(this.fElementTraverser.traverseSyntheticAnnotation(element2, syntheticAnnotation, checkAttributes, true, xsDocumentInfo));
                        }
                    }
                    this.fAttributeChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                    if (xsDocumentInfo.isAllowedNS(addSymbol)) {
                        if (!this.fHonourAllSchemaLocations) {
                            continue;
                        }
                    }
                    else {
                        xsDocumentInfo.addAllowedNS(addSymbol);
                    }
                    final String null2EmptyString = this.null2EmptyString(xsDocumentInfo.fTargetNamespace);
                    final Vector<String> vector2 = this.fImportMap.get(null2EmptyString);
                    if (vector2 == null) {
                        this.fAllTNSs.addElement(null2EmptyString);
                        final Vector<String> vector3 = new Vector<String>();
                        this.fImportMap.put(null2EmptyString, vector3);
                        vector3.addElement(addSymbol);
                    }
                    else if (!vector2.contains(addSymbol)) {
                        vector2.addElement(addSymbol);
                    }
                    this.fSchemaGrammarDescription.reset();
                    this.fSchemaGrammarDescription.setContextType((short)2);
                    this.fSchemaGrammarDescription.setBaseSystemId(this.doc2SystemId(element));
                    this.fSchemaGrammarDescription.setLocationHints(new String[] { s3 });
                    this.fSchemaGrammarDescription.setTargetNamespace(addSymbol);
                    if (!this.fHonourAllSchemaLocations && this.findGrammar(this.fSchemaGrammarDescription) != null) {
                        continue;
                    }
                    if (this.isExistingGrammar(this.fSchemaGrammarDescription)) {
                        continue;
                    }
                    element3 = this.resolveSchema(this.fSchemaGrammarDescription, false, element2, this.findGrammar(this.fSchemaGrammarDescription) == null);
                }
                else {
                    if (!localName.equals(SchemaSymbols.ELT_INCLUDE) && !localName.equals(SchemaSymbols.ELT_REDEFINE)) {
                        break;
                    }
                    final Object[] checkAttributes2 = this.fAttributeChecker.checkAttributes(element2, true, xsDocumentInfo);
                    s3 = (String)checkAttributes2[XSAttributeChecker.ATTIDX_SCHEMALOCATION];
                    if (localName.equals(SchemaSymbols.ELT_REDEFINE)) {
                        this.fRedefine2NSSupport.put(element2, new SchemaNamespaceSupport(xsDocumentInfo.fNamespaceSupport));
                    }
                    if (localName.equals(SchemaSymbols.ELT_INCLUDE)) {
                        final Element firstChildElement2 = DOMUtil.getFirstChildElement(element2);
                        if (firstChildElement2 != null) {
                            final String localName3 = DOMUtil.getLocalName(firstChildElement2);
                            if (localName3.equals(SchemaSymbols.ELT_ANNOTATION)) {
                                schemaGrammar.addAnnotation(this.fElementTraverser.traverseAnnotationDecl(firstChildElement2, checkAttributes2, true, xsDocumentInfo));
                            }
                            else {
                                this.reportSchemaError("s4s-elt-must-match.1", new Object[] { localName, "annotation?", localName3 }, element2);
                            }
                            if (DOMUtil.getNextSiblingElement(firstChildElement2) != null) {
                                this.reportSchemaError("s4s-elt-must-match.1", new Object[] { localName, "annotation?", DOMUtil.getLocalName(DOMUtil.getNextSiblingElement(firstChildElement2)) }, element2);
                            }
                        }
                        else {
                            final String syntheticAnnotation2 = DOMUtil.getSyntheticAnnotation(element2);
                            if (syntheticAnnotation2 != null) {
                                schemaGrammar.addAnnotation(this.fElementTraverser.traverseSyntheticAnnotation(element2, syntheticAnnotation2, checkAttributes2, true, xsDocumentInfo));
                            }
                        }
                    }
                    else {
                        for (Element element4 = DOMUtil.getFirstChildElement(element2); element4 != null; element4 = DOMUtil.getNextSiblingElement(element4)) {
                            if (DOMUtil.getLocalName(element4).equals(SchemaSymbols.ELT_ANNOTATION)) {
                                schemaGrammar.addAnnotation(this.fElementTraverser.traverseAnnotationDecl(element4, checkAttributes2, true, xsDocumentInfo));
                                DOMUtil.setHidden(element4, this.fHiddenNodes);
                            }
                            else {
                                final String syntheticAnnotation3 = DOMUtil.getSyntheticAnnotation(element2);
                                if (syntheticAnnotation3 != null) {
                                    schemaGrammar.addAnnotation(this.fElementTraverser.traverseSyntheticAnnotation(element2, syntheticAnnotation3, checkAttributes2, true, xsDocumentInfo));
                                }
                            }
                        }
                    }
                    this.fAttributeChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                    if (s3 == null) {
                        this.reportSchemaError("s4s-att-must-appear", new Object[] { "<include> or <redefine>", "schemaLocation" }, element2);
                    }
                    boolean nonAnnotationContent = false;
                    short contextType2 = 0;
                    if (localName.equals(SchemaSymbols.ELT_REDEFINE)) {
                        nonAnnotationContent = this.nonAnnotationContent(element2);
                        contextType2 = 1;
                    }
                    this.fSchemaGrammarDescription.reset();
                    this.fSchemaGrammarDescription.setContextType(contextType2);
                    this.fSchemaGrammarDescription.setBaseSystemId(this.doc2SystemId(element));
                    this.fSchemaGrammarDescription.setLocationHints(new String[] { s3 });
                    this.fSchemaGrammarDescription.setTargetNamespace(s2);
                    element3 = this.resolveSchema(this.fSchemaGrammarDescription, nonAnnotationContent, element2, true);
                    final String fTargetNamespace = xsDocumentInfo.fTargetNamespace;
                }
                XSDocumentInfo constructTrees;
                if (this.fLastSchemaWasDuplicate) {
                    constructTrees = ((element3 == null) ? null : this.fDoc2XSDocumentMap.get(element3));
                }
                else {
                    constructTrees = this.constructTrees(element3, s3, this.fSchemaGrammarDescription);
                }
                if (localName.equals(SchemaSymbols.ELT_REDEFINE) && constructTrees != null) {
                    this.fRedefine2XSDMap.put(element2, constructTrees);
                }
                if (element3 != null) {
                    if (constructTrees != null) {
                        vector.addElement(constructTrees);
                    }
                }
            }
        }
        this.fDependencyMap.put(xsDocumentInfo, vector);
        return xsDocumentInfo;
    }
    
    private boolean isExistingGrammar(final XSDDescription xsdDescription) {
        final SchemaGrammar grammar = this.fGrammarBucket.getGrammar(xsdDescription.getTargetNamespace());
        if (grammar == null) {
            return this.findGrammar(xsdDescription) != null;
        }
        try {
            return grammar.getDocumentLocations().contains(XMLEntityManager.expandSystemId(xsdDescription.getLiteralSystemId(), xsdDescription.getBaseSystemId(), false));
        }
        catch (URI.MalformedURIException ex) {
            return false;
        }
    }
    
    protected void buildGlobalNameRegistries() {
        final Stack stack = new Stack<XSDocumentInfo>();
        stack.push(this.fRoot);
        while (!stack.empty()) {
            final XSDocumentInfo xsDocumentInfo = stack.pop();
            final Element fSchemaElement = xsDocumentInfo.fSchemaElement;
            if (DOMUtil.isHidden(fSchemaElement, this.fHiddenNodes)) {
                continue;
            }
            final Element element = fSchemaElement;
            int n = 1;
            for (Element element2 = DOMUtil.getFirstChildElement(element); element2 != null; element2 = DOMUtil.getNextSiblingElement(element2)) {
                if (!DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_ANNOTATION)) {
                    if (DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_INCLUDE) || DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_IMPORT)) {
                        if (n == 0) {
                            this.reportSchemaError("s4s-elt-invalid-content.3", new Object[] { DOMUtil.getLocalName(element2) }, element2);
                        }
                        DOMUtil.setHidden(element2, this.fHiddenNodes);
                    }
                    else if (DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_REDEFINE)) {
                        if (n == 0) {
                            this.reportSchemaError("s4s-elt-invalid-content.3", new Object[] { DOMUtil.getLocalName(element2) }, element2);
                        }
                        for (Element element3 = DOMUtil.getFirstChildElement(element2); element3 != null; element3 = DOMUtil.getNextSiblingElement(element3)) {
                            final String attrValue = DOMUtil.getAttrValue(element3, SchemaSymbols.ATT_NAME);
                            if (attrValue.length() != 0) {
                                final String s = (xsDocumentInfo.fTargetNamespace == null) ? ("," + attrValue) : (xsDocumentInfo.fTargetNamespace + "," + attrValue);
                                final String localName = DOMUtil.getLocalName(element3);
                                if (localName.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                                    this.checkForDuplicateNames(s, this.fUnparsedAttributeGroupRegistry, this.fUnparsedAttributeGroupRegistry_sub, element3, xsDocumentInfo);
                                    this.renameRedefiningComponents(xsDocumentInfo, element3, SchemaSymbols.ELT_ATTRIBUTEGROUP, attrValue, DOMUtil.getAttrValue(element3, SchemaSymbols.ATT_NAME) + "_fn3dktizrknc9pi");
                                }
                                else if (localName.equals(SchemaSymbols.ELT_COMPLEXTYPE) || localName.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                                    this.checkForDuplicateNames(s, this.fUnparsedTypeRegistry, this.fUnparsedTypeRegistry_sub, element3, xsDocumentInfo);
                                    final String string = DOMUtil.getAttrValue(element3, SchemaSymbols.ATT_NAME) + "_fn3dktizrknc9pi";
                                    if (localName.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                                        this.renameRedefiningComponents(xsDocumentInfo, element3, SchemaSymbols.ELT_COMPLEXTYPE, attrValue, string);
                                    }
                                    else {
                                        this.renameRedefiningComponents(xsDocumentInfo, element3, SchemaSymbols.ELT_SIMPLETYPE, attrValue, string);
                                    }
                                }
                                else if (localName.equals(SchemaSymbols.ELT_GROUP)) {
                                    this.checkForDuplicateNames(s, this.fUnparsedGroupRegistry, this.fUnparsedGroupRegistry_sub, element3, xsDocumentInfo);
                                    this.renameRedefiningComponents(xsDocumentInfo, element3, SchemaSymbols.ELT_GROUP, attrValue, DOMUtil.getAttrValue(element3, SchemaSymbols.ATT_NAME) + "_fn3dktizrknc9pi");
                                }
                            }
                        }
                    }
                    else {
                        n = 0;
                        final String attrValue2 = DOMUtil.getAttrValue(element2, SchemaSymbols.ATT_NAME);
                        if (attrValue2.length() != 0) {
                            final String s2 = (xsDocumentInfo.fTargetNamespace == null) ? ("," + attrValue2) : (xsDocumentInfo.fTargetNamespace + "," + attrValue2);
                            final String localName2 = DOMUtil.getLocalName(element2);
                            if (localName2.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
                                this.checkForDuplicateNames(s2, this.fUnparsedAttributeRegistry, this.fUnparsedAttributeRegistry_sub, element2, xsDocumentInfo);
                            }
                            else if (localName2.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                                this.checkForDuplicateNames(s2, this.fUnparsedAttributeGroupRegistry, this.fUnparsedAttributeGroupRegistry_sub, element2, xsDocumentInfo);
                            }
                            else if (localName2.equals(SchemaSymbols.ELT_COMPLEXTYPE) || localName2.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                                this.checkForDuplicateNames(s2, this.fUnparsedTypeRegistry, this.fUnparsedTypeRegistry_sub, element2, xsDocumentInfo);
                            }
                            else if (localName2.equals(SchemaSymbols.ELT_ELEMENT)) {
                                this.checkForDuplicateNames(s2, this.fUnparsedElementRegistry, this.fUnparsedElementRegistry_sub, element2, xsDocumentInfo);
                            }
                            else if (localName2.equals(SchemaSymbols.ELT_GROUP)) {
                                this.checkForDuplicateNames(s2, this.fUnparsedGroupRegistry, this.fUnparsedGroupRegistry_sub, element2, xsDocumentInfo);
                            }
                            else if (localName2.equals(SchemaSymbols.ELT_NOTATION)) {
                                this.checkForDuplicateNames(s2, this.fUnparsedNotationRegistry, this.fUnparsedNotationRegistry_sub, element2, xsDocumentInfo);
                            }
                        }
                    }
                }
            }
            DOMUtil.setHidden(fSchemaElement, this.fHiddenNodes);
            final Vector<XSDocumentInfo> vector = this.fDependencyMap.get(xsDocumentInfo);
            for (int i = 0; i < vector.size(); ++i) {
                stack.push(vector.elementAt(i));
            }
        }
    }
    
    protected void traverseSchemas(final ArrayList list) {
        this.setSchemasVisible(this.fRoot);
        final Stack stack = new Stack<XSDocumentInfo>();
        stack.push(this.fRoot);
        while (!stack.empty()) {
            final XSDocumentInfo xsDocumentInfo = stack.pop();
            final Element fSchemaElement = xsDocumentInfo.fSchemaElement;
            final SchemaGrammar grammar = this.fGrammarBucket.getGrammar(xsDocumentInfo.fTargetNamespace);
            if (DOMUtil.isHidden(fSchemaElement, this.fHiddenNodes)) {
                continue;
            }
            final Element element = fSchemaElement;
            boolean b = false;
            for (Element element2 = DOMUtil.getFirstVisibleChildElement(element, this.fHiddenNodes); element2 != null; element2 = DOMUtil.getNextVisibleSiblingElement(element2, this.fHiddenNodes)) {
                DOMUtil.setHidden(element2, this.fHiddenNodes);
                final String localName = DOMUtil.getLocalName(element2);
                if (DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_REDEFINE)) {
                    xsDocumentInfo.backupNSSupport((SchemaNamespaceSupport)this.fRedefine2NSSupport.get(element2));
                    for (Element element3 = DOMUtil.getFirstVisibleChildElement(element2, this.fHiddenNodes); element3 != null; element3 = DOMUtil.getNextVisibleSiblingElement(element3, this.fHiddenNodes)) {
                        final String localName2 = DOMUtil.getLocalName(element3);
                        DOMUtil.setHidden(element3, this.fHiddenNodes);
                        if (localName2.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                            this.fAttributeGroupTraverser.traverseGlobal(element3, xsDocumentInfo, grammar);
                        }
                        else if (localName2.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                            this.fComplexTypeTraverser.traverseGlobal(element3, xsDocumentInfo, grammar);
                        }
                        else if (localName2.equals(SchemaSymbols.ELT_GROUP)) {
                            this.fGroupTraverser.traverseGlobal(element3, xsDocumentInfo, grammar);
                        }
                        else if (localName2.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                            this.fSimpleTypeTraverser.traverseGlobal(element3, xsDocumentInfo, grammar);
                        }
                        else {
                            this.reportSchemaError("s4s-elt-must-match.1", new Object[] { DOMUtil.getLocalName(element2), "(annotation | (simpleType | complexType | group | attributeGroup))*", localName2 }, element3);
                        }
                    }
                    xsDocumentInfo.restoreNSSupport();
                }
                else if (localName.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
                    this.fAttributeTraverser.traverseGlobal(element2, xsDocumentInfo, grammar);
                }
                else if (localName.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                    this.fAttributeGroupTraverser.traverseGlobal(element2, xsDocumentInfo, grammar);
                }
                else if (localName.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                    this.fComplexTypeTraverser.traverseGlobal(element2, xsDocumentInfo, grammar);
                }
                else if (localName.equals(SchemaSymbols.ELT_ELEMENT)) {
                    this.fElementTraverser.traverseGlobal(element2, xsDocumentInfo, grammar);
                }
                else if (localName.equals(SchemaSymbols.ELT_GROUP)) {
                    this.fGroupTraverser.traverseGlobal(element2, xsDocumentInfo, grammar);
                }
                else if (localName.equals(SchemaSymbols.ELT_NOTATION)) {
                    this.fNotationTraverser.traverse(element2, xsDocumentInfo, grammar);
                }
                else if (localName.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                    this.fSimpleTypeTraverser.traverseGlobal(element2, xsDocumentInfo, grammar);
                }
                else if (localName.equals(SchemaSymbols.ELT_ANNOTATION)) {
                    grammar.addAnnotation(this.fElementTraverser.traverseAnnotationDecl(element2, xsDocumentInfo.getSchemaAttrs(), true, xsDocumentInfo));
                    b = true;
                }
                else {
                    this.reportSchemaError("s4s-elt-invalid-content.1", new Object[] { SchemaSymbols.ELT_SCHEMA, DOMUtil.getLocalName(element2) }, element2);
                }
            }
            if (!b) {
                final String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
                if (syntheticAnnotation != null) {
                    grammar.addAnnotation(this.fElementTraverser.traverseSyntheticAnnotation(element, syntheticAnnotation, xsDocumentInfo.getSchemaAttrs(), true, xsDocumentInfo));
                }
            }
            if (list != null) {
                final XSAnnotationInfo annotations = xsDocumentInfo.getAnnotations();
                if (annotations != null) {
                    list.add(this.doc2SystemId(fSchemaElement));
                    list.add(annotations);
                }
            }
            xsDocumentInfo.returnSchemaAttrs();
            DOMUtil.setHidden(fSchemaElement, this.fHiddenNodes);
            final Vector<XSDocumentInfo> vector = this.fDependencyMap.get(xsDocumentInfo);
            for (int i = 0; i < vector.size(); ++i) {
                stack.push(vector.elementAt(i));
            }
        }
    }
    
    private final boolean needReportTNSError(final String s) {
        if (this.fReportedTNS == null) {
            this.fReportedTNS = new Vector();
        }
        else if (this.fReportedTNS.contains(s)) {
            return false;
        }
        this.fReportedTNS.addElement(s);
        return true;
    }
    
    protected Object getGlobalDecl(final XSDocumentInfo xsDocumentInfo, final int n, final QName qName, final Element element) {
        if (qName.uri != null && qName.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA && n == 7) {
            final XSTypeDefinition globalTypeDecl = SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(qName.localpart);
            if (globalTypeDecl != null) {
                return globalTypeDecl;
            }
        }
        if (!xsDocumentInfo.isAllowedNS(qName.uri)) {
            if (xsDocumentInfo.needReportTNSError(qName.uri)) {
                this.reportSchemaError((qName.uri == null) ? "src-resolve.4.1" : "src-resolve.4.2", new Object[] { this.fDoc2SystemId.get(xsDocumentInfo.fSchemaElement), qName.uri, qName.rawname }, element);
            }
            return null;
        }
        final SchemaGrammar grammar = this.fGrammarBucket.getGrammar(qName.uri);
        if (grammar == null) {
            if (this.needReportTNSError(qName.uri)) {
                this.reportSchemaError("src-resolve", new Object[] { qName.rawname, XSDHandler.COMP_TYPE[n] }, element);
            }
            return null;
        }
        Object o = null;
        switch (n) {
            case 1: {
                o = grammar.getGlobalAttributeDecl(qName.localpart);
                break;
            }
            case 2: {
                o = grammar.getGlobalAttributeGroupDecl(qName.localpart);
                break;
            }
            case 3: {
                o = grammar.getGlobalElementDecl(qName.localpart);
                break;
            }
            case 4: {
                o = grammar.getGlobalGroupDecl(qName.localpart);
                break;
            }
            case 5: {
                o = grammar.getIDConstraintDecl(qName.localpart);
                break;
            }
            case 6: {
                o = grammar.getGlobalNotationDecl(qName.localpart);
                break;
            }
            case 7: {
                o = grammar.getGlobalTypeDecl(qName.localpart);
                break;
            }
        }
        if (o != null) {
            return o;
        }
        Element element2 = null;
        XSDocumentInfo xsDocumentInfo2 = null;
        final String s = (qName.uri == null) ? ("," + qName.localpart) : (qName.uri + "," + qName.localpart);
        switch (n) {
            case 1: {
                element2 = (Element)this.fUnparsedAttributeRegistry.get(s);
                xsDocumentInfo2 = (XSDocumentInfo)this.fUnparsedAttributeRegistry_sub.get(s);
                break;
            }
            case 2: {
                element2 = (Element)this.fUnparsedAttributeGroupRegistry.get(s);
                xsDocumentInfo2 = (XSDocumentInfo)this.fUnparsedAttributeGroupRegistry_sub.get(s);
                break;
            }
            case 3: {
                element2 = (Element)this.fUnparsedElementRegistry.get(s);
                xsDocumentInfo2 = (XSDocumentInfo)this.fUnparsedElementRegistry_sub.get(s);
                break;
            }
            case 4: {
                element2 = (Element)this.fUnparsedGroupRegistry.get(s);
                xsDocumentInfo2 = (XSDocumentInfo)this.fUnparsedGroupRegistry_sub.get(s);
                break;
            }
            case 5: {
                element2 = (Element)this.fUnparsedIdentityConstraintRegistry.get(s);
                xsDocumentInfo2 = (XSDocumentInfo)this.fUnparsedIdentityConstraintRegistry_sub.get(s);
                break;
            }
            case 6: {
                element2 = (Element)this.fUnparsedNotationRegistry.get(s);
                xsDocumentInfo2 = (XSDocumentInfo)this.fUnparsedNotationRegistry_sub.get(s);
                break;
            }
            case 7: {
                element2 = (Element)this.fUnparsedTypeRegistry.get(s);
                xsDocumentInfo2 = (XSDocumentInfo)this.fUnparsedTypeRegistry_sub.get(s);
                break;
            }
            default: {
                this.reportSchemaError("Internal-Error", new Object[] { "XSDHandler asked to locate component of type " + n + "; it does not recognize this type!" }, element);
                break;
            }
        }
        if (element2 == null) {
            this.reportSchemaError("src-resolve", new Object[] { qName.rawname, XSDHandler.COMP_TYPE[n] }, element);
            return null;
        }
        final XSDocumentInfo xsDocumentForDecl = this.findXSDocumentForDecl(xsDocumentInfo, element2, xsDocumentInfo2);
        if (xsDocumentForDecl == null) {
            this.reportSchemaError((qName.uri == null) ? "src-resolve.4.1" : "src-resolve.4.2", new Object[] { this.fDoc2SystemId.get(xsDocumentInfo.fSchemaElement), qName.uri, qName.rawname }, element);
            return null;
        }
        if (DOMUtil.isHidden(element2, this.fHiddenNodes)) {
            String s2 = XSDHandler.CIRCULAR_CODES[n];
            if (n == 7 && SchemaSymbols.ELT_COMPLEXTYPE.equals(DOMUtil.getLocalName(element2))) {
                s2 = "ct-props-correct.3";
            }
            this.reportSchemaError(s2, new Object[] { qName.prefix + ":" + qName.localpart }, element);
            return null;
        }
        DOMUtil.setHidden(element2, this.fHiddenNodes);
        SchemaNamespaceSupport schemaNamespaceSupport = null;
        final Element parent = DOMUtil.getParent(element2);
        if (DOMUtil.getLocalName(parent).equals(SchemaSymbols.ELT_REDEFINE)) {
            schemaNamespaceSupport = (SchemaNamespaceSupport)this.fRedefine2NSSupport.get(parent);
        }
        xsDocumentForDecl.backupNSSupport(schemaNamespaceSupport);
        switch (n) {
            case 1: {
                o = this.fAttributeTraverser.traverseGlobal(element2, xsDocumentForDecl, grammar);
                break;
            }
            case 2: {
                o = this.fAttributeGroupTraverser.traverseGlobal(element2, xsDocumentForDecl, grammar);
                break;
            }
            case 3: {
                o = this.fElementTraverser.traverseGlobal(element2, xsDocumentForDecl, grammar);
                break;
            }
            case 4: {
                o = this.fGroupTraverser.traverseGlobal(element2, xsDocumentForDecl, grammar);
                break;
            }
            case 5: {
                o = null;
                break;
            }
            case 6: {
                o = this.fNotationTraverser.traverse(element2, xsDocumentForDecl, grammar);
                break;
            }
            case 7: {
                if (DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                    o = this.fComplexTypeTraverser.traverseGlobal(element2, xsDocumentForDecl, grammar);
                    break;
                }
                o = this.fSimpleTypeTraverser.traverseGlobal(element2, xsDocumentForDecl, grammar);
                break;
            }
        }
        xsDocumentForDecl.restoreNSSupport();
        return o;
    }
    
    Object getGrpOrAttrGrpRedefinedByRestriction(final int n, final QName qName, final XSDocumentInfo xsDocumentInfo, final Element element) {
        final String s = (qName.uri != null) ? (qName.uri + "," + qName.localpart) : ("," + qName.localpart);
        String s2 = null;
        switch (n) {
            case 2: {
                s2 = this.fRedefinedRestrictedAttributeGroupRegistry.get(s);
                break;
            }
            case 4: {
                s2 = this.fRedefinedRestrictedGroupRegistry.get(s);
                break;
            }
            default: {
                return null;
            }
        }
        if (s2 == null) {
            return null;
        }
        final int index = s2.indexOf(",");
        final Object globalDecl = this.getGlobalDecl(xsDocumentInfo, n, new QName(XMLSymbols.EMPTY_STRING, s2.substring(index + 1), s2.substring(index), (index == 0) ? null : s2.substring(0, index)), element);
        if (globalDecl == null) {
            switch (n) {
                case 2: {
                    this.reportSchemaError("src-redefine.7.2.1", new Object[] { qName.localpart }, element);
                    break;
                }
                case 4: {
                    this.reportSchemaError("src-redefine.6.2.1", new Object[] { qName.localpart }, element);
                    break;
                }
            }
            return null;
        }
        return globalDecl;
    }
    
    protected void resolveKeyRefs() {
        for (int i = 0; i < this.fKeyrefStackPos; ++i) {
            final XSDocumentInfo xsDocumentInfo = this.fKeyrefsMapXSDocumentInfo[i];
            xsDocumentInfo.fNamespaceSupport.makeGlobal();
            xsDocumentInfo.fNamespaceSupport.setEffectiveContext(this.fKeyrefNamespaceContext[i]);
            final SchemaGrammar grammar = this.fGrammarBucket.getGrammar(xsDocumentInfo.fTargetNamespace);
            DOMUtil.setHidden(this.fKeyrefs[i], this.fHiddenNodes);
            this.fKeyrefTraverser.traverse(this.fKeyrefs[i], this.fKeyrefElems[i], xsDocumentInfo, grammar);
        }
    }
    
    protected Hashtable getIDRegistry() {
        return this.fUnparsedIdentityConstraintRegistry;
    }
    
    protected Hashtable getIDRegistry_sub() {
        return this.fUnparsedIdentityConstraintRegistry_sub;
    }
    
    protected void storeKeyRef(final Element element, final XSDocumentInfo xsDocumentInfo, final XSElementDecl xsElementDecl) {
        final String attrValue = DOMUtil.getAttrValue(element, SchemaSymbols.ATT_NAME);
        if (attrValue.length() != 0) {
            this.checkForDuplicateNames((xsDocumentInfo.fTargetNamespace == null) ? ("," + attrValue) : (xsDocumentInfo.fTargetNamespace + "," + attrValue), this.fUnparsedIdentityConstraintRegistry, this.fUnparsedIdentityConstraintRegistry_sub, element, xsDocumentInfo);
        }
        if (this.fKeyrefStackPos == this.fKeyrefs.length) {
            final Element[] fKeyrefs = new Element[this.fKeyrefStackPos + 2];
            System.arraycopy(this.fKeyrefs, 0, fKeyrefs, 0, this.fKeyrefStackPos);
            this.fKeyrefs = fKeyrefs;
            final XSElementDecl[] fKeyrefElems = new XSElementDecl[this.fKeyrefStackPos + 2];
            System.arraycopy(this.fKeyrefElems, 0, fKeyrefElems, 0, this.fKeyrefStackPos);
            this.fKeyrefElems = fKeyrefElems;
            final String[][] fKeyrefNamespaceContext = new String[this.fKeyrefStackPos + 2][];
            System.arraycopy(this.fKeyrefNamespaceContext, 0, fKeyrefNamespaceContext, 0, this.fKeyrefStackPos);
            this.fKeyrefNamespaceContext = fKeyrefNamespaceContext;
            final XSDocumentInfo[] fKeyrefsMapXSDocumentInfo = new XSDocumentInfo[this.fKeyrefStackPos + 2];
            System.arraycopy(this.fKeyrefsMapXSDocumentInfo, 0, fKeyrefsMapXSDocumentInfo, 0, this.fKeyrefStackPos);
            this.fKeyrefsMapXSDocumentInfo = fKeyrefsMapXSDocumentInfo;
        }
        this.fKeyrefs[this.fKeyrefStackPos] = element;
        this.fKeyrefElems[this.fKeyrefStackPos] = xsElementDecl;
        this.fKeyrefNamespaceContext[this.fKeyrefStackPos] = xsDocumentInfo.fNamespaceSupport.getEffectiveLocalContext();
        this.fKeyrefsMapXSDocumentInfo[this.fKeyrefStackPos++] = xsDocumentInfo;
    }
    
    private Element resolveSchema(final XSDDescription xsdDescription, final boolean b, final Element element, final boolean b2) {
        XMLInputSource resolveDocument = null;
        try {
            resolveDocument = XMLSchemaLoader.resolveDocument(xsdDescription, b2 ? this.fLocationPairs : XSDHandler.EMPTY_TABLE, this.fEntityResolver);
        }
        catch (IOException ex) {
            if (b) {
                this.reportSchemaError("schema_reference.4", new Object[] { xsdDescription.getLocationHints()[0] }, element);
            }
            else {
                this.reportSchemaWarning("schema_reference.4", new Object[] { xsdDescription.getLocationHints()[0] }, element);
            }
        }
        if (resolveDocument instanceof DOMInputSource) {
            this.fHiddenNodes.clear();
            final Node node = ((DOMInputSource)resolveDocument).getNode();
            if (node instanceof Document) {
                return DOMUtil.getRoot((Document)node);
            }
            if (node instanceof Element) {
                return (Element)node;
            }
            return null;
        }
        else {
            if (!(resolveDocument instanceof SAXInputSource)) {
                return this.getSchemaDocument(xsdDescription.getTargetNamespace(), resolveDocument, b, xsdDescription.getContextType(), element);
            }
            XMLReader xmlReader = ((SAXInputSource)resolveDocument).getXMLReader();
            final InputSource inputSource = ((SAXInputSource)resolveDocument).getInputSource();
            boolean feature = false;
            if (xmlReader != null) {
                try {
                    feature = xmlReader.getFeature("http://xml.org/sax/features/namespace-prefixes");
                }
                catch (SAXException ex2) {}
            }
            else {
                try {
                    xmlReader = XMLReaderFactory.createXMLReader();
                }
                catch (SAXException ex3) {
                    xmlReader = new SAXParser();
                }
                try {
                    xmlReader.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
                    feature = true;
                }
                catch (SAXException ex4) {}
            }
            boolean feature2 = false;
            try {
                feature2 = xmlReader.getFeature("http://xml.org/sax/features/string-interning");
            }
            catch (SAXException ex5) {}
            if (this.fXSContentHandler == null) {
                this.fXSContentHandler = new SchemaContentHandler();
            }
            this.fXSContentHandler.reset(this.fSchemaParser, this.fSymbolTable, feature, feature2);
            xmlReader.setContentHandler(this.fXSContentHandler);
            try {
                xmlReader.parse(inputSource);
            }
            catch (SAXException ex6) {
                return null;
            }
            catch (IOException ex7) {
                return null;
            }
            final Document document = this.fXSContentHandler.getDocument();
            if (document == null) {
                return null;
            }
            return DOMUtil.getRoot(document);
        }
    }
    
    private Element getSchemaDocument(final String s, final XMLInputSource xmlInputSource, final boolean b, final short n, final Element element) {
        boolean b2 = true;
        try {
            if (xmlInputSource != null && (xmlInputSource.getSystemId() != null || xmlInputSource.getByteStream() != null || xmlInputSource.getCharacterStream() != null)) {
                XSDKey xsdKey = null;
                String expandSystemId = null;
                if (n != 3) {
                    expandSystemId = XMLEntityManager.expandSystemId(xmlInputSource.getSystemId(), xmlInputSource.getBaseSystemId(), false);
                    xsdKey = new XSDKey(expandSystemId, n, s);
                    final Element element2;
                    if ((element2 = this.fTraversed.get(xsdKey)) != null) {
                        this.fLastSchemaWasDuplicate = true;
                        return element2;
                    }
                }
                this.fSchemaParser.parse(xmlInputSource);
                final Element element3 = (this.fSchemaParser.getDocument2() == null) ? null : DOMUtil.getRoot(this.fSchemaParser.getDocument2());
                if (xsdKey != null) {
                    this.fTraversed.put(xsdKey, element3);
                }
                if (expandSystemId != null) {
                    this.fDoc2SystemId.put(element3, expandSystemId);
                }
                this.fLastSchemaWasDuplicate = false;
                return element3;
            }
            b2 = false;
        }
        catch (IOException ex) {}
        if (b) {
            if (b2) {
                this.reportSchemaError("schema_reference.4", new Object[] { xmlInputSource.getSystemId() }, element);
            }
            else {
                this.reportSchemaError("schema_reference.4", new Object[] { (xmlInputSource == null) ? "" : xmlInputSource.getSystemId() }, element);
            }
        }
        else if (b2) {
            this.reportSchemaWarning("schema_reference.4", new Object[] { xmlInputSource.getSystemId() }, element);
        }
        this.fLastSchemaWasDuplicate = false;
        return null;
    }
    
    private void createTraversers() {
        this.fAttributeChecker = new XSAttributeChecker(this);
        this.fAttributeGroupTraverser = new XSDAttributeGroupTraverser(this, this.fAttributeChecker);
        this.fAttributeTraverser = new XSDAttributeTraverser(this, this.fAttributeChecker);
        this.fComplexTypeTraverser = new XSDComplexTypeTraverser(this, this.fAttributeChecker);
        this.fElementTraverser = new XSDElementTraverser(this, this.fAttributeChecker);
        this.fGroupTraverser = new XSDGroupTraverser(this, this.fAttributeChecker);
        this.fKeyrefTraverser = new XSDKeyrefTraverser(this, this.fAttributeChecker);
        this.fNotationTraverser = new XSDNotationTraverser(this, this.fAttributeChecker);
        this.fSimpleTypeTraverser = new XSDSimpleTypeTraverser(this, this.fAttributeChecker);
        this.fUniqueOrKeyTraverser = new XSDUniqueOrKeyTraverser(this, this.fAttributeChecker);
        this.fWildCardTraverser = new XSDWildcardTraverser(this, this.fAttributeChecker);
    }
    
    void prepareForParse() {
        this.fTraversed.clear();
        this.fDoc2SystemId.clear();
        this.fHiddenNodes.clear();
        this.fLastSchemaWasDuplicate = false;
    }
    
    void prepareForTraverse() {
        this.fUnparsedAttributeRegistry.clear();
        this.fUnparsedAttributeGroupRegistry.clear();
        this.fUnparsedElementRegistry.clear();
        this.fUnparsedGroupRegistry.clear();
        this.fUnparsedIdentityConstraintRegistry.clear();
        this.fUnparsedNotationRegistry.clear();
        this.fUnparsedTypeRegistry.clear();
        this.fUnparsedAttributeRegistry_sub.clear();
        this.fUnparsedAttributeGroupRegistry_sub.clear();
        this.fUnparsedElementRegistry_sub.clear();
        this.fUnparsedGroupRegistry_sub.clear();
        this.fUnparsedIdentityConstraintRegistry_sub.clear();
        this.fUnparsedNotationRegistry_sub.clear();
        this.fUnparsedTypeRegistry_sub.clear();
        this.fXSDocumentInfoRegistry.clear();
        this.fDependencyMap.clear();
        this.fDoc2XSDocumentMap.clear();
        this.fRedefine2XSDMap.clear();
        this.fRedefine2NSSupport.clear();
        this.fAllTNSs.removeAllElements();
        this.fImportMap.clear();
        this.fRoot = null;
        for (int i = 0; i < this.fLocalElemStackPos; ++i) {
            this.fParticle[i] = null;
            this.fLocalElementDecl[i] = null;
            this.fLocalElementDecl_schema[i] = null;
            this.fLocalElemNamespaceContext[i] = null;
        }
        this.fLocalElemStackPos = 0;
        for (int j = 0; j < this.fKeyrefStackPos; ++j) {
            this.fKeyrefs[j] = null;
            this.fKeyrefElems[j] = null;
            this.fKeyrefNamespaceContext[j] = null;
            this.fKeyrefsMapXSDocumentInfo[j] = null;
        }
        this.fKeyrefStackPos = 0;
        if (this.fAttributeChecker == null) {
            this.createTraversers();
        }
        this.fAttributeChecker.reset(this.fSymbolTable);
        this.fAttributeGroupTraverser.reset(this.fSymbolTable, this.fValidateAnnotations);
        this.fAttributeTraverser.reset(this.fSymbolTable, this.fValidateAnnotations);
        this.fComplexTypeTraverser.reset(this.fSymbolTable, this.fValidateAnnotations);
        this.fElementTraverser.reset(this.fSymbolTable, this.fValidateAnnotations);
        this.fGroupTraverser.reset(this.fSymbolTable, this.fValidateAnnotations);
        this.fKeyrefTraverser.reset(this.fSymbolTable, this.fValidateAnnotations);
        this.fNotationTraverser.reset(this.fSymbolTable, this.fValidateAnnotations);
        this.fSimpleTypeTraverser.reset(this.fSymbolTable, this.fValidateAnnotations);
        this.fUniqueOrKeyTraverser.reset(this.fSymbolTable, this.fValidateAnnotations);
        this.fWildCardTraverser.reset(this.fSymbolTable, this.fValidateAnnotations);
        this.fRedefinedRestrictedAttributeGroupRegistry.clear();
        this.fRedefinedRestrictedGroupRegistry.clear();
    }
    
    public void setDeclPool(final XSDeclarationPool fDeclPool) {
        this.fDeclPool = fDeclPool;
    }
    
    public void reset(final XMLComponentManager xmlComponentManager) {
        this.fSymbolTable = (SymbolTable)xmlComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        this.fEntityResolver = (XMLEntityResolver)xmlComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-manager");
        final XMLEntityResolver entityResolver = (XMLEntityResolver)xmlComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
        if (entityResolver != null) {
            this.fSchemaParser.setEntityResolver(entityResolver);
        }
        this.fErrorReporter = (XMLErrorReporter)xmlComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        try {
            final XMLErrorHandler errorHandler = this.fErrorReporter.getErrorHandler();
            if (errorHandler != this.fSchemaParser.getProperty("http://apache.org/xml/properties/internal/error-handler")) {
                this.fSchemaParser.setProperty("http://apache.org/xml/properties/internal/error-handler", (errorHandler != null) ? errorHandler : new DefaultErrorHandler());
                if (this.fAnnotationValidator != null) {
                    this.fAnnotationValidator.setProperty("http://apache.org/xml/properties/internal/error-handler", (errorHandler != null) ? errorHandler : new DefaultErrorHandler());
                }
            }
        }
        catch (XMLConfigurationException ex) {}
        try {
            this.fValidateAnnotations = xmlComponentManager.getFeature("http://apache.org/xml/features/validate-annotations");
        }
        catch (XMLConfigurationException ex2) {
            this.fValidateAnnotations = false;
        }
        try {
            this.fHonourAllSchemaLocations = xmlComponentManager.getFeature("http://apache.org/xml/features/honour-all-schemaLocations");
        }
        catch (XMLConfigurationException ex3) {
            this.fHonourAllSchemaLocations = false;
        }
        try {
            this.fSchemaParser.setFeature("http://apache.org/xml/features/continue-after-fatal-error", this.fErrorReporter.getFeature("http://apache.org/xml/features/continue-after-fatal-error"));
        }
        catch (XMLConfigurationException ex4) {}
        try {
            this.fSchemaParser.setFeature("http://apache.org/xml/features/allow-java-encodings", xmlComponentManager.getFeature("http://apache.org/xml/features/allow-java-encodings"));
        }
        catch (XMLConfigurationException ex5) {}
        try {
            this.fSchemaParser.setFeature("http://apache.org/xml/features/standard-uri-conformant", xmlComponentManager.getFeature("http://apache.org/xml/features/standard-uri-conformant"));
        }
        catch (XMLConfigurationException ex6) {}
        try {
            this.fGrammarPool = (XMLGrammarPool)xmlComponentManager.getProperty("http://apache.org/xml/properties/internal/grammar-pool");
        }
        catch (XMLConfigurationException ex7) {
            this.fGrammarPool = null;
        }
        try {
            this.fSchemaParser.setFeature("http://apache.org/xml/features/disallow-doctype-decl", xmlComponentManager.getFeature("http://apache.org/xml/features/disallow-doctype-decl"));
        }
        catch (XMLConfigurationException ex8) {}
        try {
            final Object property = xmlComponentManager.getProperty("http://apache.org/xml/properties/security-manager");
            if (property != null) {
                this.fSchemaParser.setProperty("http://apache.org/xml/properties/security-manager", property);
            }
        }
        catch (XMLConfigurationException ex9) {}
    }
    
    void traverseLocalElements() {
        this.fElementTraverser.fDeferTraversingLocalElements = false;
        for (int i = 0; i < this.fLocalElemStackPos; ++i) {
            final Element element = this.fLocalElementDecl[i];
            final XSDocumentInfo xsDocumentInfo = this.fLocalElementDecl_schema[i];
            this.fElementTraverser.traverseLocal(this.fParticle[i], element, xsDocumentInfo, this.fGrammarBucket.getGrammar(xsDocumentInfo.fTargetNamespace), this.fAllContext[i], this.fParent[i], this.fLocalElemNamespaceContext[i]);
            if (this.fParticle[i].fType == 0) {
                XSModelGroupImpl fModelGroup = null;
                if (this.fParent[i] instanceof XSComplexTypeDecl) {
                    final XSParticle particle = ((XSComplexTypeDecl)this.fParent[i]).getParticle();
                    if (particle != null) {
                        fModelGroup = (XSModelGroupImpl)particle.getTerm();
                    }
                }
                else {
                    fModelGroup = ((XSGroupDecl)this.fParent[i]).fModelGroup;
                }
                if (fModelGroup != null) {
                    this.removeParticle(fModelGroup, this.fParticle[i]);
                }
            }
        }
    }
    
    private boolean removeParticle(final XSModelGroupImpl xsModelGroupImpl, final XSParticleDecl xsParticleDecl) {
        for (int i = 0; i < xsModelGroupImpl.fParticleCount; ++i) {
            final XSParticleDecl xsParticleDecl2 = xsModelGroupImpl.fParticles[i];
            if (xsParticleDecl2 == xsParticleDecl) {
                for (int j = i; j < xsModelGroupImpl.fParticleCount - 1; ++j) {
                    xsModelGroupImpl.fParticles[j] = xsModelGroupImpl.fParticles[j + 1];
                }
                --xsModelGroupImpl.fParticleCount;
                return true;
            }
            if (xsParticleDecl2.fType == 3 && this.removeParticle((XSModelGroupImpl)xsParticleDecl2.fValue, xsParticleDecl)) {
                return true;
            }
        }
        return false;
    }
    
    void fillInLocalElemInfo(final Element element, final XSDocumentInfo xsDocumentInfo, final int n, final XSObject xsObject, final XSParticleDecl xsParticleDecl) {
        if (this.fParticle.length == this.fLocalElemStackPos) {
            final XSParticleDecl[] fParticle = new XSParticleDecl[this.fLocalElemStackPos + 10];
            System.arraycopy(this.fParticle, 0, fParticle, 0, this.fLocalElemStackPos);
            this.fParticle = fParticle;
            final Element[] fLocalElementDecl = new Element[this.fLocalElemStackPos + 10];
            System.arraycopy(this.fLocalElementDecl, 0, fLocalElementDecl, 0, this.fLocalElemStackPos);
            this.fLocalElementDecl = fLocalElementDecl;
            final XSDocumentInfo[] fLocalElementDecl_schema = new XSDocumentInfo[this.fLocalElemStackPos + 10];
            System.arraycopy(this.fLocalElementDecl_schema, 0, fLocalElementDecl_schema, 0, this.fLocalElemStackPos);
            this.fLocalElementDecl_schema = fLocalElementDecl_schema;
            final int[] fAllContext = new int[this.fLocalElemStackPos + 10];
            System.arraycopy(this.fAllContext, 0, fAllContext, 0, this.fLocalElemStackPos);
            this.fAllContext = fAllContext;
            final XSObject[] fParent = new XSObject[this.fLocalElemStackPos + 10];
            System.arraycopy(this.fParent, 0, fParent, 0, this.fLocalElemStackPos);
            this.fParent = fParent;
            final String[][] fLocalElemNamespaceContext = new String[this.fLocalElemStackPos + 10][];
            System.arraycopy(this.fLocalElemNamespaceContext, 0, fLocalElemNamespaceContext, 0, this.fLocalElemStackPos);
            this.fLocalElemNamespaceContext = fLocalElemNamespaceContext;
        }
        this.fParticle[this.fLocalElemStackPos] = xsParticleDecl;
        this.fLocalElementDecl[this.fLocalElemStackPos] = element;
        this.fLocalElementDecl_schema[this.fLocalElemStackPos] = xsDocumentInfo;
        this.fAllContext[this.fLocalElemStackPos] = n;
        this.fParent[this.fLocalElemStackPos] = xsObject;
        this.fLocalElemNamespaceContext[this.fLocalElemStackPos++] = xsDocumentInfo.fNamespaceSupport.getEffectiveLocalContext();
    }
    
    void checkForDuplicateNames(final String s, final Hashtable hashtable, final Hashtable hashtable2, final Element element, final XSDocumentInfo xsDocumentInfo) {
        final Element value;
        if ((value = hashtable.get(s)) == null) {
            hashtable.put(s, element);
            hashtable2.put(s, xsDocumentInfo);
        }
        else {
            final Element element2 = value;
            final XSDocumentInfo xsDocumentInfo2 = hashtable2.get(s);
            if (element2 == element) {
                return;
            }
            XSDocumentInfo xsDocumentInfo3 = null;
            boolean b = true;
            final Element parent;
            if (DOMUtil.getLocalName(parent = DOMUtil.getParent(element2)).equals(SchemaSymbols.ELT_REDEFINE)) {
                xsDocumentInfo3 = (XSDocumentInfo)this.fRedefine2XSDMap.get(parent);
            }
            else if (DOMUtil.getLocalName(DOMUtil.getParent(element)).equals(SchemaSymbols.ELT_REDEFINE)) {
                xsDocumentInfo3 = xsDocumentInfo2;
                b = false;
            }
            if (xsDocumentInfo3 != null) {
                if (xsDocumentInfo2 == xsDocumentInfo) {
                    this.reportSchemaError("sch-props-correct.2", new Object[] { s }, element);
                    return;
                }
                final String string = s.substring(s.lastIndexOf(44) + 1) + "_fn3dktizrknc9pi";
                if (xsDocumentInfo3 == xsDocumentInfo) {
                    element.setAttribute(SchemaSymbols.ATT_NAME, string);
                    if (xsDocumentInfo.fTargetNamespace == null) {
                        hashtable.put("," + string, element);
                        hashtable2.put("," + string, xsDocumentInfo);
                    }
                    else {
                        hashtable.put(xsDocumentInfo.fTargetNamespace + "," + string, element);
                        hashtable2.put(xsDocumentInfo.fTargetNamespace + "," + string, xsDocumentInfo);
                    }
                    if (xsDocumentInfo.fTargetNamespace == null) {
                        this.checkForDuplicateNames("," + string, hashtable, hashtable2, element, xsDocumentInfo);
                    }
                    else {
                        this.checkForDuplicateNames(xsDocumentInfo.fTargetNamespace + "," + string, hashtable, hashtable2, element, xsDocumentInfo);
                    }
                }
                else if (b) {
                    if (xsDocumentInfo.fTargetNamespace == null) {
                        this.checkForDuplicateNames("," + string, hashtable, hashtable2, element, xsDocumentInfo);
                    }
                    else {
                        this.checkForDuplicateNames(xsDocumentInfo.fTargetNamespace + "," + string, hashtable, hashtable2, element, xsDocumentInfo);
                    }
                }
                else {
                    this.reportSchemaError("sch-props-correct.2", new Object[] { s }, element);
                }
            }
            else {
                this.reportSchemaError("sch-props-correct.2", new Object[] { s }, element);
            }
        }
    }
    
    private void renameRedefiningComponents(final XSDocumentInfo xsDocumentInfo, final Element element, final String s, final String s2, final String s3) {
        if (s.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
            Element element2 = DOMUtil.getFirstChildElement(element);
            if (element2 == null) {
                this.reportSchemaError("src-redefine.5.a.a", null, element);
            }
            else {
                if (DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_ANNOTATION)) {
                    element2 = DOMUtil.getNextSiblingElement(element2);
                }
                if (element2 == null) {
                    this.reportSchemaError("src-redefine.5.a.a", null, element);
                }
                else {
                    final String localName = DOMUtil.getLocalName(element2);
                    if (!localName.equals(SchemaSymbols.ELT_RESTRICTION)) {
                        this.reportSchemaError("src-redefine.5.a.b", new Object[] { localName }, element);
                    }
                    else {
                        final Object[] checkAttributes = this.fAttributeChecker.checkAttributes(element2, false, xsDocumentInfo);
                        final QName qName = (QName)checkAttributes[XSAttributeChecker.ATTIDX_BASE];
                        if (qName == null || qName.uri != xsDocumentInfo.fTargetNamespace || !qName.localpart.equals(s2)) {
                            this.reportSchemaError("src-redefine.5.a.c", new Object[] { localName, ((xsDocumentInfo.fTargetNamespace == null) ? "" : xsDocumentInfo.fTargetNamespace) + "," + s2 }, element);
                        }
                        else if (qName.prefix != null && qName.prefix.length() > 0) {
                            element2.setAttribute(SchemaSymbols.ATT_BASE, qName.prefix + ":" + s3);
                        }
                        else {
                            element2.setAttribute(SchemaSymbols.ATT_BASE, s3);
                        }
                        this.fAttributeChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                    }
                }
            }
        }
        else if (s.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
            Element element3 = DOMUtil.getFirstChildElement(element);
            if (element3 == null) {
                this.reportSchemaError("src-redefine.5.b.a", null, element);
            }
            else {
                if (DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_ANNOTATION)) {
                    element3 = DOMUtil.getNextSiblingElement(element3);
                }
                if (element3 == null) {
                    this.reportSchemaError("src-redefine.5.b.a", null, element);
                }
                else {
                    Element element4 = DOMUtil.getFirstChildElement(element3);
                    if (element4 == null) {
                        this.reportSchemaError("src-redefine.5.b.b", null, element3);
                    }
                    else {
                        if (DOMUtil.getLocalName(element4).equals(SchemaSymbols.ELT_ANNOTATION)) {
                            element4 = DOMUtil.getNextSiblingElement(element4);
                        }
                        if (element4 == null) {
                            this.reportSchemaError("src-redefine.5.b.b", null, element3);
                        }
                        else {
                            final String localName2 = DOMUtil.getLocalName(element4);
                            if (!localName2.equals(SchemaSymbols.ELT_RESTRICTION) && !localName2.equals(SchemaSymbols.ELT_EXTENSION)) {
                                this.reportSchemaError("src-redefine.5.b.c", new Object[] { localName2 }, element4);
                            }
                            else {
                                final QName qName2 = (QName)this.fAttributeChecker.checkAttributes(element4, false, xsDocumentInfo)[XSAttributeChecker.ATTIDX_BASE];
                                if (qName2 == null || qName2.uri != xsDocumentInfo.fTargetNamespace || !qName2.localpart.equals(s2)) {
                                    this.reportSchemaError("src-redefine.5.b.d", new Object[] { localName2, ((xsDocumentInfo.fTargetNamespace == null) ? "" : xsDocumentInfo.fTargetNamespace) + "," + s2 }, element4);
                                }
                                else if (qName2.prefix != null && qName2.prefix.length() > 0) {
                                    element4.setAttribute(SchemaSymbols.ATT_BASE, qName2.prefix + ":" + s3);
                                }
                                else {
                                    element4.setAttribute(SchemaSymbols.ATT_BASE, s3);
                                }
                            }
                        }
                    }
                }
            }
        }
        else if (s.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
            final String s4 = (xsDocumentInfo.fTargetNamespace == null) ? ("," + s2) : (xsDocumentInfo.fTargetNamespace + "," + s2);
            final int changeRedefineGroup = this.changeRedefineGroup(s4, s, s3, element, xsDocumentInfo);
            if (changeRedefineGroup > 1) {
                this.reportSchemaError("src-redefine.7.1", new Object[] { new Integer(changeRedefineGroup) }, element);
            }
            else if (changeRedefineGroup != 1) {
                if (xsDocumentInfo.fTargetNamespace == null) {
                    this.fRedefinedRestrictedAttributeGroupRegistry.put(s4, "," + s3);
                }
                else {
                    this.fRedefinedRestrictedAttributeGroupRegistry.put(s4, xsDocumentInfo.fTargetNamespace + "," + s3);
                }
            }
        }
        else if (s.equals(SchemaSymbols.ELT_GROUP)) {
            final String s5 = (xsDocumentInfo.fTargetNamespace == null) ? ("," + s2) : (xsDocumentInfo.fTargetNamespace + "," + s2);
            final int changeRedefineGroup2 = this.changeRedefineGroup(s5, s, s3, element, xsDocumentInfo);
            if (changeRedefineGroup2 > 1) {
                this.reportSchemaError("src-redefine.6.1.1", new Object[] { new Integer(changeRedefineGroup2) }, element);
            }
            else if (changeRedefineGroup2 != 1) {
                if (xsDocumentInfo.fTargetNamespace == null) {
                    this.fRedefinedRestrictedGroupRegistry.put(s5, "," + s3);
                }
                else {
                    this.fRedefinedRestrictedGroupRegistry.put(s5, xsDocumentInfo.fTargetNamespace + "," + s3);
                }
            }
        }
        else {
            this.reportSchemaError("Internal-Error", new Object[] { "could not handle this particular <redefine>; please submit your schemas and instance document in a bug report!" }, element);
        }
    }
    
    private String findQName(final String s, final XSDocumentInfo xsDocumentInfo) {
        final SchemaNamespaceSupport fNamespaceSupport = xsDocumentInfo.fNamespaceSupport;
        final int index = s.indexOf(58);
        String s2 = XMLSymbols.EMPTY_STRING;
        if (index > 0) {
            s2 = s.substring(0, index);
        }
        String s3 = fNamespaceSupport.getURI(this.fSymbolTable.addSymbol(s2));
        final String s4 = (index == 0) ? s : s.substring(index + 1);
        if (s2 == XMLSymbols.EMPTY_STRING && s3 == null && xsDocumentInfo.fIsChameleonSchema) {
            s3 = xsDocumentInfo.fTargetNamespace;
        }
        if (s3 == null) {
            return "," + s4;
        }
        return s3 + "," + s4;
    }
    
    private int changeRedefineGroup(final String s, final String s2, final String s3, final Element element, final XSDocumentInfo xsDocumentInfo) {
        int n = 0;
        for (Element element2 = DOMUtil.getFirstChildElement(element); element2 != null; element2 = DOMUtil.getNextSiblingElement(element2)) {
            if (!DOMUtil.getLocalName(element2).equals(s2)) {
                n += this.changeRedefineGroup(s, s2, s3, element2, xsDocumentInfo);
            }
            else {
                final String attribute = element2.getAttribute(SchemaSymbols.ATT_REF);
                if (attribute.length() != 0 && s.equals(this.findQName(attribute, xsDocumentInfo))) {
                    final String empty_STRING = XMLSymbols.EMPTY_STRING;
                    final int index = attribute.indexOf(":");
                    if (index > 0) {
                        element2.setAttribute(SchemaSymbols.ATT_REF, attribute.substring(0, index) + ":" + s3);
                    }
                    else {
                        element2.setAttribute(SchemaSymbols.ATT_REF, s3);
                    }
                    ++n;
                    if (s2.equals(SchemaSymbols.ELT_GROUP)) {
                        final String attribute2 = element2.getAttribute(SchemaSymbols.ATT_MINOCCURS);
                        final String attribute3 = element2.getAttribute(SchemaSymbols.ATT_MAXOCCURS);
                        if ((attribute3.length() != 0 && !attribute3.equals("1")) || (attribute2.length() != 0 && !attribute2.equals("1"))) {
                            this.reportSchemaError("src-redefine.6.1.2", new Object[] { attribute }, element2);
                        }
                    }
                }
            }
        }
        return n;
    }
    
    private XSDocumentInfo findXSDocumentForDecl(final XSDocumentInfo xsDocumentInfo, final Element element, final XSDocumentInfo xsDocumentInfo2) {
        if (xsDocumentInfo2 == null) {
            return null;
        }
        return xsDocumentInfo2;
    }
    
    private boolean nonAnnotationContent(final Element element) {
        for (Element element2 = DOMUtil.getFirstChildElement(element); element2 != null; element2 = DOMUtil.getNextSiblingElement(element2)) {
            if (!DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_ANNOTATION)) {
                return true;
            }
        }
        return false;
    }
    
    private void setSchemasVisible(final XSDocumentInfo xsDocumentInfo) {
        if (DOMUtil.isHidden(xsDocumentInfo.fSchemaElement, this.fHiddenNodes)) {
            DOMUtil.setVisible(xsDocumentInfo.fSchemaElement, this.fHiddenNodes);
            final Vector<XSDocumentInfo> vector = this.fDependencyMap.get(xsDocumentInfo);
            for (int i = 0; i < vector.size(); ++i) {
                this.setSchemasVisible(vector.elementAt(i));
            }
        }
    }
    
    public SimpleLocator element2Locator(final Element element) {
        if (!(element instanceof ElementImpl)) {
            return null;
        }
        final SimpleLocator simpleLocator = new SimpleLocator();
        return this.element2Locator(element, simpleLocator) ? simpleLocator : null;
    }
    
    public boolean element2Locator(final Element element, final SimpleLocator simpleLocator) {
        if (simpleLocator == null) {
            return false;
        }
        if (element instanceof ElementImpl) {
            final ElementImpl elementImpl = (ElementImpl)element;
            final String s = this.fDoc2SystemId.get(DOMUtil.getRoot(elementImpl.getOwnerDocument()));
            simpleLocator.setValues(s, s, elementImpl.getLineNumber(), elementImpl.getColumnNumber(), elementImpl.getCharacterOffset());
            return true;
        }
        return false;
    }
    
    void reportSchemaError(final String s, final Object[] array, final Element element) {
        if (this.element2Locator(element, this.xl)) {
            this.fErrorReporter.reportError(this.xl, "http://www.w3.org/TR/xml-schema-1", s, array, (short)1);
        }
        else {
            this.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", s, array, (short)1);
        }
    }
    
    void reportSchemaWarning(final String s, final Object[] array, final Element element) {
        if (this.element2Locator(element, this.xl)) {
            this.fErrorReporter.reportError(this.xl, "http://www.w3.org/TR/xml-schema-1", s, array, (short)0);
        }
        else {
            this.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", s, array, (short)0);
        }
    }
    
    public void setGenerateSyntheticAnnotations(final boolean b) {
        this.fSchemaParser.setFeature("http://apache.org/xml/features/generate-synthetic-annotations", b);
    }
    
    static {
        EMPTY_TABLE = new Hashtable();
        NS_ERROR_CODES = new String[][] { { "src-include.2.1", "src-include.2.1" }, { "src-redefine.3.1", "src-redefine.3.1" }, { "src-import.3.1", "src-import.3.2" }, null, { "TargetNamespace.1", "TargetNamespace.2" }, { "TargetNamespace.1", "TargetNamespace.2" }, { "TargetNamespace.1", "TargetNamespace.2" }, { "TargetNamespace.1", "TargetNamespace.2" } };
        ELE_ERROR_CODES = new String[] { "src-include.1", "src-redefine.2", "src-import.2", "schema_reference.4", "schema_reference.4", "schema_reference.4", "schema_reference.4", "schema_reference.4" };
        COMP_TYPE = new String[] { null, "attribute declaration", "attribute group", "element declaration", "group", "identity constraint", "notation", "type definition" };
        CIRCULAR_CODES = new String[] { "Internal-Error", "Internal-Error", "src-attribute_group.3", "e-props-correct.6", "mg-props-correct.2", "Internal-Error", "Internal-Error", "st-props-correct.2" };
    }
    
    private static class XSDKey
    {
        String systemId;
        short referType;
        String referNS;
        
        XSDKey(final String systemId, final short referType, final String referNS) {
            this.systemId = systemId;
            this.referType = referType;
            this.referNS = referNS;
        }
        
        public int hashCode() {
            return (this.referNS == null) ? 0 : this.referNS.hashCode();
        }
        
        public boolean equals(final Object o) {
            if (!(o instanceof XSDKey)) {
                return false;
            }
            final XSDKey xsdKey = (XSDKey)o;
            return ((this.referType != 1 && xsdKey.referType != 1) || this.referType == xsdKey.referType) && this.referNS == xsdKey.referNS && this.systemId != null && this.systemId.equals(xsdKey.systemId);
        }
    }
    
    private static class XSAnnotationGrammarPool implements XMLGrammarPool
    {
        private XSGrammarBucket fGrammarBucket;
        private Grammar[] fInitialGrammarSet;
        
        public Grammar[] retrieveInitialGrammarSet(final String s) {
            if (s == "http://www.w3.org/2001/XMLSchema") {
                if (this.fInitialGrammarSet == null) {
                    if (this.fGrammarBucket == null) {
                        this.fInitialGrammarSet = new Grammar[] { SchemaGrammar.SG_Schema4Annotations };
                    }
                    else {
                        final SchemaGrammar[] grammars = this.fGrammarBucket.getGrammars();
                        for (int i = 0; i < grammars.length; ++i) {
                            if (SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(grammars[i].getTargetNamespace())) {
                                return this.fInitialGrammarSet = grammars;
                            }
                        }
                        final Grammar[] fInitialGrammarSet = new Grammar[grammars.length + 1];
                        System.arraycopy(grammars, 0, fInitialGrammarSet, 0, grammars.length);
                        fInitialGrammarSet[fInitialGrammarSet.length - 1] = SchemaGrammar.SG_Schema4Annotations;
                        this.fInitialGrammarSet = fInitialGrammarSet;
                    }
                }
                return this.fInitialGrammarSet;
            }
            return new Grammar[0];
        }
        
        public void cacheGrammars(final String s, final Grammar[] array) {
        }
        
        public Grammar retrieveGrammar(final XMLGrammarDescription xmlGrammarDescription) {
            if (xmlGrammarDescription.getGrammarType() == "http://www.w3.org/2001/XMLSchema") {
                final String targetNamespace = ((XMLSchemaDescription)xmlGrammarDescription).getTargetNamespace();
                if (this.fGrammarBucket != null) {
                    final SchemaGrammar grammar = this.fGrammarBucket.getGrammar(targetNamespace);
                    if (grammar != null) {
                        return grammar;
                    }
                }
                if (SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(targetNamespace)) {
                    return SchemaGrammar.SG_Schema4Annotations;
                }
            }
            return null;
        }
        
        public void refreshGrammars(final XSGrammarBucket fGrammarBucket) {
            this.fGrammarBucket = fGrammarBucket;
            this.fInitialGrammarSet = null;
        }
        
        public void lockPool() {
        }
        
        public void unlockPool() {
        }
        
        public void clear() {
        }
    }
}
