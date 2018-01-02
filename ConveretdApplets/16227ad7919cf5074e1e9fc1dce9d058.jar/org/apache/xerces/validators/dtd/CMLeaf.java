// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.dtd;

import org.apache.xerces.utils.StringPool;

public class CMLeaf extends CMNode
{
    private int fElementIndex;
    private int fPosition;
    
    public CMLeaf(final int n, final int fElementIndex, final int fPosition) throws CMException {
        super(n);
        this.fPosition = -1;
        if (this.type() != 0) {
            throw new CMException(9);
        }
        this.fElementIndex = fElementIndex;
        this.fPosition = fPosition;
    }
    
    public CMLeaf(final int n, final int fElementIndex) throws CMException {
        super(n);
        this.fPosition = -1;
        if (this.type() != 0) {
            throw new CMException(9);
        }
        this.fElementIndex = fElementIndex;
    }
    
    final int getElemIndex() {
        return this.fElementIndex;
    }
    
    final int getPosition() {
        return this.fPosition;
    }
    
    final void setPosition(final int fPosition) {
        this.fPosition = fPosition;
    }
    
    boolean isNullable() throws CMException {
        return this.fPosition == -1;
    }
    
    String toString(final StringPool stringPool) {
        final StringBuffer sb = new StringBuffer(stringPool.toString(this.fElementIndex));
        if (this.fPosition >= 0) {
            sb.append(" (Pos:" + new Integer(this.fPosition).toString() + ")");
        }
        return sb.toString();
    }
    
    protected void calcFirstPos(final CMStateSet set) throws CMException {
        if (this.fPosition == -1) {
            set.zeroBits();
            return;
        }
        set.setBit(this.fPosition);
    }
    
    protected void calcLastPos(final CMStateSet set) throws CMException {
        if (this.fPosition == -1) {
            set.zeroBits();
            return;
        }
        set.setBit(this.fPosition);
    }
}
