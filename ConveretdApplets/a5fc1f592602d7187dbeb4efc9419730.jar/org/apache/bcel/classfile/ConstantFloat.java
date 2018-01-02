// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.classfile;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;

public final class ConstantFloat extends Constant implements ConstantObject
{
    private float bytes;
    
    public ConstantFloat(final float bytes) {
        super((byte)4);
        this.bytes = bytes;
    }
    
    public ConstantFloat(final ConstantFloat c) {
        this(c.getBytes());
    }
    
    ConstantFloat(final DataInputStream file) throws IOException {
        this(file.readFloat());
    }
    
    public void accept(final Visitor v) {
        v.visitConstantFloat(this);
    }
    
    public final void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.tag);
        file.writeFloat(this.bytes);
    }
    
    public final float getBytes() {
        return this.bytes;
    }
    
    public final void setBytes(final float bytes) {
        this.bytes = bytes;
    }
    
    public final String toString() {
        return super.toString() + "(bytes = " + this.bytes + ")";
    }
    
    public Object getConstantValue(final ConstantPool cp) {
        return new Float(this.bytes);
    }
}
