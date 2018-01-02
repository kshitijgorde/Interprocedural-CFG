// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

import com.ibm.xslt4j.bcel.util.ByteSequence;
import com.ibm.xslt4j.bcel.classfile.Constant;
import com.ibm.xslt4j.bcel.Constants;
import com.ibm.xslt4j.bcel.classfile.ConstantClass;
import com.ibm.xslt4j.bcel.classfile.ConstantPool;
import java.io.IOException;
import java.io.DataOutputStream;

public abstract class CPInstruction extends Instruction implements TypedInstruction, IndexedInstruction
{
    protected int index;
    
    CPInstruction() {
    }
    
    protected CPInstruction(final short opcode, final int index) {
        super(opcode, (short)3);
        this.setIndex(index);
    }
    
    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(super.opcode);
        out.writeShort(this.index);
    }
    
    public String toString(final boolean verbose) {
        return String.valueOf(super.toString(verbose)) + " " + this.index;
    }
    
    public String toString(final ConstantPool cp) {
        final Constant c = cp.getConstant(this.index);
        String str = cp.constantToString(c);
        if (c instanceof ConstantClass) {
            str = str.replace('.', '/');
        }
        return String.valueOf(Constants.OPCODE_NAMES[super.opcode]) + " " + str;
    }
    
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        this.setIndex(bytes.readUnsignedShort());
        super.length = 3;
    }
    
    public final int getIndex() {
        return this.index;
    }
    
    public void setIndex(final int index) {
        if (index < 0) {
            throw new ClassGenException("Negative index value: " + index);
        }
        this.index = index;
    }
    
    public Type getType(final ConstantPoolGen cpg) {
        final ConstantPool cp = cpg.getConstantPool();
        String name = cp.getConstantString(this.index, (byte)7);
        if (!name.startsWith("[")) {
            name = "L" + name + ";";
        }
        return Type.getType(name);
    }
}
