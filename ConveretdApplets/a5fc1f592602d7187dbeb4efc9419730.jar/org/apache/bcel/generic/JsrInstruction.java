// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public abstract class JsrInstruction extends BranchInstruction implements UnconditionalBranch, TypedInstruction, StackProducer
{
    JsrInstruction(final short opcode, final InstructionHandle target) {
        super(opcode, target);
    }
    
    JsrInstruction() {
    }
    
    public Type getType(final ConstantPoolGen cp) {
        return new ReturnaddressType(this.physicalSuccessor());
    }
    
    public InstructionHandle physicalSuccessor() {
        InstructionHandle ih;
        for (ih = super.target; ih.getPrev() != null; ih = ih.getPrev()) {}
        while (ih.getInstruction() != this) {
            ih = ih.getNext();
        }
        final InstructionHandle toThis = ih;
        while (ih != null) {
            ih = ih.getNext();
            if (ih != null && ih.getInstruction() == this) {
                throw new RuntimeException("physicalSuccessor() called on a shared JsrInstruction.");
            }
        }
        return toThis.getNext();
    }
}
