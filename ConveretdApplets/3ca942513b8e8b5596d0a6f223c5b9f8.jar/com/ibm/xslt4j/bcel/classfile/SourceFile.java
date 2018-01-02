// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.classfile;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;

public final class SourceFile extends Attribute
{
    private int sourcefile_index;
    
    public SourceFile(final SourceFile c) {
        this(c.getNameIndex(), c.getLength(), c.getSourceFileIndex(), c.getConstantPool());
    }
    
    SourceFile(final int name_index, final int length, final DataInputStream file, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, file.readUnsignedShort(), constant_pool);
    }
    
    public SourceFile(final int name_index, final int length, final int sourcefile_index, final ConstantPool constant_pool) {
        super((byte)0, name_index, length, constant_pool);
        this.sourcefile_index = sourcefile_index;
    }
    
    public void accept(final Visitor v) {
        v.visitSourceFile(this);
    }
    
    public final void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(this.sourcefile_index);
    }
    
    public final int getSourceFileIndex() {
        return this.sourcefile_index;
    }
    
    public final void setSourceFileIndex(final int sourcefile_index) {
        this.sourcefile_index = sourcefile_index;
    }
    
    public final String getSourceFileName() {
        final ConstantUtf8 c = (ConstantUtf8)super.constant_pool.getConstant(this.sourcefile_index, (byte)1);
        return c.getBytes();
    }
    
    public final String toString() {
        return "SourceFile(" + this.getSourceFileName() + ")";
    }
    
    public Attribute copy(final ConstantPool constant_pool) {
        return (SourceFile)this.clone();
    }
}
