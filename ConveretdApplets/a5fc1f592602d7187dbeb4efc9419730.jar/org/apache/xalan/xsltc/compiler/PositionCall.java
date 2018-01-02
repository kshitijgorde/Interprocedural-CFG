// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.INVOKEINTERFACE;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.ILOAD;
import org.apache.xalan.xsltc.compiler.util.TestGenerator;
import org.apache.xalan.xsltc.compiler.util.CompareGenerator;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;

final class PositionCall extends FunctionCall
{
    public PositionCall(final QName fname) {
        super(fname);
    }
    
    public boolean hasPositionCall() {
        return true;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final InstructionList il = methodGen.getInstructionList();
        if (methodGen instanceof CompareGenerator) {
            il.append(((CompareGenerator)methodGen).loadCurrentNode());
        }
        else if (methodGen instanceof TestGenerator) {
            il.append(new ILOAD(2));
        }
        else {
            final ConstantPoolGen cpg = classGen.getConstantPool();
            final int index = cpg.addInterfaceMethodref("org.apache.xml.dtm.DTMAxisIterator", "getPosition", "()I");
            il.append(methodGen.loadIterator());
            il.append(new INVOKEINTERFACE(index, 1));
        }
    }
}
