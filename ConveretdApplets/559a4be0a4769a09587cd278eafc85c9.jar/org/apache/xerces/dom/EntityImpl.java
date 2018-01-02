// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Node;
import org.w3c.dom.Entity;

public class EntityImpl extends ParentNode implements Entity
{
    static final long serialVersionUID = -3575760943444303423L;
    protected String name;
    protected String publicId;
    protected String systemId;
    protected String encoding;
    protected String actualEncoding;
    protected String version;
    protected String notationName;
    protected String baseURI;
    
    public EntityImpl(final CoreDocumentImpl ownerDoc, final String name) {
        super(ownerDoc);
        this.name = name;
        this.isReadOnly(true);
    }
    
    public short getNodeType() {
        return 6;
    }
    
    public String getNodeName() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.name;
    }
    
    public Node cloneNode(final boolean deep) {
        final EntityImpl newentity = (EntityImpl)super.cloneNode(deep);
        newentity.setReadOnly(true, deep);
        return newentity;
    }
    
    public String getPublicId() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.publicId;
    }
    
    public String getSystemId() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.systemId;
    }
    
    public String getVersion() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.version;
    }
    
    public String getEncoding() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.encoding;
    }
    
    public String getNotationName() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.notationName;
    }
    
    public void setPublicId(final String id) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.publicId = id;
    }
    
    public void setEncoding(final String value) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.encoding = value;
    }
    
    public String getActualEncoding() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.actualEncoding;
    }
    
    public void setActualEncoding(final String actualEncoding) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.actualEncoding = actualEncoding;
    }
    
    public void setVersion(final String value) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.version = value;
    }
    
    public void setSystemId(final String id) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.systemId = id;
    }
    
    public void setNotationName(final String name) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.notationName = name;
    }
    
    public String getBaseURI() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return (this.baseURI != null) ? this.baseURI : ((CoreDocumentImpl)this.getOwnerDocument()).getBaseURI();
    }
    
    public void setBaseURI(final String uri) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.baseURI = uri;
    }
}
