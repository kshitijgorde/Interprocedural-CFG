// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

import com.ibm.xslt4j.bcel.ExceptionConstants;

public class IREM extends ArithmeticInstruction implements ExceptionThrower
{
    public IREM() {
        super((short)112);
    }
    
    public Class[] getExceptions() {
        return new Class[] { ExceptionConstants.ARITHMETIC_EXCEPTION };
    }
    
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitIREM(this);
    }
}
