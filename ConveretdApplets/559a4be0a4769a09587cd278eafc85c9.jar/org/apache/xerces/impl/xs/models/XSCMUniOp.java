// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.models;

import org.apache.xerces.impl.dtd.models.CMStateSet;
import org.apache.xerces.impl.dtd.models.CMNode;

public class XSCMUniOp extends CMNode
{
    private CMNode fChild;
    
    public XSCMUniOp(final int type, final CMNode childNode) {
        super(type);
        if (this.type() != 5 && this.type() != 4 && this.type() != 6) {
            throw new RuntimeException("ImplementationMessages.VAL_UST");
        }
        this.fChild = childNode;
    }
    
    final CMNode getChild() {
        return this.fChild;
    }
    
    public boolean isNullable() {
        return this.type() != 6 || this.fChild.isNullable();
    }
    
    protected void calcFirstPos(final CMStateSet toSet) {
        toSet.setTo(this.fChild.firstPos());
    }
    
    protected void calcLastPos(final CMStateSet toSet) {
        toSet.setTo(this.fChild.lastPos());
    }
}
