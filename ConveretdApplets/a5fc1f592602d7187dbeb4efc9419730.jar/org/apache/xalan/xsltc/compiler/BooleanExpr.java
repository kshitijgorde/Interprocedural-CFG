// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;

final class BooleanExpr extends Expression
{
    private boolean _value;
    
    public BooleanExpr(final boolean value) {
        this._value = value;
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        return super._type = Type.Boolean;
    }
    
    public String toString() {
        return this._value ? "true()" : "false()";
    }
    
    public boolean getValue() {
        return this._value;
    }
    
    public boolean contextDependent() {
        return false;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(new PUSH(cpg, this._value));
    }
    
    public void translateDesynthesized(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final InstructionList il = methodGen.getInstructionList();
        if (this._value) {
            il.append(InstructionConstants.NOP);
        }
        else {
            super._falseList.add(il.append(new GOTO(null)));
        }
    }
}
