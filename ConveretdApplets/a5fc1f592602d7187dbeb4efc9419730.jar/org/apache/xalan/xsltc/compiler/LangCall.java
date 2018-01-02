// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.INVOKESTATIC;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.ILOAD;
import org.apache.xalan.xsltc.compiler.util.FilterGenerator;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.StringType;
import java.util.Vector;
import org.apache.xalan.xsltc.compiler.util.Type;

final class LangCall extends FunctionCall
{
    private Expression _lang;
    private Type _langType;
    
    public LangCall(final QName fname, final Vector arguments) {
        super(fname, arguments);
        this._lang = this.argument(0);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        this._langType = this._lang.typeCheck(stable);
        if (!(this._langType instanceof StringType)) {
            this._lang = new CastExpr(this._lang, Type.String);
        }
        return Type.Boolean;
    }
    
    public Type getType() {
        return Type.Boolean;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int tst = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "testLanguage", "(Ljava/lang/String;Lorg/apache/xalan/xsltc/DOM;I)Z");
        this._lang.translate(classGen, methodGen);
        il.append(methodGen.loadDOM());
        if (classGen instanceof FilterGenerator) {
            il.append(new ILOAD(1));
        }
        else {
            il.append(methodGen.loadContextNode());
        }
        il.append(new INVOKESTATIC(tst));
    }
}
