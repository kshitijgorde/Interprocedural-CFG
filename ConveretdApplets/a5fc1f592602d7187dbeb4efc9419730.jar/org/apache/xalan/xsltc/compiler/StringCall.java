// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.InstructionList;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import org.apache.xalan.xsltc.compiler.util.Type;
import java.util.Vector;

final class StringCall extends FunctionCall
{
    public StringCall(final QName fname, final Vector arguments) {
        super(fname, arguments);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        final int argc = this.argumentCount();
        if (argc > 1) {
            final ErrorMsg err = new ErrorMsg("ILLEGAL_ARG_ERR", this);
            throw new TypeCheckError(err);
        }
        if (argc > 0) {
            this.argument().typeCheck(stable);
        }
        return super._type = Type.String;
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
        if (!targ.identicalTo(Type.String)) {
            targ.translateTo(classGen, methodGen, Type.String);
        }
    }
}
