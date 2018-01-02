// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.INVOKESTATIC;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import java.util.Vector;

final class GenerateIdCall extends FunctionCall
{
    public GenerateIdCall(final QName fname, final Vector arguments) {
        super(fname, arguments);
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final InstructionList il = methodGen.getInstructionList();
        if (this.argumentCount() == 0) {
            il.append(methodGen.loadContextNode());
        }
        else {
            this.argument().translate(classGen, methodGen);
        }
        final ConstantPoolGen cpg = classGen.getConstantPool();
        il.append(new INVOKESTATIC(cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "generate_idF", "(I)Ljava/lang/String;")));
    }
}