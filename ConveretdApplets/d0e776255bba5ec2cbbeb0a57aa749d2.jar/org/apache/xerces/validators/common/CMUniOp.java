// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.common;

public class CMUniOp extends CMNode
{
    private CMNode fChild;
    
    public CMUniOp(final int n, final CMNode fChild) throws CMException {
        super(n);
        if (this.type() != 1 && this.type() != 2 && this.type() != 3) {
            throw new CMException(12);
        }
        this.fChild = fChild;
    }
    
    final CMNode getChild() {
        return this.fChild;
    }
    
    boolean isNullable() throws CMException {
        return this.type() != 3 || this.fChild.isNullable();
    }
    
    protected void calcFirstPos(final CMStateSet set) throws CMException {
        set.setTo(this.fChild.firstPos());
    }
    
    protected void calcLastPos(final CMStateSet set) throws CMException {
        set.setTo(this.fChild.lastPos());
    }
}
