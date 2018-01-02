// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.ProcessingInstruction;

public class ProcessingInstructionImpl extends CharacterDataImpl implements ProcessingInstruction
{
    static final long serialVersionUID = 7554435174099981510L;
    protected String target;
    
    public ProcessingInstructionImpl(final DocumentImpl documentImpl, final String target, final String s) {
        super(documentImpl, s);
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
    
    public void setData(final String nodeValue) {
        this.setNodeValue(nodeValue);
    }
}
