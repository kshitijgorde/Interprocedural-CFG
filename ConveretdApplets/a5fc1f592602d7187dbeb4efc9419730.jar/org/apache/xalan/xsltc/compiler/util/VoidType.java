// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.Instruction;

public final class VoidType extends Type
{
    public String toString() {
        return "void";
    }
    
    public boolean identicalTo(final Type other) {
        return this == other;
    }
    
    public String toSignature() {
        return "V";
    }
    
    public org.apache.bcel.generic.Type toJCType() {
        return null;
    }
    
    public Instruction POP() {
        return InstructionConstants.NOP;
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final Type type) {
        if (type == Type.String) {
            this.translateTo(classGen, methodGen, (StringType)type);
        }
        else {
            final ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", this.toString(), type.toString());
            classGen.getParser().reportError(2, err);
        }
    }
    
    public void translateTo(final ClassGenerator classGen, final MethodGenerator methodGen, final StringType type) {
        final InstructionList il = methodGen.getInstructionList();
        il.append(new PUSH(classGen.getConstantPool(), ""));
    }
    
    public void translateFrom(final ClassGenerator classGen, final MethodGenerator methodGen, final Class clazz) {
        if (!clazz.getName().equals("void")) {
            final ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", this.toString(), clazz.getName());
            classGen.getParser().reportError(2, err);
        }
    }
}
