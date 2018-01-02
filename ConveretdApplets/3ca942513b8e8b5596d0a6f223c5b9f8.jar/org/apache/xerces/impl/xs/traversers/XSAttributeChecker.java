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
    public static final int ATTIDX_NONSCHEMA;
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
    public static final int ATTIDX_XML_LANG;
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
    protected static final int DT_LANGUAGE = 8;
    protected static final int DT_COUNT = 9;
    private static final XSSimpleType[] fExtraDVs;
    protected static final int DT_BLOCK = -1;
    protected static final int DT_BLOCK1 = -2;
    protected static final int DT_FINAL = -3;
    protected static final int DT_FINAL1 = -4;
    protected static final int DT_FINAL2 = -5;
    protected static final int DT_FORM = -6;
    protected static final int DT_MAXOCCURS = -7;
    protected static final int DT_MAXOCCURS1 = -8;
    protected static final int DT_MEMBERTYPES = -9;
    protected static final int DT_MINOCCURS1 = -10;
    protected static final int DT_NAMESPACE = -11;
    protected static final int DT_PROCESSCONTENTS = -12;
    protected static final int DT_USE = -13;
    protected static final int DT_WHITESPACE = -14;
    protected static final int DT_BOOLEAN = -15;
    protected static final int DT_NONNEGINT = -16;
    protected static final int DT_POSINT = -17;
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
    
    public XSAttributeChecker(final XSDHandler fSchemaHandler) {
        this.fSchemaHandler = null;
        this.fSymbolTable = null;
        this.fNonSchemaAttrs = new Hashtable();
        this.fNamespaceList = new Vector();
        this.fSeen = new boolean[XSAttributeChecker.ATTIDX_COUNT];
        this.fArrayPool = new Object[10][XSAttributeChecker.ATTIDX_COUNT];
        this.fPoolPos = 0;
        this.fSchemaHandler = fSchemaHandler;
    }
    
    public void reset(final SymbolTable fSymbolTable) {
        this.fSymbolTable = fSymbolTable;
        this.fNonSchemaAttrs.clear();
    }
    
    public Object[] checkAttributes(final Element element, final boolean b, final XSDocumentInfo xsDocumentInfo) {
        return this.checkAttributes(element, b, xsDocumentInfo, false);
    }
    
    public Object[] checkAttributes(final Element element, final boolean b, final XSDocumentInfo xsDocumentInfo, final boolean b2) {
        if (element == null) {
            return null;
        }
        final Attr[] attrs = DOMUtil.getAttrs(element);
        this.resolveNamespace(element, attrs, xsDocumentInfo.fNamespaceSupport);
        final String namespaceURI = DOMUtil.getNamespaceURI(element);
        final String localName = DOMUtil.getLocalName(element);
        if (!SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(namespaceURI)) {
            this.reportSchemaError("s4s-elt-schema-ns", new Object[] { localName }, element);
        }
        Hashtable hashtable = XSAttributeChecker.fEleAttrsMapG;
        String s = localName;
        if (!b) {
            hashtable = XSAttributeChecker.fEleAttrsMapL;
            if (localName.equals(SchemaSymbols.ELT_ELEMENT)) {
                if (DOMUtil.getAttr(element, SchemaSymbols.ATT_REF) != null) {
                    s = "element_r";
                }
                else {
                    s = "element_n";
                }
            }
            else if (localName.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
                if (DOMUtil.getAttr(element, SchemaSymbols.ATT_REF) != null) {
                    s = "attribute_r";
                }
                else {
                    s = "attribute_n";
                }
            }
        }
        final OneElement oneElement = hashtable.get(s);
        if (oneElement == null) {
            this.reportSchemaError("s4s-elt-invalid", new Object[] { localName }, element);
            return null;
        }
        final Object[] availableArray = this.getAvailableArray();
        long n = 0L;
        final Container attrList = oneElement.attrList;
        System.arraycopy(XSAttributeChecker.fSeenTemp, 0, this.fSeen, 0, XSAttributeChecker.ATTIDX_COUNT);
        for (final Attr attr : attrs) {
            final String name = attr.getName();
            String namespaceURI2 = DOMUtil.getNamespaceURI(attr);
            final String value = DOMUtil.getValue(attr);
            Label_0746: {
                if (name.startsWith("xml")) {
                    if ("xmlns".equals(DOMUtil.getPrefix(attr))) {
                        break Label_0746;
                    }
                    if ("xmlns".equals(name)) {
                        break Label_0746;
                    }
                    if (SchemaSymbols.ATT_XML_LANG.equals(name) && (SchemaSymbols.ELT_SCHEMA.equals(localName) || SchemaSymbols.ELT_DOCUMENTATION.equals(localName))) {
                        namespaceURI2 = null;
                    }
                }
                if (namespaceURI2 != null && namespaceURI2.length() != 0) {
                    if (namespaceURI2.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) || !oneElement.allowNonSchemaAttr) {
                        this.reportSchemaError("s4s-att-not-allowed", new Object[] { localName, name }, element);
                    }
                    else {
                        if (availableArray[XSAttributeChecker.ATTIDX_NONSCHEMA] == null) {
                            availableArray[XSAttributeChecker.ATTIDX_NONSCHEMA] = new Vector(4, 2);
                        }
                        ((Vector)availableArray[XSAttributeChecker.ATTIDX_NONSCHEMA]).addElement(name);
                        ((Vector)availableArray[XSAttributeChecker.ATTIDX_NONSCHEMA]).addElement(value);
                    }
                }
                else {
                    final OneAttr value2 = attrList.get(name);
                    if (value2 == null) {
                        this.reportSchemaError("s4s-att-not-allowed", new Object[] { localName, name }, element);
                    }
                    else {
                        this.fSeen[value2.valueIndex] = true;
                        try {
                            if (value2.dvIndex >= 0) {
                                if (value2.dvIndex != 3 && value2.dvIndex != 6 && value2.dvIndex != 7) {
                                    final Object validate = XSAttributeChecker.fExtraDVs[value2.dvIndex].validate(value, xsDocumentInfo.fValidationContext, null);
                                    if (value2.dvIndex == 2) {
                                        final QName qName = (QName)validate;
                                        if (qName.prefix == XMLSymbols.EMPTY_STRING && qName.uri == null && xsDocumentInfo.fIsChameleonSchema) {
                                            qName.uri = xsDocumentInfo.fTargetNamespace;
                                        }
                                    }
                                    availableArray[value2.valueIndex] = validate;
                                }
                                else {
                                    availableArray[value2.valueIndex] = value;
                                }
                            }
                            else {
                                availableArray[value2.valueIndex] = this.validate(availableArray, name, value, value2.dvIndex, xsDocumentInfo);
                            }
                        }
                        catch (InvalidDatatypeValueException ex) {
                            this.reportSchemaError("s4s-att-invalid-value", new Object[] { localName, name, ex.getMessage() }, element);
                            if (value2.dfltValue != null) {
                                availableArray[value2.valueIndex] = value2.dfltValue;
                            }
                        }
                        if (localName.equals(SchemaSymbols.ELT_ENUMERATION) && b2) {
                            availableArray[XSAttributeChecker.ATTIDX_ENUMNSDECLS] = new SchemaNamespaceSupport(xsDocumentInfo.fNamespaceSupport);
                        }
                    }
                }
            }
        }
        final OneAttr[] values = oneElement.attrList.values;
        for (int j = 0; j < values.length; ++j) {
            final OneAttr oneAttr = values[j];
            if (oneAttr.dfltValue != null && !this.fSeen[oneAttr.valueIndex]) {
                availableArray[oneAttr.valueIndex] = oneAttr.dfltValue;
                n |= 1 << oneAttr.valueIndex;
            }
        }
        availableArray[XSAttributeChecker.ATTIDX_FROMDEFAULT] = new Long(n);
        if (availableArray[XSAttributeChecker.ATTIDX_MAXOCCURS] != null) {
            final int intValue = ((XInt)availableArray[XSAttributeChecker.ATTIDX_MINOCCURS]).intValue();
            final int intValue2 = ((XInt)availableArray[XSAttributeChecker.ATTIDX_MAXOCCURS]).intValue();
            if (intValue2 != -1 && intValue > intValue2) {
                this.reportSchemaError("p-props-correct.2.1", new Object[] { localName, availableArray[XSAttributeChecker.ATTIDX_MINOCCURS], availableArray[XSAttributeChecker.ATTIDX_MAXOCCURS] }, element);
                availableArray[XSAttributeChecker.ATTIDX_MINOCCURS] = availableArray[XSAttributeChecker.ATTIDX_MAXOCCURS];
            }
        }
        return availableArray;
    }
    
    private Object validate(final Object[] array, final String s, final String s2, final int n, final XSDocumentInfo xsDocumentInfo) throws InvalidDatatypeValueException {
        if (s2 == null) {
            return null;
        }
        String s3 = s2.trim();
        Object o = null;
        switch (n) {
            case -15: {
                if (s3.equals("false") || s3.equals("0")) {
                    o = Boolean.FALSE;
                    break;
                }
                if (s3.equals("true") || s3.equals("1")) {
                    o = Boolean.TRUE;
                    break;
                }
                throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { s3, "boolean" });
            }
            case -16: {
                try {
                    if (s3.length() > 0 && s3.charAt(0) == '+') {
                        s3 = s3.substring(1);
                    }
                    o = XSAttributeChecker.fXIntPool.getXInt(Integer.parseInt(s3));
                }
                catch (NumberFormatException ex) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { s3, "nonNegativeInteger" });
                }
                if (((XInt)o).intValue() < 0) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { s3, "nonNegativeInteger" });
                }
                break;
            }
            case -17: {
                try {
                    if (s3.length() > 0 && s3.charAt(0) == '+') {
                        s3 = s3.substring(1);
                    }
                    o = XSAttributeChecker.fXIntPool.getXInt(Integer.parseInt(s3));
                }
                catch (NumberFormatException ex2) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { s3, "positiveInteger" });
                }
                if (((XInt)o).intValue() <= 0) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { s3, "positiveInteger" });
                }
                break;
            }
            case -1: {
                int n2 = 0;
                if (s3.equals("#all")) {
                    n2 = 31;
                }
                else {
                    final StringTokenizer stringTokenizer = new StringTokenizer(s3);
                    while (stringTokenizer.hasMoreTokens()) {
                        final String nextToken = stringTokenizer.nextToken();
                        if (nextToken.equals("substitution")) {
                            n2 |= 0x4;
                        }
                        else if (nextToken.equals("extension")) {
                            n2 |= 0x1;
                        }
                        else if (nextToken.equals("restriction")) {
                            n2 |= 0x2;
                        }
                        else if (nextToken.equals("list")) {
                            n2 |= 0x10;
                        }
                        else {
                            if (!nextToken.equals("union")) {
                                throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[] { s3, "(#all | List of (substitution | extension | restriction | list | union))" });
                            }
                            n2 |= 0x2;
                        }
                    }
                }
                o = XSAttributeChecker.fXIntPool.getXInt(n2);
                break;
            }
            case -3:
            case -2: {
                int n3 = 0;
                if (s3.equals("#all")) {
                    n3 = 31;
                }
                else {
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(s3);
                    while (stringTokenizer2.hasMoreTokens()) {
                        final String nextToken2 = stringTokenizer2.nextToken();
                        if (nextToken2.equals("extension")) {
                            n3 |= 0x1;
                        }
                        else {
                            if (!nextToken2.equals("restriction")) {
                                throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[] { s3, "(#all | List of (extension | restriction))" });
                            }
                            n3 |= 0x2;
                        }
                    }
                }
                o = XSAttributeChecker.fXIntPool.getXInt(n3);
                break;
            }
            case -4: {
                int n4 = 0;
                if (s3.equals("#all")) {
                    n4 = 31;
                }
                else {
                    final StringTokenizer stringTokenizer3 = new StringTokenizer(s3);
                    while (stringTokenizer3.hasMoreTokens()) {
                        final String nextToken3 = stringTokenizer3.nextToken();
                        if (nextToken3.equals("list")) {
                            n4 |= 0x10;
                        }
                        else if (nextToken3.equals("union")) {
                            n4 |= 0x8;
                        }
                        else {
                            if (!nextToken3.equals("restriction")) {
                                throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[] { s3, "(#all | List of (list | union | restriction))" });
                            }
                            n4 |= 0x2;
                        }
                    }
                }
                o = XSAttributeChecker.fXIntPool.getXInt(n4);
                break;
            }
            case -5: {
                int n5 = 0;
                if (s3.equals("#all")) {
                    n5 = 31;
                }
                else {
                    final StringTokenizer stringTokenizer4 = new StringTokenizer(s3);
                    while (stringTokenizer4.hasMoreTokens()) {
                        final String nextToken4 = stringTokenizer4.nextToken();
                        if (nextToken4.equals("extension")) {
                            n5 |= 0x1;
                        }
                        else if (nextToken4.equals("restriction")) {
                            n5 |= 0x2;
                        }
                        else if (nextToken4.equals("list")) {
                            n5 |= 0x10;
                        }
                        else {
                            if (!nextToken4.equals("union")) {
                                throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[] { s3, "(#all | List of (extension | restriction | list | union))" });
                            }
                            n5 |= 0x8;
                        }
                    }
                }
                o = XSAttributeChecker.fXIntPool.getXInt(n5);
                break;
            }
            case -6: {
                if (s3.equals("qualified")) {
                    o = XSAttributeChecker.INT_QUALIFIED;
                    break;
                }
                if (s3.equals("unqualified")) {
                    o = XSAttributeChecker.INT_UNQUALIFIED;
                    break;
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[] { s3, "(qualified | unqualified)" });
            }
            case -7: {
                if (s3.equals("unbounded")) {
                    o = XSAttributeChecker.INT_UNBOUNDED;
                    break;
                }
                try {
                    o = this.validate(array, s, s3, -16, xsDocumentInfo);
                    break;
                }
                catch (NumberFormatException ex3) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[] { s3, "(nonNegativeInteger | unbounded)" });
                }
            }
            case -8: {
                if (s3.equals("1")) {
                    o = XSAttributeChecker.fXIntPool.getXInt(1);
                    break;
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[] { s3, "(1)" });
            }
            case -9: {
                final Vector<QName> vector = new Vector<QName>();
                try {
                    final StringTokenizer stringTokenizer5 = new StringTokenizer(s3);
                    while (stringTokenizer5.hasMoreTokens()) {
                        final QName qName = (QName)XSAttributeChecker.fExtraDVs[2].validate(stringTokenizer5.nextToken(), xsDocumentInfo.fValidationContext, null);
                        if (qName.prefix == XMLSymbols.EMPTY_STRING && qName.uri == null && xsDocumentInfo.fIsChameleonSchema) {
                            qName.uri = xsDocumentInfo.fTargetNamespace;
                        }
                        vector.addElement(qName);
                    }
                    o = vector;
                    break;
                }
                catch (InvalidDatatypeValueException ex4) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.2", new Object[] { s3, "(List of QName)" });
                }
            }
            case -10: {
                if (s3.equals("0")) {
                    o = XSAttributeChecker.fXIntPool.getXInt(0);
                    break;
                }
                if (s3.equals("1")) {
                    o = XSAttributeChecker.fXIntPool.getXInt(1);
                    break;
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[] { s3, "(0 | 1)" });
            }
            case -11: {
                if (s3.equals("##any")) {
                    o = XSAttributeChecker.INT_ANY_ANY;
                    break;
                }
                if (s3.equals("##other")) {
                    o = XSAttributeChecker.INT_ANY_NOT;
                    array[XSAttributeChecker.ATTIDX_NAMESPACE_LIST] = new String[] { xsDocumentInfo.fTargetNamespace, null };
                    break;
                }
                o = XSAttributeChecker.INT_ANY_LIST;
                this.fNamespaceList.removeAllElements();
                final StringTokenizer stringTokenizer6 = new StringTokenizer(s3);
                try {
                    while (stringTokenizer6.hasMoreTokens()) {
                        final String nextToken5 = stringTokenizer6.nextToken();
                        String s4;
                        if (nextToken5.equals("##local")) {
                            s4 = null;
                        }
                        else if (nextToken5.equals("##targetNamespace")) {
                            s4 = xsDocumentInfo.fTargetNamespace;
                        }
                        else {
                            XSAttributeChecker.fExtraDVs[0].validate(nextToken5, xsDocumentInfo.fValidationContext, null);
                            s4 = this.fSymbolTable.addSymbol(nextToken5);
                        }
                        if (!this.fNamespaceList.contains(s4)) {
                            this.fNamespaceList.addElement(s4);
                        }
                    }
                }
                catch (InvalidDatatypeValueException ex5) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[] { s3, "((##any | ##other) | List of (anyURI | (##targetNamespace | ##local)) )" });
                }
                final String[] array2 = new String[this.fNamespaceList.size()];
                this.fNamespaceList.copyInto(array2);
                array[XSAttributeChecker.ATTIDX_NAMESPACE_LIST] = array2;
                break;
            }
            case -12: {
                if (s3.equals("strict")) {
                    o = XSAttributeChecker.INT_ANY_STRICT;
                    break;
                }
                if (s3.equals("lax")) {
                    o = XSAttributeChecker.INT_ANY_LAX;
                    break;
                }
                if (s3.equals("skip")) {
                    o = XSAttributeChecker.INT_ANY_SKIP;
                    break;
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[] { s3, "(lax | skip | strict)" });
            }
            case -13: {
                if (s3.equals("optional")) {
                    o = XSAttributeChecker.INT_USE_OPTIONAL;
                    break;
                }
                if (s3.equals("required")) {
                    o = XSAttributeChecker.INT_USE_REQUIRED;
                    break;
                }
                if (s3.equals("prohibited")) {
                    o = XSAttributeChecker.INT_USE_PROHIBITED;
                    break;
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[] { s3, "(optional | prohibited | required)" });
            }
            case -14: {
                if (s3.equals("preserve")) {
                    o = XSAttributeChecker.INT_WS_PRESERVE;
                    break;
                }
                if (s3.equals("replace")) {
                    o = XSAttributeChecker.INT_WS_REPLACE;
                    break;
                }
                if (s3.equals("collapse")) {
                    o = XSAttributeChecker.INT_WS_COLLAPSE;
                    break;
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[] { s3, "(preserve | replace | collapse)" });
            }
        }
        return o;
    }
    
    void reportSchemaError(final String s, final Object[] array, final Element element) {
        this.fSchemaHandler.reportSchemaError(s, array, element);
    }
    
    public void checkNonSchemaAttributes(final XSGrammarBucket xsGrammarBucket) {
        final Enumeration<String> keys = (Enumeration<String>)this.fNonSchemaAttrs.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final String substring = s.substring(0, s.indexOf(44));
            final String substring2 = s.substring(s.indexOf(44) + 1);
            final SchemaGrammar grammar = xsGrammarBucket.getGrammar(substring);
            if (grammar == null) {
                continue;
            }
            final XSAttributeDecl globalAttributeDecl = grammar.getGlobalAttributeDecl(substring2);
            if (globalAttributeDecl == null) {
                continue;
            }
            final XSSimpleType xsSimpleType = (XSSimpleType)globalAttributeDecl.getTypeDefinition();
            if (xsSimpleType == null) {
                continue;
            }
            final Vector<String> vector = this.fNonSchemaAttrs.get(s);
            final String s2 = vector.elementAt(0);
            for (int size = vector.size(), i = 1; i < size; i += 2) {
                final String s3 = vector.elementAt(i);
                try {
                    xsSimpleType.validate(vector.elementAt(i + 1), null, null);
                }
                catch (InvalidDatatypeValueException ex) {
                    this.reportSchemaError("s4s-att-invalid-value", new Object[] { s3, s2, ex.getMessage() }, null);
                }
            }
        }
    }
    
    public static String normalize(final String s, final short n) {
        final int n2 = (s == null) ? 0 : s.length();
        if (n2 == 0 || n == 0) {
            return s;
        }
        final StringBuffer sb = new StringBuffer();
        if (n == 1) {
            for (int i = 0; i < n2; ++i) {
                final char char1 = s.charAt(i);
                if (char1 != '\t' && char1 != '\n' && char1 != '\r') {
                    sb.append(char1);
                }
                else {
                    sb.append(' ');
                }
            }
        }
        else {
            boolean b = true;
            for (int j = 0; j < n2; ++j) {
                final char char2 = s.charAt(j);
                if (char2 != '\t' && char2 != '\n' && char2 != '\r' && char2 != ' ') {
                    sb.append(char2);
                    b = false;
                }
                else {
                    while (j < n2 - 1) {
                        final char char3 = s.charAt(j + 1);
                        if (char3 != '\t' && char3 != '\n' && char3 != '\r' && char3 != ' ') {
                            break;
                        }
                        ++j;
                    }
                    if (j < n2 - 1 && !b) {
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
        final Object[] array = this.fArrayPool[this.fPoolPos];
        this.fArrayPool[this.fPoolPos++] = null;
        System.arraycopy(XSAttributeChecker.fTempArray, 0, array, 0, XSAttributeChecker.ATTIDX_COUNT - 1);
        array[XSAttributeChecker.ATTIDX_ISRETURNED] = Boolean.FALSE;
        return array;
    }
    
    public void returnAttrArray(final Object[] array, final XSDocumentInfo xsDocumentInfo) {
        if (xsDocumentInfo != null) {
            xsDocumentInfo.fNamespaceSupport.popContext();
        }
        if (this.fPoolPos == 0 || array == null || array.length != XSAttributeChecker.ATTIDX_COUNT || (boolean)array[XSAttributeChecker.ATTIDX_ISRETURNED]) {
            return;
        }
        array[XSAttributeChecker.ATTIDX_ISRETURNED] = Boolean.TRUE;
        if (array[XSAttributeChecker.ATTIDX_NONSCHEMA] != null) {
            ((Vector)array[XSAttributeChecker.ATTIDX_NONSCHEMA]).clear();
        }
        this.fArrayPool[--this.fPoolPos] = array;
    }
    
    public void resolveNamespace(final Element element, final Attr[] array, final SchemaNamespaceSupport schemaNamespaceSupport) {
        schemaNamespaceSupport.pushContext();
        for (final Attr attr : array) {
            final String name = DOMUtil.getName(attr);
            String s = null;
            if (name.equals(XMLSymbols.PREFIX_XMLNS)) {
                s = XMLSymbols.EMPTY_STRING;
            }
            else if (name.startsWith("xmlns:")) {
                s = this.fSymbolTable.addSymbol(DOMUtil.getLocalName(attr));
            }
            if (s != null) {
                final String addSymbol = this.fSymbolTable.addSymbol(DOMUtil.getValue(attr));
                schemaNamespaceSupport.declarePrefix(s, (addSymbol.length() != 0) ? addSymbol : null);
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
        ATTIDX_NONSCHEMA = XSAttributeChecker.ATTIDX_COUNT++;
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
        ATTIDX_XML_LANG = XSAttributeChecker.ATTIDX_COUNT++;
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
        fExtraDVs = new XSSimpleType[9];
        final SchemaGrammar.BuiltinSchemaGrammar sg_SchemaNS = SchemaGrammar.SG_SchemaNS;
        XSAttributeChecker.fExtraDVs[0] = (XSSimpleType)sg_SchemaNS.getGlobalTypeDecl("anyURI");
        XSAttributeChecker.fExtraDVs[1] = (XSSimpleType)sg_SchemaNS.getGlobalTypeDecl("ID");
        XSAttributeChecker.fExtraDVs[2] = (XSSimpleType)sg_SchemaNS.getGlobalTypeDecl("QName");
        XSAttributeChecker.fExtraDVs[3] = (XSSimpleType)sg_SchemaNS.getGlobalTypeDecl("string");
        XSAttributeChecker.fExtraDVs[4] = (XSSimpleType)sg_SchemaNS.getGlobalTypeDecl("token");
        XSAttributeChecker.fExtraDVs[5] = (XSSimpleType)sg_SchemaNS.getGlobalTypeDecl("NCName");
        XSAttributeChecker.fExtraDVs[6] = XSAttributeChecker.fExtraDVs[3];
        XSAttributeChecker.fExtraDVs[6] = XSAttributeChecker.fExtraDVs[3];
        XSAttributeChecker.fExtraDVs[8] = (XSSimpleType)sg_SchemaNS.getGlobalTypeDecl("language");
        int n = 0;
        final int n2 = n++;
        final int n3 = n++;
        final int n4 = n++;
        final int n5 = n++;
        final int n6 = n++;
        final int n7 = n++;
        final int n8 = n++;
        final int n9 = n++;
        final int n10 = n++;
        final int n11 = n++;
        final int n12 = n++;
        final int n13 = n++;
        final int n14 = n++;
        final int n15 = n++;
        final int n16 = n++;
        final int n17 = n++;
        final int n18 = n++;
        final int n19 = n++;
        final int n20 = n++;
        final int n21 = n++;
        final int n22 = n++;
        final int n23 = n++;
        final int n24 = n++;
        final int n25 = n++;
        final int n26 = n++;
        final int n27 = n++;
        final int n28 = n++;
        final int n29 = n++;
        final int n30 = n++;
        final int n31 = n++;
        final int n32 = n++;
        final int n33 = n++;
        final int n34 = n++;
        final int n35 = n++;
        final int n36 = n++;
        final int n37 = n++;
        final int n38 = n++;
        final int n39 = n++;
        final int n40 = n++;
        final int n41 = n++;
        final int n42 = n++;
        final int n43 = n++;
        final int n44 = n++;
        final int n45 = n++;
        final int n46 = n++;
        final int n47 = n++;
        final int n48 = n++;
        final int n49 = n++;
        final OneAttr[] array = new OneAttr[n];
        array[n2] = new OneAttr(SchemaSymbols.ATT_ABSTRACT, -15, XSAttributeChecker.ATTIDX_ABSTRACT, Boolean.FALSE);
        array[n3] = new OneAttr(SchemaSymbols.ATT_ATTRIBUTEFORMDEFAULT, -6, XSAttributeChecker.ATTIDX_AFORMDEFAULT, XSAttributeChecker.INT_UNQUALIFIED);
        array[n4] = new OneAttr(SchemaSymbols.ATT_BASE, 2, XSAttributeChecker.ATTIDX_BASE, null);
        array[n5] = new OneAttr(SchemaSymbols.ATT_BASE, 2, XSAttributeChecker.ATTIDX_BASE, null);
        array[n6] = new OneAttr(SchemaSymbols.ATT_BLOCK, -1, XSAttributeChecker.ATTIDX_BLOCK, null);
        array[n7] = new OneAttr(SchemaSymbols.ATT_BLOCK, -2, XSAttributeChecker.ATTIDX_BLOCK, null);
        array[n8] = new OneAttr(SchemaSymbols.ATT_BLOCKDEFAULT, -1, XSAttributeChecker.ATTIDX_BLOCKDEFAULT, XSAttributeChecker.INT_EMPTY_SET);
        array[n9] = new OneAttr(SchemaSymbols.ATT_DEFAULT, 3, XSAttributeChecker.ATTIDX_DEFAULT, null);
        array[n10] = new OneAttr(SchemaSymbols.ATT_ELEMENTFORMDEFAULT, -6, XSAttributeChecker.ATTIDX_EFORMDEFAULT, XSAttributeChecker.INT_UNQUALIFIED);
        array[n11] = new OneAttr(SchemaSymbols.ATT_FINAL, -3, XSAttributeChecker.ATTIDX_FINAL, null);
        array[n12] = new OneAttr(SchemaSymbols.ATT_FINAL, -4, XSAttributeChecker.ATTIDX_FINAL, null);
        array[n13] = new OneAttr(SchemaSymbols.ATT_FINALDEFAULT, -5, XSAttributeChecker.ATTIDX_FINALDEFAULT, XSAttributeChecker.INT_EMPTY_SET);
        array[n14] = new OneAttr(SchemaSymbols.ATT_FIXED, 3, XSAttributeChecker.ATTIDX_FIXED, null);
        array[n15] = new OneAttr(SchemaSymbols.ATT_FIXED, -15, XSAttributeChecker.ATTIDX_FIXED, Boolean.FALSE);
        array[n16] = new OneAttr(SchemaSymbols.ATT_FORM, -6, XSAttributeChecker.ATTIDX_FORM, null);
        array[n17] = new OneAttr(SchemaSymbols.ATT_ID, 1, XSAttributeChecker.ATTIDX_ID, null);
        array[n18] = new OneAttr(SchemaSymbols.ATT_ITEMTYPE, 2, XSAttributeChecker.ATTIDX_ITEMTYPE, null);
        array[n19] = new OneAttr(SchemaSymbols.ATT_MAXOCCURS, -7, XSAttributeChecker.ATTIDX_MAXOCCURS, XSAttributeChecker.fXIntPool.getXInt(1));
        array[n20] = new OneAttr(SchemaSymbols.ATT_MAXOCCURS, -8, XSAttributeChecker.ATTIDX_MAXOCCURS, XSAttributeChecker.fXIntPool.getXInt(1));
        array[n21] = new OneAttr(SchemaSymbols.ATT_MEMBERTYPES, -9, XSAttributeChecker.ATTIDX_MEMBERTYPES, null);
        array[n22] = new OneAttr(SchemaSymbols.ATT_MINOCCURS, -16, XSAttributeChecker.ATTIDX_MINOCCURS, XSAttributeChecker.fXIntPool.getXInt(1));
        array[n23] = new OneAttr(SchemaSymbols.ATT_MINOCCURS, -10, XSAttributeChecker.ATTIDX_MINOCCURS, XSAttributeChecker.fXIntPool.getXInt(1));
        array[n24] = new OneAttr(SchemaSymbols.ATT_MIXED, -15, XSAttributeChecker.ATTIDX_MIXED, Boolean.FALSE);
        array[n25] = new OneAttr(SchemaSymbols.ATT_MIXED, -15, XSAttributeChecker.ATTIDX_MIXED, null);
        array[n26] = new OneAttr(SchemaSymbols.ATT_NAME, 5, XSAttributeChecker.ATTIDX_NAME, null);
        array[n27] = new OneAttr(SchemaSymbols.ATT_NAMESPACE, -11, XSAttributeChecker.ATTIDX_NAMESPACE, XSAttributeChecker.INT_ANY_ANY);
        array[n28] = new OneAttr(SchemaSymbols.ATT_NAMESPACE, 0, XSAttributeChecker.ATTIDX_NAMESPACE, null);
        array[n29] = new OneAttr(SchemaSymbols.ATT_NILLABLE, -15, XSAttributeChecker.ATTIDX_NILLABLE, Boolean.FALSE);
        array[n30] = new OneAttr(SchemaSymbols.ATT_PROCESSCONTENTS, -12, XSAttributeChecker.ATTIDX_PROCESSCONTENTS, XSAttributeChecker.INT_ANY_STRICT);
        array[n31] = new OneAttr(SchemaSymbols.ATT_PUBLIC, 4, XSAttributeChecker.ATTIDX_PUBLIC, null);
        array[n32] = new OneAttr(SchemaSymbols.ATT_REF, 2, XSAttributeChecker.ATTIDX_REF, null);
        array[n33] = new OneAttr(SchemaSymbols.ATT_REFER, 2, XSAttributeChecker.ATTIDX_REFER, null);
        array[n34] = new OneAttr(SchemaSymbols.ATT_SCHEMALOCATION, 0, XSAttributeChecker.ATTIDX_SCHEMALOCATION, null);
        array[n35] = new OneAttr(SchemaSymbols.ATT_SCHEMALOCATION, 0, XSAttributeChecker.ATTIDX_SCHEMALOCATION, null);
        array[n36] = new OneAttr(SchemaSymbols.ATT_SOURCE, 0, XSAttributeChecker.ATTIDX_SOURCE, null);
        array[n37] = new OneAttr(SchemaSymbols.ATT_SUBSTITUTIONGROUP, 2, XSAttributeChecker.ATTIDX_SUBSGROUP, null);
        array[n38] = new OneAttr(SchemaSymbols.ATT_SYSTEM, 0, XSAttributeChecker.ATTIDX_SYSTEM, null);
        array[n39] = new OneAttr(SchemaSymbols.ATT_TARGETNAMESPACE, 0, XSAttributeChecker.ATTIDX_TARGETNAMESPACE, null);
        array[n40] = new OneAttr(SchemaSymbols.ATT_TYPE, 2, XSAttributeChecker.ATTIDX_TYPE, null);
        array[n41] = new OneAttr(SchemaSymbols.ATT_USE, -13, XSAttributeChecker.ATTIDX_USE, XSAttributeChecker.INT_USE_OPTIONAL);
        array[n42] = new OneAttr(SchemaSymbols.ATT_VALUE, -16, XSAttributeChecker.ATTIDX_VALUE, null);
        array[n43] = new OneAttr(SchemaSymbols.ATT_VALUE, -17, XSAttributeChecker.ATTIDX_VALUE, null);
        array[n44] = new OneAttr(SchemaSymbols.ATT_VALUE, 3, XSAttributeChecker.ATTIDX_VALUE, null);
        array[n45] = new OneAttr(SchemaSymbols.ATT_VALUE, -14, XSAttributeChecker.ATTIDX_VALUE, null);
        array[n46] = new OneAttr(SchemaSymbols.ATT_VERSION, 4, XSAttributeChecker.ATTIDX_VERSION, null);
        array[n47] = new OneAttr(SchemaSymbols.ATT_XML_LANG, 8, XSAttributeChecker.ATTIDX_XML_LANG, null);
        array[n48] = new OneAttr(SchemaSymbols.ATT_XPATH, 6, XSAttributeChecker.ATTIDX_XPATH, null);
        array[n49] = new OneAttr(SchemaSymbols.ATT_XPATH, 7, XSAttributeChecker.ATTIDX_XPATH, null);
        final Container container = Container.getContainer(5);
        container.put(SchemaSymbols.ATT_DEFAULT, array[n9]);
        container.put(SchemaSymbols.ATT_FIXED, array[n14]);
        container.put(SchemaSymbols.ATT_ID, array[n17]);
        container.put(SchemaSymbols.ATT_NAME, array[n26]);
        container.put(SchemaSymbols.ATT_TYPE, array[n40]);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_ATTRIBUTE, new OneElement(container));
        final Container container2 = Container.getContainer(7);
        container2.put(SchemaSymbols.ATT_DEFAULT, array[n9]);
        container2.put(SchemaSymbols.ATT_FIXED, array[n14]);
        container2.put(SchemaSymbols.ATT_FORM, array[n16]);
        container2.put(SchemaSymbols.ATT_ID, array[n17]);
        container2.put(SchemaSymbols.ATT_NAME, array[n26]);
        container2.put(SchemaSymbols.ATT_TYPE, array[n40]);
        container2.put(SchemaSymbols.ATT_USE, array[n41]);
        XSAttributeChecker.fEleAttrsMapL.put("attribute_n", new OneElement(container2));
        final Container container3 = Container.getContainer(5);
        container3.put(SchemaSymbols.ATT_DEFAULT, array[n9]);
        container3.put(SchemaSymbols.ATT_FIXED, array[n14]);
        container3.put(SchemaSymbols.ATT_ID, array[n17]);
        container3.put(SchemaSymbols.ATT_REF, array[n32]);
        container3.put(SchemaSymbols.ATT_USE, array[n41]);
        XSAttributeChecker.fEleAttrsMapL.put("attribute_r", new OneElement(container3));
        final Container container4 = Container.getContainer(10);
        container4.put(SchemaSymbols.ATT_ABSTRACT, array[n2]);
        container4.put(SchemaSymbols.ATT_BLOCK, array[n6]);
        container4.put(SchemaSymbols.ATT_DEFAULT, array[n9]);
        container4.put(SchemaSymbols.ATT_FINAL, array[n11]);
        container4.put(SchemaSymbols.ATT_FIXED, array[n14]);
        container4.put(SchemaSymbols.ATT_ID, array[n17]);
        container4.put(SchemaSymbols.ATT_NAME, array[n26]);
        container4.put(SchemaSymbols.ATT_NILLABLE, array[n29]);
        container4.put(SchemaSymbols.ATT_SUBSTITUTIONGROUP, array[n37]);
        container4.put(SchemaSymbols.ATT_TYPE, array[n40]);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_ELEMENT, new OneElement(container4));
        final Container container5 = Container.getContainer(10);
        container5.put(SchemaSymbols.ATT_BLOCK, array[n6]);
        container5.put(SchemaSymbols.ATT_DEFAULT, array[n9]);
        container5.put(SchemaSymbols.ATT_FIXED, array[n14]);
        container5.put(SchemaSymbols.ATT_FORM, array[n16]);
        container5.put(SchemaSymbols.ATT_ID, array[n17]);
        container5.put(SchemaSymbols.ATT_MAXOCCURS, array[n19]);
        container5.put(SchemaSymbols.ATT_MINOCCURS, array[n22]);
        container5.put(SchemaSymbols.ATT_NAME, array[n26]);
        container5.put(SchemaSymbols.ATT_NILLABLE, array[n29]);
        container5.put(SchemaSymbols.ATT_TYPE, array[n40]);
        XSAttributeChecker.fEleAttrsMapL.put("element_n", new OneElement(container5));
        final Container container6 = Container.getContainer(4);
        container6.put(SchemaSymbols.ATT_ID, array[n17]);
        container6.put(SchemaSymbols.ATT_MAXOCCURS, array[n19]);
        container6.put(SchemaSymbols.ATT_MINOCCURS, array[n22]);
        container6.put(SchemaSymbols.ATT_REF, array[n32]);
        XSAttributeChecker.fEleAttrsMapL.put("element_r", new OneElement(container6));
        final Container container7 = Container.getContainer(6);
        container7.put(SchemaSymbols.ATT_ABSTRACT, array[n2]);
        container7.put(SchemaSymbols.ATT_BLOCK, array[n7]);
        container7.put(SchemaSymbols.ATT_FINAL, array[n11]);
        container7.put(SchemaSymbols.ATT_ID, array[n17]);
        container7.put(SchemaSymbols.ATT_MIXED, array[n24]);
        container7.put(SchemaSymbols.ATT_NAME, array[n26]);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_COMPLEXTYPE, new OneElement(container7));
        final Container container8 = Container.getContainer(4);
        container8.put(SchemaSymbols.ATT_ID, array[n17]);
        container8.put(SchemaSymbols.ATT_NAME, array[n26]);
        container8.put(SchemaSymbols.ATT_PUBLIC, array[n31]);
        container8.put(SchemaSymbols.ATT_SYSTEM, array[n38]);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_NOTATION, new OneElement(container8));
        final Container container9 = Container.getContainer(2);
        container9.put(SchemaSymbols.ATT_ID, array[n17]);
        container9.put(SchemaSymbols.ATT_MIXED, array[n24]);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_COMPLEXTYPE, new OneElement(container9));
        final Container container10 = Container.getContainer(1);
        container10.put(SchemaSymbols.ATT_ID, array[n17]);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_SIMPLECONTENT, new OneElement(container10));
        final Container container11 = Container.getContainer(2);
        container11.put(SchemaSymbols.ATT_BASE, array[n5]);
        container11.put(SchemaSymbols.ATT_ID, array[n17]);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_RESTRICTION, new OneElement(container11));
        final Container container12 = Container.getContainer(2);
        container12.put(SchemaSymbols.ATT_BASE, array[n4]);
        container12.put(SchemaSymbols.ATT_ID, array[n17]);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_EXTENSION, new OneElement(container12));
        final Container container13 = Container.getContainer(2);
        container13.put(SchemaSymbols.ATT_ID, array[n17]);
        container13.put(SchemaSymbols.ATT_REF, array[n32]);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_ATTRIBUTEGROUP, new OneElement(container13));
        final Container container14 = Container.getContainer(3);
        container14.put(SchemaSymbols.ATT_ID, array[n17]);
        container14.put(SchemaSymbols.ATT_NAMESPACE, array[n27]);
        container14.put(SchemaSymbols.ATT_PROCESSCONTENTS, array[n30]);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_ANYATTRIBUTE, new OneElement(container14));
        final Container container15 = Container.getContainer(2);
        container15.put(SchemaSymbols.ATT_ID, array[n17]);
        container15.put(SchemaSymbols.ATT_MIXED, array[n25]);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_COMPLEXCONTENT, new OneElement(container15));
        final Container container16 = Container.getContainer(2);
        container16.put(SchemaSymbols.ATT_ID, array[n17]);
        container16.put(SchemaSymbols.ATT_NAME, array[n26]);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_ATTRIBUTEGROUP, new OneElement(container16));
        final Container container17 = Container.getContainer(2);
        container17.put(SchemaSymbols.ATT_ID, array[n17]);
        container17.put(SchemaSymbols.ATT_NAME, array[n26]);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_GROUP, new OneElement(container17));
        final Container container18 = Container.getContainer(4);
        container18.put(SchemaSymbols.ATT_ID, array[n17]);
        container18.put(SchemaSymbols.ATT_MAXOCCURS, array[n19]);
        container18.put(SchemaSymbols.ATT_MINOCCURS, array[n22]);
        container18.put(SchemaSymbols.ATT_REF, array[n32]);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_GROUP, new OneElement(container18));
        final Container container19 = Container.getContainer(3);
        container19.put(SchemaSymbols.ATT_ID, array[n17]);
        container19.put(SchemaSymbols.ATT_MAXOCCURS, array[n20]);
        container19.put(SchemaSymbols.ATT_MINOCCURS, array[n23]);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_ALL, new OneElement(container19));
        final Container container20 = Container.getContainer(3);
        container20.put(SchemaSymbols.ATT_ID, array[n17]);
        container20.put(SchemaSymbols.ATT_MAXOCCURS, array[n19]);
        container20.put(SchemaSymbols.ATT_MINOCCURS, array[n22]);
        final OneElement oneElement = new OneElement(container20);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_CHOICE, oneElement);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_SEQUENCE, oneElement);
        final Container container21 = Container.getContainer(5);
        container21.put(SchemaSymbols.ATT_ID, array[n17]);
        container21.put(SchemaSymbols.ATT_MAXOCCURS, array[n19]);
        container21.put(SchemaSymbols.ATT_MINOCCURS, array[n22]);
        container21.put(SchemaSymbols.ATT_NAMESPACE, array[n27]);
        container21.put(SchemaSymbols.ATT_PROCESSCONTENTS, array[n30]);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_ANY, new OneElement(container21));
        final Container container22 = Container.getContainer(2);
        container22.put(SchemaSymbols.ATT_ID, array[n17]);
        container22.put(SchemaSymbols.ATT_NAME, array[n26]);
        final OneElement oneElement2 = new OneElement(container22);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_UNIQUE, oneElement2);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_KEY, oneElement2);
        final Container container23 = Container.getContainer(3);
        container23.put(SchemaSymbols.ATT_ID, array[n17]);
        container23.put(SchemaSymbols.ATT_NAME, array[n26]);
        container23.put(SchemaSymbols.ATT_REFER, array[n33]);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_KEYREF, new OneElement(container23));
        final Container container24 = Container.getContainer(2);
        container24.put(SchemaSymbols.ATT_ID, array[n17]);
        container24.put(SchemaSymbols.ATT_XPATH, array[n48]);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_SELECTOR, new OneElement(container24));
        final Container container25 = Container.getContainer(2);
        container25.put(SchemaSymbols.ATT_ID, array[n17]);
        container25.put(SchemaSymbols.ATT_XPATH, array[n49]);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_FIELD, new OneElement(container25));
        final Container container26 = Container.getContainer(1);
        container26.put(SchemaSymbols.ATT_ID, array[n17]);
        final OneElement oneElement3 = new OneElement(container26);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_ANNOTATION, oneElement3);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_ANNOTATION, oneElement3);
        final Container container27 = Container.getContainer(1);
        container27.put(SchemaSymbols.ATT_SOURCE, array[n36]);
        final OneElement oneElement4 = new OneElement(container27);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_APPINFO, oneElement4);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_APPINFO, oneElement4);
        final Container container28 = Container.getContainer(2);
        container28.put(SchemaSymbols.ATT_SOURCE, array[n36]);
        container28.put(SchemaSymbols.ATT_XML_LANG, array[n47]);
        final OneElement oneElement5 = new OneElement(container28);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_DOCUMENTATION, oneElement5);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_DOCUMENTATION, oneElement5);
        final Container container29 = Container.getContainer(3);
        container29.put(SchemaSymbols.ATT_FINAL, array[n12]);
        container29.put(SchemaSymbols.ATT_ID, array[n17]);
        container29.put(SchemaSymbols.ATT_NAME, array[n26]);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_SIMPLETYPE, new OneElement(container29));
        final Container container30 = Container.getContainer(2);
        container30.put(SchemaSymbols.ATT_FINAL, array[n12]);
        container30.put(SchemaSymbols.ATT_ID, array[n17]);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_SIMPLETYPE, new OneElement(container30));
        final Container container31 = Container.getContainer(2);
        container31.put(SchemaSymbols.ATT_ID, array[n17]);
        container31.put(SchemaSymbols.ATT_ITEMTYPE, array[n18]);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_LIST, new OneElement(container31));
        final Container container32 = Container.getContainer(2);
        container32.put(SchemaSymbols.ATT_ID, array[n17]);
        container32.put(SchemaSymbols.ATT_MEMBERTYPES, array[n21]);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_UNION, new OneElement(container32));
        final Container container33 = Container.getContainer(8);
        container33.put(SchemaSymbols.ATT_ATTRIBUTEFORMDEFAULT, array[n3]);
        container33.put(SchemaSymbols.ATT_BLOCKDEFAULT, array[n8]);
        container33.put(SchemaSymbols.ATT_ELEMENTFORMDEFAULT, array[n10]);
        container33.put(SchemaSymbols.ATT_FINALDEFAULT, array[n13]);
        container33.put(SchemaSymbols.ATT_ID, array[n17]);
        container33.put(SchemaSymbols.ATT_TARGETNAMESPACE, array[n39]);
        container33.put(SchemaSymbols.ATT_VERSION, array[n46]);
        container33.put(SchemaSymbols.ATT_XML_LANG, array[n47]);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_SCHEMA, new OneElement(container33));
        final Container container34 = Container.getContainer(2);
        container34.put(SchemaSymbols.ATT_ID, array[n17]);
        container34.put(SchemaSymbols.ATT_SCHEMALOCATION, array[n34]);
        final OneElement oneElement6 = new OneElement(container34);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_INCLUDE, oneElement6);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_REDEFINE, oneElement6);
        final Container container35 = Container.getContainer(3);
        container35.put(SchemaSymbols.ATT_ID, array[n17]);
        container35.put(SchemaSymbols.ATT_NAMESPACE, array[n28]);
        container35.put(SchemaSymbols.ATT_SCHEMALOCATION, array[n35]);
        XSAttributeChecker.fEleAttrsMapG.put(SchemaSymbols.ELT_IMPORT, new OneElement(container35));
        final Container container36 = Container.getContainer(3);
        container36.put(SchemaSymbols.ATT_ID, array[n17]);
        container36.put(SchemaSymbols.ATT_VALUE, array[n42]);
        container36.put(SchemaSymbols.ATT_FIXED, array[n15]);
        final OneElement oneElement7 = new OneElement(container36);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_LENGTH, oneElement7);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_MINLENGTH, oneElement7);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_MAXLENGTH, oneElement7);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_FRACTIONDIGITS, oneElement7);
        final Container container37 = Container.getContainer(3);
        container37.put(SchemaSymbols.ATT_ID, array[n17]);
        container37.put(SchemaSymbols.ATT_VALUE, array[n43]);
        container37.put(SchemaSymbols.ATT_FIXED, array[n15]);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_TOTALDIGITS, new OneElement(container37));
        final Container container38 = Container.getContainer(2);
        container38.put(SchemaSymbols.ATT_ID, array[n17]);
        container38.put(SchemaSymbols.ATT_VALUE, array[n44]);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_PATTERN, new OneElement(container38));
        final Container container39 = Container.getContainer(2);
        container39.put(SchemaSymbols.ATT_ID, array[n17]);
        container39.put(SchemaSymbols.ATT_VALUE, array[n44]);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_ENUMERATION, new OneElement(container39));
        final Container container40 = Container.getContainer(3);
        container40.put(SchemaSymbols.ATT_ID, array[n17]);
        container40.put(SchemaSymbols.ATT_VALUE, array[n45]);
        container40.put(SchemaSymbols.ATT_FIXED, array[n15]);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_WHITESPACE, new OneElement(container40));
        final Container container41 = Container.getContainer(3);
        container41.put(SchemaSymbols.ATT_ID, array[n17]);
        container41.put(SchemaSymbols.ATT_VALUE, array[n44]);
        container41.put(SchemaSymbols.ATT_FIXED, array[n15]);
        final OneElement oneElement8 = new OneElement(container41);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_MAXINCLUSIVE, oneElement8);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_MAXEXCLUSIVE, oneElement8);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_MININCLUSIVE, oneElement8);
        XSAttributeChecker.fEleAttrsMapL.put(SchemaSymbols.ELT_MINEXCLUSIVE, oneElement8);
        XSAttributeChecker.fSeenTemp = new boolean[XSAttributeChecker.ATTIDX_COUNT];
        XSAttributeChecker.fTempArray = new Object[XSAttributeChecker.ATTIDX_COUNT];
    }
}
