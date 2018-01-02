// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

import com.ibm.xslt4j.bcel.ExceptionConstants;

public class PUTSTATIC extends FieldInstruction implements ExceptionThrower, PopInstruction
{
    PUTSTATIC() {
    }
    
    public PUTSTATIC(final int index) {
        super((short)179, index);
    }
    
    public int consumeStack(final ConstantPoolGen cpg) {
        return this.getFieldSize(cpg);
    }
    
    public Class[] getExceptions() {
        final Class[] cs = new Class[1 + ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length];
        System.arraycopy(ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION, 0, cs, 0, ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length);
        cs[ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length] = ExceptionConstants.INCOMPATIBLE_CLASS_CHANGE_ERROR;
        return cs;
    }
    
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitStackConsumer(this);
        v.visitPopInstruction(this);
        v.visitTypedInstruction(this);
        v.visitLoadClass(this);
        v.visitCPInstruction(this);
        v.visitFieldOrMethod(this);
        v.visitFieldInstruction(this);
        v.visitPUTSTATIC(this);
    }
}
