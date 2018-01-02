// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;

public class ProcessingInstructionImpl extends NodeImpl implements ProcessingInstruction
{
    static final long serialVersionUID = 7554435174099981510L;
    
    public ProcessingInstructionImpl(final DocumentImpl documentImpl, final String s, final String s2) {
        super(documentImpl, s, s2);
    }
    
    public short getNodeType() {
        return 7;
    }
    
    public Node cloneNode(final boolean b) {
        if (super.syncData) {
            this.synchronizeData();
        }
        return super.ownerDocument.createProcessingInstruction(super.name, super.value);
    }
    
    public String getTarget() {
        if (super.syncData) {
            this.synchronizeData();
        }
        return super.name;
    }
    
    public String getData() {
        if (super.syncData) {
            this.synchronizeData();
        }
        return super.value;
    }
    
    public void setData(final String nodeValue) {
        this.setNodeValue(nodeValue);
    }
}
