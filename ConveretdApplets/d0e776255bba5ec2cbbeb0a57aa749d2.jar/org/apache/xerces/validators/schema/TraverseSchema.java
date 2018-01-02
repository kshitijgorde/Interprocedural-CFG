// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.schema;

import org.apache.xerces.parsers.DOMParser;
import org.xml.sax.SAXParseException;
import org.apache.xerces.validators.schema.identity.XPath;
import org.apache.xerces.validators.schema.identity.Field;
import org.apache.xerces.validators.schema.identity.XPathException;
import org.apache.xerces.validators.schema.identity.Selector;
import org.apache.xerces.validators.schema.identity.KeyRef;
import org.apache.xerces.validators.schema.identity.Key;
import org.apache.xerces.validators.schema.identity.IdentityConstraint;
import org.apache.xerces.validators.schema.identity.Unique;
import org.apache.xerces.validators.datatype.InvalidDatatypeValueException;
import org.apache.xerces.framework.XMLContentSpec;
import org.apache.xerces.validators.datatype.NOTATIONDatatypeValidator;
import org.apache.xerces.validators.datatype.UnionDatatypeValidator;
import java.util.StringTokenizer;
import org.apache.xerces.validators.datatype.DatatypeValidator;
import org.apache.xerces.utils.QName;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.io.IOException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Attr;
import org.apache.xerces.validators.common.Grammar;
import org.xml.sax.EntityResolver;
import org.apache.xerces.validators.common.XMLElementDecl;
import org.apache.xerces.validators.common.XMLAttributeDecl;
import java.util.Stack;
import java.util.Vector;
import java.util.Hashtable;
import org.apache.xerces.validators.datatype.DatatypeValidatorFactoryImpl;
import org.w3c.dom.Element;
import org.apache.xerces.validators.common.GrammarResolver;
import org.apache.xerces.utils.StringPool;
import org.apache.xerces.framework.XMLErrorReporter;
import org.apache.xerces.utils.NamespacesScope;

public class TraverseSchema implements NamespacesScope.NamespacesHandler
{
    private static final int TOP_LEVEL_SCOPE = -1;
    private static final String[] IDENTITY_CONSTRAINTS;
    private static final String redefIdentifier = "#redefined";
    private static final boolean DEBUGGING = false;
    private static final boolean DEBUG_IDENTITY_CONSTRAINTS = false;
    private static final boolean DEBUG_IC_DATATYPES = false;
    private static final boolean DEBUG_UNION = false;
    private static final boolean CR_IMPL = true;
    private XMLErrorReporter fErrorReporter;
    private StringPool fStringPool;
    private GrammarResolver fGrammarResolver;
    private SchemaGrammar fSchemaGrammar;
    private Element fSchemaRootElement;
    private SchemaInfo fSchemaInfoListRoot;
    private SchemaInfo fCurrentSchemaInfo;
    private boolean fRedefineSucceeded;
    private DatatypeValidatorFactoryImpl fDatatypeRegistry;
    private Hashtable fComplexTypeRegistry;
    private Hashtable fAttributeDeclRegistry;
    private Hashtable fGroupNameRegistry;
    private Hashtable fNotationRegistry;
    private Vector fIncludeLocations;
    private Vector fImportLocations;
    private Hashtable fRedefineLocations;
    private Vector fTraversedRedefineElements;
    private int fAnonTypeCount;
    private int fScopeCount;
    private int fCurrentScope;
    private int fSimpleTypeAnonCount;
    private Stack fCurrentTypeNameStack;
    private Hashtable fElementRecurseComplex;
    private boolean fElementDefaultQualified;
    private boolean fAttributeDefaultQualified;
    private int fTargetNSURI;
    private String fTargetNSURIString;
    private NamespacesScope fNamespacesScope;
    private String fCurrentSchemaURL;
    private XMLAttributeDecl fTempAttributeDecl;
    private XMLElementDecl fTempElementDecl;
    private EntityResolver fEntityResolver;
    private Hashtable fIdentityConstraints;
    public static final String SchemaForSchemaURI = "http://www.w3.org/TR-1/Schema";
    
    private TraverseSchema() {
        this.fErrorReporter = null;
        this.fStringPool = null;
        this.fGrammarResolver = null;
        this.fSchemaGrammar = null;
        this.fSchemaInfoListRoot = null;
        this.fCurrentSchemaInfo = null;
        this.fDatatypeRegistry = null;
        this.fComplexTypeRegistry = new Hashtable();
        this.fAttributeDeclRegistry = new Hashtable();
        this.fGroupNameRegistry = new Hashtable();
        this.fNotationRegistry = new Hashtable();
        this.fIncludeLocations = new Vector();
        this.fImportLocations = new Vector();
        this.fRedefineLocations = new Hashtable();
        this.fTraversedRedefineElements = new Vector();
        this.fAnonTypeCount = 0;
        this.fScopeCount = 0;
        this.fCurrentScope = -1;
        this.fSimpleTypeAnonCount = 0;
        this.fCurrentTypeNameStack = new Stack();
        this.fElementRecurseComplex = new Hashtable();
        this.fElementDefaultQualified = false;
        this.fAttributeDefaultQualified = false;
        this.fTargetNSURIString = "";
        this.fNamespacesScope = null;
        this.fCurrentSchemaURL = "";
        this.fTempAttributeDecl = new XMLAttributeDecl();
        this.fTempElementDecl = new XMLElementDecl();
        this.fEntityResolver = null;
        this.fIdentityConstraints = new Hashtable();
    }
    
    public void setGrammarResolver(final GrammarResolver fGrammarResolver) {
        this.fGrammarResolver = fGrammarResolver;
    }
    
    public void startNamespaceDeclScope(final int n, final int n2) {
    }
    
    public void endNamespaceDeclScope(final int n) {
    }
    
    private String resolvePrefixToURI(final String s) throws Exception {
        String string = this.fStringPool.toString(this.fNamespacesScope.getNamespaceForPrefix(this.fStringPool.addSymbol(s)));
        if (string == null) {
            this.reportGenericSchemaError("prefix : [" + s + "] can not be resolved to a URI");
            return "";
        }
        if (s.length() == 0 && string.equals("http://www.w3.org/2000/10/XMLSchema") && this.fTargetNSURIString.length() == 0) {
            string = "";
        }
        return string;
    }
    
    public TraverseSchema(final Element element, final StringPool stringPool, final SchemaGrammar schemaGrammar, final GrammarResolver grammarResolver, final XMLErrorReporter fErrorReporter, final String fCurrentSchemaURL, final EntityResolver fEntityResolver) throws Exception {
        this.fErrorReporter = null;
        this.fStringPool = null;
        this.fGrammarResolver = null;
        this.fSchemaGrammar = null;
        this.fSchemaInfoListRoot = null;
        this.fCurrentSchemaInfo = null;
        this.fDatatypeRegistry = null;
        this.fComplexTypeRegistry = new Hashtable();
        this.fAttributeDeclRegistry = new Hashtable();
        this.fGroupNameRegistry = new Hashtable();
        this.fNotationRegistry = new Hashtable();
        this.fIncludeLocations = new Vector();
        this.fImportLocations = new Vector();
        this.fRedefineLocations = new Hashtable();
        this.fTraversedRedefineElements = new Vector();
        this.fAnonTypeCount = 0;
        this.fScopeCount = 0;
        this.fCurrentScope = -1;
        this.fSimpleTypeAnonCount = 0;
        this.fCurrentTypeNameStack = new Stack();
        this.fElementRecurseComplex = new Hashtable();
        this.fElementDefaultQualified = false;
        this.fAttributeDefaultQualified = false;
        this.fTargetNSURIString = "";
        this.fNamespacesScope = null;
        this.fCurrentSchemaURL = "";
        this.fTempAttributeDecl = new XMLAttributeDecl();
        this.fTempElementDecl = new XMLElementDecl();
        this.fEntityResolver = null;
        this.fIdentityConstraints = new Hashtable();
        this.fErrorReporter = fErrorReporter;
        this.fCurrentSchemaURL = fCurrentSchemaURL;
        this.fEntityResolver = fEntityResolver;
        this.doTraverseSchema(element, stringPool, schemaGrammar, grammarResolver);
    }
    
    public TraverseSchema(final Element element, final StringPool stringPool, final SchemaGrammar schemaGrammar, final GrammarResolver grammarResolver, final XMLErrorReporter fErrorReporter, final String fCurrentSchemaURL) throws Exception {
        this.fErrorReporter = null;
        this.fStringPool = null;
        this.fGrammarResolver = null;
        this.fSchemaGrammar = null;
        this.fSchemaInfoListRoot = null;
        this.fCurrentSchemaInfo = null;
        this.fDatatypeRegistry = null;
        this.fComplexTypeRegistry = new Hashtable();
        this.fAttributeDeclRegistry = new Hashtable();
        this.fGroupNameRegistry = new Hashtable();
        this.fNotationRegistry = new Hashtable();
        this.fIncludeLocations = new Vector();
        this.fImportLocations = new Vector();
        this.fRedefineLocations = new Hashtable();
        this.fTraversedRedefineElements = new Vector();
        this.fAnonTypeCount = 0;
        this.fScopeCount = 0;
        this.fCurrentScope = -1;
        this.fSimpleTypeAnonCount = 0;
        this.fCurrentTypeNameStack = new Stack();
        this.fElementRecurseComplex = new Hashtable();
        this.fElementDefaultQualified = false;
        this.fAttributeDefaultQualified = false;
        this.fTargetNSURIString = "";
        this.fNamespacesScope = null;
        this.fCurrentSchemaURL = "";
        this.fTempAttributeDecl = new XMLAttributeDecl();
        this.fTempElementDecl = new XMLElementDecl();
        this.fEntityResolver = null;
        this.fIdentityConstraints = new Hashtable();
        this.fErrorReporter = fErrorReporter;
        this.fCurrentSchemaURL = fCurrentSchemaURL;
        this.doTraverseSchema(element, stringPool, schemaGrammar, grammarResolver);
    }
    
    public TraverseSchema(final Element element, final StringPool stringPool, final SchemaGrammar schemaGrammar, final GrammarResolver grammarResolver) throws Exception {
        this.fErrorReporter = null;
        this.fStringPool = null;
        this.fGrammarResolver = null;
        this.fSchemaGrammar = null;
        this.fSchemaInfoListRoot = null;
        this.fCurrentSchemaInfo = null;
        this.fDatatypeRegistry = null;
        this.fComplexTypeRegistry = new Hashtable();
        this.fAttributeDeclRegistry = new Hashtable();
        this.fGroupNameRegistry = new Hashtable();
        this.fNotationRegistry = new Hashtable();
        this.fIncludeLocations = new Vector();
        this.fImportLocations = new Vector();
        this.fRedefineLocations = new Hashtable();
        this.fTraversedRedefineElements = new Vector();
        this.fAnonTypeCount = 0;
        this.fScopeCount = 0;
        this.fCurrentScope = -1;
        this.fSimpleTypeAnonCount = 0;
        this.fCurrentTypeNameStack = new Stack();
        this.fElementRecurseComplex = new Hashtable();
        this.fElementDefaultQualified = false;
        this.fAttributeDefaultQualified = false;
        this.fTargetNSURIString = "";
        this.fNamespacesScope = null;
        this.fCurrentSchemaURL = "";
        this.fTempAttributeDecl = new XMLAttributeDecl();
        this.fTempElementDecl = new XMLElementDecl();
        this.fEntityResolver = null;
        this.fIdentityConstraints = new Hashtable();
        this.doTraverseSchema(element, stringPool, schemaGrammar, grammarResolver);
    }
    
    public void doTraverseSchema(final Element fSchemaRootElement, final StringPool fStringPool, final SchemaGrammar fSchemaGrammar, final GrammarResolver fGrammarResolver) throws Exception {
        this.fNamespacesScope = new NamespacesScope(this);
        this.fSchemaRootElement = fSchemaRootElement;
        this.fStringPool = fStringPool;
        this.fSchemaGrammar = fSchemaGrammar;
        this.fGrammarResolver = fGrammarResolver;
        (this.fDatatypeRegistry = (DatatypeValidatorFactoryImpl)this.fGrammarResolver.getDatatypeRegistry()).expandRegistryToFullSchemaSet();
        if (fSchemaRootElement == null) {
            return;
        }
        final String prefix = fSchemaRootElement.getPrefix();
        if ((prefix == null || prefix.length() == 0) && fSchemaRootElement.getAttribute("xmlns").length() == 0) {
            fSchemaRootElement.setAttribute("xmlns", "http://www.w3.org/2000/10/XMLSchema");
        }
        this.fTargetNSURIString = fSchemaRootElement.getAttribute("targetNamespace");
        if (this.fTargetNSURIString == null) {
            this.fTargetNSURIString = "";
        }
        this.fTargetNSURI = this.fStringPool.addSymbol(this.fTargetNSURIString);
        if (this.fGrammarResolver == null) {
            this.reportGenericSchemaError("Internal error: don't have a GrammarResolver for TraverseSchema");
        }
        else {
            if (this.fSchemaGrammar.getComplexTypeRegistry() == null) {
                this.fSchemaGrammar.setComplexTypeRegistry(this.fComplexTypeRegistry);
            }
            else {
                this.fComplexTypeRegistry = this.fSchemaGrammar.getComplexTypeRegistry();
            }
            if (this.fSchemaGrammar.getAttirubteDeclRegistry() == null) {
                this.fSchemaGrammar.setAttributeDeclRegistry(this.fAttributeDeclRegistry);
            }
            else {
                this.fAttributeDeclRegistry = this.fSchemaGrammar.getAttirubteDeclRegistry();
            }
            if (this.fSchemaGrammar.getNamespacesScope() == null) {
                this.fSchemaGrammar.setNamespacesScope(this.fNamespacesScope);
            }
            else {
                this.fNamespacesScope = this.fSchemaGrammar.getNamespacesScope();
            }
            this.fSchemaGrammar.setDatatypeRegistry(this.fDatatypeRegistry);
            this.fSchemaGrammar.setTargetNamespaceURI(this.fTargetNSURIString);
            this.fGrammarResolver.putGrammar(this.fTargetNSURIString, this.fSchemaGrammar);
        }
        final NamedNodeMap attributes = fSchemaRootElement.getAttributes();
        int n = 0;
        boolean b = false;
        Attr attr;
        while ((attr = (Attr)attributes.item(n++)) != null) {
            final String name = attr.getName();
            if (name.startsWith("xmlns:")) {
                this.fNamespacesScope.setNamespaceForPrefix(this.fStringPool.addSymbol(name.substring(name.indexOf(":") + 1)), this.fStringPool.addSymbol(attr.getValue()));
            }
            if (name.equals("xmlns")) {
                this.fNamespacesScope.setNamespaceForPrefix(this.fStringPool.addSymbol(""), this.fStringPool.addSymbol(attr.getValue()));
                b = true;
            }
        }
        if (!b && this.fTargetNSURIString.length() == 0) {
            this.fNamespacesScope.setNamespaceForPrefix(this.fStringPool.addSymbol(""), this.fStringPool.addSymbol(""));
        }
        this.fElementDefaultQualified = fSchemaRootElement.getAttribute("elementFormDefault").equals("qualified");
        this.fAttributeDefaultQualified = fSchemaRootElement.getAttribute("attributeFormDefault").equals("qualified");
        if (this.fTargetNSURI == 0) {}
        this.fCurrentScope = -1;
        this.checkTopLevelDuplicateNames(fSchemaRootElement);
        this.extractTopLevel3Components(fSchemaRootElement);
        while (true) {
            for (Element element = XUtil.getFirstChildElement(fSchemaRootElement); element != null; element = XUtil.getNextSiblingElement(element)) {
                final String localName = element.getLocalName();
                if (localName.equals("annotation")) {
                    this.traverseAnnotationDecl(element);
                }
                else if (localName.equals("include")) {
                    this.traverseInclude(element);
                }
                else if (localName.equals("import")) {
                    this.traverseImport(element);
                }
                else {
                    if (!localName.equals("redefine")) {
                        while (element != null) {
                            final String localName2 = element.getLocalName();
                            if (localName2.equals("annotation")) {
                                this.traverseAnnotationDecl(element);
                            }
                            else if (localName2.equals("simpleType")) {
                                this.traverseSimpleTypeDecl(element);
                            }
                            else if (localName2.equals("complexType")) {
                                this.traverseComplexTypeDecl(element);
                            }
                            else if (localName2.equals("element")) {
                                this.traverseElementDecl(element);
                            }
                            else if (localName2.equals("attributeGroup")) {
                                this.traverseAttributeGroupDecl(element, null, null);
                            }
                            else if (localName2.equals("attribute")) {
                                this.traverseAttributeDecl(element, null, false);
                            }
                            else if (localName2.equals("group")) {
                                this.traverseGroupDecl(element);
                            }
                            else if (localName2.equals("notation")) {
                                this.traverseNotationDecl(element);
                            }
                            else {
                                this.reportGenericSchemaError("error in content of <schema> element information item");
                            }
                            element = XUtil.getNextSiblingElement(element);
                        }
                        final Enumeration<Integer> keys = this.fIdentityConstraints.keys();
                        while (keys.hasMoreElements()) {
                            final Integer n2 = keys.nextElement();
                            final Vector vector = this.fIdentityConstraints.get(n2);
                            if (vector != null) {
                                this.traverseIdentityConstraintsFor(n2, vector);
                            }
                        }
                        return;
                    }
                    this.fRedefineSucceeded = true;
                    this.traverseRedefine(element);
                }
            }
            continue;
        }
    }
    
    private void checkTopLevelDuplicateNames(final Element element) {
    }
    
    private void extractTopLevel3Components(final Element element) {
        for (Element element2 = XUtil.getFirstChildElement(element); element2 != null; element2 = XUtil.getNextSiblingElement(element2)) {
            final String localName = element2.getLocalName();
            final String attribute = element2.getAttribute("name");
            if (localName.equals("attributeGroup")) {
                this.fSchemaGrammar.topLevelAttrGrpDecls.put(attribute, element2);
            }
            else if (localName.equals("attribute")) {
                this.fSchemaGrammar.topLevelAttrDecls.put(attribute, element2);
            }
            else if (localName.equals("group")) {
                this.fSchemaGrammar.topLevelGroupDecls.put(attribute, element2);
            }
            else if (localName.equals("notation")) {
                this.fSchemaGrammar.topLevelNotationDecls.put(attribute, element2);
            }
        }
    }
    
    private String expandSystemId(final String s, final String s2) throws Exception {
        if (s == null || s.length() == 0) {
            return s;
        }
        try {
            if (new URL(s) != null) {
                return s;
            }
        }
        catch (MalformedURLException ex) {}
        final String fixURI = fixURI(s);
        URL url = null;
        try {
            URL url2;
            if (s2 == null) {
                String s3;
                try {
                    s3 = fixURI(System.getProperty("user.dir"));
                }
                catch (SecurityException ex2) {
                    s3 = "";
                }
                if (!s3.endsWith("/")) {
                    s3 += "/";
                }
                url2 = new URL("file", "", s3);
            }
            else {
                url2 = new URL(s2);
            }
            url = new URL(url2, fixURI);
        }
        catch (Exception ex3) {}
        if (url == null) {
            return s;
        }
        return url.toString();
    }
    
    private static String fixURI(String s) {
        s = s.replace(File.separatorChar, '/');
        if (s.length() >= 2 && s.charAt(1) == ':') {
            final char upperCase = Character.toUpperCase(s.charAt(0));
            if (upperCase >= 'A' && upperCase <= 'Z') {
                s = "/" + s;
            }
        }
        return s;
    }
    
    private void traverseInclude(final Element element) throws Exception {
        final Attr attributeNode = element.getAttributeNode("schemaLocation");
        if (attributeNode == null) {
            this.reportGenericSchemaError("a schemaLocation attribute must be specified on an <include> element");
            return;
        }
        String s = attributeNode.getValue();
        InputSource resolveEntity = null;
        if (this.fEntityResolver != null) {
            resolveEntity = this.fEntityResolver.resolveEntity("", s);
        }
        String fCurrentSchemaURL;
        if (resolveEntity == null) {
            fCurrentSchemaURL = this.expandSystemId(s, this.fCurrentSchemaURL);
            resolveEntity = new InputSource(fCurrentSchemaURL);
        }
        else {
            if (resolveEntity.getPublicId() != null) {
                s = resolveEntity.getPublicId();
            }
            fCurrentSchemaURL = s + ',' + resolveEntity.getSystemId();
        }
        if (this.fIncludeLocations.contains(fCurrentSchemaURL)) {
            return;
        }
        this.fIncludeLocations.addElement(fCurrentSchemaURL);
        final IgnoreWhitespaceParser ignoreWhitespaceParser = new IgnoreWhitespaceParser();
        ignoreWhitespaceParser.setEntityResolver(new Resolver());
        ignoreWhitespaceParser.setErrorHandler(new ErrorHandler());
        try {
            ignoreWhitespaceParser.setFeature("http://xml.org/sax/features/validation", false);
            ignoreWhitespaceParser.setFeature("http://xml.org/sax/features/namespaces", true);
            ignoreWhitespaceParser.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false);
        }
        catch (SAXNotRecognizedException ex) {
            ex.printStackTrace();
        }
        catch (SAXNotSupportedException ex2) {
            ex2.printStackTrace();
        }
        try {
            ignoreWhitespaceParser.parse(resolveEntity);
        }
        catch (IOException ex3) {
            ex3.printStackTrace();
        }
        catch (SAXException ex4) {}
        final Document document = ignoreWhitespaceParser.getDocument();
        Element documentElement = null;
        if (document != null) {
            documentElement = document.getDocumentElement();
        }
        if (documentElement != null) {
            final String attribute = documentElement.getAttribute("targetNamespace");
            if (attribute.length() > 0 && !attribute.equals(this.fTargetNSURIString)) {
                this.reportGenericSchemaError("included schema '" + fCurrentSchemaURL + "' has a different targetNameSpace '" + attribute + "'");
            }
            else {
                if (this.fSchemaInfoListRoot == null) {
                    this.fSchemaInfoListRoot = new SchemaInfo(this.fElementDefaultQualified, this.fAttributeDefaultQualified, this.fCurrentScope, this.fCurrentSchemaURL, this.fSchemaRootElement, null, null);
                    this.fCurrentSchemaInfo = this.fSchemaInfoListRoot;
                }
                this.fSchemaRootElement = documentElement;
                this.fCurrentSchemaURL = fCurrentSchemaURL;
                this.traverseIncludedSchemaHeader(documentElement);
                this.fCurrentSchemaInfo = new SchemaInfo(this.fElementDefaultQualified, this.fAttributeDefaultQualified, this.fCurrentScope, this.fCurrentSchemaURL, this.fSchemaRootElement, this.fCurrentSchemaInfo.getNext(), this.fCurrentSchemaInfo);
                this.fCurrentSchemaInfo.getPrev().setNext(this.fCurrentSchemaInfo);
                this.traverseIncludedSchema(documentElement);
                (this.fCurrentSchemaInfo = this.fCurrentSchemaInfo.getPrev()).restore();
            }
        }
    }
    
    private void traverseIncludedSchemaHeader(final Element element) throws Exception {
        final NamedNodeMap attributes = element.getAttributes();
        int n = 0;
        boolean b = false;
        Attr attr;
        while ((attr = (Attr)attributes.item(n++)) != null) {
            final String name = attr.getName();
            if (name.startsWith("xmlns:")) {
                this.fNamespacesScope.setNamespaceForPrefix(this.fStringPool.addSymbol(name.substring(name.indexOf(":") + 1)), this.fStringPool.addSymbol(attr.getValue()));
            }
            if (name.equals("xmlns")) {
                this.fNamespacesScope.setNamespaceForPrefix(this.fStringPool.addSymbol(""), this.fStringPool.addSymbol(attr.getValue()));
                b = true;
            }
        }
        if (!b && this.fTargetNSURIString.length() == 0) {
            this.fNamespacesScope.setNamespaceForPrefix(this.fStringPool.addSymbol(""), this.fStringPool.addSymbol(""));
        }
        this.fElementDefaultQualified = element.getAttribute("elementFormDefault").equals("qualified");
        this.fAttributeDefaultQualified = element.getAttribute("attributeFormDefault").equals("qualified");
        if (this.fTargetNSURI == 0) {
            this.fElementDefaultQualified = true;
        }
        this.fCurrentScope = -1;
    }
    
    private void traverseIncludedSchema(final Element element) throws Exception {
        this.checkTopLevelDuplicateNames(element);
        this.extractTopLevel3Components(element);
        while (true) {
            for (Element element2 = XUtil.getFirstChildElement(element); element2 != null; element2 = XUtil.getNextSiblingElement(element2)) {
                final String localName = element2.getLocalName();
                if (localName.equals("annotation")) {
                    this.traverseAnnotationDecl(element2);
                }
                else if (localName.equals("include")) {
                    this.traverseInclude(element2);
                }
                else if (localName.equals("import")) {
                    this.traverseImport(element2);
                }
                else {
                    if (!localName.equals("redefine")) {
                        while (element2 != null) {
                            final String localName2 = element2.getLocalName();
                            if (localName2.equals("annotation")) {
                                this.traverseAnnotationDecl(element2);
                            }
                            else if (localName2.equals("simpleType")) {
                                this.traverseSimpleTypeDecl(element2);
                            }
                            else if (localName2.equals("complexType")) {
                                this.traverseComplexTypeDecl(element2);
                            }
                            else if (localName2.equals("element")) {
                                this.traverseElementDecl(element2);
                            }
                            else if (localName2.equals("attributeGroup")) {
                                this.traverseAttributeGroupDecl(element2, null, null);
                            }
                            else if (localName2.equals("attribute")) {
                                this.traverseAttributeDecl(element2, null, false);
                            }
                            else if (localName2.equals("group")) {
                                this.traverseGroupDecl(element2);
                            }
                            else if (localName2.equals("notation")) {
                                this.traverseNotationDecl(element2);
                            }
                            else {
                                this.reportGenericSchemaError("error in content of included <schema> element information item");
                            }
                            element2 = XUtil.getNextSiblingElement(element2);
                        }
                        return;
                    }
                    this.fRedefineSucceeded = true;
                    this.traverseRedefine(element2);
                }
            }
            continue;
        }
    }
    
    private void openRedefinedSchema(final Element element, final SchemaInfo prev) throws Exception {
        final Attr attributeNode = element.getAttributeNode("schemaLocation");
        if (attributeNode == null) {
            this.fRedefineSucceeded = false;
            this.reportGenericSchemaError("a schemaLocation attribute must be specified on a <redefine> element");
            return;
        }
        String s = attributeNode.getValue();
        InputSource resolveEntity = null;
        if (this.fEntityResolver != null) {
            resolveEntity = this.fEntityResolver.resolveEntity("", s);
        }
        String fCurrentSchemaURL;
        if (resolveEntity == null) {
            fCurrentSchemaURL = this.expandSystemId(s, this.fCurrentSchemaURL);
            resolveEntity = new InputSource(fCurrentSchemaURL);
        }
        else {
            if (resolveEntity.getPublicId() != null) {
                s = resolveEntity.getPublicId();
            }
            fCurrentSchemaURL = s + ',' + resolveEntity.getSystemId();
        }
        if (this.fRedefineLocations.get(fCurrentSchemaURL) != null) {
            (this.fCurrentSchemaInfo = (SchemaInfo)this.fRedefineLocations.get(fCurrentSchemaURL)).restore();
            return;
        }
        final IgnoreWhitespaceParser ignoreWhitespaceParser = new IgnoreWhitespaceParser();
        ignoreWhitespaceParser.setEntityResolver(new Resolver());
        ignoreWhitespaceParser.setErrorHandler(new ErrorHandler());
        try {
            ignoreWhitespaceParser.setFeature("http://xml.org/sax/features/validation", false);
            ignoreWhitespaceParser.setFeature("http://xml.org/sax/features/namespaces", true);
            ignoreWhitespaceParser.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false);
        }
        catch (SAXNotRecognizedException ex) {
            ex.printStackTrace();
        }
        catch (SAXNotSupportedException ex2) {
            ex2.printStackTrace();
        }
        try {
            ignoreWhitespaceParser.parse(resolveEntity);
        }
        catch (IOException ex3) {
            ex3.printStackTrace();
        }
        catch (SAXException ex4) {}
        final Document document = ignoreWhitespaceParser.getDocument();
        Element documentElement = null;
        if (document != null) {
            documentElement = document.getDocumentElement();
        }
        if (documentElement == null) {
            this.fRedefineSucceeded = false;
            return;
        }
        final String attribute = documentElement.getAttribute("targetNamespace");
        if (attribute.length() > 0 && !attribute.equals(this.fTargetNSURIString)) {
            this.fRedefineSucceeded = false;
            this.reportGenericSchemaError("redefined schema '" + fCurrentSchemaURL + "' has a different targetNameSpace '" + attribute + "' from the original schema");
        }
        else {
            this.fSchemaRootElement = documentElement;
            this.fCurrentSchemaURL = fCurrentSchemaURL;
            this.traverseIncludedSchemaHeader(documentElement);
            prev.setNext(new SchemaInfo(this.fElementDefaultQualified, this.fAttributeDefaultQualified, this.fCurrentScope, this.fCurrentSchemaURL, this.fSchemaRootElement, null, prev));
            prev.getNext().setPrev(prev);
            this.fCurrentSchemaInfo = prev.getNext();
            this.fRedefineLocations.put(fCurrentSchemaURL, prev.getNext());
        }
    }
    
    private void traverseRedefine(final Element element) throws Exception {
        if (this.fSchemaInfoListRoot == null) {
            this.openRedefinedSchema(element, this.fSchemaInfoListRoot = new SchemaInfo(this.fElementDefaultQualified, this.fAttributeDefaultQualified, this.fCurrentScope, this.fCurrentSchemaURL, this.fSchemaRootElement, null, null));
            if (!this.fRedefineSucceeded) {
                return;
            }
            this.fCurrentSchemaInfo = this.fSchemaInfoListRoot.getNext();
            this.renameRedefinedComponents(element, this.fSchemaInfoListRoot.getNext().getRoot(), this.fSchemaInfoListRoot.getNext());
        }
        else {
            SchemaInfo fCurrentSchemaInfo;
            for (fCurrentSchemaInfo = this.fSchemaInfoListRoot; fCurrentSchemaInfo.getNext() != null; fCurrentSchemaInfo = fCurrentSchemaInfo.getNext()) {}
            (this.fCurrentSchemaInfo = fCurrentSchemaInfo).restore();
            this.openRedefinedSchema(element, this.fCurrentSchemaInfo);
            if (!this.fRedefineSucceeded) {
                return;
            }
            this.renameRedefinedComponents(element, this.fCurrentSchemaInfo.getRoot(), this.fCurrentSchemaInfo);
        }
        this.traverseIncludedSchema(this.fSchemaRootElement);
        for (Element element2 = XUtil.getFirstChildElement(element); element2 != null; element2 = XUtil.getNextSiblingElement(element2)) {
            final String localName = element2.getLocalName();
            if (localName.equals("annotation")) {
                this.traverseAnnotationDecl(element2);
            }
            else if (localName.equals("simpleType")) {
                this.traverseSimpleTypeDecl(element2);
            }
            else if (localName.equals("complexType")) {
                this.traverseComplexTypeDecl(element2);
            }
            else if (localName.equals("group")) {
                this.traverseGroupDecl(element2);
            }
            else if (localName.equals("attributeGroup")) {
                this.traverseAttributeGroupDecl(element2, null, null);
            }
        }
        (this.fCurrentSchemaInfo = this.fCurrentSchemaInfo.getPrev()).restore();
    }
    
    private void renameRedefinedComponents(final Element element, final Element element2, final SchemaInfo schemaInfo) throws Exception {
        for (Element element3 = XUtil.getFirstChildElement(element); element3 != null; element3 = XUtil.getNextSiblingElement(element3)) {
            final String localName = element3.getLocalName();
            if (!localName.equals("annotation")) {
                if (localName.equals("simpleType")) {
                    final String attribute = element3.getAttribute("name");
                    if (!this.fTraversedRedefineElements.contains(attribute)) {
                        if (this.validateRedefineNameChange("simpleType", attribute, attribute + "#redefined", element3)) {
                            this.fixRedefinedSchema("simpleType", attribute, attribute + "#redefined", element2, schemaInfo);
                        }
                    }
                }
                else if (localName.equals("complexType")) {
                    final String attribute2 = element3.getAttribute("name");
                    if (!this.fTraversedRedefineElements.contains(attribute2)) {
                        if (this.validateRedefineNameChange("complexType", attribute2, attribute2 + "#redefined", element3)) {
                            this.fixRedefinedSchema("complexType", attribute2, attribute2 + "#redefined", element2, schemaInfo);
                        }
                    }
                }
                else if (localName.equals("attributeGroup")) {
                    final String attribute3 = element3.getAttribute("name");
                    if (!this.fTraversedRedefineElements.contains(attribute3)) {
                        if (this.validateRedefineNameChange("attributeGroup", attribute3, attribute3 + "#redefined", element3)) {
                            this.fixRedefinedSchema("attributeGroup", attribute3, attribute3 + "#redefined", element2, schemaInfo);
                        }
                    }
                }
                else {
                    if (!localName.equals("group")) {
                        this.fRedefineSucceeded = false;
                        this.reportGenericSchemaError("invalid top-level content for <redefine>");
                        return;
                    }
                    final String attribute4 = element3.getAttribute("name");
                    if (!this.fTraversedRedefineElements.contains(attribute4)) {
                        if (this.validateRedefineNameChange("group", attribute4, attribute4 + "#redefined", element3)) {
                            this.fixRedefinedSchema("group", attribute4, attribute4 + "#redefined", element2, schemaInfo);
                        }
                    }
                }
            }
        }
    }
    
    private int changeRedefineGroup(final QName qName, final String s, final String s2, final Element element) throws Exception {
        int n = 0;
        for (Element element2 = XUtil.getFirstChildElement(element); element2 != null; element2 = XUtil.getNextSiblingElement(element2)) {
            if (!element2.getLocalName().equals(s)) {
                n += this.changeRedefineGroup(qName, s, s2, element2);
            }
            else {
                final String attribute = element2.getAttribute("ref");
                if (!attribute.equals("")) {
                    String substring = "";
                    String substring2 = attribute;
                    final int index = attribute.indexOf(":");
                    if (index > 0) {
                        substring = attribute.substring(0, index);
                        substring2 = attribute.substring(index + 1);
                    }
                    if (qName.equals(new QName(-1, this.fStringPool.addSymbol(substring2), this.fStringPool.addSymbol(substring2), this.fStringPool.addSymbol(this.resolvePrefixToURI(substring))))) {
                        if (substring.equals("")) {
                            element2.setAttribute("ref", s2);
                        }
                        else {
                            element2.setAttribute("ref", substring + ":" + s2);
                        }
                        ++n;
                    }
                }
            }
        }
        return n;
    }
    
    private void fixRedefinedSchema(final String s, final String s2, final String s3, final Element element, final SchemaInfo fCurrentSchemaInfo) throws Exception {
        int n = 0;
        for (Element element2 = XUtil.getFirstChildElement(element); element2 != null; element2 = XUtil.getNextSiblingElement(element2)) {
            final String localName = element2.getLocalName();
            if (localName.equals("redefine")) {
                Element element3 = XUtil.getFirstChildElement(element2);
                while (element3 != null) {
                    if (element3.getLocalName().equals(s) && element3.getAttribute("name").equals(s2)) {
                        n = 1;
                        this.openRedefinedSchema(element2, fCurrentSchemaInfo);
                        if (!this.fRedefineSucceeded) {
                            return;
                        }
                        if (this.validateRedefineNameChange(s, s2, s3 + "#redefined", element3) && fCurrentSchemaInfo.getNext() != null) {
                            this.fixRedefinedSchema(s, s2, s3 + "#redefined", this.fSchemaRootElement, fCurrentSchemaInfo.getNext());
                        }
                        element3.setAttribute("name", s3);
                        this.fTraversedRedefineElements.addElement(s3);
                        fCurrentSchemaInfo.restore();
                        this.fCurrentSchemaInfo = fCurrentSchemaInfo;
                        break;
                    }
                    else {
                        element3 = XUtil.getNextSiblingElement(element3);
                    }
                }
                if (n != 0) {
                    break;
                }
            }
            else if (localName.equals(s)) {
                if (element2.getAttribute("name").equals(s2)) {
                    n = 1;
                    element2.setAttribute("name", s3);
                    break;
                }
            }
        }
        if (n == 0) {
            this.fRedefineSucceeded = false;
            this.reportGenericSchemaError("could not find a declaration in the schema to be redefined corresponding to " + s2);
        }
    }
    
    private boolean validateRedefineNameChange(final String s, final String s2, final String s3, final Element element) throws Exception {
        if (s.equals("simpleType")) {
            final QName qName = new QName(-1, this.fStringPool.addSymbol(s2), this.fStringPool.addSymbol(s2), this.fTargetNSURI);
            Element element2 = XUtil.getFirstChildElement(element);
            if (element2 == null) {
                this.fRedefineSucceeded = false;
                this.reportGenericSchemaError("a simpleType child of a <redefine> must have a restriction element as a child");
            }
            else {
                String s4 = element2.getLocalName();
                if (s4.equals("annotation")) {
                    element2 = XUtil.getNextSiblingElement(element2);
                    s4 = element2.getLocalName();
                }
                if (element2 == null) {
                    this.fRedefineSucceeded = false;
                    this.reportGenericSchemaError("a simpleType child of a <redefine> must have a restriction element as a child");
                }
                else if (!s4.equals("restriction")) {
                    this.fRedefineSucceeded = false;
                    this.reportGenericSchemaError("a simpleType child of a <redefine> must have a restriction element as a child");
                }
                else {
                    final String attribute = element2.getAttribute("base");
                    if (qName.equals(this.parseBase(attribute))) {
                        String string = "";
                        final int index = attribute.indexOf(":");
                        if (index > 0) {
                            string = attribute.substring(0, index) + ":";
                        }
                        element2.setAttribute("base", string + s3);
                        return true;
                    }
                    this.fRedefineSucceeded = false;
                    this.reportGenericSchemaError("the base attribute of the restriction child of a simpleType child of a redefine must have the same value as the simpleType's type attribute");
                }
            }
        }
        else if (s.equals("complexType")) {
            final QName qName2 = new QName(-1, this.fStringPool.addSymbol(s2), this.fStringPool.addSymbol(s2), this.fTargetNSURI);
            Element element3 = XUtil.getFirstChildElement(element);
            if (element3 == null) {
                this.fRedefineSucceeded = false;
                this.reportGenericSchemaError("a complexType child of a <redefine> must have a restriction or extension element as a grandchild");
            }
            else {
                if (element3.getLocalName().equals("annotation")) {
                    element3 = XUtil.getNextSiblingElement(element3);
                }
                if (element3 == null) {
                    this.fRedefineSucceeded = false;
                    this.reportGenericSchemaError("a complexType child of a <redefine> must have a restriction or extension element as a grandchild");
                }
                else {
                    Element element4 = XUtil.getFirstChildElement(element3);
                    if (element4 == null) {
                        this.fRedefineSucceeded = false;
                        this.reportGenericSchemaError("a complexType child of a <redefine> must have a restriction or extension element as a grandchild");
                    }
                    else {
                        String s5 = element4.getLocalName();
                        if (s5.equals("annotation")) {
                            element4 = XUtil.getNextSiblingElement(element4);
                            s5 = element4.getLocalName();
                        }
                        if (element4 == null) {
                            this.fRedefineSucceeded = false;
                            this.reportGenericSchemaError("a complexType child of a <redefine> must have a restriction or extension element as a grandchild");
                        }
                        else if (!s5.equals("restriction") && !s5.equals("extension")) {
                            this.fRedefineSucceeded = false;
                            this.reportGenericSchemaError("a complexType child of a <redefine> must have a restriction or extension element as a grandchild");
                        }
                        else {
                            final String attribute2 = element4.getAttribute("base");
                            if (qName2.equals(this.parseBase(attribute2))) {
                                String string2 = "";
                                final int index2 = attribute2.indexOf(":");
                                if (index2 > 0) {
                                    string2 = attribute2.substring(0, index2) + ":";
                                }
                                element4.setAttribute("base", string2 + s3);
                                return true;
                            }
                            this.fRedefineSucceeded = false;
                            this.reportGenericSchemaError("the base attribute of the restriction or extension grandchild of a complexType child of a redefine must have the same value as the complexType's type attribute");
                        }
                    }
                }
            }
        }
        else if (s.equals("attributeGroup")) {
            final int changeRedefineGroup = this.changeRedefineGroup(new QName(-1, this.fStringPool.addSymbol(s2), this.fStringPool.addSymbol(s2), this.fTargetNSURI), s, s3, element);
            if (changeRedefineGroup > 1) {
                this.fRedefineSucceeded = false;
                this.reportGenericSchemaError("if an attributeGroup child of a <redefine> element contains an attributeGroup ref'ing itself, it must have exactly 1; this one has " + changeRedefineGroup);
            }
            else {
                if (changeRedefineGroup == 1) {
                    return true;
                }
                this.reportGenericSchemaError("an attributeGroup in a <redefine> must have exactly one ref attribute to itself in schema CR");
            }
        }
        else if (s.equals("group")) {
            final int changeRedefineGroup2 = this.changeRedefineGroup(new QName(-1, this.fStringPool.addSymbol(s2), this.fStringPool.addSymbol(s2), this.fTargetNSURI), s, s3, element);
            if (changeRedefineGroup2 > 1) {
                this.fRedefineSucceeded = false;
                this.reportGenericSchemaError("if a group child of a <redefine> element contains a group ref'ing itself, it must have exactly 1; this one has " + changeRedefineGroup2);
            }
            else {
                if (changeRedefineGroup2 == 1) {
                    return true;
                }
                this.reportGenericSchemaError("a group in a <redefine> must have exactly one ref attribute to itself in schema CR");
            }
        }
        else {
            this.fRedefineSucceeded = false;
            this.reportGenericSchemaError("internal Xerces error; please submit a bug with schema as testcase");
        }
        return false;
    }
    
    private void traverseImport(final Element element) throws Exception {
        String s = element.getAttribute("schemaLocation");
        InputSource resolveEntity = null;
        if (this.fEntityResolver != null) {
            resolveEntity = this.fEntityResolver.resolveEntity("", s);
        }
        String s2;
        if (resolveEntity == null) {
            s2 = this.expandSystemId(s, this.fCurrentSchemaURL);
            resolveEntity = new InputSource(s2);
        }
        else {
            if (resolveEntity.getPublicId() != null) {
                s = resolveEntity.getPublicId();
            }
            s2 = s + ',' + resolveEntity.getSystemId();
        }
        if (this.fImportLocations.contains(s2)) {
            return;
        }
        this.fImportLocations.addElement(s2);
        final String attribute = element.getAttribute("namespace");
        SchemaGrammar schemaGrammar = (SchemaGrammar)this.fGrammarResolver.getGrammar(attribute);
        if (schemaGrammar == null) {
            schemaGrammar = new SchemaGrammar();
        }
        final IgnoreWhitespaceParser ignoreWhitespaceParser = new IgnoreWhitespaceParser();
        ignoreWhitespaceParser.setEntityResolver(new Resolver());
        ignoreWhitespaceParser.setErrorHandler(new ErrorHandler());
        try {
            ignoreWhitespaceParser.setFeature("http://xml.org/sax/features/validation", false);
            ignoreWhitespaceParser.setFeature("http://xml.org/sax/features/namespaces", true);
            ignoreWhitespaceParser.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false);
        }
        catch (SAXNotRecognizedException ex) {
            ex.printStackTrace();
        }
        catch (SAXNotSupportedException ex2) {
            ex2.printStackTrace();
        }
        try {
            ignoreWhitespaceParser.parse(resolveEntity);
        }
        catch (IOException ex3) {
            ex3.printStackTrace();
        }
        catch (SAXException ex4) {
            ex4.printStackTrace();
        }
        final Document document = ignoreWhitespaceParser.getDocument();
        Element documentElement = null;
        if (document != null) {
            documentElement = document.getDocumentElement();
        }
        if (documentElement != null) {
            final String attribute2 = documentElement.getAttribute("targetNamespace");
            if (!attribute2.equals(attribute)) {
                this.reportGenericSchemaError("imported schema '" + s2 + "' has a different targetNameSpace '" + attribute2 + "' from what is declared '" + attribute + "'.");
            }
            else {
                new TraverseSchema(documentElement, this.fStringPool, schemaGrammar, this.fGrammarResolver, this.fErrorReporter, s2, this.fEntityResolver);
            }
        }
        else {
            this.reportGenericSchemaError("Could not get the doc root for imported Schema file: " + s2);
        }
    }
    
    private void traverseAnnotationDecl(final Element element) throws Exception {
        for (Element element2 = XUtil.getFirstChildElement(element); element2 != null; element2 = XUtil.getNextSiblingElement(element2)) {
            final String localName = element2.getLocalName();
            if (!localName.equals("appinfo") && !localName.equals("documentation")) {
                this.reportGenericSchemaError("an <annotation> can only contain <appinfo> and <documentation> elements");
            }
        }
    }
    
    private Element checkContent(final Element element, Element nextSiblingElement, final boolean b) throws Exception {
        if (nextSiblingElement == null) {
            if (!b) {
                this.reportSchemaError(25, new Object[] { element.getAttribute("name") });
            }
            return null;
        }
        if (nextSiblingElement.getLocalName().equals("annotation")) {
            this.traverseAnnotationDecl(nextSiblingElement);
            nextSiblingElement = XUtil.getNextSiblingElement(nextSiblingElement);
            if (nextSiblingElement == null) {
                if (!b) {
                    this.reportSchemaError(25, new Object[] { element.getAttribute("name") });
                }
                return null;
            }
            if (nextSiblingElement.getLocalName().equals("annotation")) {
                this.reportSchemaError(26, new Object[] { element.getAttribute("name") });
                return null;
            }
        }
        return nextSiblingElement;
    }
    
    private DatatypeValidator findDTValidator(final Element element, final String s) throws Exception {
        this.fStringPool.addSymbol(s);
        String substring = "";
        String substring2 = s;
        final int index = s.indexOf(":");
        if (index > 0) {
            substring = s.substring(0, index);
            substring2 = s.substring(index + 1);
        }
        final String resolvePrefixToURI = this.resolvePrefixToURI(substring);
        DatatypeValidator datatypeValidator = this.getDatatypeValidator(resolvePrefixToURI, substring2);
        if (datatypeValidator == null) {
            final Element topLevelComponentByName = this.getTopLevelComponentByName("simpleType", substring2);
            if (topLevelComponentByName != null) {
                this.traverseSimpleTypeDecl(topLevelComponentByName);
                datatypeValidator = this.getDatatypeValidator(resolvePrefixToURI, substring2);
            }
        }
        if (datatypeValidator == null) {
            this.reportSchemaError(15, new Object[] { element.getAttribute("base"), element.getAttribute("name") });
        }
        return datatypeValidator;
    }
    
    private int traverseSimpleTypeDecl(final Element element) throws Exception {
        String s2;
        final String s = s2 = element.getAttribute("name");
        if (this.fTargetNSURIString.length() != 0) {
            s2 = this.fTargetNSURIString + "," + s;
        }
        if (this.fDatatypeRegistry.getDatatypeValidator(s2) != null) {
            return this.fStringPool.addSymbol(s2);
        }
        boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        int n;
        if (s.equals("")) {
            n = this.fStringPool.addSymbol("#S#" + this.fSimpleTypeAnonCount++);
        }
        else {
            n = this.fStringPool.addSymbol(s);
        }
        Element element2 = this.checkContent(element, XUtil.getFirstChildElement(element), false);
        if (element2 == null) {
            return -1;
        }
        final String localName = element2.getLocalName();
        Vector<DatatypeValidator> vector = null;
        int countTokens = 0;
        StringTokenizer stringTokenizer = null;
        String s3;
        if (localName.equals("list")) {
            s3 = element2.getAttribute("itemType");
            b = true;
        }
        else if (localName.equals("restriction")) {
            s3 = element2.getAttribute("base");
            b3 = true;
        }
        else {
            if (!localName.equals("union")) {
                this.reportSchemaError(7, new Object[] { localName });
                return -1;
            }
            b2 = true;
            s3 = element2.getAttribute("memberTypes");
            if (!s3.equals("")) {
                stringTokenizer = new StringTokenizer(s3);
                countTokens = stringTokenizer.countTokens();
            }
            else {
                countTokens = 1;
            }
            vector = new Vector<DatatypeValidator>(countTokens, 2);
        }
        if (XUtil.getNextSiblingElement(element2) != null) {
            this.reportGenericSchemaError("error in content of simpleType");
        }
        DatatypeValidator datatypeValidator = null;
        if (s3.equals("")) {
            element2 = this.checkContent(element, XUtil.getFirstChildElement(element2), false);
            if (element2 == null) {
                return -1;
            }
            if (!element2.getLocalName().equals("simpleType")) {
                this.reportSchemaError(27, new Object[] { element.getAttribute("name") });
                return -1;
            }
            final int traverseSimpleTypeDecl = this.traverseSimpleTypeDecl(element2);
            if (traverseSimpleTypeDecl != -1) {
                datatypeValidator = this.fDatatypeRegistry.getDatatypeValidator(this.fStringPool.toString(traverseSimpleTypeDecl));
                if (datatypeValidator != null && b2) {
                    vector.addElement(datatypeValidator);
                }
            }
            if (traverseSimpleTypeDecl == -1 || datatypeValidator == null) {
                this.reportSchemaError(15, new Object[] { element2.getAttribute("base"), element2.getAttribute("name") });
                return -1;
            }
        }
        else {
            int n2 = 1;
            if (b2) {
                n2 = countTokens;
            }
            for (int i = 0; i < n2; ++i) {
                if (b2) {
                    s3 = stringTokenizer.nextToken();
                }
                datatypeValidator = this.findDTValidator(element, s3);
                if (datatypeValidator == null) {
                    return -1;
                }
                if (b2) {
                    vector.addElement(datatypeValidator);
                }
                if (b && datatypeValidator instanceof UnionDatatypeValidator) {
                    this.reportSchemaError(15, new Object[] { element.getAttribute("base"), element.getAttribute("name") });
                    return -1;
                }
            }
        }
        Element element3;
        if (s3.equals("")) {
            element3 = XUtil.getNextSiblingElement(element2);
        }
        else {
            element3 = XUtil.getFirstChildElement(element2);
        }
        if (b2) {
            if (!s3.equals("")) {
                element3 = this.checkContent(element, element3, true);
            }
            while (element3 != null) {
                final int traverseSimpleTypeDecl2 = this.traverseSimpleTypeDecl(element3);
                if (traverseSimpleTypeDecl2 != -1) {
                    datatypeValidator = this.fDatatypeRegistry.getDatatypeValidator(this.fStringPool.toString(traverseSimpleTypeDecl2));
                    if (datatypeValidator != null) {
                        vector.addElement(datatypeValidator);
                    }
                }
                if (datatypeValidator == null || traverseSimpleTypeDecl2 == -1) {
                    this.reportSchemaError(15, new Object[] { element.getAttribute("base"), element.getAttribute("name") });
                    return -1;
                }
                element3 = XUtil.getNextSiblingElement(element3);
            }
        }
        int n3 = 0;
        final Hashtable<String, Vector<String>> hashtable = new Hashtable<String, Vector<String>>();
        if (b3 && element3 != null) {
            int n4 = 0;
            final Vector<String> vector2 = new Vector<String>();
            Element element4 = this.checkContent(element, element3, true);
            StringBuffer sb = null;
            while (element4 != null) {
                if (element4.getNodeType() == 1) {
                    ++n3;
                    final String localName2 = element4.getLocalName();
                    if (localName2.equals("enumeration")) {
                        ++n4;
                        String attribute = element4.getAttribute("value");
                        if (datatypeValidator instanceof NOTATIONDatatypeValidator) {
                            String substring = "";
                            String substring2 = attribute;
                            final int index = attribute.indexOf(":");
                            if (index > 0) {
                                substring = attribute.substring(0, index);
                                substring2 = attribute.substring(index + 1);
                            }
                            final String s4 = substring.equals("") ? this.fTargetNSURIString : this.resolvePrefixToURI(substring);
                            final String string = s4 + ":" + substring2;
                            if (this.fNotationRegistry.get(string) == null && this.traverseNotationFromAnotherSchema(substring2, s4) == null) {
                                this.reportGenericSchemaError("Notation '" + substring2 + "' not found in the grammar " + s4);
                            }
                            attribute = string;
                        }
                        vector2.addElement(attribute);
                        this.checkContent(element, XUtil.getFirstChildElement(element4), true);
                    }
                    else if (localName2.equals("annotation")) {
                        this.reportSchemaError(25, new Object[] { element.getAttribute("name") });
                    }
                    else if (localName2.equals("pattern")) {
                        if (sb == null) {
                            sb = new StringBuffer(element4.getAttribute("value"));
                        }
                        else {
                            sb.append("|");
                            sb.append(element4.getAttribute("value"));
                            this.checkContent(element, XUtil.getFirstChildElement(element4), true);
                        }
                    }
                    else {
                        hashtable.put(localName2, (Vector<String>)element4.getAttribute("value"));
                        this.checkContent(element, XUtil.getFirstChildElement(element4), true);
                    }
                }
                element4 = XUtil.getNextSiblingElement(element4);
            }
            if (n4 > 0) {
                hashtable.put("enumeration", vector2);
            }
            if (sb != null) {
                hashtable.put("pattern", (Vector<String>)sb.toString());
            }
        }
        else if (b && element3 != null) {
            if (!s3.equals("")) {
                this.checkContent(element, element3, true);
            }
            else {
                this.reportSchemaError(27, new Object[] { element.getAttribute("name") });
            }
        }
        else if (b2 && element3 != null) {
            if (!s3.equals("")) {
                this.checkContent(element, element3, true);
            }
            else {
                this.reportSchemaError(27, new Object[] { element.getAttribute("name") });
            }
        }
        String s5 = this.fStringPool.toString(n);
        if (this.fTargetNSURIString.length() != 0) {
            s5 = this.fTargetNSURIString + "," + s5;
        }
        try {
            if (this.fDatatypeRegistry.getDatatypeValidator(s5) == null) {
                if (b) {
                    this.fDatatypeRegistry.createDatatypeValidator(s5, datatypeValidator, hashtable, true);
                }
                else if (b3) {
                    this.fDatatypeRegistry.createDatatypeValidator(s5, datatypeValidator, hashtable, false);
                }
                else {
                    this.fDatatypeRegistry.createDatatypeValidator(s5, vector);
                }
            }
        }
        catch (Exception ex) {
            this.reportSchemaError(21, new Object[] { ex.getMessage() });
        }
        return this.fStringPool.addSymbol(s5);
    }
    
    private int traverseAny(final Element element) throws Exception {
        if (this.checkContent(element, XUtil.getFirstChildElement(element), true) != null) {
            this.reportGenericSchemaError("<any> elements can contain at most one <annotation> element in their children");
        }
        int n = -1;
        final String trim = element.getAttribute("namespace").trim();
        final String trim2 = element.getAttribute("processContents").trim();
        int n2 = 6;
        int n3 = 7;
        int n4 = 8;
        if (trim2.length() > 0 && !trim2.equals("strict")) {
            if (trim2.equals("lax")) {
                n2 = 22;
                n3 = 23;
                n4 = 24;
            }
            else if (trim2.equals("skip")) {
                n2 = 38;
                n3 = 39;
                n4 = 40;
            }
        }
        if (trim.length() == 0 || trim.equals("##any")) {
            n = this.fSchemaGrammar.addContentSpecNode(n2, -1, this.fStringPool.addSymbol(this.fTargetNSURIString), false);
        }
        else if (trim.equals("##other")) {
            n = this.fSchemaGrammar.addContentSpecNode(n3, -1, this.fStringPool.addSymbol(this.fTargetNSURIString), false);
        }
        else if (trim.equals("##local")) {
            n = this.fSchemaGrammar.addContentSpecNode(n4, -1, 0, false);
        }
        else if (trim.length() > 0) {
            final StringTokenizer stringTokenizer = new StringTokenizer(trim);
            final Vector vector = new Vector<String>();
            while (stringTokenizer.hasMoreElements()) {
                String s = stringTokenizer.nextToken();
                if (s.equals("##targetNamespace")) {
                    s = this.fTargetNSURIString;
                }
                vector.addElement(s);
            }
            final int addContentSpecNode = this.fSchemaGrammar.addContentSpecNode(n2, -1, this.fStringPool.addSymbol(vector.elementAt(0)), false);
            final int size = vector.size();
            if (size > 1) {
                int n5 = this.fSchemaGrammar.addContentSpecNode(4, addContentSpecNode, this.fSchemaGrammar.addContentSpecNode(n2, -1, this.fStringPool.addSymbol(vector.elementAt(1)), false), false);
                for (int i = 2; i < size; ++i) {
                    n5 = this.fSchemaGrammar.addContentSpecNode(4, n5, this.fSchemaGrammar.addContentSpecNode(n2, -1, this.fStringPool.addSymbol(vector.elementAt(i)), false), false);
                }
                n = n5;
            }
            else {
                n = addContentSpecNode;
            }
        }
        else {
            this.reportGenericSchemaError("Empty namespace attribute for any element");
        }
        return n;
    }
    
    public DatatypeValidator getDatatypeValidator(final String s, final String s2) {
        DatatypeValidator datatypeValidator;
        if (s.length() == 0 || s.equals("http://www.w3.org/2000/10/XMLSchema")) {
            datatypeValidator = this.fDatatypeRegistry.getDatatypeValidator(s2);
        }
        else {
            datatypeValidator = this.fDatatypeRegistry.getDatatypeValidator(s + "," + s2);
        }
        return datatypeValidator;
    }
    
    private XMLAttributeDecl traverseAnyAttribute(final Element element) throws Exception {
        if (this.checkContent(element, XUtil.getFirstChildElement(element), true) != null) {
            this.reportGenericSchemaError("<anyAttribute> elements can contain at most one <annotation> element in their children");
        }
        final XMLAttributeDecl xmlAttributeDecl = new XMLAttributeDecl();
        final String trim = element.getAttribute("processContents").trim();
        final String trim2 = element.getAttribute("namespace").trim();
        final String fTargetNSURIString = this.fTargetNSURIString;
        if (trim2.length() == 0 || trim2.equals("##any")) {
            xmlAttributeDecl.type = 8;
        }
        else if (trim2.equals("##other")) {
            xmlAttributeDecl.type = 9;
            xmlAttributeDecl.name.uri = this.fStringPool.addSymbol(fTargetNSURIString);
        }
        else if (trim2.equals("##local")) {
            xmlAttributeDecl.type = 10;
        }
        else if (trim2.length() > 0) {
            xmlAttributeDecl.type = 11;
            final StringTokenizer stringTokenizer = new StringTokenizer(trim2);
            final int startStringList = this.fStringPool.startStringList();
            final Vector vector = new Vector();
            while (stringTokenizer.hasMoreElements()) {
                String nextToken = stringTokenizer.nextToken();
                if (nextToken.equals("##targetNamespace")) {
                    nextToken = fTargetNSURIString;
                }
                if (!this.fStringPool.addStringToList(startStringList, this.fStringPool.addSymbol(nextToken))) {
                    this.reportGenericSchemaError("Internal StringPool error when reading the namespace attribute for anyattribute declaration");
                }
            }
            this.fStringPool.finishStringList(startStringList);
            xmlAttributeDecl.enumeration = startStringList;
        }
        else {
            this.reportGenericSchemaError("Empty namespace attribute for anyattribute declaration");
        }
        xmlAttributeDecl.defaultType = 4;
        if (trim.equals("skip")) {
            xmlAttributeDecl.defaultType = 6;
        }
        else if (trim.equals("lax")) {
            xmlAttributeDecl.defaultType = 5;
        }
        return xmlAttributeDecl;
    }
    
    private XMLAttributeDecl mergeTwoAnyAttribute(final XMLAttributeDecl xmlAttributeDecl, final XMLAttributeDecl xmlAttributeDecl2) {
        if (xmlAttributeDecl.type == -1) {
            return xmlAttributeDecl;
        }
        if (xmlAttributeDecl2.type == -1) {
            return xmlAttributeDecl2;
        }
        if (xmlAttributeDecl.type == 8) {
            return xmlAttributeDecl2;
        }
        if (xmlAttributeDecl2.type == 8) {
            return xmlAttributeDecl;
        }
        if (xmlAttributeDecl.type == 9) {
            if (xmlAttributeDecl2.type == 9) {
                if (xmlAttributeDecl2.name.uri == xmlAttributeDecl.name.uri) {
                    return xmlAttributeDecl;
                }
                xmlAttributeDecl.type = -1;
                return xmlAttributeDecl;
            }
            else {
                if (xmlAttributeDecl2.type == 10) {
                    return xmlAttributeDecl2;
                }
                if (xmlAttributeDecl2.type == 11) {
                    if (!this.fStringPool.stringInList(xmlAttributeDecl2.enumeration, xmlAttributeDecl.name.uri)) {
                        return xmlAttributeDecl2;
                    }
                    final int[] stringListAsIntArray = this.fStringPool.stringListAsIntArray(xmlAttributeDecl2.enumeration);
                    final int startStringList = this.fStringPool.startStringList();
                    for (int i = 0; i < stringListAsIntArray.length; ++i) {
                        if (stringListAsIntArray[i] != xmlAttributeDecl.name.uri) {
                            this.fStringPool.addStringToList(startStringList, stringListAsIntArray[i]);
                        }
                    }
                    this.fStringPool.finishStringList(startStringList);
                    xmlAttributeDecl2.enumeration = startStringList;
                    return xmlAttributeDecl2;
                }
            }
        }
        if (xmlAttributeDecl.type == 10) {
            if (xmlAttributeDecl2.type == 9 || xmlAttributeDecl2.type == 10) {
                return xmlAttributeDecl;
            }
            if (xmlAttributeDecl2.type == 11) {
                xmlAttributeDecl.type = -1;
                return xmlAttributeDecl;
            }
        }
        if (xmlAttributeDecl.type == 11) {
            if (xmlAttributeDecl2.type == 9) {
                if (!this.fStringPool.stringInList(xmlAttributeDecl.enumeration, xmlAttributeDecl2.name.uri)) {
                    return xmlAttributeDecl;
                }
                final int[] stringListAsIntArray2 = this.fStringPool.stringListAsIntArray(xmlAttributeDecl.enumeration);
                final int startStringList2 = this.fStringPool.startStringList();
                for (int j = 0; j < stringListAsIntArray2.length; ++j) {
                    if (stringListAsIntArray2[j] != xmlAttributeDecl2.name.uri) {
                        this.fStringPool.addStringToList(startStringList2, stringListAsIntArray2[j]);
                    }
                }
                this.fStringPool.finishStringList(startStringList2);
                xmlAttributeDecl.enumeration = startStringList2;
                return xmlAttributeDecl;
            }
            else {
                if (xmlAttributeDecl2.type == 10) {
                    xmlAttributeDecl.type = -1;
                    return xmlAttributeDecl;
                }
                if (xmlAttributeDecl2.type == 11) {
                    final int[] intersect2sets = this.intersect2sets(this.fStringPool.stringListAsIntArray(xmlAttributeDecl.enumeration), this.fStringPool.stringListAsIntArray(xmlAttributeDecl2.enumeration));
                    final int startStringList3 = this.fStringPool.startStringList();
                    for (int k = 0; k < intersect2sets.length; ++k) {
                        this.fStringPool.addStringToList(startStringList3, intersect2sets[k]);
                    }
                    this.fStringPool.finishStringList(startStringList3);
                    xmlAttributeDecl.enumeration = startStringList3;
                    return xmlAttributeDecl;
                }
            }
        }
        return xmlAttributeDecl;
    }
    
    int[] intersect2sets(final int[] array, final int[] array2) {
        final int[] array3 = new int[(array.length > array2.length) ? array.length : array2.length];
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array2.length; ++j) {
                if (array[i] == array2[j]) {
                    array3[n++] = array[i];
                }
            }
        }
        final int[] array4 = new int[n];
        System.arraycopy(array3, 0, array4, 0, n);
        return array4;
    }
    
    private int traverseComplexTypeDecl(final Element element) throws Exception {
        final String attribute = element.getAttribute("abstract");
        final String attribute2 = element.getAttribute("block");
        final String attribute3 = element.getAttribute("final");
        element.getAttribute("ID");
        String s = element.getAttribute("name");
        final String attribute4 = element.getAttribute("mixed");
        if (s.equals("")) {
            s = this.genAnonTypeName(element);
        }
        this.fCurrentTypeNameStack.push(s);
        final int addSymbol = this.fStringPool.addSymbol(s);
        if (this.isTopLevel(element)) {
            final String string = this.fTargetNSURIString + "," + s;
            if (this.fComplexTypeRegistry.get(string) != null) {
                return this.fStringPool.addSymbol(string);
            }
        }
        final int n = this.fScopeCount++;
        final int fCurrentScope = this.fCurrentScope;
        this.fCurrentScope = n;
        final ComplexTypeInfo complexTypeInfo = new ComplexTypeInfo();
        try {
            final Element checkContent = this.checkContent(element, XUtil.getFirstChildElement(element), true);
            if (checkContent == null) {
                this.processComplexContent(addSymbol, checkContent, complexTypeInfo, null, false);
            }
            else {
                final String localName = checkContent.getLocalName();
                if (localName.equals("simpleContent")) {
                    this.traverseSimpleContentDecl(addSymbol, checkContent, complexTypeInfo);
                    if (XUtil.getNextSiblingElement(checkContent) != null) {
                        throw new ComplexTypeRecoverableError("Invalid child following the simpleContent child in the complexType");
                    }
                }
                else if (localName.equals("complexContent")) {
                    this.traverseComplexContentDecl(addSymbol, checkContent, complexTypeInfo, attribute4.equals("true"));
                    if (XUtil.getNextSiblingElement(checkContent) != null) {
                        throw new ComplexTypeRecoverableError("Invalid child following the complexContent child in the complexType");
                    }
                }
                else {
                    this.processComplexContent(addSymbol, checkContent, complexTypeInfo, null, attribute4.equals("true"));
                }
            }
        }
        catch (ComplexTypeRecoverableError complexTypeRecoverableError) {
            this.handleComplexTypeError(complexTypeRecoverableError.getMessage(), addSymbol, complexTypeInfo);
        }
        complexTypeInfo.scopeDefined = n;
        complexTypeInfo.blockSet = this.parseBlockSet(attribute2);
        complexTypeInfo.finalSet = this.parseFinalSet(attribute3);
        complexTypeInfo.isAbstract = attribute.equals("true");
        final String string2 = this.fTargetNSURIString + "," + s;
        complexTypeInfo.typeName = new String(string2);
        this.fComplexTypeRegistry.put(string2, complexTypeInfo);
        this.fCurrentScope = fCurrentScope;
        this.fCurrentTypeNameStack.pop();
        this.checkRecursingComplexType();
        this.fSchemaGrammar.setElementComplexTypeInfo(complexTypeInfo.templateElementIndex, complexTypeInfo);
        return this.fStringPool.addSymbol(string2);
    }
    
    private void traverseSimpleContentDecl(final int n, final Element element, final ComplexTypeInfo complexTypeInfo) throws Exception {
        final String string = this.fStringPool.toString(n);
        element.getAttribute("ID");
        complexTypeInfo.contentType = 4;
        complexTypeInfo.contentSpecHandle = -1;
        final Element checkContent = this.checkContent(element, XUtil.getFirstChildElement(element), false);
        if (checkContent == null) {
            throw new ComplexTypeRecoverableError();
        }
        final String localName = checkContent.getLocalName();
        if (localName.equals("restriction")) {
            complexTypeInfo.derivedBy = 2;
        }
        else {
            if (!localName.equals("extension")) {
                throw new ComplexTypeRecoverableError("The content of the simpleContent element is invalid.  The content must be RESTRICTION or EXTENSION");
            }
            complexTypeInfo.derivedBy = 1;
        }
        final String attribute = checkContent.getAttribute("base");
        checkContent.getAttribute("ID");
        Element element2 = this.checkContent(checkContent, XUtil.getFirstChildElement(checkContent), true);
        if (attribute.length() == 0) {
            throw new ComplexTypeRecoverableError("The BASE attribute must be specified for the RESTRICTION or EXTENSION element");
        }
        final QName base = this.parseBase(attribute);
        this.processBaseTypeInfo(base, complexTypeInfo);
        if (complexTypeInfo.baseComplexTypeInfo != null && complexTypeInfo.baseComplexTypeInfo.contentSpecHandle > -1) {
            throw new ComplexTypeRecoverableError("The type '" + attribute + "' specified as the " + "base in the simpleContent element must not have complexContent");
        }
        Element element3 = null;
        if (complexTypeInfo.derivedBy == 2) {
            if (complexTypeInfo.baseDataTypeValidator != null) {
                throw new ComplexTypeRecoverableError("The type '" + attribute + "' is a simple type.  It cannot be used in a " + "derivation by RESTRICTION for a complexType");
            }
            complexTypeInfo.baseDataTypeValidator = complexTypeInfo.baseComplexTypeInfo.datatypeValidator;
            if (element2.getLocalName().equals("simpleType")) {
                final int traverseSimpleTypeDecl = this.traverseSimpleTypeDecl(element2);
                if (traverseSimpleTypeDecl == -1) {
                    throw new ComplexTypeRecoverableError();
                }
                complexTypeInfo.baseDataTypeValidator = this.fDatatypeRegistry.getDatatypeValidator(this.fStringPool.toString(traverseSimpleTypeDecl));
                element2 = XUtil.getNextSiblingElement(element2);
            }
            int n2 = 0;
            int n3 = 0;
            final Hashtable<String, Vector<String>> hashtable = new Hashtable<String, Vector<String>>();
            final Vector<String> vector = new Vector<String>();
            Element nextSiblingElement;
            for (nextSiblingElement = element2; nextSiblingElement != null && (nextSiblingElement.getLocalName().equals("minExclusive") || nextSiblingElement.getLocalName().equals("minInclusive") || nextSiblingElement.getLocalName().equals("maxExclusive") || nextSiblingElement.getLocalName().equals("maxInclusive") || nextSiblingElement.getLocalName().equals("precision") || nextSiblingElement.getLocalName().equals("scale") || nextSiblingElement.getLocalName().equals("length") || nextSiblingElement.getLocalName().equals("minLength") || nextSiblingElement.getLocalName().equals("maxLength") || nextSiblingElement.getLocalName().equals("encoding") || nextSiblingElement.getLocalName().equals("period") || nextSiblingElement.getLocalName().equals("duration") || nextSiblingElement.getLocalName().equals("enumeration") || nextSiblingElement.getLocalName().equals("pattern") || nextSiblingElement.getLocalName().equals("annotation")); nextSiblingElement = XUtil.getNextSiblingElement(nextSiblingElement)) {
                if (nextSiblingElement.getNodeType() == 1) {
                    final Element element4 = nextSiblingElement;
                    ++n3;
                    if (element4.getLocalName().equals("enumeration")) {
                        ++n2;
                        vector.addElement(element4.getAttribute("value"));
                        final Element firstChildElement = XUtil.getFirstChildElement(element4);
                        if (firstChildElement != null && firstChildElement.getLocalName().equals("annotation")) {
                            this.traverseAnnotationDecl(nextSiblingElement);
                        }
                    }
                    else {
                        hashtable.put(element4.getLocalName(), (Vector<String>)element4.getAttribute("value"));
                    }
                }
            }
            if (n2 > 0) {
                hashtable.put("enumeration", vector);
            }
            if (n3 > 0) {
                complexTypeInfo.datatypeValidator = this.fDatatypeRegistry.createDatatypeValidator(string, complexTypeInfo.baseDataTypeValidator, hashtable, false);
            }
            else {
                complexTypeInfo.datatypeValidator = complexTypeInfo.baseDataTypeValidator;
            }
            if (nextSiblingElement != null) {
                if (!this.isAttrOrAttrGroup(nextSiblingElement)) {
                    throw new ComplexTypeRecoverableError("Invalid child in the RESTRICTION element of simpleContent");
                }
                element3 = nextSiblingElement;
            }
        }
        else {
            if (complexTypeInfo.baseComplexTypeInfo != null) {
                complexTypeInfo.baseDataTypeValidator = complexTypeInfo.baseComplexTypeInfo.datatypeValidator;
            }
            complexTypeInfo.datatypeValidator = complexTypeInfo.baseDataTypeValidator;
            if (element2 != null) {
                if (!this.isAttrOrAttrGroup(element2)) {
                    throw new ComplexTypeRecoverableError("Only annotations and attributes are allowed in the content of an EXTENSION element for a complexType with simpleContent");
                }
                element3 = element2;
            }
        }
        complexTypeInfo.templateElementIndex = this.fSchemaGrammar.addElementDecl(new QName(-1, this.fStringPool.addSymbol("$" + string), n, this.fTargetNSURI), (this.fTargetNSURI == 0) ? 0 : this.fCurrentScope, complexTypeInfo.scopeDefined, complexTypeInfo.contentType, complexTypeInfo.contentSpecHandle, -1, complexTypeInfo.datatypeValidator);
        complexTypeInfo.attlistHead = this.fSchemaGrammar.getFirstAttributeDeclIndex(complexTypeInfo.templateElementIndex);
        this.processAttributes(element3, base, complexTypeInfo);
        if (XUtil.getNextSiblingElement(checkContent) != null) {
            throw new ComplexTypeRecoverableError("Invalid child following the RESTRICTION or EXTENSION element in the complex type definition");
        }
    }
    
    private void traverseComplexContentDecl(final int n, final Element element, final ComplexTypeInfo complexTypeInfo, final boolean b) throws Exception {
        this.fStringPool.toString(n);
        element.getAttribute("ID");
        final String attribute = element.getAttribute("mixed");
        boolean b2 = b;
        if (attribute.equals("true")) {
            b2 = true;
        }
        else if (attribute.equals("false")) {
            b2 = false;
        }
        complexTypeInfo.datatypeValidator = null;
        complexTypeInfo.baseDataTypeValidator = null;
        final Element checkContent = this.checkContent(element, XUtil.getFirstChildElement(element), false);
        if (checkContent == null) {
            throw new ComplexTypeRecoverableError();
        }
        final String localName = checkContent.getLocalName();
        if (localName.equals("restriction")) {
            complexTypeInfo.derivedBy = 2;
        }
        else {
            if (!localName.equals("extension")) {
                throw new ComplexTypeRecoverableError("The content of the complexContent element is invalid. The content must be RESTRICTION or EXTENSION");
            }
            complexTypeInfo.derivedBy = 1;
        }
        final String attribute2 = checkContent.getAttribute("base");
        checkContent.getAttribute("ID");
        final Element checkContent2 = this.checkContent(checkContent, XUtil.getFirstChildElement(checkContent), true);
        if (attribute2.length() == 0) {
            throw new ComplexTypeRecoverableError("The BASE attribute must be specified for the RESTRICTION or EXTENSION element");
        }
        final QName base = this.parseBase(attribute2);
        final String string = this.fStringPool.toString(base.uri);
        final String string2 = this.fStringPool.toString(base.localpart);
        if (!string.equals("http://www.w3.org/2000/10/XMLSchema") || !string2.equals("anyType")) {
            this.processBaseTypeInfo(base, complexTypeInfo);
            if (complexTypeInfo.baseComplexTypeInfo == null) {
                throw new ComplexTypeRecoverableError("The base type specified in the complexContent element must be a complexType");
            }
        }
        this.processComplexContent(n, checkContent2, complexTypeInfo, base, b2);
        if (XUtil.getNextSiblingElement(checkContent) != null) {
            throw new ComplexTypeRecoverableError("Invalid child following the RESTRICTION or EXTENSION element in the complex type definition");
        }
    }
    
    private void handleComplexTypeError(final String s, final int n, final ComplexTypeInfo complexTypeInfo) throws Exception {
        final String string = this.fStringPool.toString(n);
        if (s != null) {
            if (string.startsWith("#")) {
                this.reportGenericSchemaError("Anonymous complexType: " + s);
            }
            else {
                this.reportGenericSchemaError("ComplexType '" + string + "': " + s);
            }
        }
        complexTypeInfo.contentType = 1;
        complexTypeInfo.contentSpecHandle = -1;
        complexTypeInfo.derivedBy = 0;
        complexTypeInfo.datatypeValidator = null;
        complexTypeInfo.attlistHead = -1;
        complexTypeInfo.templateElementIndex = this.fSchemaGrammar.addElementDecl(new QName(-1, this.fStringPool.addSymbol("$" + string), n, this.fTargetNSURI), (this.fTargetNSURI == 0) ? 0 : this.fCurrentScope, complexTypeInfo.scopeDefined, complexTypeInfo.contentType, complexTypeInfo.contentSpecHandle, -1, complexTypeInfo.datatypeValidator);
    }
    
    private String genAnonTypeName(final Element element) throws Exception {
        String s;
        if (this.fCurrentTypeNameStack.empty()) {
            s = "#" + this.fAnonTypeCount++;
        }
        else {
            String s2 = ((Element)element.getParentNode()).getAttribute("name") + "_AnonType";
            for (int i = this.fCurrentTypeNameStack.size() - 1; i > -1; --i) {
                final String s3 = (String)this.fCurrentTypeNameStack.elementAt(i);
                s2 = s3 + "_" + s2;
                if (!s3.startsWith("#")) {
                    break;
                }
            }
            s = "#" + s2;
        }
        return s;
    }
    
    private QName parseBase(final String s) throws Exception {
        String substring = "";
        String substring2 = s;
        final int index = s.indexOf(":");
        if (index > 0) {
            substring = s.substring(0, index);
            substring2 = s.substring(index + 1);
        }
        return new QName(this.fStringPool.addSymbol(substring), this.fStringPool.addSymbol(substring2), this.fStringPool.addSymbol(s), this.fStringPool.addSymbol(this.resolvePrefixToURI(substring)));
    }
    
    private boolean baseFromAnotherSchema(final QName qName) throws Exception {
        final String string = this.fStringPool.toString(qName.uri);
        return !string.equals(this.fTargetNSURIString) && !string.equals("http://www.w3.org/2000/10/XMLSchema") && string.length() != 0;
    }
    
    private void processBaseTypeInfo(final QName qName, final ComplexTypeInfo complexTypeInfo) throws Exception {
        DatatypeValidator baseDataTypeValidator = null;
        final String string = this.fStringPool.toString(qName.uri);
        final String string2 = this.fStringPool.toString(qName.localpart);
        final String string3 = this.fStringPool.toString(qName.rawname);
        ComplexTypeInfo typeInfoFromNS;
        if (this.baseFromAnotherSchema(qName)) {
            typeInfoFromNS = this.getTypeInfoFromNS(string, string2);
            if (typeInfoFromNS == null) {
                baseDataTypeValidator = this.getTypeValidatorFromNS(string, string2);
                if (baseDataTypeValidator == null) {
                    throw new ComplexTypeRecoverableError("Could not find base type " + string2 + " in schema " + string);
                }
            }
        }
        else {
            typeInfoFromNS = this.fComplexTypeRegistry.get(string + "," + string2);
            if (typeInfoFromNS == null) {
                baseDataTypeValidator = this.getDatatypeValidator(string, string2);
                if (baseDataTypeValidator == null) {
                    final Element topLevelComponentByName = this.getTopLevelComponentByName("complexType", string2);
                    if (topLevelComponentByName != null) {
                        typeInfoFromNS = (ComplexTypeInfo)this.fComplexTypeRegistry.get(this.fStringPool.toString(this.traverseComplexTypeDecl(topLevelComponentByName)));
                    }
                    else {
                        final Element topLevelComponentByName2 = this.getTopLevelComponentByName("simpleType", string2);
                        if (topLevelComponentByName2 == null) {
                            throw new ComplexTypeRecoverableError("Base type could not be found : " + string3);
                        }
                        this.traverseSimpleTypeDecl(topLevelComponentByName2);
                        baseDataTypeValidator = this.getDatatypeValidator(string, string2);
                        if (baseDataTypeValidator == null) {}
                    }
                }
            }
        }
        complexTypeInfo.baseComplexTypeInfo = typeInfoFromNS;
        complexTypeInfo.baseDataTypeValidator = baseDataTypeValidator;
    }
    
    private void processComplexContent(final int n, final Element element, final ComplexTypeInfo complexTypeInfo, final QName qName, final boolean b) throws Exception {
        Element element2 = null;
        int contentSpecHandle = -2;
        if (element != null) {
            final String localName = element.getLocalName();
            if (localName.equals("group")) {
                contentSpecHandle = this.expandContentModel(this.traverseGroupDecl(element), element);
                element2 = XUtil.getNextSiblingElement(element);
            }
            else if (localName.equals("sequence")) {
                contentSpecHandle = this.expandContentModel(this.traverseSequence(element), element);
                element2 = XUtil.getNextSiblingElement(element);
            }
            else if (localName.equals("choice")) {
                contentSpecHandle = this.expandContentModel(this.traverseChoice(element), element);
                element2 = XUtil.getNextSiblingElement(element);
            }
            else if (localName.equals("all")) {
                contentSpecHandle = this.expandContentModel(this.traverseAll(element), element);
                element2 = XUtil.getNextSiblingElement(element);
            }
            else {
                if (!this.isAttrOrAttrGroup(element)) {
                    throw new ComplexTypeRecoverableError("Invalid child '" + localName + "' in the complex type");
                }
                complexTypeInfo.contentType = 1;
                element2 = element;
            }
        }
        if (b) {
            final int addContentSpecNode = this.fSchemaGrammar.addContentSpecNode(0, -1, -1, false);
            if (contentSpecHandle != -2) {
                contentSpecHandle = this.fSchemaGrammar.addContentSpecNode(4, addContentSpecNode, contentSpecHandle, false);
            }
            else {
                contentSpecHandle = addContentSpecNode;
            }
        }
        complexTypeInfo.contentSpecHandle = contentSpecHandle;
        if (complexTypeInfo.baseComplexTypeInfo != null) {
            int contentSpecHandle2 = complexTypeInfo.baseComplexTypeInfo.contentSpecHandle;
            if (complexTypeInfo.derivedBy != 2) {
                if (this.baseFromAnotherSchema(qName)) {
                    contentSpecHandle2 = this.importContentSpec((SchemaGrammar)this.fGrammarResolver.getGrammar(this.fStringPool.toString(qName.uri)), contentSpecHandle2);
                }
                if (complexTypeInfo.contentSpecHandle == -2) {
                    complexTypeInfo.contentSpecHandle = contentSpecHandle2;
                }
                else if (contentSpecHandle2 > -1) {
                    complexTypeInfo.contentSpecHandle = this.fSchemaGrammar.addContentSpecNode(5, contentSpecHandle2, complexTypeInfo.contentSpecHandle, false);
                }
            }
        }
        else {
            complexTypeInfo.derivedBy = 0;
        }
        if (b) {
            complexTypeInfo.contentType = 2;
        }
        else if (complexTypeInfo.contentSpecHandle == -2) {
            complexTypeInfo.contentType = 0;
        }
        else {
            complexTypeInfo.contentType = 3;
        }
        complexTypeInfo.templateElementIndex = this.fSchemaGrammar.addElementDecl(new QName(-1, this.fStringPool.addSymbol("$" + this.fStringPool.toString(n)), n, this.fTargetNSURI), (this.fTargetNSURI == 0) ? 0 : this.fCurrentScope, complexTypeInfo.scopeDefined, complexTypeInfo.contentType, complexTypeInfo.contentSpecHandle, -1, complexTypeInfo.datatypeValidator);
        complexTypeInfo.attlistHead = this.fSchemaGrammar.getFirstAttributeDeclIndex(complexTypeInfo.templateElementIndex);
        if (element2 != null) {
            if (!this.isAttrOrAttrGroup(element2)) {
                throw new ComplexTypeRecoverableError("Invalid child " + element2.getLocalName() + " in the complexType or complexContent");
            }
            this.processAttributes(element2, qName, complexTypeInfo);
        }
        else if (complexTypeInfo.baseComplexTypeInfo != null) {
            this.processAttributes(null, qName, complexTypeInfo);
        }
    }
    
    private void processAttributes(final Element element, final QName qName, final ComplexTypeInfo complexTypeInfo) throws Exception {
        XMLAttributeDecl xmlAttributeDecl = null;
        final Vector vector = new Vector<XMLAttributeDecl>();
        for (Element nextSiblingElement = element; nextSiblingElement != null; nextSiblingElement = XUtil.getNextSiblingElement(nextSiblingElement)) {
            final String localName = nextSiblingElement.getLocalName();
            if (localName.equals("attribute")) {
                this.traverseAttributeDecl(nextSiblingElement, complexTypeInfo, false);
            }
            else if (localName.equals("attributeGroup")) {
                this.traverseAttributeGroupDecl(nextSiblingElement, complexTypeInfo, vector);
            }
            else {
                if (!localName.equals("anyAttribute")) {
                    throw new ComplexTypeRecoverableError("Invalid child among the children of the complexType definition");
                }
                xmlAttributeDecl = this.traverseAnyAttribute(nextSiblingElement);
            }
        }
        if (xmlAttributeDecl != null) {
            XMLAttributeDecl mergeTwoAnyAttribute = null;
            final int size = vector.size();
            if (size > 0) {
                mergeTwoAnyAttribute = vector.elementAt(0);
                for (int i = 1; i < size; ++i) {
                    mergeTwoAnyAttribute = this.mergeTwoAnyAttribute(mergeTwoAnyAttribute, vector.elementAt(i));
                }
            }
            if (mergeTwoAnyAttribute != null) {
                final int defaultType = xmlAttributeDecl.defaultType;
                xmlAttributeDecl = this.mergeTwoAnyAttribute(xmlAttributeDecl, mergeTwoAnyAttribute);
                xmlAttributeDecl.defaultType = defaultType;
            }
        }
        else if (vector.size() > 0) {
            xmlAttributeDecl = vector.elementAt(0);
        }
        XMLAttributeDecl fTempAttributeDecl = null;
        final ComplexTypeInfo baseComplexTypeInfo = complexTypeInfo.baseComplexTypeInfo;
        if (baseComplexTypeInfo != null && baseComplexTypeInfo.attlistHead > -1) {
            int j = baseComplexTypeInfo.attlistHead;
            SchemaGrammar fSchemaGrammar = this.fSchemaGrammar;
            final String s = this.baseFromAnotherSchema(qName) ? this.fStringPool.toString(qName.uri) : null;
            if (s != null) {
                fSchemaGrammar = (SchemaGrammar)this.fGrammarResolver.getGrammar(s);
            }
            if (fSchemaGrammar != null) {
                while (j > -1) {
                    this.fTempAttributeDecl.clear();
                    fSchemaGrammar.getAttributeDecl(j, this.fTempAttributeDecl);
                    if (this.fTempAttributeDecl.type == 8 || this.fTempAttributeDecl.type == 11 || this.fTempAttributeDecl.type == 10 || this.fTempAttributeDecl.type == 9) {
                        if (xmlAttributeDecl == null) {
                            fTempAttributeDecl = this.fTempAttributeDecl;
                        }
                        j = fSchemaGrammar.getNextAttributeDeclIndex(j);
                    }
                    else if (this.fSchemaGrammar.getAttributeDeclIndex(complexTypeInfo.templateElementIndex, this.fTempAttributeDecl.name) > -1 && complexTypeInfo.derivedBy == 2) {
                        j = this.fSchemaGrammar.getNextAttributeDeclIndex(j);
                    }
                    else {
                        this.fSchemaGrammar.addAttDef(complexTypeInfo.templateElementIndex, this.fTempAttributeDecl.name, this.fTempAttributeDecl.type, this.fTempAttributeDecl.enumeration, this.fTempAttributeDecl.defaultType, this.fTempAttributeDecl.defaultValue, this.fTempAttributeDecl.datatypeValidator, this.fTempAttributeDecl.list);
                        j = fSchemaGrammar.getNextAttributeDeclIndex(j);
                    }
                }
            }
        }
        if (xmlAttributeDecl != null) {
            if (xmlAttributeDecl.type != -1) {
                this.fSchemaGrammar.addAttDef(complexTypeInfo.templateElementIndex, xmlAttributeDecl.name, xmlAttributeDecl.type, xmlAttributeDecl.enumeration, xmlAttributeDecl.defaultType, xmlAttributeDecl.defaultValue, xmlAttributeDecl.datatypeValidator, xmlAttributeDecl.list);
            }
        }
        else if (fTempAttributeDecl != null) {
            this.fSchemaGrammar.addAttDef(complexTypeInfo.templateElementIndex, fTempAttributeDecl.name, fTempAttributeDecl.type, fTempAttributeDecl.enumeration, fTempAttributeDecl.defaultType, fTempAttributeDecl.defaultValue, fTempAttributeDecl.datatypeValidator, fTempAttributeDecl.list);
        }
        complexTypeInfo.attlistHead = this.fSchemaGrammar.getFirstAttributeDeclIndex(complexTypeInfo.templateElementIndex);
    }
    
    private boolean isAttrOrAttrGroup(final Element element) {
        final String localName = element.getLocalName();
        return localName.equals("attribute") || localName.equals("attributeGroup") || localName.equals("anyAttribute");
    }
    
    private void checkRecursingComplexType() throws Exception {
        if (this.fCurrentTypeNameStack.empty() && !this.fElementRecurseComplex.isEmpty()) {
            final Enumeration<QName> keys = this.fElementRecurseComplex.keys();
            while (keys.hasMoreElements()) {
                final QName qName = keys.nextElement();
                final String s = this.fElementRecurseComplex.get(qName);
                final int uri = qName.uri;
                final int localpart = qName.localpart;
                final int prefix = qName.prefix;
                final ComplexTypeInfo complexTypeInfo = this.fComplexTypeRegistry.get(this.fTargetNSURIString + "," + s);
                if (complexTypeInfo == null) {
                    throw new Exception("Internal Error in void checkRecursingComplexType(). ");
                }
                this.fSchemaGrammar.setElementComplexTypeInfo(this.fSchemaGrammar.addElementDecl(new QName(-1, localpart, localpart, uri), prefix, complexTypeInfo.scopeDefined, complexTypeInfo.contentType, complexTypeInfo.contentSpecHandle, complexTypeInfo.attlistHead, complexTypeInfo.datatypeValidator), complexTypeInfo);
            }
            this.fElementRecurseComplex.clear();
        }
    }
    
    private void checkParticleDerivationOK(final Element element, final Element element2) {
    }
    
    private int importContentSpec(final SchemaGrammar schemaGrammar, final int n) throws Exception {
        final XMLContentSpec xmlContentSpec = new XMLContentSpec();
        schemaGrammar.getContentSpec(n, xmlContentSpec);
        if (xmlContentSpec.type == 0 || (xmlContentSpec.type & 0xF) == 0x6 || (xmlContentSpec.type & 0xF) == 0x8 || (xmlContentSpec.type & 0xF) == 0x7) {
            return this.fSchemaGrammar.addContentSpecNode(xmlContentSpec.type, xmlContentSpec.value, xmlContentSpec.otherValue, false);
        }
        if (xmlContentSpec.type == -1) {
            return -2;
        }
        int importContentSpec;
        if (xmlContentSpec.value == -1) {
            importContentSpec = -1;
        }
        else {
            importContentSpec = this.importContentSpec(schemaGrammar, xmlContentSpec.value);
        }
        int importContentSpec2;
        if (xmlContentSpec.otherValue == -1) {
            importContentSpec2 = -1;
        }
        else {
            importContentSpec2 = this.importContentSpec(schemaGrammar, xmlContentSpec.otherValue);
        }
        return this.fSchemaGrammar.addContentSpecNode(xmlContentSpec.type, importContentSpec, importContentSpec2, false);
    }
    
    private int expandContentModel(int n, final Element element) throws Exception {
        String trim = element.getAttribute("minOccurs").trim();
        String trim2 = element.getAttribute("maxOccurs").trim();
        int n2 = 1;
        int int1 = 1;
        if (trim.equals("0") && trim2.equals("0")) {
            return -2;
        }
        if (trim.equals("")) {
            trim = "1";
        }
        if (trim2.equals("")) {
            trim2 = "1";
        }
        final int n3 = n;
        if (!trim.equals("1") || !trim2.equals("1")) {
            if (trim.equals("0") && trim2.equals("1")) {
                n = this.fSchemaGrammar.addContentSpecNode(1, n, -1, false);
            }
            else if (trim.equals("0") && trim2.equals("unbounded")) {
                n = this.fSchemaGrammar.addContentSpecNode(2, n, -1, false);
            }
            else if (trim.equals("1") && trim2.equals("unbounded")) {
                n = this.fSchemaGrammar.addContentSpecNode(3, n, -1, false);
            }
            else if (trim2.equals("unbounded")) {
                try {
                    n2 = Integer.parseInt(trim);
                }
                catch (Exception ex) {
                    this.reportSchemaError(23, new Object[] { "illegal value for minOccurs : '" + ex.getMessage() + "' " });
                }
                if (n2 < 2) {}
                n = this.fSchemaGrammar.addContentSpecNode(3, n, -1, false);
                for (int i = 0; i < n2 - 1; ++i) {
                    n = this.fSchemaGrammar.addContentSpecNode(5, n3, n, false);
                }
            }
            else {
                try {
                    n2 = Integer.parseInt(trim);
                    int1 = Integer.parseInt(trim2);
                }
                catch (Exception ex2) {
                    this.reportSchemaError(23, new Object[] { "illegal value for minOccurs or maxOccurs : '" + ex2.getMessage() + "' " });
                }
                if (n2 == 0) {
                    final int n4 = n = this.fSchemaGrammar.addContentSpecNode(1, n3, -1, 0 != 0);
                    for (int j = 0; j < int1 - n2 - 1; ++j) {
                        n = this.fSchemaGrammar.addContentSpecNode(5, n, n4, false);
                    }
                }
                else {
                    for (int k = 0; k < n2 - 1; ++k) {
                        n = this.fSchemaGrammar.addContentSpecNode(5, n, n3, false);
                    }
                    final int addContentSpecNode = this.fSchemaGrammar.addContentSpecNode(1, n3, -1, false);
                    for (int l = 0; l < int1 - n2; ++l) {
                        n = this.fSchemaGrammar.addContentSpecNode(5, n, addContentSpecNode, false);
                    }
                }
            }
        }
        return n;
    }
    
    private int traverseAttributeDecl(final Element element, final ComplexTypeInfo complexTypeInfo, final boolean b) throws Exception {
        final String attribute = element.getAttribute("name");
        final int addSymbol = this.fStringPool.addSymbol(attribute);
        final String attribute2 = element.getAttribute("form");
        final boolean topLevel = this.isTopLevel(element);
        boolean list = false;
        final String attribute3 = element.getAttribute("ref");
        final String attribute4 = element.getAttribute("type");
        if (!attribute3.equals("")) {
            if (topLevel) {
                this.reportGenericSchemaError("An attribute with \"ref\" present must not have <schema> as its parent");
            }
            if (!attribute.equals("")) {
                this.reportGenericSchemaError("Attribute " + attribute + " cannot refer to another attribute, but it refers to " + attribute3);
            }
            if (!attribute4.equals("")) {
                this.reportGenericSchemaError("Attribute with reference " + attribute3 + " cannot also contain a type");
            }
            if (!element.getAttribute("form").equals("")) {
                this.reportGenericSchemaError("Attribute with reference " + attribute3 + " cannot also contain a \"form\" property");
            }
            if (!element.getAttribute("value").equals("")) {
                this.reportGenericSchemaError("Attribute with reference " + attribute3 + " cannot also contain a value");
            }
        }
        final Element attributeSimpleType = this.findAttributeSimpleType(element);
        final String attribute5 = element.getAttribute("use");
        final boolean equals = attribute5.equals("prohibited");
        final boolean equals2 = attribute5.equals("required");
        if (attribute3.equals("")) {
            if (attribute.equals("")) {
                this.reportGenericSchemaError("An attribute must have a ref or a name!");
            }
            int type;
            int n;
            DatatypeValidator datatypeValidator;
            if (attribute4.equals("")) {
                if (attributeSimpleType != null) {
                    type = 7;
                    n = this.traverseSimpleTypeDecl(attributeSimpleType);
                    this.fStringPool.toString(n);
                }
                else {
                    type = 7;
                    n = this.fStringPool.addSymbol("string");
                }
                datatypeValidator = this.fDatatypeRegistry.getDatatypeValidator(this.fStringPool.toString(n));
            }
            else {
                if (attributeSimpleType != null && !b) {
                    this.reportGenericSchemaError("Attribute declarations may not contain both a type and a simpleType declaration");
                }
                String substring = "";
                String substring2 = attribute4;
                n = this.fStringPool.addSymbol(substring2);
                final int index = attribute4.indexOf(":");
                if (index > 0) {
                    substring = attribute4.substring(0, index);
                    substring2 = attribute4.substring(index + 1);
                }
                final String resolvePrefixToURI = this.resolvePrefixToURI(substring);
                if (resolvePrefixToURI.equals("http://www.w3.org/2000/10/XMLSchema") || resolvePrefixToURI.length() == 0) {
                    datatypeValidator = this.getDatatypeValidator("", substring2);
                    if (substring2.equals("ID")) {
                        type = 3;
                    }
                    else if (substring2.equals("IDREF")) {
                        type = 4;
                    }
                    else if (substring2.equals("IDREFS")) {
                        type = 4;
                        list = true;
                    }
                    else if (substring2.equals("ENTITY")) {
                        type = 1;
                    }
                    else if (substring2.equals("ENTITIES")) {
                        type = 1;
                        list = true;
                    }
                    else if (substring2.equals("NMTOKEN")) {
                        type = 5;
                    }
                    else if (substring2.equals("NMTOKENS")) {
                        type = 5;
                        list = true;
                    }
                    else if (substring2.equals("notation")) {
                        type = 6;
                    }
                    else {
                        type = 7;
                        if (datatypeValidator == null && resolvePrefixToURI.length() == 0) {
                            final Element topLevelComponentByName = this.getTopLevelComponentByName("simpleType", substring2);
                            if (topLevelComponentByName != null) {
                                this.traverseSimpleTypeDecl(topLevelComponentByName);
                                datatypeValidator = this.getDatatypeValidator(resolvePrefixToURI, substring2);
                            }
                            else if (!b) {
                                this.reportGenericSchemaError("simpleType not found : (" + resolvePrefixToURI + ":" + substring2 + ")");
                            }
                        }
                    }
                }
                else {
                    datatypeValidator = this.getDatatypeValidator(resolvePrefixToURI, substring2);
                    if (datatypeValidator == null && resolvePrefixToURI.equals(this.fTargetNSURIString)) {
                        final Element topLevelComponentByName2 = this.getTopLevelComponentByName("simpleType", substring2);
                        if (topLevelComponentByName2 != null) {
                            this.traverseSimpleTypeDecl(topLevelComponentByName2);
                            datatypeValidator = this.getDatatypeValidator(resolvePrefixToURI, substring2);
                        }
                        else if (!b) {
                            this.reportGenericSchemaError("simpleType not found : (" + resolvePrefixToURI + ":" + substring2 + ")");
                        }
                    }
                    type = 7;
                }
            }
            int defaultType = -1;
            int n2 = -1;
            if (datatypeValidator == null && !b) {
                this.reportGenericSchemaError("could not resolve the type or get a null validator for datatype : " + this.fStringPool.toString(n));
            }
            final String attribute6 = element.getAttribute("value");
            if (topLevel) {
                if (!attribute6.equals("")) {
                    if ((equals2 || equals || attribute5.equals("optional")) && !b) {
                        this.reportGenericSchemaError("Globally-declared attributes containing values must have \"use\" set to \"FIXED\" or \"DEFAULT\", not " + attribute5);
                    }
                    else if (attribute5.equals("") && !b) {
                        this.reportGenericSchemaError("Globally-declared attributes containing values MUST have \"use\" present and set to \"FIXED\" or \"DEFAULT\"");
                    }
                    else if (attribute5.equals("fixed")) {
                        defaultType = 1;
                        n2 = this.fStringPool.addString(attribute6);
                    }
                    else {
                        defaultType = 3;
                        n2 = this.fStringPool.addString(attribute6);
                    }
                }
                else if (!attribute5.equals("") && !b) {
                    this.reportGenericSchemaError("Globally-declared attributes containing no value may not have \"use\" present");
                }
                else {
                    defaultType = 0;
                }
            }
            else if (!attribute6.equals("")) {
                if (equals2 || equals || attribute5.equals("optional")) {
                    this.reportGenericSchemaError("Locally-declared attributes containing values must have \"use\" set to \"FIXED\" or \"DEFAULT\", not " + attribute5);
                }
                else if (attribute5.equals("")) {
                    this.reportGenericSchemaError("Locally-declared attributes containing values MUST have \"use\" present and set to \"FIXED\" or \"DEFAULT\"");
                }
                else if (attribute5.equals("fixed")) {
                    defaultType = 1;
                    n2 = this.fStringPool.addString(attribute6);
                }
                else {
                    defaultType = 3;
                    n2 = this.fStringPool.addString(attribute6);
                }
            }
            else if (equals2) {
                defaultType = 2;
            }
            else if (equals) {
                defaultType = 7;
            }
            else {
                defaultType = 0;
            }
            if (type == 7 && n2 != -1) {
                try {
                    if (datatypeValidator != null) {
                        datatypeValidator.validate(this.fStringPool.toString(n2), null);
                    }
                    else if (!b) {
                        this.reportSchemaError(2, new Object[] { attribute4 });
                    }
                }
                catch (InvalidDatatypeValueException ex) {
                    if (!b) {
                        this.reportSchemaError(18, new Object[] { element.getAttribute("name"), ex.getMessage() });
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                    System.out.println("Internal error in attribute datatype validation");
                }
            }
            int fTargetNSURI = 0;
            if (this.fTargetNSURIString.length() > 0 && (topLevel || (!attribute2.equals("unqualified") && (attribute2.equals("qualified") || this.fAttributeDefaultQualified)))) {
                fTargetNSURI = this.fTargetNSURI;
            }
            final QName values = new QName(-1, addSymbol, addSymbol, fTargetNSURI);
            if (topLevel) {
                this.fTempAttributeDecl.datatypeValidator = datatypeValidator;
                this.fTempAttributeDecl.name.setValues(values);
                this.fTempAttributeDecl.type = type;
                this.fTempAttributeDecl.defaultType = defaultType;
                this.fTempAttributeDecl.list = list;
                if (n2 != -1) {
                    this.fTempAttributeDecl.defaultValue = new String(this.fStringPool.toString(n2));
                }
                this.fAttributeDeclRegistry.put(attribute, new XMLAttributeDecl(this.fTempAttributeDecl));
            }
            if (complexTypeInfo != null) {
                this.fSchemaGrammar.addAttDef(complexTypeInfo.templateElementIndex, values, type, n, defaultType, this.fStringPool.toString(n2), datatypeValidator, list);
            }
            return -1;
        }
        if (attributeSimpleType != null) {
            this.reportGenericSchemaError("an attribute with ref present cannot contain a simpleType");
        }
        String substring3 = "";
        String substring4 = attribute3;
        final int index2 = attribute3.indexOf(":");
        if (index2 > 0) {
            substring3 = attribute3.substring(0, index2);
            substring4 = attribute3.substring(index2 + 1);
        }
        final String resolvePrefixToURI2 = this.resolvePrefixToURI(substring3);
        if (!resolvePrefixToURI2.equals(this.fTargetNSURIString)) {
            this.addAttributeDeclFromAnotherSchema(substring4, resolvePrefixToURI2, complexTypeInfo);
            return -1;
        }
        final Element topLevelComponentByName3 = this.getTopLevelComponentByName("attribute", substring4);
        if (topLevelComponentByName3 != null) {
            if (complexTypeInfo != null) {
                this.traverseAttributeDecl(topLevelComponentByName3, complexTypeInfo, true);
                final int addSymbol2 = this.fStringPool.addSymbol(topLevelComponentByName3.getAttribute("name"));
                int fTargetNSURI2 = 0;
                if (this.fTargetNSURIString.length() > 0) {
                    fTargetNSURI2 = this.fTargetNSURI;
                }
                final QName qName = new QName(-1, addSymbol2, addSymbol2, fTargetNSURI2);
                if (equals) {
                    final int attributeDeclIndex = this.fSchemaGrammar.getAttributeDeclIndex(complexTypeInfo.templateElementIndex, qName);
                    final XMLAttributeDecl xmlAttributeDecl = new XMLAttributeDecl();
                    this.fSchemaGrammar.getAttributeDecl(attributeDeclIndex, xmlAttributeDecl);
                    xmlAttributeDecl.defaultType = 7;
                    this.fSchemaGrammar.setAttributeDecl(complexTypeInfo.templateElementIndex, attributeDeclIndex, xmlAttributeDecl);
                }
                else if (equals2) {
                    final int attributeDeclIndex2 = this.fSchemaGrammar.getAttributeDeclIndex(complexTypeInfo.templateElementIndex, qName);
                    final XMLAttributeDecl xmlAttributeDecl2 = new XMLAttributeDecl();
                    this.fSchemaGrammar.getAttributeDecl(attributeDeclIndex2, xmlAttributeDecl2);
                    if (xmlAttributeDecl2.defaultType == 1) {
                        xmlAttributeDecl2.defaultType = 8;
                    }
                    else {
                        xmlAttributeDecl2.defaultType = 2;
                    }
                    this.fSchemaGrammar.setAttributeDecl(complexTypeInfo.templateElementIndex, attributeDeclIndex2, xmlAttributeDecl2);
                }
            }
        }
        else if (this.fAttributeDeclRegistry.get(substring4) != null) {
            this.addAttributeDeclFromAnotherSchema(substring4, resolvePrefixToURI2, complexTypeInfo);
        }
        else {
            this.reportGenericSchemaError("Couldn't find top level attribute " + attribute3);
        }
        return -1;
    }
    
    private int addAttributeDeclFromAnotherSchema(final String s, final String s2, final ComplexTypeInfo complexTypeInfo) throws Exception {
        final SchemaGrammar schemaGrammar = (SchemaGrammar)this.fGrammarResolver.getGrammar(s2);
        if (s2 == null || !(schemaGrammar instanceof SchemaGrammar)) {
            this.reportGenericSchemaError("!!Schema not found in #addAttributeDeclFromAnotherSchema, schema uri : " + s2);
            return -1;
        }
        final Hashtable attirubteDeclRegistry = schemaGrammar.getAttirubteDeclRegistry();
        if (attirubteDeclRegistry == null) {
            this.reportGenericSchemaError("no attribute was defined in schema : " + s2);
            return -1;
        }
        final XMLAttributeDecl xmlAttributeDecl = attirubteDeclRegistry.get(s);
        if (xmlAttributeDecl == null) {
            this.reportGenericSchemaError("no attribute named \"" + s + "\" was defined in schema : " + s2);
            return -1;
        }
        if (complexTypeInfo != null) {
            this.fSchemaGrammar.addAttDef(complexTypeInfo.templateElementIndex, xmlAttributeDecl.name, xmlAttributeDecl.type, -1, xmlAttributeDecl.defaultType, xmlAttributeDecl.defaultValue, xmlAttributeDecl.datatypeValidator, xmlAttributeDecl.list);
        }
        return 0;
    }
    
    private int traverseAttributeGroupDecl(final Element element, final ComplexTypeInfo complexTypeInfo, final Vector vector) throws Exception {
        final String attribute = element.getAttribute("name");
        this.fStringPool.addSymbol(attribute);
        final String attribute2 = element.getAttribute("ref");
        Element element2 = this.checkContent(element, XUtil.getFirstChildElement(element), true);
        if (!attribute2.equals("")) {
            if (this.isTopLevel(element)) {
                this.reportGenericSchemaError("An attributeGroup with \"ref\" present must not have <schema> or <redefine> as its parent");
            }
            if (!attribute.equals("")) {
                this.reportGenericSchemaError("attributeGroup " + attribute + " cannot refer to another attributeGroup, but it refers to " + attribute2);
            }
            String substring = "";
            String substring2 = attribute2;
            final int index = attribute2.indexOf(":");
            if (index > 0) {
                substring = attribute2.substring(0, index);
                substring2 = attribute2.substring(index + 1);
            }
            final String resolvePrefixToURI = this.resolvePrefixToURI(substring);
            if (!resolvePrefixToURI.equals(this.fTargetNSURIString)) {
                this.traverseAttributeGroupDeclFromAnotherSchema(substring2, resolvePrefixToURI, complexTypeInfo, vector);
                return -1;
            }
            if (complexTypeInfo != null) {
                final Element topLevelComponentByName = this.getTopLevelComponentByName("attributeGroup", substring2);
                if (topLevelComponentByName != null) {
                    this.traverseAttributeGroupDecl(topLevelComponentByName, complexTypeInfo, vector);
                }
                else {
                    this.reportGenericSchemaError("Couldn't find top level attributeGroup " + attribute2);
                }
                return -1;
            }
        }
        else if (attribute.equals("")) {
            this.reportGenericSchemaError("an attributeGroup must have a name or a ref attribute present");
        }
        while (element2 != null) {
            if (element2.getLocalName().equals("attribute")) {
                this.traverseAttributeDecl(element2, complexTypeInfo, false);
            }
            else {
                if (!element2.getLocalName().equals("attributeGroup")) {
                    break;
                }
                if (complexTypeInfo != null) {
                    this.traverseAttributeGroupDecl(element2, complexTypeInfo, vector);
                }
            }
            element2 = XUtil.getNextSiblingElement(element2);
        }
        if (element2 != null) {
            if (element2.getLocalName().equals("anyAttribute")) {
                if (vector != null) {
                    vector.addElement(this.traverseAnyAttribute(element2));
                }
                if (XUtil.getNextSiblingElement(element2) != null) {
                    this.reportGenericSchemaError("An attributeGroup declaration cannot have any children after an anyAttribute declaration");
                }
                return -1;
            }
            this.reportGenericSchemaError("An attributeGroup declaration must only contain attribute, attributeGroup and anyAttribute elements");
        }
        return -1;
    }
    
    private int traverseAttributeGroupDeclFromAnotherSchema(final String s, final String s2, final ComplexTypeInfo complexTypeInfo, final Vector vector) throws Exception {
        final SchemaGrammar schemaGrammar = (SchemaGrammar)this.fGrammarResolver.getGrammar(s2);
        if (s2 == null || schemaGrammar == null || !(schemaGrammar instanceof SchemaGrammar)) {
            this.reportGenericSchemaError("!!Schema not found in #traverseAttributeGroupDeclFromAnotherSchema, schema uri : " + s2);
            return -1;
        }
        final Element element = schemaGrammar.topLevelAttrGrpDecls.get(s);
        if (element == null) {
            this.reportGenericSchemaError("no attribute group named \"" + s + "\" was defined in schema : " + s2);
            return -1;
        }
        final NamespacesScope fNamespacesScope = this.fNamespacesScope;
        final int fTargetNSURI = this.fTargetNSURI;
        this.fTargetNSURI = this.fStringPool.addSymbol(schemaGrammar.getTargetNamespaceURI());
        this.fNamespacesScope = schemaGrammar.getNamespacesScope();
        Element element2;
        for (element2 = this.checkContent(element, XUtil.getFirstChildElement(element), true); element2 != null; element2 = XUtil.getNextSiblingElement(element2)) {
            if (element2.getLocalName().equals("attribute")) {
                final String attribute = element2.getAttribute("name");
                if (attribute.length() > 0) {
                    final Hashtable attirubteDeclRegistry = schemaGrammar.getAttirubteDeclRegistry();
                    if (attirubteDeclRegistry != null && attirubteDeclRegistry.get(attribute) != null) {
                        this.addAttributeDeclFromAnotherSchema(attribute, s2, complexTypeInfo);
                        return -1;
                    }
                }
                else {
                    this.traverseAttributeDecl(element2, complexTypeInfo, false);
                }
            }
            else if (element2.getLocalName().equals("attributeGroup")) {
                this.traverseAttributeGroupDecl(element2, complexTypeInfo, vector);
            }
            else {
                if (element2.getLocalName().equals("anyAttribute")) {
                    vector.addElement(this.traverseAnyAttribute(element2));
                    break;
                }
                this.reportGenericSchemaError("Invalid content for attributeGroup");
            }
        }
        this.fNamespacesScope = fNamespacesScope;
        this.fTargetNSURI = fTargetNSURI;
        if (element2 != null) {
            this.reportGenericSchemaError("Invalid content for attributeGroup");
        }
        return -1;
    }
    
    private Element findAttributeSimpleType(final Element element) throws Exception {
        Element element2 = XUtil.getFirstChildElement(element);
        if (element2 == null) {
            return null;
        }
        if (element2.getLocalName().equals("simpleType")) {
            return element2;
        }
        if (element2.getLocalName().equals("annotation")) {
            this.traverseAnnotationDecl(element2);
            element2 = XUtil.getNextSiblingElement(element2);
        }
        if (element2 == null) {
            return null;
        }
        if (element2.getLocalName().equals("simpleType") && XUtil.getNextSiblingElement(element2) == null) {
            return element2;
        }
        this.reportGenericSchemaError("An attribute declaration must contain at most one annotation preceding at most one simpleType");
        return null;
    }
    
    private QName traverseElementDecl(final Element element) throws Exception {
        int contentType = -1;
        int contentSpecHandle = -1;
        int traverseSimpleTypeDecl = -1;
        int scopeDefined = -2;
        DatatypeValidator datatypeValidator = null;
        final String attribute = element.getAttribute("name");
        final String attribute2 = element.getAttribute("ref");
        final String attribute3 = element.getAttribute("type");
        element.getAttribute("minOccurs");
        element.getAttribute("maxOccurs");
        final String attribute4 = element.getAttribute("default");
        final String attribute5 = element.getAttribute("fixed");
        if (!attribute4.equals("") && !attribute5.equals("")) {
            this.reportGenericSchemaError("an element cannot have both \"fixed\" and \"default\" present at the same time");
        }
        final String attribute6 = element.getAttribute("substitutionGroup");
        final String attribute7 = element.getAttribute("form");
        String s = null;
        if (this.isTopLevel(element)) {
            if (attribute.equals("")) {
                this.reportGenericSchemaError("globally-declared element must have a name");
            }
            else if (!attribute2.equals("")) {
                this.reportGenericSchemaError("globally-declared element " + attribute + " cannot have a ref attribute");
            }
            final int addSymbol = this.fStringPool.addSymbol(attribute);
            if (this.fSchemaGrammar.getElementDeclIndex(this.fTargetNSURI, addSymbol, -1) > -1) {
                return new QName(-1, addSymbol, addSymbol, this.fTargetNSURI);
            }
        }
        final int blockSet = this.parseBlockSet(element.getAttribute("block"));
        final int finalSet = this.parseFinalSet(element.getAttribute("final"));
        final boolean equals = element.getAttribute("nullable").equals("true");
        final boolean equals2 = element.getAttribute("abstract").equals("true");
        int n = 0;
        if (equals) {
            ++n;
        }
        if (equals2) {
            n += 2;
        }
        if (attribute2.equals("")) {
            if (attribute.equals("")) {
                this.reportGenericSchemaError("a local element must have a name or a ref attribute present");
            }
            Element topLevelComponentByName = null;
            boolean b = true;
            String string = null;
            ComplexTypeInfo complexTypeInfo = null;
            DatatypeValidator datatypeValidator2 = null;
            if (attribute6.length() > 0) {
                if (!attribute2.equals("")) {
                    this.reportGenericSchemaError("a local element cannot have a substitutionGroup");
                }
                final String resolvePrefixToURI = this.resolvePrefixToURI(this.getPrefix(attribute6));
                final String localPart = this.getLocalPart(attribute6);
                string = resolvePrefixToURI + "," + localPart;
                if (!resolvePrefixToURI.equals(this.fTargetNSURIString)) {
                    complexTypeInfo = this.getElementDeclTypeInfoFromNS(resolvePrefixToURI, localPart);
                    if (complexTypeInfo == null) {
                        datatypeValidator2 = this.getElementDeclTypeValidatorFromNS(resolvePrefixToURI, localPart);
                        if (datatypeValidator2 == null) {
                            b = false;
                            this.reportGenericSchemaError("Could not find type for element '" + localPart + "' in schema '" + resolvePrefixToURI + "'");
                        }
                    }
                }
                else {
                    topLevelComponentByName = this.getTopLevelComponentByName("element", localPart);
                    int n2;
                    if (topLevelComponentByName == null) {
                        n2 = this.fSchemaGrammar.getElementDeclIndex(this.fTargetNSURI, this.getLocalPartIndex(attribute6), -1);
                        if (n2 == -1) {
                            b = false;
                            this.reportGenericSchemaError("substitutionGroup affiliation element " + attribute6 + " in element declaration " + attribute);
                        }
                    }
                    else {
                        n2 = this.fSchemaGrammar.getElementDeclIndex(this.fTargetNSURI, this.getLocalPartIndex(attribute6), -1);
                        if (n2 == -1) {
                            this.traverseElementDecl(topLevelComponentByName);
                            n2 = this.fSchemaGrammar.getElementDeclIndex(this.fTargetNSURI, this.getLocalPartIndex(attribute6), -1);
                        }
                    }
                    if (n2 != -1) {
                        complexTypeInfo = this.fSchemaGrammar.getElementComplexTypeInfo(n2);
                        if (complexTypeInfo == null) {
                            this.fSchemaGrammar.getElementDecl(n2, this.fTempElementDecl);
                            datatypeValidator2 = this.fTempElementDecl.datatypeValidator;
                            if (datatypeValidator2 == null) {
                                b = false;
                                this.reportGenericSchemaError("Could not find type for element '" + localPart + "' in schema '" + resolvePrefixToURI + "'");
                            }
                        }
                    }
                }
            }
            ComplexTypeInfo typeInfoFromNS = null;
            Element element2 = XUtil.getFirstChildElement(element);
            if (element2 != null && element2.getLocalName().equals("annotation")) {
                this.traverseAnnotationDecl(element2);
                element2 = XUtil.getNextSiblingElement(element2);
            }
            if (element2 != null && element2.getLocalName().equals("annotation")) {
                this.reportGenericSchemaError("element declarations can contain at most one annotation Element Information Item");
            }
            boolean b2 = false;
            if (element2 != null) {
                String s2 = element2.getLocalName();
                if (s2.equals("complexType")) {
                    if (element2.getAttribute("name").length() > 0) {
                        b = false;
                        this.reportGenericSchemaError("anonymous complexType in element '" + attribute + "' has a name attribute");
                    }
                    else {
                        final String genAnonTypeName = this.genAnonTypeName(element2);
                        if (this.fCurrentTypeNameStack.search(genAnonTypeName) > -1) {
                            int fTargetNSURI = 0;
                            if (attribute7.equals("qualified") || this.fElementDefaultQualified) {
                                fTargetNSURI = this.fTargetNSURI;
                            }
                            final int addSymbol2 = this.fStringPool.addSymbol(attribute);
                            this.fElementRecurseComplex.put(new QName(this.fCurrentScope, addSymbol2, addSymbol2, fTargetNSURI), genAnonTypeName);
                            return new QName(-1, addSymbol2, addSymbol2, fTargetNSURI);
                        }
                        final int traverseComplexTypeDecl = this.traverseComplexTypeDecl(element2);
                        if (traverseComplexTypeDecl != -1) {
                            typeInfoFromNS = (ComplexTypeInfo)this.fComplexTypeRegistry.get(this.fStringPool.toString(traverseComplexTypeDecl));
                        }
                        else {
                            b = false;
                            this.reportGenericSchemaError("traverse complexType error in element '" + attribute + "'");
                        }
                    }
                    b2 = true;
                    element2 = XUtil.getNextSiblingElement(element2);
                }
                else if (s2.equals("simpleType")) {
                    if (element2.getAttribute("name").length() > 0) {
                        b = false;
                        this.reportGenericSchemaError("anonymous simpleType in element '" + attribute + "' has a name attribute");
                    }
                    else {
                        traverseSimpleTypeDecl = this.traverseSimpleTypeDecl(element2);
                    }
                    if (traverseSimpleTypeDecl != -1) {
                        datatypeValidator = this.fDatatypeRegistry.getDatatypeValidator(this.fStringPool.toString(traverseSimpleTypeDecl));
                    }
                    else {
                        b = false;
                        this.reportGenericSchemaError("traverse simpleType error in element '" + attribute + "'");
                    }
                    contentType = 4;
                    b2 = true;
                    element2 = XUtil.getNextSiblingElement(element2);
                }
                else if (attribute3.equals("")) {
                    contentType = 1;
                    contentSpecHandle = -1;
                }
                if (element2 != null) {
                    s2 = element2.getLocalName();
                }
                while (element2 != null && (s2.equals("key") || s2.equals("keyref") || s2.equals("unique"))) {
                    element2 = XUtil.getNextSiblingElement(element2);
                    if (element2 != null) {
                        s2 = element2.getLocalName();
                    }
                }
                if (element2 != null) {
                    b = false;
                    this.reportGenericSchemaError("the content of an element information item must match (annotation?, (simpleType | complexType)?, (unique | key | keyref)*)");
                }
            }
            if (b2 && attribute3.length() > 0) {
                b = false;
                this.reportGenericSchemaError("Element '" + attribute + "' have both a type attribute and a annoymous type child");
            }
            else if (!attribute3.equals("")) {
                if (topLevelComponentByName != null) {
                    this.checkSubstitutionGroupOK(element, topLevelComponentByName);
                }
                String substring = "";
                String substring2 = attribute3;
                final int index = attribute3.indexOf(":");
                if (index > 0) {
                    substring = attribute3.substring(0, index);
                    substring2 = attribute3.substring(index + 1);
                }
                final String resolvePrefixToURI2 = this.resolvePrefixToURI(substring);
                if (!resolvePrefixToURI2.equals(this.fTargetNSURIString) && !resolvePrefixToURI2.equals("http://www.w3.org/2000/10/XMLSchema") && resolvePrefixToURI2.length() != 0) {
                    s = resolvePrefixToURI2;
                    typeInfoFromNS = this.getTypeInfoFromNS(resolvePrefixToURI2, substring2);
                    if (typeInfoFromNS == null) {
                        datatypeValidator = this.getTypeValidatorFromNS(resolvePrefixToURI2, substring2);
                        if (datatypeValidator == null) {
                            b = false;
                            this.reportGenericSchemaError("Could not find type " + substring2 + " in schema " + resolvePrefixToURI2);
                        }
                    }
                }
                else {
                    typeInfoFromNS = (ComplexTypeInfo)this.fComplexTypeRegistry.get(resolvePrefixToURI2 + "," + substring2);
                    if (typeInfoFromNS == null) {
                        datatypeValidator = this.getDatatypeValidator(resolvePrefixToURI2, substring2);
                        if (datatypeValidator == null) {
                            if (resolvePrefixToURI2.equals("http://www.w3.org/2000/10/XMLSchema") && !this.fTargetNSURIString.equals("http://www.w3.org/2000/10/XMLSchema")) {
                                b = false;
                                this.reportGenericSchemaError("type not found : " + resolvePrefixToURI2 + ":" + substring2);
                            }
                            else {
                                final Element topLevelComponentByName2 = this.getTopLevelComponentByName("complexType", substring2);
                                if (topLevelComponentByName2 != null) {
                                    if (this.fCurrentTypeNameStack.search(substring2) > -1) {
                                        int fTargetNSURI2 = 0;
                                        if (attribute7.equals("qualified") || this.fElementDefaultQualified) {
                                            fTargetNSURI2 = this.fTargetNSURI;
                                        }
                                        final int addSymbol3 = this.fStringPool.addSymbol(attribute);
                                        this.fElementRecurseComplex.put(new QName(this.fCurrentScope, addSymbol3, addSymbol3, fTargetNSURI2), substring2);
                                        return new QName(-1, addSymbol3, addSymbol3, fTargetNSURI2);
                                    }
                                    typeInfoFromNS = (ComplexTypeInfo)this.fComplexTypeRegistry.get(this.fStringPool.toString(this.traverseComplexTypeDecl(topLevelComponentByName2)));
                                }
                                else {
                                    final Element topLevelComponentByName3 = this.getTopLevelComponentByName("simpleType", substring2);
                                    if (topLevelComponentByName3 != null) {
                                        this.traverseSimpleTypeDecl(topLevelComponentByName3);
                                        datatypeValidator = this.getDatatypeValidator(resolvePrefixToURI2, substring2);
                                    }
                                    else {
                                        b = false;
                                        this.reportGenericSchemaError("type not found : " + resolvePrefixToURI2 + ":" + substring2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else if (b2) {
                if (topLevelComponentByName != null) {
                    this.checkSubstitutionGroupOK(element, topLevelComponentByName);
                }
            }
            else if (typeInfoFromNS == null && datatypeValidator == null) {
                typeInfoFromNS = complexTypeInfo;
                datatypeValidator = datatypeValidator2;
            }
            if (typeInfoFromNS == null && datatypeValidator == null) {
                if (b) {
                    contentType = 1;
                }
                else {
                    this.reportGenericSchemaError("untyped element : " + attribute);
                }
            }
            if (typeInfoFromNS != null) {
                contentSpecHandle = typeInfoFromNS.contentSpecHandle;
                contentType = typeInfoFromNS.contentType;
                scopeDefined = typeInfoFromNS.scopeDefined;
                datatypeValidator = typeInfoFromNS.datatypeValidator;
            }
            if (datatypeValidator != null) {
                contentType = 4;
                if (typeInfoFromNS == null) {
                    s = null;
                }
            }
            final int addSymbol4;
            final int n3 = addSymbol4 = this.fStringPool.addSymbol(attribute);
            int n4 = 0;
            int fCurrentScope = this.fCurrentScope;
            if (this.isTopLevel(element)) {
                n4 = this.fTargetNSURI;
                fCurrentScope = -1;
            }
            else if (!attribute7.equals("unqualified") && (attribute7.equals("qualified") || this.fElementDefaultQualified)) {
                n4 = this.fTargetNSURI;
            }
            final int elementDeclIndex = this.fSchemaGrammar.getElementDeclIndex(n4, addSymbol4, fCurrentScope);
            if (elementDeclIndex > -1) {
                this.fSchemaGrammar.getElementDecl(elementDeclIndex, this.fTempElementDecl);
                final DatatypeValidator datatypeValidator3 = this.fTempElementDecl.datatypeValidator;
                final ComplexTypeInfo elementComplexTypeInfo = this.fSchemaGrammar.getElementComplexTypeInfo(elementDeclIndex);
                if ((elementComplexTypeInfo != null && elementComplexTypeInfo != typeInfoFromNS) || (datatypeValidator3 != null && datatypeValidator3 != datatypeValidator)) {
                    this.reportGenericSchemaError("duplicate element decl in the same scope : " + this.fStringPool.toString(addSymbol4));
                }
            }
            final QName qName = new QName(-1, addSymbol4, n3, n4);
            int attlistHead = -1;
            if (typeInfoFromNS != null) {
                attlistHead = typeInfoFromNS.attlistHead;
            }
            final int addElementDecl = this.fSchemaGrammar.addElementDecl(qName, fCurrentScope, scopeDefined, contentType, contentSpecHandle, attlistHead, datatypeValidator);
            this.fSchemaGrammar.setElementComplexTypeInfo(addElementDecl, typeInfoFromNS);
            this.fSchemaGrammar.setElementFromAnotherSchemaURI(addElementDecl, s);
            this.fSchemaGrammar.setElementDeclBlockSet(addElementDecl, blockSet);
            this.fSchemaGrammar.setElementDeclFinalSet(addElementDecl, finalSet);
            this.fSchemaGrammar.setElementDeclMiscFlags(addElementDecl, n);
            this.fSchemaGrammar.setElementDeclSubstitutionGroupElementFullName(addElementDecl, string);
            Element element3 = XUtil.getFirstChildElement(element, TraverseSchema.IDENTITY_CONSTRAINTS);
            if (element3 != null) {
                final Integer n5 = new Integer(addElementDecl);
                Vector<?> vector = this.fIdentityConstraints.get(n5);
                if (vector == null) {
                    vector = new Vector<Object>();
                    this.fIdentityConstraints.put(n5, vector);
                }
                while (element3 != null) {
                    vector.addElement(element3);
                    element3 = XUtil.getNextSiblingElement(element3, TraverseSchema.IDENTITY_CONSTRAINTS);
                }
            }
            return qName;
        }
        if (!attribute3.equals("") || n > 0 || finalSet > 0 || blockSet > 0 || !attribute4.equals("") || !attribute5.equals("")) {
            this.reportSchemaError(16, null);
        }
        if (!attribute.equals("")) {
            this.reportGenericSchemaError("element " + attribute + " cannot also have a ref attribute");
        }
        final Element firstChildElement = XUtil.getFirstChildElement(element);
        if (firstChildElement != null && firstChildElement.getLocalName().equals("annotation")) {
            if (XUtil.getNextSiblingElement(firstChildElement) != null) {
                this.reportSchemaError(17, null);
            }
            else {
                this.traverseAnnotationDecl(firstChildElement);
            }
        }
        else if (firstChildElement != null) {
            this.reportSchemaError(17, null);
        }
        String substring3 = "";
        String substring4 = attribute2;
        final int index2 = attribute2.indexOf(":");
        if (index2 > 0) {
            substring3 = attribute2.substring(0, index2);
            substring4 = attribute2.substring(index2 + 1);
        }
        final int addSymbol5 = this.fStringPool.addSymbol(substring4);
        final String resolvePrefixToURI3 = this.resolvePrefixToURI(substring3);
        final QName qName2 = new QName((substring3 != null) ? this.fStringPool.addSymbol(substring3) : -1, addSymbol5, this.fStringPool.addSymbol(attribute2), (resolvePrefixToURI3 != null) ? this.fStringPool.addSymbol(resolvePrefixToURI3) : 0);
        if (!resolvePrefixToURI3.equals(this.fTargetNSURIString)) {
            return qName2;
        }
        if (this.fSchemaGrammar.getElementDeclIndex(qName2, -1) == -1 && this.getTopLevelComponentByName("element", substring4) == null) {
            this.reportGenericSchemaError("Element " + substring4 + " not found in the Schema");
            return qName2;
        }
        return qName2;
    }
    
    private void traverseIdentityConstraintsFor(final int n, final Vector vector) throws Exception {
        final int n2 = (vector != null) ? vector.size() : 0;
        if (n2 > 0) {
            final XMLElementDecl xmlElementDecl = new XMLElementDecl();
            this.fSchemaGrammar.getElementDecl(n, xmlElementDecl);
            for (int i = 0; i < n2; ++i) {
                final Element element = vector.elementAt(i);
                final String localName = element.getLocalName();
                if (localName.equals("key")) {
                    this.traverseKey(element, xmlElementDecl);
                }
                else if (localName.equals("keyref")) {
                    this.traverseKeyRef(element, xmlElementDecl);
                }
                else {
                    if (!localName.equals("unique")) {
                        throw new RuntimeException("identity constraint must be one of \"unique\", \"key\", or \"keyref\"");
                    }
                    this.traverseUnique(element, xmlElementDecl);
                }
                this.fSchemaGrammar.setElementDecl(n, xmlElementDecl);
            }
        }
    }
    
    private void traverseUnique(final Element element, final XMLElementDecl xmlElementDecl) throws Exception {
        final Unique unique = new Unique(element.getAttribute("name"), this.getElementNameFor(element));
        this.traverseIdentityConstraint(unique, element);
        xmlElementDecl.unique.addElement(unique);
    }
    
    private void traverseKey(final Element element, final XMLElementDecl xmlElementDecl) throws Exception {
        final Key key = new Key(element.getAttribute("name"), this.getElementNameFor(element));
        this.traverseIdentityConstraint(key, element);
        xmlElementDecl.key.addElement(key);
    }
    
    private void traverseKeyRef(final Element element, final XMLElementDecl xmlElementDecl) throws Exception {
        final String attribute = element.getAttribute("name");
        final String attribute2 = element.getAttribute("refer");
        if (XUtil.getFirstChildElement(element.getParentNode(), "key", "name", attribute2) == null) {
            this.reportSchemaError(37, new Object[] { attribute, attribute2 });
            return;
        }
        final KeyRef keyRef = new KeyRef(attribute, attribute2, this.getElementNameFor(element));
        this.traverseIdentityConstraint(keyRef, element);
        xmlElementDecl.keyRef.addElement(keyRef);
    }
    
    private void traverseIdentityConstraint(final IdentityConstraint identityConstraint, final Element element) throws Exception {
        final Element firstChildElement = XUtil.getFirstChildElement(element, "selector");
        final String trim = firstChildElement.getAttribute("xpath").trim();
        Selector.XPath xPath;
        try {
            xPath = new Selector.XPath(trim, this.fStringPool, this.fNamespacesScope);
            identityConstraint.setSelector(new Selector(xPath, identityConstraint));
        }
        catch (XPathException ex) {
            this.reportGenericSchemaError(ex.getMessage());
            return;
        }
        final Element element2 = (Element)element.getParentNode();
        for (Element element3 = XUtil.getNextSiblingElement(firstChildElement, "field"); element3 != null; element3 = XUtil.getNextSiblingElement(element3, "field")) {
            final String trim2 = element3.getAttribute("xpath").trim();
            try {
                final Field.XPath xPath2 = new Field.XPath(trim2, this.fStringPool, this.fNamespacesScope);
                identityConstraint.addField(new Field(xPath2, this.getDatatypeValidatorFor(element2, xPath, xPath2), identityConstraint));
            }
            catch (XPathException ex2) {
                this.reportGenericSchemaError(ex2.getMessage());
                return;
            }
        }
    }
    
    private DatatypeValidator getDatatypeValidatorFor(final Element element, final Selector.XPath xPath, final Field.XPath xPath2) throws Exception {
        final String attribute = element.getAttribute("name");
        int elementDeclIndex = this.fSchemaGrammar.getElementDeclIndex(this.fStringPool.addSymbol(this.fSchemaRootElement.getAttribute("targetNamespace")), this.fStringPool.addSymbol(attribute), -1);
        final XPath.Step[] steps = xPath.getLocationPath().steps;
        for (int i = 0; i < steps.length; ++i) {
            final XPath.Step step = steps[i];
            final XPath.Axis axis = step.axis;
            final XPath.NodeTest nodeTest = step.nodeTest;
            switch (axis.type) {
                case 2: {
                    this.reportGenericSchemaError("not allowed to select attribute");
                    return null;
                }
                case 1: {
                    int n = this.fSchemaGrammar.getElementDeclIndex(nodeTest.name, elementDeclIndex);
                    if (n == -1) {
                        n = this.fSchemaGrammar.getElementDeclIndex(nodeTest.name, -1);
                    }
                    if (n == -1) {
                        this.reportGenericSchemaError("no such element \"" + this.fStringPool.toString(nodeTest.name.rawname) + '\"');
                        return null;
                    }
                    elementDeclIndex = n;
                    break;
                }
                case 3: {
                    break;
                }
                default: {
                    this.reportGenericSchemaError("invalid selector axis");
                    return null;
                }
            }
        }
        final XPath.Step[] steps2 = xPath2.getLocationPath().steps;
        for (int j = 0; j < steps2.length; ++j) {
            final XPath.Step step2 = steps2[j];
            final XPath.Axis axis2 = step2.axis;
            final XPath.NodeTest nodeTest2 = step2.nodeTest;
            switch (axis2.type) {
                case 2: {
                    if (j != steps2.length - 1) {
                        this.reportGenericSchemaError("attribute must be last step");
                        return null;
                    }
                    final int attributeDeclIndex = this.fSchemaGrammar.getAttributeDeclIndex(elementDeclIndex, nodeTest2.name);
                    if (attributeDeclIndex == -1) {
                        this.reportGenericSchemaError("no such attribute \"" + this.fStringPool.toString(nodeTest2.name.rawname) + '\"');
                    }
                    final XMLAttributeDecl xmlAttributeDecl = new XMLAttributeDecl();
                    this.fSchemaGrammar.getAttributeDecl(attributeDeclIndex, xmlAttributeDecl);
                    return xmlAttributeDecl.datatypeValidator;
                }
                case 1: {
                    int n2 = this.fSchemaGrammar.getElementDeclIndex(nodeTest2.name, elementDeclIndex);
                    if (n2 == -1) {
                        n2 = this.fSchemaGrammar.getElementDeclIndex(nodeTest2.name, -1);
                    }
                    if (n2 == -1) {
                        this.reportGenericSchemaError("no such element \"" + this.fStringPool.toString(nodeTest2.name.rawname) + '\"');
                        return null;
                    }
                    elementDeclIndex = n2;
                    if (j < steps2.length - 1) {
                        break;
                    }
                }
                case 3: {
                    if (j != steps2.length - 1) {
                        break;
                    }
                    final XMLElementDecl xmlElementDecl = new XMLElementDecl();
                    this.fSchemaGrammar.getElementDecl(elementDeclIndex, xmlElementDecl);
                    if (xmlElementDecl.type != 4) {
                        this.reportGenericSchemaError("selected element is not of simple type");
                        return null;
                    }
                    return xmlElementDecl.datatypeValidator;
                }
                default: {
                    this.reportGenericSchemaError("invalid selector axis");
                    return null;
                }
            }
        }
        this.reportGenericSchemaError("No datatype validator for field " + xPath2 + " of element " + attribute);
        return null;
    }
    
    private String getElementNameFor(final Element element) {
        final Element element2 = (Element)element.getParentNode();
        String s = element2.getAttribute("name");
        if (s.length() == 0) {
            s = element2.getAttribute("ref");
        }
        return s;
    }
    
    int getLocalPartIndex(final String s) {
        final int index = s.indexOf(":");
        String substring = s;
        if (index > -1) {
            substring = s.substring(index + 1);
        }
        return this.fStringPool.addSymbol(substring);
    }
    
    String getLocalPart(final String s) {
        final int index = s.indexOf(":");
        String substring = s;
        if (index > -1) {
            substring = s.substring(index + 1);
        }
        return substring;
    }
    
    int getPrefixIndex(final String s) {
        final int index = s.indexOf(":");
        String substring = "";
        if (index > -1) {
            substring = s.substring(0, index);
        }
        return this.fStringPool.addSymbol(substring);
    }
    
    String getPrefix(final String s) {
        final int index = s.indexOf(":");
        String substring = "";
        if (index > -1) {
            substring = s.substring(0, index);
        }
        return substring;
    }
    
    private void checkSubstitutionGroupOK(final Element element, final Element element2) {
    }
    
    private Element getTopLevelComponentByName(final String s, final String s2) throws Exception {
        Element element = null;
        SchemaInfo schemaInfo;
        for (schemaInfo = this.fSchemaInfoListRoot; schemaInfo != null || schemaInfo == this.fSchemaInfoListRoot; schemaInfo = schemaInfo.getNext()) {
            if (schemaInfo != null) {
                schemaInfo.restore();
            }
            if (s.equals("group")) {
                element = this.fSchemaGrammar.topLevelGroupDecls.get(s2);
            }
            else if (s.equals("attributeGroup")) {
                element = this.fSchemaGrammar.topLevelAttrGrpDecls.get(s2);
            }
            else if (s.equals("attribute")) {
                element = this.fSchemaGrammar.topLevelAttrDecls.get(s2);
            }
            if (element != null) {
                break;
            }
            element = XUtil.getFirstChildElement(this.fSchemaRootElement);
            if (element != null) {
                while (element != null) {
                    if (element.getLocalName().equals(s)) {
                        if (element.getAttribute("name").equals(s2)) {
                            break;
                        }
                    }
                    else if (this.fRedefineSucceeded && element.getLocalName().equals("redefine")) {
                        Element element2;
                        for (element2 = XUtil.getFirstChildElement(element); element2 != null && (!element2.getLocalName().equals(s) || !element2.getAttribute("name").equals(s2)); element2 = XUtil.getNextSiblingElement(element2)) {}
                        if (element2 != null) {
                            element = element2;
                            break;
                        }
                    }
                    element = XUtil.getNextSiblingElement(element);
                }
                if (element != null) {
                    break;
                }
                if (this.fSchemaInfoListRoot == null) {
                    break;
                }
            }
        }
        if (schemaInfo != null) {
            schemaInfo.restore();
        }
        else if (this.fSchemaInfoListRoot != null) {
            this.fSchemaInfoListRoot.restore();
        }
        return element;
    }
    
    private boolean isTopLevel(final Element element) {
        final String localName = element.getParentNode().getLocalName();
        return localName.endsWith("schema") || localName.endsWith("redefine");
    }
    
    DatatypeValidator getTypeValidatorFromNS(final String s, final String s2) throws Exception {
        return this.getDatatypeValidator(s, s2);
    }
    
    ComplexTypeInfo getTypeInfoFromNS(final String s, final String s2) throws Exception {
        final Grammar grammar = this.fGrammarResolver.getGrammar(s);
        if (grammar != null && grammar instanceof SchemaGrammar) {
            return (ComplexTypeInfo)((SchemaGrammar)grammar).getComplexTypeRegistry().get(s + "," + s2);
        }
        this.reportGenericSchemaError("could not resolve URI : " + s + " to a SchemaGrammar in getTypeInfoFromNS");
        return null;
    }
    
    DatatypeValidator getElementDeclTypeValidatorFromNS(final String s, final String s2) throws Exception {
        final Grammar grammar = this.fGrammarResolver.getGrammar(s);
        if (grammar != null && grammar instanceof SchemaGrammar) {
            final SchemaGrammar schemaGrammar = (SchemaGrammar)grammar;
            final int elementDeclIndex = schemaGrammar.getElementDeclIndex(this.fStringPool.addSymbol(s), this.fStringPool.addSymbol(s2), -1);
            DatatypeValidator datatypeValidator = null;
            if (elementDeclIndex > -1) {
                schemaGrammar.getElementDecl(elementDeclIndex, this.fTempElementDecl);
                datatypeValidator = this.fTempElementDecl.datatypeValidator;
            }
            else {
                this.reportGenericSchemaError("could not find global element : '" + s2 + " in the SchemaGrammar " + s);
            }
            return datatypeValidator;
        }
        this.reportGenericSchemaError("could not resolve URI : " + s + " to a SchemaGrammar in getELementDeclTypeValidatorFromNS");
        return null;
    }
    
    ComplexTypeInfo getElementDeclTypeInfoFromNS(final String s, final String s2) throws Exception {
        final Grammar grammar = this.fGrammarResolver.getGrammar(s);
        if (grammar != null && grammar instanceof SchemaGrammar) {
            final SchemaGrammar schemaGrammar = (SchemaGrammar)grammar;
            final int elementDeclIndex = schemaGrammar.getElementDeclIndex(this.fStringPool.addSymbol(s), this.fStringPool.addSymbol(s2), -1);
            ComplexTypeInfo elementComplexTypeInfo = null;
            if (elementDeclIndex > -1) {
                elementComplexTypeInfo = schemaGrammar.getElementComplexTypeInfo(elementDeclIndex);
            }
            else {
                this.reportGenericSchemaError("could not find global element : '" + s2 + " in the SchemaGrammar " + s);
            }
            return elementComplexTypeInfo;
        }
        this.reportGenericSchemaError("could not resolve URI : " + s + " to a SchemaGrammar in getElementDeclTypeInfoFromNS");
        return null;
    }
    
    private String traverseNotationDecl(final Element element) throws Exception {
        String s2;
        final String s = s2 = element.getAttribute("name");
        if (this.fTargetNSURIString.length() != 0) {
            s2 = this.fTargetNSURIString + ":" + s;
        }
        if (this.fNotationRegistry.get(s2) != null) {
            return s;
        }
        final String attribute = element.getAttribute("public");
        final String attribute2 = element.getAttribute("system");
        if (attribute.equals("") && attribute2.equals("")) {
            this.reportGenericSchemaError("<notation> declaration is invalid");
        }
        if (s.equals("")) {
            this.reportGenericSchemaError("<notation> declaration does not have a name");
        }
        this.fNotationRegistry.put(s2, s);
        this.checkContent(element, XUtil.getFirstChildElement(element), true);
        return s;
    }
    
    private String traverseNotationFromAnotherSchema(final String s, final String s2) throws Exception {
        final SchemaGrammar schemaGrammar = (SchemaGrammar)this.fGrammarResolver.getGrammar(s2);
        if (s2 == null || schemaGrammar == null || !(schemaGrammar instanceof SchemaGrammar)) {
            this.reportGenericSchemaError("!!Schema not found in #traverseNotationDeclFromAnotherSchema, schema uri: " + s2 + ", groupName: " + s);
            return "";
        }
        final String fTargetNSURIString = this.fTargetNSURIString;
        this.fTargetNSURIString = this.fStringPool.toString(this.fStringPool.addSymbol(schemaGrammar.getTargetNamespaceURI()));
        final String s3 = this.fNotationRegistry.get(this.fTargetNSURIString + ":" + s);
        if (s3 != null) {
            return s3;
        }
        final Element element = schemaGrammar.topLevelNotationDecls.get(s);
        if (element == null) {
            this.reportGenericSchemaError("no notation named \"" + s + "\" was defined in schema : " + s2);
            return "";
        }
        final String traverseNotationDecl = this.traverseNotationDecl(element);
        this.fTargetNSURIString = fTargetNSURIString;
        return traverseNotationDecl;
    }
    
    private int traverseGroupDecl(final Element element) throws Exception {
        final String attribute = element.getAttribute("name");
        final String attribute2 = element.getAttribute("ref");
        final Element checkContent = this.checkContent(element, XUtil.getFirstChildElement(element), true);
        if (!attribute2.equals("")) {
            if (this.isTopLevel(element)) {
                this.reportGenericSchemaError("A group with \"ref\" present must not have <schema> or <redefine> as its parent");
            }
            if (!attribute.equals("")) {
                this.reportGenericSchemaError("group " + attribute + " cannot refer to another group, but it refers to " + attribute2);
            }
            String substring = "";
            String substring2 = attribute2;
            final int index = attribute2.indexOf(":");
            if (index > 0) {
                substring = attribute2.substring(0, index);
                substring2 = attribute2.substring(index + 1);
            }
            this.fStringPool.addSymbol(substring2);
            final String resolvePrefixToURI = this.resolvePrefixToURI(substring);
            if (!resolvePrefixToURI.equals(this.fTargetNSURIString)) {
                return this.traverseGroupDeclFromAnotherSchema(substring2, resolvePrefixToURI);
            }
            final Object value = this.fGroupNameRegistry.get(resolvePrefixToURI + "," + substring2);
            if (value != null) {
                return (int)value;
            }
            int traverseGroupDecl = -1;
            final Element topLevelComponentByName = this.getTopLevelComponentByName("group", substring2);
            if (topLevelComponentByName == null) {
                this.reportGenericSchemaError("Group " + substring2 + " not found in the Schema");
            }
            else {
                traverseGroupDecl = this.traverseGroupDecl(topLevelComponentByName);
            }
            return traverseGroupDecl;
        }
        else {
            if (attribute.equals("")) {
                this.reportGenericSchemaError("a <group> must have a name or a ref present");
            }
            final String string = this.fTargetNSURIString + "," + attribute;
            final Object value2 = this.fGroupNameRegistry.get(string);
            if (value2 != null) {
                return (int)value2;
            }
            int n = -2;
            boolean b = false;
            final String s = (checkContent != null) ? checkContent.getLocalName() : "";
            if (s.equals("all")) {
                n = this.traverseAll(checkContent);
            }
            else if (s.equals("choice")) {
                n = this.traverseChoice(checkContent);
            }
            else if (s.equals("sequence")) {
                n = this.traverseSequence(checkContent);
            }
            else if (!s.equals("") || (checkContent != null && XUtil.getNextSiblingElement(checkContent) != null)) {
                b = true;
                this.reportSchemaError(14, new Object[] { "group", s });
            }
            if (checkContent != null && XUtil.getNextSiblingElement(checkContent) != null) {
                b = true;
                this.reportSchemaError(14, new Object[] { "group", s });
            }
            if (!b && checkContent != null) {
                n = this.expandContentModel(n, checkContent);
            }
            this.fGroupNameRegistry.put(string, new Integer(n));
            return n;
        }
    }
    
    private int traverseGroupDeclFromAnotherSchema(final String s, final String s2) throws Exception {
        final SchemaGrammar schemaGrammar = (SchemaGrammar)this.fGrammarResolver.getGrammar(s2);
        if (s2 == null || schemaGrammar == null || !(schemaGrammar instanceof SchemaGrammar)) {
            this.reportGenericSchemaError("!!Schema not found in #traverseGroupDeclFromAnotherSchema, schema uri: " + s2 + ", groupName: " + s);
            return -1;
        }
        final Element element = schemaGrammar.topLevelGroupDecls.get(s);
        if (element == null) {
            this.reportGenericSchemaError("no group named \"" + s + "\" was defined in schema : " + s2);
            return -1;
        }
        final NamespacesScope fNamespacesScope = this.fNamespacesScope;
        final int fTargetNSURI = this.fTargetNSURI;
        this.fTargetNSURI = this.fStringPool.addSymbol(schemaGrammar.getTargetNamespaceURI());
        this.fNamespacesScope = schemaGrammar.getNamespacesScope();
        final Element checkContent = this.checkContent(element, XUtil.getFirstChildElement(element), true);
        final String string = this.fTargetNSURIString + "," + s;
        final Object value = this.fGroupNameRegistry.get(string);
        if (value != null) {
            return (int)value;
        }
        int n = -2;
        boolean b = false;
        final String s3 = (checkContent != null) ? checkContent.getLocalName() : "";
        if (s3.equals("all")) {
            n = this.traverseAll(checkContent);
        }
        else if (s3.equals("choice")) {
            n = this.traverseChoice(checkContent);
        }
        else if (s3.equals("sequence")) {
            n = this.traverseSequence(checkContent);
        }
        else if (!s3.equals("") || (checkContent != null && XUtil.getNextSiblingElement(checkContent) != null)) {
            b = true;
            this.reportSchemaError(14, new Object[] { "group", s3 });
        }
        if (!b && checkContent != null) {
            n = this.expandContentModel(n, checkContent);
        }
        this.fGroupNameRegistry.put(string, new Integer(n));
        this.fNamespacesScope = fNamespacesScope;
        this.fTargetNSURI = fTargetNSURI;
        return n;
    }
    
    int traverseSequence(final Element element) throws Exception {
        Element element2 = this.checkContent(element, XUtil.getFirstChildElement(element), true);
        final int n = 5;
        int n2 = -2;
        int n3 = -2;
        boolean b = false;
        while (element2 != null) {
            int n4 = -2;
            b = true;
            boolean b2 = false;
            final String localName = element2.getLocalName();
            Label_0283: {
                if (localName.equals("element")) {
                    final QName traverseElementDecl = this.traverseElementDecl(element2);
                    n4 = this.fSchemaGrammar.addContentSpecNode(0, traverseElementDecl.localpart, traverseElementDecl.uri, false);
                    b2 = true;
                }
                else if (localName.equals("group")) {
                    n4 = this.traverseGroupDecl(element2);
                    if (n4 == -1) {
                        break Label_0283;
                    }
                    b2 = true;
                }
                else if (localName.equals("choice")) {
                    n4 = this.traverseChoice(element2);
                    b2 = true;
                }
                else if (localName.equals("sequence")) {
                    n4 = this.traverseSequence(element2);
                    b2 = true;
                }
                else if (localName.equals("any")) {
                    n4 = this.traverseAny(element2);
                    b2 = true;
                }
                else {
                    this.reportSchemaError(14, new Object[] { "group", localName });
                }
                if (b2) {
                    n4 = this.expandContentModel(n4, element2);
                }
                if (n2 == -2) {
                    n2 = n4;
                }
                else if (n3 == -2) {
                    n3 = n4;
                }
                else {
                    n2 = this.fSchemaGrammar.addContentSpecNode(n, n2, n3, false);
                    n3 = n4;
                }
            }
            element2 = XUtil.getNextSiblingElement(element2);
        }
        if (b && n3 != -2) {
            n2 = this.fSchemaGrammar.addContentSpecNode(n, n2, n3, false);
        }
        return n2;
    }
    
    int traverseChoice(final Element element) throws Exception {
        Element element2 = this.checkContent(element, XUtil.getFirstChildElement(element), true);
        final int n = 4;
        int n2 = -2;
        int n3 = -2;
        boolean b = false;
        while (element2 != null) {
            int n4 = -2;
            b = true;
            boolean b2 = false;
            final String localName = element2.getLocalName();
            Label_0283: {
                if (localName.equals("element")) {
                    final QName traverseElementDecl = this.traverseElementDecl(element2);
                    n4 = this.fSchemaGrammar.addContentSpecNode(0, traverseElementDecl.localpart, traverseElementDecl.uri, false);
                    b2 = true;
                }
                else if (localName.equals("group")) {
                    n4 = this.traverseGroupDecl(element2);
                    if (n4 == -1) {
                        break Label_0283;
                    }
                    b2 = true;
                }
                else if (localName.equals("choice")) {
                    n4 = this.traverseChoice(element2);
                    b2 = true;
                }
                else if (localName.equals("sequence")) {
                    n4 = this.traverseSequence(element2);
                    b2 = true;
                }
                else if (localName.equals("any")) {
                    n4 = this.traverseAny(element2);
                    b2 = true;
                }
                else {
                    this.reportSchemaError(14, new Object[] { "group", localName });
                }
                if (b2) {
                    n4 = this.expandContentModel(n4, element2);
                }
                if (n2 == -2) {
                    n2 = n4;
                }
                else if (n3 == -2) {
                    n3 = n4;
                }
                else {
                    n2 = this.fSchemaGrammar.addContentSpecNode(n, n2, n3, false);
                    n3 = n4;
                }
            }
            element2 = XUtil.getNextSiblingElement(element2);
        }
        if (b && n3 != -2) {
            n2 = this.fSchemaGrammar.addContentSpecNode(n, n2, n3, false);
        }
        return n2;
    }
    
    int traverseAll(final Element element) throws Exception {
        Element element2 = this.checkContent(element, XUtil.getFirstChildElement(element), true);
        if (element2 == null) {
            return -2;
        }
        int[] array = null;
        int n = 0;
        int allCalcWrapper = -2;
        while (element2 != null) {
            final String localName = element2.getLocalName();
            if (!localName.equals("element")) {
                this.reportGenericSchemaError("Content of all group is restricted to elements only.  '" + localName + "' was seen and is being ignored");
                break;
            }
            final QName traverseElementDecl = this.traverseElementDecl(element2);
            int n2 = this.fSchemaGrammar.addContentSpecNode(0, traverseElementDecl.localpart, traverseElementDecl.uri, false);
            if (true) {
                n2 = this.expandContentModel(n2, element2);
            }
            if (n2 != -2) {
                try {
                    array[n] = n2;
                }
                catch (NullPointerException ex) {
                    array = new int[32];
                    array[n] = n2;
                }
                catch (ArrayIndexOutOfBoundsException ex2) {
                    System.arraycopy(array, 0, new int[array.length * 2], 0, array.length);
                    array[n] = n2;
                }
                ++n;
            }
            element2 = XUtil.getNextSiblingElement(element2);
        }
        if (n == 0) {
            return allCalcWrapper;
        }
        try {
            allCalcWrapper = this.allCalcWrapper(array, n);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            this.reportGenericSchemaError("The size of the <all> declaration in your schema is too large for this parser and elements using it will not validate correctly.");
        }
        return allCalcWrapper;
    }
    
    private int allCalcWrapper(final int[] array, final int n) throws Exception {
        final int n2 = n / 2;
        final int[] array2 = new int[n];
        System.arraycopy(array, 0, array2, 0, n);
        if (array2.length == 1) {
            return array2[0];
        }
        if (array2.length < 1) {
            return -2;
        }
        if (n2 > array2.length) {
            this.reportGenericSchemaError("The size of the permutations " + n2 + " cannot be greater than the length of the array to be permuted; error in processing of <all>!");
            return -2;
        }
        if (array2.length <= 3) {
            return this.allCombo(array2);
        }
        return this.allCalc(array2, 0, n2, 0, new int[array2.length - n2], -2);
    }
    
    private int allCombo(final int[] array) throws Exception {
        if (array.length == 2) {
            return this.fSchemaGrammar.addContentSpecNode(4, this.createSeq(new int[] { array[0], array[1] }), this.createSeq(new int[] { array[1], array[0] }), false);
        }
        if (array.length == 3) {
            return this.fSchemaGrammar.addContentSpecNode(4, this.fSchemaGrammar.addContentSpecNode(4, this.fSchemaGrammar.addContentSpecNode(4, this.fSchemaGrammar.addContentSpecNode(4, this.fSchemaGrammar.addContentSpecNode(4, this.createSeq(new int[] { array[0], array[1], array[2] }), this.createSeq(new int[] { array[0], array[2], array[1] }), false), this.createSeq(new int[] { array[1], array[0], array[2] }), false), this.createSeq(new int[] { array[1], array[2], array[0] }), false), this.createSeq(new int[] { array[2], array[1], array[0] }), false), this.createSeq(new int[] { array[2], array[0], array[1] }), false);
        }
        return -2;
    }
    
    private int allCalc(final int[] array, int n, int n2, final int n3, final int[] array2, int n4) throws Exception {
        if (array.length - n2 - n == 1) {
            final int[] array3 = new int[n2 + n];
            for (int i = n; i < array.length; ++i) {
                this.arrayProducer(array, i, array3, array2, n3);
                final int addContentSpecNode = this.fSchemaGrammar.addContentSpecNode(5, this.allCalcWrapper(array3, array3.length), this.allCalcWrapper(array2, array2.length), false);
                if (n4 != -2) {
                    n4 = this.fSchemaGrammar.addContentSpecNode(4, n4, addContentSpecNode, false);
                }
                else {
                    n4 = addContentSpecNode;
                }
            }
            return n4;
        }
        for (int j = n; j < array.length; ++j) {
            final int[] array4 = new int[array.length - 1];
            this.arrayProducer(array, j, array4, array2, n3);
            n4 = this.allCalc(array4, n, n2, n3 + 1, array2, n4);
            ++n;
            --n2;
        }
        return n4;
    }
    
    private void arrayProducer(final int[] array, final int n, final int[] array2, final int[] array3, final int n2) {
        array3[n2] = array[n];
        if (n > 0) {
            System.arraycopy(array, 0, array2, 0, n);
        }
        if (n < array.length - 1) {
            System.arraycopy(array, n + 1, array2, n, array.length - n - 1);
        }
    }
    
    private int createSeq(final int[] array) throws Exception {
        int addContentSpecNode = array[0];
        int n = array[1];
        for (int i = 2; i < array.length; ++i) {
            addContentSpecNode = this.fSchemaGrammar.addContentSpecNode(5, addContentSpecNode, n, false);
            n = array[i];
        }
        return this.fSchemaGrammar.addContentSpecNode(5, addContentSpecNode, n, false);
    }
    
    private int parseInt(final String s) throws Exception {
        if (s.equals("*")) {
            return -1;
        }
        return Integer.parseInt(s);
    }
    
    private int parseSimpleFinal(final String s) throws Exception {
        if (s.equals("#all")) {
            return 30;
        }
        final int n = 0;
        int n2 = 0;
        int n3 = 0;
        final int n4 = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals("restriction")) {
                if (n2 == 0) {
                    n2 = 2;
                }
                else {
                    this.reportGenericSchemaError("restriction in set twice");
                }
            }
            else if (nextToken.equals("list")) {
                if (n3 == 0) {
                    n3 = 8;
                }
                else {
                    this.reportGenericSchemaError("list in set twice");
                }
            }
            else {
                this.reportGenericSchemaError("Invalid value (" + s + ")");
            }
        }
        return n + n2 + n3 + n4;
    }
    
    private int parseComplexContent(final String s) throws Exception {
        if (s.equals("empty")) {
            return 0;
        }
        if (s.equals("elementOnly")) {
            return 3;
        }
        if (s.equals("textOnly")) {
            return 4;
        }
        if (s.equals("mixed")) {
            return 2;
        }
        this.reportGenericSchemaError("Invalid value for content");
        return -1;
    }
    
    private int parseDerivationSet(final String s) throws Exception {
        if (s.equals("#all")) {
            return 7;
        }
        int n = 0;
        int n2 = 0;
        final int n3 = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals("extension")) {
                if (n == 0) {
                    n = 1;
                }
                else {
                    this.reportGenericSchemaError("extension already in set");
                }
            }
            else if (nextToken.equals("restriction")) {
                if (n2 == 0) {
                    n2 = 2;
                }
                else {
                    this.reportGenericSchemaError("restriction already in set");
                }
            }
            else {
                this.reportGenericSchemaError("Invalid final value (" + s + ")");
            }
        }
        return n + n2 + n3;
    }
    
    private int parseBlockSet(final String s) throws Exception {
        if (s.equals("#all")) {
            return 47;
        }
        int n = 0;
        int n2 = 0;
        final int n3 = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals("substitutionGroup")) {
                if (n == 0) {
                    n = 32;
                }
                else {
                    this.reportGenericSchemaError("'substitutionGroup' already in set");
                }
            }
            else if (nextToken.equals("extension")) {
                if (n == 0) {
                    n = 1;
                }
                else {
                    this.reportGenericSchemaError("extension already in set");
                }
            }
            else if (nextToken.equals("list")) {
                if (n == 0) {
                    n = 8;
                }
                else {
                    this.reportGenericSchemaError("'list' already in set");
                }
            }
            else if (nextToken.equals("restriction")) {
                if (n2 == 0) {
                    n2 = 2;
                }
                else {
                    this.reportGenericSchemaError("restriction already in set");
                }
            }
            else {
                this.reportGenericSchemaError("Invalid final value (" + s + ")");
            }
        }
        return n + n2 + n3;
    }
    
    private int parseFinalSet(final String s) throws Exception {
        if (s.equals("#all")) {
            return 47;
        }
        int n = 0;
        int n2 = 0;
        final int n3 = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals("substitutionGroup")) {
                if (n == 0) {
                    n = 32;
                }
                else {
                    this.reportGenericSchemaError("'substitutionGroup' already in set");
                }
            }
            else if (nextToken.equals("extension")) {
                if (n == 0) {
                    n = 1;
                }
                else {
                    this.reportGenericSchemaError("extension already in set");
                }
            }
            else if (nextToken.equals("list")) {
                if (n == 0) {
                    n = 8;
                }
                else {
                    this.reportGenericSchemaError("'list' already in set");
                }
            }
            else if (nextToken.equals("restriction")) {
                if (n2 == 0) {
                    n2 = 2;
                }
                else {
                    this.reportGenericSchemaError("restriction already in set");
                }
            }
            else {
                this.reportGenericSchemaError("Invalid final value (" + s + ")");
            }
        }
        return n + n2 + n3;
    }
    
    private void reportGenericSchemaError(final String s) throws Exception {
        if (this.fErrorReporter == null) {
            System.err.println("__TraverseSchemaError__ : " + s);
        }
        else {
            this.reportSchemaError(23, new Object[] { s });
        }
    }
    
    private void reportSchemaError(final int n, final Object[] array) throws Exception {
        if (this.fErrorReporter == null) {
            System.out.println("__TraverseSchemaError__ : " + SchemaMessageProvider.fgMessageKeys[n]);
            for (int i = 0; i < array.length; ++i) {
                System.out.println((String)array[i]);
            }
        }
        else {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/xml-schema-1", n, 0, array, 1);
        }
    }
    
    static {
        IDENTITY_CONSTRAINTS = new String[] { "unique", "key", "keyref" };
    }
    
    public class ComplexTypeInfo
    {
        public String typeName;
        public DatatypeValidator baseDataTypeValidator;
        public ComplexTypeInfo baseComplexTypeInfo;
        public int derivedBy;
        public int blockSet;
        public int finalSet;
        public boolean isAbstract;
        public int scopeDefined;
        public int contentType;
        public int contentSpecHandle;
        public int templateElementIndex;
        public int attlistHead;
        public DatatypeValidator datatypeValidator;
        
        public ComplexTypeInfo() {
            this.derivedBy = 0;
            this.blockSet = 0;
            this.finalSet = 0;
            this.isAbstract = false;
            this.scopeDefined = -1;
            this.contentSpecHandle = -1;
            this.templateElementIndex = -1;
            this.attlistHead = -1;
        }
    }
    
    private class ComplexTypeRecoverableError extends Exception
    {
        ComplexTypeRecoverableError() {
        }
        
        ComplexTypeRecoverableError(final String s) {
            super(s);
        }
    }
    
    static class Resolver implements EntityResolver
    {
        private static final String[] SYSTEM;
        private static final String[] PATH;
        
        public InputSource resolveEntity(final String publicId, final String systemId) throws IOException {
            for (int i = 0; i < Resolver.SYSTEM.length; ++i) {
                if (systemId.equals(Resolver.SYSTEM[i])) {
                    final InputSource inputSource = new InputSource(this.getClass().getResourceAsStream(Resolver.PATH[i]));
                    inputSource.setPublicId(publicId);
                    inputSource.setSystemId(systemId);
                    return inputSource;
                }
            }
            return null;
        }
        
        static {
            SYSTEM = new String[] { "http://www.w3.org/TR/2000/WD-xmlschema-1-20000407/structures.dtd", "http://www.w3.org/TR/2000/WD-xmlschema-1-20000407/datatypes.dtd", "http://www.w3.org/TR/2000/WD-xmlschema-1-20000407/versionInfo.ent" };
            PATH = new String[] { "structures.dtd", "datatypes.dtd", "versionInfo.ent" };
        }
    }
    
    static class ErrorHandler implements org.xml.sax.ErrorHandler
    {
        public void warning(final SAXParseException ex) {
            System.err.println("[Warning] " + this.getLocationString(ex) + ": " + ex.getMessage());
        }
        
        public void error(final SAXParseException ex) {
            System.err.println("[Error] " + this.getLocationString(ex) + ": " + ex.getMessage());
        }
        
        public void fatalError(final SAXParseException ex) throws SAXException {
            System.err.println("[Fatal Error] " + this.getLocationString(ex) + ": " + ex.getMessage());
            throw ex;
        }
        
        private String getLocationString(final SAXParseException ex) {
            final StringBuffer sb = new StringBuffer();
            String s = ex.getSystemId();
            if (s != null) {
                final int lastIndex = s.lastIndexOf(47);
                if (lastIndex != -1) {
                    s = s.substring(lastIndex + 1);
                }
                sb.append(s);
            }
            sb.append(':');
            sb.append(ex.getLineNumber());
            sb.append(':');
            sb.append(ex.getColumnNumber());
            return sb.toString();
        }
    }
    
    static class IgnoreWhitespaceParser extends DOMParser
    {
        public void ignorableWhitespace(final char[] array, final int n, final int n2) {
        }
        
        public void ignorableWhitespace(final int n) {
        }
    }
    
    public class SchemaInfo
    {
        private Element saveRoot;
        private boolean saveElementDefaultQualified;
        private boolean saveAttributeDefaultQualified;
        private int saveScope;
        private String savedSchemaURL;
        private SchemaInfo nextRoot;
        private SchemaInfo prevRoot;
        
        public SchemaInfo(final boolean saveElementDefaultQualified, final boolean saveAttributeDefaultQualified, final int saveScope, final String savedSchemaURL, final Element saveRoot, final SchemaInfo nextRoot, final SchemaInfo prevRoot) {
            this.saveElementDefaultQualified = TraverseSchema.this.fElementDefaultQualified;
            this.saveAttributeDefaultQualified = TraverseSchema.this.fAttributeDefaultQualified;
            this.saveScope = TraverseSchema.this.fCurrentScope;
            this.savedSchemaURL = TraverseSchema.this.fCurrentSchemaURL;
            this.saveElementDefaultQualified = saveElementDefaultQualified;
            this.saveAttributeDefaultQualified = saveAttributeDefaultQualified;
            this.saveScope = saveScope;
            this.savedSchemaURL = savedSchemaURL;
            this.saveRoot = saveRoot;
            this.nextRoot = nextRoot;
            this.prevRoot = prevRoot;
        }
        
        public void setNext(final SchemaInfo nextRoot) {
            this.nextRoot = nextRoot;
        }
        
        public SchemaInfo getNext() {
            return this.nextRoot;
        }
        
        public void setPrev(final SchemaInfo prevRoot) {
            this.prevRoot = prevRoot;
        }
        
        public String getCurrentSchemaURL() {
            return this.savedSchemaURL;
        }
        
        public SchemaInfo getPrev() {
            return this.prevRoot;
        }
        
        public Element getRoot() {
            return this.saveRoot;
        }
        
        public void restore() {
            TraverseSchema.this.fCurrentSchemaURL = this.savedSchemaURL;
            TraverseSchema.this.fCurrentScope = this.saveScope;
            TraverseSchema.this.fElementDefaultQualified = this.saveElementDefaultQualified;
            TraverseSchema.this.fAttributeDefaultQualified = this.saveAttributeDefaultQualified;
            TraverseSchema.this.fSchemaRootElement = this.saveRoot;
        }
    }
}
