// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

import com.ibm.xslt4j.bcel.ExceptionConstants;

public class LDIV extends ArithmeticInstruction implements ExceptionThrower
{
    public LDIV() {
        super((short)109);
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
        v.visitLDIV(this);
    }
}