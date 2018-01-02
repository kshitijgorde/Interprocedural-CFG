// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.verifier.structurals;

import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.ObjectType;

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
