// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

import org.apache.bcel.util.ByteSequence;
import java.io.IOException;
import java.io.DataOutputStream;

public class BIPUSH extends Instruction implements ConstantPushInstruction
{
    private byte b;
    
    BIPUSH() {
    }
    
    public BIPUSH(final byte b) {
        super((short)16, (short)2);
        this.b = b;
    }
    
    public void dump(final DataOutputStream out) throws IOException {
        super.dump(out);
        out.writeByte(this.b);
    }
    
    public String toString(final boolean verbose) {
        return super.toString(verbose) + " " + this.b;
    }
    
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.length = 2;
        this.b = bytes.readByte();
    }
    
    public Number getValue() {
        return new Integer(this.b);
    }
    
    public Type getType(final ConstantPoolGen cp) {
        return Type.BYTE;
    }
    
    public void accept(final Visitor v) {
        v.visitPushInstruction(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitConstantPushInstruction(this);
        v.visitBIPUSH(this);
    }
}
