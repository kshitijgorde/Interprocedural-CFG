// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.ConstantUtf8;
import org.apache.bcel.classfile.ConstantNameAndType;
import org.apache.bcel.classfile.ConstantCP;

public abstract class FieldOrMethod extends CPInstruction implements LoadClass
{
    FieldOrMethod() {
    }
    
    protected FieldOrMethod(final short opcode, final int index) {
        super(opcode, index);
    }
    
    public String getSignature(final ConstantPoolGen cpg) {
        final ConstantPool cp = cpg.getConstantPool();
        final ConstantCP cmr = (ConstantCP)cp.getConstant(super.index);
        final ConstantNameAndType cnat = (ConstantNameAndType)cp.getConstant(cmr.getNameAndTypeIndex());
        return ((ConstantUtf8)cp.getConstant(cnat.getSignatureIndex())).getBytes();
    }
    
    public String getName(final ConstantPoolGen cpg) {
        final ConstantPool cp = cpg.getConstantPool();
        final ConstantCP cmr = (ConstantCP)cp.getConstant(super.index);
        final ConstantNameAndType cnat = (ConstantNameAndType)cp.getConstant(cmr.getNameAndTypeIndex());
        return ((ConstantUtf8)cp.getConstant(cnat.getNameIndex())).getBytes();
    }
    
    public String getClassName(final ConstantPoolGen cpg) {
        final ConstantPool cp = cpg.getConstantPool();
        final ConstantCP cmr = (ConstantCP)cp.getConstant(super.index);
        return cp.getConstantString(cmr.getClassIndex(), (byte)7).replace('/', '.');
    }
    
    public ObjectType getClassType(final ConstantPoolGen cpg) {
        return new ObjectType(this.getClassName(cpg));
    }
    
    public ObjectType getLoadClassType(final ConstantPoolGen cpg) {
        return this.getClassType(cpg);
    }
}
