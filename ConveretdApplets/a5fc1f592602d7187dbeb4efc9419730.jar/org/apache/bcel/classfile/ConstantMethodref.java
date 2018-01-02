// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.classfile;

import java.io.IOException;
import java.io.DataInputStream;

public final class ConstantMethodref extends ConstantCP
{
    public ConstantMethodref(final ConstantMethodref c) {
        super((byte)10, c.getClassIndex(), c.getNameAndTypeIndex());
    }
    
    ConstantMethodref(final DataInputStream file) throws IOException {
        super((byte)10, file);
    }
    
    public ConstantMethodref(final int class_index, final int name_and_type_index) {
        super((byte)10, class_index, name_and_type_index);
    }
    
    public void accept(final Visitor v) {
        v.visitConstantMethodref(this);
    }
}
