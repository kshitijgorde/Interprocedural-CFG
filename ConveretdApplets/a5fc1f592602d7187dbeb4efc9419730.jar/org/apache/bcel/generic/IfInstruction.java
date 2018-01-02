// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public abstract class IfInstruction extends BranchInstruction implements StackConsumer
{
    IfInstruction() {
    }
    
    protected IfInstruction(final short opcode, final InstructionHandle target) {
        super(opcode, target);
    }
    
    public abstract IfInstruction negate();
}
