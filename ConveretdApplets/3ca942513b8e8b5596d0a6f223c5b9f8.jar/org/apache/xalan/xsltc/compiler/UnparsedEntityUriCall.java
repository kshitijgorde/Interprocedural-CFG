// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.StringType;
import org.apache.xalan.xsltc.compiler.util.Type;
import java.util.Vector;

final class UnparsedEntityUriCall extends FunctionCall
{
    private Expression _entity;
    
    public UnparsedEntityUriCall(final QName fname, final Vector arguments) {
        super(fname, arguments);
        this._entity = this.argument();
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        final Type entity = this._entity.typeCheck(stable);
        if (!(entity instanceof StringType)) {
            this._entity = new CastExpr(this._entity, Type.String);
        }
        return super._type = Type.String;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(methodGen.loadDOM());
        this._entity.translate(classGen, methodGen);
        il.append(new INVOKEINTERFACE(cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getUnparsedEntityURI", "(Ljava/lang/String;)Ljava/lang/String;"), 2));
    }
}
