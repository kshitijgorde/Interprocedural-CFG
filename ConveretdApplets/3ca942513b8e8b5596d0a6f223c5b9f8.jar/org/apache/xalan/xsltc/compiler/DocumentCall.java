// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.INVOKESTATIC;
import com.ibm.xslt4j.bcel.generic.GETFIELD;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import com.ibm.xslt4j.bcel.generic.CompoundInstruction;
import com.ibm.xslt4j.bcel.generic.PUSH;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import java.util.Vector;
import org.apache.xalan.xsltc.compiler.util.Type;

final class DocumentCall extends FunctionCall
{
    private Expression _arg1;
    private Expression _arg2;
    private Type _arg1Type;
    
    public DocumentCall(final QName fname, final Vector arguments) {
        super(fname, arguments);
        this._arg1 = null;
        this._arg2 = null;
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        final int ac = this.argumentCount();
        if (ac < 1 || ac > 2) {
            final ErrorMsg msg = new ErrorMsg("ILLEGAL_ARG_ERR", this);
            throw new TypeCheckError(msg);
        }
        if (this.getStylesheet() == null) {
            final ErrorMsg msg = new ErrorMsg("ILLEGAL_ARG_ERR", this);
            throw new TypeCheckError(msg);
        }
        this._arg1 = this.argument(0);
        if (this._arg1 == null) {
            final ErrorMsg msg = new ErrorMsg("DOCUMENT_ARG_ERR", this);
            throw new TypeCheckError(msg);
        }
        this._arg1Type = this._arg1.typeCheck(stable);
        if (this._arg1Type != Type.NodeSet && this._arg1Type != Type.String) {
            this._arg1 = new CastExpr(this._arg1, Type.String);
        }
        if (ac == 2) {
            this._arg2 = this.argument(1);
            if (this._arg2 == null) {
                final ErrorMsg msg = new ErrorMsg("DOCUMENT_ARG_ERR", this);
                throw new TypeCheckError(msg);
            }
            final Type arg2Type = this._arg2.typeCheck(stable);
            if (arg2Type.identicalTo(Type.Node)) {
                this._arg2 = new CastExpr(this._arg2, Type.NodeSet);
            }
            else if (!arg2Type.identicalTo(Type.NodeSet)) {
                final ErrorMsg msg2 = new ErrorMsg("DOCUMENT_ARG_ERR", this);
                throw new TypeCheckError(msg2);
            }
        }
        return super._type = Type.NodeSet;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int ac = this.argumentCount();
        final int domField = cpg.addFieldref(classGen.getClassName(), "_dom", "Lorg/apache/xalan/xsltc/DOM;");
        String docParamList = null;
        if (ac == 1) {
            docParamList = "(Ljava/lang/Object;Ljava/lang/String;Lorg/apache/xalan/xsltc/runtime/AbstractTranslet;Lorg/apache/xalan/xsltc/DOM;)Lorg/apache/xml/dtm/DTMAxisIterator;";
        }
        else {
            docParamList = "(Ljava/lang/Object;Lorg/apache/xml/dtm/DTMAxisIterator;Ljava/lang/String;Lorg/apache/xalan/xsltc/runtime/AbstractTranslet;Lorg/apache/xalan/xsltc/DOM;)Lorg/apache/xml/dtm/DTMAxisIterator;";
        }
        final int docIdx = cpg.addMethodref("org.apache.xalan.xsltc.dom.LoadDocument", "documentF", docParamList);
        this._arg1.translate(classGen, methodGen);
        if (this._arg1Type == Type.NodeSet) {
            this._arg1.startIterator(classGen, methodGen);
        }
        if (ac == 2) {
            this._arg2.translate(classGen, methodGen);
            this._arg2.startIterator(classGen, methodGen);
        }
        il.append(new PUSH(cpg, this.getStylesheet().getSystemId()));
        il.append(classGen.loadTranslet());
        il.append(InstructionConstants.DUP);
        il.append(new GETFIELD(domField));
        il.append(new INVOKESTATIC(docIdx));
    }
}
