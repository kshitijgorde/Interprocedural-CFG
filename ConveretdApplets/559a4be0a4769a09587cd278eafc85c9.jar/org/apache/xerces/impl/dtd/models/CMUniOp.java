// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dtd.models;

public class CMUniOp extends CMNode
{
    private CMNode fChild;
    
    public CMUniOp(final int type, final CMNode childNode) {
        super(type);
        if (this.type() != 1 && this.type() != 2 && this.type() != 3) {
            throw new RuntimeException("ImplementationMessages.VAL_UST");
        }
        this.fChild = childNode;
    }
    
    final CMNode getChild() {
        return this.fChild;
    }
    
    public boolean isNullable() {
        return this.type() != 3 || this.fChild.isNullable();
    }
    
    protected void calcFirstPos(final CMStateSet toSet) {
        toSet.setTo(this.fChild.firstPos());
    }
    
    protected void calcLastPos(final CMStateSet toSet) {
        toSet.setTo(this.fChild.lastPos());
    }
}
