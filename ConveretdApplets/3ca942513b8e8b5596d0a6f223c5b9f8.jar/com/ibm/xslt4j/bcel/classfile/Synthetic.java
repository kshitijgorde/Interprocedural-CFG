// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.classfile;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;

public final class Synthetic extends Attribute
{
    private byte[] bytes;
    
    public Synthetic(final Synthetic c) {
        this(c.getNameIndex(), c.getLength(), c.getBytes(), c.getConstantPool());
    }
    
    public Synthetic(final int name_index, final int length, final byte[] bytes, final ConstantPool constant_pool) {
        super((byte)7, name_index, length, constant_pool);
        this.bytes = bytes;
    }
    
    Synthetic(final int name_index, final int length, final DataInputStream file, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, (byte[])null, constant_pool);
        if (length > 0) {
            file.readFully(this.bytes = new byte[length]);
            System.err.println("Synthetic attribute with length > 0");
        }
    }
    
    public void accept(final Visitor v) {
        v.visitSynthetic(this);
    }
    
    public final void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        if (super.length > 0) {
            file.write(this.bytes, 0, super.length);
        }
    }
    
    public final byte[] getBytes() {
        return this.bytes;
    }
    
    public final void setBytes(final byte[] bytes) {
        this.bytes = bytes;
    }
    
    public final String toString() {
        final StringBuffer buf = new StringBuffer("Synthetic");
        if (super.length > 0) {
            buf.append(" " + Utility.toHexString(this.bytes));
        }
        return buf.toString();
    }
    
    public Attribute copy(final ConstantPool constant_pool) {
        final Synthetic c = (Synthetic)this.clone();
        if (this.bytes != null) {
            c.bytes = this.bytes.clone();
        }
        c.constant_pool = constant_pool;
        return c;
    }
}
