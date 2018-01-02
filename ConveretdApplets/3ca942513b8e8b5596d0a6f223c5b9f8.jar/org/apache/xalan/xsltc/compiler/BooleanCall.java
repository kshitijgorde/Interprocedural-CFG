// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import java.util.Vector;

final class BooleanCall extends FunctionCall
{
    private Expression _arg;
    
    public BooleanCall(final QName fname, final Vector arguments) {
        super(fname, arguments);
        this._arg = null;
        this._arg = this.argument(0);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        this._arg.typeCheck(stable);
        return super._type = Type.Boolean;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        this._arg.translate(classGen, methodGen);
        final Type targ = this._arg.getType();
        if (!targ.identicalTo(Type.Boolean)) {
            this._arg.startIterator(classGen, methodGen);
            targ.translateTo(classGen, methodGen, Type.Boolean);
        }
    }
}
