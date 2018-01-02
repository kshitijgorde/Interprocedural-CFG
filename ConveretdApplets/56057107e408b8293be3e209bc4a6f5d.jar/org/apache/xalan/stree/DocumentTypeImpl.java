// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import java.util.Hashtable;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.DocumentType;

public class DocumentTypeImpl extends Child implements DocumentType, NamedNodeMap
{
    private String m_name;
    private String m_publicID;
    private String m_systemID;
    private String m_internalSubset;
    Hashtable m_entities;
    
    DocumentTypeImpl(final DocumentImpl doc, final String name) {
        super(doc);
        this.m_entities = new Hashtable();
        this.m_name = name;
    }
    
    DocumentTypeImpl(final DocumentImpl doc, final String name, final String publicId, final String systemId) {
        super(doc);
        this.m_entities = new Hashtable();
        this.m_name = name;
        this.m_systemID = systemId;
        this.m_publicID = publicId;
    }
    
    public NamedNodeMap getEntities() {
        return this;
    }
    
    public String getInternalSubset() {
        return this.m_internalSubset;
    }
    
    public int getLength() {
        return 0;
    }
    
    public String getLocalName() {
        return this.m_name;
    }
    
    public String getName() {
        return this.m_name;
    }
    
    public Node getNamedItem(final String name) {
        return this.m_entities.get(name);
    }
    
    public Node getNamedItemNS(final String namespaceURI, final String localName) {
        return null;
    }
    
    public String getNodeName() {
        return this.m_name;
    }
    
    public short getNodeType() {
        return 10;
    }
    
    public NamedNodeMap getNotations() {
        return null;
    }
    
    public String getPublicId() {
        return this.m_publicID;
    }
    
    public String getSystemId() {
        return this.m_systemID;
    }
    
    public Node item(final int index) {
        return null;
    }
    
    public Node removeNamedItem(final String name) throws DOMException {
        return null;
    }
    
    public Node removeNamedItemNS(final String namespaceURI, final String localName) throws DOMException {
        return null;
    }
    
    public Node setNamedItem(final Node arg) throws DOMException {
        this.m_entities.put(arg.getNodeName(), arg);
        return null;
    }
    
    public Node setNamedItemNS(final Node arg) throws DOMException {
        return null;
    }
}
