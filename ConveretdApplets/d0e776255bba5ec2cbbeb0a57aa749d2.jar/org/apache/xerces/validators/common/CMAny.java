// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.common;

import org.apache.xerces.utils.StringPool;

public class CMAny extends CMNode
{
    private int fType;
    private int fURI;
    private int fPosition;
    
    public CMAny(final int fType, final int furi, final int fPosition) throws CMException {
        super(fType);
        this.fPosition = -1;
        this.fType = fType;
        this.fURI = furi;
        this.fPosition = fPosition;
    }
    
    final int getType() {
        return this.fType;
    }
    
    final int getURI() {
        return this.fURI;
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
        final StringBuffer sb = new StringBuffer();
        sb.append("(");
        sb.append("##any:uri=");
        sb.append(stringPool.toString(this.fURI));
        sb.append(')');
        if (this.fPosition >= 0) {
            sb.append(" (Pos:" + new Integer(this.fPosition).toString() + ")");
        }
        return sb.toString();
    }
    
    protected void calcFirstPos(final CMStateSet set) throws CMException {
        if (this.fPosition == -1) {
            set.zeroBits();
        }
        else {
            set.setBit(this.fPosition);
        }
    }
    
    protected void calcLastPos(final CMStateSet set) throws CMException {
        if (this.fPosition == -1) {
            set.zeroBits();
        }
        else {
            set.setBit(this.fPosition);
        }
    }
}
