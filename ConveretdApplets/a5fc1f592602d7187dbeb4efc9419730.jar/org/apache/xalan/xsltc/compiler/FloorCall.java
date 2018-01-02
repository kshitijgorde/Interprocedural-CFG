// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.INVOKESTATIC;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import java.util.Vector;

final class FloorCall extends FunctionCall
{
    public FloorCall(final QName fname, final Vector arguments) {
        super(fname, arguments);
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        this.argument().translate(classGen, methodGen);
        methodGen.getInstructionList().append(new INVOKESTATIC(classGen.getConstantPool().addMethodref("java.lang.Math", "floor", "(D)D")));
    }
}
