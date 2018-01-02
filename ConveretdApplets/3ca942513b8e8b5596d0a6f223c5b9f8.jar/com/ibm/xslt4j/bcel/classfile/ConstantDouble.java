// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.classfile;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;

public final class ConstantDouble extends Constant implements ConstantObject
{
    private double bytes;
    
    public ConstantDouble(final double bytes) {
        super((byte)6);
        this.bytes = bytes;
    }
    
    public ConstantDouble(final ConstantDouble c) {
        this(c.getBytes());
    }
    
    ConstantDouble(final DataInputStream file) throws IOException {
        this(file.readDouble());
    }
    
    public void accept(final Visitor v) {
        v.visitConstantDouble(this);
    }
    
    public final void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.tag);
        file.writeDouble(this.bytes);
    }
    
    public final double getBytes() {
        return this.bytes;
    }
    
    public final void setBytes(final double bytes) {
        this.bytes = bytes;
    }
    
    public final String toString() {
        return String.valueOf(super.toString()) + "(bytes = " + this.bytes + ")";
    }
    
    public Object getConstantValue(final ConstantPool cp) {
        return new Double(this.bytes);
    }
}
