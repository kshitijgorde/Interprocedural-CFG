// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import org.apache.xalan.xsltc.compiler.util.Type;
import java.util.Vector;

final class ElementAvailableCall extends FunctionCall
{
    public ElementAvailableCall(final QName fname, final Vector arguments) {
        super(fname, arguments);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        if (this.argument() instanceof LiteralExpr) {
            return super._type = Type.Boolean;
        }
        final ErrorMsg err = new ErrorMsg("NEED_LITERAL_ERR", "element-available", this);
        throw new TypeCheckError(err);
    }
    
    public Object evaluateAtCompileTime() {
        return this.getResult() ? Boolean.TRUE : Boolean.FALSE;
    }
    
    public boolean getResult() {
        try {
            final LiteralExpr arg = (LiteralExpr)this.argument();
            final String qname = arg.getValue();
            final int index = qname.indexOf(58);
            final String localName = (index > 0) ? qname.substring(index + 1) : qname;
            return this.getParser().elementSupported(arg.getNamespace(), localName);
        }
        catch (ClassCastException e) {
            return false;
        }
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final boolean result = this.getResult();
        methodGen.getInstructionList().append(new PUSH(cpg, result));
    }
}
