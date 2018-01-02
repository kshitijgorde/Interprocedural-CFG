// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.DOMException;

public class AttrNSImpl extends AttrImpl
{
    static final long serialVersionUID = -781906615369795414L;
    static final String xmlnsURI = "http://www.w3.org/2000/xmlns/";
    static final String xmlURI = "http://www.w3.org/XML/1998/namespace";
    protected String namespaceURI;
    protected String localName;
    
    protected AttrNSImpl(final DocumentImpl documentImpl, final String namespaceURI, final String localName) {
        super(documentImpl, localName);
        final int index = localName.indexOf(58);
        if (index < 0) {
            this.localName = localName;
            if (documentImpl.errorChecking && localName.equals("xmlns") && (namespaceURI == null || !namespaceURI.equals("http://www.w3.org/2000/xmlns/"))) {
                throw new DOMException((short)14, "DOM003 Namespace error");
            }
        }
        else {
            final String substring = localName.substring(0, index);
            this.localName = localName.substring(index + 1);
            if (documentImpl.errorChecking) {
                if (namespaceURI == null || this.localName.length() == 0 || this.localName.indexOf(58) >= 0) {
                    throw new DOMException((short)14, "DOM003 Namespace error");
                }
                if (substring.equals("xml")) {
                    if (!namespaceURI.equals("http://www.w3.org/XML/1998/namespace")) {
                        throw new DOMException((short)14, "DOM003 Namespace error");
                    }
                }
                else if (substring.equals("xmlns")) {
                    if (!namespaceURI.equals("http://www.w3.org/2000/xmlns/")) {
                        throw new DOMException((short)14, "DOM003 Namespace error");
                    }
                }
                else if (index == 0) {
                    throw new DOMException((short)14, "DOM003 Namespace error");
                }
            }
        }
        this.namespaceURI = namespaceURI;
    }
    
    protected AttrNSImpl(final DocumentImpl documentImpl, final String s) {
        super(documentImpl, s);
    }
    
    public String getNamespaceURI() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.namespaceURI;
    }
    
    public String getPrefix() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        final int index = super.name.indexOf(58);
        return (index < 0) ? null : super.name.substring(0, index);
    }
    
    public void setPrefix(final String s) throws DOMException {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.ownerDocument().errorChecking) {
            if (this.isReadOnly()) {
                throw new DOMException((short)7, "DOM001 Modification not allowed");
            }
            if (!DocumentImpl.isXMLName(s)) {
                throw new DOMException((short)5, "DOM002 Illegal character");
            }
            if (this.namespaceURI == null) {
                throw new DOMException((short)14, "DOM003 Namespace error");
            }
            if (s != null) {
                if (s.equals("xmlns")) {
                    if (!this.namespaceURI.equals("http://www.w3.org/2000/xmlns/")) {
                        throw new DOMException((short)14, "DOM003 Namespace error");
                    }
                }
                else if (s.equals("xml") && !this.namespaceURI.equals("http://www.w3.org/XML/1998/namespace")) {
                    throw new DOMException((short)14, "DOM003 Namespace error");
                }
            }
        }
        super.name = s + ":" + this.localName;
    }
    
    public String getLocalName() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.localName;
    }
}
