// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.xalan.xsltc.compiler.util.Util;
import org.apache.bcel.generic.InstructionList;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;

class TopLevelElement extends SyntaxTreeNode
{
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        return this.typeCheckContents(stable);
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ErrorMsg msg = new ErrorMsg("NOT_IMPLEMENTED_ERR", this.getClass(), this);
        this.getParser().reportError(2, msg);
    }
    
    public InstructionList compile(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final InstructionList save = methodGen.getInstructionList();
        final InstructionList result;
        methodGen.setInstructionList(result = new InstructionList());
        this.translate(classGen, methodGen);
        methodGen.setInstructionList(save);
        return result;
    }
    
    public void display(final int indent) {
        this.indent(indent);
        Util.println("TopLevelElement");
        this.displayContents(indent + 4);
    }
}
