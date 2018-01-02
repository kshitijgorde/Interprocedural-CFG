// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.impl.xs.dom.ElementNSImpl;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import java.io.IOException;
import org.apache.xerces.impl.xs.XMLSchemaLoader;
import org.apache.xerces.xni.QName;
import java.util.Stack;
import org.apache.xerces.impl.xs.SchemaNamespaceSupport;
import org.w3c.dom.Node;
import org.apache.xerces.impl.xs.XMLSchemaException;
import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import org.w3c.dom.Document;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.impl.xs.util.SimpleLocator;
import org.apache.xerces.impl.xs.XSElementDecl;
import org.apache.xerces.impl.xs.XSComplexTypeDecl;
import org.w3c.dom.Element;
import org.apache.xerces.impl.xs.XSParticleDecl;
import org.apache.xerces.impl.xs.dom.DOMNodePool;
import org.apache.xerces.impl.xs.dom.DOMParser;
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
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    public static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
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
    private boolean fAllowJavaEncodings;
    private Hashtable fUnparsedAttributeRegistry;
    private Hashtable fUnparsedAttributeGroupRegistry;
    private Hashtable fUnparsedElementRegistry;
    private Hashtable fUnparsedGroupRegistry;
    private Hashtable fUnparsedIdentityConstraintRegistry;
    private Hashtable fUnparsedNotationRegistry;
    private Hashtable fUnparsedTypeRegistry;
    private Hashtable fXSDocumentInfoRegistry;
    private Hashtable fDependencyMap;
    private Hashtable fImportMap;
    private Vector fAllTNSs;
    private Hashtable fLocationPairs;
    private Hashtable fTraversed;
    private Hashtable fDoc2SystemId;
    private XSDocumentInfo fRoot;
    private Hashtable fDoc2XSDocumentMap;
    private Hashtable fRedefine2XSDMap;
    private Hashtable fRedefine2NSSupport;
    private Hashtable fRedefinedRestrictedAttributeGroupRegistry;
    private Hashtable fRedefinedRestrictedGroupRegistry;
    private boolean fLastSchemaWasDuplicate;
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
    DOMParser fSchemaParser;
    final DOMNodePool fDOMPool;
    private static final int INIT_STACK_SIZE = 30;
    private static final int INC_STACK_SIZE = 10;
    private int fLocalElemStackPos;
    private XSParticleDecl[] fParticle;
    private Element[] fLocalElementDecl;
    private int[] fAllContext;
    private XSComplexTypeDecl[] fEnclosingCT;
    private String[][] fLocalElemNamespaceContext;
    private static final int INIT_KEYREF_STACK = 2;
    private static final int INC_KEYREF_STACK_AMOUNT = 2;
    private int fKeyrefStackPos;
    private Element[] fKeyrefs;
    private XSElementDecl[] fKeyrefElems;
    private String[][] fKeyrefNamespaceContext;
    private static final String[][] NS_ERROR_CODES;
    private static final String[] ELE_ERROR_CODES;
    private Vector fReportedTNS;
    private static final String[] COMP_TYPE;
    private static final String[] DOC_ERROR_CODES;
    private SimpleLocator xl;
    
    private String null2EmptyString(final String ns) {
        return (ns == null) ? XMLSymbols.EMPTY_STRING : ns;
    }
    
    private String emptyString2Null(final String ns) {
        return (ns == XMLSymbols.EMPTY_STRING) ? null : ns;
    }
    
    public XSDHandler(final XSGrammarBucket gBucket) {
        this.fNotationRegistry = new Hashtable();
        this.fDeclPool = null;
        this.fAllowJavaEncodings = false;
        this.fUnparsedAttributeRegistry = new Hashtable();
        this.fUnparsedAttributeGroupRegistry = new Hashtable();
        this.fUnparsedElementRegistry = new Hashtable();
        this.fUnparsedGroupRegistry = new Hashtable();
        this.fUnparsedIdentityConstraintRegistry = new Hashtable();
        this.fUnparsedNotationRegistry = new Hashtable();
        this.fUnparsedTypeRegistry = new Hashtable();
        this.fXSDocumentInfoRegistry = new Hashtable();
        this.fDependencyMap = new Hashtable();
        this.fImportMap = new Hashtable();
        this.fAllTNSs = new Vector();
        this.fLocationPairs = null;
        this.fTraversed = new Hashtable();
        this.fDoc2SystemId = new Hashtable();
        this.fRoot = null;
        this.fDoc2XSDocumentMap = new Hashtable();
        this.fRedefine2XSDMap = new Hashtable();
        this.fRedefine2NSSupport = new Hashtable();
        this.fRedefinedRestrictedAttributeGroupRegistry = new Hashtable();
        this.fRedefinedRestrictedGroupRegistry = new Hashtable();
        this.fDOMPool = new DOMNodePool();
        this.fLocalElemStackPos = 0;
        this.fParticle = new XSParticleDecl[30];
        this.fLocalElementDecl = new Element[30];
        this.fAllContext = new int[30];
        this.fEnclosingCT = new XSComplexTypeDecl[30];
        this.fLocalElemNamespaceContext = new String[30][1];
        this.fKeyrefStackPos = 0;
        this.fKeyrefs = new Element[2];
        this.fKeyrefElems = new XSElementDecl[2];
        this.fKeyrefNamespaceContext = new String[2][1];
        this.fReportedTNS = null;
        this.xl = new SimpleLocator();
        this.fGrammarBucket = gBucket;
        this.fSchemaGrammarDescription = new XSDDescription();
    }
    
    public SchemaGrammar parseSchema(final XMLInputSource is, final XSDDescription desc, final Hashtable locationPairs) {
        this.fLocationPairs = locationPairs;
        final SchemaGrammar grammar = this.findGrammar(desc);
        if (grammar != null) {
            return grammar;
        }
        this.fDOMPool.reset();
        if (this.fSchemaParser != null) {
            this.fSchemaParser.setPool(this.fDOMPool);
        }
        final short referType = desc.getContextType();
        String schemaNamespace = desc.getTargetNamespace();
        if (schemaNamespace != null) {
            schemaNamespace = this.fSymbolTable.addSymbol(schemaNamespace);
        }
        this.prepareForParse();
        final Document schemaRoot = this.getSchema(schemaNamespace, is, referType == 3, referType, null);
        if (schemaRoot == null) {
            return null;
        }
        if (schemaNamespace == null && referType == 3) {
            final Element schemaElem = DOMUtil.getRoot(schemaRoot);
            schemaNamespace = DOMUtil.getAttrValue(schemaElem, SchemaSymbols.ATT_TARGETNAMESPACE);
            if (schemaNamespace != null && schemaNamespace.length() > 0) {
                schemaNamespace = this.fSymbolTable.addSymbol(schemaNamespace);
                desc.setTargetNamespace(schemaNamespace);
                final String schemaId = XMLEntityManager.expandSystemId(desc.getLiteralSystemId(), desc.getBaseSystemId());
                final XSDKey key = new XSDKey(schemaId, referType, schemaNamespace);
                this.fTraversed.put(key, schemaRoot);
                if (schemaId != null) {
                    this.fDoc2SystemId.put(schemaRoot, schemaId);
                }
            }
        }
        this.prepareForTraverse();
        this.fRoot = this.constructTrees(schemaRoot, is.getSystemId(), desc);
        if (this.fRoot == null) {
            return null;
        }
        this.buildGlobalNameRegistries();
        this.traverseSchemas();
        this.traverseLocalElements();
        this.resolveKeyRefs();
        for (int i = this.fAllTNSs.size() - 1; i >= 0; --i) {
            final String tns = this.fAllTNSs.elementAt(i);
            final Vector ins = this.fImportMap.get(tns);
            final SchemaGrammar sg = this.fGrammarBucket.getGrammar(this.emptyString2Null(tns));
            if (sg != null) {
                int count = 0;
                for (int j = 0; j < ins.size(); ++j) {
                    final SchemaGrammar isg = this.fGrammarBucket.getGrammar(ins.elementAt(j));
                    if (isg != null) {
                        ins.setElementAt(isg, count++);
                    }
                }
                ins.setSize(count);
                sg.setImportedGrammars(ins);
            }
        }
        return this.fGrammarBucket.getGrammar(this.fRoot.fTargetNamespace);
    }
    
    protected SchemaGrammar findGrammar(final XSDDescription desc) {
        SchemaGrammar sg = this.fGrammarBucket.getGrammar(desc.getTargetNamespace());
        if (sg == null && this.fGrammarPool != null) {
            sg = (SchemaGrammar)this.fGrammarPool.retrieveGrammar(desc);
            if (sg != null && !this.fGrammarBucket.putGrammar(sg, true)) {
                this.reportSchemaWarning("GrammarConflict", null, null);
                sg = null;
            }
        }
        return sg;
    }
    
    protected XSDocumentInfo constructTrees(final Document schemaRoot, final String locationHint, final XSDDescription desc) {
        if (schemaRoot == null) {
            return null;
        }
        String callerTNS = desc.getTargetNamespace();
        final short referType = desc.getContextType();
        XSDocumentInfo currSchemaInfo = null;
        try {
            currSchemaInfo = new XSDocumentInfo(schemaRoot, this.fAttributeChecker, this.fSymbolTable);
        }
        catch (XMLSchemaException se) {
            this.reportSchemaError(XSDHandler.ELE_ERROR_CODES[referType], new Object[] { locationHint }, DOMUtil.getRoot(schemaRoot));
            return null;
        }
        if (currSchemaInfo.fTargetNamespace != null && currSchemaInfo.fTargetNamespace.length() == 0) {
            this.reportSchemaWarning("EmptyTargetNamespace", new Object[] { locationHint }, DOMUtil.getRoot(schemaRoot));
            currSchemaInfo.fTargetNamespace = null;
        }
        if (callerTNS != null) {
            final int secondIdx = 0;
            if (referType == 0 || referType == 1) {
                if (currSchemaInfo.fTargetNamespace == null) {
                    currSchemaInfo.fTargetNamespace = callerTNS;
                    currSchemaInfo.fIsChameleonSchema = true;
                }
                else if (callerTNS != currSchemaInfo.fTargetNamespace) {
                    this.reportSchemaError(XSDHandler.NS_ERROR_CODES[referType][secondIdx], new Object[] { callerTNS, currSchemaInfo.fTargetNamespace }, DOMUtil.getRoot(schemaRoot));
                    return null;
                }
            }
            else if (referType != 3 && callerTNS != currSchemaInfo.fTargetNamespace) {
                this.reportSchemaError(XSDHandler.NS_ERROR_CODES[referType][secondIdx], new Object[] { callerTNS, currSchemaInfo.fTargetNamespace }, DOMUtil.getRoot(schemaRoot));
                return null;
            }
        }
        else if (currSchemaInfo.fTargetNamespace != null) {
            if (referType != 3) {
                final int secondIdx = 1;
                this.reportSchemaError(XSDHandler.NS_ERROR_CODES[referType][secondIdx], new Object[] { callerTNS, currSchemaInfo.fTargetNamespace }, DOMUtil.getRoot(schemaRoot));
                return null;
            }
            desc.setTargetNamespace(currSchemaInfo.fTargetNamespace);
            callerTNS = currSchemaInfo.fTargetNamespace;
        }
        currSchemaInfo.addAllowedNS(currSchemaInfo.fTargetNamespace);
        SchemaGrammar sg = null;
        if (referType == 0 || referType == 1) {
            sg = this.fGrammarBucket.getGrammar(currSchemaInfo.fTargetNamespace);
        }
        else {
            sg = new SchemaGrammar(currSchemaInfo.fTargetNamespace, desc.makeClone());
            this.fGrammarBucket.putGrammar(sg);
        }
        sg.addDocument(null, this.fDoc2SystemId.get(currSchemaInfo));
        this.fDoc2XSDocumentMap.put(schemaRoot, currSchemaInfo);
        final Vector dependencies = new Vector();
        final Element rootNode = DOMUtil.getRoot(schemaRoot);
        Document newSchemaRoot = null;
        for (Element child = DOMUtil.getFirstChildElement(rootNode); child != null; child = DOMUtil.getNextSiblingElement(child)) {
            String schemaNamespace = null;
            String schemaHint = null;
            final String localName = DOMUtil.getLocalName(child);
            short refType = -1;
            if (!localName.equals(SchemaSymbols.ELT_ANNOTATION)) {
                if (localName.equals(SchemaSymbols.ELT_IMPORT)) {
                    refType = 2;
                    final Object[] includeAttrs = this.fAttributeChecker.checkAttributes(child, true, currSchemaInfo);
                    schemaHint = (String)includeAttrs[XSAttributeChecker.ATTIDX_SCHEMALOCATION];
                    schemaNamespace = (String)includeAttrs[XSAttributeChecker.ATTIDX_NAMESPACE];
                    if (schemaNamespace != null) {
                        schemaNamespace = this.fSymbolTable.addSymbol(schemaNamespace);
                    }
                    if (schemaNamespace == currSchemaInfo.fTargetNamespace) {
                        this.reportSchemaError("src-import.1.1", new Object[] { schemaNamespace }, child);
                    }
                    this.fAttributeChecker.returnAttrArray(includeAttrs, currSchemaInfo);
                    if (currSchemaInfo.isAllowedNS(schemaNamespace)) {
                        continue;
                    }
                    currSchemaInfo.addAllowedNS(schemaNamespace);
                    final String tns = this.null2EmptyString(currSchemaInfo.fTargetNamespace);
                    Vector ins = this.fImportMap.get(tns);
                    if (ins == null) {
                        this.fAllTNSs.addElement(tns);
                        ins = new Vector();
                        this.fImportMap.put(tns, ins);
                        ins.addElement(schemaNamespace);
                    }
                    else if (!ins.contains(schemaNamespace)) {
                        ins.addElement(schemaNamespace);
                    }
                    this.fSchemaGrammarDescription.reset();
                    this.fSchemaGrammarDescription.setContextType((short)2);
                    this.fSchemaGrammarDescription.setBaseSystemId(this.fDoc2SystemId.get(schemaRoot));
                    this.fSchemaGrammarDescription.setLocationHints(new String[] { schemaHint });
                    this.fSchemaGrammarDescription.setTargetNamespace(schemaNamespace);
                    if (this.findGrammar(this.fSchemaGrammarDescription) != null) {
                        continue;
                    }
                    newSchemaRoot = this.getSchema(this.fSchemaGrammarDescription, false, child);
                }
                else {
                    if (!localName.equals(SchemaSymbols.ELT_INCLUDE) && !localName.equals(SchemaSymbols.ELT_REDEFINE)) {
                        break;
                    }
                    final Object[] includeAttrs = this.fAttributeChecker.checkAttributes(child, true, currSchemaInfo);
                    schemaHint = (String)includeAttrs[XSAttributeChecker.ATTIDX_SCHEMALOCATION];
                    if (localName.equals(SchemaSymbols.ELT_REDEFINE)) {
                        this.fRedefine2NSSupport.put(child, new SchemaNamespaceSupport(currSchemaInfo.fNamespaceSupport));
                    }
                    this.fAttributeChecker.returnAttrArray(includeAttrs, currSchemaInfo);
                    if (schemaHint == null) {
                        this.reportSchemaError("s4s-att-must-appear", new Object[] { "<include> or <redefine>", "schemaLocation" }, child);
                    }
                    boolean mustResolve = false;
                    refType = 0;
                    if (localName.equals(SchemaSymbols.ELT_REDEFINE)) {
                        mustResolve = this.nonAnnotationContent(child);
                        refType = 1;
                    }
                    this.fSchemaGrammarDescription.reset();
                    this.fSchemaGrammarDescription.setContextType(refType);
                    this.fSchemaGrammarDescription.setBaseSystemId(this.fDoc2SystemId.get(schemaRoot));
                    this.fSchemaGrammarDescription.setLocationHints(new String[] { schemaHint });
                    this.fSchemaGrammarDescription.setTargetNamespace(callerTNS);
                    newSchemaRoot = this.getSchema(this.fSchemaGrammarDescription, mustResolve, child);
                    schemaNamespace = currSchemaInfo.fTargetNamespace;
                }
                XSDocumentInfo newSchemaInfo = null;
                if (this.fLastSchemaWasDuplicate) {
                    newSchemaInfo = this.fDoc2XSDocumentMap.get(newSchemaRoot);
                }
                else {
                    newSchemaInfo = this.constructTrees(newSchemaRoot, schemaHint, this.fSchemaGrammarDescription);
                }
                if (localName.equals(SchemaSymbols.ELT_REDEFINE) && newSchemaInfo != null) {
                    this.fRedefine2XSDMap.put(child, newSchemaInfo);
                }
                if (newSchemaRoot != null) {
                    if (newSchemaInfo != null) {
                        dependencies.addElement(newSchemaInfo);
                    }
                    newSchemaRoot = null;
                }
            }
        }
        this.fDependencyMap.put(currSchemaInfo, dependencies);
        return currSchemaInfo;
    }
    
    protected void buildGlobalNameRegistries() {
        final Stack schemasToProcess = new Stack();
        schemasToProcess.push(this.fRoot);
        while (!schemasToProcess.empty()) {
            final XSDocumentInfo currSchemaDoc = schemasToProcess.pop();
            final Document currDoc = currSchemaDoc.fSchemaDoc;
            if (DOMUtil.isHidden(currDoc)) {
                continue;
            }
            final Element currRoot = DOMUtil.getRoot(currDoc);
            boolean dependenciesCanOccur = true;
            for (Element globalComp = DOMUtil.getFirstChildElement(currRoot); globalComp != null; globalComp = DOMUtil.getNextSiblingElement(globalComp)) {
                if (!DOMUtil.getLocalName(globalComp).equals(SchemaSymbols.ELT_ANNOTATION)) {
                    if (DOMUtil.getLocalName(globalComp).equals(SchemaSymbols.ELT_INCLUDE) || DOMUtil.getLocalName(globalComp).equals(SchemaSymbols.ELT_IMPORT)) {
                        if (!dependenciesCanOccur) {
                            this.reportSchemaError("sch-props-correct.1", new Object[] { DOMUtil.getLocalName(globalComp) }, globalComp);
                        }
                        DOMUtil.setHidden(globalComp);
                    }
                    else if (DOMUtil.getLocalName(globalComp).equals(SchemaSymbols.ELT_REDEFINE)) {
                        if (!dependenciesCanOccur) {
                            this.reportSchemaError("sch-props-correct.1", new Object[] { DOMUtil.getLocalName(globalComp) }, globalComp);
                        }
                        for (Element redefineComp = DOMUtil.getFirstChildElement(globalComp); redefineComp != null; redefineComp = DOMUtil.getNextSiblingElement(redefineComp)) {
                            final String lName = DOMUtil.getAttrValue(redefineComp, SchemaSymbols.ATT_NAME);
                            if (lName.length() != 0) {
                                final String qName = (currSchemaDoc.fTargetNamespace == null) ? ("," + lName) : (currSchemaDoc.fTargetNamespace + "," + lName);
                                final String componentType = DOMUtil.getLocalName(redefineComp);
                                if (componentType.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                                    this.checkForDuplicateNames(qName, this.fUnparsedAttributeGroupRegistry, redefineComp, currSchemaDoc);
                                    final String targetLName = DOMUtil.getAttrValue(redefineComp, SchemaSymbols.ATT_NAME) + "_fn3dktizrknc9pi";
                                    this.renameRedefiningComponents(currSchemaDoc, redefineComp, SchemaSymbols.ELT_ATTRIBUTEGROUP, lName, targetLName);
                                }
                                else if (componentType.equals(SchemaSymbols.ELT_COMPLEXTYPE) || componentType.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                                    this.checkForDuplicateNames(qName, this.fUnparsedTypeRegistry, redefineComp, currSchemaDoc);
                                    final String targetLName = DOMUtil.getAttrValue(redefineComp, SchemaSymbols.ATT_NAME) + "_fn3dktizrknc9pi";
                                    if (componentType.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                                        this.renameRedefiningComponents(currSchemaDoc, redefineComp, SchemaSymbols.ELT_COMPLEXTYPE, lName, targetLName);
                                    }
                                    else {
                                        this.renameRedefiningComponents(currSchemaDoc, redefineComp, SchemaSymbols.ELT_SIMPLETYPE, lName, targetLName);
                                    }
                                }
                                else if (componentType.equals(SchemaSymbols.ELT_GROUP)) {
                                    this.checkForDuplicateNames(qName, this.fUnparsedGroupRegistry, redefineComp, currSchemaDoc);
                                    final String targetLName = DOMUtil.getAttrValue(redefineComp, SchemaSymbols.ATT_NAME) + "_fn3dktizrknc9pi";
                                    this.renameRedefiningComponents(currSchemaDoc, redefineComp, SchemaSymbols.ELT_GROUP, lName, targetLName);
                                }
                            }
                        }
                    }
                    else {
                        dependenciesCanOccur = false;
                        final String lName2 = DOMUtil.getAttrValue(globalComp, SchemaSymbols.ATT_NAME);
                        if (lName2.length() != 0) {
                            final String qName2 = (currSchemaDoc.fTargetNamespace == null) ? ("," + lName2) : (currSchemaDoc.fTargetNamespace + "," + lName2);
                            final String componentType2 = DOMUtil.getLocalName(globalComp);
                            if (componentType2.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
                                this.checkForDuplicateNames(qName2, this.fUnparsedAttributeRegistry, globalComp, currSchemaDoc);
                            }
                            else if (componentType2.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                                this.checkForDuplicateNames(qName2, this.fUnparsedAttributeGroupRegistry, globalComp, currSchemaDoc);
                            }
                            else if (componentType2.equals(SchemaSymbols.ELT_COMPLEXTYPE) || componentType2.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                                this.checkForDuplicateNames(qName2, this.fUnparsedTypeRegistry, globalComp, currSchemaDoc);
                            }
                            else if (componentType2.equals(SchemaSymbols.ELT_ELEMENT)) {
                                this.checkForDuplicateNames(qName2, this.fUnparsedElementRegistry, globalComp, currSchemaDoc);
                            }
                            else if (componentType2.equals(SchemaSymbols.ELT_GROUP)) {
                                this.checkForDuplicateNames(qName2, this.fUnparsedGroupRegistry, globalComp, currSchemaDoc);
                            }
                            else if (componentType2.equals(SchemaSymbols.ELT_NOTATION)) {
                                this.checkForDuplicateNames(qName2, this.fUnparsedNotationRegistry, globalComp, currSchemaDoc);
                            }
                        }
                    }
                }
            }
            DOMUtil.setHidden(currDoc);
            final Vector currSchemaDepends = this.fDependencyMap.get(currSchemaDoc);
            for (int i = 0; i < currSchemaDepends.size(); ++i) {
                schemasToProcess.push(currSchemaDepends.elementAt(i));
            }
        }
    }
    
    protected void traverseSchemas() {
        this.setSchemasVisible(this.fRoot);
        final Stack schemasToProcess = new Stack();
        schemasToProcess.push(this.fRoot);
        while (!schemasToProcess.empty()) {
            final XSDocumentInfo currSchemaDoc = schemasToProcess.pop();
            final Document currDoc = currSchemaDoc.fSchemaDoc;
            final SchemaGrammar currSG = this.fGrammarBucket.getGrammar(currSchemaDoc.fTargetNamespace);
            if (DOMUtil.isHidden(currDoc)) {
                continue;
            }
            final Element currRoot = DOMUtil.getRoot(currDoc);
            for (Element globalComp = DOMUtil.getFirstVisibleChildElement(currRoot); globalComp != null; globalComp = DOMUtil.getNextVisibleSiblingElement(globalComp)) {
                DOMUtil.setHidden(globalComp);
                final String componentType = DOMUtil.getLocalName(globalComp);
                if (DOMUtil.getLocalName(globalComp).equals(SchemaSymbols.ELT_REDEFINE)) {
                    currSchemaDoc.backupNSSupport(this.fRedefine2NSSupport.get(globalComp));
                    for (Element redefinedComp = DOMUtil.getFirstVisibleChildElement(globalComp); redefinedComp != null; redefinedComp = DOMUtil.getNextVisibleSiblingElement(redefinedComp)) {
                        final String redefinedComponentType = DOMUtil.getLocalName(redefinedComp);
                        DOMUtil.setHidden(redefinedComp);
                        if (redefinedComponentType.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                            this.fAttributeGroupTraverser.traverseGlobal(redefinedComp, currSchemaDoc, currSG);
                        }
                        else if (redefinedComponentType.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                            this.fComplexTypeTraverser.traverseGlobal(redefinedComp, currSchemaDoc, currSG);
                        }
                        else if (redefinedComponentType.equals(SchemaSymbols.ELT_GROUP)) {
                            this.fGroupTraverser.traverseGlobal(redefinedComp, currSchemaDoc, currSG);
                        }
                        else if (redefinedComponentType.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                            this.fSimpleTypeTraverser.traverseGlobal(redefinedComp, currSchemaDoc, currSG);
                        }
                        else if (redefinedComponentType.equals(SchemaSymbols.ELT_ANNOTATION)) {
                            this.fElementTraverser.traverseAnnotationDecl(redefinedComp, null, true, currSchemaDoc);
                        }
                        else {
                            this.reportSchemaError("src-redefine", new Object[] { componentType }, redefinedComp);
                        }
                    }
                    currSchemaDoc.restoreNSSupport();
                }
                else if (componentType.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
                    this.fAttributeTraverser.traverseGlobal(globalComp, currSchemaDoc, currSG);
                }
                else if (componentType.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                    this.fAttributeGroupTraverser.traverseGlobal(globalComp, currSchemaDoc, currSG);
                }
                else if (componentType.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                    this.fComplexTypeTraverser.traverseGlobal(globalComp, currSchemaDoc, currSG);
                }
                else if (componentType.equals(SchemaSymbols.ELT_ELEMENT)) {
                    this.fElementTraverser.traverseGlobal(globalComp, currSchemaDoc, currSG);
                }
                else if (componentType.equals(SchemaSymbols.ELT_GROUP)) {
                    this.fGroupTraverser.traverseGlobal(globalComp, currSchemaDoc, currSG);
                }
                else if (componentType.equals(SchemaSymbols.ELT_NOTATION)) {
                    this.fNotationTraverser.traverse(globalComp, currSchemaDoc, currSG);
                }
                else if (componentType.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                    this.fSimpleTypeTraverser.traverseGlobal(globalComp, currSchemaDoc, currSG);
                }
                else if (componentType.equals(SchemaSymbols.ELT_ANNOTATION)) {
                    this.fElementTraverser.traverseAnnotationDecl(globalComp, null, true, currSchemaDoc);
                }
                else {
                    this.reportSchemaError("sch-props-correct.1", new Object[] { DOMUtil.getLocalName(globalComp) }, globalComp);
                }
            }
            DOMUtil.setHidden(currDoc);
            final Vector currSchemaDepends = this.fDependencyMap.get(currSchemaDoc);
            for (int i = 0; i < currSchemaDepends.size(); ++i) {
                schemasToProcess.push(currSchemaDepends.elementAt(i));
            }
        }
    }
    
    private final boolean needReportTNSError(final String uri) {
        if (this.fReportedTNS == null) {
            this.fReportedTNS = new Vector();
        }
        else if (this.fReportedTNS.contains(uri)) {
            return false;
        }
        this.fReportedTNS.addElement(uri);
        return true;
    }
    
    protected Object getGlobalDecl(final XSDocumentInfo currSchema, final int declType, final QName declToTraverse, final Element elmNode) {
        if (declToTraverse.uri != null && declToTraverse.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA && declType == 7) {
            final Object retObj = SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(declToTraverse.localpart);
            if (retObj != null) {
                return retObj;
            }
        }
        if (!currSchema.isAllowedNS(declToTraverse.uri)) {
            if (currSchema.needReportTNSError(declToTraverse.uri)) {
                this.reportSchemaError("src-resolve.4", new Object[] { this.fDoc2SystemId.get(currSchema.fSchemaDoc), declToTraverse.uri }, elmNode);
            }
            return null;
        }
        final SchemaGrammar sGrammar = this.fGrammarBucket.getGrammar(declToTraverse.uri);
        if (sGrammar == null) {
            if (this.needReportTNSError(declToTraverse.uri)) {
                this.reportSchemaError("src-resolve", new Object[] { declToTraverse.rawname, XSDHandler.COMP_TYPE[declType] }, elmNode);
            }
            return null;
        }
        Object retObj2 = null;
        switch (declType) {
            case 1: {
                retObj2 = sGrammar.getGlobalAttributeDecl(declToTraverse.localpart);
                break;
            }
            case 2: {
                retObj2 = sGrammar.getGlobalAttributeGroupDecl(declToTraverse.localpart);
                break;
            }
            case 3: {
                retObj2 = sGrammar.getGlobalElementDecl(declToTraverse.localpart);
                break;
            }
            case 4: {
                retObj2 = sGrammar.getGlobalGroupDecl(declToTraverse.localpart);
                break;
            }
            case 5: {
                retObj2 = sGrammar.getIDConstraintDecl(declToTraverse.localpart);
                break;
            }
            case 6: {
                retObj2 = sGrammar.getGlobalNotationDecl(declToTraverse.localpart);
                break;
            }
            case 7: {
                retObj2 = sGrammar.getGlobalTypeDecl(declToTraverse.localpart);
                break;
            }
        }
        if (retObj2 != null) {
            return retObj2;
        }
        XSDocumentInfo schemaWithDecl = null;
        Element decl = null;
        final String declKey = (declToTraverse.uri == null) ? ("," + declToTraverse.localpart) : (declToTraverse.uri + "," + declToTraverse.localpart);
        switch (declType) {
            case 1: {
                decl = this.fUnparsedAttributeRegistry.get(declKey);
                break;
            }
            case 2: {
                decl = this.fUnparsedAttributeGroupRegistry.get(declKey);
                break;
            }
            case 3: {
                decl = this.fUnparsedElementRegistry.get(declKey);
                break;
            }
            case 4: {
                decl = this.fUnparsedGroupRegistry.get(declKey);
                break;
            }
            case 5: {
                decl = this.fUnparsedIdentityConstraintRegistry.get(declKey);
                break;
            }
            case 6: {
                decl = this.fUnparsedNotationRegistry.get(declKey);
                break;
            }
            case 7: {
                decl = this.fUnparsedTypeRegistry.get(declKey);
                break;
            }
            default: {
                this.reportSchemaError("Internal-Error", new Object[] { "XSDHandler asked to locate component of type " + declType + "; it does not recognize this type!" }, elmNode);
                break;
            }
        }
        if (decl == null) {
            this.reportSchemaError("src-resolve", new Object[] { declToTraverse.rawname, XSDHandler.COMP_TYPE[declType] }, elmNode);
            return null;
        }
        schemaWithDecl = this.findXSDocumentForDecl(currSchema, decl);
        if (schemaWithDecl == null) {
            this.reportSchemaError("src-resolve.4", new Object[] { this.fDoc2SystemId.get(currSchema.fSchemaDoc), declToTraverse.uri }, elmNode);
            return null;
        }
        if (DOMUtil.isHidden(decl)) {
            this.reportSchemaError("st-props-correct.2", new Object[] { declToTraverse.prefix + ":" + declToTraverse.localpart }, elmNode);
            return null;
        }
        DOMUtil.setHidden(decl);
        SchemaNamespaceSupport nsSupport = null;
        final Element parent = DOMUtil.getParent(decl);
        if (DOMUtil.getLocalName(parent).equals(SchemaSymbols.ELT_REDEFINE)) {
            nsSupport = this.fRedefine2NSSupport.get(parent);
        }
        schemaWithDecl.backupNSSupport(nsSupport);
        switch (declType) {
            case 1: {
                retObj2 = this.fAttributeTraverser.traverseGlobal(decl, schemaWithDecl, sGrammar);
                break;
            }
            case 2: {
                retObj2 = this.fAttributeGroupTraverser.traverseGlobal(decl, schemaWithDecl, sGrammar);
                break;
            }
            case 3: {
                retObj2 = this.fElementTraverser.traverseGlobal(decl, schemaWithDecl, sGrammar);
                break;
            }
            case 4: {
                retObj2 = this.fGroupTraverser.traverseGlobal(decl, schemaWithDecl, sGrammar);
                break;
            }
            case 5: {
                retObj2 = null;
                break;
            }
            case 6: {
                retObj2 = this.fNotationTraverser.traverse(decl, schemaWithDecl, sGrammar);
                break;
            }
            case 7: {
                if (DOMUtil.getLocalName(decl).equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                    retObj2 = this.fComplexTypeTraverser.traverseGlobal(decl, schemaWithDecl, sGrammar);
                    break;
                }
                retObj2 = this.fSimpleTypeTraverser.traverseGlobal(decl, schemaWithDecl, sGrammar);
                break;
            }
        }
        schemaWithDecl.restoreNSSupport();
        return retObj2;
    }
    
    Object getGrpOrAttrGrpRedefinedByRestriction(final int type, final QName name, final XSDocumentInfo currSchema, final Element elmNode) {
        final String realName = (name.uri != null) ? (name.uri + "," + name.localpart) : ("," + name.localpart);
        String nameToFind = null;
        switch (type) {
            case 2: {
                nameToFind = this.fRedefinedRestrictedAttributeGroupRegistry.get(realName);
                break;
            }
            case 4: {
                nameToFind = this.fRedefinedRestrictedGroupRegistry.get(realName);
                break;
            }
            default: {
                return null;
            }
        }
        if (nameToFind == null) {
            return null;
        }
        final int commaPos = nameToFind.indexOf(",");
        final QName qNameToFind = new QName(XMLSymbols.EMPTY_STRING, nameToFind.substring(commaPos + 1), nameToFind.substring(commaPos), (commaPos == 0) ? null : nameToFind.substring(0, commaPos));
        final Object retObj = this.getGlobalDecl(currSchema, type, qNameToFind, elmNode);
        if (retObj == null) {
            switch (type) {
                case 2: {
                    this.reportSchemaError("src-redefine.7.2.1", new Object[] { name.localpart }, elmNode);
                    break;
                }
                case 4: {
                    this.reportSchemaError("src-redefine.6.2.1", new Object[] { name.localpart }, elmNode);
                    break;
                }
            }
            return null;
        }
        return retObj;
    }
    
    protected void resolveKeyRefs() {
        for (int i = 0; i < this.fKeyrefStackPos; ++i) {
            final Document keyrefDoc = DOMUtil.getDocument(this.fKeyrefs[i]);
            final XSDocumentInfo keyrefSchemaDoc = this.fDoc2XSDocumentMap.get(keyrefDoc);
            keyrefSchemaDoc.fNamespaceSupport.makeGlobal();
            keyrefSchemaDoc.fNamespaceSupport.setEffectiveContext(this.fKeyrefNamespaceContext[i]);
            final SchemaGrammar keyrefGrammar = this.fGrammarBucket.getGrammar(keyrefSchemaDoc.fTargetNamespace);
            DOMUtil.setHidden(this.fKeyrefs[i]);
            this.fKeyrefTraverser.traverse(this.fKeyrefs[i], this.fKeyrefElems[i], keyrefSchemaDoc, keyrefGrammar);
        }
    }
    
    protected Hashtable getIDRegistry() {
        return this.fUnparsedIdentityConstraintRegistry;
    }
    
    protected void storeKeyRef(final Element keyrefToStore, final XSDocumentInfo schemaDoc, final XSElementDecl currElemDecl) {
        final String keyrefName = DOMUtil.getAttrValue(keyrefToStore, SchemaSymbols.ATT_NAME);
        if (keyrefName.length() != 0) {
            final String keyrefQName = (schemaDoc.fTargetNamespace == null) ? ("," + keyrefName) : (schemaDoc.fTargetNamespace + "," + keyrefName);
            this.checkForDuplicateNames(keyrefQName, this.fUnparsedIdentityConstraintRegistry, keyrefToStore, schemaDoc);
        }
        if (this.fKeyrefStackPos == this.fKeyrefs.length) {
            final Element[] elemArray = new Element[this.fKeyrefStackPos + 2];
            System.arraycopy(this.fKeyrefs, 0, elemArray, 0, this.fKeyrefStackPos);
            this.fKeyrefs = elemArray;
            final XSElementDecl[] declArray = new XSElementDecl[this.fKeyrefStackPos + 2];
            System.arraycopy(this.fKeyrefElems, 0, declArray, 0, this.fKeyrefStackPos);
            this.fKeyrefElems = declArray;
            final String[][] stringArray = new String[this.fKeyrefStackPos + 2][];
            System.arraycopy(this.fKeyrefNamespaceContext, 0, stringArray, 0, this.fKeyrefStackPos);
            this.fKeyrefNamespaceContext = stringArray;
        }
        this.fKeyrefs[this.fKeyrefStackPos] = keyrefToStore;
        this.fKeyrefElems[this.fKeyrefStackPos] = currElemDecl;
        this.fKeyrefNamespaceContext[this.fKeyrefStackPos++] = schemaDoc.fNamespaceSupport.getEffectiveLocalContext();
    }
    
    private Document getSchema(final XSDDescription desc, final boolean mustResolve, final Element referElement) {
        XMLInputSource schemaSource = null;
        try {
            schemaSource = XMLSchemaLoader.resolveDocument(desc, this.fLocationPairs, this.fEntityResolver);
        }
        catch (IOException ex) {
            if (mustResolve) {
                this.reportSchemaError(XSDHandler.DOC_ERROR_CODES[desc.getContextType()], new Object[] { desc.getLocationHints()[0] }, referElement);
            }
            else {
                this.reportSchemaWarning(XSDHandler.DOC_ERROR_CODES[desc.getContextType()], new Object[] { desc.getLocationHints()[0] }, referElement);
            }
        }
        return this.getSchema(desc.getTargetNamespace(), schemaSource, mustResolve, desc.getContextType(), referElement);
    }
    
    private Document getSchema(final String schemaNamespace, final XMLInputSource schemaSource, final boolean mustResolve, final short referType, final Element referElement) {
        boolean hasInput = true;
        Document schemaDoc = null;
        try {
            if (schemaSource != null && (schemaSource.getSystemId() != null || schemaSource.getByteStream() != null || schemaSource.getCharacterStream() != null)) {
                final String schemaId = XMLEntityManager.expandSystemId(schemaSource.getSystemId(), schemaSource.getBaseSystemId());
                final XSDKey key = new XSDKey(schemaId, referType, schemaNamespace);
                if ((schemaDoc = this.fTraversed.get(key)) != null) {
                    this.fLastSchemaWasDuplicate = true;
                    return schemaDoc;
                }
                if (this.fSchemaParser == null) {
                    this.fSchemaParser = new DOMParser();
                    this.resetSchemaParserErrorHandler();
                    this.fSchemaParser.setPool(this.fDOMPool);
                }
                this.fSchemaParser.reset();
                this.fSchemaParser.parse(schemaSource);
                schemaDoc = this.fSchemaParser.getDocument();
                this.fTraversed.put(key, schemaDoc);
                if (schemaId != null) {
                    this.fDoc2SystemId.put(schemaDoc, schemaId);
                }
                this.fLastSchemaWasDuplicate = false;
                return schemaDoc;
            }
            else {
                hasInput = false;
            }
        }
        catch (IOException ex) {}
        if (mustResolve) {
            this.reportSchemaError(XSDHandler.DOC_ERROR_CODES[referType], new Object[] { schemaSource.getSystemId() }, referElement);
        }
        else if (hasInput) {
            this.reportSchemaWarning(XSDHandler.DOC_ERROR_CODES[referType], new Object[] { schemaSource.getSystemId() }, referElement);
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
            this.fLocalElemNamespaceContext[i] = null;
        }
        this.fLocalElemStackPos = 0;
        for (int j = 0; j < this.fKeyrefStackPos; ++j) {
            this.fKeyrefs[j] = null;
            this.fKeyrefElems[j] = null;
            this.fKeyrefNamespaceContext[j] = null;
        }
        this.fKeyrefStackPos = 0;
        if (this.fAttributeChecker == null) {
            this.createTraversers();
        }
        this.fAttributeChecker.reset(this.fSymbolTable);
        this.fAttributeGroupTraverser.reset(this.fSymbolTable);
        this.fAttributeTraverser.reset(this.fSymbolTable);
        this.fComplexTypeTraverser.reset(this.fSymbolTable);
        this.fElementTraverser.reset(this.fSymbolTable);
        this.fGroupTraverser.reset(this.fSymbolTable);
        this.fKeyrefTraverser.reset(this.fSymbolTable);
        this.fNotationTraverser.reset(this.fSymbolTable);
        this.fSimpleTypeTraverser.reset(this.fSymbolTable);
        this.fUniqueOrKeyTraverser.reset(this.fSymbolTable);
        this.fWildCardTraverser.reset(this.fSymbolTable);
        this.fRedefinedRestrictedAttributeGroupRegistry.clear();
        this.fRedefinedRestrictedGroupRegistry.clear();
    }
    
    public void setDeclPool(final XSDeclarationPool declPool) {
        this.fDeclPool = declPool;
    }
    
    public void reset(final XMLErrorReporter errorReporter, final XMLEntityResolver entityResolver, final SymbolTable symbolTable, final XMLGrammarPool grammarPool, final boolean allowJavaEncodings) {
        this.fErrorReporter = errorReporter;
        this.fEntityResolver = entityResolver;
        this.fSymbolTable = symbolTable;
        this.fGrammarPool = grammarPool;
        this.fAllowJavaEncodings = allowJavaEncodings;
        this.resetSchemaParserErrorHandler();
    }
    
    void resetSchemaParserErrorHandler() {
        if (this.fSchemaParser != null) {
            try {
                final XMLErrorHandler currErrorHandler = this.fErrorReporter.getErrorHandler();
                if (currErrorHandler != this.fSchemaParser.getProperty("http://apache.org/xml/properties/internal/error-handler")) {
                    this.fSchemaParser.setProperty("http://apache.org/xml/properties/internal/error-handler", currErrorHandler);
                }
            }
            catch (Exception ex) {}
            try {
                this.fSchemaParser.setFeature("http://apache.org/xml/features/continue-after-fatal-error", this.fErrorReporter.getFeature("http://apache.org/xml/features/continue-after-fatal-error"));
            }
            catch (Exception ex2) {}
            try {
                this.fSchemaParser.setFeature("http://apache.org/xml/features/allow-java-encodings", this.fAllowJavaEncodings);
            }
            catch (Exception ex3) {}
            try {
                if (this.fEntityResolver != this.fSchemaParser.getProperty("http://apache.org/xml/properties/internal/entity-resolver")) {
                    this.fSchemaParser.setProperty("http://apache.org/xml/properties/internal/entity-resolver", this.fEntityResolver);
                }
            }
            catch (Exception ex4) {}
        }
    }
    
    void traverseLocalElements() {
        this.fElementTraverser.fDeferTraversingLocalElements = false;
        for (int i = 0; i < this.fLocalElemStackPos; ++i) {
            final Element currElem = this.fLocalElementDecl[i];
            final XSDocumentInfo currSchema = this.fDoc2XSDocumentMap.get(DOMUtil.getDocument(currElem));
            final SchemaGrammar currGrammar = this.fGrammarBucket.getGrammar(currSchema.fTargetNamespace);
            this.fElementTraverser.traverseLocal(this.fParticle[i], currElem, currSchema, currGrammar, this.fAllContext[i], this.fEnclosingCT[i]);
        }
    }
    
    void fillInLocalElemInfo(final Element elmDecl, final XSDocumentInfo schemaDoc, final int allContextFlags, final XSComplexTypeDecl enclosingCT, final XSParticleDecl particle) {
        if (this.fParticle.length == this.fLocalElemStackPos) {
            final XSParticleDecl[] newStackP = new XSParticleDecl[this.fLocalElemStackPos + 10];
            System.arraycopy(this.fParticle, 0, newStackP, 0, this.fLocalElemStackPos);
            this.fParticle = newStackP;
            final Element[] newStackE = new Element[this.fLocalElemStackPos + 10];
            System.arraycopy(this.fLocalElementDecl, 0, newStackE, 0, this.fLocalElemStackPos);
            this.fLocalElementDecl = newStackE;
            final int[] newStackI = new int[this.fLocalElemStackPos + 10];
            System.arraycopy(this.fAllContext, 0, newStackI, 0, this.fLocalElemStackPos);
            this.fAllContext = newStackI;
            final XSComplexTypeDecl[] newStackC = new XSComplexTypeDecl[this.fLocalElemStackPos + 10];
            System.arraycopy(this.fEnclosingCT, 0, newStackC, 0, this.fLocalElemStackPos);
            this.fEnclosingCT = newStackC;
            final String[][] newStackN = new String[this.fLocalElemStackPos + 10][];
            System.arraycopy(this.fLocalElemNamespaceContext, 0, newStackN, 0, this.fLocalElemStackPos);
            this.fLocalElemNamespaceContext = newStackN;
        }
        this.fParticle[this.fLocalElemStackPos] = particle;
        this.fLocalElementDecl[this.fLocalElemStackPos] = elmDecl;
        this.fAllContext[this.fLocalElemStackPos] = allContextFlags;
        this.fEnclosingCT[this.fLocalElemStackPos] = enclosingCT;
        this.fLocalElemNamespaceContext[this.fLocalElemStackPos++] = schemaDoc.fNamespaceSupport.getEffectiveLocalContext();
    }
    
    void checkForDuplicateNames(final String qName, final Hashtable registry, final Element currComp, final XSDocumentInfo currSchema) {
        Object objElem = null;
        if ((objElem = registry.get(qName)) == null) {
            registry.put(qName, currComp);
        }
        else {
            final Element collidingElem = (Element)objElem;
            if (collidingElem == currComp) {
                return;
            }
            Element elemParent = null;
            XSDocumentInfo redefinedSchema = null;
            boolean collidedWithRedefine = true;
            if (DOMUtil.getLocalName(elemParent = DOMUtil.getParent(collidingElem)).equals(SchemaSymbols.ELT_REDEFINE)) {
                redefinedSchema = this.fRedefine2XSDMap.get(elemParent);
            }
            else if (DOMUtil.getLocalName(DOMUtil.getParent(currComp)).equals(SchemaSymbols.ELT_REDEFINE)) {
                redefinedSchema = this.fDoc2XSDocumentMap.get(DOMUtil.getDocument(collidingElem));
                collidedWithRedefine = false;
            }
            if (redefinedSchema != null) {
                final String newName = qName.substring(qName.lastIndexOf(44) + 1) + "_fn3dktizrknc9pi";
                if (redefinedSchema == currSchema) {
                    currComp.setAttribute(SchemaSymbols.ATT_NAME, newName);
                    if (currSchema.fTargetNamespace == null) {
                        registry.put("," + newName, currComp);
                    }
                    else {
                        registry.put(currSchema.fTargetNamespace + "," + newName, currComp);
                    }
                    if (currSchema.fTargetNamespace == null) {
                        this.checkForDuplicateNames("," + newName, registry, currComp, currSchema);
                    }
                    else {
                        this.checkForDuplicateNames(currSchema.fTargetNamespace + "," + newName, registry, currComp, currSchema);
                    }
                }
                else if (collidedWithRedefine) {
                    if (currSchema.fTargetNamespace == null) {
                        this.checkForDuplicateNames("," + newName, registry, currComp, currSchema);
                    }
                    else {
                        this.checkForDuplicateNames(currSchema.fTargetNamespace + "," + newName, registry, currComp, currSchema);
                    }
                }
                else {
                    this.reportSchemaError("src-redefine.1", new Object[] { qName }, currComp);
                }
            }
            else {
                this.reportSchemaError("sch-props-correct.2", new Object[] { qName }, currComp);
            }
        }
    }
    
    private void renameRedefiningComponents(final XSDocumentInfo currSchema, final Element child, final String componentType, final String oldName, final String newName) {
        final SchemaNamespaceSupport currNSMap = currSchema.fNamespaceSupport;
        if (componentType.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
            Element grandKid = DOMUtil.getFirstChildElement(child);
            if (grandKid == null) {
                this.reportSchemaError("src-redefine.5", null, child);
            }
            else {
                String grandKidName = grandKid.getLocalName();
                if (grandKidName.equals(SchemaSymbols.ELT_ANNOTATION)) {
                    grandKid = DOMUtil.getNextSiblingElement(grandKid);
                    grandKidName = grandKid.getLocalName();
                }
                if (grandKid == null) {
                    this.reportSchemaError("src-redefine.5", null, child);
                }
                else if (!grandKidName.equals(SchemaSymbols.ELT_RESTRICTION)) {
                    this.reportSchemaError("src-redefine.5", null, child);
                }
                else {
                    final Object[] attrs = this.fAttributeChecker.checkAttributes(grandKid, false, currSchema);
                    final QName derivedBase = (QName)attrs[XSAttributeChecker.ATTIDX_BASE];
                    if (derivedBase == null || derivedBase.uri != currSchema.fTargetNamespace || !derivedBase.localpart.equals(oldName)) {
                        this.reportSchemaError("src-redefine.5", null, child);
                    }
                    else if (derivedBase.prefix != null && derivedBase.prefix.length() > 0) {
                        grandKid.setAttribute(SchemaSymbols.ATT_BASE, derivedBase.prefix + ":" + newName);
                    }
                    else {
                        grandKid.setAttribute(SchemaSymbols.ATT_BASE, newName);
                    }
                    this.fAttributeChecker.returnAttrArray(attrs, currSchema);
                }
            }
        }
        else if (componentType.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
            Element grandKid = DOMUtil.getFirstChildElement(child);
            if (grandKid == null) {
                this.reportSchemaError("src-redefine.5", null, child);
            }
            else {
                if (grandKid.getLocalName().equals(SchemaSymbols.ELT_ANNOTATION)) {
                    grandKid = DOMUtil.getNextSiblingElement(grandKid);
                }
                if (grandKid == null) {
                    this.reportSchemaError("src-redefine.5", null, child);
                }
                else {
                    Element greatGrandKid = DOMUtil.getFirstChildElement(grandKid);
                    if (greatGrandKid == null) {
                        this.reportSchemaError("src-redefine.5", null, grandKid);
                    }
                    else {
                        String greatGrandKidName = greatGrandKid.getLocalName();
                        if (greatGrandKidName.equals(SchemaSymbols.ELT_ANNOTATION)) {
                            greatGrandKid = DOMUtil.getNextSiblingElement(greatGrandKid);
                            greatGrandKidName = greatGrandKid.getLocalName();
                        }
                        if (greatGrandKid == null) {
                            this.reportSchemaError("src-redefine.5", null, grandKid);
                        }
                        else if (!greatGrandKidName.equals(SchemaSymbols.ELT_RESTRICTION) && !greatGrandKidName.equals(SchemaSymbols.ELT_EXTENSION)) {
                            this.reportSchemaError("src-redefine.5", null, greatGrandKid);
                        }
                        else {
                            final Object[] attrs2 = this.fAttributeChecker.checkAttributes(greatGrandKid, false, currSchema);
                            final QName derivedBase2 = (QName)attrs2[XSAttributeChecker.ATTIDX_BASE];
                            if (derivedBase2 == null || derivedBase2.uri != currSchema.fTargetNamespace || !derivedBase2.localpart.equals(oldName)) {
                                this.reportSchemaError("src-redefine.5", null, greatGrandKid);
                            }
                            else if (derivedBase2.prefix != null && derivedBase2.prefix.length() > 0) {
                                greatGrandKid.setAttribute(SchemaSymbols.ATT_BASE, derivedBase2.prefix + ":" + newName);
                            }
                            else {
                                greatGrandKid.setAttribute(SchemaSymbols.ATT_BASE, newName);
                            }
                        }
                    }
                }
            }
        }
        else if (componentType.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
            final String processedBaseName = (currSchema.fTargetNamespace == null) ? ("," + oldName) : (currSchema.fTargetNamespace + "," + oldName);
            final int attGroupRefsCount = this.changeRedefineGroup(processedBaseName, componentType, newName, child, currSchema);
            if (attGroupRefsCount > 1) {
                this.reportSchemaError("src-redefine.7.1", new Object[] { new Integer(attGroupRefsCount) }, child);
            }
            else if (attGroupRefsCount != 1) {
                if (currSchema.fTargetNamespace == null) {
                    this.fRedefinedRestrictedAttributeGroupRegistry.put(processedBaseName, "," + newName);
                }
                else {
                    this.fRedefinedRestrictedAttributeGroupRegistry.put(processedBaseName, currSchema.fTargetNamespace + "," + newName);
                }
            }
        }
        else if (componentType.equals(SchemaSymbols.ELT_GROUP)) {
            final String processedBaseName = (currSchema.fTargetNamespace == null) ? ("," + oldName) : (currSchema.fTargetNamespace + "," + oldName);
            final int groupRefsCount = this.changeRedefineGroup(processedBaseName, componentType, newName, child, currSchema);
            if (groupRefsCount > 1) {
                this.reportSchemaError("src-redefine.6.1.1", new Object[] { new Integer(groupRefsCount) }, child);
            }
            else if (groupRefsCount != 1) {
                if (currSchema.fTargetNamespace == null) {
                    this.fRedefinedRestrictedGroupRegistry.put(processedBaseName, "," + newName);
                }
                else {
                    this.fRedefinedRestrictedGroupRegistry.put(processedBaseName, currSchema.fTargetNamespace + "," + newName);
                }
            }
        }
        else {
            this.reportSchemaError("Internal-Error", new Object[] { "could not handle this particular <redefine>; please submit your schemas and instance document in a bug report!" }, child);
        }
    }
    
    private String findQName(final String name, final XSDocumentInfo schemaDoc) {
        final SchemaNamespaceSupport currNSMap = schemaDoc.fNamespaceSupport;
        final int colonPtr = name.indexOf(58);
        String prefix = XMLSymbols.EMPTY_STRING;
        if (colonPtr > 0) {
            prefix = name.substring(0, colonPtr);
        }
        String uri = currNSMap.getURI(this.fSymbolTable.addSymbol(prefix));
        final String localpart = (colonPtr == 0) ? name : name.substring(colonPtr + 1);
        if (prefix == XMLSymbols.EMPTY_STRING && uri == null && schemaDoc.fIsChameleonSchema) {
            uri = schemaDoc.fTargetNamespace;
        }
        if (uri == null) {
            return "," + localpart;
        }
        return uri + "," + localpart;
    }
    
    private int changeRedefineGroup(final String originalQName, final String elementSought, final String newName, final Element curr, final XSDocumentInfo schemaDoc) {
        final SchemaNamespaceSupport currNSMap = schemaDoc.fNamespaceSupport;
        int result = 0;
        for (Element child = DOMUtil.getFirstChildElement(curr); child != null; child = DOMUtil.getNextSiblingElement(child)) {
            final String name = child.getLocalName();
            if (!name.equals(elementSought)) {
                result += this.changeRedefineGroup(originalQName, elementSought, newName, child, schemaDoc);
            }
            else {
                final String ref = child.getAttribute(SchemaSymbols.ATT_REF);
                if (ref.length() != 0) {
                    final String processedRef = this.findQName(ref, schemaDoc);
                    if (originalQName.equals(processedRef)) {
                        String prefix = XMLSymbols.EMPTY_STRING;
                        final String localpart = ref;
                        final int colonptr = ref.indexOf(":");
                        if (colonptr > 0) {
                            prefix = ref.substring(0, colonptr);
                            child.setAttribute(SchemaSymbols.ATT_REF, prefix + ":" + newName);
                        }
                        else {
                            child.setAttribute(SchemaSymbols.ATT_REF, newName);
                        }
                        ++result;
                        if (elementSought.equals(SchemaSymbols.ELT_GROUP)) {
                            final String minOccurs = child.getAttribute(SchemaSymbols.ATT_MINOCCURS);
                            final String maxOccurs = child.getAttribute(SchemaSymbols.ATT_MAXOCCURS);
                            if ((maxOccurs.length() != 0 && !maxOccurs.equals("1")) || (minOccurs.length() != 0 && !minOccurs.equals("1"))) {
                                this.reportSchemaError("src-redefine.6.1.2", new Object[] { ref }, child);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
    
    private XSDocumentInfo findXSDocumentForDecl(final XSDocumentInfo currSchema, final Element decl) {
        final Document declDoc = DOMUtil.getDocument(decl);
        final Object temp = this.fDoc2XSDocumentMap.get(declDoc);
        if (temp == null) {
            return null;
        }
        final XSDocumentInfo declDocInfo = (XSDocumentInfo)temp;
        return declDocInfo;
    }
    
    private boolean nonAnnotationContent(final Element elem) {
        for (Element child = DOMUtil.getFirstChildElement(elem); child != null; child = DOMUtil.getNextSiblingElement(child)) {
            if (!DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
                return true;
            }
        }
        return false;
    }
    
    private void setSchemasVisible(final XSDocumentInfo startSchema) {
        if (DOMUtil.isHidden(startSchema.fSchemaDoc)) {
            DOMUtil.setVisible(startSchema.fSchemaDoc);
            final Vector dependingSchemas = this.fDependencyMap.get(startSchema);
            for (int i = 0; i < dependingSchemas.size(); ++i) {
                this.setSchemasVisible(dependingSchemas.elementAt(i));
            }
        }
    }
    
    public SimpleLocator element2Locator(final Element e) {
        if (!(e instanceof ElementNSImpl)) {
            return null;
        }
        final SimpleLocator l = new SimpleLocator();
        return this.element2Locator(e, l) ? l : null;
    }
    
    public boolean element2Locator(final Element e, final SimpleLocator l) {
        if (!(e instanceof ElementNSImpl) || l == null) {
            return false;
        }
        final ElementNSImpl ele = (ElementNSImpl)e;
        final Document doc = ele.getOwnerDocument();
        final String sid = this.fDoc2SystemId.get(doc);
        final int line = ele.getLineNumber();
        final int column = ele.getColumnNumber();
        l.setValues(sid, sid, line, column);
        return true;
    }
    
    void reportSchemaError(final String key, final Object[] args, final Element ele) {
        if (this.element2Locator(ele, this.xl)) {
            this.fErrorReporter.reportError(this.xl, "http://www.w3.org/TR/xml-schema-1", key, args, (short)1);
        }
        else {
            this.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", key, args, (short)1);
        }
    }
    
    void reportSchemaWarning(final String key, final Object[] args, final Element ele) {
        if (this.element2Locator(ele, this.xl)) {
            this.fErrorReporter.reportError(this.xl, "http://www.w3.org/TR/xml-schema-1", key, args, (short)0);
        }
        else {
            this.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", key, args, (short)0);
        }
    }
    
    static {
        NS_ERROR_CODES = new String[][] { { "src-include.2.1", "src-include.2.1" }, { "src-redefine.3.1", "src-redefine.3.1" }, { "src-import.3.1", "src-import.3.2" }, null, { "TargetNamespace.1", "TargetNamespace.2" }, { "TargetNamespace.1", "TargetNamespace.2" }, { "TargetNamespace.1", "TargetNamespace.2" }, { "TargetNamespace.1", "TargetNamespace.2" } };
        ELE_ERROR_CODES = new String[] { "src-include.1", "src-redefine.2", "src-import.2", "schema_reference.4", "schema_reference.4", "schema_reference.4", "schema_reference.4", "schema_reference.4" };
        COMP_TYPE = new String[] { null, "attribute declaration", "attribute group", "element declaration", "group", "identity constraint", "notation", "type definition" };
        DOC_ERROR_CODES = new String[] { "src-include.0", "src-redefine.0", "src-import.0", "schema_reference.4", "schema_reference.4", "schema_reference.4", "schema_reference.4", "schema_reference.4" };
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
        
        public boolean equals(final Object obj) {
            if (!(obj instanceof XSDKey)) {
                return false;
            }
            final XSDKey key = (XSDKey)obj;
            return ((this.referType != 1 && key.referType != 1) || this.referType == key.referType) && this.referNS == key.referNS && (this.systemId != null || key.systemId == null) && (this.systemId == null || this.systemId.equals(key.systemId));
        }
    }
}
