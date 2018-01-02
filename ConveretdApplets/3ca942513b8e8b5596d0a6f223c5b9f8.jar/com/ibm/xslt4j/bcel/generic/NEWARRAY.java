// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

import com.ibm.xslt4j.bcel.ExceptionConstants;
import com.ibm.xslt4j.bcel.util.ByteSequence;
import com.ibm.xslt4j.bcel.Constants;
import java.io.IOException;
import java.io.DataOutputStream;

public class NEWARRAY extends Instruction implements AllocationInstruction, ExceptionThrower, StackProducer
{
    private byte type;
    
    NEWARRAY() {
    }
    
    public NEWARRAY(final byte type) {
        super((short)188, (short)2);
        this.type = type;
    }
    
    public NEWARRAY(final BasicType type) {
        this(type.getType());
    }
    
    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(super.opcode);
        out.writeByte(this.type);
    }
    
    public final byte getTypecode() {
        return this.type;
    }
    
    public final Type getType() {
        return new ArrayType(BasicType.getType(this.type), 1);
    }
    
    public String toString(final boolean verbose) {
        return String.valueOf(super.toString(verbose)) + " " + Constants.TYPE_NAMES[this.type];
    }
    
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        this.type = bytes.readByte();
        super.length = 2;
    }
    
    public Class[] getExceptions() {
        return new Class[] { ExceptionConstants.NEGATIVE_ARRAY_SIZE_EXCEPTION };
    }
    
    public void accept(final Visitor v) {
        v.visitAllocationInstruction(this);
        v.visitExceptionThrower(this);
        v.visitStackProducer(this);
        v.visitNEWARRAY(this);
    }
}
