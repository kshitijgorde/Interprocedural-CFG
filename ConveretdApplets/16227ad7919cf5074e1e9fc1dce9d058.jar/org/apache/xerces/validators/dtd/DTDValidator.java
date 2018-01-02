// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.dtd;

import java.util.Enumeration;
import java.util.StringTokenizer;
import org.apache.xerces.utils.XMLCharacterProperties;
import org.apache.xerces.framework.XMLContentSpecNode;
import org.xml.sax.Locator;
import org.apache.xerces.framework.XMLAttrList;
import org.apache.xerces.utils.ChunkyCharArray;
import java.util.Hashtable;
import org.apache.xerces.readers.XMLEntityHandler;
import org.apache.xerces.framework.XMLErrorReporter;
import org.apache.xerces.utils.StringPool;
import org.apache.xerces.utils.NamespacesScope;
import org.apache.xerces.framework.XMLDTDScanner;
import org.apache.xerces.framework.XMLValidator;

public final class DTDValidator implements XMLValidator, XMLDTDScanner.EventHandler, NamespacesHandler
{
    private static final boolean PRINT_EXCEPTION_STACK_TRACE = false;
    private static final boolean DEBUG_PRINT_ATTRIBUTES = false;
    private static final boolean DEBUG_PRINT_CONTENT = false;
    private XMLDTDScanner fDTDScanner;
    protected StringPool fStringPool;
    private XMLErrorReporter fErrorReporter;
    private XMLEntityHandler fEntityHandler;
    protected boolean fValidating;
    private boolean fValidationEnabled;
    private boolean fDynamicValidation;
    private boolean fValidationEnabledByDynamic;
    private boolean fDynamicDisabledByValidation;
    private boolean fWarningOnDuplicateAttDef;
    private boolean fWarningOnUndeclaredElements;
    private EntityPool fEntityPool;
    private int fStandaloneReader;
    private int[] fElementTypeStack;
    private int[] fElementIndexStack;
    private int[] fContentSpecTypeStack;
    private int[] fElementChildCount;
    private int[][] fElementChildren;
    private int fElementDepth;
    private boolean fNamespacesEnabled;
    private NamespacesScope fNamespacesScope;
    private int fNamespacesPrefix;
    private int fRootElementType;
    private int fAttrIndex;
    private int fElementDeclCount;
    private int fAttlistDeclCount;
    private int fCurrentElementType;
    private int fCurrentElementIndex;
    private int fCurrentContentSpecType;
    private boolean fSeenDoctypeDecl;
    private EventHandler fEventHandler;
    private EntityPool fParameterEntityPool;
    private int fEMPTYSymbol;
    private int fANYSymbol;
    private int fMIXEDSymbol;
    private int fCHILDRENSymbol;
    private int fCDATASymbol;
    private int fIDSymbol;
    private int fIDREFSymbol;
    private int fIDREFSSymbol;
    private int fENTITYSymbol;
    private int fENTITIESSymbol;
    private int fNMTOKENSymbol;
    private int fNMTOKENSSymbol;
    private int fNOTATIONSymbol;
    private int fENUMERATIONSymbol;
    private int fREQUIREDSymbol;
    private int fFIXEDSymbol;
    private int fEpsilonIndex;
    private int fLeafCount;
    private int fCount;
    private int[] fContentList;
    private static final int CHUNK_SHIFT = 8;
    private static final int CHUNK_SIZE = 256;
    private static final int CHUNK_MASK = 255;
    private static final int INITIAL_CHUNK_COUNT = 4;
    private int fElementCount;
    private int[][] fElementType;
    private byte[][] fElementDeclIsExternal;
    private int[][] fContentSpecType;
    private int[][] fContentSpec;
    private XMLContentModel[][] fContentModel;
    private int[][] fAttlistHead;
    private int[][] fAttlistTail;
    private int fNodeCount;
    private byte[][] fNodeType;
    private int[][] fNodeValue;
    private int fAttDefCount;
    private int[][] fAttName;
    private int[][] fAttType;
    private AttributeValidator[][] fAttValidator;
    private int[][] fEnumeration;
    private int[][] fAttDefaultType;
    private int[][] fAttValue;
    private byte[][] fAttDefIsExternal;
    private int[][] fNextAttDef;
    private Hashtable fIdDefs;
    private Hashtable fIdRefs;
    private Object fNullValue;
    private AttributeValidator fAttValidatorCDATA;
    private AttributeValidator fAttValidatorID;
    private AttributeValidator fAttValidatorIDREF;
    private AttributeValidator fAttValidatorIDREFS;
    private AttributeValidator fAttValidatorENTITY;
    private AttributeValidator fAttValidatorENTITIES;
    private AttributeValidator fAttValidatorNMTOKEN;
    private AttributeValidator fAttValidatorNMTOKENS;
    private AttributeValidator fAttValidatorNOTATION;
    private AttributeValidator fAttValidatorENUMERATION;
    ContentSpecImpl fContentSpecImpl;
    
    public DTDValidator(final EventHandler fEventHandler, final StringPool fStringPool, final XMLErrorReporter fErrorReporter, final XMLEntityHandler fEntityHandler) {
        this.fValidating = false;
        this.fValidationEnabled = false;
        this.fDynamicValidation = false;
        this.fValidationEnabledByDynamic = false;
        this.fDynamicDisabledByValidation = false;
        this.fWarningOnDuplicateAttDef = false;
        this.fWarningOnUndeclaredElements = false;
        this.fStandaloneReader = -1;
        this.fElementTypeStack = new int[8];
        this.fElementIndexStack = new int[8];
        this.fContentSpecTypeStack = new int[8];
        this.fElementChildCount = new int[8];
        this.fElementChildren = new int[8][];
        this.fElementDepth = -1;
        this.fNamespacesEnabled = false;
        this.fNamespacesPrefix = -1;
        this.fRootElementType = -1;
        this.fAttrIndex = -1;
        this.fCurrentElementType = -1;
        this.fCurrentElementIndex = -1;
        this.fCurrentContentSpecType = -1;
        this.fSeenDoctypeDecl = false;
        this.fEMPTYSymbol = -1;
        this.fANYSymbol = -1;
        this.fMIXEDSymbol = -1;
        this.fCHILDRENSymbol = -1;
        this.fCDATASymbol = -1;
        this.fIDSymbol = -1;
        this.fIDREFSymbol = -1;
        this.fIDREFSSymbol = -1;
        this.fENTITYSymbol = -1;
        this.fENTITIESSymbol = -1;
        this.fNMTOKENSymbol = -1;
        this.fNMTOKENSSymbol = -1;
        this.fNOTATIONSymbol = -1;
        this.fENUMERATIONSymbol = -1;
        this.fREQUIREDSymbol = -1;
        this.fFIXEDSymbol = -1;
        this.fEpsilonIndex = -1;
        this.fContentList = new int[64];
        this.fElementType = new int[4][];
        this.fElementDeclIsExternal = new byte[4][];
        this.fContentSpecType = new int[4][];
        this.fContentSpec = new int[4][];
        this.fContentModel = new XMLContentModel[4][];
        this.fAttlistHead = new int[4][];
        this.fAttlistTail = new int[4][];
        this.fNodeType = new byte[4][];
        this.fNodeValue = new int[4][];
        this.fAttName = new int[4][];
        this.fAttType = new int[4][];
        this.fAttValidator = new AttributeValidator[4][];
        this.fEnumeration = new int[4][];
        this.fAttDefaultType = new int[4][];
        this.fAttValue = new int[4][];
        this.fAttDefIsExternal = new byte[4][];
        this.fNextAttDef = new int[4][];
        this.fEventHandler = fEventHandler;
        this.fStringPool = fStringPool;
        this.fErrorReporter = fErrorReporter;
        this.fEntityHandler = fEntityHandler;
        this.fDTDScanner = new XMLDTDScanner((XMLDTDScanner.EventHandler)this, this.fStringPool, this.fErrorReporter, this.fEntityHandler, new ChunkyCharArray(this.fStringPool));
        this.fEntityPool = new EntityPool(this.fStringPool, this.fErrorReporter, true);
        this.init();
    }
    
    public void reset(final StringPool fStringPool) throws Exception {
        this.fStringPool = fStringPool;
        this.fDTDScanner.reset(fStringPool, new ChunkyCharArray(this.fStringPool));
        this.setValidating(this.fValidationEnabled);
        this.fValidationEnabledByDynamic = false;
        this.fDynamicDisabledByValidation = false;
        this.fEntityPool.reset(this.fStringPool);
        this.poolReset();
        this.fStandaloneReader = -1;
        this.fElementDepth = -1;
        this.fSeenDoctypeDecl = false;
        this.fParameterEntityPool = null;
        this.fNamespacesScope = null;
        this.fNamespacesPrefix = -1;
        this.fRootElementType = -1;
        this.fAttrIndex = -1;
        this.fElementDeclCount = 0;
        this.fAttlistDeclCount = 0;
        this.init();
    }
    
    private void init() {
        this.fEMPTYSymbol = this.fStringPool.addSymbol("EMPTY");
        this.fANYSymbol = this.fStringPool.addSymbol("ANY");
        this.fMIXEDSymbol = this.fStringPool.addSymbol("MIXED");
        this.fCHILDRENSymbol = this.fStringPool.addSymbol("CHILDREN");
        this.fCDATASymbol = this.fStringPool.addSymbol("CDATA");
        this.fIDSymbol = this.fStringPool.addSymbol("ID");
        this.fIDREFSymbol = this.fStringPool.addSymbol("IDREF");
        this.fIDREFSSymbol = this.fStringPool.addSymbol("IDREFS");
        this.fENTITYSymbol = this.fStringPool.addSymbol("ENTITY");
        this.fENTITIESSymbol = this.fStringPool.addSymbol("ENTITIES");
        this.fNMTOKENSymbol = this.fStringPool.addSymbol("NMTOKEN");
        this.fNMTOKENSSymbol = this.fStringPool.addSymbol("NMTOKENS");
        this.fNOTATIONSymbol = this.fStringPool.addSymbol("NOTATION");
        this.fENUMERATIONSymbol = this.fStringPool.addSymbol("ENUMERATION");
        this.fREQUIREDSymbol = this.fStringPool.addSymbol("#REQUIRED");
        this.fFIXEDSymbol = this.fStringPool.addSymbol("#FIXED");
        this.fEpsilonIndex = this.fStringPool.addSymbol("<<CMNODE_EPSILON>>");
    }
    
    public void setValidationEnabled(final boolean fValidationEnabled) throws Exception {
        this.fValidationEnabled = fValidationEnabled;
        this.fValidationEnabledByDynamic = false;
        if (this.fValidationEnabled) {
            if (this.fDynamicDisabledByValidation) {
                this.fDynamicValidation = true;
                this.fDynamicDisabledByValidation = false;
            }
        }
        else if (this.fDynamicValidation) {
            this.fDynamicValidation = false;
            this.fDynamicDisabledByValidation = true;
        }
        this.setValidating(this.fValidationEnabled);
    }
    
    public boolean getValidationEnabled() {
        return this.fValidationEnabled;
    }
    
    public void setDynamicValidationEnabled(final boolean fDynamicValidation) throws Exception {
        this.fDynamicValidation = fDynamicValidation;
        this.fDynamicDisabledByValidation = false;
        if (!this.fDynamicValidation) {
            if (this.fValidationEnabledByDynamic) {
                this.fValidationEnabled = false;
                this.fValidationEnabledByDynamic = false;
            }
        }
        else if (!this.fValidationEnabled) {
            this.fValidationEnabled = true;
            this.fValidationEnabledByDynamic = true;
        }
        this.setValidating(this.fValidationEnabled);
    }
    
    public boolean getDynamicValidationEnabled() {
        return this.fDynamicValidation;
    }
    
    private void setValidating(final boolean b) throws Exception {
        this.fValidating = b;
        this.fEventHandler.setValidating(b);
    }
    
    public void setNamespacesEnabled(final boolean fNamespacesEnabled) {
        this.fNamespacesEnabled = fNamespacesEnabled;
    }
    
    public boolean getNamespacesEnabled() {
        return this.fNamespacesEnabled;
    }
    
    public void startNamespaceDeclScope(final int n, final int n2) throws Exception {
        this.fEventHandler.startNamespaceDeclScope(n, n2);
    }
    
    public void endNamespaceDeclScope(final int n) throws Exception {
        this.fEventHandler.endNamespaceDeclScope(n);
    }
    
    public void setWarningOnDuplicateAttDef(final boolean fWarningOnDuplicateAttDef) {
        this.fWarningOnDuplicateAttDef = fWarningOnDuplicateAttDef;
    }
    
    public boolean getWarningOnDuplicateAttDef() {
        return this.fWarningOnDuplicateAttDef;
    }
    
    public void setWarningOnUndeclaredElements(final boolean fWarningOnUndeclaredElements) {
        this.fWarningOnUndeclaredElements = fWarningOnUndeclaredElements;
    }
    
    public boolean getWarningOnUndeclaredElements() {
        return this.fWarningOnUndeclaredElements;
    }
    
    private boolean usingStandaloneReader() {
        return this.fStandaloneReader == -1 || this.fEntityHandler.getReaderId() == this.fStandaloneReader;
    }
    
    protected boolean invalidStandaloneAttDef(final int n, final int n2) {
        return this.fStandaloneReader != -1 && n != -1 && this.getAttDefIsExternal(this.getAttDef(n, n2));
    }
    
    public boolean scanDoctypeDecl(final boolean b) throws Exception {
        this.fSeenDoctypeDecl = true;
        this.fStandaloneReader = (b ? this.fEntityHandler.getReaderId() : -1);
        if (!this.fDTDScanner.scanDoctypeDecl()) {
            return false;
        }
        if (this.fDTDScanner.getReadingExternalEntity()) {
            this.fDTDScanner.scanDecls(true);
        }
        if (this.fValidating) {
            if (this.fWarningOnUndeclaredElements) {
                this.checkDeclaredElements();
            }
            this.fEntityPool.checkRequiredNotations();
        }
        this.fEventHandler.endDTD();
        return true;
    }
    
    public void characters(final char[] array, final int n, final int n2) throws Exception {
        if (this.fValidating) {
            this.charDataInContent();
        }
    }
    
    public void characters(final int n) throws Exception {
        if (this.fValidating) {
            this.charDataInContent();
        }
    }
    
    public void ignorableWhitespace(final char[] array, final int n, final int n2) throws Exception {
        if (this.fStandaloneReader != -1 && this.fValidating && this.getElementDeclIsExternal(this.fCurrentElementIndex)) {
            this.reportRecoverableXMLError(143, 80);
        }
    }
    
    public void ignorableWhitespace(final int n) throws Exception {
        if (this.fStandaloneReader != -1 && this.fValidating && this.getElementDeclIsExternal(this.fCurrentElementIndex)) {
            this.reportRecoverableXMLError(143, 80);
        }
    }
    
    public int lookupEntity(final int n) {
        return this.fEntityPool.lookupEntity(n);
    }
    
    public boolean externalReferenceInContent(final int n) throws Exception {
        final boolean externalEntity = this.fEntityPool.isExternalEntity(n);
        if (this.fStandaloneReader != -1 && this.fValidating) {
            if (externalEntity) {
                this.reportRecoverableXMLError(102, 80, this.fEntityPool.getEntityName(n));
            }
            else if (this.fEntityPool.getEntityDeclIsExternal(n)) {
                this.reportRecoverableXMLError(132, 80, this.fEntityPool.getEntityName(n));
            }
        }
        return externalEntity;
    }
    
    public int valueOfReferenceInAttValue(final int n) throws Exception {
        if (this.fStandaloneReader != -1 && this.fValidating && this.fEntityPool.getEntityDeclIsExternal(n)) {
            this.reportRecoverableXMLError(132, 80, this.fEntityPool.getEntityName(n));
        }
        return this.fEntityPool.getEntityValue(n);
    }
    
    public boolean isExternalEntity(final int n) {
        return this.fEntityPool.isExternalEntity(n);
    }
    
    public boolean isUnparsedEntity(final int n) {
        return this.fEntityPool.isUnparsedEntity(n);
    }
    
    public int getEntityValue(final int n) {
        return this.fEntityPool.getEntityValue(n);
    }
    
    public String getPublicIdOfEntity(final int n) {
        return this.fStringPool.toString(this.fEntityPool.getPublicId(n));
    }
    
    public String getSystemIdOfEntity(final int n) {
        return this.fStringPool.toString(this.fEntityPool.getSystemId(n));
    }
    
    public int lookupParameterEntity(final int n) throws Exception {
        int lookupEntity = -1;
        if (this.fParameterEntityPool != null) {
            lookupEntity = this.fParameterEntityPool.lookupEntity(n);
        }
        if (lookupEntity == -1 && this.fValidating) {
            this.reportRecoverableXMLError(62, 62, n);
        }
        return lookupEntity;
    }
    
    public boolean isExternalParameterEntity(final int n) {
        return this.fParameterEntityPool.isExternalEntity(n);
    }
    
    public int getParameterEntityValue(final int n) {
        return this.fParameterEntityPool.getEntityValue(n);
    }
    
    public String getPublicIdOfParameterEntity(final int n) {
        return this.fStringPool.toString(this.fParameterEntityPool.getPublicId(n));
    }
    
    public String getSystemIdOfParameterEntity(final int n) {
        return this.fStringPool.toString(this.fParameterEntityPool.getSystemId(n));
    }
    
    public void rootElementSpecified(final int n) throws Exception {
        if (this.fDynamicValidation && !this.fSeenDoctypeDecl) {
            this.setValidating(false);
        }
        if (this.fValidating && this.fRootElementType != -1 && n != this.fRootElementType) {
            this.reportRecoverableXMLError(3, 1, this.fRootElementType, n);
        }
        if (this.fNamespacesEnabled && this.fNamespacesScope == null) {
            this.fNamespacesScope = new NamespacesScope((NamespacesScope.NamespacesHandler)this);
            this.fNamespacesPrefix = this.fStringPool.addSymbol("xmlns");
            this.fNamespacesScope.setNamespaceForPrefix(this.fNamespacesPrefix, -1);
            this.fNamespacesScope.setNamespaceForPrefix(this.fStringPool.addSymbol("xml"), this.fStringPool.addSymbol("http://www.w3.org/XML/1998/namespace"));
        }
    }
    
    public boolean attributeSpecified(final int n, final XMLAttrList list, final int n2, final Locator locator, int normalize) throws Exception {
        if (!this.fValidating && this.fAttlistDeclCount == 0) {
            final int fcdataSymbol = this.fCDATASymbol;
            if (this.fAttrIndex == -1) {
                this.fAttrIndex = list.startAttrList();
            }
            return list.addAttr(n2, normalize, fcdataSymbol, true, true) != -1;
        }
        final int attDef = this.getAttDef(n, n2);
        if (attDef == -1) {
            if (this.fValidating) {
                this.fErrorReporter.reportError(locator, "http://www.w3.org/TR/1998/REC-xml-19980210", 82, 78, new Object[] { this.fStringPool.toString(n), this.fStringPool.toString(n2) }, 1);
            }
            final int fcdataSymbol2 = this.fCDATASymbol;
            if (this.fAttrIndex == -1) {
                this.fAttrIndex = list.startAttrList();
            }
            return list.addAttr(n2, normalize, fcdataSymbol2, true, true) != -1;
        }
        final int attType = this.getAttType(attDef);
        if (attType != this.fCDATASymbol) {
            normalize = this.getAttributeValidator(attDef).normalize(n, n2, normalize, attType, (attType == this.fNOTATIONSymbol || attType == this.fENUMERATIONSymbol) ? this.getEnumeration(attDef) : -1);
        }
        if (this.fAttrIndex == -1) {
            this.fAttrIndex = list.startAttrList();
        }
        return list.addAttr(n2, normalize, attType, true, true) != -1;
    }
    
    public boolean startElement(final int fCurrentElementType, final XMLAttrList list) throws Exception {
        int n = this.fAttrIndex;
        this.fAttrIndex = -1;
        if (this.fElementDeclCount == 0 && this.fAttlistDeclCount == 0 && !this.fValidating && !this.fNamespacesEnabled) {
            return false;
        }
        final int declaration = this.fStringPool.getDeclaration(fCurrentElementType);
        final int fCurrentContentSpecType = (declaration == -1) ? -1 : this.getContentSpecType(declaration);
        if (fCurrentContentSpecType == -1 && this.fValidating) {
            this.reportRecoverableXMLError(83, 79, fCurrentElementType);
        }
        if (this.fAttlistDeclCount != 0 && declaration != -1) {
            n = this.addDefaultAttributes(declaration, list, n, this.fValidating, this.fStandaloneReader != -1);
        }
        if (this.fNamespacesEnabled) {
            this.fNamespacesScope.increaseDepth();
            if (n != -1) {
                for (int i = list.getFirstAttr(n); i != -1; i = list.getNextAttr(i)) {
                    final int attrName = list.getAttrName(i);
                    if (this.fStringPool.equalNames(attrName, this.fNamespacesPrefix)) {
                        this.fNamespacesScope.setNamespaceForPrefix(0, this.fStringPool.addSymbol(list.getAttValue(i)));
                    }
                    else if (this.fStringPool.getPrefixForQName(attrName) == this.fNamespacesPrefix) {
                        this.fNamespacesScope.setNamespaceForPrefix(this.fStringPool.getLocalPartForQName(attrName), this.fStringPool.addSymbol(list.getAttValue(i)));
                    }
                }
            }
            final int prefixForQName = this.fStringPool.getPrefixForQName(fCurrentElementType);
            int n2;
            if (prefixForQName == -1) {
                n2 = this.fNamespacesScope.getNamespaceForPrefix(0);
                if (n2 != -1) {
                    this.fStringPool.setURIForQName(fCurrentElementType, n2);
                }
            }
            else {
                n2 = this.fNamespacesScope.getNamespaceForPrefix(prefixForQName);
                if (n2 == -1) {
                    this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1999/REC-xml-names-19990114", 167, 144, new Object[] { this.fStringPool.toString(prefixForQName) }, 1);
                }
                this.fStringPool.setURIForQName(fCurrentElementType, n2);
            }
            if (n != -1) {
                for (int j = list.getFirstAttr(n); j != -1; j = list.getNextAttr(j)) {
                    final int attrName2 = list.getAttrName(j);
                    if (!this.fStringPool.equalNames(attrName2, this.fNamespacesPrefix)) {
                        final int prefixForQName2 = this.fStringPool.getPrefixForQName(attrName2);
                        if (prefixForQName2 != this.fNamespacesPrefix) {
                            if (prefixForQName2 == -1) {
                                this.fStringPool.setURIForQName(attrName2, n2);
                            }
                            else {
                                final int namespaceForPrefix = this.fNamespacesScope.getNamespaceForPrefix(prefixForQName2);
                                if (namespaceForPrefix == -1) {
                                    this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1999/REC-xml-names-19990114", 167, 144, new Object[] { this.fStringPool.toString(prefixForQName2) }, 1);
                                }
                                this.fStringPool.setURIForQName(attrName2, namespaceForPrefix);
                            }
                        }
                    }
                }
            }
        }
        if (this.fElementDepth >= 0) {
            final int[] array = this.fElementChildren[this.fElementDepth];
            int n3 = this.fElementChildCount[this.fElementDepth];
            try {
                array[n3] = fCurrentElementType;
            }
            catch (NullPointerException ex) {
                final int[][] fElementChildren = this.fElementChildren;
                final int fElementDepth = this.fElementDepth;
                final int[] array2 = new int[256];
                fElementChildren[fElementDepth] = array2;
                final int[] array3 = array2;
                n3 = 0;
                array3[n3] = fCurrentElementType;
            }
            catch (ArrayIndexOutOfBoundsException ex2) {
                final int[] array4 = new int[n3 * 2];
                System.arraycopy(array, 0, array4, 0, n3);
                (this.fElementChildren[this.fElementDepth] = array4)[n3] = fCurrentElementType;
            }
            this.fElementChildCount[this.fElementDepth] = ++n3;
        }
        ++this.fElementDepth;
        if (this.fElementDepth == this.fElementTypeStack.length) {
            final int[] fElementTypeStack = new int[this.fElementDepth * 2];
            System.arraycopy(this.fElementTypeStack, 0, fElementTypeStack, 0, this.fElementDepth);
            this.fElementTypeStack = fElementTypeStack;
            final int[] fElementIndexStack = new int[this.fElementDepth * 2];
            System.arraycopy(this.fElementIndexStack, 0, fElementIndexStack, 0, this.fElementDepth);
            this.fElementIndexStack = fElementIndexStack;
            final int[] fContentSpecTypeStack = new int[this.fElementDepth * 2];
            System.arraycopy(this.fContentSpecTypeStack, 0, fContentSpecTypeStack, 0, this.fElementDepth);
            this.fContentSpecTypeStack = fContentSpecTypeStack;
            final int[] fElementChildCount = new int[this.fElementDepth * 2];
            System.arraycopy(this.fElementChildCount, 0, fElementChildCount, 0, this.fElementDepth);
            this.fElementChildCount = fElementChildCount;
            final int[][] fElementChildren2 = new int[this.fElementDepth * 2][];
            System.arraycopy(this.fElementChildren, 0, fElementChildren2, 0, this.fElementDepth);
            this.fElementChildren = fElementChildren2;
        }
        this.fCurrentElementType = fCurrentElementType;
        this.fCurrentElementIndex = declaration;
        this.fCurrentContentSpecType = fCurrentContentSpecType;
        this.fElementTypeStack[this.fElementDepth] = fCurrentElementType;
        this.fElementIndexStack[this.fElementDepth] = declaration;
        this.fContentSpecTypeStack[this.fElementDepth] = fCurrentContentSpecType;
        this.fElementChildCount[this.fElementDepth] = 0;
        return fCurrentContentSpecType == this.fCHILDRENSymbol;
    }
    
    public boolean endElement(final int n) throws Exception {
        if (!this.fValidating && !this.fNamespacesEnabled && this.fElementDeclCount == 0 && this.fAttlistDeclCount == 0) {
            return false;
        }
        if (this.fValidating) {
            final int fCurrentElementIndex = this.fCurrentElementIndex;
            if (fCurrentElementIndex != -1 && this.fCurrentContentSpecType != -1) {
                final int peekChildCount = this.peekChildCount();
                final int checkContent = this.checkContent(fCurrentElementIndex, peekChildCount, this.peekChildren());
                if (checkContent != -1) {
                    this.reportRecoverableXMLError((checkContent != peekChildCount) ? 87 : 88, 0, this.fStringPool.toString(n), this.getContentSpecAsString(fCurrentElementIndex));
                }
            }
        }
        if (this.fElementDepth-- < 0) {
            throw new RuntimeException("Element stack underflow");
        }
        if (this.fElementDepth < 0) {
            this.fCurrentElementType = -1;
            this.fCurrentElementIndex = -1;
            this.fCurrentContentSpecType = -1;
            if (this.fValidating && this.fIdRefs != null) {
                this.checkIdRefs();
            }
        }
        else {
            this.fCurrentElementType = this.fElementTypeStack[this.fElementDepth];
            this.fCurrentElementIndex = this.fElementIndexStack[this.fElementDepth];
            this.fCurrentContentSpecType = this.fContentSpecTypeStack[this.fElementDepth];
        }
        if (this.fNamespacesEnabled) {
            this.fNamespacesScope.decreaseDepth();
        }
        return this.fCurrentContentSpecType == this.fCHILDRENSymbol;
    }
    
    private void charDataInContent() {
        final int[] array = this.fElementChildren[this.fElementDepth];
        int n = this.fElementChildCount[this.fElementDepth];
        try {
            array[n] = -1;
        }
        catch (NullPointerException ex) {
            final int[][] fElementChildren = this.fElementChildren;
            final int fElementDepth = this.fElementDepth;
            final int[] array2 = new int[256];
            fElementChildren[fElementDepth] = array2;
            final int[] array3 = array2;
            n = 0;
            array3[n] = -1;
        }
        catch (ArrayIndexOutOfBoundsException ex2) {
            final int[] array4 = new int[n * 2];
            System.arraycopy(array, 0, array4, 0, n);
            (this.fElementChildren[this.fElementDepth] = array4)[n] = -1;
        }
        this.fElementChildCount[this.fElementDepth] = ++n;
    }
    
    private int peekChildCount() {
        return this.fElementChildCount[this.fElementDepth];
    }
    
    private int[] peekChildren() {
        return this.fElementChildren[this.fElementDepth];
    }
    
    public int checkContent(final int n, final int n2, final int[] array) throws Exception {
        final int fCurrentElementType = this.fCurrentElementType;
        final int fCurrentContentSpecType = this.fCurrentContentSpecType;
        if (fCurrentContentSpecType == this.fEMPTYSymbol) {
            if (n2 != 0) {
                return 0;
            }
        }
        else if (fCurrentContentSpecType != this.fANYSymbol) {
            if (fCurrentContentSpecType == this.fMIXEDSymbol || fCurrentContentSpecType == this.fCHILDRENSymbol) {
                try {
                    return this.getContentModel(n).validateContent(n2, array);
                }
                catch (CMException ex) {
                    this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.apache.org/xml/xerces.html", ex.getErrorCode(), 0, null, 2);
                    return -1;
                }
            }
            if (fCurrentContentSpecType == -1) {
                this.reportRecoverableXMLError(83, 79, fCurrentElementType);
            }
            else {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.apache.org/xml/xerces.html", 8, 0, null, 2);
            }
        }
        return -1;
    }
    
    public int whatCanGoHere(final int n, final boolean b, final InsertableElementsInfo insertableElementsInfo) throws Exception {
        if (insertableElementsInfo.insertAt > insertableElementsInfo.childCount || insertableElementsInfo.curChildren == null || insertableElementsInfo.childCount < 1 || insertableElementsInfo.childCount > insertableElementsInfo.curChildren.length) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.apache.org/xml/xerces.html", 13, 0, null, 2);
        }
        int whatCanGoHere = 0;
        try {
            whatCanGoHere = this.getContentModel(n).whatCanGoHere(b, insertableElementsInfo);
        }
        catch (CMException ex) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.apache.org/xml/xerces.html", ex.getErrorCode(), 0, null, 2);
        }
        return whatCanGoHere;
    }
    
    private final XMLContentModel createChildModel(final int n) throws CMException {
        final XMLContentSpecNode xmlContentSpecNode = new XMLContentSpecNode();
        final int contentSpecHandle = this.getContentSpecHandle(n);
        this.getContentSpecNode(contentSpecHandle, xmlContentSpecNode);
        if (xmlContentSpecNode.value == -1) {
            throw new CMException(11);
        }
        if (xmlContentSpecNode.type == 0) {
            return new SimpleContentModel(xmlContentSpecNode.value, -1, xmlContentSpecNode.type);
        }
        if (xmlContentSpecNode.type == 4 || xmlContentSpecNode.type == 5) {
            final XMLContentSpecNode xmlContentSpecNode2 = new XMLContentSpecNode();
            final XMLContentSpecNode xmlContentSpecNode3 = new XMLContentSpecNode();
            this.getContentSpecNode(xmlContentSpecNode.value, xmlContentSpecNode2);
            this.getContentSpecNode(xmlContentSpecNode.otherValue, xmlContentSpecNode3);
            if (xmlContentSpecNode2.type == 0 && xmlContentSpecNode3.type == 0) {
                return new SimpleContentModel(xmlContentSpecNode2.value, xmlContentSpecNode3.value, xmlContentSpecNode.type);
            }
        }
        else {
            if (xmlContentSpecNode.type != 1 && xmlContentSpecNode.type != 2 && xmlContentSpecNode.type != 3) {
                throw new CMException(8);
            }
            final XMLContentSpecNode xmlContentSpecNode4 = new XMLContentSpecNode();
            this.getContentSpecNode(xmlContentSpecNode.value, xmlContentSpecNode4);
            if (xmlContentSpecNode4.type == 0) {
                return new SimpleContentModel(xmlContentSpecNode4.value, -1, xmlContentSpecNode.type);
            }
        }
        this.fLeafCount = 0;
        return new DFAContentModel(this.fStringPool, this.buildSyntaxTree(contentSpecHandle, xmlContentSpecNode), this.fLeafCount);
    }
    
    private XMLContentModel getContentModel(final int n) throws CMException {
        final XMLContentModel elementContentModel = this.getElementContentModel(n);
        if (elementContentModel != null) {
            return elementContentModel;
        }
        final int contentSpecType = this.getContentSpecType(n);
        XMLContentModel childModel;
        if (contentSpecType == this.fMIXEDSymbol) {
            this.makeContentList(this.getContentSpecHandle(n), new XMLContentSpecNode());
            childModel = new MixedContentModel(this.fCount, this.fContentList);
        }
        else {
            if (contentSpecType != this.fCHILDRENSymbol) {
                throw new CMException(8);
            }
            childModel = this.createChildModel(n);
        }
        this.setContentModel(n, childModel);
        return childModel;
    }
    
    protected void reportRecoverableXMLError(final int n, final int n2) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, null, 1);
    }
    
    protected void reportRecoverableXMLError(final int n, final int n2, final int n3) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, new Object[] { this.fStringPool.toString(n3) }, 1);
    }
    
    protected void reportRecoverableXMLError(final int n, final int n2, final String s) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, new Object[] { s }, 1);
    }
    
    protected void reportRecoverableXMLError(final int n, final int n2, final int n3, final int n4) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, new Object[] { this.fStringPool.toString(n3), this.fStringPool.toString(n4) }, 1);
    }
    
    protected void reportRecoverableXMLError(final int n, final int n2, final String s, final String s2) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, new Object[] { s, s2 }, 1);
    }
    
    protected void reportRecoverableXMLError(final int n, final int n2, final String s, final String s2, final String s3) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, new Object[] { s, s2, s3 }, 1);
    }
    
    public void readerChange(final XMLEntityHandler.EntityReader entityReader, final int n) throws Exception {
        this.fDTDScanner.readerChange(entityReader, n);
    }
    
    public void endOfInput(final int n, final boolean b) throws Exception {
        if (this.fValidating) {
            final int readerDepth = this.fEntityHandler.getReaderDepth();
            if (this.fDTDScanner.getReadingContentSpec()) {
                if (readerDepth != this.fDTDScanner.parenDepth()) {
                    this.reportRecoverableXMLError(74, 75, n);
                }
            }
            else if (readerDepth != this.fDTDScanner.markupDepth()) {
                this.reportRecoverableXMLError(73, 74, n);
            }
        }
        this.fDTDScanner.endOfInput(n, b);
    }
    
    public int saveCurrentLocation() {
        return -1;
    }
    
    public boolean validVersionNum(final String s) {
        return XMLCharacterProperties.validVersionNum(s);
    }
    
    public boolean validEncName(final String s) {
        return XMLCharacterProperties.validEncName(s);
    }
    
    public int validPublicId(final String s) {
        return XMLCharacterProperties.validPublicId(s);
    }
    
    public void doctypeDecl(final int fRootElementType, final int n, final int n2) throws Exception {
        this.fRootElementType = fRootElementType;
        this.fEventHandler.startDTD(fRootElementType, n, n2);
    }
    
    public void startReadingFromExternalSubset(final int n, final int n2) throws Exception {
        this.fEntityHandler.startReadingFromExternalSubset(this.fStringPool.toString(n), this.fStringPool.toString(n2), this.fDTDScanner.markupDepth());
    }
    
    public void stopReadingFromExternalSubset() throws Exception {
        this.fEntityHandler.stopReadingFromExternalSubset();
    }
    
    public int addElementDecl(final int n) throws Exception {
        return this.addElement(n);
    }
    
    public int addElementDecl(final int n, final int n2, final int n3) throws Exception {
        final int addElementDecl = this.addElementDecl(n, n2, n3, !this.usingStandaloneReader());
        if (addElementDecl == -1) {
            if (this.fValidating) {
                this.reportRecoverableXMLError(89, 82, n);
            }
        }
        else {
            this.fEventHandler.elementDecl(n, this.getContentSpec(addElementDecl));
            ++this.fElementDeclCount;
        }
        return addElementDecl;
    }
    
    public int addAttDef(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) throws Exception {
        final int addAttDef = this.addAttDef(n, n2, n3, n4, n5, n6, !this.usingStandaloneReader(), this.fValidating, this.fWarningOnDuplicateAttDef);
        if (addAttDef != -1) {
            this.fEventHandler.attlistDecl(this.getElementType(n), n2, n3, (n4 == -1) ? null : this.fStringPool.stringListAsString(n4), n5, n6);
            ++this.fAttlistDeclCount;
        }
        return addAttDef;
    }
    
    public int addUniqueLeafNode(final int n) throws Exception {
        final int addContentSpecNode = this.addContentSpecNode(0, n, -1, true);
        if (addContentSpecNode == -1 && this.fValidating) {
            this.reportRecoverableXMLError(67, 67, n);
        }
        return addContentSpecNode;
    }
    
    public int addContentSpecNode(final int n, final int n2) throws Exception {
        return this.addContentSpecNode(n, n2, -1, false);
    }
    
    public int addContentSpecNode(final int n, final int n2, final int n3) throws Exception {
        return this.addContentSpecNode(n, n2, n3, false);
    }
    
    public int addInternalPEDecl(final int n, final int n2, final int n3) throws Exception {
        if (this.fParameterEntityPool == null) {
            this.fParameterEntityPool = new EntityPool(this.fStringPool, this.fErrorReporter, false);
        }
        final int addEntityDecl = this.fParameterEntityPool.addEntityDecl(n, n2, n3, -1, -1, -1, !this.usingStandaloneReader());
        this.fEventHandler.internalPEDecl(n, n2);
        return addEntityDecl;
    }
    
    public int addExternalPEDecl(final int n, final int n2, final int n3) throws Exception {
        if (this.fParameterEntityPool == null) {
            this.fParameterEntityPool = new EntityPool(this.fStringPool, this.fErrorReporter, false);
        }
        final int addEntityDecl = this.fParameterEntityPool.addEntityDecl(n, -1, -1, n2, n3, -1, !this.usingStandaloneReader());
        this.fEventHandler.externalPEDecl(n, n2, n3);
        return addEntityDecl;
    }
    
    public int addInternalEntityDecl(final int n, final int n2, final int n3) throws Exception {
        final int addEntityDecl = this.fEntityPool.addEntityDecl(n, n2, n3, -1, -1, -1, !this.usingStandaloneReader());
        this.fEventHandler.internalEntityDecl(n, n2);
        return addEntityDecl;
    }
    
    public int addExternalEntityDecl(final int n, final int n2, final int n3) throws Exception {
        final int addEntityDecl = this.fEntityPool.addEntityDecl(n, -1, -1, n2, n3, -1, !this.usingStandaloneReader());
        this.fEventHandler.externalEntityDecl(n, n2, n3);
        return addEntityDecl;
    }
    
    public int addUnparsedEntityDecl(final int n, final int n2, final int n3, final int n4) throws Exception {
        final int addEntityDecl = this.fEntityPool.addEntityDecl(n, -1, -1, n2, n3, n4, !this.usingStandaloneReader());
        this.fEventHandler.unparsedEntityDecl(n, n2, n3, n4);
        if (this.fEntityPool.lookupNotation(n4) == -1) {
            this.fEntityPool.addRequiredNotation(n4, this.fErrorReporter.getLocator(), 114, 89, new Object[] { this.fStringPool.toString(n), this.fStringPool.toString(n4) });
        }
        return addEntityDecl;
    }
    
    public int startEnumeration() {
        return this.fStringPool.startStringList();
    }
    
    public void addNameToEnumeration(final int n, final int n2, final int n3, final int n4, final boolean b) {
        this.fStringPool.addStringToList(n, n4);
        if (b && this.fEntityPool.lookupNotation(n4) == -1) {
            this.fEntityPool.addRequiredNotation(n4, this.fErrorReporter.getLocator(), 117, 89, new Object[] { this.fStringPool.toString(n2), this.fStringPool.toString(n3), this.fStringPool.toString(n4) });
        }
    }
    
    public void endEnumeration(final int n) {
        this.fStringPool.finishStringList(n);
    }
    
    public int addNotationDecl(final int n, final int n2, final int n3) throws Exception {
        final int addNotationDecl = this.fEntityPool.addNotationDecl(n, n2, n3, !this.usingStandaloneReader());
        if (addNotationDecl != -1) {
            this.fEventHandler.notationDecl(n, n2, n3);
        }
        return addNotationDecl;
    }
    
    public void callProcessingInstruction(final int n, final int n2) throws Exception {
        this.fEventHandler.processingInstructionInDTD(n, n2);
    }
    
    public void callComment(final int n) throws Exception {
        this.fEventHandler.commentInDTD(n);
    }
    
    public int scanElementType(final XMLEntityHandler.EntityReader entityReader, final char c) throws Exception {
        if (!this.fNamespacesEnabled) {
            return entityReader.scanName(c);
        }
        final int scanQName = entityReader.scanQName(c);
        if (entityReader.lookingAtChar(':', false)) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 126, 139, null, 2);
            entityReader.skipPastNmtoken(' ');
        }
        return scanQName;
    }
    
    public int checkForElementTypeWithPEReference(final XMLEntityHandler.EntityReader entityReader, final char c) throws Exception {
        if (!this.fNamespacesEnabled) {
            return entityReader.scanName(c);
        }
        final int scanQName = entityReader.scanQName(c);
        if (entityReader.lookingAtChar(':', false)) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 126, 139, null, 2);
            entityReader.skipPastNmtoken(' ');
        }
        return scanQName;
    }
    
    public int checkForAttributeNameWithPEReference(final XMLEntityHandler.EntityReader entityReader, final char c) throws Exception {
        if (!this.fNamespacesEnabled) {
            return entityReader.scanName(c);
        }
        final int scanQName = entityReader.scanQName(c);
        if (entityReader.lookingAtChar(':', false)) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 126, 139, null, 2);
            entityReader.skipPastNmtoken(' ');
        }
        return scanQName;
    }
    
    public int checkForNameWithPEReference(final XMLEntityHandler.EntityReader entityReader, final char c) throws Exception {
        return entityReader.scanName(c);
    }
    
    public int checkForNmtokenWithPEReference(final XMLEntityHandler.EntityReader entityReader, final char c) throws Exception {
        final int currentOffset = entityReader.currentOffset();
        entityReader.skipPastNmtoken(c);
        final int n = entityReader.currentOffset() - currentOffset;
        if (n == 0) {
            return -1;
        }
        return entityReader.addSymbol(currentOffset, n);
    }
    
    public int scanDefaultAttValue(final int n, final int n2, final int n3, final int n4) throws Exception {
        if (this.fValidating && n3 == this.fIDSymbol) {
            this.reportRecoverableXMLError(69, 69, n2);
        }
        int n5 = this.fDTDScanner.scanDefaultAttValue(n, n2);
        if (n5 == -1) {
            return -1;
        }
        if (n3 != this.fCDATASymbol) {
            n5 = this.getValidatorForAttType(n3).normalize(-1, n2, n5, n3, n4);
        }
        return n5;
    }
    
    private final CMNode buildSyntaxTree(final int n, final XMLContentSpecNode xmlContentSpecNode) throws CMException {
        this.getContentSpecNode(n, xmlContentSpecNode);
        CMNode cmNode;
        if (xmlContentSpecNode.type == 0) {
            cmNode = new CMLeaf(xmlContentSpecNode.type, xmlContentSpecNode.value, this.fLeafCount++);
        }
        else {
            final int value = xmlContentSpecNode.value;
            final int otherValue = xmlContentSpecNode.otherValue;
            if (xmlContentSpecNode.type == 4 || xmlContentSpecNode.type == 5) {
                cmNode = new CMBinOp(xmlContentSpecNode.type, this.buildSyntaxTree(value, xmlContentSpecNode), this.buildSyntaxTree(otherValue, xmlContentSpecNode));
            }
            else if (xmlContentSpecNode.type == 2) {
                cmNode = new CMUniOp(xmlContentSpecNode.type, this.buildSyntaxTree(value, xmlContentSpecNode));
            }
            else if (xmlContentSpecNode.type == 1) {
                cmNode = new CMBinOp(4, this.buildSyntaxTree(value, xmlContentSpecNode), new CMLeaf(0, this.fEpsilonIndex));
            }
            else {
                if (xmlContentSpecNode.type != 3) {
                    throw new CMException(8);
                }
                cmNode = new CMBinOp(5, this.buildSyntaxTree(value, xmlContentSpecNode), new CMUniOp(2, this.buildSyntaxTree(value, xmlContentSpecNode)));
            }
        }
        return cmNode;
    }
    
    private final void makeContentList(final int n, final XMLContentSpecNode xmlContentSpecNode) throws CMException {
        while (true) {
            this.fCount = 0;
            try {
                this.fCount = this.buildContentList(n, 0, xmlContentSpecNode);
            }
            catch (IndexOutOfBoundsException ex) {
                this.fContentList = new int[this.fContentList.length * 2];
                this.fCount = 0;
            }
        }
    }
    
    private final int buildContentList(final int n, int n2, final XMLContentSpecNode xmlContentSpecNode) throws CMException {
        this.getContentSpecNode(n, xmlContentSpecNode);
        if (xmlContentSpecNode.type == 0) {
            this.fContentList[n2++] = xmlContentSpecNode.value;
            return n2;
        }
        final int value = xmlContentSpecNode.value;
        final int otherValue = xmlContentSpecNode.otherValue;
        if (xmlContentSpecNode.type == 4 || xmlContentSpecNode.type == 5) {
            n2 = this.buildContentList(value, n2, xmlContentSpecNode);
            n2 = this.buildContentList(otherValue, n2, xmlContentSpecNode);
        }
        else {
            if (xmlContentSpecNode.type != 1 && xmlContentSpecNode.type != 2 && xmlContentSpecNode.type != 3) {
                throw new CMException(8);
            }
            n2 = this.buildContentList(value, n2, xmlContentSpecNode);
        }
        return n2;
    }
    
    private void poolReset() {
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < this.fElementCount; ++i) {
            this.fContentModel[n][n2] = null;
            if (++n2 == 256) {
                ++n;
                n2 = 0;
            }
        }
        this.fElementCount = 0;
        this.fNodeCount = 0;
        this.fAttDefCount = 0;
        if (this.fIdDefs != null) {
            this.fIdDefs.clear();
        }
        if (this.fIdRefs != null) {
            this.fIdRefs.clear();
        }
    }
    
    private boolean ensureElementCapacity(final int n) {
        try {
            return this.fElementType[n][0] == 0;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            final byte[][] fElementDeclIsExternal = new byte[n * 2][];
            System.arraycopy(this.fElementDeclIsExternal, 0, fElementDeclIsExternal, 0, n);
            this.fElementDeclIsExternal = fElementDeclIsExternal;
            final int[][] fElementType = new int[n * 2][];
            System.arraycopy(this.fElementType, 0, fElementType, 0, n);
            this.fElementType = fElementType;
            final int[][] fContentSpecType = new int[n * 2][];
            System.arraycopy(this.fContentSpecType, 0, fContentSpecType, 0, n);
            this.fContentSpecType = fContentSpecType;
            final int[][] fContentSpec = new int[n * 2][];
            System.arraycopy(this.fContentSpec, 0, fContentSpec, 0, n);
            this.fContentSpec = fContentSpec;
            final XMLContentModel[][] fContentModel = new XMLContentModel[n * 2][];
            System.arraycopy(this.fContentModel, 0, fContentModel, 0, n);
            this.fContentModel = fContentModel;
            final int[][] fAttlistHead = new int[n * 2][];
            System.arraycopy(this.fAttlistHead, 0, fAttlistHead, 0, n);
            this.fAttlistHead = fAttlistHead;
            final int[][] fAttlistTail = new int[n * 2][];
            System.arraycopy(this.fAttlistTail, 0, fAttlistTail, 0, n);
            this.fAttlistTail = fAttlistTail;
        }
        catch (NullPointerException ex2) {}
        this.fElementType[n] = new int[256];
        this.fElementDeclIsExternal[n] = new byte[256];
        this.fContentSpecType[n] = new int[256];
        this.fContentSpec[n] = new int[256];
        this.fContentModel[n] = new XMLContentModel[256];
        this.fAttlistHead[n] = new int[256];
        this.fAttlistTail[n] = new int[256];
        return true;
    }
    
    private int lookupElement(final int n) {
        return this.fStringPool.getDeclaration(n);
    }
    
    private int addElement(final int n) {
        final int declaration = this.fStringPool.getDeclaration(n);
        if (declaration != -1) {
            return declaration;
        }
        final int n2 = this.fElementCount >> 8;
        final int n3 = this.fElementCount & 0xFF;
        this.ensureElementCapacity(n2);
        this.fElementType[n2][n3] = n;
        this.fElementDeclIsExternal[n2][n3] = 0;
        this.fContentSpecType[n2][n3] = -1;
        this.fContentSpec[n2][n3] = -1;
        this.fContentModel[n2][n3] = null;
        this.fAttlistHead[n2][n3] = -1;
        this.fAttlistTail[n2][n3] = -1;
        this.fStringPool.setDeclaration(n, this.fElementCount);
        return this.fElementCount++;
    }
    
    private int addElementDecl(final int n, final int n2, final int n3, final boolean b) {
        final int declaration = this.fStringPool.getDeclaration(n);
        if (declaration == -1) {
            final int n4 = this.fElementCount >> 8;
            final int n5 = this.fElementCount & 0xFF;
            this.ensureElementCapacity(n4);
            this.fElementType[n4][n5] = n;
            this.fElementDeclIsExternal[n4][n5] = (byte)(b ? 1 : 0);
            this.fContentSpecType[n4][n5] = n2;
            this.fContentSpec[n4][n5] = n3;
            this.fContentModel[n4][n5] = null;
            this.fAttlistHead[n4][n5] = -1;
            this.fAttlistTail[n4][n5] = -1;
            this.fStringPool.setDeclaration(n, this.fElementCount);
            return this.fElementCount++;
        }
        final int n6 = declaration >> 8;
        final int n7 = declaration & 0xFF;
        if (this.fContentSpecType[n6][n7] != -1) {
            return -1;
        }
        this.fElementDeclIsExternal[n6][n7] = (byte)(b ? 1 : 0);
        this.fContentSpecType[n6][n7] = n2;
        this.fContentSpec[n6][n7] = n3;
        this.fContentModel[n6][n7] = null;
        return declaration;
    }
    
    private int getElementType(final int n) {
        if (n < 0 || n >= this.fElementCount) {
            return -1;
        }
        return this.fElementType[n >> 8][n & 0xFF];
    }
    
    private boolean getElementDeclIsExternal(final int n) {
        return n >= 0 && n < this.fElementCount && this.fElementDeclIsExternal[n >> 8][n & 0xFF] != 0;
    }
    
    private int getContentSpecType(final int n) {
        if (n < 0 || n >= this.fElementCount) {
            return -1;
        }
        return this.fContentSpecType[n >> 8][n & 0xFF];
    }
    
    private ContentSpec getContentSpec(final int n) {
        if (n < 0 || n >= this.fElementCount) {
            return null;
        }
        final int n2 = n >> 8;
        final int n3 = n & 0xFF;
        if (this.fContentSpecImpl == null) {
            this.fContentSpecImpl = new ContentSpecImpl();
        }
        this.fContentSpecImpl.fStringPool = this.fStringPool;
        this.fContentSpecImpl.fHandle = this.fContentSpec[n2][n3];
        this.fContentSpecImpl.fType = this.fContentSpecType[n2][n3];
        return this.fContentSpecImpl;
    }
    
    private int getContentSpecHandle(final int n) {
        if (n < 0 || n >= this.fElementCount) {
            return -1;
        }
        return this.fContentSpec[n >> 8][n & 0xFF];
    }
    
    public String getContentSpecAsString(final int n) {
        if (n < 0 || n >= this.fElementCount) {
            return null;
        }
        final int n2 = n >> 8;
        final int n3 = n & 0xFF;
        final int n4 = this.fContentSpecType[n2][n3];
        if (n4 == this.fMIXEDSymbol || n4 == this.fCHILDRENSymbol) {
            return this.getContentSpecNodeAsString(this.fContentSpec[n2][n3]);
        }
        return this.fStringPool.toString(n4);
    }
    
    private XMLContentModel getElementContentModel(final int n) {
        if (n < 0 || n >= this.fElementCount) {
            return null;
        }
        return this.fContentModel[n >> 8][n & 0xFF];
    }
    
    private void setContentModel(final int n, final XMLContentModel xmlContentModel) {
        if (n < 0 || n >= this.fElementCount) {
            return;
        }
        this.fContentModel[n >> 8][n & 0xFF] = xmlContentModel;
    }
    
    private boolean ensureNodeCapacity(final int n) {
        try {
            return this.fNodeType[n][0] == 0;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            final byte[][] fNodeType = new byte[n * 2][];
            System.arraycopy(this.fNodeType, 0, fNodeType, 0, n);
            this.fNodeType = fNodeType;
            final int[][] fNodeValue = new int[n * 2][];
            System.arraycopy(this.fNodeValue, 0, fNodeValue, 0, n);
            this.fNodeValue = fNodeValue;
        }
        catch (NullPointerException ex2) {}
        this.fNodeType[n] = new byte[256];
        this.fNodeValue[n] = new int[256];
        return true;
    }
    
    private int addContentSpecLeafNode(final int n) throws Exception {
        if (n != -1) {
            int n2 = this.fNodeCount >> 8;
            int n3 = this.fNodeCount & 0xFF;
            while (true) {
                if (n3-- == 0) {
                    n3 = 255;
                    --n2;
                }
                if (this.fNodeType[n2][n3] == 0) {
                    final int n4 = this.fNodeValue[n2][n3];
                    if (n4 == -1) {
                        break;
                    }
                    if (n4 == n) {
                        return -1;
                    }
                    continue;
                }
            }
        }
        final int n5 = this.fNodeCount >> 8;
        final int n6 = this.fNodeCount & 0xFF;
        this.ensureNodeCapacity(n5);
        this.fNodeType[n5][n6] = 0;
        this.fNodeValue[n5][n6] = n;
        return this.fNodeCount++;
    }
    
    private int addContentSpecNode(final int n, final int n2, final int n3, final boolean b) throws Exception {
        if (b) {
            return this.addContentSpecLeafNode(n2);
        }
        int n4 = this.fNodeCount >> 8;
        int n5 = this.fNodeCount & 0xFF;
        this.ensureNodeCapacity(n4);
        switch (n) {
            case 0:
            case 1:
            case 2:
            case 3: {
                this.fNodeType[n4][n5] = (byte)n;
                this.fNodeValue[n4][n5] = n2;
                return this.fNodeCount++;
            }
            case 4:
            case 5: {
                this.fNodeType[n4][n5] = (byte)n;
                this.fNodeValue[n4][n5] = n2;
                final int n6 = this.fNodeCount++;
                if (++n5 == 256) {
                    ++n4;
                    this.ensureNodeCapacity(n4);
                    n5 = 0;
                }
                this.fNodeType[n4][n5] = (byte)(n | 0x40);
                this.fNodeValue[n4][n5] = n3;
                ++this.fNodeCount;
                return n6;
            }
            default: {
                return -1;
            }
        }
    }
    
    protected void getContentSpecNode(final int n, final XMLContentSpecNode xmlContentSpecNode) {
        int n2 = n >> 8;
        int n3 = n & 0xFF;
        xmlContentSpecNode.type = this.fNodeType[n2][n3];
        xmlContentSpecNode.value = this.fNodeValue[n2][n3];
        if (xmlContentSpecNode.type == 4 || xmlContentSpecNode.type == 5) {
            if (++n3 == 256) {
                ++n2;
                n3 = 0;
            }
            xmlContentSpecNode.otherValue = this.fNodeValue[n2][n3];
            return;
        }
        xmlContentSpecNode.otherValue = -1;
    }
    
    private void appendContentSpecNode(final int n, final StringBuffer sb, final boolean b) {
        int n2 = n >> 8;
        int n3 = n & 0xFF;
        final byte b2 = this.fNodeType[n2][n3];
        final int n4 = this.fNodeValue[n2][n3];
        switch (b2) {
            case 0: {
                sb.append((n4 == -1) ? "#PCDATA" : this.fStringPool.toString(n4));
            }
            case 1: {
                this.appendContentSpecNode(n4, sb, false);
                sb.append('?');
            }
            case 2: {
                this.appendContentSpecNode(n4, sb, false);
                sb.append('*');
            }
            case 3: {
                this.appendContentSpecNode(n4, sb, false);
                sb.append('+');
            }
            case 4:
            case 5: {
                if (!b) {
                    sb.append('(');
                }
                this.appendContentSpecNode(n4, sb, this.fNodeType[n4 >> 8][n4 & 0xFF] == b2);
                sb.append((char)((b2 == 4) ? 124 : 44));
                if (++n3 == 256) {
                    ++n2;
                    n3 = 0;
                }
                this.appendContentSpecNode(this.fNodeValue[n2][n3], sb, false);
                if (!b) {
                    sb.append(')');
                }
            }
            default: {}
        }
    }
    
    public String getContentSpecNodeAsString(final int n) {
        final int n2 = n >> 8;
        final int n3 = n & 0xFF;
        final byte b = this.fNodeType[n2][n3];
        final int n4 = this.fNodeValue[n2][n3];
        final StringBuffer sb = new StringBuffer();
        switch (b) {
            case 0: {
                sb.append("(" + ((n4 == -1) ? "#PCDATA" : this.fStringPool.toString(n4)) + ")");
                break;
            }
            case 1: {
                final int n5 = n4 >> 8;
                final int n6 = n4 & 0xFF;
                if (this.fNodeType[n5][n6] == 0) {
                    final int n7 = this.fNodeValue[n5][n6];
                    sb.append("(" + ((n7 == -1) ? "#PCDATA" : this.fStringPool.toString(n7)) + ")?");
                    break;
                }
                this.appendContentSpecNode(n, sb, false);
                break;
            }
            case 2: {
                final int n8 = n4 >> 8;
                final int n9 = n4 & 0xFF;
                if (this.fNodeType[n8][n9] == 0) {
                    final int n10 = this.fNodeValue[n8][n9];
                    sb.append("(" + ((n10 == -1) ? "#PCDATA" : this.fStringPool.toString(n10)) + ")*");
                    break;
                }
                this.appendContentSpecNode(n, sb, false);
                break;
            }
            case 3: {
                final int n11 = n4 >> 8;
                final int n12 = n4 & 0xFF;
                if (this.fNodeType[n11][n12] == 0) {
                    final int n13 = this.fNodeValue[n11][n12];
                    sb.append("(" + ((n13 == -1) ? "#PCDATA" : this.fStringPool.toString(n13)) + ")+");
                    break;
                }
                this.appendContentSpecNode(n, sb, false);
                break;
            }
            case 4:
            case 5: {
                this.appendContentSpecNode(n, sb, false);
                break;
            }
            default: {
                return null;
            }
        }
        return sb.toString();
    }
    
    private boolean ensureAttrCapacity(final int n) {
        try {
            return this.fAttName[n][0] == 0;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            final byte[][] fAttDefIsExternal = new byte[n * 2][];
            System.arraycopy(this.fAttDefIsExternal, 0, fAttDefIsExternal, 0, n);
            this.fAttDefIsExternal = fAttDefIsExternal;
            final int[][] fAttName = new int[n * 2][];
            System.arraycopy(this.fAttName, 0, fAttName, 0, n);
            this.fAttName = fAttName;
            final int[][] fAttType = new int[n * 2][];
            System.arraycopy(this.fAttType, 0, fAttType, 0, n);
            this.fAttType = fAttType;
            final int[][] fEnumeration = new int[n * 2][];
            System.arraycopy(this.fEnumeration, 0, fEnumeration, 0, n);
            this.fEnumeration = fEnumeration;
            final int[][] fAttDefaultType = new int[n * 2][];
            System.arraycopy(this.fAttDefaultType, 0, fAttDefaultType, 0, n);
            this.fAttDefaultType = fAttDefaultType;
            final int[][] fAttValue = new int[n * 2][];
            System.arraycopy(this.fAttValue, 0, fAttValue, 0, n);
            this.fAttValue = fAttValue;
            final int[][] fNextAttDef = new int[n * 2][];
            System.arraycopy(this.fNextAttDef, 0, fNextAttDef, 0, n);
            this.fNextAttDef = fNextAttDef;
            final AttributeValidator[][] fAttValidator = new AttributeValidator[n * 2][];
            System.arraycopy(this.fAttValidator, 0, fAttValidator, 0, n);
            this.fAttValidator = fAttValidator;
        }
        catch (NullPointerException ex2) {}
        this.fAttDefIsExternal[n] = new byte[256];
        this.fAttName[n] = new int[256];
        this.fAttType[n] = new int[256];
        this.fAttValidator[n] = new AttributeValidator[256];
        this.fEnumeration[n] = new int[256];
        this.fAttDefaultType[n] = new int[256];
        this.fAttValue[n] = new int[256];
        this.fNextAttDef[n] = new int[256];
        return true;
    }
    
    private int addAttDef(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2, final boolean b3) throws Exception {
        final int n7 = n >> 8;
        final int n8 = n & 0xFF;
        int i = this.fAttlistHead[n7][n8];
        int n9 = -1;
        int n10 = -1;
        while (i != -1) {
            final int n11 = i >> 8;
            final int n12 = i & 0xFF;
            if (this.fStringPool.equalNames(this.fAttName[n11][n12], n2)) {
                if (b3) {
                    this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 85, 85, new Object[] { this.fStringPool.toString(this.fElementType[n7][n8]), this.fStringPool.toString(n2) }, 0);
                }
                return -1;
            }
            if (b2) {
                if (n3 == this.fIDSymbol && this.fAttType[n11][n12] == this.fIDSymbol) {
                    n9 = this.fAttName[n11][n12];
                }
                if (n3 == this.fNOTATIONSymbol && this.fAttType[n11][n12] == this.fNOTATIONSymbol) {
                    n10 = this.fAttName[n11][n12];
                }
            }
            i = this.fNextAttDef[n11][n12];
        }
        if (b2) {
            if (n9 != -1) {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 86, 81, new Object[] { this.fStringPool.toString(this.fElementType[n7][n8]), this.fStringPool.toString(n9), this.fStringPool.toString(n2) }, 1);
                return -1;
            }
            if (n10 != -1) {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 121, 143, new Object[] { this.fStringPool.toString(this.fElementType[n7][n8]), this.fStringPool.toString(n10), this.fStringPool.toString(n2) }, 1);
                return -1;
            }
        }
        final int n13 = this.fAttDefCount >> 8;
        final int n14 = this.fAttDefCount & 0xFF;
        this.ensureAttrCapacity(n13);
        this.fAttName[n13][n14] = n2;
        this.fAttType[n13][n14] = n3;
        this.fAttValidator[n13][n14] = this.getValidatorForAttType(n3);
        this.fEnumeration[n13][n14] = n4;
        this.fAttDefaultType[n13][n14] = n5;
        this.fAttDefIsExternal[n13][n14] = (byte)(b ? 1 : 0);
        int n15;
        if ((this.fAttValue[n13][n14] = n6) != -1) {
            n15 = this.fAttlistHead[n7][n8];
            this.fAttlistHead[n7][n8] = this.fAttDefCount;
            if (n15 == -1) {
                this.fAttlistTail[n7][n8] = this.fAttDefCount;
            }
        }
        else {
            n15 = this.fAttlistTail[n7][n8];
            this.fAttlistTail[n7][n8] = this.fAttDefCount;
            if (n15 == -1) {
                this.fAttlistHead[n7][n8] = this.fAttDefCount;
            }
            else {
                this.fNextAttDef[n15 >> 8][n15 & 0xFF] = this.fAttDefCount;
                n15 = -1;
            }
        }
        this.fNextAttDef[n13][n14] = n15;
        return this.fAttDefCount++;
    }
    
    private AttributeValidator getValidatorForAttType(final int n) {
        if (n == this.fCDATASymbol) {
            if (this.fAttValidatorCDATA == null) {
                this.fAttValidatorCDATA = (AttributeValidator)new AttValidatorCDATA();
            }
            return this.fAttValidatorCDATA;
        }
        if (n == this.fIDSymbol) {
            if (this.fAttValidatorID == null) {
                this.fAttValidatorID = (AttributeValidator)new AttValidatorID();
            }
            return this.fAttValidatorID;
        }
        if (n == this.fIDREFSymbol) {
            if (this.fAttValidatorIDREF == null) {
                this.fAttValidatorIDREF = (AttributeValidator)new AttValidatorIDREF();
            }
            return this.fAttValidatorIDREF;
        }
        if (n == this.fIDREFSSymbol) {
            if (this.fAttValidatorIDREFS == null) {
                this.fAttValidatorIDREFS = (AttributeValidator)new AttValidatorIDREFS();
            }
            return this.fAttValidatorIDREFS;
        }
        if (n == this.fENTITYSymbol) {
            if (this.fAttValidatorENTITY == null) {
                this.fAttValidatorENTITY = (AttributeValidator)new AttValidatorENTITY();
            }
            return this.fAttValidatorENTITY;
        }
        if (n == this.fENTITIESSymbol) {
            if (this.fAttValidatorENTITIES == null) {
                this.fAttValidatorENTITIES = (AttributeValidator)new AttValidatorENTITIES();
            }
            return this.fAttValidatorENTITIES;
        }
        if (n == this.fNMTOKENSymbol) {
            if (this.fAttValidatorNMTOKEN == null) {
                this.fAttValidatorNMTOKEN = (AttributeValidator)new AttValidatorNMTOKEN();
            }
            return this.fAttValidatorNMTOKEN;
        }
        if (n == this.fNMTOKENSSymbol) {
            if (this.fAttValidatorNMTOKENS == null) {
                this.fAttValidatorNMTOKENS = (AttributeValidator)new AttValidatorNMTOKENS();
            }
            return this.fAttValidatorNMTOKENS;
        }
        if (n == this.fNOTATIONSymbol) {
            if (this.fAttValidatorNOTATION == null) {
                this.fAttValidatorNOTATION = (AttributeValidator)new AttValidatorNOTATION();
            }
            return this.fAttValidatorNOTATION;
        }
        if (n == this.fENUMERATIONSymbol) {
            if (this.fAttValidatorENUMERATION == null) {
                this.fAttValidatorENUMERATION = (AttributeValidator)new AttValidatorENUMERATION();
            }
            return this.fAttValidatorENUMERATION;
        }
        throw new RuntimeException("getValidatorForAttType(" + this.fStringPool.toString(n) + ")");
    }
    
    private int getAttDef(final int n, final int n2) {
        final int declaration = this.fStringPool.getDeclaration(n);
        if (declaration == -1) {
            return -1;
        }
        int n3;
        int n4;
        for (int i = this.fAttlistHead[declaration >> 8][declaration & 0xFF]; i != -1; i = this.fNextAttDef[n3][n4]) {
            n3 = i >> 8;
            n4 = (i & 0xFF);
            if (this.fAttName[n3][n4] == n2 || this.fStringPool.equalNames(this.fAttName[n3][n4], n2)) {
                return i;
            }
        }
        return -1;
    }
    
    private boolean getAttDefIsExternal(final int n) {
        return this.fAttDefIsExternal[n >> 8][n & 0xFF] != 0;
    }
    
    private int getAttName(final int n) {
        return this.fAttName[n >> 8][n & 0xFF];
    }
    
    private int getAttValue(final int n) {
        return this.fAttValue[n >> 8][n & 0xFF];
    }
    
    private AttributeValidator getAttributeValidator(final int n) {
        return this.fAttValidator[n >> 8][n & 0xFF];
    }
    
    private int getAttType(final int n) {
        return this.fAttType[n >> 8][n & 0xFF];
    }
    
    private int getAttDefaultType(final int n) {
        return this.fAttDefaultType[n >> 8][n & 0xFF];
    }
    
    private int getEnumeration(final int n) {
        return this.fEnumeration[n >> 8][n & 0xFF];
    }
    
    private int addDefaultAttributes(final int n, final XMLAttrList list, int startAttrList, final boolean b, final boolean b2) throws Exception {
        final int n2 = n >> 8;
        final int n3 = n & 0xFF;
        int i = this.fAttlistHead[n2][n3];
        final int n4 = startAttrList;
        int n5 = -1;
        while (i != -1) {
            final int n6 = i >> 8;
            final int n7 = i & 0xFF;
            final int n8 = this.fAttName[n6][n7];
            final int n9 = this.fAttType[n6][n7];
            final int n10 = this.fAttDefaultType[n6][n7];
            final int n11 = this.fAttValue[n6][n7];
            boolean b3 = false;
            final boolean b4 = n10 == this.fREQUIREDSymbol;
            if (n4 != -1 && (n9 != this.fCDATASymbol || b4 || n11 != -1)) {
                for (int n12 = list.getFirstAttr(n4); n12 != -1 && (n5 == -1 || n12 <= n5); n12 = list.getNextAttr(n12)) {
                    if (this.fStringPool.equalNames(list.getAttrName(n12), n8)) {
                        if (b && n10 == this.fFIXEDSymbol) {
                            final int attValue = list.getAttValue(n12);
                            if (attValue != n11 && !this.fStringPool.toString(attValue).equals(this.fStringPool.toString(n11))) {
                                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 93, 87, new Object[] { this.fStringPool.toString(this.fElementType[n2][n3]), this.fStringPool.toString(n8), this.fStringPool.toString(attValue), this.fStringPool.toString(n11) }, 1);
                            }
                        }
                        b3 = true;
                        break;
                    }
                }
            }
            if (!b3) {
                if (b4) {
                    if (b) {
                        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 94, 88, new Object[] { this.fStringPool.toString(this.fElementType[n2][n3]), this.fStringPool.toString(n8) }, 1);
                    }
                }
                else if (n11 != -1) {
                    if (b && b2 && this.fAttDefIsExternal[n6][n7] != 0) {
                        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 95, 80, new Object[] { this.fStringPool.toString(this.fElementType[n2][n3]), this.fStringPool.toString(n8) }, 1);
                    }
                    if (n9 == this.fIDREFSymbol) {
                        this.addIdRef(n11);
                    }
                    else if (n9 == this.fIDREFSSymbol) {
                        final StringTokenizer stringTokenizer = new StringTokenizer(this.fStringPool.toString(n11));
                        while (stringTokenizer.hasMoreTokens()) {
                            this.addIdRef(this.fStringPool.addSymbol(stringTokenizer.nextToken()));
                        }
                    }
                    if (startAttrList == -1) {
                        startAttrList = list.startAttrList();
                    }
                    final int addAttr = list.addAttr(n8, n11, n9, false, false);
                    if (n5 == -1) {
                        n5 = addAttr;
                    }
                }
            }
            i = this.fNextAttDef[n6][n7];
        }
        return startAttrList;
    }
    
    protected boolean addId(final int n) {
        final Integer n2 = new Integer(n);
        if (this.fIdDefs == null) {
            this.fIdDefs = new Hashtable();
        }
        else if (this.fIdDefs.containsKey(n2)) {
            return false;
        }
        if (this.fNullValue == null) {
            this.fNullValue = new Object();
        }
        this.fIdDefs.put(n2, this.fNullValue);
        return true;
    }
    
    protected void addIdRef(final int n) {
        final Integer n2 = new Integer(n);
        if (this.fIdDefs != null && this.fIdDefs.containsKey(n2)) {
            return;
        }
        if (this.fIdRefs == null) {
            this.fIdRefs = new Hashtable();
        }
        else if (this.fIdRefs.containsKey(n2)) {
            return;
        }
        if (this.fNullValue == null) {
            this.fNullValue = new Object();
        }
        this.fIdRefs.put(n2, this.fNullValue);
    }
    
    private void checkIdRefs() throws Exception {
        if (this.fIdRefs == null) {
            return;
        }
        final Enumeration<Integer> keys = this.fIdRefs.keys();
        while (keys.hasMoreElements()) {
            final Integer n = keys.nextElement();
            if (this.fIdDefs == null || !this.fIdDefs.containsKey(n)) {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 81, 2, new Object[] { this.fStringPool.toString(n) }, 1);
            }
        }
    }
    
    private void checkDeclaredElements() throws Exception {
        for (int i = 0; i < this.fElementCount; ++i) {
            final int contentSpecType = this.getContentSpecType(i);
            if (contentSpecType == this.fMIXEDSymbol || contentSpecType == this.fCHILDRENSymbol) {
                this.checkDeclaredElements(i, this.fContentSpec[i >> 8][i & 0xFF]);
            }
        }
    }
    
    private void checkDeclaredElements(final int n, final int n2) throws Exception {
        int n3 = n2 >> 8;
        int n4 = n2 & 0xFF;
        final byte b = this.fNodeType[n3][n4];
        final int n5 = this.fNodeValue[n3][n4];
        switch (b) {
            case 0: {
                if (n5 != -1 && this.fStringPool.getDeclaration(n5) == -1) {
                    this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 92, 83, new Object[] { this.fStringPool.toString(this.fElementType[n >> 8][n & 0xFF]), this.fStringPool.toString(n5) }, 0);
                    return;
                }
                break;
            }
            case 1:
            case 2:
            case 3: {
                this.checkDeclaredElements(n, n5);
            }
            case 4:
            case 5: {
                this.checkDeclaredElements(n, n5);
                if (++n4 == 256) {
                    ++n3;
                    n4 = 0;
                }
                this.checkDeclaredElements(n, this.fNodeValue[n3][n4]);
            }
        }
    }
    
    final class AttValidatorCDATA implements AttributeValidator
    {
        public int normalize(final int n, final int n2, final int n3, final int n4, final int n5) throws Exception {
            return n3;
        }
    }
    
    final class AttValidatorID implements AttributeValidator
    {
        public int normalize(final int n, final int n2, int n3, final int n4, final int n5) throws Exception {
            final String string = DTDValidator.this.fStringPool.toString(n3);
            final String trim = string.trim();
            if (DTDValidator.this.fValidating) {
                if (trim != string) {
                    if (DTDValidator.this.invalidStandaloneAttDef(n, n2)) {
                        DTDValidator.this.reportRecoverableXMLError(123, 80, DTDValidator.this.fStringPool.toString(n2), string, trim);
                    }
                    n3 = DTDValidator.this.fStringPool.addSymbol(trim);
                }
                else {
                    n3 = DTDValidator.this.fStringPool.addSymbol(n3);
                }
                if (!XMLCharacterProperties.validName(trim)) {
                    DTDValidator.this.reportRecoverableXMLError(75, 76, DTDValidator.this.fStringPool.toString(n2), trim);
                }
                if (n != -1 && !DTDValidator.this.addId(n3)) {
                    DTDValidator.this.reportRecoverableXMLError(76, 76, DTDValidator.this.fStringPool.toString(n2), trim);
                }
            }
            else if (trim != string) {
                n3 = DTDValidator.this.fStringPool.addSymbol(trim);
            }
            return n3;
        }
    }
    
    final class AttValidatorIDREF implements AttributeValidator
    {
        public int normalize(final int n, final int n2, int n3, final int n4, final int n5) throws Exception {
            final String string = DTDValidator.this.fStringPool.toString(n3);
            final String trim = string.trim();
            if (DTDValidator.this.fValidating) {
                if (trim != string) {
                    if (DTDValidator.this.invalidStandaloneAttDef(n, n2)) {
                        DTDValidator.this.reportRecoverableXMLError(123, 80, DTDValidator.this.fStringPool.toString(n2), string, trim);
                    }
                    n3 = DTDValidator.this.fStringPool.addSymbol(trim);
                }
                else {
                    n3 = DTDValidator.this.fStringPool.addSymbol(n3);
                }
                if (!XMLCharacterProperties.validName(trim)) {
                    DTDValidator.this.reportRecoverableXMLError(77, 2, DTDValidator.this.fStringPool.toString(n2), trim);
                }
                if (n != -1) {
                    DTDValidator.this.addIdRef(n3);
                }
            }
            else if (trim != string) {
                n3 = DTDValidator.this.fStringPool.addSymbol(trim);
            }
            return n3;
        }
    }
    
    final class AttValidatorIDREFS implements AttributeValidator
    {
        public int normalize(final int n, final int n2, int addString, final int n3, final int n4) throws Exception {
            final String string = DTDValidator.this.fStringPool.toString(addString);
            final StringTokenizer stringTokenizer = new StringTokenizer(string);
            final StringBuffer sb = new StringBuffer(string.length());
            boolean b = true;
            if (stringTokenizer.hasMoreTokens()) {
                while (true) {
                    final String nextToken = stringTokenizer.nextToken();
                    if (DTDValidator.this.fValidating) {
                        if (!XMLCharacterProperties.validName(nextToken)) {
                            b = false;
                        }
                        if (n != -1) {
                            DTDValidator.this.addIdRef(DTDValidator.this.fStringPool.addSymbol(nextToken));
                        }
                    }
                    sb.append(nextToken);
                    if (!stringTokenizer.hasMoreTokens()) {
                        break;
                    }
                    sb.append(' ');
                }
            }
            final String string2 = sb.toString();
            if (DTDValidator.this.fValidating && (!b || string2.length() == 0)) {
                DTDValidator.this.reportRecoverableXMLError(4, 2, DTDValidator.this.fStringPool.toString(n2), string2);
            }
            if (!string2.equals(string)) {
                addString = DTDValidator.this.fStringPool.addString(string2);
                if (DTDValidator.this.fValidating && DTDValidator.this.invalidStandaloneAttDef(n, n2)) {
                    DTDValidator.this.reportRecoverableXMLError(123, 80, DTDValidator.this.fStringPool.toString(n2), string, string2);
                }
            }
            return addString;
        }
    }
    
    final class AttValidatorENTITY implements AttributeValidator
    {
        public int normalize(final int n, final int n2, int n3, final int n4, final int n5) throws Exception {
            final String string = DTDValidator.this.fStringPool.toString(n3);
            final String trim = string.trim();
            if (DTDValidator.this.fValidating) {
                if (trim != string) {
                    if (DTDValidator.this.invalidStandaloneAttDef(n, n2)) {
                        DTDValidator.this.reportRecoverableXMLError(123, 80, DTDValidator.this.fStringPool.toString(n2), string, trim);
                    }
                    n3 = DTDValidator.this.fStringPool.addSymbol(trim);
                }
                else {
                    n3 = DTDValidator.this.fStringPool.addSymbol(n3);
                }
                final int lookupEntity = DTDValidator.this.fEntityPool.lookupEntity(n3);
                if (lookupEntity == -1 || !DTDValidator.this.fEntityPool.isUnparsedEntity(lookupEntity)) {
                    DTDValidator.this.reportRecoverableXMLError(79, 77, DTDValidator.this.fStringPool.toString(n2), trim);
                }
            }
            else if (trim != string) {
                n3 = DTDValidator.this.fStringPool.addSymbol(trim);
            }
            return n3;
        }
    }
    
    final class AttValidatorENTITIES implements AttributeValidator
    {
        public int normalize(final int n, final int n2, int addString, final int n3, final int n4) throws Exception {
            final String string = DTDValidator.this.fStringPool.toString(addString);
            final StringTokenizer stringTokenizer = new StringTokenizer(string);
            final StringBuffer sb = new StringBuffer(string.length());
            boolean b = true;
            if (stringTokenizer.hasMoreTokens()) {
                while (true) {
                    final String nextToken = stringTokenizer.nextToken();
                    if (DTDValidator.this.fValidating) {
                        final int lookupEntity = DTDValidator.this.fEntityPool.lookupEntity(DTDValidator.this.fStringPool.addSymbol(nextToken));
                        if (lookupEntity == -1 || !DTDValidator.this.fEntityPool.isUnparsedEntity(lookupEntity)) {
                            b = false;
                        }
                    }
                    sb.append(nextToken);
                    if (!stringTokenizer.hasMoreTokens()) {
                        break;
                    }
                    sb.append(' ');
                }
            }
            final String string2 = sb.toString();
            if (DTDValidator.this.fValidating && (!b || string2.length() == 0)) {
                DTDValidator.this.reportRecoverableXMLError(80, 77, DTDValidator.this.fStringPool.toString(n2), string2);
            }
            if (!string2.equals(string)) {
                addString = DTDValidator.this.fStringPool.addString(string2);
                if (DTDValidator.this.fValidating && DTDValidator.this.invalidStandaloneAttDef(n, n2)) {
                    DTDValidator.this.reportRecoverableXMLError(123, 80, DTDValidator.this.fStringPool.toString(n2), string, string2);
                }
            }
            return addString;
        }
    }
    
    final class AttValidatorNMTOKEN implements AttributeValidator
    {
        public int normalize(final int n, final int n2, int n3, final int n4, final int n5) throws Exception {
            final String string = DTDValidator.this.fStringPool.toString(n3);
            final String trim = string.trim();
            if (DTDValidator.this.fValidating) {
                if (trim != string) {
                    if (DTDValidator.this.invalidStandaloneAttDef(n, n2)) {
                        DTDValidator.this.reportRecoverableXMLError(123, 80, DTDValidator.this.fStringPool.toString(n2), string, trim);
                    }
                    n3 = DTDValidator.this.fStringPool.addSymbol(trim);
                }
                else {
                    n3 = DTDValidator.this.fStringPool.addSymbol(n3);
                }
                if (!XMLCharacterProperties.validNmtoken(trim)) {
                    DTDValidator.this.reportRecoverableXMLError(78, 3, DTDValidator.this.fStringPool.toString(n2), trim);
                }
            }
            else if (trim != string) {
                n3 = DTDValidator.this.fStringPool.addSymbol(trim);
            }
            return n3;
        }
    }
    
    final class AttValidatorNMTOKENS implements AttributeValidator
    {
        public int normalize(final int n, final int n2, int addString, final int n3, final int n4) throws Exception {
            final String string = DTDValidator.this.fStringPool.toString(addString);
            final StringTokenizer stringTokenizer = new StringTokenizer(string);
            final StringBuffer sb = new StringBuffer(string.length());
            boolean b = true;
            if (stringTokenizer.hasMoreTokens()) {
                while (true) {
                    final String nextToken = stringTokenizer.nextToken();
                    if (DTDValidator.this.fValidating && !XMLCharacterProperties.validNmtoken(nextToken)) {
                        b = false;
                    }
                    sb.append(nextToken);
                    if (!stringTokenizer.hasMoreTokens()) {
                        break;
                    }
                    sb.append(' ');
                }
            }
            final String string2 = sb.toString();
            if (DTDValidator.this.fValidating && (!b || string2.length() == 0)) {
                DTDValidator.this.reportRecoverableXMLError(5, 3, DTDValidator.this.fStringPool.toString(n2), string2);
            }
            if (!string2.equals(string)) {
                addString = DTDValidator.this.fStringPool.addString(string2);
                if (DTDValidator.this.fValidating && DTDValidator.this.invalidStandaloneAttDef(n, n2)) {
                    DTDValidator.this.reportRecoverableXMLError(123, 80, DTDValidator.this.fStringPool.toString(n2), string, string2);
                }
            }
            return addString;
        }
    }
    
    final class AttValidatorNOTATION implements AttributeValidator
    {
        public int normalize(final int n, final int n2, int n3, final int n4, final int n5) throws Exception {
            final String string = DTDValidator.this.fStringPool.toString(n3);
            final String trim = string.trim();
            if (DTDValidator.this.fValidating) {
                if (trim != string) {
                    if (DTDValidator.this.invalidStandaloneAttDef(n, n2)) {
                        DTDValidator.this.reportRecoverableXMLError(123, 80, DTDValidator.this.fStringPool.toString(n2), string, trim);
                    }
                    n3 = DTDValidator.this.fStringPool.addSymbol(trim);
                }
                else {
                    n3 = DTDValidator.this.fStringPool.addSymbol(n3);
                }
                if (!DTDValidator.this.fStringPool.stringInList(n5, n3)) {
                    DTDValidator.this.reportRecoverableXMLError(90, 84, DTDValidator.this.fStringPool.toString(n2), trim, DTDValidator.this.fStringPool.stringListAsString(n5));
                }
            }
            else if (trim != string) {
                n3 = DTDValidator.this.fStringPool.addSymbol(trim);
            }
            return n3;
        }
    }
    
    final class AttValidatorENUMERATION implements AttributeValidator
    {
        public int normalize(final int n, final int n2, int n3, final int n4, final int n5) throws Exception {
            final String string = DTDValidator.this.fStringPool.toString(n3);
            final String trim = string.trim();
            if (DTDValidator.this.fValidating) {
                if (trim != string) {
                    if (DTDValidator.this.invalidStandaloneAttDef(n, n2)) {
                        DTDValidator.this.reportRecoverableXMLError(123, 80, DTDValidator.this.fStringPool.toString(n2), string, trim);
                    }
                    n3 = DTDValidator.this.fStringPool.addSymbol(trim);
                }
                else {
                    n3 = DTDValidator.this.fStringPool.addSymbol(n3);
                }
                if (!DTDValidator.this.fStringPool.stringInList(n5, n3)) {
                    DTDValidator.this.reportRecoverableXMLError(90, 86, DTDValidator.this.fStringPool.toString(n2), trim, DTDValidator.this.fStringPool.stringListAsString(n5));
                }
            }
            else if (trim != string) {
                n3 = DTDValidator.this.fStringPool.addSymbol(trim);
            }
            return n3;
        }
    }
    
    class ContentSpecImpl implements ContentSpec
    {
        public StringPool fStringPool;
        public int fHandle;
        public int fType;
        
        public String toString() {
            if (this.fType == DTDValidator.this.fMIXEDSymbol || this.fType == DTDValidator.this.fCHILDRENSymbol) {
                return DTDValidator.this.getContentSpecNodeAsString(this.fHandle);
            }
            return this.fStringPool.toString(this.fType);
        }
        
        public int getType() {
            return this.fType;
        }
        
        public int getHandle() {
            return this.fHandle;
        }
        
        public void getNode(final int n, final XMLContentSpecNode xmlContentSpecNode) {
            DTDValidator.this.getContentSpecNode(n, xmlContentSpecNode);
        }
    }
    
    public interface EventHandler
    {
        void setValidating(final boolean p0) throws Exception;
        
        void startDTD(final int p0, final int p1, final int p2) throws Exception;
        
        void endDTD() throws Exception;
        
        void elementDecl(final int p0, final ContentSpec p1) throws Exception;
        
        void attlistDecl(final int p0, final int p1, final int p2, final String p3, final int p4, final int p5) throws Exception;
        
        void internalPEDecl(final int p0, final int p1) throws Exception;
        
        void externalPEDecl(final int p0, final int p1, final int p2) throws Exception;
        
        void internalEntityDecl(final int p0, final int p1) throws Exception;
        
        void externalEntityDecl(final int p0, final int p1, final int p2) throws Exception;
        
        void unparsedEntityDecl(final int p0, final int p1, final int p2, final int p3) throws Exception;
        
        void notationDecl(final int p0, final int p1, final int p2) throws Exception;
        
        void commentInDTD(final int p0) throws Exception;
        
        void processingInstructionInDTD(final int p0, final int p1) throws Exception;
        
        void startNamespaceDeclScope(final int p0, final int p1) throws Exception;
        
        void endNamespaceDeclScope(final int p0) throws Exception;
    }
    
    interface AttributeValidator
    {
        int normalize(final int p0, final int p1, final int p2, final int p3, final int p4) throws Exception;
    }
}
