// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dtd.models;

public class CMAny extends CMNode
{
    private int fType;
    private String fURI;
    private int fPosition;
    
    public CMAny(final int type, final String uri, final int position) {
        super(type);
        this.fPosition = -1;
        this.fType = type;
        this.fURI = uri;
        this.fPosition = position;
    }
    
    final int getType() {
        return this.fType;
    }
    
    final String getURI() {
        return this.fURI;
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
        final StringBuffer strRet = new StringBuffer();
        strRet.append("(");
        strRet.append("##any:uri=");
        strRet.append(this.fURI);
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
