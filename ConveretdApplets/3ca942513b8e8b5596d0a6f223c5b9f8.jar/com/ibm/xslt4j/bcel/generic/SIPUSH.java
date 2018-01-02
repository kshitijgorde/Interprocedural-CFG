// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

import com.ibm.xslt4j.bcel.util.ByteSequence;
import java.io.IOException;
import java.io.DataOutputStream;

public class SIPUSH extends Instruction implements ConstantPushInstruction
{
    private short b;
    
    SIPUSH() {
    }
    
    public SIPUSH(final short b) {
        super((short)17, (short)3);
        this.b = b;
    }
    
    public void dump(final DataOutputStream out) throws IOException {
        super.dump(out);
        out.writeShort(this.b);
    }
    
    public String toString(final boolean verbose) {
        return String.valueOf(super.toString(verbose)) + " " + this.b;
    }
    
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.length = 3;
        this.b = bytes.readShort();
    }
    
    public Number getValue() {
        return new Integer(this.b);
    }
    
    public Type getType(final ConstantPoolGen cp) {
        return Type.SHORT;
    }
    
    public void accept(final Visitor v) {
        v.visitPushInstruction(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitConstantPushInstruction(this);
        v.visitSIPUSH(this);
    }
}
