// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import org.w3c.dom.DOMException;
import java.util.Vector;
import org.w3c.dom.Entity;
import org.w3c.dom.Notation;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import java.io.IOException;
import org.w3c.dom.Element;
import java.io.Writer;
import org.w3c.dom.DocumentType;

final class Doctype extends NodeBase implements DocumentType
{
    private String name;
    private Nodemap entities;
    private Nodemap notations;
    private String publicId;
    private String systemId;
    private String internalSubset;
    
    Doctype(final String pub, final String sys, final String subset) {
        this.publicId = pub;
        this.systemId = sys;
        this.internalSubset = subset;
    }
    
    Doctype(final String name, final String publicId, final String systemId, final String internalSubset) {
        this.name = name;
        this.publicId = publicId;
        this.systemId = systemId;
        this.internalSubset = internalSubset;
        this.entities = new Nodemap();
        this.notations = new Nodemap();
    }
    
    void setPrintInfo(final String pub, final String sys, final String subset) {
        this.publicId = pub;
        this.systemId = sys;
        this.internalSubset = subset;
    }
    
    public void writeXml(final XmlWriteContext context) throws IOException {
        final Writer out = context.getWriter();
        final Element root = this.getOwnerDocument().getDocumentElement();
        out.write("<!DOCTYPE ");
        out.write((root == null) ? "UNKNOWN-ROOT" : root.getNodeName());
        if (this.systemId != null) {
            if (this.publicId != null) {
                out.write(" PUBLIC '");
                out.write(this.publicId);
                out.write("' '");
            }
            else {
                out.write(" SYSTEM '");
            }
            out.write(this.systemId);
            out.write("'");
        }
        if (this.internalSubset != null) {
            out.write(XmlDocument.eol);
            out.write("[");
            out.write(this.internalSubset);
            out.write("]");
        }
        out.write(">");
        out.write(XmlDocument.eol);
    }
    
    public short getNodeType() {
        return 10;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getNodeName() {
        return this.name;
    }
    
    public Node cloneNode(final boolean deep) {
        final Doctype retval = new Doctype(this.name, this.publicId, this.systemId, this.internalSubset);
        retval.setOwnerDocument((XmlDocument)this.getOwnerDocument());
        return retval;
    }
    
    public NamedNodeMap getEntities() {
        return this.entities;
    }
    
    public NamedNodeMap getNotations() {
        return this.notations;
    }
    
    public String getPublicId() {
        return this.publicId;
    }
    
    public String getSystemId() {
        return this.systemId;
    }
    
    public String getInternalSubset() {
        return this.internalSubset;
    }
    
    void setOwnerDocument(final XmlDocument doc) {
        super.setOwnerDocument(doc);
        if (this.entities != null) {
            for (int i = 0; this.entities.item(i) != null; ++i) {
                ((NodeBase)this.entities.item(i)).setOwnerDocument(doc);
            }
        }
        if (this.notations != null) {
            for (int j = 0; this.notations.item(j) != null; ++j) {
                ((NodeBase)this.notations.item(j)).setOwnerDocument(doc);
            }
        }
    }
    
    void addNotation(final String name, final String pub, final String sys) {
        final NotationNode node = new NotationNode(name, pub, sys);
        node.setOwnerDocument((XmlDocument)this.getOwnerDocument());
        this.notations.setNamedItem(node);
    }
    
    void addEntityNode(final String name, final String pub, final String sys, final String not) {
        final EntityNode node = new EntityNode(name, pub, sys, not);
        node.setOwnerDocument((XmlDocument)this.getOwnerDocument());
        this.entities.setNamedItem(node);
    }
    
    void addEntityNode(final String name, final String value) {
        if ("lt".equals(name) || "gt".equals(name) || "apos".equals(name) || "quot".equals(name) || "amp".equals(name)) {
            return;
        }
        final EntityNode node = new EntityNode(name, value);
        node.setOwnerDocument((XmlDocument)this.getOwnerDocument());
        this.entities.setNamedItem(node);
    }
    
    void setReadonly() {
        this.entities.readonly = true;
        this.notations.readonly = true;
    }
    
    static class NotationNode extends NodeBase implements Notation
    {
        private String notation;
        private String publicId;
        private String systemId;
        
        NotationNode(final String name, final String pub, final String sys) {
            this.notation = name;
            this.publicId = pub;
            this.systemId = sys;
        }
        
        public String getPublicId() {
            return this.publicId;
        }
        
        public String getSystemId() {
            return this.systemId;
        }
        
        public short getNodeType() {
            return 12;
        }
        
        public String getNodeName() {
            return this.notation;
        }
        
        public Node cloneNode(final boolean ignored) {
            final NotationNode retval = new NotationNode(this.notation, this.publicId, this.systemId);
            retval.setOwnerDocument((XmlDocument)this.getOwnerDocument());
            return retval;
        }
        
        public void writeXml(final XmlWriteContext context) throws IOException {
            final Writer out = context.getWriter();
            out.write("<!NOTATION ");
            out.write(this.notation);
            if (this.publicId != null) {
                out.write(" PUBLIC '");
                out.write(this.publicId);
                if (this.systemId != null) {
                    out.write("' '");
                    out.write(this.systemId);
                }
            }
            else {
                out.write(" SYSTEM '");
                out.write(this.systemId);
            }
            out.write("'>");
        }
    }
    
    static class EntityNode extends NodeBase implements Entity
    {
        private String entityName;
        private String publicId;
        private String systemId;
        private String notation;
        private String value;
        
        EntityNode(final String name, final String pub, final String sys, final String not) {
            this.entityName = name;
            this.publicId = pub;
            this.systemId = sys;
            this.notation = not;
        }
        
        EntityNode(final String name, final String value) {
            this.entityName = name;
            this.value = value;
        }
        
        public String getNodeName() {
            return this.entityName;
        }
        
        public short getNodeType() {
            return 6;
        }
        
        public String getPublicId() {
            return this.publicId;
        }
        
        public String getSystemId() {
            return this.systemId;
        }
        
        public String getNotationName() {
            return this.notation;
        }
        
        public Node cloneNode(final boolean ignored) {
            final EntityNode retval = new EntityNode(this.entityName, this.publicId, this.systemId, this.notation);
            retval.setOwnerDocument((XmlDocument)this.getOwnerDocument());
            return retval;
        }
        
        public void writeXml(final XmlWriteContext context) throws IOException {
            final Writer out = context.getWriter();
            out.write("<!ENTITY ");
            out.write(this.entityName);
            if (this.value == null) {
                if (this.publicId != null) {
                    out.write(" PUBLIC '");
                    out.write(this.publicId);
                    out.write("' '");
                }
                else {
                    out.write(" SYSTEM '");
                }
                out.write(this.systemId);
                out.write("'");
                if (this.notation != null) {
                    out.write(" NDATA ");
                    out.write(this.notation);
                }
            }
            else {
                out.write(" \"");
                for (int length = this.value.length(), i = 0; i < length; ++i) {
                    final char c = this.value.charAt(i);
                    if (c == '\"') {
                        out.write("&quot;");
                    }
                    else {
                        out.write(c);
                    }
                }
                out.write(34);
            }
            out.write(">");
        }
    }
    
    static class Nodemap implements NamedNodeMap
    {
        boolean readonly;
        Vector list;
        
        Nodemap() {
            this.list = new Vector();
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
            throw new DomEx((short)7);
        }
        
        public Node removeNamedItemNS(final String namespaceURI, final String localName) throws DOMException {
            throw new DomEx((short)7);
        }
        
        public Node setNamedItem(final Node item) throws DOMException {
            if (this.readonly) {
                throw new DomEx((short)7);
            }
            this.list.addElement(item);
            return null;
        }
        
        public Node setNamedItemNS(final Node arg) throws DOMException {
            if (this.readonly) {
                throw new DomEx((short)7);
            }
            this.list.addElement(arg);
            return null;
        }
    }
}
