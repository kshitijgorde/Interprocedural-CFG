// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.common;

import org.apache.xerces.utils.StringPool;
import org.apache.xerces.utils.QName;

public class CMLeaf extends CMNode
{
    private QName fElement;
    private int fPosition;
    
    public CMLeaf(final QName values, final int fPosition) throws CMException {
        super(0);
        this.fElement = new QName();
        this.fPosition = -1;
        this.fElement.setValues(values);
        this.fPosition = fPosition;
    }
    
    public CMLeaf(final QName values) throws CMException {
        super(0);
        this.fElement = new QName();
        this.fPosition = -1;
        this.fElement.setValues(values);
    }
    
    final QName getElement() {
        return this.fElement;
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
        final StringBuffer sb = new StringBuffer(this.fElement.toString());
        sb.append(" (");
        sb.append(stringPool.toString(this.fElement.uri));
        sb.append(',');
        sb.append(stringPool.toString(this.fElement.localpart));
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
