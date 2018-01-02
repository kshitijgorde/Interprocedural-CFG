// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Writer;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.apache.crimson.util.XmlNames;
import org.apache.crimson.parser.AttributesEx;
import org.xml.sax.Attributes;
import org.w3c.dom.Node;
import java.util.Vector;
import org.w3c.dom.NamedNodeMap;

final class AttributeSet implements NamedNodeMap, XmlWritable
{
    private boolean readonly;
    private Vector list;
    private ElementNode ownerElement;
    
    AttributeSet(final ElementNode ownerElement) {
        this.list = new Vector(5);
        this.ownerElement = ownerElement;
    }
    
    AttributeSet(final AttributeSet original, final boolean deep) {
        final int size = original.getLength();
        this.list = new Vector(size);
        for (int i = 0; i < size; ++i) {
            Node node = original.item(i);
            if (!(node instanceof AttributeNode)) {
                throw new IllegalArgumentException(((NodeBase)node).getMessage("A-003"));
            }
            final AttributeNode attr = (AttributeNode)node;
            node = attr.cloneAttributeNode(deep);
            this.list.addElement(node);
        }
    }
    
    AttributeSet(final Attributes source) throws DOMException {
        final int len = source.getLength();
        AttributesEx ex = null;
        this.list = new Vector(len);
        if (source instanceof AttributesEx) {
            ex = (AttributesEx)source;
        }
        for (int i = 0; i < len; ++i) {
            final String qName = source.getQName(i);
            String uri;
            if ("xmlns".equals(qName) || "xmlns".equals(XmlNames.getPrefix(qName))) {
                uri = "http://www.w3.org/2000/xmlns/";
            }
            else {
                uri = source.getURI(i);
                if (uri.equals("")) {
                    uri = null;
                }
            }
            final AttributeNode attrNode = new AttributeNode(uri, qName, source.getValue(i), ex == null || ex.isSpecified(i), (ex == null) ? null : ex.getDefault(i));
            this.list.addElement(attrNode);
        }
        this.list.trimToSize();
    }
    
    void trimToSize() {
        this.list.trimToSize();
    }
    
    public void setReadonly() {
        this.readonly = true;
        for (int i = 0; i < this.list.size(); ++i) {
            this.list.elementAt(i).setReadonly(true);
        }
    }
    
    public boolean isReadonly() {
        if (this.readonly) {
            return true;
        }
        for (int i = 0; i < this.list.size(); ++i) {
            if (this.list.elementAt(i).isReadonly()) {
                return true;
            }
        }
        return false;
    }
    
    void setOwnerElement(final ElementNode e) {
        if (e != null && this.ownerElement != null) {
            throw new IllegalStateException(e.getMessage("A-004"));
        }
        this.ownerElement = e;
        for (int length = this.list.size(), i = 0; i < length; ++i) {
            final AttributeNode node = this.list.elementAt(i);
            node.setOwnerElement(null);
            node.setOwnerElement(e);
        }
    }
    
    String getValue(final String name) {
        final Attr attr = (Attr)this.getNamedItem(name);
        if (attr == null) {
            return "";
        }
        return attr.getValue();
    }
    
    public Node getNamedItem(final String name) {
        for (int length = this.list.size(), i = 0; i < length; ++i) {
            final Node value = this.item(i);
            if (value.getNodeName().equals(name)) {
                return value;
            }
        }
        return null;
    }
    
    public Node getNamedItemNS(final String namespaceURI, final String localName) {
        for (int i = 0; i < this.list.size(); ++i) {
            final Node value = this.item(i);
            final String iLocalName = value.getLocalName();
            if (iLocalName != null && iLocalName.equals(localName)) {
                final String iNamespaceURI = value.getNamespaceURI();
                if (iNamespaceURI != null && iNamespaceURI.equals(namespaceURI)) {
                    return value;
                }
            }
        }
        return null;
    }
    
    public int getLength() {
        return this.list.size();
    }
    
    public Node item(final int index) {
        if (index < 0 || index >= this.list.size()) {
            return null;
        }
        return this.list.elementAt(index);
    }
    
    public Node removeNamedItem(final String name) throws DOMException {
        if (this.readonly) {
            throw new DomEx((short)7);
        }
        for (int i = 0; i < this.list.size(); ++i) {
            final Node value = this.list.elementAt(i);
            if (value.getNodeName().equals(name)) {
                final AttributeNode att = (AttributeNode)value;
                final String defaultValue = att.getDefaultValue();
                if (defaultValue != null) {
                    att.setValue(defaultValue);
                    att.setSpecified(false);
                }
                else {
                    this.list.removeElementAt(i);
                }
                return value;
            }
        }
        throw new DomEx((short)8);
    }
    
    public Node removeNamedItemNS(final String namespaceURI, final String localName) throws DOMException {
        if (this.readonly) {
            throw new DomEx((short)7);
        }
        for (int i = 0; i < this.list.size(); ++i) {
            final Node value = this.list.elementAt(i);
            final String iLocalName = value.getLocalName();
            if (iLocalName != null && iLocalName.equals(localName)) {
                final String iNamespaceURI = value.getNamespaceURI();
                if (iNamespaceURI != null && iNamespaceURI.equals(namespaceURI)) {
                    final AttributeNode attr = (AttributeNode)value;
                    final String defaultValue = attr.getDefaultValue();
                    if (defaultValue != null) {
                        attr.setValue(defaultValue);
                        attr.setSpecified(false);
                    }
                    else {
                        this.list.removeElementAt(i);
                    }
                    return value;
                }
            }
        }
        throw new DomEx((short)8);
    }
    
    public Node setNamedItem(final Node value) throws DOMException {
        if (this.readonly) {
            throw new DomEx((short)7);
        }
        if (!(value instanceof AttributeNode) || value.getOwnerDocument() != this.ownerElement.getOwnerDocument()) {
            throw new DomEx((short)4);
        }
        final AttributeNode att = (AttributeNode)value;
        if (att.getOwnerElement() != null) {
            throw new DomEx((short)10);
        }
        final int length = this.list.size();
        int i = 0;
        while (i < length) {
            final AttributeNode oldAtt = (AttributeNode)this.item(i);
            if (oldAtt.getNodeName().equals(value.getNodeName())) {
                if (oldAtt.isReadonly()) {
                    throw new DomEx((short)7);
                }
                att.setOwnerElement(this.ownerElement);
                this.list.setElementAt(att, i);
                oldAtt.setOwnerElement(null);
                return oldAtt;
            }
            else {
                ++i;
            }
        }
        att.setOwnerElement(this.ownerElement);
        this.list.addElement(value);
        return null;
    }
    
    public Node setNamedItemNS(final Node arg) throws DOMException {
        if (this.readonly) {
            throw new DomEx((short)7);
        }
        if (!(arg instanceof AttributeNode) || arg.getOwnerDocument() != this.ownerElement.getOwnerDocument()) {
            throw new DomEx((short)4);
        }
        final AttributeNode attr = (AttributeNode)arg;
        if (attr.getOwnerElement() != null) {
            throw new DomEx((short)10);
        }
        final int length = this.list.size();
        int i = 0;
        while (i < length) {
            final AttributeNode oldNode = (AttributeNode)this.item(i);
            final String localName = oldNode.getLocalName();
            final String namespaceURI = oldNode.getNamespaceURI();
            if (attr.getLocalName().equals(localName) && attr.getNamespaceURI().equals(namespaceURI)) {
                if (oldNode.isReadonly()) {
                    throw new DomEx((short)7);
                }
                attr.setOwnerElement(this.ownerElement);
                this.list.setElementAt(attr, i);
                oldNode.setOwnerElement(null);
                return oldNode;
            }
            else {
                ++i;
            }
        }
        attr.setOwnerElement(this.ownerElement);
        this.list.addElement(attr);
        return null;
    }
    
    public void writeXml(final XmlWriteContext context) throws IOException {
        final Writer out = context.getWriter();
        for (int length = this.list.size(), i = 0; i < length; ++i) {
            final AttributeNode tmp = this.list.elementAt(i);
            if (tmp.getSpecified()) {
                out.write(32);
                tmp.writeXml(context);
            }
        }
    }
    
    public void writeChildrenXml(final XmlWriteContext context) throws IOException {
    }
    
    public String toString() {
        try {
            final CharArrayWriter out = new CharArrayWriter();
            final XmlWriteContext x = new XmlWriteContext(out);
            this.writeXml(x);
            return out.toString();
        }
        catch (IOException e) {
            return super.toString();
        }
    }
}
