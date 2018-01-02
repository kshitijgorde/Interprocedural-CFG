// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

import com.ibm.xslt4j.bcel.ExceptionConstants;
import com.ibm.xslt4j.bcel.classfile.ConstantPool;
import com.ibm.xslt4j.bcel.util.ByteSequence;
import java.io.IOException;
import java.io.DataOutputStream;

public class MULTIANEWARRAY extends CPInstruction implements LoadClass, AllocationInstruction, ExceptionThrower
{
    private short dimensions;
    
    MULTIANEWARRAY() {
    }
    
    public MULTIANEWARRAY(final int index, final short dimensions) {
        super((short)197, index);
        if (dimensions < 1) {
            throw new ClassGenException("Invalid dimensions value: " + dimensions);
        }
        this.dimensions = dimensions;
        super.length = 4;
    }
    
    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(super.opcode);
        out.writeShort(super.index);
        out.writeByte(this.dimensions);
    }
    
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.initFromFile(bytes, wide);
        this.dimensions = bytes.readByte();
        super.length = 4;
    }
    
    public final short getDimensions() {
        return this.dimensions;
    }
    
    public String toString(final boolean verbose) {
        return String.valueOf(super.toString(verbose)) + " " + super.index + " " + this.dimensions;
    }
    
    public String toString(final ConstantPool cp) {
        return String.valueOf(super.toString(cp)) + " " + this.dimensions;
    }
    
    public int consumeStack(final ConstantPoolGen cpg) {
        return this.dimensions;
    }
    
    public Class[] getExceptions() {
        final Class[] cs = new Class[2 + ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length];
        System.arraycopy(ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION, 0, cs, 0, ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length);
        cs[ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length + 1] = ExceptionConstants.NEGATIVE_ARRAY_SIZE_EXCEPTION;
        cs[ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length] = ExceptionConstants.ILLEGAL_ACCESS_ERROR;
        return cs;
    }
    
    public ObjectType getLoadClassType(final ConstantPoolGen cpg) {
        Type t = this.getType(cpg);
        if (t instanceof ArrayType) {
            t = ((ArrayType)t).getBasicType();
        }
        return (t instanceof ObjectType) ? ((ObjectType)t) : null;
    }
    
    public void accept(final Visitor v) {
        v.visitLoadClass(this);
        v.visitAllocationInstruction(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitCPInstruction(this);
        v.visitMULTIANEWARRAY(this);
    }
}
