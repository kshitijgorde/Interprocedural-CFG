// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.common;

import org.apache.xerces.validators.schema.identity.Unique;
import org.apache.xerces.validators.schema.identity.KeyRef;
import org.apache.xerces.validators.schema.identity.Key;
import java.util.Vector;
import org.apache.xerces.utils.IntStack;
import org.xml.sax.SAXParseException;
import org.apache.xerces.validators.schema.SubstitutionGroupComparator;
import org.apache.xerces.validators.datatype.NOTATIONDatatypeValidator;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.apache.xerces.validators.schema.TraverseSchema;
import org.xml.sax.SAXException;
import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.EntityResolver;
import org.apache.xerces.parsers.DOMParser;
import java.util.StringTokenizer;
import java.util.Enumeration;
import org.apache.xerces.validators.schema.SchemaGrammar;
import org.xml.sax.Locator;
import org.apache.xerces.validators.dtd.DTDGrammar;
import org.apache.xerces.validators.datatype.InvalidDatatypeValueException;
import org.apache.xerces.framework.XMLContentSpec;
import org.apache.xerces.validators.schema.identity.ValueStore;
import org.apache.xerces.validators.schema.identity.XPathMatcher;
import org.apache.xerces.validators.schema.identity.Field;
import org.apache.xerces.validators.schema.identity.IdentityConstraint;
import org.apache.xerces.validators.datatype.DatatypeValidatorFactoryImpl;
import org.xml.sax.helpers.LocatorImpl;
import org.apache.xerces.framework.XMLAttrList;
import org.apache.xerces.framework.XMLDocumentHandler;
import org.apache.xerces.validators.datatype.DatatypeValidator;
import org.apache.xerces.utils.QName;
import org.apache.xerces.framework.XMLErrorReporter;
import org.apache.xerces.utils.StringPool;
import org.apache.xerces.validators.datatype.StateMessageDatatype;
import java.util.Hashtable;
import org.apache.xerces.validators.schema.identity.FieldActivator;
import org.apache.xerces.utils.NamespacesScope;
import org.apache.xerces.framework.XMLDocumentScanner;
import org.apache.xerces.readers.XMLEntityHandler;
import org.apache.xerces.readers.DefaultEntityHandler;

public final class XMLValidator implements DefaultEntityHandler.EventHandler, XMLEntityHandler.CharDataHandler, XMLDocumentScanner.EventHandler, NamespacesScope.NamespacesHandler, FieldActivator
{
    private static final boolean PRINT_EXCEPTION_STACK_TRACE = false;
    private static final boolean DEBUG_PRINT_ATTRIBUTES = false;
    private static final boolean DEBUG_PRINT_CONTENT = false;
    private static final boolean DEBUG_SCHEMA_VALIDATION = false;
    private static final boolean DEBUG_ELEMENT_CHILDREN = false;
    protected static final boolean DEBUG_IDENTITY_CONSTRAINTS = false;
    protected static final boolean DEBUG_VALUE_STORES = false;
    private static final int CHUNK_SHIFT = 8;
    private static final int CHUNK_SIZE = 256;
    private static final int CHUNK_MASK = 255;
    private static final int INITIAL_CHUNK_COUNT = 4;
    private Hashtable fIdDefs;
    private StateMessageDatatype fStoreIDRef;
    private StateMessageDatatype fResetID;
    private StateMessageDatatype fResetIDRef;
    private StateMessageDatatype fValidateIDRef;
    private StateMessageDatatype fValidateENTITYMsg;
    private AttributeValidator fAttValidatorNOTATION;
    private AttributeValidator fAttValidatorENUMERATION;
    private AttributeValidator fAttValidatorDATATYPE;
    StringPool fStringPool;
    boolean fValidating;
    boolean fInElementContent;
    int fStandaloneReader;
    private boolean fValidationEnabled;
    private boolean fDynamicValidation;
    private boolean fSchemaValidation;
    private boolean fValidationEnabledByDynamic;
    private boolean fDynamicDisabledByValidation;
    private boolean fWarningOnDuplicateAttDef;
    private boolean fWarningOnUndeclaredElements;
    private boolean fNormalizeAttributeValues;
    private boolean fLoadDTDGrammar;
    private int[] fDeclaration;
    private XMLErrorReporter fErrorReporter;
    private DefaultEntityHandler fEntityHandler;
    private QName fCurrentElement;
    private ContentLeafNameTypeVector[] fContentLeafStack;
    private int[] fValidationFlagStack;
    private int[] fScopeStack;
    private int[] fGrammarNameSpaceIndexStack;
    private int[] fElementEntityStack;
    private int[] fElementIndexStack;
    private int[] fContentSpecTypeStack;
    private static final int sizeQNameParts = 8;
    private QName[] fElementQNamePartsStack;
    private QName[] fElementChildren;
    private int fElementChildrenLength;
    private int[] fElementChildrenOffsetStack;
    private int fElementDepth;
    private boolean fNamespacesEnabled;
    private NamespacesScope fNamespacesScope;
    private int fNamespacesPrefix;
    private QName fRootElement;
    private int fAttrListHandle;
    private int fCurrentElementEntity;
    private int fCurrentElementIndex;
    private int fCurrentContentSpecType;
    private boolean fSeenDoctypeDecl;
    private final int TOP_LEVEL_SCOPE = -1;
    private int fCurrentScope;
    private int fCurrentSchemaURI;
    private int fEmptyURI;
    private int fXsiPrefix;
    private int fXsiURI;
    private int fXsiTypeAttValue;
    private DatatypeValidator fXsiTypeValidator;
    private Grammar fGrammar;
    private int fGrammarNameSpaceIndex;
    private GrammarResolver fGrammarResolver;
    private boolean fScanningDTD;
    private XMLDocumentScanner fDocumentScanner;
    private boolean fCalledStartDocument;
    private XMLDocumentHandler fDocumentHandler;
    private XMLDocumentHandler.DTDHandler fDTDHandler;
    private boolean fSeenRootElement;
    private XMLAttrList fAttrList;
    private int fXMLLang;
    private LocatorImpl fAttrNameLocator;
    private boolean fCheckedForSchema;
    private boolean fDeclsAreExternal;
    private StringPool.CharArrayRange fCurrentElementCharArrayRange;
    private char[] fCharRefData;
    private boolean fSendCharDataAsCharArray;
    private boolean fBufferDatatype;
    private StringBuffer fDatatypeBuffer;
    private QName fTempQName;
    private XMLAttributeDecl fTempAttDecl;
    private XMLAttributeDecl fTempAttributeDecl;
    private XMLElementDecl fTempElementDecl;
    private boolean fGrammarIsDTDGrammar;
    private boolean fGrammarIsSchemaGrammar;
    private boolean fNeedValidationOff;
    private static final boolean DEBUG_NORMALIZATION = false;
    private DatatypeValidator fCurrentDV;
    private boolean fFirstChunk;
    private boolean fTrailing;
    private short fWhiteSpace;
    private StringBuffer fStringBuffer;
    private StringBuffer fTempBuffer;
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
    private int fDATATYPESymbol;
    private int fEpsilonIndex;
    private DatatypeValidatorFactoryImpl fDataTypeReg;
    private DatatypeValidator fValID;
    private DatatypeValidator fValIDRef;
    private DatatypeValidator fValIDRefs;
    private DatatypeValidator fValENTITY;
    private DatatypeValidator fValENTITIES;
    private DatatypeValidator fValNMTOKEN;
    private DatatypeValidator fValNMTOKENS;
    private DatatypeValidator fValNOTATION;
    protected XPathMatcherStack fMatcherStack;
    protected ValueStoreCache fValueStoreCache;
    
    public XMLValidator(final StringPool fStringPool, final XMLErrorReporter fErrorReporter, final DefaultEntityHandler fEntityHandler, final XMLDocumentScanner fDocumentScanner) {
        this.fIdDefs = null;
        this.fStoreIDRef = new StateMessageDatatype() {
            private Hashtable fIdDefs;
            
            public Object getDatatypeObject() {
                return this.fIdDefs;
            }
            
            public int getDatatypeState() {
                return 0;
            }
            
            public void setDatatypeObject(final Object o) {
                this.fIdDefs = (Hashtable)o;
            }
        };
        this.fResetID = new StateMessageDatatype() {
            public Object getDatatypeObject() {
                return null;
            }
            
            public int getDatatypeState() {
                return 1;
            }
            
            public void setDatatypeObject(final Object o) {
            }
        };
        this.fResetIDRef = new StateMessageDatatype() {
            public Object getDatatypeObject() {
                return null;
            }
            
            public int getDatatypeState() {
                return 1;
            }
            
            public void setDatatypeObject(final Object o) {
            }
        };
        this.fValidateIDRef = new StateMessageDatatype() {
            public Object getDatatypeObject() {
                return null;
            }
            
            public int getDatatypeState() {
                return 2;
            }
            
            public void setDatatypeObject(final Object o) {
            }
        };
        this.fValidateENTITYMsg = new StateMessageDatatype() {
            private Object packagedMessage = null;
            
            public Object getDatatypeObject() {
                return this.packagedMessage;
            }
            
            public int getDatatypeState() {
                return 0;
            }
            
            public void setDatatypeObject(final Object packagedMessage) {
                this.packagedMessage = packagedMessage;
            }
        };
        this.fAttValidatorNOTATION = new AttValidatorNOTATION();
        this.fAttValidatorENUMERATION = new AttValidatorENUMERATION();
        this.fAttValidatorDATATYPE = null;
        this.fStringPool = null;
        this.fValidating = false;
        this.fInElementContent = false;
        this.fStandaloneReader = -1;
        this.fValidationEnabled = false;
        this.fDynamicValidation = false;
        this.fSchemaValidation = true;
        this.fValidationEnabledByDynamic = false;
        this.fDynamicDisabledByValidation = false;
        this.fWarningOnDuplicateAttDef = false;
        this.fWarningOnUndeclaredElements = false;
        this.fNormalizeAttributeValues = true;
        this.fLoadDTDGrammar = true;
        this.fErrorReporter = null;
        this.fEntityHandler = null;
        this.fCurrentElement = new QName();
        this.fContentLeafStack = new ContentLeafNameTypeVector[8];
        this.fValidationFlagStack = new int[8];
        this.fScopeStack = new int[8];
        this.fGrammarNameSpaceIndexStack = new int[8];
        this.fElementEntityStack = new int[8];
        this.fElementIndexStack = new int[8];
        this.fContentSpecTypeStack = new int[8];
        this.fElementQNamePartsStack = new QName[8];
        this.fElementChildren = new QName[32];
        this.fElementChildrenLength = 0;
        this.fElementChildrenOffsetStack = new int[32];
        this.fElementDepth = -1;
        this.fNamespacesEnabled = false;
        this.fNamespacesScope = null;
        this.fNamespacesPrefix = -1;
        this.fRootElement = new QName();
        this.fAttrListHandle = -1;
        this.fCurrentElementEntity = -1;
        this.fCurrentElementIndex = -1;
        this.fCurrentContentSpecType = -1;
        this.fSeenDoctypeDecl = false;
        this.fCurrentScope = -1;
        this.fCurrentSchemaURI = 0;
        this.fEmptyURI = 0;
        this.fXsiPrefix = -1;
        this.fXsiURI = -2;
        this.fXsiTypeAttValue = -1;
        this.fXsiTypeValidator = null;
        this.fGrammar = null;
        this.fGrammarNameSpaceIndex = 0;
        this.fGrammarResolver = null;
        this.fScanningDTD = false;
        this.fDocumentScanner = null;
        this.fCalledStartDocument = false;
        this.fDocumentHandler = null;
        this.fDTDHandler = null;
        this.fSeenRootElement = false;
        this.fAttrList = null;
        this.fXMLLang = -1;
        this.fAttrNameLocator = null;
        this.fCheckedForSchema = false;
        this.fDeclsAreExternal = false;
        this.fCurrentElementCharArrayRange = null;
        this.fCharRefData = null;
        this.fSendCharDataAsCharArray = false;
        this.fBufferDatatype = false;
        this.fDatatypeBuffer = new StringBuffer();
        this.fTempQName = new QName();
        this.fTempAttDecl = new XMLAttributeDecl();
        this.fTempAttributeDecl = new XMLAttributeDecl();
        this.fTempElementDecl = new XMLElementDecl();
        this.fGrammarIsDTDGrammar = false;
        this.fGrammarIsSchemaGrammar = false;
        this.fNeedValidationOff = false;
        this.fCurrentDV = null;
        this.fFirstChunk = true;
        this.fTrailing = false;
        this.fWhiteSpace = 2;
        this.fStringBuffer = new StringBuffer(256);
        this.fTempBuffer = new StringBuffer(256);
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
        this.fDATATYPESymbol = -1;
        this.fEpsilonIndex = -1;
        this.fDataTypeReg = null;
        this.fValID = null;
        this.fValIDRef = null;
        this.fValIDRefs = null;
        this.fValENTITY = null;
        this.fValENTITIES = null;
        this.fValNMTOKEN = null;
        this.fValNMTOKENS = null;
        this.fValNOTATION = null;
        this.fMatcherStack = new XPathMatcherStack();
        this.fValueStoreCache = new ValueStoreCache();
        this.fStringPool = fStringPool;
        this.fErrorReporter = fErrorReporter;
        this.fEntityHandler = fEntityHandler;
        this.fDocumentScanner = fDocumentScanner;
        this.fEmptyURI = this.fStringPool.addSymbol("");
        this.fXsiURI = this.fStringPool.addSymbol("http://www.w3.org/2000/10/XMLSchema-instance");
        this.fAttrList = new XMLAttrList(this.fStringPool);
        fEntityHandler.setEventHandler(this);
        fEntityHandler.setCharDataHandler(this);
        this.fDocumentScanner.setEventHandler(this);
        for (int i = 0; i < 8; ++i) {
            this.fElementQNamePartsStack[i] = new QName();
        }
        this.init();
    }
    
    public void setGrammarResolver(final GrammarResolver fGrammarResolver) {
        this.fGrammarResolver = fGrammarResolver;
        if (this.fValidating) {
            this.initDataTypeValidators();
        }
    }
    
    public void initHandlers(final boolean fSendCharDataAsCharArray, final XMLDocumentHandler fDocumentHandler, final XMLDocumentHandler.DTDHandler fdtdHandler) {
        this.fSendCharDataAsCharArray = fSendCharDataAsCharArray;
        this.fEntityHandler.setSendCharDataAsCharArray(this.fSendCharDataAsCharArray);
        this.fDocumentHandler = fDocumentHandler;
        this.fDTDHandler = fdtdHandler;
    }
    
    public void resetOrCopy(final StringPool stringPool) throws Exception {
        this.fAttrList = new XMLAttrList(stringPool);
        this.resetCommon(stringPool);
    }
    
    public void reset(final StringPool stringPool) throws Exception {
        this.fAttrList.reset(stringPool);
        this.resetCommon(stringPool);
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
        this.fValidating = this.fValidationEnabled;
        if (this.fValidating) {
            this.initDataTypeValidators();
        }
    }
    
    public boolean getValidationEnabled() {
        return this.fValidationEnabled;
    }
    
    public void setSchemaValidationEnabled(final boolean fSchemaValidation) {
        this.fSchemaValidation = fSchemaValidation;
    }
    
    public boolean getSchemaValidationEnabled() {
        return this.fSchemaValidation;
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
        this.fValidating = this.fValidationEnabled;
        if (this.fValidating) {
            this.initDataTypeValidators();
        }
    }
    
    public boolean getDynamicValidationEnabled() {
        return this.fDynamicValidation;
    }
    
    public void setNormalizeAttributeValues(final boolean fNormalizeAttributeValues) {
        this.fNormalizeAttributeValues = fNormalizeAttributeValues;
    }
    
    public void setLoadDTDGrammar(final boolean fLoadDTDGrammar) {
        if (this.fValidating) {
            this.fLoadDTDGrammar = true;
        }
        else {
            this.fLoadDTDGrammar = fLoadDTDGrammar;
        }
    }
    
    public boolean getLoadDTDGrammar() {
        return this.fLoadDTDGrammar;
    }
    
    public void setNamespacesEnabled(final boolean fNamespacesEnabled) {
        this.fNamespacesEnabled = fNamespacesEnabled;
    }
    
    public boolean getNamespacesEnabled() {
        return this.fNamespacesEnabled;
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
    
    public void startValueScopeFor(final IdentityConstraint identityConstraint) throws Exception {
        this.fValueStoreCache.getValueStoreFor(identityConstraint.getFieldAt(0)).startValueScope();
    }
    
    public XPathMatcher activateField(final Field field) throws Exception {
        final XPathMatcher matcher = field.createMatcher(this.fValueStoreCache.getValueStoreFor(field));
        this.fMatcherStack.addMatcher(matcher);
        matcher.startDocumentFragment(this.fStringPool, this.fNamespacesScope);
        return matcher;
    }
    
    public void endValueScopeFor(final IdentityConstraint identityConstraint) throws Exception {
        this.fValueStoreCache.getValueStoreFor(identityConstraint.getFieldAt(0)).endValueScope();
    }
    
    public void startEntityReference(final int n, final int n2, final int n3) throws Exception {
        this.fDocumentHandler.startEntityReference(n, n2, n3);
    }
    
    public void endEntityReference(final int n, final int n2, final int n3) throws Exception {
        this.fDocumentHandler.endEntityReference(n, n2, n3);
    }
    
    public void sendEndOfInputNotifications(final int n, final boolean b) throws Exception {
        this.fDocumentScanner.endOfInput(n, b);
    }
    
    public void sendReaderChangeNotifications(final XMLEntityHandler.EntityReader entityReader, final int n) throws Exception {
        this.fDocumentScanner.readerChange(entityReader, n);
    }
    
    public boolean externalEntityStandaloneCheck() {
        return this.fStandaloneReader != -1 && this.fValidating;
    }
    
    public boolean getValidating() {
        return this.fValidating;
    }
    
    private int normalizeWhitespace(final StringBuffer sb, final boolean b) {
        final int length = this.fTempBuffer.length();
        this.fStringBuffer.setLength(0);
        int n = b ? 1 : 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        for (int i = 0; i < length; ++i) {
            final char char1 = sb.charAt(i);
            if (char1 == ' ' || char1 == '\r' || char1 == '\n' || char1 == '\t') {
                if (n == 0) {
                    this.fStringBuffer.append(' ');
                    n = (b ? 1 : 0);
                }
                if (n2 == 0) {
                    n3 = 1;
                }
            }
            else {
                this.fStringBuffer.append(char1);
                n = 0;
                n2 = 1;
            }
        }
        if (n != 0) {
            int length2 = this.fStringBuffer.length();
            if (length2 != 0) {
                this.fStringBuffer.setLength(--length2);
                n4 = 2;
            }
            else if (n3 != 0 && n2 == 0) {
                n4 = 2;
            }
        }
        return b ? (n3 + n4) : 0;
    }
    
    public void processCharacters(char[] array, int n, int n2) throws Exception {
        if (this.fValidating) {
            if (this.fInElementContent || this.fCurrentContentSpecType == 0) {
                this.charDataInContent();
            }
            if (this.fBufferDatatype) {
                if (this.fFirstChunk && this.fGrammar != null) {
                    this.fGrammar.getElementDecl(this.fCurrentElementIndex, this.fTempElementDecl);
                    this.fCurrentDV = this.fTempElementDecl.datatypeValidator;
                    if (this.fCurrentDV != null) {
                        this.fWhiteSpace = this.fCurrentDV.getWSFacet();
                    }
                }
                if (this.fWhiteSpace != 0) {
                    this.fTempBuffer.setLength(0);
                    this.fTempBuffer.append(array, n, n2);
                    final int normalizeWhitespace = this.normalizeWhitespace(this.fTempBuffer, this.fWhiteSpace == 2);
                    int length = this.fStringBuffer.length();
                    if (length > 0) {
                        if (!this.fFirstChunk && this.fWhiteSpace == 2 && this.fTrailing) {
                            this.fStringBuffer.insert(0, ' ');
                            ++length;
                        }
                        if (n2 - n != length) {
                            final char[] array2 = new char[length];
                            this.fStringBuffer.getChars(0, length, array2, 0);
                            array = array2;
                            n = 0;
                            n2 = length;
                        }
                        else {
                            this.fStringBuffer.getChars(0, length, array, 0);
                        }
                        this.fDatatypeBuffer.append(array, n, n2);
                        this.fDocumentHandler.characters(array, n, n2);
                        for (int matcherCount = this.fMatcherStack.getMatcherCount(), i = 0; i < matcherCount; ++i) {
                            this.fMatcherStack.getMatcherAt(i).characters(array, n, n2);
                        }
                    }
                    this.fTrailing = (normalizeWhitespace > 1);
                    this.fFirstChunk = false;
                    return;
                }
                this.fDatatypeBuffer.append(array, n, n2);
            }
        }
        this.fFirstChunk = false;
        this.fDocumentHandler.characters(array, n, n2);
        for (int matcherCount2 = this.fMatcherStack.getMatcherCount(), j = 0; j < matcherCount2; ++j) {
            this.fMatcherStack.getMatcherAt(j).characters(array, n, n2);
        }
    }
    
    public void processCharacters(int addString) throws Exception {
        if (this.fValidating) {
            if (this.fInElementContent || this.fCurrentContentSpecType == 0) {
                this.charDataInContent();
            }
            if (this.fBufferDatatype) {
                this.fGrammar.getElementDecl(this.fCurrentElementIndex, this.fTempElementDecl);
                this.fCurrentDV = this.fTempElementDecl.datatypeValidator;
                if (this.fCurrentDV != null) {
                    this.fWhiteSpace = this.fCurrentDV.getWSFacet();
                }
                if (this.fWhiteSpace == 0) {
                    this.fDatatypeBuffer.append(this.fStringPool.toString(addString));
                }
                else {
                    final String string = this.fStringPool.toString(addString);
                    string.length();
                    this.fTempBuffer.setLength(0);
                    this.fTempBuffer.append(string);
                    this.normalizeWhitespace(this.fTempBuffer, this.fWhiteSpace == 2);
                    if (this.fWhiteSpace != 0) {
                        this.fStringPool.releaseString(addString);
                        addString = this.fStringPool.addString(this.fStringBuffer.toString());
                    }
                    this.fDatatypeBuffer.append(this.fStringBuffer.toString());
                }
            }
        }
        this.fDocumentHandler.characters(addString);
        final int matcherCount = this.fMatcherStack.getMatcherCount();
        if (matcherCount > 0) {
            final String string2 = this.fStringPool.toString(addString);
            final char[] array = new char[string2.length()];
            final int n = 0;
            final int length = array.length;
            string2.getChars(length, length, array, n);
            for (int i = 0; i < matcherCount; ++i) {
                this.fMatcherStack.getMatcherAt(i).characters(array, n, length);
            }
        }
    }
    
    public void processWhitespace(final char[] array, final int n, final int n2) throws Exception {
        if (this.fInElementContent) {
            if (this.fStandaloneReader != -1 && this.fValidating && this.getElementDeclIsExternal(this.fCurrentElementIndex)) {
                this.reportRecoverableXMLError(143, 80);
            }
            this.fDocumentHandler.ignorableWhitespace(array, n, n2);
        }
        else {
            if (this.fCurrentContentSpecType == 0) {
                this.charDataInContent();
            }
            this.fDocumentHandler.characters(array, n, n2);
            for (int matcherCount = this.fMatcherStack.getMatcherCount(), i = 0; i < matcherCount; ++i) {
                this.fMatcherStack.getMatcherAt(i).characters(array, n, n2);
            }
        }
    }
    
    public void processWhitespace(final int n) throws Exception {
        if (this.fInElementContent) {
            if (this.fStandaloneReader != -1 && this.fValidating && this.getElementDeclIsExternal(this.fCurrentElementIndex)) {
                this.reportRecoverableXMLError(143, 80);
            }
            this.fDocumentHandler.ignorableWhitespace(n);
        }
        else {
            if (this.fCurrentContentSpecType == 0) {
                this.charDataInContent();
            }
            this.fDocumentHandler.characters(n);
            final int matcherCount = this.fMatcherStack.getMatcherCount();
            if (matcherCount > 0) {
                final String string = this.fStringPool.toString(n);
                final char[] array = new char[string.length()];
                final int n2 = 0;
                final int length = array.length;
                string.getChars(length, length, array, n2);
                for (int i = 0; i < matcherCount; ++i) {
                    this.fMatcherStack.getMatcherAt(i).characters(array, n2, length);
                }
            }
        }
    }
    
    public void scanElementType(final XMLEntityHandler.EntityReader entityReader, final char c, final QName qName) throws Exception {
        if (!this.fNamespacesEnabled) {
            qName.clear();
            qName.localpart = entityReader.scanName(c);
            qName.rawname = qName.localpart;
        }
        else {
            entityReader.scanQName(c, qName);
            if (entityReader.lookingAtChar(':', false)) {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 126, 139, null, 2);
                entityReader.skipPastNmtoken(' ');
            }
        }
    }
    
    public boolean scanExpectedElementType(final XMLEntityHandler.EntityReader entityReader, final char c, final QName qName) throws Exception {
        if (this.fCurrentElementCharArrayRange == null) {
            this.fCurrentElementCharArrayRange = this.fStringPool.createCharArrayRange();
        }
        this.fStringPool.getCharArrayRange(this.fCurrentElement.rawname, this.fCurrentElementCharArrayRange);
        return entityReader.scanExpectedName(c, this.fCurrentElementCharArrayRange);
    }
    
    public void scanAttributeName(final XMLEntityHandler.EntityReader entityReader, final QName qName, final QName qName2) throws Exception {
        if (!this.fSeenRootElement) {
            this.fSeenRootElement = true;
            this.rootElementSpecified(qName);
            this.fStringPool.resetShuffleCount();
        }
        if (!this.fNamespacesEnabled) {
            qName2.clear();
            qName2.localpart = entityReader.scanName('=');
            qName2.rawname = qName2.localpart;
        }
        else {
            entityReader.scanQName('=', qName2);
            if (entityReader.lookingAtChar(':', false)) {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 126, 139, null, 2);
                entityReader.skipPastNmtoken(' ');
            }
        }
    }
    
    public void callStartDocument() throws Exception {
        if (!this.fCalledStartDocument) {
            this.fDocumentHandler.startDocument();
            this.fCalledStartDocument = true;
            if (this.fValidating) {
                this.fValueStoreCache.startDocument();
            }
        }
    }
    
    public void callEndDocument() throws Exception {
        if (this.fCalledStartDocument) {
            if (this.fValidating) {
                this.fValueStoreCache.endDocument();
            }
            this.fDocumentHandler.endDocument();
        }
    }
    
    public void callXMLDecl(final int n, final int n2, final int n3) throws Exception {
        this.fDocumentHandler.xmlDecl(n, n2, n3);
    }
    
    public void callStandaloneIsYes() throws Exception {
        this.fStandaloneReader = this.fEntityHandler.getReaderId();
    }
    
    public void callTextDecl(final int n, final int n2) throws Exception {
        this.fDocumentHandler.textDecl(n, n2);
    }
    
    public void element(final QName qName) throws Exception {
        this.fAttrListHandle = -1;
    }
    
    public boolean attribute(final QName qName, final QName qName2, final int n) throws Exception {
        if (this.fAttrListHandle == -1) {
            this.fAttrListHandle = this.fAttrList.startAttrList();
        }
        return this.fAttrList.addAttr(qName2, n, this.fCDATASymbol, true, true) == -1;
    }
    
    public void callStartElement(final QName qName) throws Exception {
        if (!this.fSeenRootElement) {
            this.fSeenRootElement = true;
            this.rootElementSpecified(qName);
            this.fStringPool.resetShuffleCount();
        }
        if (this.fGrammar != null && this.fGrammarIsDTDGrammar) {
            this.fAttrListHandle = this.addDTDDefaultAttributes(qName, this.fAttrList, this.fAttrListHandle, this.fValidating, this.fStandaloneReader != -1);
        }
        this.fCheckedForSchema = true;
        if (this.fNamespacesEnabled) {
            this.bindNamespacesToElementAndAttributes(qName, this.fAttrList);
        }
        this.validateElementAndAttributes(qName, this.fAttrList);
        if (this.fAttrListHandle != -1) {
            this.fAttrList.endAttrList();
        }
        if (this.fValidating && this.fGrammar != null) {
            this.fMatcherStack.pushContext();
            final int elementDeclIndex = this.fGrammar.getElementDeclIndex(qName, -1);
            if (elementDeclIndex != -1) {
                this.fGrammar.getElementDecl(elementDeclIndex, this.fTempElementDecl);
                this.fValueStoreCache.initValueStoresFor(this.fTempElementDecl);
                for (int size = this.fTempElementDecl.unique.size(), i = 0; i < size; ++i) {
                    this.activateSelectorFor((IdentityConstraint)this.fTempElementDecl.unique.elementAt(i));
                }
                for (int size2 = this.fTempElementDecl.key.size(), j = 0; j < size2; ++j) {
                    this.activateSelectorFor((IdentityConstraint)this.fTempElementDecl.key.elementAt(j));
                }
                for (int size3 = this.fTempElementDecl.keyRef.size(), k = 0; k < size3; ++k) {
                    this.activateSelectorFor((IdentityConstraint)this.fTempElementDecl.keyRef.elementAt(k));
                }
            }
            for (int matcherCount = this.fMatcherStack.getMatcherCount(), l = 0; l < matcherCount; ++l) {
                this.fMatcherStack.getMatcherAt(l).startElement(qName, this.fAttrList, this.fAttrListHandle);
            }
        }
        this.fDocumentHandler.startElement(qName, this.fAttrList, this.fAttrListHandle);
        ++this.fElementDepth;
        this.fAttrListHandle = -1;
        if (this.fValidating) {
            if (this.fElementChildrenOffsetStack.length <= this.fElementDepth) {
                final int[] fElementChildrenOffsetStack = new int[this.fElementChildrenOffsetStack.length * 2];
                System.arraycopy(this.fElementChildrenOffsetStack, 0, fElementChildrenOffsetStack, 0, this.fElementChildrenOffsetStack.length);
                this.fElementChildrenOffsetStack = fElementChildrenOffsetStack;
            }
            this.fElementChildrenOffsetStack[this.fElementDepth] = this.fElementChildrenLength;
            if (this.fElementChildren.length <= this.fElementChildrenLength) {
                final QName[] fElementChildren = new QName[this.fElementChildrenLength * 2];
                System.arraycopy(this.fElementChildren, 0, fElementChildren, 0, this.fElementChildren.length);
                this.fElementChildren = fElementChildren;
            }
            QName qName2 = this.fElementChildren[this.fElementChildrenLength];
            if (qName2 == null) {
                for (int fElementChildrenLength = this.fElementChildrenLength; fElementChildrenLength < this.fElementChildren.length; ++fElementChildrenLength) {
                    this.fElementChildren[fElementChildrenLength] = new QName();
                }
                qName2 = this.fElementChildren[this.fElementChildrenLength];
            }
            qName2.setValues(qName);
            ++this.fElementChildrenLength;
        }
        this.ensureStackCapacity(this.fElementDepth);
        this.fCurrentElement.setValues(qName);
        this.fCurrentElementEntity = this.fEntityHandler.getReaderId();
        this.fElementQNamePartsStack[this.fElementDepth].setValues(this.fCurrentElement);
        this.fElementEntityStack[this.fElementDepth] = this.fCurrentElementEntity;
        this.fElementIndexStack[this.fElementDepth] = this.fCurrentElementIndex;
        this.fContentSpecTypeStack[this.fElementDepth] = this.fCurrentContentSpecType;
        if (this.fNeedValidationOff) {
            this.fValidating = false;
            this.fNeedValidationOff = false;
        }
        if (this.fValidating && this.fGrammarIsSchemaGrammar) {
            this.pushContentLeafStack();
        }
        this.fValidationFlagStack[this.fElementDepth] = (this.fValidating ? 0 : -1);
        this.fScopeStack[this.fElementDepth] = this.fCurrentScope;
        this.fGrammarNameSpaceIndexStack[this.fElementDepth] = this.fGrammarNameSpaceIndex;
    }
    
    private void activateSelectorFor(final IdentityConstraint identityConstraint) throws Exception {
        final XPathMatcher matcher = identityConstraint.getSelector().createMatcher(this);
        this.fMatcherStack.addMatcher(matcher);
        matcher.startDocumentFragment(this.fStringPool, null);
    }
    
    private void pushContentLeafStack() throws Exception {
        if (this.getContentSpecType(this.fCurrentElementIndex) == 3) {
            final XMLContentModel elementContentModel = this.getElementContentModel(this.fCurrentElementIndex);
            final ContentLeafNameTypeVector contentLeafNameTypeVector = elementContentModel.getContentLeafNameTypeVector();
            if (elementContentModel != null) {
                this.fContentLeafStack[this.fElementDepth] = contentLeafNameTypeVector;
            }
        }
    }
    
    private void ensureStackCapacity(final int n) {
        if (n == this.fElementQNamePartsStack.length) {
            final int[] fScopeStack = new int[n * 2];
            System.arraycopy(this.fScopeStack, 0, fScopeStack, 0, n);
            this.fScopeStack = fScopeStack;
            final int[] fGrammarNameSpaceIndexStack = new int[n * 2];
            System.arraycopy(this.fGrammarNameSpaceIndexStack, 0, fGrammarNameSpaceIndexStack, 0, n);
            this.fGrammarNameSpaceIndexStack = fGrammarNameSpaceIndexStack;
            final QName[] fElementQNamePartsStack = new QName[n * 2];
            System.arraycopy(this.fElementQNamePartsStack, 0, fElementQNamePartsStack, 0, n);
            this.fElementQNamePartsStack = fElementQNamePartsStack;
            if (this.fElementQNamePartsStack[n] == null) {
                for (int i = n; i < this.fElementQNamePartsStack.length; ++i) {
                    this.fElementQNamePartsStack[i] = new QName();
                }
            }
            final int[] fElementEntityStack = new int[n * 2];
            System.arraycopy(this.fElementEntityStack, 0, fElementEntityStack, 0, n);
            this.fElementEntityStack = fElementEntityStack;
            final int[] fElementIndexStack = new int[n * 2];
            System.arraycopy(this.fElementIndexStack, 0, fElementIndexStack, 0, n);
            this.fElementIndexStack = fElementIndexStack;
            final int[] fContentSpecTypeStack = new int[n * 2];
            System.arraycopy(this.fContentSpecTypeStack, 0, fContentSpecTypeStack, 0, n);
            this.fContentSpecTypeStack = fContentSpecTypeStack;
            final int[] fValidationFlagStack = new int[n * 2];
            System.arraycopy(this.fValidationFlagStack, 0, fValidationFlagStack, 0, n);
            this.fValidationFlagStack = fValidationFlagStack;
            final ContentLeafNameTypeVector[] fContentLeafStack = new ContentLeafNameTypeVector[n * 2];
            System.arraycopy(this.fContentLeafStack, 0, fContentLeafStack, 0, n);
            this.fContentLeafStack = fContentLeafStack;
        }
    }
    
    public void callEndElement(final int n) throws Exception {
        final int prefix = this.fCurrentElement.prefix;
        final int rawname = this.fCurrentElement.rawname;
        if (this.fCurrentElementEntity != n) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 68, 68, new Object[] { this.fStringPool.toString(rawname) }, 2);
        }
        --this.fElementDepth;
        if (this.fValidating) {
            final int fCurrentElementIndex = this.fCurrentElementIndex;
            if (fCurrentElementIndex != -1 && this.fCurrentContentSpecType != -1) {
                final QName[] fElementChildren = this.fElementChildren;
                final int n2 = this.fElementChildrenOffsetStack[this.fElementDepth + 1] + 1;
                final int n3 = this.fElementChildrenLength - n2;
                final int checkContent = this.checkContent(fCurrentElementIndex, fElementChildren, n2, n3);
                if (checkContent != -1) {
                    final int n4 = (checkContent != n3) ? 87 : 88;
                    this.fGrammar.getElementDecl(fCurrentElementIndex, this.fTempElementDecl);
                    if (this.fTempElementDecl.type == 0) {
                        this.reportRecoverableXMLError(n4, 0, this.fStringPool.toString(rawname), "EMPTY");
                    }
                    else {
                        this.reportRecoverableXMLError(n4, 0, this.fStringPool.toString(rawname), XMLContentSpec.toString(this.fGrammar, this.fStringPool, this.fTempElementDecl.contentSpecIndex));
                    }
                }
            }
            this.fElementChildrenLength = this.fElementChildrenOffsetStack[this.fElementDepth + 1] + 1;
            final int matcherCount = this.fMatcherStack.getMatcherCount();
            for (int i = matcherCount - 1; i >= 0; --i) {
                this.fMatcherStack.getMatcherAt(i).endElement(this.fCurrentElement);
            }
            if (this.fMatcherStack.size() > 0) {
                this.fMatcherStack.popContext();
            }
            for (int matcherCount2 = this.fMatcherStack.getMatcherCount(), j = matcherCount - 1; j >= matcherCount2; --j) {
                this.fMatcherStack.getMatcherAt(j).endDocumentFragment();
            }
        }
        this.fDocumentHandler.endElement(this.fCurrentElement);
        if (this.fNamespacesEnabled) {
            this.fNamespacesScope.decreaseDepth();
        }
        if (this.fElementDepth < -1) {
            throw new RuntimeException("FWK008 Element stack underflow");
        }
        if (this.fElementDepth < 0) {
            this.fCurrentElement.clear();
            this.fCurrentElementEntity = -1;
            this.fCurrentElementIndex = -1;
            this.fCurrentContentSpecType = -1;
            this.fInElementContent = false;
            if (this.fValidating) {
                try {
                    this.fValIDRef.validate(null, this.fValidateIDRef);
                    this.fValIDRefs.validate(null, this.fValidateIDRef);
                    this.fValID.validate(null, this.fResetID);
                    this.fValIDRef.validate(null, this.fResetIDRef);
                    this.fValIDRefs.validate(null, this.fResetID);
                }
                catch (InvalidDatatypeValueException ex) {
                    this.reportRecoverableXMLError(ex.getMajorCode(), ex.getMinorCode(), ex.getMessage());
                }
            }
            return;
        }
        this.fCurrentElement.prefix = -1;
        if (this.fNamespacesEnabled) {
            this.fCurrentElement.localpart = this.fElementQNamePartsStack[this.fElementDepth].localpart;
        }
        else {
            this.fCurrentElement.localpart = this.fElementQNamePartsStack[this.fElementDepth].rawname;
        }
        this.fCurrentElement.rawname = this.fElementQNamePartsStack[this.fElementDepth].rawname;
        this.fCurrentElement.uri = this.fElementQNamePartsStack[this.fElementDepth].uri;
        this.fCurrentElement.prefix = this.fElementQNamePartsStack[this.fElementDepth].prefix;
        this.fCurrentElementEntity = this.fElementEntityStack[this.fElementDepth];
        this.fCurrentElementIndex = this.fElementIndexStack[this.fElementDepth];
        this.fCurrentContentSpecType = this.fContentSpecTypeStack[this.fElementDepth];
        this.fValidating = (this.fValidationFlagStack[this.fElementDepth] == 0);
        this.fCurrentScope = this.fScopeStack[this.fElementDepth];
        if (this.fGrammarNameSpaceIndex != this.fGrammarNameSpaceIndexStack[this.fElementDepth]) {
            this.fGrammarNameSpaceIndex = this.fGrammarNameSpaceIndexStack[this.fElementDepth];
            if (this.fValidating && this.fGrammarIsSchemaGrammar) {
                if (this.fGrammarNameSpaceIndex < 0) {
                    this.fGrammar = null;
                    this.fGrammarIsSchemaGrammar = false;
                    this.fGrammarIsDTDGrammar = false;
                }
                else if (!this.switchGrammar(this.fGrammarNameSpaceIndex)) {
                    this.reportRecoverableXMLError(169, 146, "Grammar with uri 1: " + this.fStringPool.toString(this.fGrammarNameSpaceIndex) + " , can not found");
                }
            }
        }
        if (this.fValidating) {
            this.fBufferDatatype = false;
        }
        this.fInElementContent = (this.fCurrentContentSpecType == 3);
    }
    
    public void callStartCDATA() throws Exception {
        if (this.fValidating && this.fInElementContent) {
            this.charDataInContent();
        }
        this.fDocumentHandler.startCDATA();
    }
    
    public void callEndCDATA() throws Exception {
        this.fDocumentHandler.endCDATA();
    }
    
    public void callCharacters(final int n) throws Exception {
        if (this.fCharRefData == null) {
            this.fCharRefData = new char[2];
        }
        final int n2 = (n < 65536) ? 1 : 2;
        if (n2 == 1) {
            this.fCharRefData[0] = (char)n;
        }
        else {
            this.fCharRefData[0] = (char)((n - 65536 >> 10) + 55296);
            this.fCharRefData[1] = (char)((n - 65536 & 0x3FF) + 56320);
        }
        if (this.fValidating && (this.fInElementContent || this.fCurrentContentSpecType == 0)) {
            this.charDataInContent();
        }
        if (this.fValidating && this.fBufferDatatype) {
            this.fDatatypeBuffer.append(this.fCharRefData, 0, 1);
        }
        if (this.fSendCharDataAsCharArray) {
            this.fDocumentHandler.characters(this.fCharRefData, 0, n2);
        }
        else {
            this.fDocumentHandler.characters(this.fStringPool.addString(new String(this.fCharRefData, 0, n2)));
        }
        for (int matcherCount = this.fMatcherStack.getMatcherCount(), i = 0; i < matcherCount; ++i) {
            this.fMatcherStack.getMatcherAt(i).characters(this.fCharRefData, 0, n2);
        }
    }
    
    public void callProcessingInstruction(final int n, final int n2) throws Exception {
        this.fDocumentHandler.processingInstruction(n, n2);
    }
    
    public void callComment(final int n) throws Exception {
        this.fDocumentHandler.comment(n);
    }
    
    public void startNamespaceDeclScope(final int n, final int n2) throws Exception {
        this.fDocumentHandler.startNamespaceDeclScope(n, n2);
    }
    
    public void endNamespaceDeclScope(final int n) throws Exception {
        this.fDocumentHandler.endNamespaceDeclScope(n);
    }
    
    public void setRootElementType(final QName values) {
        this.fRootElement.setValues(values);
    }
    
    private boolean getElementDeclIsExternal(final int n) {
        return this.fGrammarIsDTDGrammar && ((DTDGrammar)this.fGrammar).getElementDeclIsExternal(n);
    }
    
    public int getContentSpecType(final int n) {
        int type = -1;
        if (n > -1 && this.fGrammar.getElementDecl(n, this.fTempElementDecl)) {
            type = this.fTempElementDecl.type;
        }
        return type;
    }
    
    public int getContentSpecHandle(final int n) {
        int contentSpecIndex = -1;
        if (n > -1 && this.fGrammar.getElementDecl(n, this.fTempElementDecl)) {
            contentSpecIndex = this.fTempElementDecl.contentSpecIndex;
        }
        return contentSpecIndex;
    }
    
    private void reportSchemaError(final int n, final Object[] array) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/xml-schema-1", n, 0, array, 1);
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
    
    protected int whatCanGoHere(final int n, final boolean b, final InsertableElementsInfo insertableElementsInfo) throws Exception {
        if (insertableElementsInfo.insertAt > insertableElementsInfo.childCount || insertableElementsInfo.curChildren == null || insertableElementsInfo.childCount < 1 || insertableElementsInfo.childCount > insertableElementsInfo.curChildren.length) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.apache.org/xml/xerces.html", 13, 0, null, 2);
        }
        int whatCanGoHere;
        try {
            whatCanGoHere = this.getElementContentModel(n).whatCanGoHere(b, insertableElementsInfo);
        }
        catch (CMException ex) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.apache.org/xml/xerces.html", ex.getErrorCode(), 0, null, 2);
            throw ex;
        }
        return whatCanGoHere;
    }
    
    protected boolean getAttDefIsExternal(final QName qName, final QName qName2) {
        final int attDef = this.getAttDef(qName, qName2);
        return this.fGrammarIsDTDGrammar && ((DTDGrammar)this.fGrammar).getAttributeDeclIsExternal(attDef);
    }
    
    private boolean usingStandaloneReader() {
        return this.fStandaloneReader == -1 || this.fEntityHandler.getReaderId() == this.fStandaloneReader;
    }
    
    private LocatorImpl getLocatorImpl(final LocatorImpl locatorImpl) {
        final Locator locator = this.fErrorReporter.getLocator();
        if (locatorImpl == null) {
            return new LocatorImpl(locator);
        }
        locatorImpl.setPublicId(locator.getPublicId());
        locatorImpl.setSystemId(locator.getSystemId());
        locatorImpl.setLineNumber(locator.getLineNumber());
        locatorImpl.setColumnNumber(locator.getColumnNumber());
        return locatorImpl;
    }
    
    private void poolReset() {
        if (this.fValidating) {
            try {
                this.fValID.validate(null, this.fResetID);
                this.fValIDRef.validate(null, this.fResetIDRef);
                this.fValIDRefs.validate(null, this.fResetIDRef);
            }
            catch (InvalidDatatypeValueException ex) {
                System.err.println("Error re-Initializing: ID,IDRef,IDRefs pools");
            }
        }
    }
    
    private void resetCommon(final StringPool fStringPool) throws Exception {
        this.fStringPool = fStringPool;
        this.fValidating = this.fValidationEnabled;
        this.fValidationEnabledByDynamic = false;
        this.fDynamicDisabledByValidation = false;
        this.poolReset();
        this.fCalledStartDocument = false;
        this.fStandaloneReader = -1;
        this.fElementChildrenLength = 0;
        this.fElementDepth = -1;
        this.fSeenRootElement = false;
        this.fSeenDoctypeDecl = false;
        this.fNamespacesScope = null;
        this.fNamespacesPrefix = -1;
        this.fRootElement.clear();
        this.fAttrListHandle = -1;
        this.fCheckedForSchema = false;
        this.fCurrentScope = -1;
        this.fCurrentSchemaURI = 0;
        this.fEmptyURI = 0;
        this.fXsiPrefix = -1;
        this.fXsiTypeValidator = null;
        this.fGrammar = null;
        this.fGrammarNameSpaceIndex = 0;
        if (this.fGrammarResolver != null) {
            this.fGrammarResolver.clearGrammarResolver();
        }
        this.fGrammarIsDTDGrammar = false;
        this.fGrammarIsSchemaGrammar = false;
        this.fCurrentDV = null;
        this.fFirstChunk = true;
        this.fTrailing = false;
        this.fWhiteSpace = 2;
        this.fMatcherStack.clear();
        this.init();
    }
    
    private void init() {
        this.fEmptyURI = this.fStringPool.addSymbol("");
        this.fXsiURI = this.fStringPool.addSymbol("http://www.w3.org/2000/10/XMLSchema-instance");
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
        this.fDATATYPESymbol = this.fStringPool.addSymbol("<<datatype>>");
        this.fEpsilonIndex = this.fStringPool.addSymbol("<<CMNODE_EPSILON>>");
        this.fXMLLang = this.fStringPool.addSymbol("xml:lang");
    }
    
    private void initDataTypeValidators() {
        try {
            if (this.fGrammarResolver != null) {
                (this.fDataTypeReg = (DatatypeValidatorFactoryImpl)this.fGrammarResolver.getDatatypeRegistry()).initializeDTDRegistry();
            }
            if (this.fDataTypeReg != null) {
                this.fValID = this.fDataTypeReg.getDatatypeValidator("ID");
                this.fValIDRef = this.fDataTypeReg.getDatatypeValidator("IDREF");
                this.fValIDRefs = this.fDataTypeReg.getDatatypeValidator("IDREFS");
                this.fValENTITY = this.fDataTypeReg.getDatatypeValidator("ENTITY");
                this.fValENTITIES = this.fDataTypeReg.getDatatypeValidator("ENTITIES");
                this.fValNMTOKEN = this.fDataTypeReg.getDatatypeValidator("NMTOKEN");
                this.fValNMTOKENS = this.fDataTypeReg.getDatatypeValidator("NMTOKENS");
                this.fValNOTATION = this.fDataTypeReg.getDatatypeValidator("NOTATION");
                this.fValidateENTITYMsg.setDatatypeObject(new Object[] { this.fEntityHandler, this.fStringPool });
                this.fValENTITY.validate(null, this.fValidateENTITYMsg);
                this.fValENTITIES.validate(null, this.fValidateENTITYMsg);
            }
        }
        catch (InvalidDatatypeValueException ex) {
            System.err.println("Error: " + ex.getLocalizedMessage());
        }
    }
    
    private int addDefaultAttributes(final int n, final XMLAttrList list, int startAttrList, final boolean b, final boolean b2) throws Exception {
        this.fGrammar.getElementDecl(n, this.fTempElementDecl);
        final int localpart = this.fTempElementDecl.name.localpart;
        int i = this.fGrammar.getFirstAttributeDeclIndex(n);
        final int n2 = startAttrList;
        int n3 = -1;
        while (i != -1) {
            this.fGrammar.getAttributeDecl(i, this.fTempAttDecl);
            final int prefix = this.fTempAttDecl.name.prefix;
            final int localpart2 = this.fTempAttDecl.name.localpart;
            final int attributeTypeName = this.attributeTypeName(this.fTempAttDecl);
            final int defaultType = this.fTempAttDecl.defaultType;
            int addSymbol = -1;
            if (this.fTempAttDecl.defaultValue != null) {
                addSymbol = this.fStringPool.addSymbol(this.fTempAttDecl.defaultValue);
            }
            boolean b3 = false;
            final boolean b4 = defaultType == 2;
            final boolean b5 = defaultType == 7;
            final boolean b6 = defaultType == 8;
            if (n2 != -1 && (attributeTypeName != this.fCDATASymbol || b4 || b5 || addSymbol != -1 || b6)) {
                for (int n4 = list.getFirstAttr(n2); n4 != -1 && (n3 == -1 || n4 <= n3); n4 = list.getNextAttr(n4)) {
                    if ((this.fGrammarIsDTDGrammar && list.getAttrName(n4) == this.fTempAttDecl.name.rawname) || (this.fStringPool.equalNames(list.getAttrLocalpart(n4), localpart2) && this.fStringPool.equalNames(list.getAttrURI(n4), this.fTempAttDecl.name.uri))) {
                        if (b5 && b) {
                            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/xml-schema-1", 28, 0, new Object[] { this.fStringPool.toString(localpart), this.fStringPool.toString(localpart2) }, 1);
                        }
                        if (b && (defaultType == 1 || b6)) {
                            final int attValue = list.getAttValue(n4);
                            if (attValue != addSymbol && !this.fStringPool.toString(attValue).equals(this.fStringPool.toString(addSymbol))) {
                                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 93, 87, new Object[] { this.fStringPool.toString(localpart), this.fStringPool.toString(localpart2), this.fStringPool.toString(attValue), this.fStringPool.toString(addSymbol) }, 1);
                            }
                        }
                        b3 = true;
                        break;
                    }
                }
            }
            if (!b3) {
                if (b4 || b6) {
                    if (b) {
                        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 94, 88, new Object[] { this.fStringPool.toString(localpart), this.fStringPool.toString(localpart2) }, 1);
                    }
                }
                else if (addSymbol != -1) {
                    if (b && b2 && this.fGrammarIsDTDGrammar && ((DTDGrammar)this.fGrammar).getAttributeDeclIsExternal(i)) {
                        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 95, 80, new Object[] { this.fStringPool.toString(localpart), this.fStringPool.toString(localpart2) }, 1);
                    }
                    if (b) {
                        if (attributeTypeName == this.fIDREFSymbol) {
                            this.fValIDRef.validate(this.fStringPool.toString(addSymbol), this.fStoreIDRef);
                        }
                        else if (attributeTypeName == this.fIDREFSSymbol) {
                            this.fValIDRefs.validate(this.fStringPool.toString(addSymbol), this.fStoreIDRef);
                        }
                    }
                    if (startAttrList == -1) {
                        startAttrList = list.startAttrList();
                    }
                    this.fTempQName.setValues(prefix, localpart2, localpart2, this.fTempAttDecl.name.uri);
                    final int addAttr = list.addAttr(this.fTempQName, addSymbol, attributeTypeName, false, false);
                    if (n3 == -1) {
                        n3 = addAttr;
                    }
                }
            }
            i = this.fGrammar.getNextAttributeDeclIndex(i);
        }
        return startAttrList;
    }
    
    private int addDTDDefaultAttributes(final QName qName, final XMLAttrList list, int startAttrList, final boolean b, final boolean b2) throws Exception {
        final int elementDeclIndex = this.fGrammar.getElementDeclIndex(qName, -1);
        if (elementDeclIndex == -1) {
            return startAttrList;
        }
        this.fGrammar.getElementDecl(elementDeclIndex, this.fTempElementDecl);
        final int rawname = this.fTempElementDecl.name.rawname;
        int i = this.fGrammar.getFirstAttributeDeclIndex(elementDeclIndex);
        final int n = startAttrList;
        int n2 = -1;
        while (i != -1) {
            this.fGrammar.getAttributeDecl(i, this.fTempAttDecl);
            final int prefix = this.fTempAttDecl.name.prefix;
            final int rawname2 = this.fTempAttDecl.name.rawname;
            final int localpart = this.fTempAttDecl.name.localpart;
            final int attributeTypeName = this.attributeTypeName(this.fTempAttDecl);
            final int defaultType = this.fTempAttDecl.defaultType;
            int addSymbol = -1;
            if (this.fTempAttDecl.defaultValue != null) {
                addSymbol = this.fStringPool.addSymbol(this.fTempAttDecl.defaultValue);
            }
            boolean b3 = false;
            final boolean b4 = defaultType == 2;
            if (n != -1 && (attributeTypeName != this.fCDATASymbol || b4 || addSymbol != -1)) {
                for (int n3 = list.getFirstAttr(n); n3 != -1 && (n2 == -1 || n3 <= n2); n3 = list.getNextAttr(n3)) {
                    if (list.getAttrName(n3) == this.fTempAttDecl.name.rawname) {
                        if (b && defaultType == 1) {
                            final int attValue = list.getAttValue(n3);
                            if (attValue != addSymbol && !this.fStringPool.toString(attValue).equals(this.fStringPool.toString(addSymbol))) {
                                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 93, 87, new Object[] { this.fStringPool.toString(rawname), this.fStringPool.toString(rawname2), this.fStringPool.toString(attValue), this.fStringPool.toString(addSymbol) }, 1);
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
                        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 94, 88, new Object[] { this.fStringPool.toString(rawname), this.fStringPool.toString(rawname2) }, 1);
                    }
                }
                else if (addSymbol != -1) {
                    if (b && b2 && this.fGrammarIsDTDGrammar && ((DTDGrammar)this.fGrammar).getAttributeDeclIsExternal(i)) {
                        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 95, 80, new Object[] { this.fStringPool.toString(rawname), this.fStringPool.toString(rawname2) }, 1);
                    }
                    if (b) {
                        if (attributeTypeName == this.fIDREFSymbol) {
                            this.fValIDRef.validate(this.fStringPool.toString(addSymbol), this.fStoreIDRef);
                        }
                        else if (attributeTypeName == this.fIDREFSSymbol) {
                            this.fValIDRefs.validate(this.fStringPool.toString(addSymbol), this.fStoreIDRef);
                        }
                    }
                    if (startAttrList == -1) {
                        startAttrList = list.startAttrList();
                    }
                    this.fTempQName.setValues(prefix, localpart, rawname2, this.fTempAttDecl.name.uri);
                    final int addAttr = list.addAttr(this.fTempQName, addSymbol, attributeTypeName, false, false);
                    if (n2 == -1) {
                        n2 = addAttr;
                    }
                }
            }
            i = this.fGrammar.getNextAttributeDeclIndex(i);
        }
        return startAttrList;
    }
    
    private XMLContentModel getElementContentModel(final int n) throws CMException {
        XMLContentModel elementContentModel = null;
        if (n > -1 && this.fGrammar.getElementDecl(n, this.fTempElementDecl)) {
            elementContentModel = this.fGrammar.getElementContentModel(n);
        }
        return elementContentModel;
    }
    
    private int getAttDef(final QName qName, final QName qName2) {
        if (this.fGrammar != null) {
            int fCurrentScope = this.fCurrentScope;
            if (qName.uri > -1) {
                fCurrentScope = -1;
            }
            final int elementDeclIndex = this.fGrammar.getElementDeclIndex(qName, fCurrentScope);
            if (elementDeclIndex == -1) {
                return -1;
            }
            for (int i = this.fGrammar.getFirstAttributeDeclIndex(elementDeclIndex); i != -1; i = this.fGrammar.getNextAttributeDeclIndex(i)) {
                this.fGrammar.getAttributeDecl(i, this.fTempAttributeDecl);
                if (this.fTempAttributeDecl.name.localpart == qName2.localpart && this.fTempAttributeDecl.name.uri == qName2.uri) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    private int getAttDefByElementIndex(final int n, final QName qName) {
        if (this.fGrammar != null && n > -1) {
            if (n == -1) {
                return -1;
            }
            for (int i = this.fGrammar.getFirstAttributeDeclIndex(n); i != -1; i = this.fGrammar.getNextAttributeDeclIndex(i)) {
                this.fGrammar.getAttributeDecl(i, this.fTempAttDecl);
                if (this.fGrammarIsDTDGrammar) {
                    if (this.fTempAttDecl.name.rawname == qName.rawname) {
                        return i;
                    }
                }
                else if (this.fTempAttDecl.name.localpart == qName.localpart && this.fTempAttDecl.name.uri == qName.uri) {
                    return i;
                }
                if (this.fGrammarIsSchemaGrammar) {
                    if (this.fTempAttDecl.type == 8) {
                        return i;
                    }
                    if (this.fTempAttDecl.type == 10) {
                        if (qName.uri == 0) {
                            return i;
                        }
                    }
                    else if (this.fTempAttDecl.type == 9) {
                        if (qName.uri != this.fTempAttDecl.name.uri) {
                            return i;
                        }
                    }
                    else if (this.fTempAttDecl.type == 11 && this.fStringPool.stringInList(this.fTempAttDecl.enumeration, qName.uri)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    
    private void rootElementSpecified(final QName qName) throws Exception {
        if (this.fLoadDTDGrammar && this.fGrammar == null) {
            this.fGrammar = this.fGrammarResolver.getGrammar("");
            if (this.fDynamicValidation && this.fGrammar == null) {
                this.fValidating = false;
            }
            if (this.fGrammar != null) {
                if (this.fGrammar instanceof DTDGrammar) {
                    this.fGrammarIsDTDGrammar = true;
                    this.fGrammarIsSchemaGrammar = false;
                }
                else if (this.fGrammar instanceof SchemaGrammar) {
                    this.fGrammarIsSchemaGrammar = true;
                    this.fGrammarIsDTDGrammar = false;
                }
                this.fGrammarNameSpaceIndex = this.fEmptyURI;
            }
        }
        if (this.fValidating && this.fGrammarIsDTDGrammar && ((DTDGrammar)this.fGrammar).getRootElementQName(this.fRootElement) && !this.fStringPool.toString(this.fRootElement.rawname).equals(this.fStringPool.toString(qName.rawname))) {
            this.reportRecoverableXMLError(3, 1, this.fRootElement.rawname, qName.rawname);
        }
        if (this.fNamespacesEnabled && this.fNamespacesScope == null) {
            this.fNamespacesScope = new NamespacesScope(this);
            this.fNamespacesPrefix = this.fStringPool.addSymbol("xmlns");
            this.fNamespacesScope.setNamespaceForPrefix(this.fNamespacesPrefix, -1);
            this.fNamespacesScope.setNamespaceForPrefix(this.fStringPool.addSymbol("xml"), this.fStringPool.addSymbol("http://www.w3.org/XML/1998/namespace"));
        }
    }
    
    private boolean switchGrammar(final int n) throws Exception {
        Grammar fGrammar = this.fGrammarResolver.getGrammar(this.fStringPool.toString(n));
        if (fGrammar == null) {
            fGrammar = this.fGrammarResolver.getGrammar("");
        }
        if (fGrammar == null) {
            return false;
        }
        this.fGrammar = fGrammar;
        if (this.fGrammar instanceof DTDGrammar) {
            this.fGrammarIsDTDGrammar = true;
            this.fGrammarIsSchemaGrammar = false;
        }
        else if (this.fGrammar instanceof SchemaGrammar) {
            this.fGrammarIsSchemaGrammar = true;
            this.fGrammarIsDTDGrammar = false;
        }
        return true;
    }
    
    private void bindNamespacesToElementAndAttributes(final QName qName, final XMLAttrList list) throws Exception {
        this.fNamespacesScope.increaseDepth();
        Hashtable<String, String> hashtable = null;
        if (this.fAttrListHandle != -1) {
            for (int i = list.getFirstAttr(this.fAttrListHandle); i != -1; i = list.getNextAttr(i)) {
                final int attrName = list.getAttrName(i);
                final int attrPrefix = list.getAttrPrefix(i);
                if (!this.fStringPool.equalNames(attrName, this.fXMLLang)) {
                    if (this.fStringPool.equalNames(attrName, this.fNamespacesPrefix)) {
                        this.fNamespacesScope.setNamespaceForPrefix(0, this.fStringPool.addSymbol(list.getAttValue(i)));
                    }
                    else if (attrPrefix == this.fNamespacesPrefix) {
                        final int attrLocalpart = list.getAttrLocalpart(i);
                        this.fNamespacesScope.setNamespaceForPrefix(attrLocalpart, this.fStringPool.addSymbol(list.getAttValue(i)));
                        if (this.fValidating && this.fSchemaValidation) {
                            boolean b = false;
                            if (this.fStringPool.toString(list.getAttValue(i)).equals("http://www.w3.org/2000/10/XMLSchema-instance")) {
                                this.fXsiPrefix = attrLocalpart;
                                b = true;
                            }
                            if (!b) {}
                        }
                    }
                }
            }
            if (this.fValidating && this.fSchemaValidation) {
                this.fXsiTypeAttValue = -1;
                for (int j = list.getFirstAttr(this.fAttrListHandle); j != -1; j = list.getNextAttr(j)) {
                    final int attrName2 = list.getAttrName(j);
                    final int attrPrefix2 = list.getAttrPrefix(j);
                    if (!this.fStringPool.equalNames(attrName2, this.fNamespacesPrefix)) {
                        if (this.fXsiPrefix != -1 && attrPrefix2 == this.fXsiPrefix) {
                            final int attrLocalpart2 = list.getAttrLocalpart(j);
                            if (attrLocalpart2 == this.fStringPool.addSymbol("schemaLocation")) {
                                if (hashtable == null) {
                                    hashtable = new Hashtable<String, String>();
                                }
                                this.parseSchemaLocation(this.fStringPool.toString(list.getAttValue(j)), hashtable);
                            }
                            else if (attrLocalpart2 == this.fStringPool.addSymbol("noNamespaceSchemaLocation")) {
                                if (hashtable == null) {
                                    hashtable = new Hashtable<String, String>();
                                }
                                hashtable.put(this.fStringPool.toString(list.getAttValue(j)), "");
                                if (this.fNamespacesScope != null) {
                                    this.fNamespacesScope.setNamespaceForPrefix(this.fStringPool.addSymbol(""), this.fStringPool.addSymbol(""));
                                }
                            }
                            else if (attrLocalpart2 == this.fStringPool.addSymbol("type")) {
                                this.fXsiTypeAttValue = list.getAttValue(j);
                            }
                        }
                    }
                }
                if (hashtable != null) {
                    final Enumeration<String> keys = hashtable.keys();
                    while (keys.hasMoreElements()) {
                        final String s = keys.nextElement();
                        this.resolveSchemaGrammar(s, hashtable.get(s));
                    }
                }
            }
        }
        final int namespaceForPrefix = this.fNamespacesScope.getNamespaceForPrefix((qName.prefix != -1) ? qName.prefix : 0);
        if (qName.prefix != -1 || namespaceForPrefix != 0) {
            qName.uri = namespaceForPrefix;
            if (qName.uri == 0) {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1999/REC-xml-names-19990114", 167, 144, new Object[] { this.fStringPool.toString(qName.prefix) }, 1);
            }
        }
        if (this.fAttrListHandle != -1) {
            for (int k = list.getFirstAttr(this.fAttrListHandle); k != -1; k = list.getNextAttr(k)) {
                if (!this.fStringPool.equalNames(list.getAttrName(k), this.fNamespacesPrefix)) {
                    final int attrPrefix3 = list.getAttrPrefix(k);
                    if (attrPrefix3 != this.fNamespacesPrefix && attrPrefix3 != -1) {
                        final int namespaceForPrefix2 = this.fNamespacesScope.getNamespaceForPrefix(attrPrefix3);
                        if (namespaceForPrefix2 == -1) {
                            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1999/REC-xml-names-19990114", 167, 144, new Object[] { this.fStringPool.toString(attrPrefix3) }, 1);
                        }
                        list.setAttrURI(k, namespaceForPrefix2);
                    }
                }
            }
        }
    }
    
    void parseSchemaLocation(final String s, final Hashtable hashtable) {
        if (hashtable != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, " \n\t\r", false);
            if (stringTokenizer.countTokens() % 2 == 0) {
                while (stringTokenizer.hasMoreTokens()) {
                    hashtable.put(stringTokenizer.nextToken(), stringTokenizer.nextToken());
                }
            }
        }
    }
    
    private void resolveSchemaGrammar(String expandSystemId, final String s) throws Exception {
        if (this.fGrammarResolver.getGrammar(s) == null) {
            final DOMParser domParser = new DOMParser();
            domParser.setEntityResolver(new Resolver(this.fEntityHandler));
            domParser.setErrorHandler(new ErrorHandler());
            try {
                domParser.setFeature("http://xml.org/sax/features/validation", false);
                domParser.setFeature("http://xml.org/sax/features/namespaces", true);
                domParser.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false);
            }
            catch (SAXNotRecognizedException ex) {
                ex.printStackTrace();
            }
            catch (SAXNotSupportedException ex2) {
                ex2.printStackTrace();
            }
            InputSource resolveEntity = null;
            final EntityResolver entityResolver = domParser.getEntityResolver();
            if (entityResolver != null) {
                resolveEntity = entityResolver.resolveEntity("", expandSystemId);
            }
            if (resolveEntity == null) {
                expandSystemId = this.fEntityHandler.expandSystemId(expandSystemId);
                resolveEntity = new InputSource(expandSystemId);
            }
            try {
                domParser.parse(resolveEntity);
            }
            catch (IOException ex3) {
                ex3.printStackTrace();
            }
            catch (SAXException ex4) {
                this.reportRecoverableXMLError(169, 146, ex4.getMessage());
            }
            final Document document = domParser.getDocument();
            try {
                Element documentElement = null;
                if (document != null) {
                    documentElement = document.getDocumentElement();
                }
                if (documentElement == null) {
                    this.reportRecoverableXMLError(169, 146, "Can't get back Schema document's root element :" + expandSystemId);
                }
                else {
                    if (s == null || !s.equals(documentElement.getAttribute("targetNamespace"))) {
                        this.reportRecoverableXMLError(169, 146, "Schema in " + expandSystemId + " has a different target namespace " + "from the one specified in the instance document :" + s);
                    }
                    final SchemaGrammar fGrammar = new SchemaGrammar();
                    fGrammar.setGrammarDocument(document);
                    this.fGrammarIsSchemaGrammar = true;
                    this.fGrammarIsDTDGrammar = false;
                    final TraverseSchema traverseSchema = new TraverseSchema(documentElement, this.fStringPool, fGrammar, this.fGrammarResolver, this.fErrorReporter, resolveEntity.getSystemId(), entityResolver);
                    this.fGrammarResolver.putGrammar(documentElement.getAttribute("targetNamespace"), fGrammar);
                    this.fGrammar = fGrammar;
                }
            }
            catch (Exception ex5) {
                ex5.printStackTrace(System.err);
            }
        }
    }
    
    private void resolveSchemaGrammar(final String s) throws Exception {
        this.resolveSchemaGrammar(s, s);
    }
    
    private String bindNotationURI(final String s) throws Exception {
        final int index = s.indexOf(":");
        String substring = "";
        String substring2 = s;
        if (index > -1) {
            substring = s.substring(0, index);
            substring2 = s.substring(index + 1);
        }
        if (this.fNamespacesScope != null) {
            final int namespaceForPrefix = this.fNamespacesScope.getNamespaceForPrefix(this.fStringPool.addSymbol(substring));
            if (namespaceForPrefix > 0) {
                return this.fStringPool.toString(namespaceForPrefix) + ":" + substring2;
            }
            if (this.fGrammarNameSpaceIndex != -1) {
                return this.fStringPool.toString(this.fGrammarNameSpaceIndex) + ":" + substring2;
            }
        }
        return s;
    }
    
    private int attributeTypeName(final XMLAttributeDecl xmlAttributeDecl) {
        switch (xmlAttributeDecl.type) {
            case 1: {
                return xmlAttributeDecl.list ? this.fENTITIESSymbol : this.fENTITYSymbol;
            }
            case 2: {
                return this.fStringPool.addSymbol(this.fStringPool.stringListAsString(xmlAttributeDecl.enumeration));
            }
            case 3: {
                return this.fIDSymbol;
            }
            case 4: {
                return xmlAttributeDecl.list ? this.fIDREFSSymbol : this.fIDREFSymbol;
            }
            case 5: {
                return xmlAttributeDecl.list ? this.fNMTOKENSSymbol : this.fNMTOKENSSymbol;
            }
            case 6: {
                return this.fNOTATIONSymbol;
            }
            default: {
                return this.fCDATASymbol;
            }
        }
    }
    
    private void validateElementAndAttributes(final QName qName, final XMLAttrList list) throws Exception {
        if ((this.fGrammarIsSchemaGrammar && this.fElementDepth >= 0 && this.fValidationFlagStack[this.fElementDepth] != 0) || (this.fGrammar == null && !this.fValidating && !this.fNamespacesEnabled)) {
            this.fCurrentElementIndex = -1;
            this.fCurrentContentSpecType = -1;
            this.fInElementContent = false;
            if (this.fAttrListHandle != -1) {
                this.fAttrList.endAttrList();
                for (int i = this.fAttrList.getFirstAttr(this.fAttrListHandle); i != -1; i = this.fAttrList.getNextAttr(i)) {
                    if (this.fStringPool.equalNames(this.fAttrList.getAttrName(i), this.fXMLLang)) {
                        this.fDocumentScanner.checkXMLLangAttributeValue(this.fAttrList.getAttValue(i));
                        break;
                    }
                }
            }
            return;
        }
        int fCurrentElementIndex = -1;
        int contentSpecType = -1;
        boolean b = false;
        boolean b2 = false;
        if (this.fGrammarIsSchemaGrammar && this.fElementDepth > -1 && this.fContentLeafStack[this.fElementDepth] != null) {
            final ContentLeafNameTypeVector contentLeafNameTypeVector = this.fContentLeafStack[this.fElementDepth];
            final QName[] leafNames = contentLeafNameTypeVector.leafNames;
            for (int j = 0; j < contentLeafNameTypeVector.leafCount; ++j) {
                final int n = contentLeafNameTypeVector.leafTypes[j];
                if (n == 0) {
                    if (leafNames[j].uri == qName.uri && leafNames[j].localpart == qName.localpart) {
                        break;
                    }
                }
                else if (n == 6) {
                    final int uri = leafNames[j].uri;
                    if (uri == 0) {
                        break;
                    }
                    if (uri == qName.uri) {
                        break;
                    }
                }
                else if (n == 8) {
                    if (qName.uri == 0) {
                        break;
                    }
                }
                else if (n == 7) {
                    if (leafNames[j].uri != qName.uri) {
                        break;
                    }
                }
                else if (n == 38) {
                    final int uri2 = leafNames[j].uri;
                    if (uri2 == 0 || uri2 == qName.uri) {
                        b = true;
                        break;
                    }
                }
                else if (n == 40) {
                    if (qName.uri == 0) {
                        b = true;
                        break;
                    }
                }
                else if (n == 39) {
                    if (leafNames[j].uri != qName.uri) {
                        b = true;
                        break;
                    }
                }
                else if (n == 22) {
                    final int uri3 = leafNames[j].uri;
                    if (uri3 == 0 || uri3 == qName.uri) {
                        b2 = true;
                        break;
                    }
                }
                else if (n == 24) {
                    if (qName.uri == 0) {
                        b2 = true;
                        break;
                    }
                }
                else if (n == 23 && leafNames[j].uri != qName.uri) {
                    b2 = true;
                    break;
                }
            }
        }
        if (b) {
            this.fNeedValidationOff = true;
        }
        else {
            if (this.fNamespacesEnabled && this.fValidating && qName.uri != this.fGrammarNameSpaceIndex && qName.uri != 0) {
                this.fGrammarNameSpaceIndex = qName.uri;
                if (!this.switchGrammar(this.fGrammarNameSpaceIndex) && !b2) {
                    this.reportRecoverableXMLError(169, 146, "Grammar with uri 2: " + this.fStringPool.toString(this.fGrammarNameSpaceIndex) + " , can not found");
                }
            }
            if (this.fGrammar != null) {
                fCurrentElementIndex = this.fGrammar.getElementDeclIndex(qName, this.fCurrentScope);
                if (fCurrentElementIndex == -1) {
                    fCurrentElementIndex = this.fGrammar.getElementDeclIndex(qName, -1);
                }
                if (fCurrentElementIndex == -1) {
                    if (this.fGrammarIsSchemaGrammar && this.fCurrentElementIndex != -1) {
                        TraverseSchema.ComplexTypeInfo complexTypeInfo = ((SchemaGrammar)this.fGrammar).getElementComplexTypeInfo(this.fCurrentElementIndex);
                        int fGrammarNameSpaceIndex = this.fGrammarNameSpaceIndex;
                        while (complexTypeInfo != null) {
                            fCurrentElementIndex = this.fGrammar.getElementDeclIndex(qName, complexTypeInfo.scopeDefined);
                            if (fCurrentElementIndex > -1) {
                                this.fGrammarNameSpaceIndex = fGrammarNameSpaceIndex;
                                break;
                            }
                            complexTypeInfo = complexTypeInfo.baseComplexTypeInfo;
                            if (complexTypeInfo == null) {
                                continue;
                            }
                            final String typeName = complexTypeInfo.typeName;
                            if (typeName.startsWith("#")) {
                                continue;
                            }
                            fGrammarNameSpaceIndex = this.fStringPool.addSymbol(typeName.substring(0, typeName.indexOf(44)).trim());
                            if (fGrammarNameSpaceIndex != this.fGrammarNameSpaceIndex && !this.switchGrammar(fGrammarNameSpaceIndex)) {
                                break;
                            }
                        }
                        if (fCurrentElementIndex == -1) {
                            this.switchGrammar(this.fGrammarNameSpaceIndex);
                        }
                    }
                    if (qName.uri == 0 && fCurrentElementIndex == -1 && this.fNamespacesScope != null && this.fNamespacesScope.getNamespaceForPrefix(0) != 0) {
                        fCurrentElementIndex = this.fGrammar.getElementDeclIndex(qName.localpart, -1);
                        qName.uri = 0;
                    }
                    if (fCurrentElementIndex == -1 && b2) {
                        this.fNeedValidationOff = true;
                    }
                }
            }
            final int contentSpecType2 = this.getContentSpecType(fCurrentElementIndex);
            if (this.fGrammarIsSchemaGrammar && fCurrentElementIndex != -1) {
                if (this.fXsiTypeAttValue > -1) {
                    final String string = this.fStringPool.toString(this.fXsiTypeAttValue);
                    final int index = string.indexOf(":");
                    String substring = "";
                    String substring2 = string;
                    if (index > -1) {
                        substring = string.substring(0, index);
                        substring2 = string.substring(index + 1);
                    }
                    String string2 = "";
                    if (this.fNamespacesScope != null) {
                        final int namespaceForPrefix = this.fNamespacesScope.getNamespaceForPrefix(this.fStringPool.addSymbol(substring));
                        if (namespaceForPrefix > 0) {
                            string2 = this.fStringPool.toString(namespaceForPrefix);
                            if (namespaceForPrefix != this.fGrammarNameSpaceIndex) {
                                final int n2 = namespaceForPrefix;
                                this.fCurrentSchemaURI = n2;
                                this.fGrammarNameSpaceIndex = n2;
                                if (!this.switchGrammar(this.fCurrentSchemaURI) && !this.fNeedValidationOff) {
                                    this.reportRecoverableXMLError(169, 146, "Grammar with uri 3: " + this.fStringPool.toString(this.fCurrentSchemaURI) + " , can not found");
                                }
                            }
                        }
                    }
                    final Hashtable complexTypeRegistry = ((SchemaGrammar)this.fGrammar).getComplexTypeRegistry();
                    final DatatypeValidatorFactoryImpl datatypeRegistry = ((SchemaGrammar)this.fGrammar).getDatatypeRegistry();
                    if (complexTypeRegistry == null || datatypeRegistry == null) {
                        this.reportRecoverableXMLError(169, 146, this.fErrorReporter.getLocator().getSystemId() + " line" + this.fErrorReporter.getLocator().getLineNumber() + ", canot resolve xsi:type = " + string + "  ---2");
                    }
                    else {
                        final TraverseSchema.ComplexTypeInfo complexTypeInfo2 = complexTypeRegistry.get(string2 + "," + substring2);
                        if (complexTypeInfo2 == null) {
                            if (string2.length() == 0 || string2.equals("http://www.w3.org/2000/10/XMLSchema")) {
                                this.fXsiTypeValidator = datatypeRegistry.getDatatypeValidator(substring2);
                            }
                            else {
                                this.fXsiTypeValidator = datatypeRegistry.getDatatypeValidator(string2 + "," + substring2);
                            }
                            if (this.fXsiTypeValidator == null) {
                                this.reportRecoverableXMLError(169, 146, "unresolved type : " + string2 + "," + substring2 + " found  in xsi:type handling");
                            }
                        }
                        else {
                            if (complexTypeInfo2.isAbstract) {
                                this.reportRecoverableXMLError(169, 146, "Abstract type " + string + " should not be used in xsi:type");
                            }
                            fCurrentElementIndex = complexTypeInfo2.templateElementIndex;
                        }
                    }
                    this.fXsiTypeAttValue = -1;
                }
                else {
                    final TraverseSchema.ComplexTypeInfo elementComplexTypeInfo = ((SchemaGrammar)this.fGrammar).getElementComplexTypeInfo(fCurrentElementIndex);
                    if (elementComplexTypeInfo != null && elementComplexTypeInfo.isAbstract) {
                        this.reportRecoverableXMLError(169, 146, "Element " + this.fStringPool.toString(qName.rawname) + " is declared with a type that is abstract.  Use xsi:type to specify a non-abstract type");
                    }
                }
                if ((((SchemaGrammar)this.fGrammar).getElementDeclMiscFlags(fCurrentElementIndex) & 0x2) != 0x0) {
                    this.reportRecoverableXMLError(169, 146, "A member of abstract element " + this.fStringPool.toString(qName.rawname) + "'s substitution group must be specified");
                }
                this.fCurrentScope = ((SchemaGrammar)this.fGrammar).getElementDefinedScope(fCurrentElementIndex);
                final String elementFromAnotherSchemaURI = ((SchemaGrammar)this.fGrammar).getElementFromAnotherSchemaURI(fCurrentElementIndex);
                if (elementFromAnotherSchemaURI != null) {
                    if (contentSpecType2 != -1 && contentSpecType2 != 0) {
                        final TraverseSchema.ComplexTypeInfo elementComplexTypeInfo2 = ((SchemaGrammar)this.fGrammar).getElementComplexTypeInfo(fCurrentElementIndex);
                        if (elementComplexTypeInfo2 != null) {
                            fCurrentElementIndex = elementComplexTypeInfo2.templateElementIndex;
                        }
                    }
                    final int addSymbol = this.fStringPool.addSymbol(elementFromAnotherSchemaURI);
                    this.fCurrentSchemaURI = addSymbol;
                    this.fGrammarNameSpaceIndex = addSymbol;
                    if (!this.switchGrammar(this.fCurrentSchemaURI) && !this.fNeedValidationOff) {
                        this.reportRecoverableXMLError(169, 146, "Grammar with uri 4: " + this.fStringPool.toString(this.fCurrentSchemaURI) + " , can not found");
                    }
                }
            }
            contentSpecType = this.getContentSpecType(fCurrentElementIndex);
            if (contentSpecType == -1 && this.fValidating && !this.fNeedValidationOff) {
                this.reportRecoverableXMLError(83, 79, qName.rawname);
            }
            if (this.fGrammar != null && this.fGrammarIsSchemaGrammar && fCurrentElementIndex != -1) {
                this.fAttrListHandle = this.addDefaultAttributes(fCurrentElementIndex, list, this.fAttrListHandle, this.fValidating, this.fStandaloneReader != -1);
            }
            if (this.fAttrListHandle != -1) {
                this.fAttrList.endAttrList();
            }
            if (this.fAttrListHandle != -1 && !this.fNeedValidationOff) {
                for (int k = this.fAttrList.getFirstAttr(this.fAttrListHandle); k != -1; k = this.fAttrList.getNextAttr(k)) {
                    final int attrName = list.getAttrName(k);
                    if (this.fStringPool.equalNames(attrName, this.fXMLLang)) {
                        this.fDocumentScanner.checkXMLLangAttributeValue(list.getAttValue(k));
                    }
                    final int addSymbol2 = this.fStringPool.addSymbol("xmlns");
                    if (attrName != addSymbol2 && list.getAttrPrefix(k) != addSymbol2 && this.fGrammar != null) {
                        this.fTempQName.setValues(list.getAttrPrefix(k), list.getAttrLocalpart(k), list.getAttrName(k), list.getAttrURI(k));
                        final int attDefByElementIndex = this.getAttDefByElementIndex(fCurrentElementIndex, this.fTempQName);
                        if (this.fTempQName.uri != this.fXsiURI) {
                            if (attDefByElementIndex == -1) {
                                if (this.fValidating) {
                                    final Object[] array = { this.fStringPool.toString(qName.rawname), this.fStringPool.toString(list.getAttrName(k)) };
                                    this.fAttrNameLocator = this.getLocatorImpl(this.fAttrNameLocator);
                                    this.fErrorReporter.reportError(this.fAttrNameLocator, "http://www.w3.org/TR/1998/REC-xml-19980210", 82, 78, array, 1);
                                }
                            }
                            else {
                                this.fGrammar.getAttributeDecl(attDefByElementIndex, this.fTempAttDecl);
                                list.setAttType(k, this.attributeTypeName(this.fTempAttDecl));
                                if (this.fValidating) {
                                    if (this.fGrammarIsDTDGrammar) {
                                        list.setAttValue(k, this.validateDTDattribute(qName, list.getAttValue(k), this.fTempAttDecl));
                                    }
                                    else if (this.fGrammarIsSchemaGrammar && (this.fTempAttDecl.type == 8 || this.fTempAttDecl.type == 11 || this.fTempAttDecl.type == 10 || this.fTempAttDecl.type == 9)) {
                                        if (this.fTempAttDecl.defaultType != 6) {
                                            if (this.fTempAttDecl.defaultType == 4 || this.fTempAttDecl.defaultType == 5) {
                                                boolean b3 = false;
                                                final boolean b4 = this.fTempAttDecl.defaultType == 4;
                                                if (this.fTempQName.uri == 0) {
                                                    if (b4) {
                                                        b3 = true;
                                                    }
                                                }
                                                else {
                                                    final Grammar grammar = this.fGrammarResolver.getGrammar(this.fStringPool.toString(this.fTempQName.uri));
                                                    if (grammar == null || !(grammar instanceof SchemaGrammar)) {
                                                        if (b4) {
                                                            b3 = true;
                                                        }
                                                    }
                                                    else {
                                                        final Hashtable attirubteDeclRegistry = ((SchemaGrammar)grammar).getAttirubteDeclRegistry();
                                                        if (attirubteDeclRegistry == null) {
                                                            if (b4) {
                                                                b3 = true;
                                                            }
                                                        }
                                                        else {
                                                            final XMLAttributeDecl xmlAttributeDecl = attirubteDeclRegistry.get(this.fStringPool.toString(this.fTempQName.localpart));
                                                            if (xmlAttributeDecl == null) {
                                                                if (b4) {
                                                                    b3 = true;
                                                                }
                                                            }
                                                            else {
                                                                final DatatypeValidator datatypeValidator = xmlAttributeDecl.datatypeValidator;
                                                                if (datatypeValidator == null) {
                                                                    if (b4) {
                                                                        b3 = true;
                                                                    }
                                                                }
                                                                else {
                                                                    try {
                                                                        final String string3 = this.fStringPool.toString(list.getAttValue(k));
                                                                        String s = string3.trim();
                                                                        if (xmlAttributeDecl.type == 3) {
                                                                            this.fStoreIDRef.setDatatypeObject(this.fValID.validate(s, null));
                                                                        }
                                                                        if (xmlAttributeDecl.type == 4) {
                                                                            datatypeValidator.validate(s, this.fStoreIDRef);
                                                                        }
                                                                        else {
                                                                            this.fWhiteSpace = datatypeValidator.getWSFacet();
                                                                            if (this.fWhiteSpace == 1) {
                                                                                datatypeValidator.validate(string3, null);
                                                                            }
                                                                            else {
                                                                                list.setAttValue(k, this.fStringPool.addString(s));
                                                                                if (datatypeValidator instanceof NOTATIONDatatypeValidator && s != null) {
                                                                                    s = this.bindNotationURI(s);
                                                                                }
                                                                                datatypeValidator.validate(s, null);
                                                                            }
                                                                        }
                                                                    }
                                                                    catch (InvalidDatatypeValueException ex) {
                                                                        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/xml-schema-1", 21, 0, new Object[] { ex.getMessage() }, 1);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                if (b3) {
                                                    final Object[] array2 = { this.fStringPool.toString(qName.rawname), "ANY---" + this.fStringPool.toString(list.getAttrName(k)) };
                                                    this.fAttrNameLocator = this.getLocatorImpl(this.fAttrNameLocator);
                                                    this.fErrorReporter.reportError(this.fAttrNameLocator, "http://www.w3.org/TR/1998/REC-xml-19980210", 82, 78, array2, 1);
                                                }
                                            }
                                        }
                                    }
                                    else if (this.fTempAttDecl.datatypeValidator == null) {
                                        final Object[] array3 = { this.fStringPool.toString(qName.rawname), this.fStringPool.toString(list.getAttrName(k)) };
                                        System.out.println("[Error] Datatypevalidator for attribute " + this.fStringPool.toString(list.getAttrName(k)) + " not found in element type " + this.fStringPool.toString(qName.rawname));
                                        this.fAttrNameLocator = this.getLocatorImpl(this.fAttrNameLocator);
                                        this.fErrorReporter.reportError(this.fAttrNameLocator, "http://www.w3.org/TR/1998/REC-xml-19980210", 82, 78, array3, 1);
                                    }
                                    else {
                                        try {
                                            final String string4 = this.fStringPool.toString(list.getAttValue(k));
                                            String s2 = string4.trim();
                                            if (this.fTempAttDecl.type == 3) {
                                                this.fStoreIDRef.setDatatypeObject(this.fValID.validate(s2, null));
                                            }
                                            else if (this.fTempAttDecl.type == 4) {
                                                this.fTempAttDecl.datatypeValidator.validate(s2, this.fStoreIDRef);
                                            }
                                            else {
                                                this.fWhiteSpace = this.fTempAttDecl.datatypeValidator.getWSFacet();
                                                if (this.fWhiteSpace == 1) {
                                                    this.fTempAttDecl.datatypeValidator.validate(string4, null);
                                                }
                                                else {
                                                    list.setAttValue(k, this.fStringPool.addString(s2));
                                                    if (this.fTempAttDecl.datatypeValidator instanceof NOTATIONDatatypeValidator && s2 != null) {
                                                        s2 = this.bindNotationURI(s2);
                                                    }
                                                    this.fTempAttDecl.datatypeValidator.validate(s2, null);
                                                }
                                            }
                                        }
                                        catch (InvalidDatatypeValueException ex2) {
                                            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/xml-schema-1", 21, 0, new Object[] { ex2.getMessage() }, 1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (this.fAttrListHandle != -1) {
            for (int l = list.getFirstAttr(this.fAttrListHandle); l != -1; l = list.getNextAttr(l)) {
                if (!this.fStringPool.equalNames(list.getAttrName(l), this.fNamespacesPrefix)) {
                    final int attrPrefix = list.getAttrPrefix(l);
                    if (attrPrefix != this.fNamespacesPrefix && attrPrefix != -1) {
                        final int namespaceForPrefix2 = this.fNamespacesScope.getNamespaceForPrefix(attrPrefix);
                        if (namespaceForPrefix2 == 0) {
                            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1999/REC-xml-names-19990114", 167, 144, new Object[] { this.fStringPool.toString(attrPrefix) }, 1);
                        }
                        list.setAttrURI(l, namespaceForPrefix2);
                    }
                }
            }
        }
        this.fCurrentElementIndex = fCurrentElementIndex;
        this.fCurrentContentSpecType = contentSpecType;
        if (this.fValidating && contentSpecType == 4) {
            this.fBufferDatatype = true;
            this.fDatatypeBuffer.setLength(0);
        }
        this.fInElementContent = (contentSpecType == 3);
    }
    
    private int validateDTDattribute(final QName qName, int n, final XMLAttributeDecl xmlAttributeDecl) throws Exception {
        AttributeValidator attributeValidator = null;
        switch (xmlAttributeDecl.type) {
            case 1: {
                final boolean list = xmlAttributeDecl.list;
                final String string = this.fStringPool.toString(n);
                final String trim = string.trim();
                if (this.fValidationEnabled && trim != string && this.invalidStandaloneAttDef(qName, xmlAttributeDecl.name)) {
                    this.reportRecoverableXMLError(123, 80, this.fStringPool.toString(xmlAttributeDecl.name.rawname), string, trim);
                }
                try {
                    if (list) {
                        this.fValENTITIES.validate(trim, null);
                    }
                    else {
                        this.fValENTITY.validate(trim, null);
                    }
                }
                catch (InvalidDatatypeValueException ex) {
                    if (ex.getMajorCode() != 1 && ex.getMinorCode() != -1) {
                        this.reportRecoverableXMLError(ex.getMajorCode(), ex.getMinorCode(), this.fStringPool.toString(xmlAttributeDecl.name.rawname), trim);
                    }
                    else {
                        System.err.println("Error: " + ex.getLocalizedMessage());
                    }
                }
                if (!this.fNormalizeAttributeValues) {
                    break;
                }
                if (xmlAttributeDecl.list) {
                    n = this.normalizeListAttribute(trim, n, string);
                    break;
                }
                if (trim != string) {
                    n = this.fStringPool.addSymbol(trim);
                    break;
                }
                break;
            }
            case 2: {
                attributeValidator = this.fAttValidatorENUMERATION;
                break;
            }
            case 3: {
                final String string2 = this.fStringPool.toString(n);
                final String trim2 = string2.trim();
                if (this.fValidationEnabled && trim2 != string2 && this.invalidStandaloneAttDef(qName, xmlAttributeDecl.name)) {
                    this.reportRecoverableXMLError(123, 80, this.fStringPool.toString(xmlAttributeDecl.name.rawname), string2, trim2);
                }
                try {
                    this.fStoreIDRef.setDatatypeObject(this.fValID.validate(trim2, null));
                    this.fValIDRef.validate(trim2, this.fStoreIDRef);
                }
                catch (InvalidDatatypeValueException ex2) {
                    this.reportRecoverableXMLError(ex2.getMajorCode(), ex2.getMinorCode(), this.fStringPool.toString(xmlAttributeDecl.name.rawname), trim2);
                }
                if (this.fNormalizeAttributeValues && trim2 != string2) {
                    n = this.fStringPool.addSymbol(trim2);
                    break;
                }
                break;
            }
            case 4: {
                final String string3 = this.fStringPool.toString(n);
                final String trim3 = string3.trim();
                final boolean list2 = xmlAttributeDecl.list;
                if (this.fValidationEnabled) {
                    if (trim3 != string3 && this.invalidStandaloneAttDef(qName, xmlAttributeDecl.name)) {
                        this.reportRecoverableXMLError(123, 80, this.fStringPool.toString(xmlAttributeDecl.name.rawname), string3, trim3);
                    }
                    if (xmlAttributeDecl.list && trim3.length() == 0) {
                        this.reportRecoverableXMLError(4, 2, this.fStringPool.toString(xmlAttributeDecl.name.rawname));
                    }
                }
                try {
                    if (list2) {
                        this.fValIDRefs.validate(trim3, this.fStoreIDRef);
                    }
                    else {
                        this.fValIDRef.validate(trim3, this.fStoreIDRef);
                    }
                }
                catch (InvalidDatatypeValueException ex3) {
                    if (ex3.getMajorCode() != 1 && ex3.getMinorCode() != -1) {
                        this.reportRecoverableXMLError(ex3.getMajorCode(), ex3.getMinorCode(), this.fStringPool.toString(xmlAttributeDecl.name.rawname), trim3);
                    }
                    else {
                        System.err.println("Error: " + ex3.getLocalizedMessage());
                    }
                }
                if (!this.fNormalizeAttributeValues) {
                    break;
                }
                if (xmlAttributeDecl.list) {
                    n = this.normalizeListAttribute(trim3, n, string3);
                    break;
                }
                if (trim3 != string3) {
                    n = this.fStringPool.addSymbol(trim3);
                    break;
                }
                break;
            }
            case 6: {
                attributeValidator = this.fAttValidatorNOTATION;
                break;
            }
            case 5: {
                final String string4 = this.fStringPool.toString(n);
                final String trim4 = string4.trim();
                final boolean list3 = xmlAttributeDecl.list;
                if (this.fValidationEnabled) {
                    if (trim4 != string4 && this.invalidStandaloneAttDef(qName, xmlAttributeDecl.name)) {
                        this.reportRecoverableXMLError(123, 80, this.fStringPool.toString(xmlAttributeDecl.name.rawname), string4, trim4);
                    }
                    if (xmlAttributeDecl.list && trim4.length() == 0) {
                        this.reportRecoverableXMLError(5, 3, this.fStringPool.toString(xmlAttributeDecl.name.rawname));
                    }
                }
                try {
                    if (list3) {
                        this.fValNMTOKENS.validate(trim4, null);
                    }
                    else {
                        this.fValNMTOKEN.validate(trim4, null);
                    }
                }
                catch (InvalidDatatypeValueException ex4) {
                    this.reportRecoverableXMLError(78, 3, this.fStringPool.toString(xmlAttributeDecl.name.rawname), trim4);
                }
                if (!this.fNormalizeAttributeValues) {
                    break;
                }
                if (xmlAttributeDecl.list) {
                    n = this.normalizeListAttribute(trim4, n, string4);
                    break;
                }
                if (trim4 != string4) {
                    n = this.fStringPool.addSymbol(trim4);
                    break;
                }
                break;
            }
        }
        if (attributeValidator != null) {
            final int normalize = attributeValidator.normalize(qName, xmlAttributeDecl.name, n, xmlAttributeDecl.type, xmlAttributeDecl.enumeration);
            if (this.fNormalizeAttributeValues) {
                n = normalize;
            }
        }
        return n;
    }
    
    private int normalizeListAttribute(final String s, final int n, final String s2) {
        final int length = s.length();
        StringBuffer sb = null;
        int n2 = 0;
        int n3 = 0;
        for (int i = 0; i < length; ++i) {
            if (s.charAt(i) == ' ') {
                if (n2 == 0) {
                    n2 = 1;
                }
                else if (n2 == 1) {
                    n2 = 2;
                    if (sb == null) {
                        sb = new StringBuffer(length);
                    }
                    sb.append(s.substring(n3, i));
                }
            }
            else {
                if (n2 == 2) {
                    n3 = i;
                }
                n2 = 0;
            }
        }
        if (sb == null) {
            return (s == s2) ? n : this.fStringPool.addSymbol(s);
        }
        sb.append(s.substring(n3));
        return this.fStringPool.addSymbol(new String(sb));
    }
    
    private void charDataInContent() {
        if (this.fElementChildren.length <= this.fElementChildrenLength) {
            final QName[] fElementChildren = new QName[this.fElementChildren.length * 2];
            System.arraycopy(this.fElementChildren, 0, fElementChildren, 0, this.fElementChildren.length);
            this.fElementChildren = fElementChildren;
        }
        QName qName = this.fElementChildren[this.fElementChildrenLength];
        if (qName == null) {
            for (int i = this.fElementChildrenLength; i < this.fElementChildren.length; ++i) {
                this.fElementChildren[i] = new QName();
            }
            qName = this.fElementChildren[this.fElementChildrenLength];
        }
        qName.clear();
        ++this.fElementChildrenLength;
    }
    
    private int checkContent(final int n, final QName[] array, final int n2, final int n3) throws Exception {
        final int rawname = this.fCurrentElement.rawname;
        final int fCurrentContentSpecType = this.fCurrentContentSpecType;
        if (fCurrentContentSpecType == 0) {
            if (n3 != 0) {
                return 0;
            }
        }
        else if (fCurrentContentSpecType != 1) {
            if (fCurrentContentSpecType == 2 || fCurrentContentSpecType == 3) {
                try {
                    final XMLContentModel elementContentModel = this.getElementContentModel(n);
                    int n4 = elementContentModel.validateContent(array, n2, n3);
                    if (n4 != -1 && this.fGrammarIsSchemaGrammar) {
                        elementContentModel.setSubstitutionGroupComparator(new SubstitutionGroupComparator(this.fGrammarResolver, this.fStringPool));
                        n4 = elementContentModel.validateContentSpecial(array, n2, n3);
                    }
                    return n4;
                }
                catch (CMException ex) {
                    this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.apache.org/xml/xerces.html", ex.getErrorCode(), 0, null, 2);
                    return -1;
                }
            }
            if (fCurrentContentSpecType == -1) {
                this.reportRecoverableXMLError(83, 79, rawname);
            }
            else if (fCurrentContentSpecType == 4) {
                if (n3 > 0) {
                    this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/xml-schema-1", 21, 0, new Object[] { "In element '" + this.fStringPool.toString(rawname) + "' : " + "Can not have element children within a simple type content" }, 1);
                }
                else {
                    try {
                        if (this.fCurrentDV == null) {
                            this.fGrammar.getElementDecl(n, this.fTempElementDecl);
                            this.fCurrentDV = this.fTempElementDecl.datatypeValidator;
                        }
                        if (this.fXsiTypeValidator != null) {
                            this.fCurrentDV = this.fXsiTypeValidator;
                            this.fXsiTypeValidator = null;
                        }
                        if (this.fCurrentDV == null) {
                            System.out.println("Internal Error: this element have a simpletype but no datatypevalidator was found, element " + this.fTempElementDecl.name + ",locapart: " + this.fStringPool.toString(this.fTempElementDecl.name.localpart));
                        }
                        else {
                            String s = this.fDatatypeBuffer.toString();
                            if (this.fCurrentDV instanceof NOTATIONDatatypeValidator && s != null) {
                                s = this.bindNotationURI(s);
                            }
                            this.fCurrentDV.validate(s, null);
                        }
                    }
                    catch (InvalidDatatypeValueException ex2) {
                        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/xml-schema-1", 21, 0, new Object[] { "In element '" + this.fStringPool.toString(rawname) + "' : " + ex2.getMessage() }, 1);
                    }
                    this.fCurrentDV = null;
                    this.fFirstChunk = true;
                    this.fTrailing = false;
                }
            }
            else {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.apache.org/xml/xerces.html", 8, 0, null, 2);
            }
        }
        return -1;
    }
    
    private void printChildren() {
    }
    
    private void printStack() {
    }
    
    boolean invalidStandaloneAttDef(final QName qName, final QName qName2) {
        return this.fStandaloneReader != -1 && qName.rawname != -1 && this.getAttDefIsExternal(qName, qName2);
    }
    
    static class Resolver implements EntityResolver
    {
        private static final String[] SYSTEM;
        private static final String[] PATH;
        private DefaultEntityHandler fEntityHandler;
        
        public Resolver(final DefaultEntityHandler fEntityHandler) {
            this.fEntityHandler = fEntityHandler;
        }
        
        public InputSource resolveEntity(final String publicId, final String systemId) throws IOException, SAXException {
            for (int i = 0; i < Resolver.SYSTEM.length; ++i) {
                if (systemId.equals(Resolver.SYSTEM[i])) {
                    final InputSource inputSource = new InputSource(this.getClass().getResourceAsStream(Resolver.PATH[i]));
                    inputSource.setPublicId(publicId);
                    inputSource.setSystemId(systemId);
                    return inputSource;
                }
            }
            final EntityResolver entityResolver = this.fEntityHandler.getEntityResolver();
            if (entityResolver != null) {
                final InputSource resolveEntity = entityResolver.resolveEntity(publicId, systemId);
                if (resolveEntity != null) {
                    return resolveEntity;
                }
            }
            return new InputSource(this.fEntityHandler.expandSystemId(systemId));
        }
        
        static {
            SYSTEM = new String[] { "http://www.w3.org/2000/10/XMLSchema.dtd", "http://www.w3.org/XMLSchema/datatypes.dtd", "http://www.w3.org/XMLSchema/versionInfo.ent" };
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
        
        public void fatalError(final SAXParseException ex) {
            System.err.println("[Fatal Error] " + this.getLocationString(ex) + ": " + ex.getMessage());
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
    
    final class AttValidatorNOTATION implements AttributeValidator
    {
        public int normalize(final QName qName, final QName qName2, int n, final int n2, final int n3) throws Exception {
            final String string = XMLValidator.this.fStringPool.toString(n);
            final String trim = string.trim();
            if (XMLValidator.this.fValidating) {
                if (trim != string) {
                    if (this.invalidStandaloneAttDef(qName, qName2)) {
                        XMLValidator.this.reportRecoverableXMLError(123, 80, XMLValidator.this.fStringPool.toString(qName2.rawname), string, trim);
                    }
                    n = XMLValidator.this.fStringPool.addSymbol(trim);
                }
                else {
                    n = XMLValidator.this.fStringPool.addSymbol(n);
                }
                if (!XMLValidator.this.fStringPool.stringInList(n3, n)) {
                    XMLValidator.this.reportRecoverableXMLError(90, 84, XMLValidator.this.fStringPool.toString(qName2.rawname), trim, XMLValidator.this.fStringPool.stringListAsString(n3));
                }
            }
            else if (trim != string) {
                n = XMLValidator.this.fStringPool.addSymbol(trim);
            }
            return n;
        }
        
        boolean invalidStandaloneAttDef(final QName qName, final QName qName2) {
            return XMLValidator.this.fStandaloneReader != -1 && qName.rawname != -1 && XMLValidator.this.getAttDefIsExternal(qName, qName2);
        }
    }
    
    final class AttValidatorENUMERATION implements AttributeValidator
    {
        public int normalize(final QName qName, final QName qName2, int n, final int n2, final int n3) throws Exception {
            final String string = XMLValidator.this.fStringPool.toString(n);
            final String trim = string.trim();
            if (XMLValidator.this.fValidating) {
                if (trim != string) {
                    if (this.invalidStandaloneAttDef(qName, qName2)) {
                        XMLValidator.this.reportRecoverableXMLError(123, 80, XMLValidator.this.fStringPool.toString(qName2.rawname), string, trim);
                    }
                    n = XMLValidator.this.fStringPool.addSymbol(trim);
                }
                else {
                    n = XMLValidator.this.fStringPool.addSymbol(n);
                }
                if (!XMLValidator.this.fStringPool.stringInList(n3, n)) {
                    XMLValidator.this.reportRecoverableXMLError(90, 86, XMLValidator.this.fStringPool.toString(qName2.rawname), trim, XMLValidator.this.fStringPool.stringListAsString(n3));
                }
            }
            else if (trim != string) {
                n = XMLValidator.this.fStringPool.addSymbol(trim);
            }
            return n;
        }
        
        boolean invalidStandaloneAttDef(final QName qName, final QName qName2) {
            return XMLValidator.this.fStandaloneReader != -1 && qName.rawname != -1 && XMLValidator.this.getAttDefIsExternal(qName, qName2);
        }
    }
    
    protected static class XPathMatcherStack
    {
        protected XPathMatcher[] fMatchers;
        protected int fMatchersCount;
        protected IntStack fContextStack;
        
        public XPathMatcherStack() {
            this.fMatchers = new XPathMatcher[4];
            this.fContextStack = new IntStack();
        }
        
        public void clear() {
            for (int i = 0; i < this.fMatchersCount; ++i) {
                this.fMatchers[i] = null;
            }
            this.fMatchersCount = 0;
            this.fContextStack.clear();
        }
        
        public int size() {
            return this.fContextStack.size();
        }
        
        public int getMatcherCount() {
            return this.fMatchersCount;
        }
        
        public void addMatcher(final XPathMatcher xPathMatcher) {
            this.ensureMatcherCapacity();
            this.fMatchers[this.fMatchersCount++] = xPathMatcher;
        }
        
        public XPathMatcher getMatcherAt(final int n) {
            return this.fMatchers[n];
        }
        
        public void pushContext() {
            this.fContextStack.push(this.fMatchersCount);
        }
        
        public void popContext() {
            this.fMatchersCount = this.fContextStack.pop();
        }
        
        private void ensureMatcherCapacity() {
            if (this.fMatchersCount == this.fMatchers.length) {
                final XPathMatcher[] fMatchers = new XPathMatcher[this.fMatchers.length * 2];
                System.arraycopy(this.fMatchers, 0, fMatchers, 0, this.fMatchers.length);
                this.fMatchers = fMatchers;
            }
        }
    }
    
    protected abstract class ValueStoreBase implements ValueStore
    {
        protected final String NOT_A_VALUE = "\uffff";
        protected IdentityConstraint fIdentityConstraint;
        protected final OrderedHashtable fValues;
        protected int fValuesCount;
        protected final Vector fValueTuples;
        
        protected ValueStoreBase(final IdentityConstraint fIdentityConstraint) {
            this.fValues = new OrderedHashtable();
            this.fValueTuples = new Vector();
            this.fIdentityConstraint = fIdentityConstraint;
        }
        
        public void startValueScope() throws Exception {
            this.fValuesCount = 0;
            for (int fieldCount = this.fIdentityConstraint.getFieldCount(), i = 0; i < fieldCount; ++i) {
                this.fValues.put(this.fIdentityConstraint.getFieldAt(i), "\uffff");
            }
        }
        
        public void endValueScope() throws Exception {
            if (this.fValuesCount == 0) {
                return;
            }
            if (this.fValuesCount != this.fIdentityConstraint.getFieldCount()) {
                switch (this.fIdentityConstraint.getType()) {
                    case 0: {
                        XMLValidator.this.reportSchemaError(29, new Object[] { this.fIdentityConstraint.getElementName() });
                        break;
                    }
                    case 1: {
                        XMLValidator.this.reportSchemaError(30, new Object[] { this.fIdentityConstraint.getElementName(), ((Key)this.fIdentityConstraint).getIdentityConstraintName() });
                        break;
                    }
                    case 2: {
                        XMLValidator.this.reportSchemaError(31, new Object[] { this.fIdentityConstraint.getElementName(), ((KeyRef)this.fIdentityConstraint).getReferName() });
                        break;
                    }
                }
            }
        }
        
        public void endDocument() throws Exception {
        }
        
        public void addValue(final Field field, final String s) throws Exception {
            final int index = this.fValues.indexOf(field);
            if (index == -1) {
                XMLValidator.this.reportSchemaError(36, new Object[] { field.toString() });
                return;
            }
            if (this.fValues.valueAt(index).equals("\uffff")) {
                ++this.fValuesCount;
            }
            this.fValues.put(field, s);
            if (this.fValuesCount == this.fValues.size()) {
                if (this.contains(this.fValues)) {
                    this.duplicateValue(this.fValues);
                }
                this.fValueTuples.addElement(this.fValues.clone());
            }
        }
        
        public boolean contains(final OrderedHashtable orderedHashtable) {
            final int size = this.fValues.size();
            if (size != orderedHashtable.size()) {
                return false;
            }
            final int size2 = this.fValueTuples.size();
            int i = 0;
        Label_0117:
            while (i < size2) {
                final OrderedHashtable orderedHashtable2 = this.fValueTuples.elementAt(i);
                for (int j = 0; j < size; ++j) {
                    final String value = orderedHashtable2.valueAt(j);
                    final String value2 = orderedHashtable.valueAt(j);
                    this.fValues.keyAt(j).getDatatypeValidator();
                    if (!value.equals(value2)) {
                        ++i;
                        continue Label_0117;
                    }
                }
                return true;
            }
            return false;
        }
        
        protected void duplicateValue(final OrderedHashtable orderedHashtable) throws Exception {
        }
        
        protected String toString(final OrderedHashtable orderedHashtable) {
            final int size = orderedHashtable.size();
            if (size == 0) {
                return "";
            }
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < size; ++i) {
                if (i > 0) {
                    sb.append(',');
                }
                sb.append(orderedHashtable.valueAt(i));
            }
            return sb.toString();
        }
        
        public String toString() {
            String s = super.toString();
            final int lastIndex = s.lastIndexOf(36);
            if (lastIndex != -1) {
                s = s.substring(lastIndex + 1);
            }
            final int lastIndex2 = s.lastIndexOf(46);
            if (lastIndex2 != -1) {
                s = s.substring(lastIndex2 + 1);
            }
            return s + '[' + this.fIdentityConstraint + ']';
        }
    }
    
    protected class UniqueValueStore extends ValueStoreBase
    {
        public UniqueValueStore(final Unique unique) {
            super(unique);
        }
        
        protected void duplicateValue(final OrderedHashtable orderedHashtable) throws Exception {
            XMLValidator.this.reportSchemaError(33, new Object[] { this.toString(orderedHashtable), super.fIdentityConstraint.getElementName() });
        }
    }
    
    protected class KeyValueStore extends ValueStoreBase
    {
        public KeyValueStore(final Key key) {
            super(key);
        }
        
        protected void duplicateValue(final OrderedHashtable orderedHashtable) throws Exception {
            XMLValidator.this.reportSchemaError(34, new Object[] { this.toString(orderedHashtable), super.fIdentityConstraint.getElementName() });
        }
    }
    
    protected class KeyRefValueStore extends ValueStoreBase
    {
        protected KeyValueStore fKeyValueStore;
        
        public KeyRefValueStore(final KeyRef keyRef, final KeyValueStore fKeyValueStore) {
            super(keyRef);
            this.fKeyValueStore = fKeyValueStore;
        }
        
        public void endDocument() throws Exception {
            super.endDocument();
            for (int size = super.fValueTuples.size(), i = 0; i < size; ++i) {
                final OrderedHashtable orderedHashtable = super.fValueTuples.elementAt(i);
                if (!this.fKeyValueStore.contains(orderedHashtable)) {
                    XMLValidator.this.reportSchemaError(35, new Object[] { this.toString(orderedHashtable), super.fIdentityConstraint.getElementName() });
                }
            }
        }
    }
    
    protected class ValueStoreCache
    {
        protected final Vector fValueStores;
        protected final Hashtable fIdentityConstraint2ValueStoreMap;
        
        public ValueStoreCache() {
            this.fValueStores = new Vector();
            this.fIdentityConstraint2ValueStoreMap = new Hashtable();
        }
        
        public void startDocument() throws Exception {
            this.fValueStores.removeAllElements();
            this.fIdentityConstraint2ValueStoreMap.clear();
        }
        
        public void initValueStoresFor(final XMLElementDecl xmlElementDecl) throws Exception {
            final Vector unique = xmlElementDecl.unique;
            for (int size = unique.size(), i = 0; i < size; ++i) {
                final Unique unique2 = unique.elementAt(i);
                UniqueValueStore uniqueValueStore = null;
                for (int fieldCount = unique2.getFieldCount(), j = 0; j < fieldCount; ++j) {
                    unique2.getFieldAt(j);
                    if (uniqueValueStore == null) {
                        if (this.fIdentityConstraint2ValueStoreMap.get(unique2) != null) {
                            return;
                        }
                        uniqueValueStore = new UniqueValueStore(unique2);
                        this.fValueStores.addElement(uniqueValueStore);
                    }
                    this.fIdentityConstraint2ValueStoreMap.put(unique2, uniqueValueStore);
                }
            }
            final Vector key = xmlElementDecl.key;
            final int size2 = key.size();
            Hashtable<String, KeyValueStore> hashtable = null;
            if (size2 > 0) {
                hashtable = new Hashtable<String, KeyValueStore>(size2);
                for (int k = 0; k < size2; ++k) {
                    final Key key2 = key.elementAt(k);
                    KeyValueStore keyValueStore = null;
                    for (int fieldCount2 = key2.getFieldCount(), l = 0; l < fieldCount2; ++l) {
                        key2.getFieldAt(l);
                        if (keyValueStore == null) {
                            keyValueStore = new KeyValueStore(key2);
                            this.fValueStores.addElement(keyValueStore);
                            hashtable.put(key2.getIdentityConstraintName(), keyValueStore);
                        }
                        this.fIdentityConstraint2ValueStoreMap.put(key2, keyValueStore);
                    }
                }
            }
            final Vector keyRef = xmlElementDecl.keyRef;
            for (int size3 = keyRef.size(), n = 0; n < size3; ++n) {
                final KeyRef keyRef2 = keyRef.elementAt(n);
                final KeyValueStore keyValueStore2 = hashtable.get(keyRef2.getReferName());
                Object o = null;
                for (int fieldCount3 = keyRef2.getFieldCount(), n2 = 0; n2 < fieldCount3; ++n2) {
                    keyRef2.getFieldAt(n);
                    if (o == null) {
                        o = new KeyRefValueStore(keyRef2, keyValueStore2);
                        this.fValueStores.addElement(o);
                    }
                    this.fIdentityConstraint2ValueStoreMap.put(keyRef2, o);
                }
            }
        }
        
        public ValueStoreBase getValueStoreFor(final Field field) {
            return this.fIdentityConstraint2ValueStoreMap.get(field.getIdentityConstraint());
        }
        
        public void endDocument() throws Exception {
            for (int size = this.fValueStores.size(), i = 0; i < size; ++i) {
                ((ValueStoreBase)this.fValueStores.elementAt(i)).endDocument();
            }
        }
        
        public String toString() {
            final String string = super.toString();
            final int lastIndex = string.lastIndexOf(36);
            if (lastIndex != -1) {
                return string.substring(lastIndex + 1);
            }
            final int lastIndex2 = string.lastIndexOf(46);
            if (lastIndex2 != -1) {
                return string.substring(lastIndex2 + 1);
            }
            return string;
        }
    }
    
    static final class OrderedHashtable implements Cloneable
    {
        private int fSize;
        private Entry[] fEntries;
        
        OrderedHashtable() {
            this.fEntries = null;
        }
        
        public int size() {
            return this.fSize;
        }
        
        public void put(final Field key, final String value) {
            int index = this.indexOf(key);
            if (index == -1) {
                this.ensureCapacity(this.fSize);
                index = this.fSize++;
                this.fEntries[index].key = key;
            }
            this.fEntries[index].value = value;
        }
        
        public String get(final Field field) {
            return this.fEntries[this.indexOf(field)].value;
        }
        
        public int indexOf(final Field field) {
            for (int i = 0; i < this.fSize; ++i) {
                if (this.fEntries[i].key == field) {
                    return i;
                }
            }
            return -1;
        }
        
        public Field keyAt(final int n) {
            return this.fEntries[n].key;
        }
        
        public String valueAt(final int n) {
            return this.fEntries[n].value;
        }
        
        public void clear() {
            this.fSize = 0;
        }
        
        private void ensureCapacity(final int n) {
            int length = -1;
            int n2 = -1;
            if (this.fEntries == null) {
                length = 0;
                n2 = 2;
                this.fEntries = new Entry[n2];
            }
            else if (this.fEntries.length <= n) {
                length = this.fEntries.length;
                n2 = 2 * length;
                final Entry[] fEntries = new Entry[n2];
                System.arraycopy(this.fEntries, 0, fEntries, 0, length);
                this.fEntries = fEntries;
            }
            for (int i = length; i < n2; ++i) {
                this.fEntries[i] = new Entry();
            }
        }
        
        public Object clone() {
            final OrderedHashtable orderedHashtable = new OrderedHashtable();
            for (int i = 0; i < this.fSize; ++i) {
                orderedHashtable.put(this.fEntries[i].key, this.fEntries[i].value);
            }
            return orderedHashtable;
        }
        
        public String toString() {
            if (this.fSize == 0) {
                return "[]";
            }
            final StringBuffer sb = new StringBuffer();
            sb.append('[');
            for (int i = 0; i < this.fSize; ++i) {
                if (i > 0) {
                    sb.append(',');
                }
                sb.append('{');
                sb.append(this.fEntries[i].key);
                sb.append(',');
                sb.append(this.fEntries[i].value);
                sb.append('}');
            }
            sb.append(']');
            return sb.toString();
        }
        
        public static final class Entry
        {
            public Field key;
            public String value;
        }
    }
    
    public interface AttributeValidator
    {
        int normalize(final QName p0, final QName p1, final int p2, final int p3, final int p4) throws Exception;
    }
}
