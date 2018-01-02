// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.INVOKEINTERFACE;
import org.apache.bcel.generic.INVOKESPECIAL;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.NEW;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.NodeType;
import org.apache.xalan.xsltc.compiler.util.Type;

final class AbsoluteLocationPath extends Expression
{
    private Expression _path;
    
    public AbsoluteLocationPath() {
        this._path = null;
    }
    
    public AbsoluteLocationPath(final Expression path) {
        this._path = path;
        if (path != null) {
            this._path.setParent(this);
        }
    }
    
    public void setParser(final Parser parser) {
        super.setParser(parser);
        if (this._path != null) {
            this._path.setParser(parser);
        }
    }
    
    public Expression getPath() {
        return this._path;
    }
    
    public String toString() {
        return "AbsoluteLocationPath(" + ((this._path != null) ? this._path.toString() : "null") + ')';
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        if (this._path != null) {
            final Type ptype = this._path.typeCheck(stable);
            if (ptype instanceof NodeType) {
                this._path = new CastExpr(this._path, Type.NodeSet);
            }
        }
        return super._type = Type.NodeSet;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (this._path != null) {
            final int initAI = cpg.addMethodref("org.apache.xalan.xsltc.dom.AbsoluteIterator", "<init>", "(Lorg/apache/xml/dtm/DTMAxisIterator;)V");
            il.append(new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.AbsoluteIterator")));
            il.append(InstructionConstants.DUP);
            this._path.translate(classGen, methodGen);
            il.append(new INVOKESPECIAL(initAI));
        }
        else {
            final int gitr = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getIterator", "()Lorg/apache/xml/dtm/DTMAxisIterator;");
            il.append(methodGen.loadDOM());
            il.append(new INVOKEINTERFACE(gitr, 1));
        }
    }
}
