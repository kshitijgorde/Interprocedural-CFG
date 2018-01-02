// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class FCONST extends Instruction implements ConstantPushInstruction, TypedInstruction
{
    private float value;
    
    FCONST() {
    }
    
    public FCONST(final float f) {
        super((short)11, (short)1);
        if (f == 0.0) {
            super.opcode = 11;
        }
        else if (f == 1.0) {
            super.opcode = 12;
        }
        else {
            if (f != 2.0) {
                throw new ClassGenException("FCONST can be used only for 0.0, 1.0 and 2.0: " + f);
            }
            super.opcode = 13;
        }
        this.value = f;
    }
    
    public Number getValue() {
        return new Float(this.value);
    }
    
    public Type getType(final ConstantPoolGen cp) {
        return Type.FLOAT;
    }
    
    public void accept(final Visitor v) {
        v.visitPushInstruction(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitConstantPushInstruction(this);
        v.visitFCONST(this);
    }
}
