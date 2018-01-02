// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.verifier.structurals;

import com.ibm.xslt4j.bcel.generic.InstructionHandle;
import com.ibm.xslt4j.bcel.generic.ObjectType;

public class ExceptionHandler
{
    private ObjectType catchtype;
    private InstructionHandle handlerpc;
    
    ExceptionHandler(final ObjectType catch_type, final InstructionHandle handler_pc) {
        this.catchtype = catch_type;
        this.handlerpc = handler_pc;
    }
    
    public ObjectType getExceptionType() {
        return this.catchtype;
    }
    
    public InstructionHandle getHandlerStart() {
        return this.handlerpc;
    }
}
