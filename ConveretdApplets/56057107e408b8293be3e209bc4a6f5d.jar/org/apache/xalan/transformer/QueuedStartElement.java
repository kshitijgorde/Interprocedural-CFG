// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.apache.xml.utils.NameSpace;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import java.util.Vector;
import org.apache.xml.utils.MutableAttrListImpl;

public class QueuedStartElement extends QueuedSAXEvent
{
    private MutableAttrListImpl m_attributes;
    private boolean m_nsDeclsHaveBeenAdded;
    private String m_name;
    private String m_url;
    private String m_localName;
    Vector m_namespaces;
    
    public QueuedStartElement() {
        super(2);
        this.m_attributes = new MutableAttrListImpl();
        this.m_nsDeclsHaveBeenAdded = false;
        this.m_namespaces = null;
    }
    
    void addAttribute(final String uri, final String localName, final String qName, final String type, final String value) {
        this.m_attributes.addAttribute(uri, localName, qName, type, value);
    }
    
    void flush() throws SAXException {
        if (super.isPending) {
            if (this.m_name != null) {
                super.m_contentHandler.startElement(this.m_url, this.m_localName, this.m_name, this.m_attributes);
                if (super.m_traceManager != null) {
                    this.fireGenerateEvent(3, this.m_name, this.m_attributes);
                }
            }
            this.reset();
        }
    }
    
    MutableAttrListImpl getAttrs() {
        return this.m_attributes;
    }
    
    String getLocalName() {
        return this.m_localName;
    }
    
    String getName() {
        return this.m_name;
    }
    
    String getURL() {
        return this.m_url;
    }
    
    boolean isElement(final String ns, final String localName) {
        if (this.m_localName != null && this.m_localName.equals(localName)) {
            if (ns == null && this.m_url == null) {
                return true;
            }
            if (ns != null && this.m_url != null) {
                return ns.equals(this.m_url);
            }
        }
        return false;
    }
    
    boolean nsDeclsHaveBeenAdded() {
        return this.m_nsDeclsHaveBeenAdded;
    }
    
    void reset() {
        super.reset();
        this.m_attributes.clear();
        this.m_nsDeclsHaveBeenAdded = false;
        this.m_name = null;
        this.m_url = null;
        this.m_localName = null;
        this.m_namespaces = null;
    }
    
    void setNSDeclsHaveBeenAdded(final boolean b) {
        this.m_nsDeclsHaveBeenAdded = b;
    }
    
    void setPending(final String ns, final String localName, final String name, final Attributes atts) {
        this.m_name = name;
        this.m_url = ns;
        this.m_localName = localName;
        if (atts != null) {
            this.m_attributes.addAttributes(atts);
        }
        this.setPending(true);
    }
    
    void startPrefixMapping(final String prefix, final String uri) {
        if (this.m_namespaces == null) {
            this.m_namespaces = new Vector();
        }
        final NameSpace ns = new NameSpace(prefix, uri);
        this.m_namespaces.addElement(ns);
    }
}
