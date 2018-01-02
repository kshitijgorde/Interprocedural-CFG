// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.BranchHandle;
import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import java.util.Vector;

final class NotCall extends FunctionCall
{
    public NotCall(final QName fname, final Vector arguments) {
        super(fname, arguments);
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final InstructionList il = methodGen.getInstructionList();
        this.argument().translate(classGen, methodGen);
        il.append(InstructionConstants.ICONST_1);
        il.append(InstructionConstants.IXOR);
    }
    
    public void translateDesynthesized(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final InstructionList il = methodGen.getInstructionList();
        final Expression exp = this.argument();
        exp.translateDesynthesized(classGen, methodGen);
        final BranchHandle gotoh = il.append(new GOTO(null));
        super._trueList = exp._falseList;
        (super._falseList = exp._trueList).add(gotoh);
    }
}
