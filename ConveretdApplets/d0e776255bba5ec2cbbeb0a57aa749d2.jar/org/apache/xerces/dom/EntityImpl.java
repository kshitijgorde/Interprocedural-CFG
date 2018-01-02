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
    protected String version;
    protected String notationName;
    
    public EntityImpl(final DocumentImpl documentImpl, final String name) {
        super(documentImpl);
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
    
    public Node cloneNode(final boolean b) {
        return super.cloneNode(b);
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
    
    public void setPublicId(final String publicId) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.publicId = publicId;
    }
    
    public void setEncoding(final String encoding) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.encoding = encoding;
    }
    
    public void setVersion(final String version) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.version = version;
    }
    
    public void setSystemId(final String systemId) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.systemId = systemId;
    }
    
    public void setNotationName(final String notationName) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.notationName = notationName;
    }
}
