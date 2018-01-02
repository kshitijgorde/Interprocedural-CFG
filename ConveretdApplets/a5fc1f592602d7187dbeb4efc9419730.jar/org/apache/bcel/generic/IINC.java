// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

import org.apache.bcel.util.ByteSequence;
import java.io.IOException;
import java.io.DataOutputStream;

public class IINC extends LocalVariableInstruction
{
    private boolean wide;
    private int c;
    
    IINC() {
    }
    
    public IINC(final int n, final int c) {
        super.opcode = 132;
        super.length = 3;
        this.setIndex(n);
        this.setIncrement(c);
    }
    
    public void dump(final DataOutputStream out) throws IOException {
        if (this.wide) {
            out.writeByte(196);
        }
        out.writeByte(super.opcode);
        if (this.wide) {
            out.writeShort(super.n);
            out.writeShort(this.c);
        }
        else {
            out.writeByte(super.n);
            out.writeByte(this.c);
        }
    }
    
    private final void setWide() {
        final boolean wide = super.n > 65535 || Math.abs(this.c) > 127;
        this.wide = wide;
        if (wide) {
            super.length = 6;
        }
        else {
            super.length = 3;
        }
    }
    
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        this.wide = wide;
        if (wide) {
            super.length = 6;
            super.n = bytes.readUnsignedShort();
            this.c = bytes.readShort();
        }
        else {
            super.length = 3;
            super.n = bytes.readUnsignedByte();
            this.c = bytes.readByte();
        }
    }
    
    public String toString(final boolean verbose) {
        return super.toString(verbose) + " " + this.c;
    }
    
    public final void setIndex(final int n) {
        if (n < 0) {
            throw new ClassGenException("Negative index value: " + n);
        }
        super.n = n;
        this.setWide();
    }
    
    public final int getIncrement() {
        return this.c;
    }
    
    public final void setIncrement(final int c) {
        this.c = c;
        this.setWide();
    }
    
    public Type getType(final ConstantPoolGen cp) {
        return Type.INT;
    }
    
    public void accept(final Visitor v) {
        v.visitLocalVariableInstruction(this);
        v.visitIINC(this);
    }
}
