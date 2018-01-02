// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.dtd;

public class CMBinOp extends CMNode
{
    private CMNode fLeftChild;
    private CMNode fRightChild;
    
    public CMBinOp(final int n, final CMNode fLeftChild, final CMNode fRightChild) throws CMException {
        super(n);
        if (this.type() != 4 && this.type() != 5) {
            throw new CMException(6);
        }
        this.fLeftChild = fLeftChild;
        this.fRightChild = fRightChild;
    }
    
    final CMNode getLeft() {
        return this.fLeftChild;
    }
    
    final CMNode getRight() {
        return this.fRightChild;
    }
    
    boolean isNullable() throws CMException {
        if (this.type() == 4) {
            return this.fLeftChild.isNullable() || this.fRightChild.isNullable();
        }
        if (this.type() == 5) {
            return this.fLeftChild.isNullable() && this.fRightChild.isNullable();
        }
        throw new CMException(6);
    }
    
    protected void calcFirstPos(final CMStateSet set) throws CMException {
        if (this.type() == 4) {
            set.setTo(this.fLeftChild.firstPos());
            set.union(this.fRightChild.firstPos());
            return;
        }
        if (this.type() != 5) {
            throw new CMException(6);
        }
        set.setTo(this.fLeftChild.firstPos());
        if (this.fLeftChild.isNullable()) {
            set.union(this.fRightChild.firstPos());
        }
    }
    
    protected void calcLastPos(final CMStateSet set) throws CMException {
        if (this.type() == 4) {
            set.setTo(this.fLeftChild.lastPos());
            set.union(this.fRightChild.lastPos());
            return;
        }
        if (this.type() != 5) {
            throw new CMException(6);
        }
        set.setTo(this.fRightChild.lastPos());
        if (this.fRightChild.isNullable()) {
            set.union(this.fLeftChild.lastPos());
        }
    }
}
