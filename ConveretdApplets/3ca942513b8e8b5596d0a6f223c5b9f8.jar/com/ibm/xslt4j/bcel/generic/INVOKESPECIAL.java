// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

import com.ibm.xslt4j.bcel.ExceptionConstants;

public class INVOKESPECIAL extends InvokeInstruction
{
    INVOKESPECIAL() {
    }
    
    public INVOKESPECIAL(final int index) {
        super((short)183, index);
    }
    
    public Class[] getExceptions() {
        final Class[] cs = new Class[4 + ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length];
        System.arraycopy(ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION, 0, cs, 0, ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length);
        cs[ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length + 3] = ExceptionConstants.UNSATISFIED_LINK_ERROR;
        cs[ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length + 2] = ExceptionConstants.ABSTRACT_METHOD_ERROR;
        cs[ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length + 1] = ExceptionConstants.INCOMPATIBLE_CLASS_CHANGE_ERROR;
        cs[ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length] = ExceptionConstants.NULL_POINTER_EXCEPTION;
        return cs;
    }
    
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitStackConsumer(this);
        v.visitStackProducer(this);
        v.visitLoadClass(this);
        v.visitCPInstruction(this);
        v.visitFieldOrMethod(this);
        v.visitInvokeInstruction(this);
        v.visitINVOKESPECIAL(this);
    }
}
