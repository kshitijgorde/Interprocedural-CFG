// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.verifier.structurals;

import com.ibm.xslt4j.bcel.generic.InstructionHandle;

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
