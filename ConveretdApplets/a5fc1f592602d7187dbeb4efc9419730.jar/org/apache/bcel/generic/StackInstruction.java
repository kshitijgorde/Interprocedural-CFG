// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public abstract class StackInstruction extends Instruction
{
    StackInstruction() {
    }
    
    protected StackInstruction(final short opcode) {
        super(opcode, (short)1);
    }
    
    public Type getType(final ConstantPoolGen cp) {
        return Type.UNKNOWN;
    }
}
