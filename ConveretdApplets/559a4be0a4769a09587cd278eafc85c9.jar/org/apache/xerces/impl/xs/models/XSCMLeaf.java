// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.models;

import org.apache.xerces.impl.dtd.models.CMStateSet;
import org.apache.xerces.impl.dtd.models.CMNode;

public class XSCMLeaf extends CMNode
{
    private Object fLeaf;
    private int fParticleId;
    private int fPosition;
    
    public XSCMLeaf(final int type, final Object leaf, final int id, final int position) {
        super(type);
        this.fLeaf = null;
        this.fParticleId = -1;
        this.fPosition = -1;
        this.fLeaf = leaf;
        this.fParticleId = id;
        this.fPosition = position;
    }
    
    final Object getLeaf() {
        return this.fLeaf;
    }
    
    final int getParticleId() {
        return this.fParticleId;
    }
    
    final int getPosition() {
        return this.fPosition;
    }
    
    final void setPosition(final int newPosition) {
        this.fPosition = newPosition;
    }
    
    public boolean isNullable() {
        return this.fPosition == -1;
    }
    
    public String toString() {
        final StringBuffer strRet = new StringBuffer(this.fLeaf.toString());
        if (this.fPosition >= 0) {
            strRet.append(" (Pos:" + new Integer(this.fPosition).toString() + ")");
        }
        return strRet.toString();
    }
    
    protected void calcFirstPos(final CMStateSet toSet) {
        if (this.fPosition == -1) {
            toSet.zeroBits();
        }
        else {
            toSet.setBit(this.fPosition);
        }
    }
    
    protected void calcLastPos(final CMStateSet toSet) {
        if (this.fPosition == -1) {
            toSet.zeroBits();
        }
        else {
            toSet.setBit(this.fPosition);
        }
    }
}
