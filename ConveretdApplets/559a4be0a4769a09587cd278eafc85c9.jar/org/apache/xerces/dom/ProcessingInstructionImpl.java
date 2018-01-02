// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.ProcessingInstruction;

public class ProcessingInstructionImpl extends CharacterDataImpl implements ProcessingInstruction
{
    static final long serialVersionUID = 7554435174099981510L;
    protected String target;
    protected String baseURI;
    
    public ProcessingInstructionImpl(final CoreDocumentImpl ownerDoc, final String target, final String data) {
        super(ownerDoc, data);
        this.target = target;
    }
    
    public short getNodeType() {
        return 7;
    }
    
    public String getNodeName() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.target;
    }
    
    public String getTarget() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.target;
    }
    
    public String getData() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return super.data;
    }
    
    public void setData(final String data) {
        this.setNodeValue(data);
    }
    
    public String getBaseURI() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return (this.baseURI != null) ? this.baseURI : super.ownerNode.getBaseURI();
    }
    
    public void setBaseURI(final String uri) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.baseURI = uri;
    }
}
