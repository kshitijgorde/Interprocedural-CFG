// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.verifier.structurals;

import org.apache.bcel.generic.InstructionHandle;

public interface Subroutine
{
    InstructionHandle[] getEnteringJsrInstructions();
    
    InstructionHandle getLeavingRET();
    
    InstructionHandle[] getInstructions();
    
    boolean contains(final InstructionHandle p0);
    
    int[] getAccessedLocalsIndices();
    
    int[] getRecursivelyAccessedLocalsIndices();
    
    Subroutine[] subSubs();
}
