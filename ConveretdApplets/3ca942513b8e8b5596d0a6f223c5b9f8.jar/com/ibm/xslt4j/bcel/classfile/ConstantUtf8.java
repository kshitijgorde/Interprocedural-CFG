// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.classfile;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;

public final class ConstantUtf8 extends Constant
{
    private String bytes;
    
    public ConstantUtf8(final ConstantUtf8 c) {
        this(c.getBytes());
    }
    
    ConstantUtf8(final DataInputStream file) throws IOException {
        super((byte)1);
        this.bytes = file.readUTF();
    }
    
    public ConstantUtf8(final String bytes) {
        super((byte)1);
        this.bytes = bytes;
    }
    
    public void accept(final Visitor v) {
        v.visitConstantUtf8(this);
    }
    
    public final void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.tag);
        file.writeUTF(this.bytes);
    }
    
    public final String getBytes() {
        return this.bytes;
    }
    
    public final void setBytes(final String bytes) {
        this.bytes = bytes;
    }
    
    public final String toString() {
        return String.valueOf(super.toString()) + "(\"" + Utility.replace(this.bytes, "\n", "\\n") + "\")";
    }
}
