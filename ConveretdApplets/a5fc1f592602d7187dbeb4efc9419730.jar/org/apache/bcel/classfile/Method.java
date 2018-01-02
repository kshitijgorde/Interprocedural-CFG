// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.classfile;

import java.io.IOException;
import java.io.DataInputStream;

public final class Method extends FieldOrMethod
{
    public Method() {
    }
    
    public Method(final Method c) {
        super(c);
    }
    
    Method(final DataInputStream file, final ConstantPool constant_pool) throws IOException, ClassFormatError {
        super(file, constant_pool);
    }
    
    public Method(final int access_flags, final int name_index, final int signature_index, final Attribute[] attributes, final ConstantPool constant_pool) {
        super(access_flags, name_index, signature_index, attributes, constant_pool);
    }
    
    public void accept(final Visitor v) {
        v.visitMethod(this);
    }
    
    public final Code getCode() {
        for (int i = 0; i < super.attributes_count; ++i) {
            if (super.attributes[i] instanceof Code) {
                return (Code)super.attributes[i];
            }
        }
        return null;
    }
    
    public final ExceptionTable getExceptionTable() {
        for (int i = 0; i < super.attributes_count; ++i) {
            if (super.attributes[i] instanceof ExceptionTable) {
                return (ExceptionTable)super.attributes[i];
            }
        }
        return null;
    }
    
    public final LocalVariableTable getLocalVariableTable() {
        final Code code = this.getCode();
        if (code != null) {
            return code.getLocalVariableTable();
        }
        return null;
    }
    
    public final LineNumberTable getLineNumberTable() {
        final Code code = this.getCode();
        if (code != null) {
            return code.getLineNumberTable();
        }
        return null;
    }
    
    public final String toString() {
        final String access = Utility.accessToString(super.access_flags);
        ConstantUtf8 c = (ConstantUtf8)super.constant_pool.getConstant(super.signature_index, (byte)1);
        String signature = c.getBytes();
        c = (ConstantUtf8)super.constant_pool.getConstant(super.name_index, (byte)1);
        final String name = c.getBytes();
        signature = Utility.methodSignatureToString(signature, name, access, true, this.getLocalVariableTable());
        final StringBuffer buf = new StringBuffer(signature);
        for (int i = 0; i < super.attributes_count; ++i) {
            final Attribute a = super.attributes[i];
            if (!(a instanceof Code) && !(a instanceof ExceptionTable)) {
                buf.append(" [" + a.toString() + "]");
            }
        }
        final ExceptionTable e = this.getExceptionTable();
        if (e != null) {
            final String str = e.toString();
            if (!str.equals("")) {
                buf.append("\n\t\tthrows " + str);
            }
        }
        return buf.toString();
    }
    
    public final Method copy(final ConstantPool constant_pool) {
        return (Method)this.copy_(constant_pool);
    }
}
