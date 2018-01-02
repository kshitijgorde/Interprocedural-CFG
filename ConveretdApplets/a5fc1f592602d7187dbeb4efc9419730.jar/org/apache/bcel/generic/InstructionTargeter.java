// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public interface InstructionTargeter
{
    boolean containsTarget(final InstructionHandle p0);
    
    void updateTarget(final InstructionHandle p0, final InstructionHandle p1);
}
