// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.dtd;

public abstract class CMNode
{
    private int fType;
    private CMStateSet fFirstPos;
    private CMStateSet fFollowPos;
    private CMStateSet fLastPos;
    private int fMaxStates;
    
    CMNode(final int fType) throws CMException {
        this.fMaxStates = -1;
        this.fType = fType;
    }
    
    abstract boolean isNullable() throws CMException;
    
    final int type() {
        return this.fType;
    }
    
    final CMStateSet firstPos() throws CMException {
        if (this.fFirstPos == null) {
            this.calcFirstPos(this.fFirstPos = new CMStateSet(this.fMaxStates));
        }
        return this.fFirstPos;
    }
    
    final CMStateSet lastPos() throws CMException {
        if (this.fLastPos == null) {
            this.calcLastPos(this.fLastPos = new CMStateSet(this.fMaxStates));
        }
        return this.fLastPos;
    }
    
    final void setFollowPos(final CMStateSet fFollowPos) {
        this.fFollowPos = fFollowPos;
    }
    
    final void setMaxStates(final int fMaxStates) {
        this.fMaxStates = fMaxStates;
    }
    
    protected abstract void calcFirstPos(final CMStateSet p0) throws CMException;
    
    protected abstract void calcLastPos(final CMStateSet p0) throws CMException;
}
