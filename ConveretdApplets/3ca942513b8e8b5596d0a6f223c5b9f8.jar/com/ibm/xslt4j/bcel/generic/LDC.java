// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

import com.ibm.xslt4j.bcel.ExceptionConstants;
import com.ibm.xslt4j.bcel.classfile.Constant;
import com.ibm.xslt4j.bcel.classfile.ConstantInteger;
import com.ibm.xslt4j.bcel.classfile.ConstantFloat;
import com.ibm.xslt4j.bcel.classfile.ConstantUtf8;
import com.ibm.xslt4j.bcel.classfile.ConstantString;
import com.ibm.xslt4j.bcel.util.ByteSequence;
import java.io.IOException;
import java.io.DataOutputStream;

public class LDC extends CPInstruction implements PushInstruction, ExceptionThrower, TypedInstruction
{
    LDC() {
    }
    
    public LDC(final int index) {
        super((short)19, index);
        this.setSize();
    }
    
    protected final void setSize() {
        if (super.index <= 255) {
            super.opcode = 18;
            super.length = 2;
        }
        else {
            super.opcode = 19;
            super.length = 3;
        }
    }
    
    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(super.opcode);
        if (super.length == 2) {
            out.writeByte(super.index);
        }
        else {
            out.writeShort(super.index);
        }
    }
    
    public final void setIndex(final int index) {
        super.setIndex(index);
        this.setSize();
    }
    
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.length = 2;
        super.index = bytes.readUnsignedByte();
    }
    
    public Object getValue(final ConstantPoolGen cpg) {
        Constant c = cpg.getConstantPool().getConstant(super.index);
        switch (c.getTag()) {
            case 8: {
                final int i = ((ConstantString)c).getStringIndex();
                c = cpg.getConstantPool().getConstant(i);
                return ((ConstantUtf8)c).getBytes();
            }
            case 4: {
                return new Float(((ConstantFloat)c).getBytes());
            }
            case 3: {
                return new Integer(((ConstantInteger)c).getBytes());
            }
            default: {
                throw new RuntimeException("Unknown or invalid constant type at " + super.index);
            }
        }
    }
    
    public Type getType(final ConstantPoolGen cpg) {
        switch (cpg.getConstantPool().getConstant(super.index).getTag()) {
            case 8: {
                return Type.STRING;
            }
            case 4: {
                return Type.FLOAT;
            }
            case 3: {
                return Type.INT;
            }
            default: {
                throw new RuntimeException("Unknown or invalid constant type at " + super.index);
            }
        }
    }
    
    public Class[] getExceptions() {
        return ExceptionConstants.EXCS_STRING_RESOLUTION;
    }
    
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitCPInstruction(this);
        v.visitLDC(this);
    }
}
