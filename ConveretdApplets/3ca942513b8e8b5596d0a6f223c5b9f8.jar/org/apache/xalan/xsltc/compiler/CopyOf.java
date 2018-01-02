// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.INVOKEVIRTUAL;
import com.ibm.xslt4j.bcel.generic.INVOKESTATIC;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.ResultTreeType;
import org.apache.xalan.xsltc.compiler.util.ReferenceType;
import org.apache.xalan.xsltc.compiler.util.NodeSetType;
import org.apache.xalan.xsltc.compiler.util.NodeType;
import org.apache.xalan.xsltc.compiler.util.Type;
import org.apache.xalan.xsltc.compiler.util.Util;

final class CopyOf extends Instruction
{
    private Expression _select;
    
    public void display(final int indent) {
        this.indent(indent);
        Util.println("CopyOf");
        this.indent(indent + 4);
        Util.println("select " + this._select.toString());
    }
    
    public void parseContents(final Parser parser) {
        this._select = parser.parseExpression(this, "select", null);
        if (this._select.isDummy()) {
            this.reportError(this, parser, "REQUIRED_ATTR_ERR", "select");
        }
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        final Type tselect = this._select.typeCheck(stable);
        if (!(tselect instanceof NodeType) && !(tselect instanceof NodeSetType) && !(tselect instanceof ReferenceType)) {
            if (!(tselect instanceof ResultTreeType)) {
                this._select = new CastExpr(this._select, Type.String);
            }
        }
        return Type.Void;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final Type tselect = this._select.getType();
        final String CPY1_SIG = "(Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xml/serializer/SerializationHandler;)V";
        final int cpy1 = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "copy", "(Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xml/serializer/SerializationHandler;)V");
        final String CPY2_SIG = "(ILorg/apache/xml/serializer/SerializationHandler;)V";
        final int cpy2 = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "copy", "(ILorg/apache/xml/serializer/SerializationHandler;)V");
        final String getDoc_SIG = "()I";
        final int getDoc = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getDocument", "()I");
        if (tselect instanceof NodeSetType) {
            il.append(methodGen.loadDOM());
            this._select.translate(classGen, methodGen);
            this._select.startIterator(classGen, methodGen);
            il.append(methodGen.loadHandler());
            il.append(new INVOKEINTERFACE(cpy1, 3));
        }
        else if (tselect instanceof NodeType) {
            il.append(methodGen.loadDOM());
            this._select.translate(classGen, methodGen);
            il.append(methodGen.loadHandler());
            il.append(new INVOKEINTERFACE(cpy2, 3));
        }
        else if (tselect instanceof ResultTreeType) {
            this._select.translate(classGen, methodGen);
            il.append(InstructionConstants.DUP);
            il.append(new INVOKEINTERFACE(getDoc, 1));
            il.append(methodGen.loadHandler());
            il.append(new INVOKEINTERFACE(cpy2, 3));
        }
        else if (tselect instanceof ReferenceType) {
            this._select.translate(classGen, methodGen);
            il.append(methodGen.loadHandler());
            il.append(methodGen.loadCurrentNode());
            il.append(methodGen.loadDOM());
            final int copy = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "copy", "(Ljava/lang/Object;Lorg/apache/xml/serializer/SerializationHandler;ILorg/apache/xalan/xsltc/DOM;)V");
            il.append(new INVOKESTATIC(copy));
        }
        else {
            il.append(classGen.loadTranslet());
            this._select.translate(classGen, methodGen);
            il.append(methodGen.loadHandler());
            il.append(new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "characters", "(Ljava/lang/String;Lorg/apache/xml/serializer/SerializationHandler;)V")));
        }
    }
}
