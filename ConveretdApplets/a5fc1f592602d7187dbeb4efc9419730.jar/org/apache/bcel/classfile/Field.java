// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.classfile;

import java.io.IOException;
import java.io.DataInputStream;

public final class Field extends FieldOrMethod
{
    public Field(final Field c) {
        super(c);
    }
    
    Field(final DataInputStream file, final ConstantPool constant_pool) throws IOException, ClassFormatError {
        super(file, constant_pool);
    }
    
    public Field(final int access_flags, final int name_index, final int signature_index, final Attribute[] attributes, final ConstantPool constant_pool) {
        super(access_flags, name_index, signature_index, attributes, constant_pool);
    }
    
    public void accept(final Visitor v) {
        v.visitField(this);
    }
    
    public final ConstantValue getConstantValue() {
        for (int i = 0; i < super.attributes_count; ++i) {
            if (super.attributes[i].getTag() == 1) {
                return (ConstantValue)super.attributes[i];
            }
        }
        return null;
    }
    
    public final String toString() {
        String access = Utility.accessToString(super.access_flags);
        access = (access.equals("") ? "" : (access + " "));
        final String signature = Utility.signatureToString(this.getSignature());
        final String name = this.getName();
        final StringBuffer buf = new StringBuffer(access + signature + " " + name);
        final ConstantValue cv = this.getConstantValue();
        if (cv != null) {
            buf.append(" = " + cv);
        }
        for (int i = 0; i < super.attributes_count; ++i) {
            final Attribute a = super.attributes[i];
            if (!(a instanceof ConstantValue)) {
                buf.append(" [" + a.toString() + "]");
            }
        }
        return buf.toString();
    }
    
    public final Field copy(final ConstantPool constant_pool) {
        return (Field)this.copy_(constant_pool);
    }
}
