// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.classfile;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;

public final class ConstantInteger extends Constant implements ConstantObject
{
    private int bytes;
    
    public ConstantInteger(final int bytes) {
        super((byte)3);
        this.bytes = bytes;
    }
    
    public ConstantInteger(final ConstantInteger c) {
        this(c.getBytes());
    }
    
    ConstantInteger(final DataInputStream file) throws IOException {
        this(file.readInt());
    }
    
    public void accept(final Visitor v) {
        v.visitConstantInteger(this);
    }
    
    public final void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.tag);
        file.writeInt(this.bytes);
    }
    
    public final int getBytes() {
        return this.bytes;
    }
    
    public final void setBytes(final int bytes) {
        this.bytes = bytes;
    }
    
    public final String toString() {
        return String.valueOf(super.toString()) + "(bytes = " + this.bytes + ")";
    }
    
    public Object getConstantValue(final ConstantPool cp) {
        return new Integer(this.bytes);
    }
}
