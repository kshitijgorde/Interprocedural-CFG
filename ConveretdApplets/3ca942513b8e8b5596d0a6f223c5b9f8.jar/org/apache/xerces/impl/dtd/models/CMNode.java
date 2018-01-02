// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dtd.models;

public abstract class CMNode
{
    private int fType;
    private CMStateSet fFirstPos;
    private CMStateSet fFollowPos;
    private CMStateSet fLastPos;
    private int fMaxStates;
    
    public CMNode(final int fType) {
        this.fFirstPos = null;
        this.fFollowPos = null;
        this.fLastPos = null;
        this.fMaxStates = -1;
        this.fType = fType;
    }
    
    public abstract boolean isNullable();
    
    public final int type() {
        return this.fType;
    }
    
    public final CMStateSet firstPos() {
        if (this.fFirstPos == null) {
            this.calcFirstPos(this.fFirstPos = new CMStateSet(this.fMaxStates));
        }
        return this.fFirstPos;
    }
    
    public final CMStateSet lastPos() {
        if (this.fLastPos == null) {
            this.calcLastPos(this.fLastPos = new CMStateSet(this.fMaxStates));
        }
        return this.fLastPos;
    }
    
    final void setFollowPos(final CMStateSet fFollowPos) {
        this.fFollowPos = fFollowPos;
    }
    
    public final void setMaxStates(final int fMaxStates) {
        this.fMaxStates = fMaxStates;
    }
    
    protected abstract void calcFirstPos(final CMStateSet p0);
    
    protected abstract void calcLastPos(final CMStateSet p0);
}
