// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public abstract class GotoInstruction extends BranchInstruction implements UnconditionalBranch
{
    GotoInstruction(final short opcode, final InstructionHandle target) {
        super(opcode, target);
    }
    
    GotoInstruction() {
    }
}
