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

final class LastCall extends FunctionCall
{
    public LastCall(final QName fname) {
        super(fname);
    }
    
    public boolean hasPositionCall() {
        return true;
    }
    
    public boolean hasLastCall() {
        return true;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final InstructionList il = methodGen.getInstructionList();
        if (methodGen instanceof CompareGenerator) {
            il.append(((CompareGenerator)methodGen).loadLastNode());
        }
        else if (methodGen instanceof TestGenerator) {
            il.append(new ILOAD(3));
        }
        else {
            final ConstantPoolGen cpg = classGen.getConstantPool();
            final int getLast = cpg.addInterfaceMethodref("org.apache.xml.dtm.DTMAxisIterator", "getLast", "()I");
            il.append(methodGen.loadIterator());
            il.append(new INVOKEINTERFACE(getLast, 1));
        }
    }
}
