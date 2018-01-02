// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

import org.apache.bcel.util.ByteSequence;
import java.io.IOException;
import java.io.DataOutputStream;

public class RET extends Instruction implements IndexedInstruction, TypedInstruction
{
    private boolean wide;
    private int index;
    
    RET() {
    }
    
    public RET(final int index) {
        super((short)169, (short)2);
        this.setIndex(index);
    }
    
    public void dump(final DataOutputStream out) throws IOException {
        if (this.wide) {
            out.writeByte(196);
        }
        out.writeByte(super.opcode);
        if (this.wide) {
            out.writeShort(this.index);
        }
        else {
            out.writeByte(this.index);
        }
    }
    
    private final void setWide() {
        final boolean wide = this.index > 255;
        this.wide = wide;
        if (wide) {
            super.length = 4;
        }
        else {
            super.length = 2;
        }
    }
    
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        this.wide = wide;
        if (wide) {
            this.index = bytes.readUnsignedShort();
            super.length = 4;
        }
        else {
            this.index = bytes.readUnsignedByte();
            super.length = 2;
        }
    }
    
    public final int getIndex() {
        return this.index;
    }
    
    public final void setIndex(final int n) {
        if (n < 0) {
            throw new ClassGenException("Negative index value: " + n);
        }
        this.index = n;
        this.setWide();
    }
    
    public String toString(final boolean verbose) {
        return super.toString(verbose) + " " + this.index;
    }
    
    public Type getType(final ConstantPoolGen cp) {
        return ReturnaddressType.NO_TARGET;
    }
    
    public void accept(final Visitor v) {
        v.visitRET(this);
    }
}
