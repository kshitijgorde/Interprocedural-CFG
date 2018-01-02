// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.IFLT;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import java.util.Vector;

final class ContainsCall extends FunctionCall
{
    private Expression _base;
    private Expression _token;
    
    public ContainsCall(final QName fname, final Vector arguments) {
        super(fname, arguments);
        this._base = null;
        this._token = null;
    }
    
    public boolean isBoolean() {
        return true;
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        if (this.argumentCount() != 2) {
            throw new TypeCheckError("ILLEGAL_ARG_ERR", this.getName(), this);
        }
        this._base = this.argument(0);
        final Type baseType = this._base.typeCheck(stable);
        if (baseType != Type.String) {
            this._base = new CastExpr(this._base, Type.String);
        }
        this._token = this.argument(1);
        final Type tokenType = this._token.typeCheck(stable);
        if (tokenType != Type.String) {
            this._token = new CastExpr(this._token, Type.String);
        }
        return super._type = Type.Boolean;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        this.translateDesynthesized(classGen, methodGen);
        this.synthesize(classGen, methodGen);
    }
    
    public void translateDesynthesized(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        this._base.translate(classGen, methodGen);
        this._token.translate(classGen, methodGen);
        il.append(new INVOKEVIRTUAL(cpg.addMethodref("java.lang.String", "indexOf", "(Ljava/lang/String;)I")));
        super._falseList.add(il.append(new IFLT(null)));
    }
}
