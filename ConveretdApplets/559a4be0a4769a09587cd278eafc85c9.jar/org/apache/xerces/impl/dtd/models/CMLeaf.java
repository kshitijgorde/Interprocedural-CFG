// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dtd.models;

import org.apache.xerces.xni.QName;

public class CMLeaf extends CMNode
{
    private QName fElement;
    private int fPosition;
    
    public CMLeaf(final QName element, final int position) {
        super(0);
        this.fElement = new QName();
        this.fPosition = -1;
        this.fElement.setValues(element);
        this.fPosition = position;
    }
    
    public CMLeaf(final QName element) {
        super(0);
        this.fElement = new QName();
        this.fPosition = -1;
        this.fElement.setValues(element);
    }
    
    final QName getElement() {
        return this.fElement;
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
        final StringBuffer strRet = new StringBuffer(this.fElement.toString());
        strRet.append(" (");
        strRet.append(this.fElement.uri);
        strRet.append(',');
        strRet.append(this.fElement.localpart);
        strRet.append(')');
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
