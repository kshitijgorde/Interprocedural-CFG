// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.classfile;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;

public final class ConstantClass extends Constant implements ConstantObject
{
    private int name_index;
    
    public ConstantClass(final ConstantClass c) {
        this(c.getNameIndex());
    }
    
    ConstantClass(final DataInputStream file) throws IOException {
        this(file.readUnsignedShort());
    }
    
    public ConstantClass(final int name_index) {
        super((byte)7);
        this.name_index = name_index;
    }
    
    public void accept(final Visitor v) {
        v.visitConstantClass(this);
    }
    
    public final void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.tag);
        file.writeShort(this.name_index);
    }
    
    public final int getNameIndex() {
        return this.name_index;
    }
    
    public final void setNameIndex(final int name_index) {
        this.name_index = name_index;
    }
    
    public Object getConstantValue(final ConstantPool cp) {
        final Constant c = cp.getConstant(this.name_index, (byte)1);
        return ((ConstantUtf8)c).getBytes();
    }
    
    public String getBytes(final ConstantPool cp) {
        return (String)this.getConstantValue(cp);
    }
    
    public final String toString() {
        return String.valueOf(super.toString()) + "(name_index = " + this.name_index + ")";
    }
}
