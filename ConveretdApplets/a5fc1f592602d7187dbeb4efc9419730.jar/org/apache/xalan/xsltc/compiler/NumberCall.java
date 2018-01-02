// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.InstructionList;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import java.util.Vector;

final class NumberCall extends FunctionCall
{
    public NumberCall(final QName fname, final Vector arguments) {
        super(fname, arguments);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        if (this.argumentCount() > 0) {
            this.argument().typeCheck(stable);
        }
        return super._type = Type.Real;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final InstructionList il = methodGen.getInstructionList();
        Type targ;
        if (this.argumentCount() == 0) {
            il.append(methodGen.loadContextNode());
            targ = Type.Node;
        }
        else {
            final Expression arg = this.argument();
            arg.translate(classGen, methodGen);
            arg.startIterator(classGen, methodGen);
            targ = arg.getType();
        }
        if (!targ.identicalTo(Type.Real)) {
            targ.translateTo(classGen, methodGen, Type.Real);
        }
    }
}
