// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dtd.models;

public class CMBinOp extends CMNode
{
    private CMNode fLeftChild;
    private CMNode fRightChild;
    
    public CMBinOp(final int type, final CMNode leftNode, final CMNode rightNode) {
        super(type);
        if (this.type() != 4 && this.type() != 5) {
            throw new RuntimeException("ImplementationMessages.VAL_BST");
        }
        this.fLeftChild = leftNode;
        this.fRightChild = rightNode;
    }
    
    final CMNode getLeft() {
        return this.fLeftChild;
    }
    
    final CMNode getRight() {
        return this.fRightChild;
    }
    
    public boolean isNullable() {
        if (this.type() == 4) {
            return this.fLeftChild.isNullable() || this.fRightChild.isNullable();
        }
        if (this.type() == 5) {
            return this.fLeftChild.isNullable() && this.fRightChild.isNullable();
        }
        throw new RuntimeException("ImplementationMessages.VAL_BST");
    }
    
    protected void calcFirstPos(final CMStateSet toSet) {
        if (this.type() == 4) {
            toSet.setTo(this.fLeftChild.firstPos());
            toSet.union(this.fRightChild.firstPos());
        }
        else {
            if (this.type() != 5) {
                throw new RuntimeException("ImplementationMessages.VAL_BST");
            }
            toSet.setTo(this.fLeftChild.firstPos());
            if (this.fLeftChild.isNullable()) {
                toSet.union(this.fRightChild.firstPos());
            }
        }
    }
    
    protected void calcLastPos(final CMStateSet toSet) {
        if (this.type() == 4) {
            toSet.setTo(this.fLeftChild.lastPos());
            toSet.union(this.fRightChild.lastPos());
        }
        else {
            if (this.type() != 5) {
                throw new RuntimeException("ImplementationMessages.VAL_BST");
            }
            toSet.setTo(this.fRightChild.lastPos());
            if (this.fRightChild.isNullable()) {
                toSet.union(this.fLeftChild.lastPos());
            }
        }
    }
}
