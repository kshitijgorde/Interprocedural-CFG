// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.ConstantPool;

public abstract class FieldInstruction extends FieldOrMethod implements TypedInstruction
{
    FieldInstruction() {
    }
    
    protected FieldInstruction(final short opcode, final int index) {
        super(opcode, index);
    }
    
    public String toString(final ConstantPool cp) {
        return Constants.OPCODE_NAMES[super.opcode] + " " + cp.constantToString(super.index, (byte)9);
    }
    
    protected int getFieldSize(final ConstantPoolGen cpg) {
        return this.getType(cpg).getSize();
    }
    
    public Type getType(final ConstantPoolGen cpg) {
        return this.getFieldType(cpg);
    }
    
    public Type getFieldType(final ConstantPoolGen cpg) {
        return Type.getType(this.getSignature(cpg));
    }
    
    public String getFieldName(final ConstantPoolGen cpg) {
        return this.getName(cpg);
    }
}
