// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import org.w3c.dom.Node;
import org.w3c.dom.Attr;
import java.io.IOException;
import java.io.Writer;
import java.io.CharArrayWriter;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.DOMException;
import org.apache.crimson.util.XmlNames;

public class ElementNode extends NamespacedNode implements ElementEx
{
    private AttributeSet attributes;
    private String idAttributeName;
    private Object userObject;
    private static final char[] tagStart;
    private static final char[] tagEnd;
    
    public ElementNode() {
    }
    
    public ElementNode(final String name) {
        super.name = name;
    }
    
    public ElementNode(final String namespaceURI, final String qName) throws DomEx {
        this.checkArguments(namespaceURI, qName);
        super.name = qName;
        super.namespaceURI = namespaceURI;
    }
    
    private void checkArguments(final String namespaceURI, final String qualifiedName) throws DomEx {
        if (qualifiedName == null) {
            throw new DomEx((short)14);
        }
        final int first = qualifiedName.indexOf(58);
        if (first <= 0) {
            if (!XmlNames.isUnqualifiedName(qualifiedName)) {
                throw new DomEx((short)5);
            }
        }
        else {
            final int last = qualifiedName.lastIndexOf(58);
            if (last != first) {
                throw new DomEx((short)14);
            }
            final String prefix = qualifiedName.substring(0, first);
            final String localName = qualifiedName.substring(first + 1);
            if (!XmlNames.isUnqualifiedName(prefix) || !XmlNames.isUnqualifiedName(localName)) {
                throw new DomEx((short)5);
            }
            if (namespaceURI == null || namespaceURI.equals("") || (prefix.equals("xml") && !"http://www.w3.org/XML/1998/namespace".equals(namespaceURI))) {
                throw new DomEx((short)14);
            }
        }
    }
    
    public void trimToSize() {
        super.trimToSize();
        if (this.attributes != null) {
            this.attributes.trimToSize();
        }
    }
    
    protected void setTag(final String t) {
        super.name = t;
    }
    
    void setAttributes(final AttributeSet a) {
        final AttributeSet oldAtts = this.attributes;
        if (oldAtts != null && oldAtts.isReadonly()) {
            throw new DomEx((short)7);
        }
        if (a != null) {
            a.setOwnerElement(this);
        }
        this.attributes = a;
        if (oldAtts != null) {
            oldAtts.setOwnerElement(null);
        }
    }
    
    void checkChildType(final int type) throws DOMException {
        switch (type) {
            case 1:
            case 3:
            case 4:
            case 5:
            case 7:
            case 8: {}
            default: {
                throw new DomEx((short)3);
            }
        }
    }
    
    public void setReadonly(final boolean deep) {
        if (this.attributes != null) {
            this.attributes.setReadonly();
        }
        super.setReadonly(deep);
    }
    
    public NamedNodeMap getAttributes() {
        if (this.attributes == null) {
            this.attributes = new AttributeSet(this);
        }
        return this.attributes;
    }
    
    public boolean hasAttributes() {
        return this.attributes != null;
    }
    
    public String toString() {
        try {
            final CharArrayWriter out = new CharArrayWriter();
            final XmlWriteContext x = new XmlWriteContext(out);
            this.writeXml(x);
            return out.toString();
        }
        catch (Exception e) {
            return super.toString();
        }
    }
    
    public void writeXml(final XmlWriteContext context) throws IOException {
        final Writer out = context.getWriter();
        if (super.name == null) {
            throw new IllegalStateException(this.getMessage("EN-002"));
        }
        out.write(ElementNode.tagStart, 0, 1);
        out.write(super.name);
        if (this.attributes != null) {
            this.attributes.writeXml(context);
        }
        if (!this.hasChildNodes()) {
            out.write(ElementNode.tagEnd, 0, 3);
        }
        else {
            out.write(ElementNode.tagEnd, 2, 1);
            this.writeChildrenXml(context);
            out.write(ElementNode.tagStart, 0, 2);
            out.write(super.name);
            out.write(ElementNode.tagEnd, 2, 1);
        }
    }
    
    public void setIdAttributeName(final String attName) {
        if (super.readonly) {
            throw new DomEx((short)7);
        }
        this.idAttributeName = attName;
    }
    
    public String getIdAttributeName() {
        return this.idAttributeName;
    }
    
    public void setUserObject(final Object userObject) {
        this.userObject = userObject;
    }
    
    public Object getUserObject() {
        return this.userObject;
    }
    
    public short getNodeType() {
        return 1;
    }
    
    public String getTagName() {
        return super.name;
    }
    
    public String getNodeName() {
        return super.name;
    }
    
    public boolean hasAttribute(final String name) {
        return this.getAttributeNode(name) != null;
    }
    
    public boolean hasAttributeNS(final String namespaceURI, final String localName) {
        return this.getAttributeNodeNS(namespaceURI, localName) != null;
    }
    
    public String getAttribute(final String name) {
        return (this.attributes == null) ? "" : this.attributes.getValue(name);
    }
    
    public String getAttributeNS(final String namespaceURI, final String localName) {
        if (this.attributes == null) {
            return "";
        }
        final Attr attr = this.getAttributeNodeNS(namespaceURI, localName);
        if (attr == null) {
            return "";
        }
        return attr.getValue();
    }
    
    public Attr getAttributeNodeNS(final String namespaceURI, final String localName) {
        if (localName == null) {
            return null;
        }
        if (this.attributes == null) {
            return null;
        }
        int i = 0;
        while (true) {
            final AttributeNode attr = (AttributeNode)this.attributes.item(i);
            if (attr == null) {
                return null;
            }
            if (attr.getLocalName().equals(localName) && attr.getNamespaceURI().equals(namespaceURI)) {
                return attr;
            }
            ++i;
        }
    }
    
    public void setAttribute(final String name, final String value) throws DOMException {
        if (super.readonly) {
            throw new DomEx((short)7);
        }
        if (this.attributes == null) {
            this.attributes = new AttributeSet(this);
        }
        AttributeNode att;
        if ((att = (AttributeNode)this.attributes.getNamedItem(name)) != null) {
            att.setNodeValue(value);
        }
        else {
            att = new AttributeNode(name, value, true, null);
            att.setOwnerDocument((XmlDocument)this.getOwnerDocument());
            this.attributes.setNamedItem(att);
        }
    }
    
    public void setAttributeNS(final String namespaceURI, final String qualifiedName, final String value) throws DOMException {
        AttributeNode.checkArguments(namespaceURI, qualifiedName);
        final String prefix = XmlNames.getPrefix(qualifiedName);
        final Attr attr = this.getAttributeNodeNS(namespaceURI, XmlNames.getLocalPart(qualifiedName));
        if (attr == null) {
            final AttributeNode newAttr = new AttributeNode(namespaceURI, qualifiedName, value, true, null);
            newAttr.setOwnerDocument((XmlDocument)this.getOwnerDocument());
            this.setAttributeNodeNS(newAttr);
        }
        else {
            attr.setValue(value);
            attr.setPrefix(prefix);
        }
    }
    
    public Attr setAttributeNodeNS(final Attr newAttr) throws DOMException {
        if (super.readonly) {
            throw new DomEx((short)7);
        }
        if (newAttr.getOwnerDocument() != this.getOwnerDocument()) {
            throw new DomEx((short)4);
        }
        if (this.attributes == null) {
            this.attributes = new AttributeSet(this);
        }
        return (Attr)this.attributes.setNamedItemNS(newAttr);
    }
    
    public void removeAttribute(final String name) throws DOMException {
        if (super.readonly) {
            throw new DomEx((short)7);
        }
        if (this.attributes == null) {
            throw new DomEx((short)8);
        }
        this.attributes.removeNamedItem(name);
    }
    
    public void removeAttributeNS(final String namespaceURI, final String localName) throws DOMException {
        if (super.readonly) {
            throw new DomEx((short)7);
        }
        this.attributes.removeNamedItemNS(namespaceURI, localName);
    }
    
    public Attr getAttributeNode(final String name) {
        if (this.attributes != null) {
            return (Attr)this.attributes.getNamedItem(name);
        }
        return null;
    }
    
    public Attr setAttributeNode(final Attr newAttr) throws DOMException {
        if (super.readonly) {
            throw new DomEx((short)7);
        }
        if (!(newAttr instanceof AttributeNode)) {
            throw new DomEx((short)4);
        }
        if (this.attributes == null) {
            this.attributes = new AttributeSet(this);
        }
        return (Attr)this.attributes.setNamedItem(newAttr);
    }
    
    public Attr removeAttributeNode(final Attr oldAttr) throws DOMException {
        if (this.isReadonly()) {
            throw new DomEx((short)7);
        }
        final Attr attr = this.getAttributeNode(oldAttr.getNodeName());
        if (attr == null) {
            throw new DomEx((short)8);
        }
        this.removeAttribute(attr.getNodeName());
        return attr;
    }
    
    public Node cloneNode(final boolean deep) {
        try {
            final ElementNode elementNode = (ElementNode)this.getOwnerDocument().createElement(super.name);
            if (this.attributes != null) {
                elementNode.setAttributes(new AttributeSet(this.attributes, true));
            }
            if (deep) {
                int i = 0;
                while (true) {
                    final Node node = this.item(i);
                    if (node == null) {
                        break;
                    }
                    elementNode.appendChild(node.cloneNode(true));
                    ++i;
                }
            }
            return elementNode;
        }
        catch (DOMException e) {
            throw new RuntimeException(this.getMessage("EN-001"));
        }
    }
    
    public void write(final Writer out) throws IOException {
        this.writeXml(new XmlWriteContext(out));
    }
    
    static {
        tagStart = new char[] { '<', '/' };
        tagEnd = new char[] { ' ', '/', '>' };
    }
}
