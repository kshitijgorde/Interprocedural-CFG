// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.utils.QName;
import java.util.StringTokenizer;
import org.apache.xml.utils.StringVector;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import org.apache.xalan.templates.AVT;
import org.apache.xpath.XPath;
import org.apache.xml.utils.StringToIntTable;

public class XSLTAttributeDef
{
    static final int T_CDATA = 1;
    static final int T_URL = 2;
    static final int T_AVT = 3;
    static final int T_PATTERN = 4;
    static final int T_EXPR = 5;
    static final int T_CHAR = 6;
    static final int T_PRIORITY = 7;
    static final int T_YESNO = 8;
    static final int T_QNAME = 9;
    static final int T_QNAMES = 10;
    static final int T_ENUM = 11;
    static final int T_SIMPLEPATTERNLIST = 12;
    static final int T_NMTOKEN = 13;
    static final int T_STRINGLIST = 14;
    static final int T_PREFIX_URLLIST = 15;
    static XSLTAttributeDef m_foreignAttr;
    static String S_FOREIGNATTR_SETTER;
    private String m_namespace;
    private String m_name;
    private int m_type;
    private StringToIntTable m_enums;
    private String m_default;
    private boolean m_required;
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
    
    static {
        XSLTAttributeDef.m_foreignAttr = new XSLTAttributeDef("*", "*", 1, false);
        XSLTAttributeDef.S_FOREIGNATTR_SETTER = "setForeignAttr";
    }
    
    XSLTAttributeDef(final String namespace, final String name, final int type, final String defaultVal) {
        this.m_setterString = null;
        this.m_namespace = namespace;
        this.m_name = name;
        this.m_type = type;
        this.m_required = false;
        this.m_default = defaultVal;
    }
    
    XSLTAttributeDef(final String namespace, final String name, final int type, final boolean required) {
        this.m_setterString = null;
        this.m_namespace = namespace;
        this.m_name = name;
        this.m_type = type;
        this.m_required = required;
    }
    
    XSLTAttributeDef(final String namespace, final String name, final boolean required, final String k1, final int v1, final String k2, final int v2) {
        this.m_setterString = null;
        this.m_namespace = namespace;
        this.m_name = name;
        this.m_type = 11;
        this.m_required = required;
        (this.m_enums = new StringToIntTable(2)).put(k1, v1);
        this.m_enums.put(k2, v2);
    }
    
    XSLTAttributeDef(final String namespace, final String name, final boolean required, final String k1, final int v1, final String k2, final int v2, final String k3, final int v3) {
        this.m_setterString = null;
        this.m_namespace = namespace;
        this.m_name = name;
        this.m_type = 11;
        this.m_required = required;
        (this.m_enums = new StringToIntTable(3)).put(k1, v1);
        this.m_enums.put(k2, v2);
        this.m_enums.put(k3, v3);
    }
    
    XSLTAttributeDef(final String namespace, final String name, final boolean required, final String k1, final int v1, final String k2, final int v2, final String k3, final int v3, final String k4, final int v4) {
        this.m_setterString = null;
        this.m_namespace = namespace;
        this.m_name = name;
        this.m_type = 11;
        this.m_required = required;
        (this.m_enums = new StringToIntTable(4)).put(k1, v1);
        this.m_enums.put(k2, v2);
        this.m_enums.put(k3, v3);
        this.m_enums.put(k4, v4);
    }
    
    static /* synthetic */ Class class$(final String class$) {
        try {
            return Class.forName(class$);
        }
        catch (ClassNotFoundException forName) {
            throw new NoClassDefFoundError(forName.getMessage());
        }
    }
    
    String getDefault() {
        return this.m_default;
    }
    
    private int getEnum(final String key) {
        return this.m_enums.get(key);
    }
    
    String getName() {
        return this.m_name;
    }
    
    String getNamespace() {
        return this.m_namespace;
    }
    
    private Class getPrimativeClass(final Object obj) {
        if (obj instanceof XPath) {
            return (XSLTAttributeDef.class$org$apache$xpath$XPath != null) ? XSLTAttributeDef.class$org$apache$xpath$XPath : (XSLTAttributeDef.class$org$apache$xpath$XPath = class$("org.apache.xpath.XPath"));
        }
        Class cl = obj.getClass();
        if (cl == ((XSLTAttributeDef.class$java$lang$Double != null) ? XSLTAttributeDef.class$java$lang$Double : (XSLTAttributeDef.class$java$lang$Double = class$("java.lang.Double")))) {
            cl = Double.TYPE;
        }
        if (cl == ((XSLTAttributeDef.class$java$lang$Float != null) ? XSLTAttributeDef.class$java$lang$Float : (XSLTAttributeDef.class$java$lang$Float = class$("java.lang.Float")))) {
            cl = Float.TYPE;
        }
        else if (cl == ((XSLTAttributeDef.class$java$lang$Boolean != null) ? XSLTAttributeDef.class$java$lang$Boolean : (XSLTAttributeDef.class$java$lang$Boolean = class$("java.lang.Boolean")))) {
            cl = Boolean.TYPE;
        }
        else if (cl == ((XSLTAttributeDef.class$java$lang$Byte != null) ? XSLTAttributeDef.class$java$lang$Byte : (XSLTAttributeDef.class$java$lang$Byte = class$("java.lang.Byte")))) {
            cl = Byte.TYPE;
        }
        else if (cl == ((XSLTAttributeDef.class$java$lang$Character != null) ? XSLTAttributeDef.class$java$lang$Character : (XSLTAttributeDef.class$java$lang$Character = class$("java.lang.Character")))) {
            cl = Character.TYPE;
        }
        else if (cl == ((XSLTAttributeDef.class$java$lang$Short != null) ? XSLTAttributeDef.class$java$lang$Short : (XSLTAttributeDef.class$java$lang$Short = class$("java.lang.Short")))) {
            cl = Short.TYPE;
        }
        else if (cl == ((XSLTAttributeDef.class$java$lang$Integer != null) ? XSLTAttributeDef.class$java$lang$Integer : (XSLTAttributeDef.class$java$lang$Integer = class$("java.lang.Integer")))) {
            cl = Integer.TYPE;
        }
        else if (cl == ((XSLTAttributeDef.class$java$lang$Long != null) ? XSLTAttributeDef.class$java$lang$Long : (XSLTAttributeDef.class$java$lang$Long = class$("java.lang.Long")))) {
            cl = Long.TYPE;
        }
        return cl;
    }
    
    boolean getRequired() {
        return this.m_required;
    }
    
    public String getSetterMethodName() {
        if (this.m_setterString == null) {
            if (XSLTAttributeDef.m_foreignAttr == this) {
                return XSLTAttributeDef.S_FOREIGNATTR_SETTER;
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
                if (c == '-') {
                    ++i;
                    c = this.m_name.charAt(i);
                    c = Character.toUpperCase(c);
                }
                else if (i == 0) {
                    c = Character.toUpperCase(c);
                }
                outBuf.append(c);
            }
            this.m_setterString = outBuf.toString();
        }
        return this.m_setterString;
    }
    
    int getType() {
        return this.m_type;
    }
    
    AVT processAVT(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value) throws SAXException {
        try {
            final AVT avt = new AVT(handler, uri, name, rawName, value);
            return avt;
        }
        catch (TransformerException te) {
            throw new SAXException(te);
        }
    }
    
    Object processCDATA(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value) {
        return value;
    }
    
    Object processCHAR(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value) throws SAXException {
        if (value.length() != 1) {
            handler.error("An XSLT attribute of type T_CHAR must be only 1 character!", null);
        }
        return new Character(value.charAt(0));
    }
    
    Object processENUM(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value) {
        final int enum1 = this.getEnum(value);
        return new Integer(enum1);
    }
    
    Object processEXPR(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value) throws SAXException {
        try {
            final XPath expr = handler.createXPath(value);
            return expr;
        }
        catch (TransformerException te) {
            final SAXException se = new SAXException(te);
            throw new SAXException(te);
        }
    }
    
    Object processNMTOKEN(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value) {
        return value;
    }
    
    Object processPATTERN(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value) throws SAXException {
        try {
            final XPath pattern = handler.createMatchPatternXPath(value);
            return pattern;
        }
        catch (TransformerException te) {
            throw new SAXException(te);
        }
    }
    
    StringVector processPREFIX_URLLIST(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value) throws SAXException {
        final StringTokenizer tokenizer = new StringTokenizer(value, " \t\n\r\f");
        final int nStrings = tokenizer.countTokens();
        final StringVector strings = new StringVector(nStrings);
        for (int i = 0; i < nStrings; ++i) {
            final String prefix = tokenizer.nextToken();
            final String url = handler.getNamespaceForPrefix(prefix);
            strings.addElement(url);
        }
        return strings;
    }
    
    Object processPRIORITY(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value) throws SAXException {
        try {
            return Double.valueOf(value);
        }
        catch (NumberFormatException nfe) {
            handler.error("Priority value does not contain a parsable number.", nfe);
            return new Double(0.0);
        }
    }
    
    Object processQNAME(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value) throws SAXException {
        return new QName(value, handler);
    }
    
    Vector processQNAMES(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value) throws SAXException {
        final StringTokenizer tokenizer = new StringTokenizer(value, " \t\n\r\f");
        final int nQNames = tokenizer.countTokens();
        final Vector qnames = new Vector(nQNames);
        for (int i = 0; i < nQNames; ++i) {
            qnames.addElement(new QName(tokenizer.nextToken()));
        }
        return qnames;
    }
    
    Vector processSIMPLEPATTERNLIST(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value) throws SAXException {
        try {
            final StringTokenizer tokenizer = new StringTokenizer(value, " \t\n\r\f");
            final int nPatterns = tokenizer.countTokens();
            final Vector patterns = new Vector(nPatterns);
            for (int i = 0; i < nPatterns; ++i) {
                final XPath pattern = handler.createMatchPatternXPath(tokenizer.nextToken());
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
    
    String processURL(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value) throws SAXException {
        return value;
    }
    
    Object processValue(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value) throws SAXException {
        final int type = this.getType();
        Object processedValue = null;
        switch (type) {
            case 3: {
                processedValue = this.processAVT(handler, uri, name, rawName, value);
                break;
            }
            case 1: {
                processedValue = this.processCDATA(handler, uri, name, rawName, value);
                break;
            }
            case 6: {
                processedValue = this.processCHAR(handler, uri, name, rawName, value);
                break;
            }
            case 11: {
                processedValue = this.processENUM(handler, uri, name, rawName, value);
                break;
            }
            case 5: {
                processedValue = this.processEXPR(handler, uri, name, rawName, value);
                break;
            }
            case 13: {
                processedValue = this.processNMTOKEN(handler, uri, name, rawName, value);
                break;
            }
            case 4: {
                processedValue = this.processPATTERN(handler, uri, name, rawName, value);
                break;
            }
            case 7: {
                processedValue = this.processPRIORITY(handler, uri, name, rawName, value);
                break;
            }
            case 9: {
                processedValue = this.processQNAME(handler, uri, name, rawName, value);
                break;
            }
            case 10: {
                processedValue = this.processQNAMES(handler, uri, name, rawName, value);
                break;
            }
            case 12: {
                processedValue = this.processSIMPLEPATTERNLIST(handler, uri, name, rawName, value);
                break;
            }
            case 2: {
                processedValue = this.processURL(handler, uri, name, rawName, value);
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
        }
        return processedValue;
    }
    
    private Boolean processYESNO(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String value) throws SAXException {
        if (!value.equals("yes") && !value.equals("no")) {
            handler.error("Value for " + name + " should equal 'yes' or 'no'", null);
        }
        return new Boolean(value.equals("yes"));
    }
    
    void setAttrValue(final StylesheetHandler handler, String attrUri, final String attrLocalName, final String attrRawName, final String attrValue, final Object elem) throws SAXException {
        if (attrRawName.equals("xmlns") || attrRawName.startsWith("xmlns:")) {
            return;
        }
        final String setterString = this.getSetterMethodName();
        if (setterString != null) {
            try {
                Method meth;
                Object[] args;
                if (setterString.equals(XSLTAttributeDef.S_FOREIGNATTR_SETTER)) {
                    if (attrUri == null) {
                        attrUri = "";
                    }
                    final Class sclass = attrUri.getClass();
                    final Class[] argTypes = { sclass, sclass, sclass, sclass };
                    meth = elem.getClass().getMethod(setterString, (Class<?>[])argTypes);
                    args = new Object[] { attrUri, attrLocalName, attrRawName, attrValue };
                }
                else {
                    final Object value = this.processValue(handler, attrUri, attrLocalName, attrRawName, attrValue);
                    final Class[] argTypes = { this.getPrimativeClass(value) };
                    try {
                        meth = elem.getClass().getMethod(setterString, (Class<?>[])argTypes);
                    }
                    catch (NoSuchMethodException ex) {
                        final Class cl = value.getClass();
                        argTypes[0] = cl;
                        meth = elem.getClass().getMethod(setterString, (Class<?>[])argTypes);
                    }
                    args = new Object[] { value };
                }
                meth.invoke(elem, args);
            }
            catch (NoSuchMethodException nsme) {
                if (!setterString.equals(XSLTAttributeDef.S_FOREIGNATTR_SETTER)) {
                    handler.error("Failed calling " + setterString + " method!", nsme);
                }
            }
            catch (IllegalAccessException iae) {
                handler.error("Failed calling " + setterString + " method!", iae);
            }
            catch (InvocationTargetException nsme2) {
                handler.error("Failed calling " + setterString + " method!", nsme2);
            }
        }
    }
    
    void setDefAttrValue(final StylesheetHandler handler, final Object elem) throws SAXException {
        this.setAttrValue(handler, this.getNamespace(), this.getName(), this.getName(), this.getDefault(), elem);
    }
    
    void setDefault(final String def) {
        this.m_default = def;
    }
}
