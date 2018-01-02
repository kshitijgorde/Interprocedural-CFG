// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.InstructionList;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.MethodType;
import org.apache.xalan.xsltc.compiler.util.Type;

final class UnaryOpExpr extends Expression
{
    private Expression _left;
    
    public UnaryOpExpr(final Expression left) {
        (this._left = left).setParent(this);
    }
    
    public boolean hasPositionCall() {
        return this._left.hasPositionCall();
    }
    
    public boolean hasLastCall() {
        return this._left.hasLastCall();
    }
    
    public void setParser(final Parser parser) {
        super.setParser(parser);
        this._left.setParser(parser);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        final Type tleft = this._left.typeCheck(stable);
        final MethodType ptype = this.lookupPrimop(stable, "u-", new MethodType(Type.Void, tleft));
        if (ptype != null) {
            final Type arg1 = ptype.argsType().elementAt(0);
            if (!arg1.identicalTo(tleft)) {
                this._left = new CastExpr(this._left, arg1);
            }
            return super._type = ptype.resultType();
        }
        throw new TypeCheckError(this);
    }
    
    public String toString() {
        return "u-(" + this._left + ')';
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final InstructionList il = methodGen.getInstructionList();
        this._left.translate(classGen, methodGen);
        il.append(super._type.NEG());
    }
}
