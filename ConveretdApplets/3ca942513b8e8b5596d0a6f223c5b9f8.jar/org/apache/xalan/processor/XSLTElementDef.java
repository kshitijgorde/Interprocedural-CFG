// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import java.util.Enumeration;
import org.apache.xml.utils.QName;
import java.util.Hashtable;

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
    private boolean m_has_required;
    private boolean m_required;
    Hashtable m_requiredFound;
    boolean m_isOrdered;
    private int m_order;
    private int m_lastOrder;
    private boolean m_multiAllowed;
    
    XSLTElementDef() {
        this.m_type = 1;
        this.m_has_required = false;
        this.m_required = false;
        this.m_isOrdered = false;
        this.m_order = -1;
        this.m_lastOrder = -1;
        this.m_multiAllowed = true;
    }
    
    XSLTElementDef(final XSLTSchema schema, final String namespace, final String name, final String nameAlias, final XSLTElementDef[] elements, final XSLTAttributeDef[] attributes, final XSLTElementProcessor contentHandler, final Class classObject) {
        this.m_type = 1;
        this.m_has_required = false;
        this.m_required = false;
        this.m_isOrdered = false;
        this.m_order = -1;
        this.m_lastOrder = -1;
        this.m_multiAllowed = true;
        this.build(namespace, name, nameAlias, elements, attributes, contentHandler, classObject);
        if (null != namespace && (namespace.equals("http://www.w3.org/1999/XSL/Transform") || namespace.equals("http://xml.apache.org/xalan") || namespace.equals("http://xml.apache.org/xslt"))) {
            schema.addAvailableElement(new QName(namespace, name));
            if (null != nameAlias) {
                schema.addAvailableElement(new QName(namespace, nameAlias));
            }
        }
    }
    
    XSLTElementDef(final XSLTSchema schema, final String namespace, final String name, final String nameAlias, final XSLTElementDef[] elements, final XSLTAttributeDef[] attributes, final XSLTElementProcessor contentHandler, final Class classObject, final boolean has_required) {
        this.m_type = 1;
        this.m_has_required = false;
        this.m_required = false;
        this.m_isOrdered = false;
        this.m_order = -1;
        this.m_lastOrder = -1;
        this.m_multiAllowed = true;
        this.m_has_required = has_required;
        this.build(namespace, name, nameAlias, elements, attributes, contentHandler, classObject);
        if (null != namespace && (namespace.equals("http://www.w3.org/1999/XSL/Transform") || namespace.equals("http://xml.apache.org/xalan") || namespace.equals("http://xml.apache.org/xslt"))) {
            schema.addAvailableElement(new QName(namespace, name));
            if (null != nameAlias) {
                schema.addAvailableElement(new QName(namespace, nameAlias));
            }
        }
    }
    
    XSLTElementDef(final XSLTSchema schema, final String namespace, final String name, final String nameAlias, final XSLTElementDef[] elements, final XSLTAttributeDef[] attributes, final XSLTElementProcessor contentHandler, final Class classObject, final boolean has_required, final boolean required) {
        this(schema, namespace, name, nameAlias, elements, attributes, contentHandler, classObject, has_required);
        this.m_required = required;
    }
    
    XSLTElementDef(final XSLTSchema schema, final String namespace, final String name, final String nameAlias, final XSLTElementDef[] elements, final XSLTAttributeDef[] attributes, final XSLTElementProcessor contentHandler, final Class classObject, final boolean has_required, final boolean required, final int order, final boolean multiAllowed) {
        this(schema, namespace, name, nameAlias, elements, attributes, contentHandler, classObject, has_required, required);
        this.m_order = order;
        this.m_multiAllowed = multiAllowed;
    }
    
    XSLTElementDef(final XSLTSchema schema, final String namespace, final String name, final String nameAlias, final XSLTElementDef[] elements, final XSLTAttributeDef[] attributes, final XSLTElementProcessor contentHandler, final Class classObject, final boolean has_required, final boolean required, final boolean has_order, final int order, final boolean multiAllowed) {
        this(schema, namespace, name, nameAlias, elements, attributes, contentHandler, classObject, has_required, required);
        this.m_order = order;
        this.m_multiAllowed = multiAllowed;
        this.m_isOrdered = has_order;
    }
    
    XSLTElementDef(final XSLTSchema schema, final String namespace, final String name, final String nameAlias, final XSLTElementDef[] elements, final XSLTAttributeDef[] attributes, final XSLTElementProcessor contentHandler, final Class classObject, final boolean has_order, final int order, final boolean multiAllowed) {
        this(schema, namespace, name, nameAlias, elements, attributes, contentHandler, classObject, order, multiAllowed);
        this.m_isOrdered = has_order;
    }
    
    XSLTElementDef(final XSLTSchema schema, final String namespace, final String name, final String nameAlias, final XSLTElementDef[] elements, final XSLTAttributeDef[] attributes, final XSLTElementProcessor contentHandler, final Class classObject, final int order, final boolean multiAllowed) {
        this(schema, namespace, name, nameAlias, elements, attributes, contentHandler, classObject);
        this.m_order = order;
        this.m_multiAllowed = multiAllowed;
    }
    
    XSLTElementDef(final Class classObject, final XSLTElementProcessor contentHandler, final int type) {
        this.m_type = 1;
        this.m_has_required = false;
        this.m_required = false;
        this.m_isOrdered = false;
        this.m_order = -1;
        this.m_lastOrder = -1;
        this.m_multiAllowed = true;
        this.m_classObject = classObject;
        this.m_type = type;
        this.setElementProcessor(contentHandler);
    }
    
    void build(final String namespace, final String name, final String nameAlias, final XSLTElementDef[] elements, final XSLTAttributeDef[] attributes, final XSLTElementProcessor contentHandler, final Class classObject) {
        this.m_namespace = namespace;
        this.m_name = name;
        this.m_nameAlias = nameAlias;
        this.m_elements = elements;
        this.m_attributes = attributes;
        this.setElementProcessor(contentHandler);
        this.m_classObject = classObject;
        if (this.hasRequired() && this.m_elements != null) {
            for (int n = this.m_elements.length, i = 0; i < n; ++i) {
                final XSLTElementDef def = this.m_elements[i];
                if (def != null && def.getRequired()) {
                    if (this.m_requiredFound == null) {
                        this.m_requiredFound = new Hashtable();
                    }
                    this.m_requiredFound.put(def.getName(), "xsl:" + def.getName());
                }
            }
        }
    }
    
    private static boolean equalsMayBeNull(final Object obj1, final Object obj2) {
        return obj2 == obj1 || (null != obj1 && null != obj2 && obj2.equals(obj1));
    }
    
    private static boolean equalsMayBeNullOrZeroLen(final String s1, final String s2) {
        final int len1 = (s1 == null) ? 0 : s1.length();
        final int len2 = (s2 == null) ? 0 : s2.length();
        return len1 == len2 && (len1 == 0 || s1.equals(s2));
    }
    
    int getType() {
        return this.m_type;
    }
    
    void setType(final int t) {
        this.m_type = t;
    }
    
    String getNamespace() {
        return this.m_namespace;
    }
    
    String getName() {
        return this.m_name;
    }
    
    String getNameAlias() {
        return this.m_nameAlias;
    }
    
    public XSLTElementDef[] getElements() {
        return this.m_elements;
    }
    
    void setElements(final XSLTElementDef[] defs) {
        this.m_elements = defs;
    }
    
    private boolean QNameEquals(final String uri, final String localName) {
        return equalsMayBeNullOrZeroLen(this.m_namespace, uri) && (equalsMayBeNullOrZeroLen(this.m_name, localName) || equalsMayBeNullOrZeroLen(this.m_nameAlias, localName));
    }
    
    XSLTElementProcessor getProcessorFor(final String uri, final String localName) {
        XSLTElementProcessor elemDef = null;
        if (null == this.m_elements) {
            return null;
        }
        final int n = this.m_elements.length;
        int order = -1;
        boolean multiAllowed = true;
        for (int i = 0; i < n; ++i) {
            final XSLTElementDef def = this.m_elements[i];
            if (def.m_name.equals("*")) {
                if (!equalsMayBeNullOrZeroLen(uri, "http://www.w3.org/1999/XSL/Transform")) {
                    elemDef = def.m_elementProcessor;
                    order = def.getOrder();
                    multiAllowed = def.getMultiAllowed();
                }
            }
            else if (def.QNameEquals(uri, localName)) {
                if (def.getRequired()) {
                    this.setRequiredFound(def.getName(), true);
                }
                order = def.getOrder();
                multiAllowed = def.getMultiAllowed();
                elemDef = def.m_elementProcessor;
                break;
            }
        }
        if (elemDef != null && this.isOrdered()) {
            final int lastOrder = this.getLastOrder();
            if (order > lastOrder) {
                this.setLastOrder(order);
            }
            else {
                if (order == lastOrder && !multiAllowed) {
                    return null;
                }
                if (order < lastOrder && order > 0) {
                    return null;
                }
            }
        }
        return elemDef;
    }
    
    XSLTElementProcessor getProcessorForUnknown(final String uri, final String localName) {
        if (null == this.m_elements) {
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
    
    XSLTAttributeDef[] getAttributes() {
        return this.m_attributes;
    }
    
    XSLTAttributeDef getAttributeDef(final String uri, final String localName) {
        XSLTAttributeDef defaultDef = null;
        for (final XSLTAttributeDef attrDef : this.getAttributes()) {
            final String uriDef = attrDef.getNamespace();
            final String nameDef = attrDef.getName();
            if (nameDef.equals("*") && (equalsMayBeNullOrZeroLen(uri, uriDef) || (uriDef != null && uriDef.equals("*") && uri != null && uri.length() > 0))) {
                return attrDef;
            }
            if (nameDef.equals("*") && uriDef == null) {
                defaultDef = attrDef;
            }
            else if (equalsMayBeNullOrZeroLen(uri, uriDef) && localName.equals(nameDef)) {
                return attrDef;
            }
        }
        if (null == defaultDef && uri.length() > 0 && !equalsMayBeNullOrZeroLen(uri, "http://www.w3.org/1999/XSL/Transform")) {
            return XSLTAttributeDef.m_foreignAttr;
        }
        return defaultDef;
    }
    
    public XSLTElementProcessor getElementProcessor() {
        return this.m_elementProcessor;
    }
    
    public void setElementProcessor(final XSLTElementProcessor handler) {
        if (handler != null) {
            (this.m_elementProcessor = handler).setElemDef(this);
        }
    }
    
    Class getClassObject() {
        return this.m_classObject;
    }
    
    boolean hasRequired() {
        return this.m_has_required;
    }
    
    boolean getRequired() {
        return this.m_required;
    }
    
    void setRequiredFound(final String elem, final boolean found) {
        if (this.m_requiredFound.get(elem) != null) {
            this.m_requiredFound.remove(elem);
        }
    }
    
    boolean getRequiredFound() {
        return this.m_requiredFound == null || this.m_requiredFound.isEmpty();
    }
    
    String getRequiredElem() {
        if (this.m_requiredFound == null) {
            return null;
        }
        final Enumeration elems = this.m_requiredFound.elements();
        String s = "";
        boolean first = true;
        while (elems.hasMoreElements()) {
            if (first) {
                first = false;
            }
            else {
                s += ", ";
            }
            s += elems.nextElement();
        }
        return s;
    }
    
    boolean isOrdered() {
        return this.m_isOrdered;
    }
    
    int getOrder() {
        return this.m_order;
    }
    
    int getLastOrder() {
        return this.m_lastOrder;
    }
    
    void setLastOrder(final int order) {
        this.m_lastOrder = order;
    }
    
    boolean getMultiAllowed() {
        return this.m_multiAllowed;
    }
}
