// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.schema;

import org.apache.xerces.validators.datatype.UnknownFacetException;
import org.apache.xerces.validators.datatype.IllegalFacetValueException;
import org.apache.xerces.validators.datatype.IllegalFacetException;
import org.apache.xerces.validators.datatype.DecimalValidator;
import org.apache.xerces.validators.datatype.RealValidator;
import org.apache.xerces.validators.datatype.StringValidator;
import org.apache.xerces.validators.datatype.IntegerValidator;
import org.apache.xerces.validators.datatype.BooleanValidator;
import org.xml.sax.SAXParseException;
import java.io.IOException;
import org.xml.sax.InputSource;
import org.apache.xerces.dom.NodeImpl;
import java.util.Vector;
import java.util.Enumeration;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.EntityResolver;
import org.apache.xerces.validators.dtd.CMUniOp;
import org.apache.xerces.validators.dtd.CMBinOp;
import org.apache.xerces.validators.dtd.CMLeaf;
import org.apache.xerces.validators.dtd.CMNode;
import org.apache.xerces.validators.dtd.MixedContentModel;
import org.apache.xerces.validators.dtd.DFAContentModel;
import org.apache.xerces.validators.dtd.SimpleContentModel;
import org.apache.xerces.validators.dtd.XMLContentModel;
import org.apache.xerces.validators.dtd.InsertableElementsInfo;
import org.apache.xerces.validators.dtd.CMException;
import org.apache.xerces.validators.datatype.DatatypeValidator;
import org.apache.xerces.validators.datatype.InvalidDatatypeValueException;
import java.util.StringTokenizer;
import org.apache.xerces.utils.XMLCharacterProperties;
import org.apache.xerces.framework.XMLAttrList;
import org.apache.xerces.framework.XMLContentSpecNode;
import org.xml.sax.Locator;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import java.util.Hashtable;
import org.apache.xerces.utils.StringPool;
import org.apache.xerces.readers.XMLEntityHandler;
import org.apache.xerces.framework.XMLErrorReporter;
import org.apache.xerces.utils.NamespacesScope;
import org.apache.xerces.validators.dtd.ElementDeclPool;
import org.apache.xerces.validators.dtd.EntityPool;
import org.apache.xerces.framework.XMLValidator;

public class XSchemaValidator implements XMLValidator
{
    private static final boolean PRINT_EXCEPTION_STACK_TRACE = false;
    private static final boolean DEBUG_PRINT_ATTRIBUTES = false;
    private static final boolean DEBUG_PRINT_CONTENT = false;
    private static final boolean DEBUG_PAREN_DEPTH = false;
    private static final boolean DEBUG_MARKUP_DEPTH = false;
    private boolean fValidationEnabled;
    private boolean fDynamicValidation;
    private boolean fValidationEnabledByDynamic;
    private boolean fDynamicDisabledByValidation;
    private boolean fValidating;
    private boolean fWarningOnDuplicateAttDef;
    private boolean fWarningOnUndeclaredElements;
    private EntityPool fEntityPool;
    private ElementDeclPool fElementDeclPool;
    private int fStandaloneReader;
    private int[] fElementTypeStack;
    private int[] fContentSpecTypeStack;
    private int[] fElementChildCount;
    private int[][] fElementChildren;
    private int fElementDepth;
    private NamespacesScope fNamespacesScope;
    private boolean fNamespacesEnabled;
    private int fRootElementType;
    private int fAttrIndex;
    private XMLErrorReporter fErrorReporter;
    private XMLEntityHandler fEntityHandler;
    private StringPool fStringPool;
    private boolean fBufferDatatype;
    private StringBuffer fDatatypeBuffer;
    private DatatypeValidatorRegistry fDatatypeRegistry;
    private int fTypeCount;
    private int fGroupCount;
    private int fModelGroupCount;
    private int fAttributeGroupCount;
    private Hashtable fForwardRefs;
    private Hashtable fAttrGroupUses;
    private static final String ELT_COMMENT = "comment";
    private static final String ELT_DATATYPEDECL = "datatype";
    private static final String ELT_ARCHETYPEDECL = "archetype";
    private static final String ELT_ELEMENTDECL = "element";
    private static final String ELT_GROUP = "group";
    private static final String ELT_ATTRGROUPDECL = "attrGroup";
    private static final String ELT_ATTRGROUPREF = "attrGroupRef";
    private static final String ELT_MODELGROUPDECL = "modelGroup";
    private static final String ELT_MODELGROUPREF = "modelGroupRef";
    private static final String ELT_TEXTENTITYDECL = "textEntity";
    private static final String ELT_EXTERNALENTITYDECL = "externalEntity";
    private static final String ELT_UNPARSEDENTITYDECL = "unparsedEntity";
    private static final String ELT_NOTATIONDECL = "notation";
    private static final String ELT_REFINES = "refines";
    private static final String ELT_ATTRIBUTEDECL = "attribute";
    private static final String ATT_NAME = "name";
    private static final String ATT_CONTENT = "content";
    private static final String ATT_MODEL = "model";
    private static final String ATT_ORDER = "order";
    private static final String ATT_TYPE = "type";
    private static final String ATT_DEFAULT = "default";
    private static final String ATT_FIXED = "fixed";
    private static final String ATT_COLLECTION = "collection";
    private static final String ATT_REF = "ref";
    private static final String ATT_ARCHREF = "archRef";
    private static final String ATT_SCHEMAABBREV = "schemaAbbrev";
    private static final String ATT_SCHEMANAME = "schemaName";
    private static final String ATT_MINOCCURS = "minOccurs";
    private static final String ATT_MAXOCCURS = "maxOccurs";
    private static final String ATT_EXPORT = "export";
    private static final String ATTVAL_ANY = "any";
    private static final String ATTVAL_MIXED = "mixed";
    private static final String ATTVAL_EMPTY = "empty";
    private static final String ATTVAL_CHOICE = "choice";
    private static final String ATTVAL_SEQ = "seq";
    private static final String ATTVAL_ALL = "all";
    private static final String ATTVAL_ELEMONLY = "elemOnly";
    private static final String ATTVAL_TEXTONLY = "textOnly";
    private Document fSchemaDocument;
    private int fLeafCount;
    private int fEpsilonIndex;
    private int fCount;
    private int[] fContentList;
    private static final int CONTENTSPECNODE_ZERO_TO_N = 6;
    private static final int CONTENTSPECNODE_M_TO_N = 7;
    private static final int CONTENTSPECNODE_M_OR_MORE = 8;
    private DOMParser fSchemaParser;
    
    public XSchemaValidator(final StringPool fStringPool, final XMLErrorReporter fErrorReporter, final XMLEntityHandler fEntityHandler) {
        this.fValidationEnabled = false;
        this.fDynamicValidation = false;
        this.fValidationEnabledByDynamic = false;
        this.fDynamicDisabledByValidation = false;
        this.fValidating = false;
        this.fWarningOnDuplicateAttDef = false;
        this.fWarningOnUndeclaredElements = false;
        this.fStandaloneReader = -1;
        this.fElementTypeStack = new int[8];
        this.fContentSpecTypeStack = new int[8];
        this.fElementChildCount = new int[8];
        this.fElementChildren = new int[8][];
        this.fElementDepth = -1;
        this.fNamespacesEnabled = false;
        this.fRootElementType = -1;
        this.fAttrIndex = -1;
        this.fBufferDatatype = false;
        this.fDatatypeBuffer = new StringBuffer();
        this.fDatatypeRegistry = new DatatypeValidatorRegistry();
        this.fForwardRefs = new Hashtable();
        this.fAttrGroupUses = new Hashtable();
        this.fEpsilonIndex = -1;
        this.fContentList = new int[64];
        this.fEntityPool = new EntityPool(fStringPool, fErrorReporter, true);
        this.fElementDeclPool = new ElementDeclPool(fStringPool, fErrorReporter);
        this.fErrorReporter = fErrorReporter;
        this.fEntityHandler = fEntityHandler;
        this.fStringPool = fStringPool;
        this.fDatatypeRegistry.initializeRegistry();
    }
    
    public void reset(final StringPool fStringPool, final XMLErrorReporter fErrorReporter, final XMLEntityHandler fEntityHandler) throws Exception {
        this.fEntityPool.reset(fStringPool);
        this.fValidating = this.fValidationEnabled;
        this.fElementDeclPool.reset(fStringPool);
        this.fRootElementType = -1;
        this.fAttrIndex = -1;
        this.fStandaloneReader = -1;
        this.fElementDepth = -1;
        this.fErrorReporter = fErrorReporter;
        this.fEntityHandler = fEntityHandler;
        this.fStringPool = fStringPool;
        this.fSchemaDocument = null;
    }
    
    public Document getSchemaDocument() {
        return this.fSchemaDocument;
    }
    
    public void setValidationEnabled(final boolean fValidationEnabled) {
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
    }
    
    public boolean getValidationEnabled() {
        return this.fValidationEnabled;
    }
    
    public void setDynamicValidationEnabled(final boolean fDynamicValidation) {
        this.fDynamicValidation = fDynamicValidation;
        this.fDynamicDisabledByValidation = false;
        if (this.fDynamicValidation) {
            if (!this.fValidationEnabled) {
                this.fValidationEnabledByDynamic = true;
                this.fValidationEnabled = true;
            }
        }
        else if (this.fValidationEnabledByDynamic) {
            this.fValidationEnabled = false;
            this.fValidationEnabledByDynamic = false;
        }
    }
    
    public boolean getDynamicValidationEnabled() {
        return this.fDynamicValidation;
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
    
    private boolean usingStandaloneReader() {
        return this.fStandaloneReader == -1 || this.fEntityHandler.getReaderId() == this.fStandaloneReader;
    }
    
    private boolean invalidStandaloneAttDef(final int n, final int n2) {
        return this.fStandaloneReader != -1 && n != -1 && this.fElementDeclPool.getAttDefIsExternal(this.fElementDeclPool.getAttDef(n, n2));
    }
    
    public boolean notationDeclared(final int n) {
        return this.fEntityPool.lookupNotation(n) != -1;
    }
    
    protected void addRequiredNotation(final int n, final Locator locator, final int n2, final int n3, final Object[] array) throws Exception {
        this.fEntityPool.addRequiredNotation(n, locator, n2, n3, array);
    }
    
    public void characters(final char[] array, final int n, final int n2) throws Exception {
        if (this.fValidating) {
            this.charDataInContent();
            if (this.fBufferDatatype) {
                this.fDatatypeBuffer.append(array, n, n2);
            }
        }
    }
    
    public void characters(final int n) throws Exception {
        if (this.fValidating) {
            this.charDataInContent();
            if (this.fBufferDatatype) {
                this.fDatatypeBuffer.append(this.fStringPool.toString(n));
            }
        }
    }
    
    public void ignorableWhitespace(final char[] array, final int n, final int n2) throws Exception {
        if (this.fStandaloneReader != -1 && this.fValidating && this.fElementDeclPool.getElementDeclIsExternal(this.getElement(this.peekElementType()))) {
            this.reportRecoverableXMLError(143, 80);
        }
    }
    
    public void ignorableWhitespace(final int n) throws Exception {
        if (this.fStandaloneReader != -1 && this.fValidating && this.fElementDeclPool.getElementDeclIsExternal(this.getElement(this.peekElementType()))) {
            this.reportRecoverableXMLError(143, 80);
        }
    }
    
    public int getElement(final int n) {
        return this.fElementDeclPool.getElement(n);
    }
    
    public int getElementType(final int n) throws Exception {
        return this.fElementDeclPool.getElementType(n);
    }
    
    public int getContentSpecType(final int n) {
        return this.fElementDeclPool.getContentSpecType(n);
    }
    
    public int getContentSpec(final int n) {
        return this.fElementDeclPool.getContentSpec(n);
    }
    
    public String getContentSpecAsString(final int n) {
        return this.fElementDeclPool.getContentSpecAsString(n);
    }
    
    public void getContentSpecNode(final int n, final XMLContentSpecNode xmlContentSpecNode) {
        this.fElementDeclPool.getContentSpecNode(n, xmlContentSpecNode);
    }
    
    public int getAttName(final int n) {
        return this.fElementDeclPool.getAttName(n);
    }
    
    public int getAttValue(final int n) {
        return this.fElementDeclPool.getAttValue(n);
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
    
    public int getEntityName(final int n) {
        return this.fEntityPool.getEntityName(n);
    }
    
    public int getEntityValue(final int n) {
        return this.fEntityPool.getEntityValue(n);
    }
    
    public String getPublicIdOfEntity(final int n) {
        return this.fStringPool.toString(this.fEntityPool.getPublicId(n));
    }
    
    public int getPublicIdIndexOfEntity(final int n) {
        return this.fEntityPool.getPublicId(n);
    }
    
    public String getSystemIdOfEntity(final int n) {
        return this.fStringPool.toString(this.fEntityPool.getSystemId(n));
    }
    
    public int getSystemIdIndexOfEntity(final int n) {
        return this.fEntityPool.getSystemId(n);
    }
    
    public int getNotationName(final int n) {
        return this.fEntityPool.getNotationName(n);
    }
    
    public int lookupParameterEntity(final int n) throws Exception {
        throw new RuntimeException("cannot happen 26");
    }
    
    public boolean isExternalParameterEntity(final int n) {
        throw new RuntimeException("cannot happen 27");
    }
    
    public int getParameterEntityValue(final int n) {
        throw new RuntimeException("cannot happen 28");
    }
    
    public String getPublicIdOfParameterEntity(final int n) {
        throw new RuntimeException("cannot happen 29");
    }
    
    public String getSystemIdOfParameterEntity(final int n) {
        throw new RuntimeException("cannot happen 30");
    }
    
    public void rootElementSpecified(final int fRootElementType) throws Exception {
        if (this.fValidating) {
            this.fRootElementType = fRootElementType;
            if (this.fRootElementType != -1 && fRootElementType != this.fRootElementType) {
                this.reportRecoverableXMLError(3, 1, this.fRootElementType, fRootElementType);
            }
        }
    }
    
    public boolean attributeSpecified(final int n, final XMLAttrList list, final int n2, final Locator locator, int normalizeAttributeValue) throws Exception {
        final int attDef = this.fElementDeclPool.getAttDef(n, n2);
        if (attDef == -1) {
            if (this.fValidating) {
                this.fErrorReporter.reportError(locator, "http://www.w3.org/TR/1998/REC-xml-19980210", 82, 78, new Object[] { this.fStringPool.toString(n), this.fStringPool.toString(n2) }, 1);
            }
            final int addSymbol = this.fStringPool.addSymbol("CDATA");
            if (this.fAttrIndex == -1) {
                this.fAttrIndex = list.startAttrList();
            }
            return list.addAttr(n2, normalizeAttributeValue, addSymbol, true, true) != -1;
        }
        final int attType = this.fElementDeclPool.getAttType(attDef);
        if (attType != this.fStringPool.addSymbol("CDATA")) {
            normalizeAttributeValue = this.normalizeAttributeValue(n, n2, normalizeAttributeValue, attType, this.fElementDeclPool.getEnumeration(attDef));
        }
        if (this.fAttrIndex == -1) {
            this.fAttrIndex = list.startAttrList();
        }
        return list.addAttr(n2, normalizeAttributeValue, attType, true, true) != -1;
    }
    
    public boolean startElement(final int n, final XMLAttrList list) throws Exception {
        int n2 = this.fAttrIndex;
        this.fAttrIndex = -1;
        final int element = this.fElementDeclPool.getElement(n);
        final int n3 = (element == -1) ? -1 : this.fElementDeclPool.getContentSpecType(element);
        if (n3 == -1 && this.fValidating) {
            this.reportRecoverableXMLError(83, 79, n);
        }
        if (element != -1) {
            n2 = this.fElementDeclPool.addDefaultAttributes(element, list, n2, this.fValidating, this.fStandaloneReader != -1);
        }
        this.checkAttributes(element, list, n2);
        if (this.fValidating && n3 == this.fStringPool.addSymbol("DATATYPE")) {
            this.fBufferDatatype = true;
            this.fDatatypeBuffer.setLength(0);
        }
        this.pushElement(n, n3);
        return n3 == this.fStringPool.addSymbol("CHILDREN");
    }
    
    public boolean endElement(final int n) throws Exception {
        if (this.fValidating) {
            final int element = this.fElementDeclPool.getElement(n);
            if (element != -1 && this.fElementDeclPool.getContentSpecType(element) != -1) {
                final int peekChildCount = this.peekChildCount();
                final int checkContent = this.checkContent(element, peekChildCount, this.peekChildren());
                if (checkContent != -1) {
                    this.reportRecoverableXMLError((checkContent != peekChildCount) ? 87 : 88, 0, this.fStringPool.toString(n), this.fElementDeclPool.getContentSpecAsString(element));
                }
            }
            this.fBufferDatatype = false;
        }
        this.popElement();
        return this.peekContentSpecType() == this.fStringPool.addSymbol("CHILDREN");
    }
    
    private int normalizeAttributeValue(final int n, final int n2, int n3, final int n4, final int n5) throws Exception {
        final String string = this.fStringPool.toString(n3);
        if (n4 == this.fStringPool.addSymbol("ID")) {
            final String trim = string.trim();
            if (this.fValidating) {
                n3 = this.fStringPool.addSymbol(trim);
                if (trim != string && this.invalidStandaloneAttDef(n, n2)) {
                    this.reportRecoverableXMLError(123, 80, this.fStringPool.toString(n2), string, trim);
                }
                if (!XMLCharacterProperties.validName(trim)) {
                    this.reportRecoverableXMLError(75, 76, this.fStringPool.toString(n2), trim);
                }
                if (n != -1 && !this.fElementDeclPool.addId(n3)) {
                    this.reportRecoverableXMLError(76, 76, this.fStringPool.toString(n2), trim);
                }
            }
            else if (trim != string) {
                n3 = this.fStringPool.addSymbol(trim);
            }
        }
        else if (n4 == this.fStringPool.addSymbol("IDREF")) {
            final String trim2 = string.trim();
            if (this.fValidating) {
                n3 = this.fStringPool.addSymbol(trim2);
                if (trim2 != string && this.invalidStandaloneAttDef(n, n2)) {
                    this.reportRecoverableXMLError(123, 80, this.fStringPool.toString(n2), string, trim2);
                }
                if (!XMLCharacterProperties.validName(trim2)) {
                    this.reportRecoverableXMLError(77, 2, this.fStringPool.toString(n2), trim2);
                }
                if (n != -1) {
                    this.fElementDeclPool.addIdRef(n3);
                }
            }
            else if (trim2 != string) {
                n3 = this.fStringPool.addSymbol(trim2);
            }
        }
        else if (n4 == this.fStringPool.addSymbol("IDREFS")) {
            final StringTokenizer stringTokenizer = new StringTokenizer(string);
            final StringBuffer sb = new StringBuffer(string.length());
            boolean b = true;
            if (stringTokenizer.hasMoreTokens()) {
                while (true) {
                    final String nextToken = stringTokenizer.nextToken();
                    if (this.fValidating) {
                        if (!XMLCharacterProperties.validName(nextToken)) {
                            b = false;
                        }
                        if (n != -1) {
                            this.fElementDeclPool.addIdRef(this.fStringPool.addSymbol(nextToken));
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
            if (this.fValidating && (!b || string2.length() == 0)) {
                this.reportRecoverableXMLError(4, 2, this.fStringPool.toString(n2), string2);
            }
            if (!string2.equals(string)) {
                n3 = this.fStringPool.addString(string2);
                if (this.fValidating && this.invalidStandaloneAttDef(n, n2)) {
                    this.reportRecoverableXMLError(123, 80, this.fStringPool.toString(n2), string, string2);
                }
            }
        }
        else if (n4 == this.fStringPool.addSymbol("ENTITY")) {
            final String trim3 = string.trim();
            if (this.fValidating) {
                n3 = this.fStringPool.addSymbol(trim3);
                if (trim3 != string && this.invalidStandaloneAttDef(n, n2)) {
                    this.reportRecoverableXMLError(123, 80, this.fStringPool.toString(n2), string, trim3);
                }
                final int lookupEntity = this.fEntityPool.lookupEntity(n3);
                if (lookupEntity == -1 || !this.fEntityPool.isUnparsedEntity(lookupEntity)) {
                    this.reportRecoverableXMLError(79, 77, this.fStringPool.toString(n2), trim3);
                }
            }
            else if (trim3 != string) {
                n3 = this.fStringPool.addSymbol(trim3);
            }
        }
        else if (n4 == this.fStringPool.addSymbol("ENTITIES")) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(string);
            final StringBuffer sb2 = new StringBuffer(string.length());
            boolean b2 = true;
            if (stringTokenizer2.hasMoreTokens()) {
                while (true) {
                    final String nextToken2 = stringTokenizer2.nextToken();
                    if (this.fValidating) {
                        final int lookupEntity2 = this.fEntityPool.lookupEntity(this.fStringPool.addSymbol(nextToken2));
                        if (lookupEntity2 == -1 || !this.fEntityPool.isUnparsedEntity(lookupEntity2)) {
                            b2 = false;
                        }
                    }
                    sb2.append(nextToken2);
                    if (!stringTokenizer2.hasMoreTokens()) {
                        break;
                    }
                    sb2.append(' ');
                }
            }
            final String string3 = sb2.toString();
            if (this.fValidating && (!b2 || string3.length() == 0)) {
                this.reportRecoverableXMLError(80, 77, this.fStringPool.toString(n2), string3);
            }
            if (!string3.equals(string)) {
                n3 = this.fStringPool.addString(string3);
                if (this.fValidating && this.invalidStandaloneAttDef(n, n2)) {
                    this.reportRecoverableXMLError(123, 80, this.fStringPool.toString(n2), string, string3);
                }
            }
        }
        else if (n4 == this.fStringPool.addSymbol("NMTOKEN")) {
            final String trim4 = string.trim();
            if (this.fValidating) {
                n3 = this.fStringPool.addSymbol(trim4);
                if (trim4 != string && this.invalidStandaloneAttDef(n, n2)) {
                    this.reportRecoverableXMLError(123, 80, this.fStringPool.toString(n2), string, trim4);
                }
                if (!XMLCharacterProperties.validNmtoken(trim4)) {
                    this.reportRecoverableXMLError(78, 3, this.fStringPool.toString(n2), trim4);
                }
            }
            else if (trim4 != string) {
                n3 = this.fStringPool.addSymbol(trim4);
            }
        }
        else if (n4 == this.fStringPool.addSymbol("NMTOKENS")) {
            final StringTokenizer stringTokenizer3 = new StringTokenizer(string);
            final StringBuffer sb3 = new StringBuffer(string.length());
            boolean b3 = true;
            if (stringTokenizer3.hasMoreTokens()) {
                while (true) {
                    final String nextToken3 = stringTokenizer3.nextToken();
                    if (this.fValidating && !XMLCharacterProperties.validNmtoken(nextToken3)) {
                        b3 = false;
                    }
                    sb3.append(nextToken3);
                    if (!stringTokenizer3.hasMoreTokens()) {
                        break;
                    }
                    sb3.append(' ');
                }
            }
            final String string4 = sb3.toString();
            if (this.fValidating && (!b3 || string4.length() == 0)) {
                this.reportRecoverableXMLError(5, 3, this.fStringPool.toString(n2), string4);
            }
            if (!string4.equals(string)) {
                n3 = this.fStringPool.addString(string4);
                if (this.fValidating && this.invalidStandaloneAttDef(n, n2)) {
                    this.reportRecoverableXMLError(123, 80, this.fStringPool.toString(n2), string, string4);
                }
            }
        }
        else if (n4 == this.fStringPool.addSymbol("NOTATION") || n4 == this.fStringPool.addSymbol("ENUMERATION")) {
            final String trim5 = string.trim();
            if (this.fValidating) {
                n3 = this.fStringPool.addSymbol(trim5);
                if (trim5 != string && this.invalidStandaloneAttDef(n, n2)) {
                    this.reportRecoverableXMLError(123, 80, this.fStringPool.toString(n2), string, trim5);
                }
                if (!this.fStringPool.stringInList(n5, n3)) {
                    this.reportRecoverableXMLError(90, (n4 == this.fStringPool.addSymbol("NOTATION")) ? 84 : 86, this.fStringPool.toString(n2), trim5, this.fStringPool.stringListAsString(n5));
                }
            }
            else if (trim5 != string) {
                n3 = this.fStringPool.addSymbol(trim5);
            }
        }
        else {
            if (n4 != this.fStringPool.addSymbol("DATATYPE")) {
                throw new RuntimeException("cannot happen 1");
            }
            try {
                final String string5 = this.fStringPool.toString(n5);
                final DatatypeValidator validator = this.fDatatypeRegistry.getValidatorFor(string5);
                if (validator != null) {
                    validator.validate(string);
                }
                else {
                    this.reportSchemaError(2, new Object[] { string5 });
                }
            }
            catch (InvalidDatatypeValueException ex) {
                this.reportSchemaError(3, new Object[] { ex.getMessage() });
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
                System.out.println("Internal error in attribute datatype validation");
            }
        }
        return n3;
    }
    
    private void pushElement(final int n, final int n2) {
        if (this.fElementDepth >= 0) {
            int[] array = this.fElementChildren[this.fElementDepth];
            int n3 = this.fElementChildCount[this.fElementDepth];
            if (array == null) {
                final int[][] fElementChildren = this.fElementChildren;
                final int fElementDepth = this.fElementDepth;
                final int[] array2 = new int[8];
                fElementChildren[fElementDepth] = array2;
                array = array2;
                n3 = 0;
            }
            else if (n3 == array.length) {
                final int[] array3 = new int[n3 * 2];
                System.arraycopy(array, 0, array3, 0, n3);
                final int[][] fElementChildren2 = this.fElementChildren;
                final int fElementDepth2 = this.fElementDepth;
                final int[] array4 = array3;
                fElementChildren2[fElementDepth2] = array4;
                array = array4;
            }
            array[n3++] = n;
            this.fElementChildCount[this.fElementDepth] = n3;
        }
        ++this.fElementDepth;
        if (this.fElementDepth == this.fElementTypeStack.length) {
            final int[] fElementTypeStack = new int[this.fElementDepth * 2];
            System.arraycopy(this.fElementTypeStack, 0, fElementTypeStack, 0, this.fElementDepth);
            this.fElementTypeStack = fElementTypeStack;
            final int[] fContentSpecTypeStack = new int[this.fElementDepth * 2];
            System.arraycopy(this.fContentSpecTypeStack, 0, fContentSpecTypeStack, 0, this.fElementDepth);
            this.fContentSpecTypeStack = fContentSpecTypeStack;
            final int[] fElementChildCount = new int[this.fElementDepth * 2];
            System.arraycopy(this.fElementChildCount, 0, fElementChildCount, 0, this.fElementDepth);
            this.fElementChildCount = fElementChildCount;
            final int[][] fElementChildren3 = new int[this.fElementDepth * 2][];
            System.arraycopy(this.fElementChildren, 0, fElementChildren3, 0, this.fElementDepth);
            this.fElementChildren = fElementChildren3;
        }
        this.fElementTypeStack[this.fElementDepth] = n;
        this.fContentSpecTypeStack[this.fElementDepth] = n2;
        this.fElementChildCount[this.fElementDepth] = 0;
    }
    
    private void charDataInContent() {
        int[] array = this.fElementChildren[this.fElementDepth];
        int n = this.fElementChildCount[this.fElementDepth];
        if (array == null) {
            final int[][] fElementChildren = this.fElementChildren;
            final int fElementDepth = this.fElementDepth;
            final int[] array2 = new int[8];
            fElementChildren[fElementDepth] = array2;
            array = array2;
            n = 0;
        }
        else if (n == array.length) {
            final int[] array3 = new int[n * 2];
            System.arraycopy(array, 0, array3, 0, n);
            final int[][] fElementChildren2 = this.fElementChildren;
            final int fElementDepth2 = this.fElementDepth;
            final int[] array4 = array3;
            fElementChildren2[fElementDepth2] = array4;
            array = array4;
        }
        array[n++] = -1;
        this.fElementChildCount[this.fElementDepth] = n;
    }
    
    private int peekElementType() {
        return this.fElementTypeStack[this.fElementDepth];
    }
    
    private int peekContentSpecType() {
        if (this.fElementDepth < 0) {
            return -1;
        }
        return this.fContentSpecTypeStack[this.fElementDepth];
    }
    
    private int peekChildCount() {
        return this.fElementChildCount[this.fElementDepth];
    }
    
    private int[] peekChildren() {
        return this.fElementChildren[this.fElementDepth];
    }
    
    private void popElement() throws Exception {
        if (this.fElementDepth < 0) {
            throw new RuntimeException("Element stack underflow");
        }
        final int fElementDepth = this.fElementDepth - 1;
        this.fElementDepth = fElementDepth;
        if (fElementDepth < 0 && this.fValidating) {
            this.checkIDRefNames();
        }
    }
    
    private void checkAttributes(final int n, final XMLAttrList list, final int n2) throws Exception {
    }
    
    public int checkContent(final int n, final int n2, final int[] array) throws Exception {
        final int elementType = this.fElementDeclPool.getElementType(n);
        final int contentSpecType = this.fElementDeclPool.getContentSpecType(n);
        if (contentSpecType == this.fStringPool.addSymbol("EMPTY")) {
            if (n2 != 0) {
                return 0;
            }
        }
        else if (contentSpecType != this.fStringPool.addSymbol("ANY")) {
            if (contentSpecType == this.fStringPool.addSymbol("MIXED") || contentSpecType == this.fStringPool.addSymbol("CHILDREN")) {
                try {
                    return this.getContentModel(n).validateContent(n2, array);
                }
                catch (CMException ex) {
                    this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.apache.org/xml/xerces.html", ex.getErrorCode(), 0, null, 2);
                    return -1;
                }
            }
            if (contentSpecType == -1) {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 83, 79, new Object[] { this.fStringPool.toString(elementType) }, 1);
            }
            else {
                if (contentSpecType == this.fStringPool.addSymbol("DATATYPE")) {
                    try {
                        return this.getContentModel(n).validateContent(1, new int[] { this.fStringPool.addString(this.fDatatypeBuffer.toString()) });
                    }
                    catch (CMException ex3) {
                        System.out.println("Internal Error in datatype validation");
                        return -1;
                    }
                    catch (InvalidDatatypeValueException ex2) {
                        this.reportSchemaError(21, new Object[] { ex2.getMessage() });
                        return -1;
                    }
                }
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.apache.org/xml/xerces.html", 8, 0, null, 2);
            }
        }
        return -1;
    }
    
    public void checkIDRefNames() throws Exception {
        this.fElementDeclPool.checkIdRefs();
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
        final int contentSpec = this.fElementDeclPool.getContentSpec(n);
        this.fElementDeclPool.getContentSpecNode(contentSpec, xmlContentSpecNode);
        if (xmlContentSpecNode.value == -1) {
            throw new CMException(11);
        }
        if (xmlContentSpecNode.type == 0) {
            return new SimpleContentModel(xmlContentSpecNode.value, -1, xmlContentSpecNode.type);
        }
        if (xmlContentSpecNode.type == 4 || xmlContentSpecNode.type == 5) {
            final XMLContentSpecNode xmlContentSpecNode2 = new XMLContentSpecNode();
            final XMLContentSpecNode xmlContentSpecNode3 = new XMLContentSpecNode();
            this.fElementDeclPool.getContentSpecNode(xmlContentSpecNode.value, xmlContentSpecNode2);
            this.fElementDeclPool.getContentSpecNode(xmlContentSpecNode.otherValue, xmlContentSpecNode3);
            if (xmlContentSpecNode2.type == 0 && xmlContentSpecNode3.type == 0) {
                return new SimpleContentModel(xmlContentSpecNode2.value, xmlContentSpecNode3.value, xmlContentSpecNode.type);
            }
        }
        else {
            if (xmlContentSpecNode.type != 1 && xmlContentSpecNode.type != 2 && xmlContentSpecNode.type != 3) {
                throw new CMException(8);
            }
            final XMLContentSpecNode xmlContentSpecNode4 = new XMLContentSpecNode();
            this.fElementDeclPool.getContentSpecNode(xmlContentSpecNode.value, xmlContentSpecNode4);
            if (xmlContentSpecNode4.type == 0) {
                return new SimpleContentModel(xmlContentSpecNode4.value, -1, xmlContentSpecNode.type);
            }
        }
        this.fLeafCount = 0;
        this.fEpsilonIndex = this.fStringPool.addSymbol("<<CMNODE_EPSILON>>");
        return new DFAContentModel(this.fStringPool, this.buildSyntaxTree(contentSpec, xmlContentSpecNode), this.fLeafCount);
    }
    
    public XMLContentModel getContentModel(final int n) throws CMException {
        final XMLContentModel contentModel = this.fElementDeclPool.getContentModel(n);
        if (contentModel != null) {
            return contentModel;
        }
        final int contentSpecType = this.fElementDeclPool.getContentSpecType(n);
        XMLContentModel childModel;
        if (contentSpecType == this.fStringPool.addSymbol("MIXED")) {
            this.makeContentList(this.fElementDeclPool.getContentSpec(n), new XMLContentSpecNode());
            childModel = new MixedContentModel(this.fCount, this.fContentList);
        }
        else if (contentSpecType == this.fStringPool.addSymbol("CHILDREN")) {
            childModel = this.createChildModel(n);
        }
        else {
            if (contentSpecType != this.fStringPool.addSymbol("DATATYPE")) {
                throw new CMException(8);
            }
            childModel = new DatatypeContentModel(this.fDatatypeRegistry, this.fElementDeclPool, this.fStringPool, n);
        }
        this.fElementDeclPool.setContentModel(n, childModel);
        return childModel;
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
        this.fElementDeclPool.getContentSpecNode(n, xmlContentSpecNode);
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
    
    public void loadSchema(final String s) {
        final String expandSystemId = this.fEntityHandler.expandSystemId(s);
        if (this.fSchemaParser == null) {
            (this.fSchemaParser = new DOMParser() {
                public void ignorableWhitespace(final char[] array, final int n, final int n2) {
                }
                
                public void ignorableWhitespace(final int n) {
                }
            }).setEntityResolver(new Resolver());
            this.fSchemaParser.setErrorHandler(new ErrorHandler());
        }
        try {
            this.fSchemaParser.setFeature("http://xml.org/sax/features/validation", true);
            this.fSchemaParser.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false);
            this.fSchemaParser.parse(expandSystemId);
        }
        catch (SAXException ex) {
            ex.getException().printStackTrace();
            System.err.println("error parsing schema file");
            System.exit(1);
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            System.err.println("error parsing schema file");
            System.exit(1);
        }
        this.fSchemaDocument = this.fSchemaParser.getDocument();
        if (this.fSchemaDocument == null) {
            System.err.println("error: couldn't load schema file!");
            return;
        }
        try {
            this.traverseSchema(this.fSchemaDocument.getDocumentElement());
        }
        catch (Exception ex3) {
            ex3.printStackTrace(System.err);
            System.exit(1);
        }
    }
    
    private void traverseSchema(final Element element) throws Exception {
        if (element == null) {
            return;
        }
        for (Element element2 = XUtil.getFirstChildElement(element); element2 != null; element2 = XUtil.getNextSiblingElement(element2)) {
            final String nodeName = element2.getNodeName();
            if (nodeName.equals("comment")) {
                this.traverseComment(element2);
            }
            else if (nodeName.equals("datatype")) {
                this.traverseDatatypeDecl(element2);
            }
            else if (nodeName.equals("archetype")) {
                this.traverseTypeDecl(element2);
            }
            else if (nodeName.equals("element")) {
                this.traverseElementDecl(element2);
            }
            else if (nodeName.equals("attrGroup")) {
                this.traverseAttrGroup(element2);
            }
            else if (nodeName.equals("modelGroup")) {
                this.traverseModelGroup(element2);
            }
            else if (nodeName.equals("textEntity") || nodeName.equals("externalEntity") || nodeName.equals("unparsedEntity")) {
                final int addSymbol = this.fStringPool.addSymbol(element2.getAttribute("name"));
                if (nodeName.equals("textEntity")) {
                    this.fEntityPool.addEntityDecl(addSymbol, this.fStringPool.addString(element2.getFirstChild().getNodeValue()), -1, -1, -1, -1, true);
                }
                else {
                    final int addString = this.fStringPool.addString(element2.getAttribute("public"));
                    final int addString2 = this.fStringPool.addString(element2.getAttribute("system"));
                    if (nodeName.equals("externalEntity")) {
                        this.fEntityPool.addEntityDecl(addSymbol, -1, -1, addString, addString2, -1, true);
                    }
                    else {
                        this.fEntityPool.addEntityDecl(addSymbol, -1, -1, addString, addString2, this.fStringPool.addSymbol(element2.getAttribute("notation")), true);
                    }
                }
            }
            else if (nodeName.equals("notation")) {
                this.fEntityPool.addNotationDecl(this.fStringPool.addSymbol(element2.getAttribute("name")), this.fStringPool.addString(element2.getAttribute("public")), this.fStringPool.addString(element2.getAttribute("system")), true);
            }
        }
        this.cleanupForwardReferences();
    }
    
    private void cleanupForwardReferences() {
        final Enumeration<Integer> keys = this.fForwardRefs.keys();
        while (keys.hasMoreElements()) {
            this.cleanupForwardReferencesTo(keys.nextElement());
        }
    }
    
    private void cleanupForwardReferencesTo(final int n) {
        final Vector<Integer> vector = this.fForwardRefs.get(new Integer(n));
        if (vector == null) {
            return;
        }
        for (int i = 0; i < vector.size(); ++i) {
            vector.elementAt(i);
            this.fElementDeclPool.copyAtts(n, vector.elementAt(i));
        }
    }
    
    private void traverseComment(final Element element) {
    }
    
    private int traverseTypeDecl(final Element element) throws Exception {
        String s = element.getAttribute("name");
        final String attribute = element.getAttribute("content");
        element.getAttribute("model");
        final String attribute2 = element.getAttribute("order");
        final String attribute3 = element.getAttribute("type");
        final String attribute4 = element.getAttribute("default");
        final String attribute5 = element.getAttribute("fixed");
        final String attribute6 = element.getAttribute("schemaAbbrev");
        final String attribute7 = element.getAttribute("schemaName");
        if (s.equals("")) {
            s = "http://www.apache.org/xml/xerces/internalType" + this.fTypeCount++;
        }
        if (attribute3.equals("")) {
            if (!attribute6.equals("")) {
                this.reportSchemaError(4, new Object[] { "schemaAbbrev" });
            }
            if (!attribute7.equals("")) {
                this.reportSchemaError(4, new Object[] { "schemaName" });
            }
            if (!attribute4.equals("")) {
                this.reportSchemaError(4, new Object[] { "default" });
            }
            if (!attribute5.equals("")) {
                this.reportSchemaError(4, new Object[] { "fixed" });
            }
        }
        else {
            if (this.fDatatypeRegistry.getValidatorFor(attribute3) != null) {
                this.reportSchemaError(5, new Object[] { attribute3 });
            }
            if (!attribute.equals("textOnly")) {
                this.reportSchemaError(6, null);
            }
        }
        Element element2 = XUtil.getFirstChildElement(element);
        if (element2 != null && element2.getNodeName().equals("refines")) {
            this.reportSchemaError(7, new Object[] { "Refinement" });
            element2 = XUtil.getNextSiblingElement(element2);
        }
        int n = 0;
        int n2 = 0;
        boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        int[] array = null;
        int n3 = 0;
        int n4 = -2;
        int n5 = -2;
        boolean b4 = false;
        if (attribute2.equals("choice")) {
            n2 = 4;
            n = this.fStringPool.addSymbol("CHILDREN");
        }
        else if (attribute2.equals("seq")) {
            n2 = 5;
            n = this.fStringPool.addSymbol("CHILDREN");
        }
        else if (attribute2.equals("all")) {
            b3 = true;
            array = new int[((NodeImpl)element).getLength()];
            n3 = 0;
        }
        if (attribute.equals("empty")) {
            n = this.fStringPool.addSymbol("EMPTY");
            n4 = -1;
        }
        else if (attribute.equals("any")) {
            n = this.fStringPool.addSymbol("ANY");
            n4 = -1;
        }
        else if (attribute.equals("mixed")) {
            n = this.fStringPool.addSymbol("MIXED");
            b = true;
            n2 = 4;
        }
        else if (attribute.equals("elemOnly")) {
            b2 = true;
        }
        else if (attribute.equals("textOnly")) {}
        if (b) {
            n4 = this.fElementDeclPool.addContentSpecNode(0, -1, -1, false);
        }
        final Vector vector = new Vector<Integer>();
        while (element2 != null) {
            int n6 = -2;
            b4 = true;
            final String nodeName = element2.getNodeName();
            if (nodeName.equals("element")) {
                if (element2.getAttribute("ref").equals("")) {
                    if (b2) {
                        this.reportSchemaError(7, new Object[] { "Nesting element declarations" });
                    }
                    else {
                        this.reportSchemaError(8, null);
                    }
                }
                else if (b || b2) {
                    n6 = this.traverseElementRef(element2);
                }
                else {
                    this.reportSchemaError(9, null);
                }
            }
            else if (nodeName.equals("group")) {
                if (b2 && !b3) {
                    n6 = this.getContentSpec(this.getElement(this.traverseGroup(element2)));
                }
                else if (!b2) {
                    this.reportSchemaError(10, new Object[] { "group" });
                }
                else {
                    this.reportSchemaError(11, new Object[] { "group" });
                }
            }
            else if (nodeName.equals("modelGroupRef")) {
                if (b2 && !b3) {
                    final int contentSpec = this.getContentSpec(this.getElement(this.traverseModelGroup(element2)));
                    if (contentSpec == -1) {
                        this.reportSchemaError(7, new Object[] { "Forward reference to model group" });
                    }
                    n6 = this.expandContentModel(contentSpec, element2);
                }
                else if (!b2) {
                    this.reportSchemaError(10, new Object[] { "modelGroupRef" });
                }
                else {
                    this.reportSchemaError(11, new Object[] { "modelGroupRef" });
                }
            }
            else {
                if (nodeName.equals("attribute")) {
                    break;
                }
                if (nodeName.equals("attrGroupRef")) {
                    break;
                }
                if (attribute3.equals("")) {
                    this.reportSchemaError(12, null);
                }
                else {
                    this.reportSchemaError(13, new Object[] { nodeName });
                }
            }
            vector.addElement(new Integer(n6));
            if (b3) {
                array[n3++] = n6;
            }
            else if (n4 == -2) {
                n4 = n6;
            }
            else if (n5 == -2) {
                n5 = n6;
            }
            else {
                n4 = this.fElementDeclPool.addContentSpecNode(n2, n4, n5, false);
                n5 = n6;
            }
            element2 = XUtil.getNextSiblingElement(element2);
        }
        if (b3) {
            n4 = this.buildAllModel(array, n3);
        }
        else {
            if (b4 && n5 != -2) {
                n4 = this.fElementDeclPool.addContentSpecNode(n2, n4, n5, false);
            }
            if (b && b4) {
                n4 = this.fElementDeclPool.addContentSpecNode(2, n4, -1, false);
            }
        }
        final int addSymbol = this.fStringPool.addSymbol(s);
        final int addElementDecl = this.fElementDeclPool.addElementDecl(addSymbol, n, n4, false);
        for (int i = 0; i < vector.size(); ++i) {
            this.addUse(addSymbol, vector.elementAt(i));
        }
        while (element2 != null) {
            final String nodeName2 = element2.getNodeName();
            if (nodeName2.equals("attribute")) {
                this.traverseAttributeDecl(element2, addElementDecl);
            }
            else if (nodeName2.equals("attrGroupRef")) {
                final int traverseAttrGroupRef = this.traverseAttrGroupRef(element2);
                if (this.getContentSpec(this.getElement(traverseAttrGroupRef)) == -1) {
                    this.reportSchemaError(7, new Object[] { "Forward References to attrGroup" });
                    final Integer n7 = new Integer(traverseAttrGroupRef);
                    Vector<Integer> vector2;
                    if ((vector2 = this.fForwardRefs.get(n7)) == null) {
                        vector2 = new Vector<Integer>();
                    }
                    vector2.addElement(new Integer(addSymbol));
                    this.fForwardRefs.put(n7, vector2);
                    this.addUse(addSymbol, traverseAttrGroupRef);
                }
                else {
                    this.fElementDeclPool.copyAtts(traverseAttrGroupRef, addSymbol);
                }
            }
            element2 = XUtil.getNextSiblingElement(element2);
        }
        return addSymbol;
    }
    
    private int traverseGroup(final Element element) throws Exception {
        String s = element.getAttribute("name");
        element.getAttribute("collection");
        final String attribute = element.getAttribute("order");
        if (s.equals("")) {
            s = "http://www.apache.org/xml/xerces/internalGroup" + this.fGroupCount++;
        }
        Element element2 = XUtil.getFirstChildElement(element);
        int n = 0;
        int n2 = 0;
        boolean b = false;
        int[] array = null;
        int n3 = 0;
        if (attribute.equals("choice")) {
            n2 = 4;
            n = this.fStringPool.addSymbol("CHILDREN");
        }
        else if (attribute.equals("seq")) {
            n2 = 5;
            n = this.fStringPool.addSymbol("CHILDREN");
        }
        else if (attribute.equals("all")) {
            b = true;
            array = new int[((NodeImpl)element).getLength()];
            n3 = 0;
        }
        int n4 = -2;
        int n5 = -2;
        boolean b2 = false;
        final int[] array2 = new int[((NodeImpl)element).getLength()];
        int n6 = 0;
        while (element2 != null) {
            int n7 = -2;
            b2 = true;
            final String nodeName = element2.getNodeName();
            if (nodeName.equals("element")) {
                if (element2.getAttribute("ref").equals("")) {
                    this.reportSchemaError(7, new Object[] { "Nesting element declarations" });
                }
                else {
                    n7 = this.traverseElementRef(element2);
                }
            }
            else if (nodeName.equals("group")) {
                if (!b) {
                    final int traverseGroup = this.traverseGroup(element2);
                    array2[n6++] = traverseGroup;
                    n7 = this.getContentSpec(this.getElement(traverseGroup));
                }
                else {
                    this.reportSchemaError(11, new Object[] { "group" });
                }
            }
            else if (nodeName.equals("modelGroupRef")) {
                if (!b) {
                    n7 = this.expandContentModel(this.getContentSpec(this.getElement(this.traverseModelGroupRef(element2))), element2);
                }
                else {
                    this.reportSchemaError(11, new Object[] { "modelGroupRef" });
                }
            }
            else {
                this.reportSchemaError(14, new Object[] { "group", nodeName });
            }
            if (b) {
                array[n3++] = n7;
            }
            else if (n4 == -2) {
                n4 = n7;
            }
            else if (n5 == -2) {
                n5 = n7;
            }
            else {
                n4 = this.fElementDeclPool.addContentSpecNode(n2, n4, n5, false);
                n5 = n7;
            }
            element2 = XUtil.getNextSiblingElement(element2);
        }
        if (b) {
            n4 = this.buildAllModel(array, n3);
        }
        else if (b2 && n5 != -2) {
            n4 = this.fElementDeclPool.addContentSpecNode(n2, n4, n5, false);
        }
        final int expandContentModel = this.expandContentModel(n4, element);
        final int addSymbol = this.fStringPool.addSymbol(s);
        this.fElementDeclPool.addElementDecl(addSymbol, n, expandContentModel, false);
        return addSymbol;
    }
    
    private int traverseModelGroup(final Element element) throws Exception {
        String s = element.getAttribute("name");
        final String attribute = element.getAttribute("order");
        if (s.equals("")) {
            s = "http://www.apache.org/xml/xerces/internalModelGroup" + this.fModelGroupCount++;
        }
        Element element2 = XUtil.getFirstChildElement(element);
        int n = 0;
        int n2 = 0;
        boolean b = false;
        int[] array = null;
        int n3 = 0;
        if (attribute.equals("choice")) {
            n2 = 4;
            n = this.fStringPool.addSymbol("CHILDREN");
        }
        else if (attribute.equals("seq")) {
            n2 = 5;
            n = this.fStringPool.addSymbol("CHILDREN");
        }
        else if (attribute.equals("all")) {
            b = true;
            array = new int[((NodeImpl)element).getLength()];
            n3 = 0;
        }
        int n4 = -2;
        int n5 = -2;
        boolean b2 = false;
        while (element2 != null) {
            int n6 = -2;
            b2 = true;
            final String nodeName = element2.getNodeName();
            if (nodeName.equals("element")) {
                if (element2.getAttribute("ref").equals("")) {
                    this.reportSchemaError(7, new Object[] { "Nesting element declarations" });
                }
                else {
                    n6 = this.traverseElementRef(element2);
                }
            }
            else if (nodeName.equals("group")) {
                n6 = this.getContentSpec(this.getElement(this.traverseGroup(element2)));
            }
            else if (nodeName.equals("modelGroupRef")) {
                n6 = this.expandContentModel(this.getContentSpec(this.getElement(this.traverseModelGroupRef(element2))), element2);
            }
            else {
                this.reportSchemaError(14, new Object[] { "modelGroup", nodeName });
            }
            if (b) {
                array[n3++] = n6;
            }
            else if (n4 == -2) {
                n4 = n6;
            }
            else if (n5 == -2) {
                n5 = n6;
            }
            else {
                n4 = this.fElementDeclPool.addContentSpecNode(n2, n4, n5, false);
                n5 = n6;
            }
            element2 = XUtil.getNextSiblingElement(element2);
        }
        if (b) {
            n4 = this.buildAllModel(array, n3);
        }
        else if (b2 && n5 != -2) {
            n4 = this.fElementDeclPool.addContentSpecNode(n2, n4, n5, false);
        }
        final int expandContentModel = this.expandContentModel(n4, element);
        final int addSymbol = this.fStringPool.addSymbol(s);
        this.fElementDeclPool.addElementDecl(addSymbol, n, expandContentModel, false);
        return addSymbol;
    }
    
    private int traverseModelGroupRef(final Element element) {
        return this.fStringPool.addSymbol(element.getAttribute("name"));
    }
    
    public int traverseDatatypeDecl(final Element element) {
        final int addSymbol = this.fStringPool.addSymbol(element.getAttribute("name"));
        this.fStringPool.addSymbol(element.getAttribute("export"));
        final Element firstChildElement = XUtil.getFirstChildElement(element);
        this.fStringPool.addSymbol(firstChildElement.getNodeName());
        final DatatypeValidator validator = this.fDatatypeRegistry.getValidatorFor(firstChildElement.getAttribute("name"));
        if (validator == null) {
            this.reportSchemaError(15, new Object[] { firstChildElement.getAttribute("name"), element.getAttribute("name") });
            return -1;
        }
        int n = 0;
        int n2 = 0;
        final Hashtable<String, Vector<String>> facets = new Hashtable<String, Vector<String>>();
        final Vector<String> vector = new Vector<String>();
        for (Node node = firstChildElement.getNextSibling(); node != null; node = node.getNextSibling()) {
            if (node.getNodeType() == 1) {
                ++n;
                if (node.getNodeName().equals("enumeration")) {
                    for (Element element2 = XUtil.getFirstChildElement(node); element2 != null; element2 = XUtil.getNextSiblingElement(element2)) {
                        ++n2;
                        vector.addElement(element2.getFirstChild().getNodeValue());
                    }
                }
                else {
                    facets.put(node.getNodeName(), (Vector<String>)node.getFirstChild().getNodeValue());
                }
            }
        }
        if (n2 > 0) {
            facets.put("enumeration", vector);
        }
        try {
            final DatatypeValidator datatypeValidator = (DatatypeValidator)validator.getClass().newInstance();
            if (n > 0) {
                datatypeValidator.setFacets(facets);
            }
            this.fDatatypeRegistry.addValidator(this.fStringPool.toString(addSymbol), datatypeValidator);
        }
        catch (Exception ex) {
            this.reportSchemaError(21, new Object[] { ex.getMessage() });
        }
        return -1;
    }
    
    private int traverseElementDecl(final Element element) throws Exception {
        int n = -1;
        int n2 = -1;
        int traverseTypeDecl = -1;
        final String attribute = element.getAttribute("name");
        final String attribute2 = element.getAttribute("ref");
        final String attribute3 = element.getAttribute("archRef");
        final String attribute4 = element.getAttribute("type");
        element.getAttribute("schemaAbbrev");
        element.getAttribute("schemaName");
        element.getAttribute("minOccurs");
        element.getAttribute("maxOccurs");
        element.getAttribute("export");
        int n3 = 0;
        if (!attribute2.equals("")) {
            ++n3;
        }
        if (!attribute4.equals("")) {
            ++n3;
        }
        if (!attribute3.equals("")) {
            ++n3;
        }
        if (n3 > 1) {
            this.reportSchemaError(16, null);
        }
        if (!attribute2.equals("") || !attribute3.equals("")) {
            if (XUtil.getFirstChildElement(element) != null) {
                this.reportSchemaError(17, null);
            }
            final int n4 = attribute2.equals("") ? this.fStringPool.addSymbol(attribute3) : this.fStringPool.addSymbol(attribute2);
            final int contentSpec = this.getContentSpec(this.getElement(n4));
            final int contentSpecType = this.getContentSpecType(this.getElement(n4));
            final int addSymbol = this.fStringPool.addSymbol(attribute);
            if (contentSpec == -1) {
                this.fElementDeclPool.addElementDecl(addSymbol, 0, this.fElementDeclPool.addContentSpecNode(0, addSymbol, -1, false), true);
                this.reportSchemaError(7, new Object[] { "Forward references to archetypes" });
                final Integer n5 = new Integer(n4);
                Vector<Object> vector;
                if ((vector = this.fForwardRefs.get(n5)) == null) {
                    vector = new Vector<Object>();
                }
                vector.addElement(new Integer(addSymbol));
                this.fForwardRefs.put(n5, vector);
                this.addUse(addSymbol, n4);
            }
            else {
                this.fElementDeclPool.addElementDecl(addSymbol, contentSpecType, contentSpec, true);
                this.fElementDeclPool.copyAtts(n4, addSymbol);
            }
            return addSymbol;
        }
        final Element firstChildElement = XUtil.getFirstChildElement(element);
        if (firstChildElement != null) {
            final String nodeName = firstChildElement.getNodeName();
            if (nodeName.equals("archetype")) {
                traverseTypeDecl = this.traverseTypeDecl(firstChildElement);
                n2 = this.getContentSpec(this.getElement(traverseTypeDecl));
                n = this.getContentSpecType(this.getElement(traverseTypeDecl));
            }
            else if (nodeName.equals("datatype")) {
                this.reportSchemaError(7, new Object[] { "Nesting datatype declarations" });
            }
            else if (!attribute4.equals("")) {
                n = this.fStringPool.addSymbol("DATATYPE");
                n2 = this.expandContentModel(this.fElementDeclPool.addContentSpecNode(0, this.fStringPool.addSymbol(firstChildElement.getAttribute("name")), -1, false), firstChildElement);
            }
            else if (attribute4.equals("")) {
                n = this.fStringPool.addSymbol("CHILDREN");
                this.fElementDeclPool.addContentSpecNode(0, this.fStringPool.addSymbol(firstChildElement.getAttribute("name")), -1, false);
                n2 = this.expandContentModel(n2, firstChildElement);
            }
            else {
                System.out.println("unhandled case in element decl code");
            }
        }
        else if (!attribute4.equals("")) {
            n = this.fStringPool.addSymbol("DATATYPE");
            n2 = this.expandContentModel(this.fElementDeclPool.addContentSpecNode(0, this.fStringPool.addSymbol(attribute4), -1, false), element);
        }
        final int addSymbol2 = this.fStringPool.addSymbol(element.getAttribute("name"));
        this.fElementDeclPool.addElementDecl(addSymbol2, n, n2, true);
        this.fElementDeclPool.copyAtts(traverseTypeDecl, addSymbol2);
        return addSymbol2;
    }
    
    private int traverseElementRef(final Element element) {
        final int addSymbol = this.fStringPool.addSymbol(element.getAttribute("ref"));
        int n = 0;
        try {
            n = this.fElementDeclPool.addContentSpecNode(0, addSymbol, -1, false);
            n = this.expandContentModel(n, element);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    private void traverseAttributeDecl(final Element element, final int n) throws Exception {
        final int addSymbol = this.fStringPool.addSymbol(element.getAttribute("name"));
        int n2 = -1;
        final String attribute = element.getAttribute("type");
        int n3;
        if (attribute.equals("")) {
            n3 = this.fStringPool.addSymbol("CDATA");
        }
        else if (attribute.equals("string")) {
            n3 = this.fStringPool.addSymbol("CDATA");
        }
        else if (attribute.equals("ID")) {
            n3 = this.fStringPool.addSymbol("ID");
        }
        else if (attribute.equals("IDREF")) {
            n3 = this.fStringPool.addSymbol("IDREF");
        }
        else if (attribute.equals("IDREFS")) {
            n3 = this.fStringPool.addSymbol("IDREFS");
        }
        else if (attribute.equals("ENTITY")) {
            n3 = this.fStringPool.addSymbol("ENTITY");
        }
        else if (attribute.equals("ENTITIES")) {
            n3 = this.fStringPool.addSymbol("ENTITIES");
        }
        else if (attribute.equals("NMTOKEN")) {
            final Element firstChildElement = XUtil.getFirstChildElement(element, "enumeration");
            if (firstChildElement == null) {
                n3 = this.fStringPool.addSymbol("NMTOKEN");
            }
            else {
                n3 = this.fStringPool.addSymbol("ENUMERATION");
                n2 = this.fStringPool.startStringList();
                for (Element element2 = XUtil.getFirstChildElement(firstChildElement, "literal"); element2 != null; element2 = XUtil.getNextSiblingElement(element2, "literal")) {
                    this.fStringPool.addStringToList(n2, this.fStringPool.addSymbol(element2.getFirstChild().getNodeValue()));
                }
                this.fStringPool.finishStringList(n2);
            }
        }
        else if (attribute.equals("NMTOKENS")) {
            n3 = this.fStringPool.addSymbol("NMTOKENS");
        }
        else if (attribute.equals("notation")) {
            n3 = this.fStringPool.addSymbol("NOTATION");
        }
        else {
            n3 = this.fStringPool.addSymbol("DATATYPE");
            n2 = this.fStringPool.addSymbol(attribute);
        }
        int n4 = -1;
        int n5;
        if (element.getAttribute("required").equals("true")) {
            n5 = this.fStringPool.addSymbol("#REQUIRED");
        }
        else {
            final String attribute2 = element.getAttribute("fixed");
            if (!attribute2.equals("")) {
                n5 = this.fStringPool.addSymbol("#FIXED");
                n4 = this.fStringPool.addString(attribute2);
            }
            else {
                final String attribute3 = element.getAttribute("default");
                if (!attribute3.equals("")) {
                    n5 = this.fStringPool.addSymbol("");
                    n4 = this.fStringPool.addString(attribute3);
                }
                else {
                    n5 = this.fStringPool.addSymbol("#IMPLIED");
                }
            }
            if (n3 == this.fStringPool.addSymbol("DATATYPE") && n4 != -1) {
                try {
                    final String string = this.fStringPool.toString(n2);
                    final DatatypeValidator validator = this.fDatatypeRegistry.getValidatorFor(string);
                    if (validator != null) {
                        validator.validate(this.fStringPool.toString(n4));
                    }
                    else {
                        this.reportSchemaError(2, new Object[] { string });
                    }
                }
                catch (InvalidDatatypeValueException ex) {
                    this.reportSchemaError(18, new Object[] { element.getAttribute("name"), ex.getMessage() });
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                    System.out.println("Internal error in attribute datatype validation");
                }
            }
        }
        this.fElementDeclPool.addAttDef(n, addSymbol, n3, n2, n5, n4, true, true, true);
    }
    
    private int traverseAttrGroup(final Element element) throws Exception {
        String s = element.getAttribute("name");
        if (s.equals("")) {
            s = "http://www.apache.org/xml/xerces/internalGroup" + this.fGroupCount++;
        }
        Element element2 = XUtil.getFirstChildElement(element);
        final int[] array = new int[((NodeImpl)element).getLength()];
        int n = 0;
        while (element2 != null) {
            final String nodeName = element2.getNodeName();
            if (nodeName.equals("attrGroupRef")) {
                array[n++] = this.traverseAttrGroupRef(element2);
                if (this.getContentSpec(this.getElement(array[n - 1])) == -1) {
                    this.reportSchemaError(7, new Object[] { "Forward reference to AttrGroup" });
                }
            }
            else if (!nodeName.equals("attribute")) {
                this.reportSchemaError(19, new Object[] { nodeName });
            }
            element2 = XUtil.getNextSiblingElement(element2);
        }
        final int addSymbol = this.fStringPool.addSymbol(s);
        final int addElementDecl = this.fElementDeclPool.addElementDecl(addSymbol, 0, 0, false);
        for (Element element3 = XUtil.getFirstChildElement(element); element3 != null; element3 = XUtil.getNextSiblingElement(element3)) {
            final String nodeName2 = element3.getNodeName();
            if (nodeName2.equals("attribute")) {
                this.traverseAttributeDecl(element3, addElementDecl);
            }
            else if (nodeName2.equals("attrGroup")) {
                final int traverseAttrGroupRef = this.traverseAttrGroupRef(element3);
                if (this.getContentSpec(this.getElement(traverseAttrGroupRef)) == -1) {
                    this.reportSchemaError(7, new Object[] { "Forward reference to AttrGroup" });
                    final Integer n2 = new Integer(traverseAttrGroupRef);
                    Vector<Integer> vector;
                    if ((vector = this.fForwardRefs.get(n2)) == null) {
                        vector = new Vector<Integer>();
                    }
                    vector.addElement(new Integer(addSymbol));
                    this.fForwardRefs.put(n2, vector);
                    this.addUse(addSymbol, traverseAttrGroupRef);
                }
                else {
                    array[n++] = this.getContentSpec(this.getElement(traverseAttrGroupRef));
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            this.fElementDeclPool.copyAtts(array[i], addSymbol);
        }
        return addSymbol;
    }
    
    private int traverseAttrGroupRef(final Element element) {
        return this.fStringPool.addSymbol(element.getAttribute("name"));
    }
    
    private void addUse(final int n, final int n2) {
        this.addUse(n, new Integer(n2));
    }
    
    private void addUse(final int n, final Integer n2) {
        Vector<Integer> vector = this.fAttrGroupUses.get(new Integer(n));
        if (vector == null) {
            vector = new Vector<Integer>();
        }
        vector.addElement(n2);
    }
    
    private int buildAllModel(final int[] array, final int n) throws Exception {
        if (n > 1) {
            final XMLContentSpecNode xmlContentSpecNode = new XMLContentSpecNode();
            xmlContentSpecNode.type = 4;
            xmlContentSpecNode.value = -1;
            xmlContentSpecNode.otherValue = -1;
            this.sort(array, 0, n);
            return this.buildAllModel(array, 0, xmlContentSpecNode);
        }
        if (n > 0) {
            return array[0];
        }
        return -1;
    }
    
    private int buildAllModel(final int[] array, final int n, final XMLContentSpecNode xmlContentSpecNode) throws Exception {
        if (array.length - n == 2) {
            final int seq = this.createSeq(array);
            if (xmlContentSpecNode.value == -1) {
                xmlContentSpecNode.value = seq;
            }
            else {
                if (xmlContentSpecNode.otherValue != -1) {
                    xmlContentSpecNode.value = this.fElementDeclPool.addContentSpecNode(xmlContentSpecNode.type, xmlContentSpecNode.value, xmlContentSpecNode.otherValue, false);
                }
                xmlContentSpecNode.otherValue = seq;
            }
            this.swap(array, n, n + 1);
            final int seq2 = this.createSeq(array);
            if (xmlContentSpecNode.value == -1) {
                xmlContentSpecNode.value = seq2;
            }
            else {
                if (xmlContentSpecNode.otherValue != -1) {
                    xmlContentSpecNode.value = this.fElementDeclPool.addContentSpecNode(xmlContentSpecNode.type, xmlContentSpecNode.value, xmlContentSpecNode.otherValue, false);
                }
                xmlContentSpecNode.otherValue = seq2;
            }
            return this.fElementDeclPool.addContentSpecNode(xmlContentSpecNode.type, xmlContentSpecNode.value, xmlContentSpecNode.otherValue, false);
        }
        for (int i = n; i < array.length - 1; ++i) {
            xmlContentSpecNode.value = this.buildAllModel(array, n + 1, xmlContentSpecNode);
            xmlContentSpecNode.otherValue = -1;
            this.sort(array, n, array.length - n);
            this.shift(array, n, i + 1);
        }
        final int buildAllModel = this.buildAllModel(array, n + 1, xmlContentSpecNode);
        this.sort(array, n, array.length - n);
        return buildAllModel;
    }
    
    private int createSeq(final int[] array) throws Exception {
        int addContentSpecNode = array[0];
        int n = array[1];
        for (int i = 2; i < array.length; ++i) {
            addContentSpecNode = this.fElementDeclPool.addContentSpecNode(5, addContentSpecNode, n, false);
            n = array[i];
        }
        return this.fElementDeclPool.addContentSpecNode(5, addContentSpecNode, n, false);
    }
    
    private void shift(final int[] array, final int n, final int n2) {
        final int n3 = array[n2];
        for (int i = n2; i > n; --i) {
            array[i] = array[i - 1];
        }
        array[n] = n3;
    }
    
    private void sort(final int[] array, final int n, final int n2) {
        for (int i = n; i < n + n2 - 1; ++i) {
            int n3 = i;
            for (int j = i + 1; j < n + n2; ++j) {
                if (array[j] < array[n3]) {
                    n3 = j;
                }
            }
            if (n3 != i) {
                final int n4 = array[i];
                array[i] = array[n3];
                array[n3] = n4;
            }
        }
    }
    
    private void swap(final int[] array, final int n, final int n2) {
        final int n3 = array[n];
        array[n] = array[n2];
        array[n2] = n3;
    }
    
    private int expandContentModel(int n, final Element element) throws Exception {
        final int occurrenceCount = this.getOccurrenceCount(element);
        int int1 = 1;
        int int2 = 1;
        if (!this.isSimpleOccurrenceCount(occurrenceCount)) {
            try {
                int1 = Integer.parseInt(element.getAttribute("minOccurs"));
            }
            catch (NumberFormatException ex) {
                this.reportSchemaError(20, new Object[] { "minOccurs" });
            }
            try {
                int2 = Integer.parseInt(element.getAttribute("maxOccurs"));
            }
            catch (NumberFormatException ex2) {
                this.reportSchemaError(20, new Object[] { "maxOccurs" });
            }
        }
        switch (occurrenceCount) {
            case 3: {
                n = this.fElementDeclPool.addContentSpecNode(3, n, -1, false);
                break;
            }
            case 2: {
                n = this.fElementDeclPool.addContentSpecNode(2, n, -1, false);
                break;
            }
            case 1: {
                n = this.fElementDeclPool.addContentSpecNode(1, n, -1, false);
                break;
            }
            case 8: {
                int n2 = n;
                int n3 = -1;
                for (int i = 1; i < int1; ++i) {
                    if (n3 != -1) {
                        n2 = this.fElementDeclPool.addContentSpecNode(5, n2, n3, false);
                    }
                    n3 = n;
                }
                n = this.fElementDeclPool.addContentSpecNode(2, n, -1, false);
                if (n3 != -1) {
                    n2 = this.fElementDeclPool.addContentSpecNode(5, n2, n3, false);
                }
                n = this.fElementDeclPool.addContentSpecNode(5, n2, n, false);
                break;
            }
            case 7: {
                int n4 = n;
                int n5 = -1;
                for (int j = 1; j < int1; ++j) {
                    if (n5 != -1) {
                        n4 = this.fElementDeclPool.addContentSpecNode(5, n4, n5, false);
                    }
                    n5 = n;
                }
                n = this.fElementDeclPool.addContentSpecNode(1, n, -1, false);
                for (int k = int2; k > int1; --k) {
                    if (n5 != -1) {
                        n4 = this.fElementDeclPool.addContentSpecNode(5, n4, n5, false);
                    }
                    n5 = n;
                }
                n = this.fElementDeclPool.addContentSpecNode(5, n4, n5, false);
                break;
            }
            case 6: {
                int n6;
                n = (n6 = this.fElementDeclPool.addContentSpecNode(1, n, -1, false));
                int n7 = -1;
                for (int l = 1; l < int2; ++l) {
                    if (n7 != -1) {
                        n6 = this.fElementDeclPool.addContentSpecNode(5, n6, n7, false);
                    }
                    n7 = n;
                }
                n = this.fElementDeclPool.addContentSpecNode(5, n6, n7, false);
                break;
            }
        }
        return n;
    }
    
    private boolean isSimpleOccurrenceCount(final int n) {
        return n == -1 || n == 3 || n == 2 || n == 1;
    }
    
    private int getOccurrenceCount(final Element element) {
        final String attribute = element.getAttribute("minOccurs");
        final String attribute2 = element.getAttribute("maxOccurs");
        if (attribute.equals("0")) {
            if (attribute2.equals("1") || attribute2.length() == 0) {
                return 1;
            }
            if (attribute2.equals("*")) {
                return 2;
            }
            return 6;
        }
        else if (attribute.equals("1") || attribute.length() == 0) {
            if (attribute2.equals("*")) {
                return 3;
            }
            if (!attribute2.equals("1") && attribute2.length() > 0) {
                return 7;
            }
            return -1;
        }
        else {
            if (attribute2.equals("*")) {
                return 8;
            }
            return 7;
        }
    }
    
    private void reportSchemaError(final int n, final Object[] array) {
        try {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/xml-schema-1", n, 0, array, 1);
        }
        catch (Exception ex) {
            ex.printStackTrace();
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
            SYSTEM = new String[] { "http://www.w3.org/XML/Group/1999/09/23-xmlschema/structures/structures.dtd", "http://www.w3.org/XML/Group/1999/09/23-xmlschema/datatypes/datatypes.dtd" };
            PATH = new String[] { "structures.dtd", "datatypes.dtd" };
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
    
    class DatatypeValidatorRegistry
    {
        Hashtable fRegistry;
        String[][] integerSubtypeTable;
        
        void initializeRegistry() {
            this.fRegistry.put("boolean", new BooleanValidator());
            final IntegerValidator basetype = new IntegerValidator();
            this.fRegistry.put("integer", basetype);
            this.fRegistry.put("string", new StringValidator());
            this.fRegistry.put("real", new RealValidator());
            this.fRegistry.put("decimal", new DecimalValidator());
            for (int i = 0; i < this.integerSubtypeTable.length; ++i) {
                final IntegerValidator integerValidator = new IntegerValidator();
                final Hashtable<String, String> facets = new Hashtable<String, String>();
                facets.put(this.integerSubtypeTable[i][1], this.integerSubtypeTable[i][2]);
                integerValidator.setBasetype(basetype);
                try {
                    integerValidator.setFacets(facets);
                }
                catch (IllegalFacetException ex) {
                    System.out.println("Internal error initializing registry - Illegal facet: " + this.integerSubtypeTable[i][0]);
                }
                catch (IllegalFacetValueException ex2) {
                    System.out.println("Internal error initializing registry - Illegal facet value: " + this.integerSubtypeTable[i][0]);
                }
                catch (UnknownFacetException ex3) {
                    System.out.println("Internal error initializing registry - Unknown facet: " + this.integerSubtypeTable[i][0]);
                }
                this.fRegistry.put(this.integerSubtypeTable[0], integerValidator);
            }
        }
        
        DatatypeValidator getValidatorFor(final String s) {
            return this.fRegistry.get(s);
        }
        
        void addValidator(final String s, final DatatypeValidator datatypeValidator) {
            this.fRegistry.put(s, datatypeValidator);
        }
        
        DatatypeValidatorRegistry() {
            this.fRegistry = new Hashtable();
            this.integerSubtypeTable = new String[][] { { "non-negative-integer", "minInclusive", "0" }, { "postive-integer", "minInclusive", "1" }, { "non-positive-integer", "maxInclusive", "0" }, { "negative-integer", "maxInclusive", "-1" } };
        }
    }
}
