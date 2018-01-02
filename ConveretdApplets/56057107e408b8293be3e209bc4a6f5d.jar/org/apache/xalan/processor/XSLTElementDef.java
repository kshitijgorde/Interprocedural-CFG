// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.apache.xml.utils.QName;

public class XSLTElementDef
{
    static final int T_ELEMENT = 1;
    static final int T_PCDATA = 2;
    static final int T_ANY = 3;
    private int m_type;
    private String m_namespace;
    private String m_name;
    private String m_nameAlias;
    private XSLTElementDef[] m_elements;
    private XSLTAttributeDef[] m_attributes;
    private XSLTElementProcessor m_elementProcessor;
    private Class m_classObject;
    
    XSLTElementDef() {
        this.m_type = 1;
    }
    
    XSLTElementDef(final Class classObject, final XSLTElementProcessor contentHandler, final int type) {
        this.m_type = 1;
        this.m_classObject = classObject;
        this.m_type = type;
        this.setElementProcessor(contentHandler);
    }
    
    XSLTElementDef(final XSLTSchema schema, final String namespace, final String name, final String nameAlias, final XSLTElementDef[] elements, final XSLTAttributeDef[] attributes, final XSLTElementProcessor contentHandler, final Class classObject) {
        this.m_type = 1;
        this.build(namespace, name, nameAlias, elements, attributes, contentHandler, classObject);
        if (namespace != null && (namespace.equals("http://www.w3.org/1999/XSL/Transform") || namespace.equals("http://xml.apache.org/xslt"))) {
            schema.addAvailableElement(new QName(namespace, name));
            if (nameAlias != null) {
                schema.addAvailableElement(new QName(namespace, nameAlias));
            }
        }
    }
    
    private boolean QNameEquals(final String uri, final String localName) {
        return equalsMayBeNullOrZeroLen(this.m_namespace, uri) && (equalsMayBeNullOrZeroLen(this.m_name, localName) || equalsMayBeNullOrZeroLen(this.m_nameAlias, localName));
    }
    
    void build(final String namespace, final String name, final String nameAlias, final XSLTElementDef[] elements, final XSLTAttributeDef[] attributes, final XSLTElementProcessor contentHandler, final Class classObject) {
        this.m_namespace = namespace;
        this.m_name = name;
        this.m_nameAlias = nameAlias;
        this.m_elements = elements;
        this.m_attributes = attributes;
        this.setElementProcessor(contentHandler);
        this.m_classObject = classObject;
    }
    
    private static boolean equalsMayBeNull(final Object obj1, final Object obj2) {
        return obj2 == obj1 || (obj1 != null && obj2 != null && obj2.equals(obj1));
    }
    
    private static boolean equalsMayBeNullOrZeroLen(final String s1, final String s2) {
        final int len1 = (s1 == null) ? 0 : s1.length();
        final int len2 = (s2 == null) ? 0 : s2.length();
        return len1 == len2 && (len1 == 0 || s1.equals(s2));
    }
    
    XSLTAttributeDef getAttributeDef(final String uri, final String localName) {
        XSLTAttributeDef defaultDef = null;
        for (final XSLTAttributeDef attrDef : this.getAttributes()) {
            final String uriDef = attrDef.getNamespace();
            final String nameDef = attrDef.getName();
            if (nameDef.equals("*") && (equalsMayBeNullOrZeroLen(uri, uriDef) || (uriDef != null && uri.length() > 0 && uriDef.equals("*")))) {
                return attrDef;
            }
            if (nameDef.equals("*") && uriDef == null) {
                defaultDef = attrDef;
            }
            else if (equalsMayBeNullOrZeroLen(uri, uriDef) && localName.equals(nameDef)) {
                return attrDef;
            }
        }
        if (defaultDef == null && uri.length() > 0 && !equalsMayBeNullOrZeroLen(uri, "http://www.w3.org/1999/XSL/Transform")) {
            return XSLTAttributeDef.m_foreignAttr;
        }
        return defaultDef;
    }
    
    XSLTAttributeDef[] getAttributes() {
        return this.m_attributes;
    }
    
    Class getClassObject() {
        return this.m_classObject;
    }
    
    XSLTElementProcessor getElementProcessor() {
        return this.m_elementProcessor;
    }
    
    XSLTElementDef[] getElements() {
        return this.m_elements;
    }
    
    String getName() {
        return this.m_name;
    }
    
    String getNameAlias() {
        return this.m_nameAlias;
    }
    
    String getNamespace() {
        return this.m_namespace;
    }
    
    XSLTElementProcessor getProcessorFor(final String uri, final String localName) {
        XSLTElementProcessor lreDef = null;
        if (this.m_elements == null) {
            return null;
        }
        for (int n = this.m_elements.length, i = 0; i < n; ++i) {
            final XSLTElementDef def = this.m_elements[i];
            if (def.m_name.equals("*")) {
                if (!equalsMayBeNullOrZeroLen(uri, "http://www.w3.org/1999/XSL/Transform")) {
                    lreDef = def.m_elementProcessor;
                }
            }
            else if (def.QNameEquals(uri, localName)) {
                return def.m_elementProcessor;
            }
        }
        return lreDef;
    }
    
    XSLTElementProcessor getProcessorForUnknown(final String uri, final String localName) {
        if (this.m_elements == null) {
            return null;
        }
        for (int n = this.m_elements.length, i = 0; i < n; ++i) {
            final XSLTElementDef def = this.m_elements[i];
            if (def.m_name.equals("unknown") && uri.length() > 0) {
                return def.m_elementProcessor;
            }
        }
        return null;
    }
    
    int getType() {
        return this.m_type;
    }
    
    void setElementProcessor(final XSLTElementProcessor handler) {
        if (handler != null) {
            (this.m_elementProcessor = handler).setElemDef(this);
        }
    }
    
    void setElements(final XSLTElementDef[] defs) {
        this.m_elements = defs;
    }
    
    void setType(final int t) {
        this.m_type = t;
    }
}
