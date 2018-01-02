// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.verifier.structurals;

import org.apache.bcel.generic.InstructionHandle;
import java.util.ArrayList;

public interface InstructionContext
{
    int getTag();
    
    void setTag(final int p0);
    
    boolean execute(final Frame p0, final ArrayList p1, final InstConstraintVisitor p2, final ExecutionVisitor p3);
    
    Frame getOutFrame(final ArrayList p0);
    
    InstructionHandle getInstruction();
    
    InstructionContext[] getSuccessors();
    
    ExceptionHandler[] getExceptionHandlers();
}
