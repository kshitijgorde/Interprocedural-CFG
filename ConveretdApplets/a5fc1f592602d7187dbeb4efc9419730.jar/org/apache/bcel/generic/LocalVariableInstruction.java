// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

import org.apache.bcel.util.ByteSequence;
import java.io.IOException;
import java.io.DataOutputStream;

public abstract class LocalVariableInstruction extends Instruction implements TypedInstruction, IndexedInstruction
{
    protected int n;
    private short c_tag;
    private short canon_tag;
    
    private final boolean wide() {
        return this.n > 255;
    }
    
    LocalVariableInstruction(final short canon_tag, final short c_tag) {
        this.n = -1;
        this.c_tag = -1;
        this.canon_tag = -1;
        this.canon_tag = canon_tag;
        this.c_tag = c_tag;
    }
    
    LocalVariableInstruction() {
        this.n = -1;
        this.c_tag = -1;
        this.canon_tag = -1;
    }
    
    protected LocalVariableInstruction(final short opcode, final short c_tag, final int n) {
        super(opcode, (short)2);
        this.n = -1;
        this.c_tag = -1;
        this.canon_tag = -1;
        this.c_tag = c_tag;
        this.canon_tag = opcode;
        this.setIndex(n);
    }
    
    public void dump(final DataOutputStream out) throws IOException {
        if (this.wide()) {
            out.writeByte(196);
        }
        out.writeByte(super.opcode);
        if (super.length > 1) {
            if (this.wide()) {
                out.writeShort(this.n);
            }
            else {
                out.writeByte(this.n);
            }
        }
    }
    
    public String toString(final boolean verbose) {
        if ((super.opcode >= 26 && super.opcode <= 45) || (super.opcode >= 59 && super.opcode <= 78)) {
            return super.toString(verbose);
        }
        return super.toString(verbose) + " " + this.n;
    }
    
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        if (wide) {
            this.n = bytes.readUnsignedShort();
            super.length = 4;
        }
        else if ((super.opcode >= 21 && super.opcode <= 25) || (super.opcode >= 54 && super.opcode <= 58)) {
            this.n = bytes.readUnsignedByte();
            super.length = 2;
        }
        else if (super.opcode <= 45) {
            this.n = (super.opcode - 26) % 4;
            super.length = 1;
        }
        else {
            this.n = (super.opcode - 59) % 4;
            super.length = 1;
        }
    }
    
    public final int getIndex() {
        return this.n;
    }
    
    public void setIndex(final int n) {
        if (n < 0 || n > 65535) {
            throw new ClassGenException("Illegal value: " + n);
        }
        this.n = n;
        if (n >= 0 && n <= 3) {
            super.opcode = (short)(this.c_tag + n);
            super.length = 1;
        }
        else {
            super.opcode = this.canon_tag;
            if (this.wide()) {
                super.length = 4;
            }
            else {
                super.length = 2;
            }
        }
    }
    
    public short getCanonicalTag() {
        return this.canon_tag;
    }
    
    public Type getType(final ConstantPoolGen cp) {
        switch (this.canon_tag) {
            case 21:
            case 54: {
                return Type.INT;
            }
            case 22:
            case 55: {
                return Type.LONG;
            }
            case 24:
            case 57: {
                return Type.DOUBLE;
            }
            case 23:
            case 56: {
                return Type.FLOAT;
            }
            case 25:
            case 58: {
                return Type.OBJECT;
            }
            default: {
                throw new ClassGenException("Oops: unknown case in switch" + this.canon_tag);
            }
        }
    }
}
