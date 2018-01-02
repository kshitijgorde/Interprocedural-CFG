// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.DOMException;
import org.apache.xml.utils.QName;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.Attributes;

public class ElementImpl extends Parent implements Attributes, NamedNodeMap
{
    private String m_name;
    private short m_attrsEnd;
    private AttrImpl m_firstAttr;
    private AttrImpl m_lastAttr;
    
    ElementImpl(final DocumentImpl doc, final String name) {
        super(doc);
        this.m_attrsEnd = 0;
        this.m_name = name;
    }
    
    ElementImpl(final DocumentImpl doc, final String name, final Attributes atts) {
        super(doc);
        this.m_attrsEnd = 0;
        this.m_name = name;
        this.setAttributes(atts);
    }
    
    public Attr createAttribute(final String name) throws DOMException {
        AttrImpl attrImpl;
        if (QName.isXMLNSDecl(name)) {
            attrImpl = new NameSpaceDecl(this.getDocumentImpl(), "http://www.w3.org/2000/xmlns/", name, "");
        }
        else {
            attrImpl = new AttrImpl(this.getDocumentImpl(), name, "");
        }
        boolean found = false;
        for (AttrImpl attr = this.m_firstAttr; attr != null; attr = (AttrImpl)attr.m_next) {
            if (attr.getNodeName().equals(name)) {
                if (attr.m_prev != null) {
                    attr.m_prev.m_next = attr.m_next;
                }
                if (super.m_next != null) {
                    attr.m_next.m_prev = attr.m_prev;
                }
                attr.m_next = null;
                attr.m_prev = null;
                found = true;
                break;
            }
        }
        if (!found) {
            if (this.m_firstAttr == null) {
                this.m_firstAttr = attrImpl;
            }
            else {
                this.m_lastAttr.m_next = attrImpl;
                attrImpl.m_prev = this.m_lastAttr;
            }
            this.m_lastAttr = attrImpl;
            super.m_doc.incrementDocOrderCount();
            attrImpl.setUid(super.m_doc.getDocOrderCount());
            attrImpl.setParent(this);
            attrImpl.setLevel((short)(this.getLevel() + 1));
            ++this.m_attrsEnd;
        }
        return attrImpl;
    }
    
    public Attr createAttributeNS(final String namespaceURI, final String qualifiedName) throws DOMException {
        final AttrImplNS attrImpl = new AttrImplNS(this.getDocumentImpl(), namespaceURI, qualifiedName, "");
        boolean found = false;
        for (AttrImpl attr = this.m_firstAttr; attr != null; attr = (AttrImpl)attr.m_next) {
            String attrURI = attr.getNamespaceURI();
            if (attrURI == null) {
                attrURI = "";
            }
            if (attr.getLocalName().equals(attrImpl.getLocalName()) && attrURI.equals(attrImpl.getNamespaceURI())) {
                if (attr.m_prev != null) {
                    attr.m_prev.m_next = attr.m_next;
                }
                if (super.m_next != null) {
                    attr.m_next.m_prev = attr.m_prev;
                }
                attr.m_next = null;
                attr.m_prev = null;
                found = true;
                break;
            }
        }
        if (!found) {
            if (this.m_firstAttr == null) {
                this.m_firstAttr = attrImpl;
            }
            else {
                this.m_lastAttr.m_next = attrImpl;
                attrImpl.m_prev = this.m_lastAttr;
            }
            this.m_lastAttr = attrImpl;
            super.m_doc.incrementDocOrderCount();
            attrImpl.setUid(super.m_doc.getDocOrderCount());
            attrImpl.setParent(this);
            attrImpl.setLevel((short)(this.getLevel() + 1));
            ++this.m_attrsEnd;
        }
        return attrImpl;
    }
    
    public int getAttrCount() {
        synchronized (super.m_doc) {
            // monitorexit(super.m_doc)
            return this.m_attrsEnd;
        }
    }
    
    public String getAttribute(final String name) {
        return this.getValue(name);
    }
    
    public NamedNodeMap getAttributes() {
        return this;
    }
    
    public AttrImpl getChildAttribute(final int i) throws ArrayIndexOutOfBoundsException, NullPointerException {
        synchronized (super.m_doc) {
            if (this.m_firstAttr != null) {
                Child next = this.m_firstAttr;
                for (int k = 0; k < i; ++k) {
                    if (next == null) {
                        // monitorexit(super.m_doc)
                        return null;
                    }
                    next = next.m_next;
                }
                // monitorexit(super.m_doc)
                return (AttrImpl)next;
            }
            // monitorexit(super.m_doc)
            return null;
        }
    }
    
    public int getChildCount() {
        if (!this.isComplete()) {
            synchronized (super.m_doc) {
                try {
                    while (!this.isComplete()) {
                        super.m_doc.wait(100L);
                        this.throwIfParseError();
                    }
                }
                catch (InterruptedException ex) {
                    this.throwIfParseError();
                }
            }
            // monitorexit(super.m_doc)
        }
        return super.m_childCount;
    }
    
    public int getIndex(final String rawName) {
        for (int i = 0; i < this.getAttrCount(); ++i) {
            final AttrImpl attr = this.getChildAttribute(i);
            if (attr.getNodeName().equals(rawName)) {
                return i;
            }
        }
        return -1;
    }
    
    public int getIndex(final String uri, final String localPart) {
        for (int i = 0; i < this.getAttrCount(); ++i) {
            final AttrImpl attr = this.getChildAttribute(i);
            if (attr.getLocalName().equals(localPart) && attr.getNamespaceURI().equals(uri)) {
                return i;
            }
        }
        return -1;
    }
    
    public int getLength() {
        return this.getAttrCount();
    }
    
    public String getLocalName() {
        return this.m_name;
    }
    
    public String getLocalName(final int index) {
        final AttrImpl attr = this.getChildAttribute(index);
        if (attr != null) {
            return attr.getLocalName();
        }
        return null;
    }
    
    public Node getNamedItem(final String name) {
        return this.getChildAttribute(this.getIndex(name));
    }
    
    public Node getNamedItemNS(final String namespaceURI, final String localName) {
        return this.getChildAttribute(this.getIndex(namespaceURI, localName));
    }
    
    public String getNodeName() {
        return this.m_name;
    }
    
    public short getNodeType() {
        return 1;
    }
    
    public String getQName(final int index) {
        final AttrImpl attr = this.getChildAttribute(index);
        if (attr != null) {
            return attr.getNodeName();
        }
        return null;
    }
    
    public String getTagName() {
        return this.m_name;
    }
    
    public String getType(final int index) {
        final AttrImpl attr = this.getChildAttribute(index);
        if (attr != null) {
            return Integer.toString(attr.getNodeType());
        }
        return null;
    }
    
    public String getType(final String rawName) {
        for (int i = 0; i < this.getAttrCount(); ++i) {
            final AttrImpl attr = this.getChildAttribute(i);
            if (attr.getNodeName().equals(rawName)) {
                return Integer.toString(attr.getNodeType());
            }
        }
        return null;
    }
    
    public String getType(final String uri, final String localName) {
        for (int i = 0; i < this.getAttrCount(); ++i) {
            final AttrImpl attr = this.getChildAttribute(i);
            if (attr.getLocalName().equals(localName) && attr.getNamespaceURI().equals(uri)) {
                return Integer.toString(attr.getNodeType());
            }
        }
        return null;
    }
    
    public String getURI(final int index) {
        final AttrImpl attr = this.getChildAttribute(index);
        if (attr != null) {
            return attr.getNamespaceURI();
        }
        return null;
    }
    
    public String getValue(final int index) {
        final AttrImpl attr = this.getChildAttribute(index);
        if (attr != null) {
            return attr.getValue();
        }
        return null;
    }
    
    public String getValue(final String rawName) {
        for (int i = 0; i < this.getAttrCount(); ++i) {
            final AttrImpl attr = this.getChildAttribute(i);
            if (attr.getNodeName().equals(rawName)) {
                return attr.getValue();
            }
        }
        return null;
    }
    
    public String getValue(final String uri, final String localName) {
        for (int i = 0; i < this.getAttrCount(); ++i) {
            final AttrImpl attr = this.getChildAttribute(i);
            if (attr.getLocalName().equals(localName) && attr.getNamespaceURI().equals(uri)) {
                return attr.getValue();
            }
        }
        return null;
    }
    
    public Node item(final int index) {
        return this.getChildAttribute(index);
    }
    
    public Node removeItem(final int index) throws DOMException {
        AttrImpl attr = this.m_firstAttr;
        for (int pos = 0; attr != null; attr = (AttrImpl)attr.m_next, ++pos) {
            if (pos == index) {
                if (attr.m_prev != null) {
                    attr.m_prev.m_next = attr.m_next;
                }
                if (super.m_next != null) {
                    attr.m_next.m_prev = attr.m_prev;
                }
                attr.m_next = null;
                attr.m_prev = null;
                return attr;
            }
        }
        return null;
    }
    
    public Node removeNamedItem(final String name) throws DOMException {
        final int index = this.getIndex(name);
        return this.removeItem(index);
    }
    
    public Node removeNamedItemNS(final String namespaceURI, final String localName) throws DOMException {
        final int index = this.getIndex(namespaceURI, localName);
        return this.removeItem(index);
    }
    
    public void setAttribute(final String name, final String value) throws DOMException {
        final AttrImpl attr = (AttrImpl)this.createAttribute(name);
        attr.setValue(value);
    }
    
    public void setAttributeNS(final String namespaceURI, final String qualifiedName, final String value) throws DOMException {
        final AttrImplNS attr = (AttrImplNS)this.createAttributeNS(namespaceURI, qualifiedName);
        attr.setValue(value);
    }
    
    public void setAttributes(final Attributes atts) throws DOMException {
        for (int i = 0; i < atts.getLength(); ++i) {
            final String uri = atts.getURI(i);
            final String name = atts.getQName(i);
            AttrImpl attr;
            if (uri != null || name.indexOf(58) > 0) {
                attr = (AttrImplNS)this.createAttributeNS(uri, name);
            }
            else {
                attr = (AttrImpl)this.createAttribute(name);
            }
            attr.setValue(atts.getValue(i));
        }
    }
    
    public void setIDAttribute(final String value) {
        this.getDocumentImpl().setIDAttribute(value, this);
    }
    
    public Node setNamedItem(final Node arg) throws DOMException {
        this.setAttribute(((Attr)arg).getName(), ((Attr)arg).getValue());
        return this.getChildAttribute(this.getIndex(((Attr)arg).getName()));
    }
    
    public Node setNamedItemNS(final Node arg) throws DOMException {
        this.setAttributeNS(((Attr)arg).getNamespaceURI(), ((Attr)arg).getName(), ((Attr)arg).getValue());
        return this.getChildAttribute(this.getIndex(((Attr)arg).getNamespaceURI(), ((Attr)arg).getName()));
    }
}
