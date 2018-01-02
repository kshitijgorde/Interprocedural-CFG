// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.CHECKCAST;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.ObjectType;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import org.apache.xalan.xsltc.compiler.util.Type;
import java.util.Vector;

final class CastCall extends FunctionCall
{
    private String _className;
    private Expression _right;
    
    public CastCall(final QName fname, final Vector arguments) {
        super(fname, arguments);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        if (this.argumentCount() != 2) {
            throw new TypeCheckError(new ErrorMsg("ILLEGAL_ARG_ERR", this.getName(), this));
        }
        final Expression exp = this.argument(0);
        if (!(exp instanceof LiteralExpr)) {
            throw new TypeCheckError(new ErrorMsg("NEED_LITERAL_ERR", this.getName(), this));
        }
        this._className = ((LiteralExpr)exp).getValue();
        super._type = Type.newObjectType(this._className);
        this._right = this.argument(1);
        final Type tright = this._right.typeCheck(stable);
        if (tright != Type.Reference && !(tright instanceof ObjectType)) {
            throw new TypeCheckError(new ErrorMsg("DATA_CONVERSION_ERR", tright, super._type, this));
        }
        return super._type;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        this._right.translate(classGen, methodGen);
        il.append(new CHECKCAST(cpg.addClass(this._className)));
    }
}
