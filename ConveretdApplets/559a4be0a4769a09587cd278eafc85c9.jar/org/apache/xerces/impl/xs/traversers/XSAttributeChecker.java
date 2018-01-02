// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.XSAttributeDecl;
import org.apache.xerces.impl.xs.SchemaGrammar;
import java.util.Enumeration;
import org.apache.xerces.impl.xs.XSGrammarBucket;
import java.util.StringTokenizer;
import org.w3c.dom.Attr;
import org.apache.xerces.impl.xs.SchemaNamespaceSupport;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.QName;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.dv.ValidationContext;
import java.util.Locale;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.w3c.dom.Node;
import org.apache.xerces.util.DOMUtil;
import org.w3c.dom.Element;
import java.util.Vector;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.impl.dv.XSSimpleType;
import java.util.Hashtable;
import org.apache.xerces.impl.xs.util.XInt;
import org.apache.xerces.impl.xs.util.XIntPool;

public class XSAttributeChecker
{
    private static final String ELEMENT_N = "element_n";
    private static final String ELEMENT_R = "element_r";
    private static final String ATTRIBUTE_N = "attribute_n";
    private static final String ATTRIBUTE_R = "attribute_r";
    private static int ATTIDX_COUNT;
    public static final int ATTIDX_ABSTRACT;
    public static final int ATTIDX_AFORMDEFAULT;
    public static final int ATTIDX_BASE;
    public static final int ATTIDX_BLOCK;
    public static final int ATTIDX_BLOCKDEFAULT;
    public static final int ATTIDX_DEFAULT;
    public static final int ATTIDX_EFORMDEFAULT;
    public static final int ATTIDX_FINAL;
    public static final int ATTIDX_FINALDEFAULT;
    public static final int ATTIDX_FIXED;
    public static final int ATTIDX_FORM;
    public static final int ATTIDX_ID;
    public static final int ATTIDX_ITEMTYPE;
    public static final int ATTIDX_MAXOCCURS;
    public static final int ATTIDX_MEMBERTYPES;
    public static final int ATTIDX_MINOCCURS;
    public static final int ATTIDX_MIXED;
    public static final int ATTIDX_NAME;
    public static final int ATTIDX_NAMESPACE;
    public static final int ATTIDX_NAMESPACE_LIST;
    public static final int ATTIDX_NILLABLE;
    public static final int ATTIDX_PROCESSCONTENTS;
    public static final int ATTIDX_PUBLIC;
    public static final int ATTIDX_REF;
    public static final int ATTIDX_REFER;
    public static final int ATTIDX_SCHEMALOCATION;
    public static final int ATTIDX_SOURCE;
    public static final int ATTIDX_SUBSGROUP;
    public static final int ATTIDX_SYSTEM;
    public static final int ATTIDX_TARGETNAMESPACE;
    public static final int ATTIDX_TYPE;
    public static final int ATTIDX_USE;
    public static final int ATTIDX_VALUE;
    public static final int ATTIDX_ENUMNSDECLS;
    public static final int ATTIDX_VERSION;
    public static final int ATTIDX_XPATH;
    public static final int ATTIDX_FROMDEFAULT;
    public static final int ATTIDX_ISRETURNED;
    private static final XIntPool fXIntPool;
    private static final XInt INT_QUALIFIED;
    private static final XInt INT_UNQUALIFIED;
    private static final XInt INT_EMPTY_SET;
    private static final XInt INT_ANY_STRICT;
    private static final XInt INT_ANY_LAX;
    private static final XInt INT_ANY_SKIP;
    private static final XInt INT_ANY_ANY;
    private static final XInt INT_ANY_LIST;
    private static final XInt INT_ANY_NOT;
    private static final XInt INT_USE_OPTIONAL;
    private static final XInt INT_USE_REQUIRED;
    private static final XInt INT_USE_PROHIBITED;
    private static final XInt INT_WS_PRESERVE;
    private static final XInt INT_WS_REPLACE;
    private static final XInt INT_WS_COLLAPSE;
    private static final XInt INT_UNBOUNDED;
    private static final Hashtable fEleAttrsMapG;
    private static final Hashtable fEleAttrsMapL;
    protected static final int DT_ANYURI = 0;
    protected static final int DT_ID = 1;
    protected static final int DT_QNAME = 2;
    protected static final int DT_STRING = 3;
    protected static final int DT_TOKEN = 4;
    protected static final int DT_NCNAME = 5;
    protected static final int DT_XPATH = 6;
    protected static final int DT_XPATH1 = 7;
    protected static final int DT_COUNT = 8;
    private static final XSSimpleType[] fExtraDVs;
    protected static final int DT_BLOCK = -1;
    protected static final int DT_BLOCK1 = -2;
    protected static final int DT_FINAL = -3;
    protected static final int DT_FINAL1 = -4;
    protected static final int DT_FORM = -5;
    protected static final int DT_MAXOCCURS = -6;
    protected static final int DT_MAXOCCURS1 = -7;
    protected static final int DT_MEMBERTYPES = -8;
    protected static final int DT_MINOCCURS1 = -9;
    protected static final int DT_NAMESPACE = -10;
    protected static final int DT_PROCESSCONTENTS = -11;
    protected static final int DT_NSTRING = -12;
    protected static final int DT_USE = -13;
    protected static final int DT_WHITESPACE = -14;
    protected static final int DT_BOOLEAN = -15;
    protected static final int DT_NONNEGINT = -16;
    protected XSDHandler fSchemaHandler;
    protected SymbolTable fSymbolTable;
    protected Hashtable fNonSchemaAttrs;
    protected Vector fNamespaceList;
    protected boolean[] fSeen;
    private static boolean[] fSeenTemp;
    static final int INIT_POOL_SIZE = 10;
    static final int INC_POOL_SIZE = 10;
    Object[][] fArrayPool;
    private static Object[] fTempArray;
    int fPoolPos;
    
    public XSAttributeChecker(final XSDHandler schemaHandler) {
        this.fSchemaHandler = null;
        this.fSymbolTable = null;
        this.fNonSchemaAttrs = new Hashtable();
        this.fNamespaceList = new Vector();
        this.fSeen = new boolean[XSAttributeChecker.ATTIDX_COUNT];
        this.fArrayPool = new Object[10][XSAttributeChecker.ATTIDX_COUNT];
        this.fPoolPos = 0;
        this.fSchemaHandler = schemaHandler;
    }
    
    public void reset(final SymbolTable symbolTable) {
        this.fSymbolTable = symbolTable;
        this.fNonSchemaAttrs.clear();
    }
    
    public Object[] checkAttributes(final Element element, final boolean isGlobal, final XSDocumentInfo schemaDoc) {
        return this.checkAttributes(element, isGlobal, schemaDoc, false);
    }
    
    public Object[] checkAttributes(final Element element, final boolean isGlobal, final XSDocumentInfo schemaDoc, final boolean enumAsQName) {
        if (element == null) {
            return null;
        }
        final Attr[] attrs = DOMUtil.getAttrs(element);
        this.resolveNamespace(element, attrs, schemaDoc.fNamespaceSupport);
        final String uri = DOMUtil.getNamespaceURI(element);
        final String elName = DOMUtil.getLocalName(element);
        if (!SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(uri)) {
            this.reportSchemaError("s4s-elt-schema-ns", new Object[] { elName }, element);
        }
        Hashtable eleAttrsMap = XSAttributeChecker.fEleAttrsMapG;
        String lookupName = elName;
        if (!isGlobal) {
            eleAttrsMap = XSAttributeChecker.fEleAttrsMapL;
            if (elName.equals(SchemaSymbols.ELT_ELEMENT)) {
                if (DOMUtil.getAttr(element, SchemaSymbols.ATT_REF) != null) {
                    lookupName = "element_r";
                }
                else {
                    lookupName = "element_n";
                }
            }
            else if (elName.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
                if (DOMUtil.getAttr(element, SchemaSymbols.ATT_REF) != null) {
                    lookupName = "attribute_r";
                }
                else {
                    lookupName = "attribute_n";
                }
            }
        }
        final OneElement oneEle = eleAttrsMap.get(lookupName);
        if (oneEle == null) {
            this.reportSchemaError("s4s-elt-invalid", new Object[] { elName }, element);
            return null;
        }
        final Object[] attrValues = this.getAvailableArray();
        long fromDefault = 0L;
        final Container attrList = oneEle.attrList;
        System.arraycopy(XSAttributeChecker.fSeenTemp, 0, this.fSeen, 0, XSAttributeChecker.ATTIDX_COUNT);
        final int length = attrs.length;
        Attr sattr = null;
        for (int i = 0; i < length; ++i) {
            sattr = attrs[i];
            final String attrName = sattr.getName();
            final String attrVal = DOMUtil.getValue(sattr);
            if (!attrName.toLowerCase(Locale.ENGLISH).startsWith("xml")) {
                final String attrURI = DOMUtil.getNamespaceURI(sattr);
                if (attrURI != null && attrURI.length() != 0) {
                    if (attrURI.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) || !oneEle.allowNonSchemaAttr) {
                        this.reportSchemaError("s4s-att-not-allowed", new Object[] { elName, attrName }, element);
                    }
                    else {
                        final String attrRName = attrURI + "," + attrName;
                        Vector values = this.fNonSchemaAttrs.get(attrRName);
                        if (values == null) {
                            values = new Vector();
                            values.addElement(attrName);
                            values.addElement(elName);
                            values.addElement(attrVal);
                            this.fNonSchemaAttrs.put(attrRName, values);
                        }
                        else {
                            values.addElement(elName);
                            values.addElement(attrVal);
                        }
                    }
                }
                else {
                    final OneAttr oneAttr = attrList.get(attrName);
                    if (oneAttr == null) {
                        this.reportSchemaError("s4s-att-not-allowed", new Object[] { elName, attrName }, element);
                    }
                    else {
                        this.fSeen[oneAttr.valueIndex] = true;
                        try {
                            if (oneAttr.dvIndex >= 0) {
                                if (oneAttr.dvIndex != 3 && oneAttr.dvIndex != 6 && oneAttr.dvIndex != 7) {
                                    final XSSimpleType dv = XSAttributeChecker.fExtraDVs[oneAttr.dvIndex];
                                    final Object avalue = dv.validate(attrVal, schemaDoc.fValidationContext, null);
                                    if (oneAttr.dvIndex == 2) {
                                        final QName qname = (QName)avalue;
                                        if (qname.prefix == XMLSymbols.EMPTY_STRING && qname.uri == null && schemaDoc.fIsChameleonSchema) {
                                            qname.uri = schemaDoc.fTargetNamespace;
                                        }
                                    }
                                    attrValues[oneAttr.valueIndex] = avalue;
                                }
                                else {
                                    attrValues[oneAttr.valueIndex] = attrVal;
                                }
                            }
                            else {
                                attrValues[oneAttr.valueIndex] = this.validate(attrValues, attrName, attrVal, oneAttr.dvIndex, schemaDoc);
                            }
                        }
                        catch (InvalidDatatypeValueException ide) {
                            this.reportSchemaError("s4s-att-invalid-value", new Object[] { elName, attrName, ide.getKey() }, element);
                            if (oneAttr.dfltValue != null) {
                                attrValues[oneAttr.valueIndex] = oneAttr.dfltValue;
                            }
                        }
                        if (elName.equals(SchemaSymbols.ELT_ENUMERATION) && enumAsQName) {
                            attrValues[XSAttributeChecker.ATTIDX_ENUMNSDECLS] = new SchemaNamespaceSupport(schemaDoc.fNamespaceSupport);
                        }
                    }
                }
            }
        }
        final OneAttr[] reqAttrs = oneEle.attrList.values;
        for (int j = 0; j < reqAttrs.length; ++j) {
            final OneAttr oneAttr2 = reqAttrs[j];
            if (oneAttr2.dfltValue != null && !this.fSeen[oneAttr2.valueIndex]) {
                attrValues[oneAttr2.valueIndex] = oneAttr2.dfltValue;
                fromDefault |= 1 << oneAttr2.valueIndex;
            }
        }
        attrValues[XSAttributeChecker.ATTIDX_FROMDEFAULT] = new Long(fromDefault);
        if (attrValues[XSAttributeChecker.ATTIDX_MAXOCCURS] != null) {
            final int min = ((XInt)attrValues[XSAttributeChecker.ATTIDX_MINOCCURS]).intValue();
            final int max = ((XInt)attrValues[XSAttributeChecker.ATTIDX_MAXOCCURS]).intValue();
            if (max != -1 && min > max) {
                this.reportSchemaError("p-props-correct.2.1", new Object[] { elName, attrValues[XSAttributeChecker.ATTIDX_MINOCCURS], attrValues[XSAttributeChecker.ATTIDX_MAXOCCURS] }, element);
                attrValues[XSAttributeChecker.ATTIDX_MINOCCURS] = attrValues[XSAttributeChecker.ATTIDX_MAXOCCURS];
            }
        }
        return attrValues;
    }
    
    private Object validate(final Object[] attrValues, final String attr, final String ivalue, final int dvIndex, final XSDocumentInfo schemaDoc) throws InvalidDatatypeValueException {
        if (ivalue == null) {
            return null;
        }
        final String value = ivalue.trim();
        Object retValue = null;
        switch (dvIndex) {
            case -15: {
                if (value.equals("false") || value.equals("0")) {
                    retValue = Boolean.FALSE;
                    break;
                }
                if (value.equals("true") || value.equals("1")) {
                    retValue = Boolean.TRUE;
                    break;
                }
                throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { value, "boolean" });
            }
            case -16: {
                try {
                    retValue = XSAttributeChecker.fXIntPool.getXInt(Integer.parseInt(value));
                }
                catch (NumberFormatException e) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { value, "nonNegativeInteger" });
                }
                if (((XInt)retValue).intValue() < 0) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { value, "nonNegativeInteger" });
                }
                break;
            }
            case -1: {
                int choice = 0;
                if (value.equals("#all")) {
                    choice = 31;
                }
                else {
                    final StringTokenizer t = new StringTokenizer(value);
                    while (t.hasMoreTokens()) {
                        final String token = t.nextToken();
                        if (token.equals("substitution")) {
                            choice |= 0x4;
                        }
                        else if (token.equals("extension")) {
                            choice |= 0x1;
                        }
                        else if (token.equals("restriction")) {
                            choice |= 0x2;
                        }
                        else if (token.equals("list")) {
                            choice |= 0x10;
                        }
                        else {
                            if (!token.equals("union")) {
                                throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[] { value, "(#all | List of (substitution | extension | restriction | list | union))" });
                            }
                            choice |= 0x2;
                        }
                    }
                }
                retValue = XSAttributeChecker.fXIntPool.getXInt(choice);
                break;
            }
            case -3:
            case -2: {
                int choice = 0;
                if (value.equals("#all")) {
                    choice = 31;
                }
                else {
                    final StringTokenizer t = new StringTokenizer(value);
                    while (t.hasMoreTokens()) {
                        final String token = t.nextToken();
                        if (token.equals("extension")) {
                            choice |= 0x1;
                        }
                        else {
                            if (!token.equals("restriction")) {
                                throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[] { value, "(#all | List of (extension | restriction))" });
                            }
                            choice |= 0x2;
                        }
                    }
                }
                retValue = XSAttributeChecker.fXIntPool.getXInt(choice);
                break;
            }
            case -4: {
                int choice = 0;
                if (value.equals("#all")) {
                    choice = 31;
                }
                else if (value.equals("list")) {
                    choice = 16;
                }
                else if (value.equals("union")) {
                    choice = 8;
                }
                else {
                    if (!value.equals("restriction")) {
                        throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[] { value, "(#all | (list | union | restriction))" });
                    }
                    choice = 2;
                }
                retValue = XSAttributeChecker.fXIntPool.getXInt(choice);
                break;
            }
            case -5: {
                if (value.equals("qualified")) {
                    retValue = XSAttributeChecker.INT_QUALIFIED;
                    break;
                }
                if (value.equals("unqualified")) {
                    retValue = XSAttributeChecker.INT_UNQUALIFIED;
                    break;
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[] { value, "(qualified | unqualified)" });
            }
            case -6: {
                if (value.equals("unbounded")) {
                    retValue = XSAttributeChecker.INT_UNBOUNDED;
                    break;
                }
                try {
                    retValue = this.validate(attrValues, attr, value, -16, schemaDoc);
                }
                catch (NumberFormatException e) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[] { value, "(nonNegativeInteger | unbounded)" });
                }
                break;
            }
            case -7: {
                if (value.equals("1")) {
                    retValue = XSAttributeChecker.fXIntPool.getXInt(1);
                    break;
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[] { value, "(1)" });
            }
            case -8: {
                final Vector memberType = new Vector();
                try {
                    final StringTokenizer t = new StringTokenizer(value);
                    while (t.hasMoreTokens()) {
                        final String token = t.nextToken();
                        final QName qname = (QName)XSAttributeChecker.fExtraDVs[2].validate(token, schemaDoc.fValidationContext, null);
                        if (qname.prefix == XMLSymbols.EMPTY_STRING && qname.uri == null && schemaDoc.fIsChameleonSchema) {
                            qname.uri = schemaDoc.fTargetNamespace;
                        }
                        memberType.addElement(qname);
                    }
                    retValue = memberType;
                }
                catch (InvalidDatatypeValueException ide) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.2", new Object[] { value, "(List of QName)" });
                }
                break;
            }
            case -9: {
                if (value.equals("0")) {
                    retValue = XSAttributeChecker.fXIntPool.getXInt(0);
                    break;
                }
                if (value.equals("1")) {
                    retValue = XSAttributeChecker.fXIntPool.getXInt(1);
                    break;
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[] { value, "(0 | 1)" });
            }
            case -10: {
                if (value.equals("##any")) {
                    retValue = XSAttributeChecker.INT_ANY_ANY;
                    break;
                }
                if (value.equals("##other")) {
                    retValue = XSAttributeChecker.INT_ANY_NOT;
                    final String[] list = { schemaDoc.fTargetNamespace, null };
                    attrValues[XSAttributeChecker.ATTIDX_NAMESPACE_LIST] = list;
                    break;
                }
                retValue = XSAttributeChecker.INT_ANY_LIST;
                this.fNamespaceList.removeAllElements();
                final StringTokenizer tokens = new StringTokenizer(value);
                try {
                    while (tokens.hasMoreTokens()) {
                        final String token = tokens.nextToken();
                        String tempNamespace;
                        if (token.equals("##local")) {
                            tempNamespace = null;
                        }
                        else if (token.equals("##targetNamespace")) {
                            tempNamespace = schemaDoc.fTargetNamespace;
                        }
                        else {
                            XSAttributeChecker.fExtraDVs[0].validate(token, schemaDoc.fValidationContext, null);
                            tempNamespace = this.fSymbolTable.addSymbol(token);
                        }
                        if (!this.fNamespaceList.contains(tempNamespace)) {
                            this.fNamespaceList.addElement(tempNamespace);
                        }
                    }
                }
                catch (InvalidDatatypeValueException ide2) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[] { value, "((##any | ##other) | List of (anyURI | (##targetNamespace | ##local)) )" });
                }
                final int num = this.fNamespaceList.size();
                final String[] list2 = new String[num];
                this.fNamespaceList.copyInto(list2);
                attrValues[XSAttributeChecker.ATTIDX_NAMESPACE_LIST] = list2;
                break;
            }
            case -11: {
                if (value.equals("strict")) {
                    retValue = XSAttributeChecker.INT_ANY_STRICT;
                    break;
                }
                if (value.equals("lax")) {
                    retValue = XSAttributeChecker.INT_ANY_LAX;
                    break;
                }
                if (value.equals("skip")) {
                    retValue = XSAttributeChecker.INT_ANY_SKIP;
                    break;
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[] { value, "(lax | skip | strict)" });
            }
            case -12: {
                final StringBuffer buf = new StringBuffer(value);
                boolean changed = false;
                for (int len = value.length(), i = 0; i < len; ++i) {
                    final char ch = buf.charAt(i);
                    if (ch == '\t' || ch == '\n' || ch == '\r') {
                        buf.setCharAt(i, ' ');
                        changed = true;
                    }
                }
                retValue = (changed ? buf.toString() : value);
                break;
            }
            case -13: {
                if (value.equals("optional")) {
                    retValue = XSAttributeChecker.INT_USE_OPTIONAL;
                    break;
                }
                if (value.equals("required")) {
                    retValue = XSAttributeChecker.INT_USE_REQUIRED;
                    break;
                }
                if (value.equals("prohibited")) {
                    retValue = XSAttributeChecker.INT_USE_PROHIBITED;
                    break;
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[] { value, "(optional | prohibited | required)" });
            }
            case -14: {
                if (value.equals("preserve")) {
                    retValue = XSAttributeChecker.INT_WS_PRESERVE;
                    break;
                }
                if (value.equals("replace")) {
                    retValue = XSAttributeChecker.INT_WS_REPLACE;
                    break;
                }
                if (value.equals("collapse")) {
                    retValue = XSAttributeChecker.INT_WS_COLLAPSE;
                    break;
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[] { value, "(preserve | replace | collapse)" });
            }
        }
        return retValue;
    }
    
    void reportSchemaError(final String key, final Object[] args, final Element ele) {
        this.fSchemaHandler.reportSchemaError(key, args, ele);
    }
    
    public void checkNonSchemaAttributes(final XSGrammarBucket grammarBucket) {
        final Enumeration enum1 = this.fNonSchemaAttrs.keys();
        while (enum1.hasMoreElements()) {
            final String attrRName = enum1.nextElement();
            final String attrURI = attrRName.substring(0, attrRName.indexOf(44));
            final String attrLocal = attrRName.substring(attrRName.indexOf(44) + 1);
            final SchemaGrammar sGrammar = grammarBucket.getGrammar(attrURI);
            if (sGrammar == null) {
                continue;
            }
            final XSAttributeDecl attrDecl = sGrammar.getGlobalAttributeDecl(attrLocal);
            if (attrDecl == null) {
                continue;
            }
            final XSSimpleType dv = (XSSimpleType)attrDecl.getTypeDefinition();
            if (dv == null) {
                continue;
            }
            final Vector values = this.fNonSchemaAttrs.get(attrRName);
            final String attrName = values.elementAt(0);
            for (int count = values.size(), i = 1; i < count; i += 2) {
                final String elName = values.elementAt(i);
                try {
                    dv.validate(values.elementAt(i + 1), null, null);
                }
                catch (InvalidDatatypeValueException ide) {
                    this.reportSchemaError("s4s-att-invalid-value", new Object[] { elName, attrName, ide.getKey() }, null);
                }
            }
        }
    }
    
    public static String normalize(final String content, final short ws) {
        final int len = (content == null) ? 0 : content.length();
        if (len == 0 || ws == 0) {
            return content;
        }
        final StringBuffer sb = new StringBuffer();
        if (ws == 1) {
            for (int i = 0; i < len; ++i) {
                final char ch = content.charAt(i);
                if (ch != '\t' && ch != '\n' && ch != '\r') {
                    sb.append(ch);
                }
                else {
                    sb.append(' ');
                }
            }
        }
        else {
            boolean isLeading = true;
            for (int i = 0; i < len; ++i) {
                char ch = content.charAt(i);
                if (ch != '\t' && ch != '\n' && ch != '\r' && ch != ' ') {
                    sb.append(ch);
                    isLeading = false;
                }
                else {
                    while (i < len - 1) {
                        ch = content.charAt(i + 1);
                        if (ch != '\t' && ch != '\n' && ch != '\r' && ch != ' ') {
                            break;
                        }
                        ++i;
                    }
                    if (i < len - 1 && !isLeading) {
                        sb.append(' ');
                    }
                }
            }
        }
        return sb.toString();
    }
    
    protected Object[] getAvailableArray() {
        if (this.fArrayPool.length == this.fPoolPos) {
            this.fArrayPool = new Object[this.fPoolPos + 10][];
            for (int i = this.fPoolPos; i < this.fArrayPool.length; ++i) {
                this.fArrayPool[i] = new Object[XSAttributeChecker.ATTIDX_COUNT];
            }
        }
        final Object[] retArray = this.fArrayPool[this.fPoolPos];
        this.fArrayPool[this.fPoolPos++] = null;
        System.arraycopy(XSAttributeChecker.fTempArray, 0, retArray, 0, XSAttributeChecker.ATTIDX_COUNT - 1);
        retArray[XSAttributeChecker.ATTIDX_ISRETURNED] = Boolean.FALSE;
        return retArray;
    }
    
    public void returnAttrArray(final Object[] attrArray, final XSDocumentInfo schemaDoc) {
        if (schemaDoc != null) {
            schemaDoc.fNamespaceSupport.popContext();
        }
        if (this.fPoolPos == 0 || attrArray == null || attrArray.length != XSAttributeChecker.ATTIDX_COUNT || (boolean)attrArray[XSAttributeChecker.ATTIDX_ISRETURNED]) {
            return;
        }
        attrArray[XSAttributeChecker.ATTIDX_ISRETURNED] = Boolean.TRUE;
        this.fArrayPool[--this.fPoolPos] = attrArray;
    }
    
    public void resolveNamespace(final Element element, final Attr[] attrs, final SchemaNamespaceSupport nsSupport) {
        nsSupport.pushContext();
        final int length = attrs.length;
        Attr sattr = null;
        for (int i = 0; i < length; ++i) {
            sattr = attrs[i];
            final String rawname = DOMUtil.getName(sattr);
            String prefix = null;
            if (rawname.equals(XMLSymbols.PREFIX_XMLNS)) {
                prefix = XMLSymbols.EMPTY_STRING;
            }
            else if (rawname.startsWith("xmlns:")) {
                prefix = this.fSymbolTable.addSymbol(DOMUtil.getLocalName(sattr));
            }
            if (prefix != null) {
                final String uri = this.fSymbolTable.addSymbol(DOMUtil.getValue(sattr));
                nsSupport.declarePrefix(prefix, (uri.length() != 0) ? uri : null);
            }
        }
    }
    
    static {
        XSAttributeChecker.ATTIDX_COUNT = 0;
        ATTIDX_ABSTRACT = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_AFORMDEFAULT = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_BASE = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_BLOCK = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_BLOCKDEFAULT = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_DEFAULT = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_EFORMDEFAULT = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_FINAL = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_FINALDEFAULT = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_FIXED = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_FORM = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_ID = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_ITEMTYPE = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_MAXOCCURS = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_MEMBERTYPES = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_MINOCCURS = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_MIXED = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_NAME = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_NAMESPACE = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_NAMESPACE_LIST = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_NILLABLE = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_PROCESSCONTENTS = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_PUBLIC = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_REF = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_REFER = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_SCHEMALOCATION = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_SOURCE = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_SUBSGROUP = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_SYSTEM = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_TARGETNAMESPACE = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_TYPE = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_USE = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_VALUE = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_ENUMNSDECLS = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_VERSION = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_XPATH = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_FROMDEFAULT = XSAttributeChecker.ATTIDX_COUNT++;
        ATTIDX_ISRETURNED = XSAttributeChecker.ATTIDX_COUNT++;
        fXIntPool = new XIntPool();
        INT_QUALIFIED = XSAttributeChecker.fXIntPool.getXInt(1);
        INT_UNQUALIFIED = XSAttributeChecker.fXIntPool.getXInt(0);
        INT_EMPTY_SET = XSAttributeChecker.fXIntPool.getXInt(0);
        INT_ANY_STRICT = XSAttributeChecker.fXIntPool.getXInt(1);
        INT_ANY_LAX = XSAttributeChecker.fXIntPool.getXInt(3);
        INT_ANY_SKIP = XSAttributeChecker.fXIntPool.getXInt(2);
        INT_ANY_ANY = XSAttributeChecker.fXIntPool.getXInt(1);
        INT_ANY_LIST = XSAttributeChecker.fXIntPool.getXInt(3);
        INT_ANY_NOT = XSAttributeChecker.fXIntPool.getXInt(2);
        INT_USE_OPTIONAL = XSAttributeChecker.fXIntPool.getXInt(0);
        INT_USE_REQUIRED = XSAttributeChecker.fXIntPool.getXInt(1);
        INT_USE_PROHIBITED = XSAttributeChecker.fXIntPool.getXInt(2);
        INT_WS_PRESERVE = XSAttributeChecker.fXIntPool.getXInt(0);
        INT_WS_REPLACE = XSAttributeChecker.fXIntPool.getXInt(1);
        INT_WS_COLLAPSE = XSAttributeChecker.fXIntPool.getXInt(2);
        INT_UNBOUNDED = XSAttributeChecker.fXIntPool.getXInt(-1);
        fEleAttrsMapG = new Hashtable(29);
        fEleAttrsMapL = new Hashtable(79);
        fExtraDVs = new XSSimpleType[8];
        final SchemaGrammar grammar = SchemaGrammar.SG_SchemaNS;
        XSAttributeChecker.fExtraDVs[0] = (XSSimpleType)grammar.getGlobalTypeDecl("anyURI");
        XSAttributeChecker.fExtraDVs[1] = (XSSimpleType)grammar.getGlobalTypeDecl("ID");
        XSAttributeChecker.fExtraDVs[2] = (XSSimpleType)grammar.getGlobalTypeDecl("QName");
        XSAttributeChecker.fExtraDVs[3] = (XSSimpleType)grammar.getGlobalTypeDecl("string");
        XSAttributeChecker.fExtraDVs[4] = (XSSimpleType)grammar.getGlobalTypeDecl("token");
        XSAttributeChecker.fExtraDVs[5] = (XSSimpleType)grammar.getGlobalTypeDecl("NCName");
        XSAttributeChecker.fExtraDVs[6] = XSAttributeChecker.fExtraDVs[3];
        XSAttributeChecker.fExtraDVs[6] = XSAttributeChecker.fExtraDVs[3];
        int attCount = 0;
        final int ATT_ABSTRACT_D = attCount++;
        final int ATT_ATTRIBUTE_FD_D = attCount++;
        final int ATT_BASE_R = attCount++;
        final int ATT_BASE_N = attCount++;
        final int ATT_BLOCK_N = attCount++;
        final int ATT_BLOCK1_N = attCount++;
        final int ATT_BLOCK_D_D = attCount++;
        final int ATT_DEFAULT_N = attCount++;
        final int ATT_ELEMENT_FD_D = attCount++;
        final int ATT_FINAL_N = attCount++;
        final int ATT_FINAL1_N = attCount++;
        final int ATT_FINAL_D_D = attCount++;
        final int ATT_FIXED_N = attCount++;
        final int ATT_FIXED_D = attCount++;
        final int ATT_FORM_N = attCount++;
        final int ATT_ID_N = attCount++;
        final int ATT_ITEMTYPE_N = attCount++;
        final int ATT_MAXOCCURS_D = attCount++;
        final int ATT_MAXOCCURS1_D = attCount++;
        final int ATT_MEMBER_T_N = attCount++;
        final int ATT_MINOCCURS_D = attCount++;
        final int ATT_MINOCCURS1_D = attCount++;
        final int ATT_MIXED_D = attCount++;
        final int ATT_MIXED_N = attCount++;
        final int ATT_NAME_R = attCount++;
        final int ATT_NAMESPACE_D = attCount++;
        final int ATT_NAMESPACE_N = attCount++;
        final int ATT_NILLABLE_D = attCount++;
        final int ATT_PROCESS_C_D = attCount++;
        final int ATT_PUBLIC_R = attCount++;
        final int ATT_REF_R = attCount++;
        final int ATT_REFER_R = attCount++;
        final int ATT_SCHEMA_L_R = attCount++;
        final int ATT_SCHEMA_L_N = attCount++;
        final int ATT_SOURCE_N = attCount++;
        final int ATT_SUBSTITUTION_G_N = attCount++;
        final int ATT_SYSTEM_N = attCount++;
        final int ATT_TARGET_N_N = attCount++;
        final int ATT_TYPE_N = attCount++;
        final int ATT_USE_D = attCount++;
        final int ATT_VALUE_NNI_N = attCount++;
        final int ATT_VALUE_STR_N = attCount++;
        final int ATT_VALUE_WS_N = attCount++;
        final int ATT_VERSION_N = attCount++;
        final int ATT_XPATH_R = attCount++;
        final int ATT_XPATH1_R = attCount++;
        final OneAttr[] allAttrs = new OneAttr[attCount];
        allAttrs[ATT_ABSTRACT_D] = new OneAttr(SchemaSymbols.ATT_ABSTRACT, -15, XSAttributeChecker.ATTIDX_ABSTRACT, Boolean.FALSE);
        allAttrs[ATT_ATTRIBUTE_FD_D] = new OneAttr(SchemaSymbols.ATT_ATTRIBUTEFORMDEFAULT, -5, XSAttributeChecker.ATTIDX_AFORMDEFAULT, XSAttributeChecker.INT_UNQUALIFIED);
        allAttrs[ATT_BASE_R] = new OneAttr(SchemaSymbols.ATT_BASE, 2, XSAttributeChecker.ATTIDX_BASE, null);
        allAttrs[ATT_BASE_N] = new OneAttr(SchemaSymbols.ATT_BASE, 2, XSAttributeChecker.ATTIDX_BASE, null);
        allAttrs[ATT_BLOCK_N] = new OneAttr(SchemaSymbols.ATT_BLOCK, -1, XSAttributeChecker.ATTIDX_BLOCK, null);
        allAttrs[ATT_BLOCK1_N] = new OneAttr(SchemaSymbols.ATT_BLOCK, -2, XSAttributeChecker.ATTIDX_BLOCK, null);
        allAttrs[ATT_BLOCK_D_D] = new OneAttr(SchemaSymbols.ATT_BLOCKDEFAULT, -1, XSAttributeChecker.ATTIDX_BLOCKDEFAULT, XSAttributeChecker.INT_EMPTY_SET);
        allAttrs[ATT_DEFAULT_N] = new OneAttr(SchemaSymbols.ATT_DEFAULT, 3, XSAttributeChecker.ATTIDX_DEFAULT, null);
        allAttrs[ATT_ELEMENT_FD_D] = new OneAttr(SchemaSymbols.ATT_ELEMENTFORMDEFAULT, -5, XSAttributeChecker.ATTIDX_EFORMDEFAULT, XSAttributeChecker.INT_UNQUALIFIED);
        allAttrs[ATT_FINAL_N] = new OneAttr(SchemaSymbols.ATT_FINAL, -3, XSAttributeChecker.ATTIDX_FINAL, null);
        allAttrs[ATT_FINAL1_N] = new OneAttr(SchemaSymbols.ATT_FINAL, -4, XSAttributeChecker.ATTIDX_FINAL, null);
        allAttrs[ATT_FINAL_D_D] = new OneAttr(SchemaSymbols.ATT_FINALDEFAULT, -3, XSAttributeChecker.ATTIDX_FINALDEFAULT, XSAttributeChecker.INT_EMPTY_SET);
        allAttrs[ATT_FIXED_N] = new OneAttr(SchemaSymbols.ATT_FIXED, 3, XSAttributeChecker.ATTIDX_FIXED, null);
        allAttrs[ATT_FIXED_D] = new OneAttr(SchemaSymbols.ATT_FIXED, -15, XSAttributeChecker.ATTIDX_FIXED, Boolean.FALSE);
        allAttrs[ATT_FORM_N] = new OneAttr(SchemaSymbols.ATT_FORM, -5, XSAttributeChecker.ATTIDX_FORM, null);
        allAttrs[ATT_ID_N] = new OneAttr(SchemaSymbols.ATT_ID, 1, XSAttributeChecker.ATTIDX_ID, null);
        allAttrs[ATT_ITEMTYPE_N] = new OneAttr(SchemaSymbols.ATT_ITEMTYPE, 2, XSAttributeChecker.ATTIDX_ITEMTYPE, null);
        allAttrs[ATT_MAXOCCURS_D] = new OneAttr(SchemaSymbols.ATT_MAXOCCURS, -6, XSAttributeChecker.ATTIDX_MAXOCCURS, XSAttributeChecker.fXIntPool.getXInt(1));
        allAttrs[ATT_MAXOCCURS1_D] = new OneAttr(SchemaSymbols.ATT_MAXOCCURS, -7, XSAttributeChecker.ATTIDX_MAXOCCURS, XSAttributeChecker.fXIntPool.getXInt(1));
        allAttrs[ATT_MEMBER_T_N] = new OneAttr(SchemaSymbols.ATT_MEMBERTYPES, -8, XSAttributeChecker.ATTIDX_MEMBERTYPES, null);
        allAttrs[ATT_MINOCCURS_D] = new OneAttr(SchemaSymbols.ATT_MINOCCURS, -16, XSAttributeChecker.ATTIDX_MINOCCURS, XSAttributeChecker.fXIntPool.getXInt(1));
        allAttrs[ATT_MINOCCURS1_D] = new OneAttr(SchemaSymbols.ATT_MINOCCURS, -9, XSAttributeChecker.ATTIDX_MINOCCURS, XSAttributeChecker.fXIntPool.getXInt(1));
        allAttrs[ATT_MIXED_D] = new OneAttr(SchemaSymbols.ATT_MIXED, -15, XSAttributeChecker.ATTIDX_MIXED, Boolean.FALSE);
        allAttrs[ATT_MIXED_N] = new OneAttr(SchemaSymbols.ATT_MIXED, -15, XSAttributeChecker.ATTIDX_MIXED, null);
        allAttrs[ATT_NAME_R] = new OneAttr(SchemaSymbols.ATT_NAME, 5, XSAttributeChecker.ATTIDX_NAME, null);
        allAttrs[ATT_NAMESPACE_D] = new OneAttr(SchemaSymbols.ATT_NAMESPACE, -10, XSAttributeChecker.ATTIDX_NAMESPACE, XSAttributeChecker.INT_ANY_ANY);
        allAttrs[ATT_NAMESPACE_N] = new OneAttr(SchemaSymbols.ATT_NAMESPACE, 0, XSAttributeChecker.ATTIDX_NAMESPACE, null);
        allAttrs[ATT_NILLABLE_D] = new OneAttr(SchemaSymbols.ATT_NILLABLE, -15, XSAttributeChecker.ATTIDX_NILLABLE, Boolean.FALSE);
        allAttrs[ATT_PROCESS_C_D] = new OneAttr(SchemaSymbols.ATT_PROCESSCONTENTS, -11, XSAttributeChecker.ATTIDX_PROCESSCONTENTS, XSAttributeChecker.INT_ANY_STRICT);
        allAttrs[ATT_PUBLIC_R] = new OneAttr(SchemaSymbols.ATT_PUBLIC, 4, XSAttributeChecker.ATTIDX_PUBLIC, null);
        allAttrs[ATT_REF_R] = new OneAttr(SchemaSymbols.ATT_REF, 2, XSAttributeChecker.ATTIDX_REF, null);
        allAttrs[ATT_REFER_R] = new OneAttr(SchemaSymbols.ATT_REFER, 2, XSAttributeChecker.ATTIDX_REFER, null);
        allAttrs[ATT_SCHEMA_L_R] = new OneAttr(SchemaSymbols.ATT_SCHEMALOCATION, 0, XSAttributeChecker.ATTIDX_SCHEMALOCATION, null);
        allAttrs[ATT_SCHEMA_L_N] = new OneAttr(SchemaSymbols.ATT_SCHEMALOCATION, 0, XSAttributeChecker.ATTIDX_SCHEMALOCATION, null);
        allAttrs[ATT_SOURCE_N] = new OneAttr(SchemaSymbols.ATT_SOURCE, 0, XSAttributeChecker.ATTIDX_SOURCE, null);
        allAttrs[ATT_SUBSTITUTION_G_N] = new OneAttr(SchemaSymbols.ATT_SUBSTITUTIONGROUP, 2, XSAttributeChecker.ATTIDX_SUBSGROUP, null);
        allAttrs[ATT_SYSTEM_N] = new OneAttr(SchemaSymbols.ATT_SYSTEM, 0, XSAttributeChecker.ATTIDX_SYSTEM, null);
        allAttrs[ATT_TARGET_N_N] = new OneAttr(SchemaSymbols.ATT_TARGETNAMESPACE, 0, XSAttributeChecker.ATTIDX_TARGETNAMESPACE, null);
        allAttrs[ATT_TYPE_N] = new OneAttr(SchemaSymbols.ATT_TYPE, 2, XSAttributeChecker.ATTIDX_TYPE, null);
        allAttrs[ATT_USE_D] = new OneAttr(SchemaSymbols.ATT_USE, -13, XSAttributeChecker.ATTIDX_USE, XSAttributeChecker.INT_USE_OPTIONAL);
        allAttrs[ATT_VALUE_NNI_N] = new OneAttr(SchemaSymbols.ATT_VALUE, -16, XSAttributeChecker.ATTIDX_VALUE, null);
        allAttrs[ATT_VALUE_STR_N] = new OneAttr(SchemaSymbols.ATT_VALUE, 3, XSAttributeChecker.ATTIDX_VALUE, null);
        allAttrs[ATT_VALUE_WS_N] = new OneAttr(SchemaSymbols.ATT_VALUE, -14, XSAttributeChecker.ATTIDX_VALUE, null);
        allAttrs[ATT_VERSION_N] = new OneAttr(SchemaSymbols.ATT_VERSION, -12, XSAttributeChecker.ATTIDX_VERSION, null);
        allAttrs[ATT_XPATH_R] = new OneAttr(SchemaSymbols.ATT_XPATH, 6, XSAttributeChecker.ATTIDX_XPATH, null);
        allAttrs[ATT_XPATH1_R] = new OneAttr(SchemaSymbols.ATT_XPATH, 7, XSAttributeChecker.ATTIDX_XPATH, null);
        Container attrList = Container.getContainer(5);
        attrList.put(SchemaSymbols.ATT_DEFAULT, allAttrs[ATT_DEFAULT_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        attrList.put(SchemaSymbols.ATT_TYPE, allAttrs[ATT_TYPE_N]);
        OneElement oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_ATTRIBUTE, oneEle);
        attrList = Container.getContainer(7);
        attrList.put(SchemaSymbols.ATT_DEFAULT, allAttrs[ATT_DEFAULT_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_N]);
        attrList.put(SchemaSymbols.ATT_FORM, allAttrs[ATT_FORM_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        attrList.put(SchemaSymbols.ATT_TYPE, allAttrs[ATT_TYPE_N]);
        attrList.put(SchemaSymbols.ATT_USE, allAttrs[ATT_USE_D]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put("attribute_n", oneEle);
        attrList = Container.getContainer(5);
        attrList.put(SchemaSymbols.ATT_DEFAULT, allAttrs[ATT_DEFAULT_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_REF, allAttrs[ATT_REF_R]);
        attrList.put(SchemaSymbols.ATT_USE, allAttrs[ATT_USE_D]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put("attribute_r", oneEle);
        attrList = Container.getContainer(10);
        attrList.put(SchemaSymbols.ATT_ABSTRACT, allAttrs[ATT_ABSTRACT_D]);
        attrList.put(SchemaSymbols.ATT_BLOCK, allAttrs[ATT_BLOCK_N]);
        attrList.put(SchemaSymbols.ATT_DEFAULT, allAttrs[ATT_DEFAULT_N]);
        attrList.put(SchemaSymbols.ATT_FINAL, allAttrs[ATT_FINAL_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        attrList.put(SchemaSymbols.ATT_NILLABLE, allAttrs[ATT_NILLABLE_D]);
        attrList.put(SchemaSymbols.ATT_SUBSTITUTIONGROUP, allAttrs[ATT_SUBSTITUTION_G_N]);
        attrList.put(SchemaSymbols.ATT_TYPE, allAttrs[ATT_TYPE_N]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_ELEMENT, oneEle);
        attrList = Container.getContainer(10);
        attrList.put(SchemaSymbols.ATT_BLOCK, allAttrs[ATT_BLOCK_N]);
        attrList.put(SchemaSymbols.ATT_DEFAULT, allAttrs[ATT_DEFAULT_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_N]);
        attrList.put(SchemaSymbols.ATT_FORM, allAttrs[ATT_FORM_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MAXOCCURS, allAttrs[ATT_MAXOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_MINOCCURS, allAttrs[ATT_MINOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        attrList.put(SchemaSymbols.ATT_NILLABLE, allAttrs[ATT_NILLABLE_D]);
        attrList.put(SchemaSymbols.ATT_TYPE, allAttrs[ATT_TYPE_N]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put("element_n", oneEle);
        attrList = Container.getContainer(4);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MAXOCCURS, allAttrs[ATT_MAXOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_MINOCCURS, allAttrs[ATT_MINOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_REF, allAttrs[ATT_REF_R]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put("element_r", oneEle);
        attrList = Container.getContainer(6);
        attrList.put(SchemaSymbols.ATT_ABSTRACT, allAttrs[ATT_ABSTRACT_D]);
        attrList.put(SchemaSymbols.ATT_BLOCK, allAttrs[ATT_BLOCK1_N]);
        attrList.put(SchemaSymbols.ATT_FINAL, allAttrs[ATT_FINAL_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MIXED, allAttrs[ATT_MIXED_D]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_COMPLEXTYPE, oneEle);
        attrList = Container.getContainer(4);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        attrList.put(SchemaSymbols.ATT_PUBLIC, allAttrs[ATT_PUBLIC_R]);
        attrList.put(SchemaSymbols.ATT_SYSTEM, allAttrs[ATT_SYSTEM_N]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_NOTATION, oneEle);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MIXED, allAttrs[ATT_MIXED_D]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_COMPLEXTYPE, oneEle);
        attrList = Container.getContainer(1);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_SIMPLECONTENT, oneEle);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_BASE, allAttrs[ATT_BASE_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_RESTRICTION, oneEle);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_BASE, allAttrs[ATT_BASE_R]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_EXTENSION, oneEle);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_REF, allAttrs[ATT_REF_R]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_ATTRIBUTEGROUP, oneEle);
        attrList = Container.getContainer(3);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAMESPACE, allAttrs[ATT_NAMESPACE_D]);
        attrList.put(SchemaSymbols.ATT_PROCESSCONTENTS, allAttrs[ATT_PROCESS_C_D]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_ANYATTRIBUTE, oneEle);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MIXED, allAttrs[ATT_MIXED_N]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_COMPLEXCONTENT, oneEle);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_ATTRIBUTEGROUP, oneEle);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_GROUP, oneEle);
        attrList = Container.getContainer(4);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MAXOCCURS, allAttrs[ATT_MAXOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_MINOCCURS, allAttrs[ATT_MINOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_REF, allAttrs[ATT_REF_R]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_GROUP, oneEle);
        attrList = Container.getContainer(3);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MAXOCCURS, allAttrs[ATT_MAXOCCURS1_D]);
        attrList.put(SchemaSymbols.ATT_MINOCCURS, allAttrs[ATT_MINOCCURS1_D]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_ALL, oneEle);
        attrList = Container.getContainer(3);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MAXOCCURS, allAttrs[ATT_MAXOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_MINOCCURS, allAttrs[ATT_MINOCCURS_D]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_CHOICE, oneEle);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_SEQUENCE, oneEle);
        attrList = Container.getContainer(5);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MAXOCCURS, allAttrs[ATT_MAXOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_MINOCCURS, allAttrs[ATT_MINOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_NAMESPACE, allAttrs[ATT_NAMESPACE_D]);
        attrList.put(SchemaSymbols.ATT_PROCESSCONTENTS, allAttrs[ATT_PROCESS_C_D]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_ANY, oneEle);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_UNIQUE, oneEle);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_KEY, oneEle);
        attrList = Container.getContainer(3);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        attrList.put(SchemaSymbols.ATT_REFER, allAttrs[ATT_REFER_R]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_KEYREF, oneEle);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_XPATH, allAttrs[ATT_XPATH_R]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_SELECTOR, oneEle);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_XPATH, allAttrs[ATT_XPATH1_R]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_FIELD, oneEle);
        attrList = Container.getContainer(1);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_ANNOTATION, oneEle);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_ANNOTATION, oneEle);
        attrList = Container.getContainer(1);
        attrList.put(SchemaSymbols.ATT_SOURCE, allAttrs[ATT_SOURCE_N]);
        oneEle = new OneElement(attrList, false);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_APPINFO, oneEle);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_APPINFO, oneEle);
        attrList = Container.getContainer(1);
        attrList.put(SchemaSymbols.ATT_SOURCE, allAttrs[ATT_SOURCE_N]);
        oneEle = new OneElement(attrList, false);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_DOCUMENTATION, oneEle);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_DOCUMENTATION, oneEle);
        attrList = Container.getContainer(3);
        attrList.put(SchemaSymbols.ATT_FINAL, allAttrs[ATT_FINAL1_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_SIMPLETYPE, oneEle);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_FINAL, allAttrs[ATT_FINAL1_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_SIMPLETYPE, oneEle);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_ITEMTYPE, allAttrs[ATT_ITEMTYPE_N]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_LIST, oneEle);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MEMBERTYPES, allAttrs[ATT_MEMBER_T_N]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_UNION, oneEle);
        attrList = Container.getContainer(7);
        attrList.put(SchemaSymbols.ATT_ATTRIBUTEFORMDEFAULT, allAttrs[ATT_ATTRIBUTE_FD_D]);
        attrList.put(SchemaSymbols.ATT_BLOCKDEFAULT, allAttrs[ATT_BLOCK_D_D]);
        attrList.put(SchemaSymbols.ATT_ELEMENTFORMDEFAULT, allAttrs[ATT_ELEMENT_FD_D]);
        attrList.put(SchemaSymbols.ATT_FINALDEFAULT, allAttrs[ATT_FINAL_D_D]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_TARGETNAMESPACE, allAttrs[ATT_TARGET_N_N]);
        attrList.put(SchemaSymbols.ATT_VERSION, allAttrs[ATT_VERSION_N]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_SCHEMA, oneEle);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_SCHEMALOCATION, allAttrs[ATT_SCHEMA_L_R]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_INCLUDE, oneEle);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_REDEFINE, oneEle);
        attrList = Container.getContainer(3);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAMESPACE, allAttrs[ATT_NAMESPACE_N]);
        attrList.put(SchemaSymbols.ATT_SCHEMALOCATION, allAttrs[ATT_SCHEMA_L_N]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_IMPORT, oneEle);
        attrList = Container.getContainer(3);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_VALUE, allAttrs[ATT_VALUE_NNI_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_D]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_LENGTH, oneEle);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_MINLENGTH, oneEle);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_MAXLENGTH, oneEle);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_TOTALDIGITS, oneEle);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_FRACTIONDIGITS, oneEle);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_VALUE, allAttrs[ATT_VALUE_STR_N]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_PATTERN, oneEle);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_VALUE, allAttrs[ATT_VALUE_STR_N]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_ENUMERATION, oneEle);
        attrList = Container.getContainer(3);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_VALUE, allAttrs[ATT_VALUE_WS_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_D]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_WHITESPACE, oneEle);
        attrList = Container.getContainer(3);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_VALUE, allAttrs[ATT_VALUE_STR_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_D]);
        oneEle = new OneElement(attrList);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_MAXINCLUSIVE, oneEle);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_MAXEXCLUSIVE, oneEle);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_MININCLUSIVE, oneEle);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_MINEXCLUSIVE, oneEle);
        XSAttributeChecker.fSeenTemp = new boolean[XSAttributeChecker.ATTIDX_COUNT];
        XSAttributeChecker.fTempArray = new Object[XSAttributeChecker.ATTIDX_COUNT];
    }
}
