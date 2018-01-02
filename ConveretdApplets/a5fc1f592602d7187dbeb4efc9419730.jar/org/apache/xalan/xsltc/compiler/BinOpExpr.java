// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.InstructionList;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.MethodType;
import org.apache.xalan.xsltc.compiler.util.Type;

final class BinOpExpr extends Expression
{
    public static final int PLUS = 0;
    public static final int MINUS = 1;
    public static final int TIMES = 2;
    public static final int DIV = 3;
    public static final int MOD = 4;
    private static final String[] Ops;
    private int _op;
    private Expression _left;
    private Expression _right;
    
    public BinOpExpr(final int op, final Expression left, final Expression right) {
        this._op = op;
        (this._left = left).setParent(this);
        (this._right = right).setParent(this);
    }
    
    public boolean hasPositionCall() {
        return this._left.hasPositionCall() || this._right.hasPositionCall();
    }
    
    public boolean hasLastCall() {
        return this._left.hasLastCall() || this._right.hasLastCall();
    }
    
    public void setParser(final Parser parser) {
        super.setParser(parser);
        this._left.setParser(parser);
        this._right.setParser(parser);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        final Type tleft = this._left.typeCheck(stable);
        final Type tright = this._right.typeCheck(stable);
        final MethodType ptype = this.lookupPrimop(stable, BinOpExpr.Ops[this._op], new MethodType(Type.Void, tleft, tright));
        if (ptype != null) {
            final Type arg1 = ptype.argsType().elementAt(0);
            if (!arg1.identicalTo(tleft)) {
                this._left = new CastExpr(this._left, arg1);
            }
            final Type arg2 = ptype.argsType().elementAt(1);
            if (!arg2.identicalTo(tright)) {
                this._right = new CastExpr(this._right, arg1);
            }
            return super._type = ptype.resultType();
        }
        throw new TypeCheckError(this);
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final InstructionList il = methodGen.getInstructionList();
        this._left.translate(classGen, methodGen);
        this._right.translate(classGen, methodGen);
        switch (this._op) {
            case 0: {
                il.append(super._type.ADD());
                break;
            }
            case 1: {
                il.append(super._type.SUB());
                break;
            }
            case 2: {
                il.append(super._type.MUL());
                break;
            }
            case 3: {
                il.append(super._type.DIV());
                break;
            }
            case 4: {
                il.append(super._type.REM());
                break;
            }
            default: {
                final ErrorMsg msg = new ErrorMsg("ILLEGAL_BINARY_OP_ERR", this);
                this.getParser().reportError(3, msg);
                break;
            }
        }
    }
    
    public String toString() {
        return BinOpExpr.Ops[this._op] + '(' + this._left + ", " + this._right + ')';
    }
    
    static {
        Ops = new String[] { "+", "-", "*", "/", "%" };
    }
}
