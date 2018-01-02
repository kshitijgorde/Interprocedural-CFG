// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

import com.ibm.xslt4j.bcel.ExceptionConstants;

public class ARRAYLENGTH extends Instruction implements ExceptionThrower, StackProducer
{
    public ARRAYLENGTH() {
        super((short)190, (short)1);
    }
    
    public Class[] getExceptions() {
        return new Class[] { ExceptionConstants.NULL_POINTER_EXCEPTION };
    }
    
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitStackProducer(this);
        v.visitARRAYLENGTH(this);
    }
}
