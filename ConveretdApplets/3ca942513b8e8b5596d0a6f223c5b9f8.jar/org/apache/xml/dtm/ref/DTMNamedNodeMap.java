// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm.ref;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.apache.xml.dtm.DTM;
import org.w3c.dom.NamedNodeMap;

public class DTMNamedNodeMap implements NamedNodeMap
{
    DTM dtm;
    int element;
    short m_count;
    
    public DTMNamedNodeMap(final DTM dtm, final int element) {
        this.m_count = -1;
        this.dtm = dtm;
        this.element = element;
    }
    
    public int getLength() {
        if (this.m_count == -1) {
            short count = 0;
            for (int n = this.dtm.getFirstAttribute(this.element); n != -1; n = this.dtm.getNextAttribute(n)) {
                ++count;
            }
            this.m_count = count;
        }
        return this.m_count;
    }
    
    public Node getNamedItem(final String name) {
        for (int n = this.dtm.getFirstAttribute(this.element); n != -1; n = this.dtm.getNextAttribute(n)) {
            if (this.dtm.getNodeName(n).equals(name)) {
                return this.dtm.getNode(n);
            }
        }
        return null;
    }
    
    public Node item(final int i) {
        int count = 0;
        for (int n = this.dtm.getFirstAttribute(this.element); n != -1; n = this.dtm.getNextAttribute(n)) {
            if (count == i) {
                return this.dtm.getNode(n);
            }
            ++count;
        }
        return null;
    }
    
    public Node setNamedItem(final Node newNode) {
        throw new DTMException((short)7);
    }
    
    public Node removeNamedItem(final String name) {
        throw new DTMException((short)7);
    }
    
    public Node getNamedItemNS(final String namespaceURI, final String localName) {
        Node retNode = null;
        for (int n = this.dtm.getFirstAttribute(this.element); n != -1; n = this.dtm.getNextAttribute(n)) {
            if (localName.equals(this.dtm.getLocalName(n))) {
                final String nsURI = this.dtm.getNamespaceURI(n);
                if ((namespaceURI == null && nsURI == null) || (namespaceURI != null && namespaceURI.equals(nsURI))) {
                    retNode = this.dtm.getNode(n);
                    break;
                }
            }
        }
        return retNode;
    }
    
    public Node setNamedItemNS(final Node arg) throws DOMException {
        throw new DTMException((short)7);
    }
    
    public Node removeNamedItemNS(final String namespaceURI, final String localName) throws DOMException {
        throw new DTMException((short)7);
    }
    
    public class DTMException extends DOMException
    {
        static final long serialVersionUID = -8290238117162437678L;
        
        public DTMException(final short code, final String message) {
            super(code, message);
        }
        
        public DTMException(final short code) {
            super(code, "");
        }
    }
}
