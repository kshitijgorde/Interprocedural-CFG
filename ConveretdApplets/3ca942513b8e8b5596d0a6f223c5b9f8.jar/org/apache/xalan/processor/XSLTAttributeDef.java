// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import org.apache.xalan.res.XSLMessages;
import org.apache.xml.utils.StringVector;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.xml.utils.XML11Char;
import org.apache.xpath.XPath;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.utils.QName;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import org.apache.xalan.templates.AVT;
import org.apache.xalan.templates.ElemTemplateElement;
import org.apache.xml.utils.StringToIntTable;

public class XSLTAttributeDef
{
    static final int FATAL = 0;
    static final int ERROR = 1;
    static final int WARNING = 2;
    static final int T_CDATA = 1;
    static final int T_URL = 2;
    static final int T_AVT = 3;
    static final int T_PATTERN = 4;
    static final int T_EXPR = 5;
    static final int T_CHAR = 6;
    static final int T_NUMBER = 7;
    static final int T_YESNO = 8;
    static final int T_QNAME = 9;
    static final int T_QNAMES = 10;
    static final int T_ENUM = 11;
    static final int T_SIMPLEPATTERNLIST = 12;
    static final int T_NMTOKEN = 13;
    static final int T_STRINGLIST = 14;
    static final int T_PREFIX_URLLIST = 15;
    static final int T_ENUM_OR_PQNAME = 16;
    static final int T_NCNAME = 17;
    static final int T_AVT_QNAME = 18;
    static final int T_QNAMES_RESOLVE_NULL = 19;
    static final int T_PREFIXLIST = 20;
    static final XSLTAttributeDef m_foreignAttr;
    static final String S_FOREIGNATTR_SETTER = "setForeignAttr";
    private String m_namespace;
    private String m_name;
    private int m_type;
    private StringToIntTable m_enums;
    private String m_default;
    private boolean m_required;
    private boolean m_supportsAVT;
    int m_errorType;
    String m_setterString;
    static /* synthetic */ Class class$org$apache$xpath$XPath;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Character;
    static /* synthetic */ Class class$java$lang$Short;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Long;
    
    XSLTAttributeDef(final String namespace, final String name, final int type, final boolean required, final boolean supportsAVT, final int errorType) {
        this.m_errorType = 2;
        this.m_setterString = null;
        this.m_namespace = namespace;
        this.m_name = name;
        this.m_type = type;
        this.m_required = required;
        this.m_supportsAVT = supportsAVT;
        this.m_errorType = errorType;
    }
    
    XSLTAttributeDef(final String namespace, final String name, final int type, final boolean supportsAVT, final int errorType, final String defaultVal) {
        this.m_errorType = 2;
        this.m_setterString = null;
        this.m_namespace = namespace;
        this.m_name = name;
        this.m_type = type;
        this.m_required = false;
        this.m_supportsAVT = supportsAVT;
        this.m_errorType = errorType;
        this.m_default = defaultVal;
    }
    
    XSLTAttributeDef(final String namespace, final String name, final boolean required, final boolean supportsAVT, final boolean prefixedQNameValAllowed, final int errorType, final String k1, final int v1, final String k2, final int v2) {
        this.m_errorType = 2;
        this.m_setterString = null;
        this.m_namespace = namespace;
        this.m_name = name;
        this.m_type = (prefixedQNameValAllowed ? 16 : 11);
        this.m_required = required;
        this.m_supportsAVT = supportsAVT;
        this.m_errorType = errorType;
        (this.m_enums = new StringToIntTable(2)).put(k1, v1);
        this.m_enums.put(k2, v2);
    }
    
    XSLTAttributeDef(final String namespace, final String name, final boolean required, final boolean supportsAVT, final boolean prefixedQNameValAllowed, final int errorType, final String k1, final int v1, final String k2, final int v2, final String k3, final int v3) {
        this.m_errorType = 2;
        this.m_setterString = null;
        this.m_namespace = namespace;
        this.m_name = name;
        this.m_type = (prefixedQNameValAllowed ? 16 : 11);
        this.m_required = required;
        this.m_supportsAVT = supportsAVT;
        this.m_errorType = errorType;
        (this.m_enums = new StringToIntTable(3)).put(k1, v1);
        this.m_enums.put(k2, v2);
        this.m_enums.put(k3, v3);
    }
    
    XSLTAttributeDef(final String namespace, final String name, final boolean required, final boolean supportsAVT, final boolean prefixedQNameValAllowed, final int errorType, final String k1, final int v1, final String k2, final int v2, final String k3, final int v3, final String k4, final int v4) {
        this.m_errorType = 2;
        this.m_setterString = null;
        this.m_namespace = namespace;
        this.m_name = name;
        this.m_type = (prefixedQNameValAllowed ? 16 : 11);
        this.m_required = required;
        this.m_supportsAVT = supportsAVT;
        this.m_errorType = errorType;
        (this.m_enums = new StringToIntTable(4)).put(k1, v1);
        this.m_enums.put(k2, v2);
        this.m_enums.put(k3, v3);
        this.m_enums.put(k4, v4);
    }
    
    String getNamespace() {
        return this.m_namespace;
    }
    
    String getName() {
        return this.m_name;
    }
    
    int getType() {
        return this.m_type;
    }
    
    private int getEnum(final String key) {
        return this.m_enums.get(key);
    }
    
    private String[] getEnumNames() {
        return this.m_enums.keys();
    }
    
    String getDefault() {
        return this.m_default;
    }
    
    void setDefault(final String def) {
        this.m_default = def;
    }
    
    boolean getRequired() {
        return this.m_required;
    }
    
    boolean getSupportsAVT() {
        return this.m_supportsAVT;
    }
    
    int getErrorType() {
        return this.m_errorType;
    }
    
    public String getSetterMethodName() {
        if (null == this.m_setterString) {
            if (XSLTAttributeDef.m_foreignAttr == this) {
                return "setForeignAttr";
            }
            if (this.m_name.equals("*")) {
                return this.m_setterString = "addLiteralResultAttribute";
            }
            final StringBuffer outBuf = new StringBuffer();
            outBuf.append("set");
            if (this.m_namespace != null && this.m_namespace.equals("http://www.w3.org/XML/1998/namespace")) {
                outBuf.append("Xml");
            }
            for (int n = this.m_name.length(), i = 0; i < n; ++i) {
                char c = this.m_name.charAt(i);
                if ('-' == c) {
                    ++i;
                    c = this.m_name.charAt(i);
                    c = Character.toUpperCase(c);
                }
                else if (0 == i) {
                    c = Character.toUpperCase(c);
                }
                outBuf.append(c);
            }
            this.m_setterString = outBuf.toString();
        }
        return this.m_setterString;
    }
    
    AVT processAVT(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value, final ElemTemplateElement owner) throws SAXException {
        try {
            final AVT avt = new AVT(handler, uri, name, rawName, value, owner);
            return avt;
        }
        catch (TransformerException te) {
            throw new SAXException(te);
        }
    }
    
    Object processCDATA(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value, final ElemTemplateElement owner) throws SAXException {
        if (this.getSupportsAVT()) {
            try {
                final AVT avt = new AVT(handler, uri, name, rawName, value, owner);
                return avt;
            }
            catch (TransformerException te) {
                throw new SAXException(te);
            }
        }
        return value;
    }
    
    Object processCHAR(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value, final ElemTemplateElement owner) throws SAXException {
        if (this.getSupportsAVT()) {
            try {
                final AVT avt = new AVT(handler, uri, name, rawName, value, owner);
                if (avt.isSimple() && value.length() != 1) {
                    this.handleError(handler, "INVALID_TCHAR", new Object[] { name, value }, null);
                    return null;
                }
                return avt;
            }
            catch (TransformerException te) {
                throw new SAXException(te);
            }
        }
        if (value.length() != 1) {
            this.handleError(handler, "INVALID_TCHAR", new Object[] { name, value }, null);
            return null;
        }
        return new Character(value.charAt(0));
    }
    
    Object processENUM(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value, final ElemTemplateElement owner) throws SAXException {
        AVT avt = null;
        if (this.getSupportsAVT()) {
            try {
                avt = new AVT(handler, uri, name, rawName, value, owner);
                if (!avt.isSimple()) {
                    return avt;
                }
            }
            catch (TransformerException te) {
                throw new SAXException(te);
            }
        }
        final int retVal = this.getEnum(value);
        if (retVal == -10000) {
            final StringBuffer enumNamesList = this.getListOfEnums();
            this.handleError(handler, "INVALID_ENUM", new Object[] { name, value, enumNamesList.toString() }, null);
            return null;
        }
        if (this.getSupportsAVT()) {
            return avt;
        }
        return new Integer(retVal);
    }
    
    Object processENUM_OR_PQNAME(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value, final ElemTemplateElement owner) throws SAXException {
        Object objToReturn = null;
        if (this.getSupportsAVT()) {
            try {
                final AVT avt = new AVT(handler, uri, name, rawName, value, owner);
                if (!avt.isSimple()) {
                    return avt;
                }
                objToReturn = avt;
            }
            catch (TransformerException te) {
                throw new SAXException(te);
            }
        }
        final int key = this.getEnum(value);
        if (key != -10000) {
            if (objToReturn == null) {
                objToReturn = new Integer(key);
            }
        }
        else {
            try {
                final QName qname = new QName(value, handler, true);
                if (objToReturn == null) {
                    objToReturn = qname;
                }
                if (qname.getPrefix() == null) {
                    final StringBuffer enumNamesList = this.getListOfEnums();
                    enumNamesList.append(" <qname-but-not-ncname>");
                    this.handleError(handler, "INVALID_ENUM", new Object[] { name, value, enumNamesList.toString() }, null);
                    return null;
                }
            }
            catch (IllegalArgumentException ie) {
                final StringBuffer enumNamesList = this.getListOfEnums();
                enumNamesList.append(" <qname-but-not-ncname>");
                this.handleError(handler, "INVALID_ENUM", new Object[] { name, value, enumNamesList.toString() }, ie);
                return null;
            }
            catch (RuntimeException re) {
                final StringBuffer enumNamesList2 = this.getListOfEnums();
                enumNamesList2.append(" <qname-but-not-ncname>");
                this.handleError(handler, "INVALID_ENUM", new Object[] { name, value, enumNamesList2.toString() }, re);
                return null;
            }
        }
        return objToReturn;
    }
    
    Object processEXPR(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value, final ElemTemplateElement owner) throws SAXException {
        try {
            final XPath expr = handler.createXPath(value, owner);
            return expr;
        }
        catch (TransformerException te) {
            final SAXException se = new SAXException(te);
            throw new SAXException(te);
        }
    }
    
    Object processNMTOKEN(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value, final ElemTemplateElement owner) throws SAXException {
        if (this.getSupportsAVT()) {
            try {
                final AVT avt = new AVT(handler, uri, name, rawName, value, owner);
                if (avt.isSimple() && !XML11Char.isXML11ValidNmtoken(value)) {
                    this.handleError(handler, "INVALID_NMTOKEN", new Object[] { name, value }, null);
                    return null;
                }
                return avt;
            }
            catch (TransformerException te) {
                throw new SAXException(te);
            }
        }
        if (!XML11Char.isXML11ValidNmtoken(value)) {
            this.handleError(handler, "INVALID_NMTOKEN", new Object[] { name, value }, null);
            return null;
        }
        return value;
    }
    
    Object processPATTERN(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value, final ElemTemplateElement owner) throws SAXException {
        try {
            final XPath pattern = handler.createMatchPatternXPath(value, owner);
            return pattern;
        }
        catch (TransformerException te) {
            throw new SAXException(te);
        }
    }
    
    Object processNUMBER(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value, final ElemTemplateElement owner) throws SAXException {
        if (this.getSupportsAVT()) {
            AVT avt = null;
            try {
                avt = new AVT(handler, uri, name, rawName, value, owner);
                if (avt.isSimple()) {
                    final Double val = Double.valueOf(value);
                }
            }
            catch (TransformerException te) {
                throw new SAXException(te);
            }
            catch (NumberFormatException nfe) {
                this.handleError(handler, "INVALID_NUMBER", new Object[] { name, value }, nfe);
                return null;
            }
            return avt;
        }
        try {
            return Double.valueOf(value);
        }
        catch (NumberFormatException nfe2) {
            this.handleError(handler, "INVALID_NUMBER", new Object[] { name, value }, nfe2);
            return null;
        }
    }
    
    Object processQNAME(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value, final ElemTemplateElement owner) throws SAXException {
        try {
            final QName qname = new QName(value, handler, true);
            return qname;
        }
        catch (IllegalArgumentException ie) {
            this.handleError(handler, "INVALID_QNAME", new Object[] { name, value }, ie);
            return null;
        }
        catch (RuntimeException re) {
            this.handleError(handler, "INVALID_QNAME", new Object[] { name, value }, re);
            return null;
        }
    }
    
    Object processAVT_QNAME(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value, final ElemTemplateElement owner) throws SAXException {
        AVT avt = null;
        try {
            avt = new AVT(handler, uri, name, rawName, value, owner);
            if (avt.isSimple()) {
                final int indexOfNSSep = value.indexOf(58);
                if (indexOfNSSep >= 0) {
                    final String prefix = value.substring(0, indexOfNSSep);
                    if (!XML11Char.isXML11ValidNCName(prefix)) {
                        this.handleError(handler, "INVALID_QNAME", new Object[] { name, value }, null);
                        return null;
                    }
                }
                final String localName = (indexOfNSSep < 0) ? value : value.substring(indexOfNSSep + 1);
                if (localName == null || localName.length() == 0 || !XML11Char.isXML11ValidNCName(localName)) {
                    this.handleError(handler, "INVALID_QNAME", new Object[] { name, value }, null);
                    return null;
                }
            }
        }
        catch (TransformerException te) {
            throw new SAXException(te);
        }
        return avt;
    }
    
    Object processNCNAME(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value, final ElemTemplateElement owner) throws SAXException {
        if (this.getSupportsAVT()) {
            AVT avt = null;
            try {
                avt = new AVT(handler, uri, name, rawName, value, owner);
                if (avt.isSimple() && !XML11Char.isXML11ValidNCName(value)) {
                    this.handleError(handler, "INVALID_NCNAME", new Object[] { name, value }, null);
                    return null;
                }
                return avt;
            }
            catch (TransformerException te) {
                throw new SAXException(te);
            }
        }
        if (!XML11Char.isXML11ValidNCName(value)) {
            this.handleError(handler, "INVALID_NCNAME", new Object[] { name, value }, null);
            return null;
        }
        return value;
    }
    
    Vector processQNAMES(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value) throws SAXException {
        final StringTokenizer tokenizer = new StringTokenizer(value, " \t\n\r\f");
        final int nQNames = tokenizer.countTokens();
        final Vector qnames = new Vector(nQNames);
        for (int i = 0; i < nQNames; ++i) {
            qnames.addElement(new QName(tokenizer.nextToken(), handler));
        }
        return qnames;
    }
    
    final Vector processQNAMESRNU(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value) throws SAXException {
        final StringTokenizer tokenizer = new StringTokenizer(value, " \t\n\r\f");
        final int nQNames = tokenizer.countTokens();
        final Vector qnames = new Vector(nQNames);
        final String defaultURI = handler.getNamespaceForPrefix("");
        for (int i = 0; i < nQNames; ++i) {
            final String tok = tokenizer.nextToken();
            if (tok.indexOf(58) == -1) {
                qnames.addElement(new QName(defaultURI, tok));
            }
            else {
                qnames.addElement(new QName(tok, handler));
            }
        }
        return qnames;
    }
    
    Vector processSIMPLEPATTERNLIST(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value, final ElemTemplateElement owner) throws SAXException {
        try {
            final StringTokenizer tokenizer = new StringTokenizer(value, " \t\n\r\f");
            final int nPatterns = tokenizer.countTokens();
            final Vector patterns = new Vector(nPatterns);
            for (int i = 0; i < nPatterns; ++i) {
                final XPath pattern = handler.createMatchPatternXPath(tokenizer.nextToken(), owner);
                patterns.addElement(pattern);
            }
            return patterns;
        }
        catch (TransformerException te) {
            throw new SAXException(te);
        }
    }
    
    StringVector processSTRINGLIST(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value) {
        final StringTokenizer tokenizer = new StringTokenizer(value, " \t\n\r\f");
        final int nStrings = tokenizer.countTokens();
        final StringVector strings = new StringVector(nStrings);
        for (int i = 0; i < nStrings; ++i) {
            strings.addElement(tokenizer.nextToken());
        }
        return strings;
    }
    
    StringVector processPREFIX_URLLIST(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value) throws SAXException {
        final StringTokenizer tokenizer = new StringTokenizer(value, " \t\n\r\f");
        final int nStrings = tokenizer.countTokens();
        final StringVector strings = new StringVector(nStrings);
        for (int i = 0; i < nStrings; ++i) {
            final String prefix = tokenizer.nextToken();
            final String url = handler.getNamespaceForPrefix(prefix);
            if (url == null) {
                throw new SAXException(XSLMessages.createMessage("ER_CANT_RESOLVE_NSPREFIX", new Object[] { prefix }));
            }
            strings.addElement(url);
        }
        return strings;
    }
    
    StringVector processPREFIX_LIST(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value) throws SAXException {
        final StringTokenizer tokenizer = new StringTokenizer(value, " \t\n\r\f");
        final int nStrings = tokenizer.countTokens();
        final StringVector strings = new StringVector(nStrings);
        for (int i = 0; i < nStrings; ++i) {
            final String prefix = tokenizer.nextToken();
            final String url = handler.getNamespaceForPrefix(prefix);
            if (!prefix.equals("#default") && url == null) {
                throw new SAXException(XSLMessages.createMessage("ER_CANT_RESOLVE_NSPREFIX", new Object[] { prefix }));
            }
            strings.addElement(prefix);
        }
        return strings;
    }
    
    Object processURL(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value, final ElemTemplateElement owner) throws SAXException {
        if (this.getSupportsAVT()) {
            try {
                final AVT avt = new AVT(handler, uri, name, rawName, value, owner);
                return avt;
            }
            catch (TransformerException te) {
                throw new SAXException(te);
            }
        }
        return value;
    }
    
    private Boolean processYESNO(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value) throws SAXException {
        if (!value.equals("yes") && !value.equals("no")) {
            this.handleError(handler, "INVALID_BOOLEAN", new Object[] { name, value }, null);
            return null;
        }
        return new Boolean(value.equals("yes"));
    }
    
    Object processValue(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value, final ElemTemplateElement owner) throws SAXException {
        final int type = this.getType();
        Object processedValue = null;
        switch (type) {
            case 3: {
                processedValue = this.processAVT(handler, uri, name, rawName, value, owner);
                break;
            }
            case 1: {
                processedValue = this.processCDATA(handler, uri, name, rawName, value, owner);
                break;
            }
            case 6: {
                processedValue = this.processCHAR(handler, uri, name, rawName, value, owner);
                break;
            }
            case 11: {
                processedValue = this.processENUM(handler, uri, name, rawName, value, owner);
                break;
            }
            case 5: {
                processedValue = this.processEXPR(handler, uri, name, rawName, value, owner);
                break;
            }
            case 13: {
                processedValue = this.processNMTOKEN(handler, uri, name, rawName, value, owner);
                break;
            }
            case 4: {
                processedValue = this.processPATTERN(handler, uri, name, rawName, value, owner);
                break;
            }
            case 7: {
                processedValue = this.processNUMBER(handler, uri, name, rawName, value, owner);
                break;
            }
            case 9: {
                processedValue = this.processQNAME(handler, uri, name, rawName, value, owner);
                break;
            }
            case 10: {
                processedValue = this.processQNAMES(handler, uri, name, rawName, value);
                break;
            }
            case 19: {
                processedValue = this.processQNAMESRNU(handler, uri, name, rawName, value);
                break;
            }
            case 12: {
                processedValue = this.processSIMPLEPATTERNLIST(handler, uri, name, rawName, value, owner);
                break;
            }
            case 2: {
                processedValue = this.processURL(handler, uri, name, rawName, value, owner);
                break;
            }
            case 8: {
                processedValue = this.processYESNO(handler, uri, name, rawName, value);
                break;
            }
            case 14: {
                processedValue = this.processSTRINGLIST(handler, uri, name, rawName, value);
                break;
            }
            case 15: {
                processedValue = this.processPREFIX_URLLIST(handler, uri, name, rawName, value);
                break;
            }
            case 16: {
                processedValue = this.processENUM_OR_PQNAME(handler, uri, name, rawName, value, owner);
                break;
            }
            case 17: {
                processedValue = this.processNCNAME(handler, uri, name, rawName, value, owner);
                break;
            }
            case 18: {
                processedValue = this.processAVT_QNAME(handler, uri, name, rawName, value, owner);
                break;
            }
            case 20: {
                processedValue = this.processPREFIX_LIST(handler, uri, name, rawName, value);
                break;
            }
        }
        return processedValue;
    }
    
    void setDefAttrValue(final StylesheetHandler handler, final ElemTemplateElement elem) throws SAXException {
        this.setAttrValue(handler, this.getNamespace(), this.getName(), this.getName(), this.getDefault(), elem);
    }
    
    private Class getPrimativeClass(final Object obj) {
        if (obj instanceof XPath) {
            return (XSLTAttributeDef.class$org$apache$xpath$XPath == null) ? (XSLTAttributeDef.class$org$apache$xpath$XPath = class$("org.apache.xpath.XPath")) : XSLTAttributeDef.class$org$apache$xpath$XPath;
        }
        Class cl = obj.getClass();
        if (cl == ((XSLTAttributeDef.class$java$lang$Double == null) ? (XSLTAttributeDef.class$java$lang$Double = class$("java.lang.Double")) : XSLTAttributeDef.class$java$lang$Double)) {
            cl = Double.TYPE;
        }
        if (cl == ((XSLTAttributeDef.class$java$lang$Float == null) ? (XSLTAttributeDef.class$java$lang$Float = class$("java.lang.Float")) : XSLTAttributeDef.class$java$lang$Float)) {
            cl = Float.TYPE;
        }
        else if (cl == ((XSLTAttributeDef.class$java$lang$Boolean == null) ? (XSLTAttributeDef.class$java$lang$Boolean = class$("java.lang.Boolean")) : XSLTAttributeDef.class$java$lang$Boolean)) {
            cl = Boolean.TYPE;
        }
        else if (cl == ((XSLTAttributeDef.class$java$lang$Byte == null) ? (XSLTAttributeDef.class$java$lang$Byte = class$("java.lang.Byte")) : XSLTAttributeDef.class$java$lang$Byte)) {
            cl = Byte.TYPE;
        }
        else if (cl == ((XSLTAttributeDef.class$java$lang$Character == null) ? (XSLTAttributeDef.class$java$lang$Character = class$("java.lang.Character")) : XSLTAttributeDef.class$java$lang$Character)) {
            cl = Character.TYPE;
        }
        else if (cl == ((XSLTAttributeDef.class$java$lang$Short == null) ? (XSLTAttributeDef.class$java$lang$Short = class$("java.lang.Short")) : XSLTAttributeDef.class$java$lang$Short)) {
            cl = Short.TYPE;
        }
        else if (cl == ((XSLTAttributeDef.class$java$lang$Integer == null) ? (XSLTAttributeDef.class$java$lang$Integer = class$("java.lang.Integer")) : XSLTAttributeDef.class$java$lang$Integer)) {
            cl = Integer.TYPE;
        }
        else if (cl == ((XSLTAttributeDef.class$java$lang$Long == null) ? (XSLTAttributeDef.class$java$lang$Long = class$("java.lang.Long")) : XSLTAttributeDef.class$java$lang$Long)) {
            cl = Long.TYPE;
        }
        return cl;
    }
    
    private StringBuffer getListOfEnums() {
        final StringBuffer enumNamesList = new StringBuffer();
        final String[] enumValues = this.getEnumNames();
        for (int i = 0; i < enumValues.length; ++i) {
            if (i > 0) {
                enumNamesList.append(' ');
            }
            enumNamesList.append(enumValues[i]);
        }
        return enumNamesList;
    }
    
    boolean setAttrValue(final StylesheetHandler handler, String attrUri, final String attrLocalName, final String attrRawName, final String attrValue, final ElemTemplateElement elem) throws SAXException {
        if (attrRawName.equals("xmlns") || attrRawName.startsWith("xmlns:")) {
            return true;
        }
        final String setterString = this.getSetterMethodName();
        if (null != setterString) {
            try {
                Method meth;
                Object[] args;
                if (setterString.equals("setForeignAttr")) {
                    if (attrUri == null) {
                        attrUri = "";
                    }
                    final Class sclass = attrUri.getClass();
                    final Class[] argTypes = { sclass, sclass, sclass, sclass };
                    meth = elem.getClass().getMethod(setterString, (Class<?>[])argTypes);
                    args = new Object[] { attrUri, attrLocalName, attrRawName, attrValue };
                }
                else {
                    final Object value = this.processValue(handler, attrUri, attrLocalName, attrRawName, attrValue, elem);
                    if (null == value) {
                        return false;
                    }
                    final Class[] argTypes = { this.getPrimativeClass(value) };
                    try {
                        meth = elem.getClass().getMethod(setterString, (Class<?>[])argTypes);
                    }
                    catch (NoSuchMethodException nsme3) {
                        final Class cl = value.getClass();
                        argTypes[0] = cl;
                        meth = elem.getClass().getMethod(setterString, (Class<?>[])argTypes);
                    }
                    args = new Object[] { value };
                }
                meth.invoke(elem, args);
            }
            catch (NoSuchMethodException nsme) {
                if (!setterString.equals("setForeignAttr")) {
                    handler.error("ER_FAILED_CALLING_METHOD", new Object[] { setterString }, nsme);
                    return false;
                }
            }
            catch (IllegalAccessException iae) {
                handler.error("ER_FAILED_CALLING_METHOD", new Object[] { setterString }, iae);
                return false;
            }
            catch (InvocationTargetException nsme2) {
                this.handleError(handler, "WG_ILLEGAL_ATTRIBUTE_VALUE", new Object[] { "name", this.getName() }, nsme2);
                return false;
            }
        }
        return true;
    }
    
    private void handleError(final StylesheetHandler handler, final String msg, final Object[] args, final Exception exc) throws SAXException {
        switch (this.getErrorType()) {
            case 0:
            case 1: {
                handler.error(msg, args, exc);
                break;
            }
            case 2: {
                handler.warn(msg, args);
                break;
            }
        }
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        m_foreignAttr = new XSLTAttributeDef("*", "*", 1, false, false, 2);
    }
}
