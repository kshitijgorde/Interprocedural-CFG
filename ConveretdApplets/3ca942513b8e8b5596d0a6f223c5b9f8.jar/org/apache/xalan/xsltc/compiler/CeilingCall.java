// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.INVOKESTATIC;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import java.util.Vector;

final class CeilingCall extends FunctionCall
{
    public CeilingCall(final QName fname, final Vector arguments) {
        super(fname, arguments);
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        this.argument(0).translate(classGen, methodGen);
        il.append(new INVOKESTATIC(cpg.addMethodref("java.lang.Math", "ceil", "(D)D")));
    }
}