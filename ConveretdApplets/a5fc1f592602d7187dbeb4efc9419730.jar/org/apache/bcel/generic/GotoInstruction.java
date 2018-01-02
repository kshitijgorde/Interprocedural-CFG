// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public abstract class GotoInstruction extends BranchInstruction implements UnconditionalBranch
{
    GotoInstruction(final short opcode, final InstructionHandle target) {
        super(opcode, target);
    }
    
    GotoInstruction() {
    }
}
